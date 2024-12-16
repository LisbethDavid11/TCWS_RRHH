package consultas;

import conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class consultas_roles extends conexion {

	public ArrayList<String> obtenerUsuarios() {
	    ArrayList<String> usuarios = new ArrayList<>();
	    String sql = "SELECT usuario FROM usuarios";

	    try (Connection con = conectar();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            usuarios.add(rs.getString("usuario")); // Agregar cada usuario a la lista
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al obtener los usuarios.");
	        e.printStackTrace();
	    }

	    return usuarios;
	}


    // Obtener contraseña de un usuario específico
    public String obtenerContrasenaUsuario(String usuario) {
        String contrasena = "";
        String sql = "SELECT contrasena FROM usuarios WHERE usuario = ?";

        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    contrasena = rs.getString("contrasena");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la contraseña del usuario: " + e.getMessage());
        }

        return contrasena;
    }

    // Validar si un usuario tiene un rol asignado
    public boolean usuarioTieneRol(String usuario) {
        String sql = "SELECT COUNT(*) AS total FROM roles_usuarios WHERE nombre_usuario = ?";

        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total") > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar si el usuario tiene un rol: " + e.getMessage());
        }
        return false;
    }

    // Guardar un nuevo rol
    public boolean guardarRol(String nombreUsuario, String contrasena, String nombreRol, String descripcionRol,
                               boolean empleados, boolean ausenciaLaboral, boolean incapacidades,
                               boolean vacaciones, boolean cargos, boolean areas, boolean reportes,
                               boolean respaldos, boolean usuarios) {
        String sql = "INSERT INTO roles_usuarios (nombre_usuario, contrasena, nombre_rol, descripcion_rol, "
                   + "permisos_empleados, permisos_ausencia_laboral, permisos_incapacidades, permisos_vacaciones, "
                   + "permisos_cargos, permisos_areas, permisos_reportes, permisos_respaldos, permisos_usuarios) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasena);
            ps.setString(3, nombreRol);
            ps.setString(4, descripcionRol);
            ps.setBoolean(5, empleados);
            ps.setBoolean(6, ausenciaLaboral);
            ps.setBoolean(7, incapacidades);
            ps.setBoolean(8, vacaciones);
            ps.setBoolean(9, cargos);
            ps.setBoolean(10, areas);
            ps.setBoolean(11, reportes);
            ps.setBoolean(12, respaldos);
            ps.setBoolean(13, usuarios);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al guardar el rol: " + e.getMessage());
            return false;
        }
    }

    // Obtener permisos específicos de un usuario
    public boolean tienePermiso(String nombreUsuario, String permiso) {
        String sql = "SELECT " + permiso + " FROM roles_usuarios WHERE nombre_usuario = ?";

        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombreUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar el permiso del usuario: " + e.getMessage());
        }

        return false;
    }

    // Obtener nombre completo del usuario
    public String obtenerNombreCompleto(String usuario) {
        String sql = "SELECT CONCAT(nombres, ' ', apellidos) AS nombre_completo FROM usuarios WHERE usuario = ?";

        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nombre_completo");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el nombre completo del usuario: " + e.getMessage());
        }

        return null;
    }

    // Validar credenciales de usuario
    public boolean validarCredenciales(String usuario, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?";

        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, contrasena);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Retorna true si existe un registro
            }
        } catch (SQLException e) {
            System.err.println("Error al validar las credenciales del usuario: " + e.getMessage());
        }

        return false;
    }

    // Obtener todos los roles
    public List<Object[]> obtenerRoles() {
        List<Object[]> listaRoles = new ArrayList<>();
        String sql = "SELECT id_rol, nombre_usuario, nombre_rol, descripcion_rol, permisos_empleados, permisos_ausencia_laboral, permisos_incapacidades, "
                   + "permisos_vacaciones, permisos_cargos, permisos_areas, permisos_reportes, permisos_respaldos, permisos_usuarios, fecha_creacion "
                   + "FROM roles_usuarios";

        try (Connection con = conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] fila = new Object[14];
                fila[0] = rs.getInt("id_rol");
                fila[1] = rs.getString("nombre_usuario");
                fila[2] = rs.getString("nombre_rol");
                fila[3] = rs.getString("descripcion_rol");
                fila[4] = rs.getBoolean("permisos_empleados");
                fila[5] = rs.getBoolean("permisos_ausencia_laboral");
                fila[6] = rs.getBoolean("permisos_incapacidades");
                fila[7] = rs.getBoolean("permisos_vacaciones");
                fila[8] = rs.getBoolean("permisos_cargos");
                fila[9] = rs.getBoolean("permisos_areas");
                fila[10] = rs.getBoolean("permisos_reportes");
                fila[11] = rs.getBoolean("permisos_respaldos");
                fila[12] = rs.getBoolean("permisos_usuarios");
                fila[13] = rs.getTimestamp("fecha_creacion");

                listaRoles.add(fila);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener los roles: " + e.getMessage());
        }

        return listaRoles;
    }

    // Actualizar rol existente
    public boolean actualizarRol(String usuario, String contrasena, String nombreRol, String descripcion,
            boolean empleados, boolean permisos, boolean incapacidades, boolean vacaciones,
            boolean cargos, boolean areas, boolean reportes, boolean respaldos, boolean usuarios) {
			Connection con = null;
			PreparedStatement ps = null;
			
			try {
			con = conectar();
			String sql = "UPDATE roles_usuarios SET contrasena = ?, nombre_rol = ?, descripcion = ?, empleados = ?, " +
			   "permisos = ?, incapacidades = ?, vacaciones = ?, cargos = ?, areas = ?, reportes = ?, " +
			   "respaldos = ?, usuarios = ? WHERE nombre_usuario = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, contrasena);
			ps.setString(2, nombreRol);
			ps.setString(3, descripcion);
			ps.setBoolean(4, empleados);
			ps.setBoolean(5, permisos);
			ps.setBoolean(6, incapacidades);
			ps.setBoolean(7, vacaciones);
			ps.setBoolean(8, cargos);
			ps.setBoolean(9, areas);
			ps.setBoolean(10, reportes);
			ps.setBoolean(11, respaldos);
			ps.setBoolean(12, usuarios);
			ps.setString(13, usuario);
			
			int filasActualizadas = ps.executeUpdate();
			return filasActualizadas > 0; // Verificar si se actualizó alguna fila
			} catch (SQLException e) {
			e.printStackTrace();
			return false;
			} finally {
			try {
			if (ps != null) ps.close();
			if (con != null) desconectar(con);
			} catch (SQLException ex) {
			ex.printStackTrace();
			}
			}
			}


}
