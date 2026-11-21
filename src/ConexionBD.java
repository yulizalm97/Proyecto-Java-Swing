import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private String url;
    private String user;
    private String password; 

    public ConexionBD(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password; 
    }

    // Se agregan 'throws' para que ConexionTester capture los errores y los muestre en el JTextArea
    public Connection conectar() throws ClassNotFoundException, SQLException {
        // 1. Carga explícita del driver (esto dispara ClassNotFoundException si falta el .jar)
        if (url.startsWith("jdbc:sqlite:")) {
            Class.forName("org.sqlite.JDBC");
        } else if (url.startsWith("jdbc:mysql:")) {
        Class.forName("com.mysql.cj.jdbc.Driver");
        } else {
        // Si escriben una URL inválida o de otro motor sin driver
        throw new ClassNotFoundException("Driver no soportado o URL inválida (debe iniciar con jdbc:mysql: o jdbc:sqlite:)");
        }

    return DriverManager.getConnection(url, user, password);
    }
}