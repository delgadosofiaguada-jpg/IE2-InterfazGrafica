import vista.VistaPrincipalFrame;
import vista.FrameBienvenida;
import controlador.Controlador;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Controlador controlador = new Controlador();
            VistaPrincipalFrame ventana = new VistaPrincipalFrame();
            ventana.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);

            FrameBienvenida bienvenida = new FrameBienvenida(controlador);
            ventana.getDesktop().add(bienvenida);
            bienvenida.pack();
            javax.swing.SwingUtilities.invokeLater(() -> {
            bienvenida.setLocation(
                (ventana.getDesktop().getWidth() - bienvenida.getWidth()) / 2,
                (ventana.getDesktop().getHeight() - bienvenida.getHeight()) / 2
            );
});
            bienvenida.setVisible(true);
        });
    }
}
