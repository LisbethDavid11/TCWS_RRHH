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
	
	public boolean guardarVacaciones(vacaciones claseVacaciones, Date fechaActual, Date fechaInicio) {
	    PreparedStatement ps = null;
	    Connection con = null;

	    try {
	        con = conectar();
	        
	        int diasTomadosPrevios = obtenerDiasTomados(claseVacaciones.getId_empleado());
	        int diasCorrespondientes = claseVacaciones.getDias_correspondientes();
	        int totalDiasNuevos = claseVacaciones.getTotal_dias();
	        int diasPendientesActuales = diasCorrespondientes - diasTomadosPrevios - totalDiasNuevos;
	        if (diasPendientesActuales < 0) {
	            JOptionPane.showMessageDialog(null, "El empleado no tiene suficientes días disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
	            return false;
	        }

	        String sql = "INSERT INTO vacaciones (id_empleado, nombres_empleado, apellidos_empleado, identidad_empleado, "
	                + "tel_empleado, correo_empleado, cargo_empleado, area_empleado, nacimiento_empleado, "
	                + "sexo_empleado, edad_empleado, fecha_actual, hora_actual, antiguedad, dias_correspondientes, "
	                + "fecha_inicio_v, fecha_finalizacion_v, total_dias, dias_pendientes, pagadas) "
	                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
	        ps.setDate(12, new java.sql.Date(fechaActual.getTime()));
	        ps.setTime(13, claseVacaciones.getHora_actual());
	        ps.setInt(14, claseVacaciones.getAntiguedad());
	        ps.setInt(15, diasCorrespondientes);  // Días correspondientes del empleado
	        ps.setDate(16, new java.sql.Date(claseVacaciones.getFecha_inicio_v().getTime()));
	        ps.setDate(17, new java.sql.Date(claseVacaciones.getFecha_finalizacion_v().getTime()));
	        ps.setInt(18, totalDiasNuevos);  // Días que el empleado está tomando en esta ocasión
	        ps.setInt(19, diasPendientesActuales);  // Días pendientes después de esta toma
	        ps.setString(20, claseVacaciones.getPagadas());
	        ps.executeUpdate();
	        return true;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al guardar las vacaciones: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}


	// ESTA MAL ESTRUCTURADA LA CONSULTA
	public boolean actualizarVacaciones(vacaciones_nuevo ventana) {
	    Connection con = null;
	    PreparedStatement ps = null;

	    try {
	        con = new conexion().conectar();
	        
	        String sql = "UPDATE vacaciones SET " +
	                     "id_empleado = ?, nombres_empleado = ?, apellidos_empleado = ?, identidad_empleado = ?, " +
	                     "tel_empleado = ?, correo_empleado = ?, cargo_empleado = ?, area_empleado = ?, " +
	                     "nacimiento_empleado = ?, sexo_empleado = ?, edad_empleado = ?, antiguedad = ?, " +
	                     "dias_correspondientes = ?, fecha_inicio_v = ?, fecha_finalizacion_v = ?, total_dias = ?, " +
	                     "dias_pendientes = ?, pagadas = ?, fecha_actual = ?, hora_actual = ? " +
	                     "WHERE id_vacaciones = ?";

	        ps = con.prepareStatement(sql);
	        ps.setInt(1, Integer.parseInt(ventana.txtid.getText()));  
	        ps.setString(2, (String) ventana.cbxnombres.getSelectedItem());  
	        ps.setString(3, ventana.txtapellidos.getText());  
	        ps.setString(4, ventana.txtidentidad.getText()); 
	        ps.setString(5, ventana.txttel.getText()); 
	        ps.setString(6, ventana.txtcorreo.getText());  
	        ps.setString(7, ventana.txtcargo.getText());  
	        ps.setString(8, ventana.txtarea.getText());  
	        ps.setDate(9, new java.sql.Date(ventana.fecha_nacimiento.getDate().getTime()));  
	        ps.setString(10, ventana.txtsexo.getText()); 
	        ps.setInt(11, Integer.parseInt(ventana.txtedad.getText()));  
	        ps.setInt(12, Integer.parseInt(ventana.txtantiguedad.getText()));  
	        ps.setInt(13, Integer.parseInt(ventana.txtdias_correspondientes.getText()));  
	        ps.setDate(14, new java.sql.Date(ventana.fecha_inicio_v.getDate().getTime()));  
	        ps.setDate(15, new java.sql.Date(ventana.fecha_finalizacion_v.getDate().getTime()));  
	        ps.setInt(16, Integer.parseInt(ventana.txttotal_dias.getText()));  
	        ps.setInt(17, Integer.parseInt(ventana.txtdias_pendientes.getText()));  
	        
	        // Manejo del campo "pagadas"
	        ps.setString(18, ventana.radio_si.isSelected() ? "Si" : "No");
	        
	        ps.setDate(19, new java.sql.Date(new Date().getTime())); 
	        ps.setTime(20, java.sql.Time.valueOf(ventana.txthora_actual.getText() + ":00")); 
	        
	        ps.setInt(21, Integer.parseInt(ventana.txtid_tabla.getText()));

	        int resultado = ps.executeUpdate();

	        if (resultado > 0) {
	            JOptionPane.showMessageDialog(null, "Vacaciones actualizadas correctamente.");
	            return true;
	        } else {
	            JOptionPane.showMessageDialog(null, "No se pudo actualizar el registro.");
	            return false;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (con != null) {
	            new conexion().desconectar();
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

	
	public int cargarDiasPendientes(String nombreEmpleado) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int diasPendientes = 0;

	    try {
	        con = new conexion().conectar();

	        String sql = "SELECT SUM(total_dias) AS dias_tomados FROM vacaciones WHERE nombres_empleado = ?";
	        ps = con.prepareStatement(sql);
	        ps.setString(1, nombreEmpleado);
	        rs = ps.executeQuery();

	        int diasTomados = 0;
	        if (rs.next()) {
	            diasTomados = rs.getInt("dias_tomados");
	        }

	        int diasCorrespondientes = obtenerDiasCorrespondientes(nombreEmpleado);
	        diasPendientes = diasCorrespondientes - diasTomados;
	        
	        if (diasPendientes < 0) {
	            diasPendientes = 0;
	        }

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al calcular los días pendientes: " + e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return diasPendientes;  // Devolver el valor calculado
	}

	
	
	
	public int obtenerDiasTomados(int idEmpleado) {
	    int diasTomados = 0;
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        con = new conexion().conectar();
	        String sql = "SELECT SUM(total_dias) AS dias_tomados FROM vacaciones WHERE id_empleado = ?";
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, idEmpleado);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            diasTomados = rs.getInt("dias_tomados");
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al obtener los días tomados: " + e.getMessage());
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
