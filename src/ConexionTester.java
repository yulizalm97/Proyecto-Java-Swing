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
    
    // Constructor
    public ConexionTester() {
        setTitle("Prueba de Conexión JDBC");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 5, 5));

        // 1. Inicializar los atributos declarados arriba
        lblUrl = new JLabel("URL:");
        txtUrl = new JTextField();

        lbUsuario = new JLabel("Usuario:");
        txtUsuario = new JTextField();

        lbPassword = new JLabel("Contraseña:");
        txtPassword = new JPasswordField();

        btnProbar = new JButton("Probar Conexión");

        // 2. Agregar esos mismos atributos al panel
        panel.add(lblUrl);
        panel.add(txtUrl);

        panel.add(lbUsuario);
        panel.add(txtUsuario);

        panel.add(lbPassword);
        panel.add(txtPassword);

        panel.add(new JLabel("")); // Espacio vacío para cuadrar la cuadrícula
        panel.add(btnProbar);

        add(panel, BorderLayout.NORTH);

        // 3. Inicializar el atributo txtResultado (sin repetir "JTextArea" adelante)
        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtResultado);
        add(scrollPane, BorderLayout.CENTER);

        // 4. Evento del botón
        btnProbar.addActionListener(e -> {
            String url = txtUrl.getText().trim();
            String usuario = txtUsuario.getText().trim();
            String password = new String(txtPassword.getPassword());

            ConexionBD conexionBD = new ConexionBD(url, usuario, password);

            // try-with-resources garantiza el cierre automático de 'conexion' (Requisito 4)
            try (Connection conexion = conexionBD.conectar()) {
                if (conexion != null && !conexion.isClosed()) {
                    // Color verde legible para conexión exitosa
                    txtResultado.setForeground(new Color(0, 128, 0));
                    txtResultado.setText("Conexión exitosa a la base de datos.");
                }
            } catch (ClassNotFoundException ex) {
                // Color rojo para fallo del driver
                txtResultado.setForeground(Color.RED);
                txtResultado.setText("Fallo de conexión: No se encontró el driver JDBC.\nDetalle técnico: " + ex.getMessage());
            } catch (SQLException ex) {
                // Color rojo para fallo de conexión o credenciales + e.getMessage()
                txtResultado.setForeground(Color.RED);
                txtResultado.setText("Fallo de conexión.\nDetalle técnico: " + ex.getMessage());
            }
        });
    }
}