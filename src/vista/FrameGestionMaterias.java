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

    /**
     * Creates new form FrameGestionMaterias
     */
    public FrameGestionMaterias() {
        initComponents();
        setBackground(new java.awt.Color(200, 216, 240));
        getContentPane().setBackground(new java.awt.Color(200, 216, 240));

        // Fix: sacar la tabla del scroll doble
        jScrollPane4.setViewportView(TablaMaterias);
        jScrollPane1.setViewportView(null);

        // Colores de la tabla
        TablaMaterias.setBackground(new java.awt.Color(46, 80, 140));
        TablaMaterias.setSelectionBackground(new java.awt.Color(107, 79, 163));
        TablaMaterias.getTableHeader().setBackground(new java.awt.Color(27, 58, 107));
        TablaMaterias.getTableHeader().setForeground(java.awt.Color.WHITE);

        // Limpiar items hardcodeados de la lista de alertas
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
        jScrollPane4.setViewportView(TablaMaterias);
        TablaMaterias.setBackground(new java.awt.Color(46, 80, 140));
        TablaMaterias.setSelectionBackground(new java.awt.Color(107, 79, 163));
        TablaMaterias.getTableHeader().setBackground(new java.awt.Color(27, 58, 107));
        TablaMaterias.getTableHeader().setForeground(java.awt.Color.WHITE);
        Alertas.setModel(new javax.swing.DefaultListModel<>());
        Alertas.setBackground(new java.awt.Color(46, 80, 140));
        Alertas.setForeground(java.awt.Color.WHITE);
        actualizarTabla();
 
        getBtnBaja().addActionListener(e -> {
            int fila = getTablaMaterias().getSelectedRow();
            if (fila < 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "Seleccioná una materia.");
                return;
            }
            String nombre = getTablaMaterias().getValueAt(fila, 0).toString();
            String codigo = getTablaMaterias().getValueAt(fila, 1).toString();
            int conf = javax.swing.JOptionPane.showConfirmDialog(this,
                "¿Confirmás la baja de '" + nombre + "' (" + codigo + ")?",
                "Confirmar Baja", javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.WARNING_MESSAGE);
            if (conf == javax.swing.JOptionPane.YES_OPTION) {
                controlador.darDeBaja(codigo);
                actualizarTabla();
            }
        });
 
        getBtnInscribirse().addActionListener(e ->
            abrirFrame(new FrameInscribirMateria(controlador), 600, 400, 150, 80));
 
        ItemPerfil.addActionListener(e -> abrirFrame(new FramePerfil(controlador), 550, 420, 100, 50));
        ItemAsistencia.addActionListener(e -> abrirFrame(new FrameAsistencias(controlador), 650, 400, 150, 80));
        ItemCalificacion.addActionListener(e -> abrirFrame(new FrameRegistrarCalificacion(controlador), 650, 400, 150, 80));
        ItemSitGeneral.addActionListener(e -> abrirFrame(new frameSituacionGeneral(controlador), 750, 500, 100, 50));
        ItemMatRiesgo.addActionListener(e -> abrirFrame(new FrameMateriasEnRiesgo(controlador), 550, 400, 150, 80));
        ItemMatAprobadas.addActionListener(e -> abrirFrame(new FrameMateriasAprobadas(controlador), 550, 400, 150, 80));
        ItemSalir.addActionListener(e -> System.exit(0));
        btnVolver.addActionListener(e -> {
        FrameBienvenida f = new FrameBienvenida(controlador);
        f.setSize(500, 300);
        f.setLocation(300, 200);
        this.getDesktopPane().add(f);
        f.setVisible(true);
        this.dispose();
});
    }
 
    private void actualizarTabla() {
        javax.swing.table.DefaultTableModel modelo =
            (javax.swing.table.DefaultTableModel) getTablaMaterias().getModel();
        modelo.setRowCount(0);
        for (modelo.InscripcionMateria i : controlador.getEstudiante().getMaterias()) {
            modelo.addRow(new Object[]{
                i.getMateria().getNombre(), i.getMateria().getCodigo(),
                i.getMateria().getCuatrimestre(), i.getMateria().getAnio(),
                i.getCondicion(),
                String.format("%.1f%%", i.getPorcentajeAsistencia()),
                String.format("%.2f", i.getNota())
            });
        }
        javax.swing.DefaultListModel<String> modeloLista = new javax.swing.DefaultListModel<>();
        for (modelo.InscripcionMateria i : controlador.getEstudiante().getMateriasCriticas())
            modeloLista.addElement(i.getMateria().getNombre()
                + " - " + String.format("%.1f%%", i.getPorcentajeAsistencia()));
        getAlertas().setModel(modeloLista);
        getTablaMaterias().revalidate();
        getTablaMaterias().repaint();
    }
 
    private void abrirFrame(javax.swing.JInternalFrame frame, int w, int h, int x, int y) {
        frame.setSize(w, h); frame.setLocation(x, y);
        this.getDesktopPane().add(frame);
        frame.setVisible(true); this.dispose();
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
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaMaterias = new javax.swing.JTable();
        btnBaja = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        btnInscribirse = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
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

        btnBaja.setBackground(new java.awt.Color(107, 79, 163));
        btnBaja.setForeground(new java.awt.Color(255, 255, 255));
        btnBaja.setText("Dar De Baja");

        btnVolver.setText("Volver al Inicio");

        btnInscribirse.setBackground(new java.awt.Color(107, 79, 163));
        btnInscribirse.setForeground(new java.awt.Color(255, 255, 255));
        btnInscribirse.setText("Inscribirse a la Materia");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(107, 79, 163));
        jLabel2.setText("Gestion de Materias");

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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(btnBaja)
                        .addGap(30, 30, 30)
                        .addComponent(btnInscribirse))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(btnVolver))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(314, 314, 314)
                        .addComponent(jLabel2)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBaja)
                    .addComponent(btnInscribirse))
                .addGap(41, 41, 41)
                .addComponent(btnVolver)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


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
    private javax.swing.JButton btnInscribirse;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
public javax.swing.JTable getTablaMaterias() { return TablaMaterias; }
public javax.swing.JList<String> getAlertas() { return Alertas; }
public javax.swing.JButton getBtnBaja() { return btnBaja; }
public javax.swing.JButton getBtnInscribirse() { return btnInscribirse; }

}
