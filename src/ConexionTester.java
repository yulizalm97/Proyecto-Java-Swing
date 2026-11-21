import javax.swing.*; // componentes visuales
import java.awt.*;    // organizadores de espacio layouts
 // para la conexión a la base de datos
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionTester extends JFrame {

    private JLabel lblUrl;
    private JLabel lbUsuario;
    private JLabel lbPassword;
    private JTextField txtUrl;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnProbar;
    private JTextArea txtResultado;