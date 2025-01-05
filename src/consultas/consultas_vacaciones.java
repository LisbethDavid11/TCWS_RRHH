package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import clases.vacaciones;
import conexion.conexion;
import ventanas.vacaciones_nuevo;

public class consultas_vacaciones extends conexion {
	
	public boolean guardarVacaciones(vacaciones claseVacaciones) {
	    Connection con = null;
	    PreparedStatement ps = null;

	    try {
	        con = new conexion().conectar();
	        String sql = "INSERT INTO vacaciones (id_empleado, nombres_empleado, apellidos_empleado, identidad_empleado, " +
	                     "tel_empleado, correo_empleado, cargo_empleado, area_empleado, nacimiento_empleado, sexo_empleado, " +
	                     "edad_empleado, antiguedad, dias_correspondientes, total_dias, dias_pendientes, pagadas, " +
	                     "fecha_inicio_v, fecha_finalizacion_v, extendido, cargo_ext, fecha_actual, hora_actual) " +
	                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	        ps = con.prepareStatement(sql);

	        ps.setInt(1, claseVacaciones.getId_empleado());
	        ps.setString(2, claseVacaciones.getNombres_empleado());
	        ps.setString(3, claseVacaciones.getApellidos_empleado());
	        ps.setString(4, claseVacaciones.getIdentidad_empleado());
	        ps.setString(5, claseVacaciones.getTel_empleado());
	        ps.setString(6, claseVacaciones.getCorreo_empleado());
	        ps.setString(7, claseVacaciones.getCargo_empleado());
	        ps.setString(8, claseVacaciones.getArea_empleado());
	        ps.setDate(9, new java.sql.Date(claseVacaciones.getNacimiento_empleado().getTime()));
	        ps.setString(10, claseVacaciones.getSexo_empleado());
	        ps.setInt(11, claseVacaciones.getEdad_empleado());
	        ps.setInt(12, claseVacaciones.getAntiguedad());
	        ps.setInt(13, claseVacaciones.getDias_correspondientes());
	        ps.setInt(14, claseVacaciones.getTotal_dias());
	        ps.setInt(15, claseVacaciones.getDias_pendientes());
	        ps.setString(16, claseVacaciones.getPagadas());
	        ps.setDate(17, new java.sql.Date(claseVacaciones.getFecha_inicio_v().getTime()));
	        ps.setDate(18, new java.sql.Date(claseVacaciones.getFecha_finalizacion_v().getTime()));
	        ps.setString(19, claseVacaciones.getExtendido());
	        ps.setString(20, claseVacaciones.getCargo_ext());
	        ps.setDate(21, new java.sql.Date(claseVacaciones.getFecha_actual().getTime()));
	        ps.setTime(22, claseVacaciones.getHora_actual());

	        ps.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al guardar las vacaciones: " + e.getMessage());
	        return false;
	    } finally {
	        desconectar(con);
	    }
	}


	
	
	public boolean actualizarVacaciones(vacaciones claseVacaciones) {
	    Connection con = null;
	    PreparedStatement ps = null;

	    try {
	        con = new conexion().conectar();
	        String sql = "UPDATE vacaciones SET nombres_empleado = ?, apellidos_empleado = ?, identidad_empleado = ?, "
	                   + "tel_empleado = ?, correo_empleado = ?, cargo_empleado = ?, area_empleado = ?, "
	                   + "nacimiento_empleado = ?, sexo_empleado = ?, edad_empleado = ?, antiguedad = ?, "
	                   + "dias_correspondientes = ?, total_dias = ?, dias_pendientes = ?, pagadas = ?, "
	                   + "fecha_inicio_v = ?, fecha_finalizacion_v = ?, extendido = ?, cargo_ext = ?, "
	                   + "fecha_actual = ?, hora_actual = ? WHERE id_empleado = ?";

	        ps = con.prepareStatement(sql);
	        ps.setString(1, claseVacaciones.getNombres_empleado());
	        ps.setString(2, claseVacaciones.getApellidos_empleado());
	        ps.setString(3, claseVacaciones.getIdentidad_empleado());
	        ps.setString(4, claseVacaciones.getTel_empleado());
	        ps.setString(5, claseVacaciones.getCorreo_empleado());
	        ps.setString(6, claseVacaciones.getCargo_empleado());
	        ps.setString(7, claseVacaciones.getArea_empleado());
	        ps.setDate(8, new java.sql.Date(claseVacaciones.getNacimiento_empleado().getTime()));
	        ps.setString(9, claseVacaciones.getSexo_empleado());
	        ps.setInt(10, claseVacaciones.getEdad_empleado());
	        ps.setInt(11, claseVacaciones.getAntiguedad());
	        ps.setInt(12, claseVacaciones.getDias_correspondientes());
	        ps.setInt(13, claseVacaciones.getTotal_dias());
	        ps.setInt(14, claseVacaciones.getDias_pendientes());
	        ps.setString(15, claseVacaciones.getPagadas());
	        ps.setDate(16, new java.sql.Date(claseVacaciones.getFecha_inicio_v().getTime()));
	        ps.setDate(17, new java.sql.Date(claseVacaciones.getFecha_finalizacion_v().getTime()));
	        ps.setString(18, claseVacaciones.getExtendido());
	        ps.setString(19, claseVacaciones.getCargo_ext());
	        ps.setDate(20, new java.sql.Date(claseVacaciones.getFecha_actual().getTime()));
	        ps.setTime(21, claseVacaciones.getHora_actual());
	        ps.setInt(22, claseVacaciones.getId_empleado());

	        int rowsUpdated = ps.executeUpdate();
	        return rowsUpdated > 0;
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al actualizar las vacaciones: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	
	public int obtenerDiasCorrespondientes(String nombreEmpleado) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int diasCorrespondientes = 0;

	    try {
	        con = new conexion().conectar();
	        
	        String sql = "SELECT dias_correspondientes FROM vacaciones WHERE nombres_empleado = ? ORDER BY fecha_inicio_v DESC LIMIT 1";
	        ps = con.prepareStatement(sql);
	        ps.setString(1, nombreEmpleado);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            diasCorrespondientes = rs.getInt("dias_correspondientes");
	        }

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al obtener los días correspondientes: " + e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return diasCorrespondientes; // Retorna los días correspondientes
	}

	
	public int cargarDiasPendientes(int idEmpleado) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int diasPendientes = 0;

	    try {
	        con = new conexion().conectar();

	        // Obtener los días pendientes del último registro de vacaciones del empleado
	        String sql = "SELECT dias_pendientes FROM vacaciones " +
	                     "WHERE id_empleado = ? " +
	                     "ORDER BY fecha_finalizacion_v DESC LIMIT 1";
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, idEmpleado);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            diasPendientes = rs.getInt("dias_pendientes");
	        }

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al cargar los días pendientes: " + e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return diasPendientes;
	}


	
	
	
	public int obtenerDiasTomados(int idEmpleado) {
	    int diasTomados = 0;
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        con = conectar();
	        String sql = "SELECT SUM(total_dias) AS dias_tomados FROM vacaciones WHERE id_empleado = ?";
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, idEmpleado);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            diasTomados = rs.getInt("dias_tomados");
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al obtener los días tomados: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return diasTomados;
	}
	

	public int obtenerDiasPendientesUltimoRegistro(int idEmpleado) {
	    Connection con = null;
	    int diasPendientes = -1;

	    try {
	        con = new conexion().conectar();

	        // Consulta para obtener el último registro del empleado
	        String sql = "SELECT dias_pendientes FROM vacaciones WHERE id_empleado = ? ORDER BY fecha_finalizacion_v DESC LIMIT 1";
	        try (PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setInt(1, idEmpleado);
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    diasPendientes = rs.getInt("dias_pendientes");
	                }
	            }
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al obtener los días pendientes del último registro: " + e.getMessage(),
	                "Error", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
	    } finally {
	       // cerrarConexion(con);
	    }

	    return diasPendientes;
	}

	
	



	    
	    
	public boolean eliminarVacaciones(int idVacaciones) {
	    Connection con = null;
	    PreparedStatement pst = null;
	    String sql = "DELETE FROM vacaciones WHERE id_vacaciones = ?";
	    
	    try {
	        con = new conexion().conectar();
	        pst = con.prepareStatement(sql);
	        pst.setInt(1, idVacaciones);

	        int resultado = pst.executeUpdate();
	        return resultado > 0;  // Si se eliminó una fila, retornamos true

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;  // Retornamos false en caso de un error
	    } finally {
	        try {
	            if (pst != null) pst.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	

}
