/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.Controlador;
import modelo.InscripcionMateria;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VistaPrincipal extends JFrame {

    // Colores del diseño Figma
    public static final Color AZUL_OSCURO  = new Color(0x1B, 0x3A, 0x6B);
    public static final Color AZUL_PANEL   = new Color(0x2E, 0x4C, 0x80);
    public static final Color VIOLETA_BTN  = new Color(0x6B, 0x4F, 0xA3);
    public static final Color BLANCO       = Color.WHITE;
    
    private Controlador controlador;
    private CardLayout cardLayout;
    private JPanel panelCentral;
    
    //Perfil
     private JLabel lblNombre, lblLegajo, lblCarrera, lblAnio, lblPromedio, lblEstado;
    
     //tabla y lista
    private JTable tablaMaterias;
    private DefaultTableModel modeloTabla;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaAlertas;
      // Reporte 
    private JTextArea areaReporte;

    // Campos de formulario — 
    public JTextField txtNombre, txtCodigo, txtCuatrimestre, txtAnio;
    public JTextField txtCodigoAccion, txtNota;
    public JCheckBox chkPresente;
    
    public VistaPrincipal() {
        super("Sistema de Autogestion Estudiantil — IES Siglo 21");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 680);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(AZUL_OSCURO);

        initMenuBar();
        initPanelNorte();
        initPanelCentral();

        setVisible(true);
    }
    
     public void setControlador(Controlador c) { this.controlador = c; }

    // MENÚ (Martina)
    private void initMenuBar() {
        JMenuBar bar = new JMenuBar();
        bar.setBackground(AZUL_OSCURO);

        JMenu mArchivo = new JMenu("Archivo");
        mArchivo.setForeground(BLANCO);
        JMenuItem cerrar = new JMenuItem("Cerrar");
        cerrar.addActionListener(e -> System.exit(0));
        mArchivo.add(cerrar);

        JMenu mReportes = new JMenu("Reportes");
        mReportes.setForeground(BLANCO);

        JMenuItem iSit = new JMenuItem("Situacion General");
        iSit.addActionListener(e -> {
            controlador.generarReporteSituacion();
            cardLayout.show(panelCentral, "reportes");
        });
    }
    
    // PANEL NORTE — barra azul con perfil 
    private void initPanelNorte() {
        JPanel norte = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 10));
        norte.setBackground(AZUL_OSCURO);

        JLabel titulo = new JLabel("IES SIGLO 21");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 14));
        titulo.setForeground(new Color(0xC0, 0xA0, 0xFF)); // violeta claro
        norte.add(titulo);

        lblNombre   = new JLabel("Nombre: -");
        lblLegajo   = new JLabel("Legajo: -");
        lblCarrera  = new JLabel("Carrera: -");
        lblAnio     = new JLabel("Ingreso: -");
        lblPromedio = new JLabel("Promedio: 0.00");
        
        for (JLabel l : new JLabel[]{lblNombre, lblLegajo, lblCarrera, lblAnio, lblPromedio}) {
            l.setForeground(BLANCO);
            l.setFont(new Font("SansSerif", Font.BOLD, 13));
            norte.add(l);
        }

        add(norte, BorderLayout.NORTH);
    }
    
    // CARD LAYOUT — navegación entre pantallas (Martina)
    private void initPanelCentral() {
        cardLayout = new CardLayout();
        panelCentral = new JPanel(cardLayout);
        panelCentral.setBackground(AZUL_OSCURO);

        panelCentral.add(buildPanelPrincipal(), "principal");
        panelCentral.add(buildPanelReportes(),  "reportes");

        add(panelCentral, BorderLayout.CENTER);
    }
    
    
    
    
    
    
    
}