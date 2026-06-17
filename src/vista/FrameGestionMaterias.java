/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vista;

/**
 *
 * @author laram
 */
public class FrameGestionMaterias extends javax.swing.JInternalFrame {
 
    public FrameGestionMaterias() {
        initComponents();
        setBackground(new java.awt.Color(200, 216, 240));
        getContentPane().setBackground(new java.awt.Color(200, 216, 240));
        jScrollPane1.setViewportView(TablaMaterias);
        jScrollPane4.setViewportView(jScrollPane1);
        TablaMaterias.setBackground(new java.awt.Color(46, 80, 140));
        TablaMaterias.setSelectionBackground(new java.awt.Color(107, 79, 163));
        TablaMaterias.getTableHeader().setBackground(new java.awt.Color(27, 58, 107));
        TablaMaterias.getTableHeader().setForeground(java.awt.Color.WHITE);
        Alertas.setModel(new javax.swing.DefaultListModel<>());
        Alertas.setBackground(new java.awt.Color(46, 80, 140));
        Alertas.setForeground(java.awt.Color.WHITE);
    }
 
    private controlador.Controlador controlador;
 
    public FrameGestionMaterias(controlador.Controlador controlador) {
        initComponents();
        this.controlador = controlador;
        setBackground(new java.awt.Color(200, 216, 240));
        getContentPane().setBackground(new java.awt.Color(200, 216, 240));
        jScrollPane1.setViewportView(TablaMaterias);
        jScrollPane4.setViewportView(jScrollPane1);
        TablaMaterias.setBackground(new java.awt.Color(46, 80, 140));
        TablaMaterias.setSelectionBackground(new java.awt.Color(107, 79, 163));
        TablaMaterias.getTableHeader().setBackground(new java.awt.Color(27, 58, 107));
        TablaMaterias.getTableHeader().setForeground(java.awt.Color.WHITE);
        Alertas.setModel(new javax.swing.DefaultListModel<>());
        Alertas.setBackground(new java.awt.Color(46, 80, 140));
        Alertas.setForeground(java.awt.Color.WHITE);
 
        TablaMaterias.getColumnModel().getColumn(0).setPreferredWidth(120);
        TablaMaterias.getColumnModel().getColumn(1).setPreferredWidth(60);
        TablaMaterias.getColumnModel().getColumn(2).setPreferredWidth(80);
        TablaMaterias.getColumnModel().getColumn(3).setPreferredWidth(50);
        TablaMaterias.getColumnModel().getColumn(4).setPreferredWidth(70);
        TablaMaterias.getColumnModel().getColumn(5).setPreferredWidth(80);
        TablaMaterias.getColumnModel().getColumn(6).setPreferredWidth(70);
        TablaMaterias.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        TablaMaterias.setBorder(null);
 
        
        // CardLayout: navega entre vista tabla y vista búsqueda
        javax.swing.JPanel panelCards = new javax.swing.JPanel(new java.awt.CardLayout());
        panelCards.setOpaque(false);

        javax.swing.JPanel cardTabla = new javax.swing.JPanel();
        cardTabla.setOpaque(false);
        javax.swing.JLabel lblTabla = new javax.swing.JLabel("Todas las materias");
        lblTabla.setForeground(new java.awt.Color(107, 79, 163));
        cardTabla.add(lblTabla);

        javax.swing.JPanel cardBusqueda = new javax.swing.JPanel();
        cardBusqueda.setOpaque(false);
        javax.swing.JLabel lblBusqueda = new javax.swing.JLabel("Resultados de búsqueda");
        lblBusqueda.setForeground(new java.awt.Color(107, 79, 163));
        cardBusqueda.add(lblBusqueda);

        panelCards.add(cardTabla, "TABLA");
        panelCards.add(cardBusqueda, "BUSQUEDA");
        jPanel1.add(panelCards);

        final java.awt.CardLayout cl = (java.awt.CardLayout) panelCards.getLayout();
        cl.show(panelCards, "TABLA");
        // Mostrar carta activa en el título
        jLabel2.setText("Gestión de Materias — Todas las materias");
        // Limpiar placeholder y conectar búsqueda
        txtBusqueda.setText("");
        
            btnBuscar.addActionListener(e -> {
        String termino = txtBusqueda.getText().trim();
        javax.swing.table.DefaultTableModel m =
            (javax.swing.table.DefaultTableModel) TablaMaterias.getModel();
        if (termino.isEmpty()) {
            cl.show(panelCards, "TABLA");
            jLabel2.setText("Gestión de Materias — Todas las materias");
            
            actualizarTabla();
            return;
        }
        cl.show(panelCards, "BUSQUEDA");
        jLabel2.setText("Gestión de Materias — Resultados de búsqueda");
        m.setRowCount(0); // ← AGREGAR ESTA LÍNEA
        java.util.List<modelo.InscripcionMateria> resultados =
            controlador.buscarMateriaBonus(termino);
        for (modelo.InscripcionMateria i : resultados) {
            m.addRow(new Object[]{
                i.getMateria().getNombre(),
                i.getMateria().getCodigo(),
                i.getMateria().getCuatrimestre(),
                i.getMateria().getAnio(),
                i.getCondicion(),
                String.format("%.1f%%", i.getPorcentajeAsistencia()),
                String.format("%.2f", i.getNota())
            });
        }
        if (resultados.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "No se encontraron materias con '" + termino + "'.",
                "Sin resultados", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            cl.show(panelCards, "TABLA");
            jLabel2.setText("Gestión de Materias — Todas las materias");
            actualizarTabla();
        }
    });
 
        // Dar de baja
        btnBaja.addActionListener(e -> {
            int fila = TablaMaterias.getSelectedRow();
            if (fila < 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "Seleccioná una materia.");
                return;
            }
            String nombre = TablaMaterias.getValueAt(fila, 0).toString();
            String codigo = TablaMaterias.getValueAt(fila, 1).toString();
            int conf = javax.swing.JOptionPane.showConfirmDialog(this,
                "¿Confirmás la baja de '" + nombre + "' (" + codigo + ")?",
                "Confirmar Baja", javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.WARNING_MESSAGE);
            if (conf == javax.swing.JOptionPane.YES_OPTION) {
                controlador.darDeBaja(codigo);
                actualizarTabla();
            }
        });
 
        // Inscribirse
        btnInscribirse.addActionListener(e ->
            abrirFrame(new FrameInscribirMateria(controlador), 600, 400, 150, 80));
 
        // Menú
        ItemPerfil.addActionListener(e -> abrirFrame(new FramePerfil(controlador), 550, 420, 100, 50));
        ItemAsistencia.addActionListener(e -> abrirFrame(new FrameAsistencias(controlador), 650, 400, 150, 80));
        ItemCalificacion.addActionListener(e -> abrirFrame(new FrameRegistrarCalificacion(controlador), 650, 400, 150, 80));
        ItemSitGeneral.addActionListener(e -> abrirFrame(new frameSituacionGeneral(controlador), 750, 500, 100, 50));
        ItemMatRiesgo.addActionListener(e -> abrirFrame(new FrameMateriasEnRiesgo(controlador), 550, 400, 150, 80));
        ItemMatAprobadas.addActionListener(e -> abrirFrame(new FrameMateriasAprobadas(controlador), 550, 400, 150, 80));
        ItemSalir.addActionListener(e -> System.exit(0));
 
        // Volver
        btnVolver.addActionListener(e -> {
            FrameBienvenida f = new FrameBienvenida(controlador);
            f.pack();
            f.setLocation(
                (this.getDesktopPane().getWidth() - f.getWidth()) / 2,
                (this.getDesktopPane().getHeight() - f.getHeight()) / 2
            );
            this.getDesktopPane().add(f);
            f.setVisible(true);
            this.dispose();
        });
 
        actualizarTabla();
    }
 
    private void actualizarTabla() {
        javax.swing.table.DefaultTableModel modelo =
            (javax.swing.table.DefaultTableModel) TablaMaterias.getModel();
        modelo.setRowCount(0);
        for (modelo.InscripcionMateria i : controlador.getEstudiante().getMaterias()) {
            modelo.addRow(new Object[]{
                i.getMateria().getNombre(),
                i.getMateria().getCodigo(),
                i.getMateria().getCuatrimestre(),
                i.getMateria().getAnio(),
                i.getCondicion(),
                String.format("%.1f%%", i.getPorcentajeAsistencia()),
                String.format("%.2f", i.getNota())
            });
        }
        javax.swing.DefaultListModel<String> modeloLista = new javax.swing.DefaultListModel<>();
        for (modelo.InscripcionMateria i : controlador.getEstudiante().getMateriasCriticas())
            modeloLista.addElement(i.getMateria().getNombre()
                + " - " + String.format("%.1f%%", i.getPorcentajeAsistencia()));
        Alertas.setModel(modeloLista);
        TablaMaterias.revalidate();
        TablaMaterias.repaint();
        if (modelo.getRowCount() == 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "No hay materias inscriptas aún.",
                "Sin materias", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }
 
    private void abrirFrame(javax.swing.JInternalFrame frame, int w, int h, int x, int y) {
        frame.pack();
        frame.setLocation(
            (this.getDesktopPane().getWidth() - frame.getWidth()) / 2,
            (this.getDesktopPane().getHeight() - frame.getHeight()) / 2
        );
        this.getDesktopPane().add(frame);
        frame.setVisible(true);
        this.dispose();
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        Alertas = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        btnBaja = new javax.swing.JButton();
        btnInscribirse = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaMaterias = new javax.swing.JTable();
        txtBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu10 = new javax.swing.JMenu();
        ItemPerfil = new javax.swing.JMenuItem();
        ItemAsistencia = new javax.swing.JMenuItem();
        ItemGestionM = new javax.swing.JMenuItem();
        ItemCalificacion = new javax.swing.JMenuItem();
        Reportes = new javax.swing.JMenu();
        ItemSitGeneral = new javax.swing.JMenuItem();
        ItemMatRiesgo = new javax.swing.JMenuItem();
        ItemMatAprobadas = new javax.swing.JMenuItem();
        ItemSalir = new javax.swing.JMenuItem();

        Alertas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(Alertas);

        setBackground(new java.awt.Color(200, 216, 240));

        btnBaja.setBackground(new java.awt.Color(107, 79, 163));
        btnBaja.setForeground(new java.awt.Color(255, 255, 255));
        btnBaja.setText("Dar De Baja");

        btnInscribirse.setBackground(new java.awt.Color(107, 79, 163));
        btnInscribirse.setForeground(new java.awt.Color(255, 255, 255));
        btnInscribirse.setText("Inscribirse a la Materia");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(107, 79, 163));
        jLabel2.setText("Gestion de Materias");

        btnVolver.setText("Volver al Inicio");
        btnVolver.addActionListener(this::btnVolverActionPerformed);

        TablaMaterias.setBorder(javax.swing.BorderFactory.createTitledBorder("Materias Inscriptas"));
        TablaMaterias.setForeground(new java.awt.Color(255, 255, 255));
        TablaMaterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Codigo", "Cuatrimetre", "Año", "Condicion", "Asistencia", "Promedio"
            }
        ));
        TablaMaterias.setToolTipText("");
        jScrollPane1.setViewportView(TablaMaterias);

        jScrollPane4.setViewportView(jScrollPane1);

        txtBusqueda.setText("jTextField1");
        txtBusqueda.addActionListener(this::txtBusquedaActionPerformed);

        btnBuscar.setText("Buscar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(jLabel2))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(btnBuscar))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnBaja)
                                .addGap(27, 27, 27)
                                .addComponent(btnInscribirse)
                                .addGap(56, 56, 56)
                                .addComponent(btnVolver)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInscribirse)
                    .addComponent(btnBaja)
                    .addComponent(btnVolver))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jMenuBar2.setBackground(new java.awt.Color(200, 216, 240));

        jMenu10.setText("Menu");

        ItemPerfil.setText("Mi Perfil");
        jMenu10.add(ItemPerfil);

        ItemAsistencia.setText("Registrar Asistencia");
        jMenu10.add(ItemAsistencia);

        ItemGestionM.setText("Gestion de Materias");
        jMenu10.add(ItemGestionM);

        ItemCalificacion.setText("Registrar Calificacion");
        jMenu10.add(ItemCalificacion);

        Reportes.setText("Reportes Academicos");

        ItemSitGeneral.setText("Situacion General");
        Reportes.add(ItemSitGeneral);

        ItemMatRiesgo.setText("Materias en Riesgo");
        Reportes.add(ItemMatRiesgo);

        ItemMatAprobadas.setText("Materias Aprobadas");
        Reportes.add(ItemMatAprobadas);

        jMenu10.add(Reportes);

        ItemSalir.setText("Salir");
        jMenu10.add(ItemSalir);

        jMenuBar2.add(jMenu10);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVolverActionPerformed

    private void txtBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBusquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusquedaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> Alertas;
    private javax.swing.JMenuItem ItemAsistencia;
    private javax.swing.JMenuItem ItemCalificacion;
    private javax.swing.JMenuItem ItemGestionM;
    private javax.swing.JMenuItem ItemMatAprobadas;
    private javax.swing.JMenuItem ItemMatRiesgo;
    private javax.swing.JMenuItem ItemPerfil;
    private javax.swing.JMenuItem ItemSalir;
    private javax.swing.JMenuItem ItemSitGeneral;
    private javax.swing.JMenu Reportes;
    private javax.swing.JTable TablaMaterias;
    private javax.swing.JButton btnBaja;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnInscribirse;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
    public javax.swing.JTable getTablaMaterias() { return TablaMaterias; }
    public javax.swing.JList<String> getAlertas() { return Alertas; }
    public javax.swing.JButton getBtnBaja() { return btnBaja; }
    public javax.swing.JButton getBtnInscribirse() { return btnInscribirse; }

}
