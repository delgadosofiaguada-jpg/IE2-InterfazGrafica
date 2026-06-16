import vista.VistaPrincipalFrame;
import vista.FrameMateriasAprobadas;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VistaPrincipalFrame ventana = new VistaPrincipalFrame();
            ventana.setSize(1100, 750);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);

            FrameMateriasAprobadas bienvenida = new FrameMateriasAprobadas();
            ventana.getDesktop().add(bienvenida);
            bienvenida.setSize(500, 300);
            bienvenida.setLocation(300, 200);
            bienvenida.setVisible(true);
        });
    }
}