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
	        
	        // Obtener los días tomados previamente
	        int diasTomadosPrevios = obtenerDiasTomados(claseVacaciones.getId_empleado());
	        
	        // Calcular los días pendientes actuales
	        int diasCorrespondientes = claseVacaciones.getDias_correspondientes();
	        int totalDiasNuevos = claseVacaciones.getTotal_dias();
	        int diasPendientesActuales = diasCorrespondientes - diasTomadosPrevios - totalDiasNuevos;

	        // Validar si el empleado tiene suficientes días disponibles
	        if (diasPendientesActuales < 0) {
	            JOptionPane.showMessageDialog(null, "El empleado no tiene suficientes días disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
	            return false;
	        }

	        // Preparar la consulta SQL
	        String sql = "INSERT INTO vacaciones (id_empleado, nombres_empleado, apellidos_empleado, identidad_empleado, "
	                + "tel_empleado, correo_empleado, cargo_empleado, area_empleado, nacimiento_empleado, "
	                + "sexo_empleado, edad_empleado, fecha_actual, hora_actual, antiguedad, dias_correspondientes, "
	                + "fecha_inicio_v, fecha_finalizacion_v, total_dias, dias_pendientes, pagadas) "
	                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	        ps = con.prepareStatement(sql);

	        // Asignar valores a la consulta SQL
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

	        // Ejecutar la consulta
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
	        
	        // Consulta para actualizar las vacaciones
	        String sql = "UPDATE vacaciones SET " +
	                     "id_empleado = ?, nombres_empleado = ?, apellidos_empleado = ?, identidad_empleado = ?, " +
	                     "tel_empleado = ?, correo_empleado = ?, cargo_empleado = ?, area_empleado = ?, " +
	                     "nacimiento_empleado = ?, sexo_empleado = ?, edad_empleado = ?, antiguedad = ?, " +
	                     "dias_correspondientes = ?, fecha_inicio_v = ?, fecha_finalizacion_v = ?, total_dias = ?, " +
	                     "dias_pendientes = ?, pagadas = ?, fecha_actual = ?, hora_actual = ? " +
	                     "WHERE id_vacaciones = ?";

	        ps = con.prepareStatement(sql);

	        // Asignar valores a la consulta SQL
	        ps.setInt(1, Integer.parseInt(ventana.txtid.getText()));  // id_empleado
	        ps.setString(2, (String) ventana.cbxnombres.getSelectedItem());  // nombres_empleado
	        ps.setString(3, ventana.txtapellidos.getText());  // apellidos_empleado
	        ps.setString(4, ventana.txtidentidad.getText());  // identidad_empleado
	        ps.setString(5, ventana.txttel.getText());  // tel_empleado
	        ps.setString(6, ventana.txtcorreo.getText());  // correo_empleado
	        ps.setString(7, ventana.txtcargo.getText());  // cargo_empleado
	        ps.setString(8, ventana.txtarea.getText());  // area_empleado
	        ps.setDate(9, new java.sql.Date(ventana.fecha_nacimiento.getDate().getTime()));  // nacimiento_empleado
	        ps.setString(10, ventana.txtsexo.getText());  // sexo_empleado
	        ps.setInt(11, Integer.parseInt(ventana.txtedad.getText()));  // edad_empleado
	        ps.setInt(12, Integer.parseInt(ventana.txtantiguedad.getText()));  // antiguedad
	        ps.setInt(13, Integer.parseInt(ventana.txtdias_correspondientes.getText()));  // dias_correspondientes
	        ps.setDate(14, new java.sql.Date(ventana.fecha_inicio_v.getDate().getTime()));  // fecha_inicio_v
	        ps.setDate(15, new java.sql.Date(ventana.fecha_finalizacion_v.getDate().getTime()));  // fecha_finalizacion_v
	        ps.setInt(16, Integer.parseInt(ventana.txttotal_dias.getText()));  // total_dias
	        ps.setInt(17, Integer.parseInt(ventana.txtdias_pendientes.getText()));  // dias_pendientes
	        
	        // Manejo del campo "pagadas"
	        ps.setString(18, ventana.radio_si.isSelected() ? "Si" : "No");
	        
	        // Asignar la fecha y hora actuales
	        ps.setDate(19, new java.sql.Date(new Date().getTime()));  // fecha_actual
	        ps.setTime(20, java.sql.Time.valueOf(ventana.txthora_actual.getText() + ":00"));  // hora_actual
	        
	        // Establecer el id_vacaciones para la actualización
	        ps.setInt(21, Integer.parseInt(ventana.txtid_tabla.getText()));  // id_vacaciones

	        // Ejecutar la consulta de actualización
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

	        // Consulta para obtener los días correspondientes del empleado
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

	        // Consultar los días tomados previamente por el empleado
	        String sql = "SELECT SUM(total_dias) AS dias_tomados FROM vacaciones WHERE nombres_empleado = ?";
	        ps = con.prepareStatement(sql);
	        ps.setString(1, nombreEmpleado);
	        rs = ps.executeQuery();

	        int diasTomados = 0;
	        if (rs.next()) {
	            diasTomados = rs.getInt("dias_tomados");
	        }

	        // Obtener los días correspondientes del empleado
	        int diasCorrespondientes = obtenerDiasCorrespondientes(nombreEmpleado);

	        // Calcular los días pendientes
	        diasPendientes = diasCorrespondientes - diasTomados;

	        // Asegurarse de no devolver días pendientes negativos
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

	        // Consulta para obtener la suma de los días tomados
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



	
	

	    
	    
	public boolean eliminarVacaciones(vacaciones_nuevo ventana) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    String sql = "DELETE FROM vacaciones WHERE id_vacaciones = ?";

	    try {
	        con = new conexion().conectar();
	        ps = con.prepareStatement(sql);
	        
	        // Obtener el id_vacaciones desde el campo txtid_tabla
	        int idVacaciones = Integer.parseInt(ventana.txtid_tabla.getText());
	        ps.setInt(1, idVacaciones);

	        // Ejecutar la consulta de eliminación
	        int filasAfectadas = ps.executeUpdate();
	        return filasAfectadas > 0; // Retorna true si al menos una fila fue eliminada

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

	

	
	
	
	
	

}
