package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    private final String database = "tcws1";
    private final String usuario = "root";
    private final String contrasena = "mendoza11.";
    private final String global = "localhost";
    private final String url = "jdbc:mysql://" + global + "/" + database;
    public Connection conexion = null;

    public Connection conectar() {
        try {
            if (conexion == null || conexion.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(this.url, this.usuario, this.contrasena);
            }
        } catch (Exception e) {
            System.out.println("Error de conexión!");
            e.printStackTrace();
        }
        return conexion;
    }

    public void desconectar(Connection con) {
        if (con != null) {
            try {
                if (!con.isClosed()) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión!");
                e.printStackTrace();
            }
        }
    }
}
