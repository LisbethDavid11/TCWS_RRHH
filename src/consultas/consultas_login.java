package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import clases.usuarioC;
import conexion.conexion;

public class consultas_login extends conexion{
	
	// Método para validar el usuarioC
    public boolean validarUsuario(String usuario, String contrasena) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = conectar();
            String sql = "SELECT contrasena FROM usuarios WHERE usuario = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, usuario);
            rs = pst.executeQuery();

            if (rs.next()) {
                String contrasenaEnBase = rs.getString("contrasena");
                return contrasena.equals(contrasenaEnBase); // Comparación en texto plano
            }

            return false; // Usuario no encontrado
        } catch (SQLException e) {
            System.err.println("Error al validar usuarioC");
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                desconectar(con);
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos");
            }
        }
    }

    // Método para obtener el nombre del usuarioC
    public String obtenerNombreUsuario(String usuario) {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = conectar();
            String sql = "SELECT nombres FROM usuarios WHERE usuario = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, usuario);
            rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getString("nombres"); // Devuelve el nombre del usuarioC
            }

            return null; // Si no se encuentra el usuarioC
        } catch (SQLException e) {
            System.err.println("Error al obtener nombre del usuarioC" );
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                desconectar(con);
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
    
    
    

	

}
