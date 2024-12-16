package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import clases.usuarioC;
import conexion.conexion;

public class consultas_usuario extends conexion{
	
	public boolean guardarUsuario(usuarioC usuario) {
	    Connection con = null;
	    PreparedStatement pst = null;

	    try {
	        con = new conexion().conectar();
	        String sql = "INSERT INTO usuarios (usuario, contrasena, nombres, apellidos, correo) VALUES (?, ?, ?, ?, ?)";
	        pst = con.prepareStatement(sql);
	        pst.setString(1, usuario.getUsuario());
	        pst.setString(2, usuario.getContrasena());
	        pst.setString(3, usuario.getNombres());
	        pst.setString(4, usuario.getApellidos());
	        pst.setString(5, usuario.getCorreo());

	        int rows = pst.executeUpdate(); // Retorna el número de filas afectadas
	        return rows > 0; // Si se afecta al menos una fila, retorna true
	    } catch (SQLException e) {
	        System.err.println("Error al guardar el usuario: " + e.getMessage());
	        return false;
	    } finally {
	        try {
	            if (pst != null) pst.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	
	
	public boolean usuarioExiste(String usuario) {
	    Connection con = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;

	    try {
	        con = new conexion().conectar();
	        String sql = "SELECT COUNT(*) FROM usuarios WHERE usuario = ?";
	        pst = con.prepareStatement(sql);
	        pst.setString(1, usuario);
	        rs = pst.executeQuery();

	        if (rs.next()) {
	            return rs.getInt(1) > 0; // Retorna true si encuentra el usuario
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al verificar si el usuario existe: " + e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pst != null) pst.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return false; // Retorna false si no encuentra el usuario
	}

    
    
    
	public boolean usuarioDuplicado(String usuario, int idUsuario) {
	    Connection con = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    boolean duplicado = false;

	    try {
	        con = new conexion().conectar();
	        String sql = "SELECT COUNT(*) FROM usuarios WHERE usuario= ? AND id_usuarios != ?";
	        pst = con.prepareStatement(sql);
	        pst.setString(1, usuario);
	        pst.setInt(2, idUsuario); 
	        rs = pst.executeQuery();

	        if (rs.next()) {
	            duplicado = rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al verificar duplicado de usuario");
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pst != null) pst.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return duplicado;
	}


    
	public boolean actualizarUsuario(usuarioC usuario, int idUsuario) {
	    Connection con = null;
	    PreparedStatement pst = null;
	    boolean actualizado = false;

	    try {
	        con = new conexion().conectar();
	        String sql = "UPDATE usuarios SET usuario = ?, contrasena = ?, nombres = ?, apellidos = ?, correo = ? WHERE id_usuarios = ?";
	        pst = con.prepareStatement(sql);
	        pst.setString(1, usuario.getUsuario());
	        pst.setString(2, usuario.getContrasena());
	        pst.setString(3, usuario.getNombres());
	        pst.setString(4, usuario.getApellidos());
	        pst.setString(5, usuario.getCorreo());
	        pst.setInt(6, idUsuario);

	        actualizado = pst.executeUpdate() > 0;
	    } catch (SQLException e) {
	        System.err.println("Error al actualizar usuario");
	    } finally {
	        try {
	            if (pst != null) pst.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return actualizado;
	}

    
    
	public boolean usuarioDuplicadoEnOtroRegistro(String usuario, int idActual) {
	    Connection con = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;

	    try {
	        con = new conexion().conectar();
	        String sql = "SELECT COUNT(*) FROM usuarios WHERE usuario = ? AND id_usuarios != ?";
	        pst = con.prepareStatement(sql);
	        pst.setString(1, usuario);
	        pst.setInt(2, idActual);
	        rs = pst.executeQuery();

	        if (rs.next()) {
	            return rs.getInt(1) > 0; // Retorna true si encuentra registros duplicados en otro ID
	        }
	    } catch (SQLException e) {
	        System.err.println("Error al verificar usuario duplicado: " + e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pst != null) pst.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return false;
	}
	
	
	
	///////////////////
	
	public String obtenerCorreoPorUsuario(String usuario) {
	    String correo = null;
	    Connection con = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;

	    try {
	        con = new conexion().conectar();
	        String sql = "SELECT correo FROM usuarios WHERE usuario = ?";
	        pst = con.prepareStatement(sql);
	        pst.setString(1, usuario);
	        rs = pst.executeQuery();

	        if (rs.next()) {
	            correo = rs.getString("correo");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pst != null) pst.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return correo;
	}

	
	public boolean actualizarContrasena(String usuario, String nuevaContrasena) {
	    Connection con = null;
	    PreparedStatement pst = null;
	    boolean resultado = false;

	    try {
	        con = new conexion().conectar();
	        String sql = "UPDATE usuarios SET contrasena = ? WHERE usuario = ?";
	        pst = con.prepareStatement(sql);
	        pst.setString(1, nuevaContrasena); // Texto plano, considera usar hashing
	        pst.setString(2, usuario);

	        resultado = pst.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (pst != null) pst.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return resultado;
	}






}
