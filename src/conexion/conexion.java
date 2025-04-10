package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    private final String database = "tcws1";
    private final String usuario = "root";
    private final String contrasena = "";
    private final String global = "localhost";
    private final String url = "jdbc:mysql://" + global + "/" + database;

    // Método para establecer conexión
    public Connection conectar() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(this.url, this.usuario, this.contrasena);
        } catch (Exception e) {
            System.out.println("Error al conectar a la base de datos!");
            e.printStackTrace();
        }
        return conexion;
    }

    // Método para cerrar conexión
    public void desconectar(Connection conexion) {
        if (conexion != null) {
            try {
                if (!conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión!");
                e.printStackTrace();
            }
        }
    }
    
    public class TestConnection {
        public static void main(String[] args) {
            String url = "jdbc:mysql://localhost:3306/tcws1?useSSL=false";
            String user = "root";
            String password = ""; // Contraseña vacía

            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                if (conn != null) {
                    System.out.println("Conexión exitosa a la base de datos.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
    }
    
    
   

}
