import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        // Inicia la interfaz gráfica de forma segura
        SwingUtilities.invokeLater(() -> {
            ConexionTester ventana = new ConexionTester();
            ventana.setVisible(true);
        });
    }
}
