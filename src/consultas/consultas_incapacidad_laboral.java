package consultas;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

import javax.swing.JOptionPane;

import clases.incapacidad_laboral;
import conexion.conexion;



public class consultas_incapacidad_laboral extends conexion {
	
	public boolean guardar_incapacidad(incapacidad_laboral incapacidad, Date fecha_expedicion, Date fecha_inicio, Date fecha_finalizacion) {
	    PreparedStatement ps = null;
	    Connection con = null;

	    String sql = "INSERT INTO incapacidad_laboral (id_empleado, nombres_empleado, apellidos_empleado, "
	            + "identidad_empleado, tel_empleado, correo_empleado, cargo_empleado, area_empleado, nacimiento_empleado, "
	            + "sexo_empleado, edad_empleado, riesgo_incapacidad, inicio_incapacidad, fin_incapacidad, total_dias, "
	            + "tipo_incapacidad, tipo_reposo, fecha_expedicion, hora_expedicion, numero_certificado, fecha_actual, hora_actual) "
	            + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try {
	        con = conectar();
	        ps = con.prepareStatement(sql);

	        // Verificar si nacimiento_empleado es nulo
	        if (incapacidad.getNacimiento_empleado() == null) {
	            throw new IllegalArgumentException("El campo nacimiento_empleado no puede ser nulo");
	        }

	        ps.setInt(1, incapacidad.getId_empleado());
	        ps.setString(2, incapacidad.getNombres_empleado());
	        ps.setString(3, incapacidad.getApellidos_empleado());
	        ps.setString(4, incapacidad.getIdentidad_empleado());
	        ps.setString(5, incapacidad.getTel_empleado());
	        ps.setString(6, incapacidad.getCorreo_empleado());
	        ps.setString(7, incapacidad.getCargo_empleado());
	        ps.setString(8, incapacidad.getArea_empleado());
	        ps.setDate(9, new java.sql.Date(incapacidad.getNacimiento_empleado().getTime()));
	        ps.setString(10, incapacidad.getSexo_empleado());
	        ps.setInt(11, incapacidad.getEdad_empleado());
	        ps.setString(12, incapacidad.getRiesgo_incapacidad());
	        ps.setDate(13, fecha_inicio != null ? new java.sql.Date(fecha_inicio.getTime()) : null);
	        ps.setDate(14, fecha_finalizacion != null ? new java.sql.Date(fecha_finalizacion.getTime()) : null);
	        ps.setInt(15, incapacidad.getTotal_dias());
	        ps.setString(16, incapacidad.getTipo_incapacidad());
	        ps.setString(17, incapacidad.getTipo_reposo());
	        ps.setDate(18, fecha_expedicion != null ? new java.sql.Date(fecha_expedicion.getTime()) : null);
	        ps.setTime(19, incapacidad.getHora_expedicion());
	        ps.setString(20, incapacidad.getNumero_certificado());
	        ps.setDate(21, incapacidad.getFecha_actual() != null ? new java.sql.Date(incapacidad.getFecha_actual().getTime()) : null);
	        ps.setTime(22, incapacidad.getHora_actual());

	        ps.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al guardar la incapacidad: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
	

	 public boolean actualizarIncapacidad(incapacidad_laboral incapacidad) {
	        conexion con = new conexion();
	        Connection cn = con.conectar();

	        String sql = "UPDATE incapacidad_laboral SET "
	                   + "nombres_empleado=?, apellidos_empleado=?, identidad_empleado=?, tel_empleado=?, "
	                   + "correo_empleado=?, cargo_empleado=?, area_empleado=?, nacimiento_empleado=?, sexo_empleado=?, "
	                   + "edad_empleado=?, riesgo_incapacidad=?, inicio_incapacidad=?, fin_incapacidad=?, "
	                   + "total_dias=?, tipo_incapacidad=?, tipo_reposo=?, fecha_expedicion=?, hora_expedicion=?, "
	                   + "numero_certificado=?, fecha_actual=?, hora_actual=? "
	                   + "WHERE id_incapacidad=?";

	        try (PreparedStatement pst = cn.prepareStatement(sql)) {
	            pst.setString(1, incapacidad.getNombres_empleado());
	            pst.setString(2, incapacidad.getApellidos_empleado());
	            pst.setString(3, incapacidad.getIdentidad_empleado());
	            pst.setString(4, incapacidad.getTel_empleado());
	            pst.setString(5, incapacidad.getCorreo_empleado());
	            pst.setString(6, incapacidad.getCargo_empleado());
	            pst.setString(7, incapacidad.getArea_empleado());
	            pst.setDate(8, (java.sql.Date) incapacidad.getNacimiento_empleado());
	            pst.setString(9, incapacidad.getSexo_empleado());
	            pst.setInt(10, incapacidad.getEdad_empleado());
	            pst.setString(11, incapacidad.getRiesgo_incapacidad());
	            pst.setDate(12, (java.sql.Date) incapacidad.getInicio_incapacidad());
	            pst.setDate(13, (java.sql.Date) incapacidad.getFin_incapacidad());
	            pst.setInt(14, incapacidad.getTotal_dias());
	            pst.setString(15, incapacidad.getTipo_incapacidad());
	            pst.setString(16, incapacidad.getTipo_reposo());
	            pst.setDate(17, (java.sql.Date) incapacidad.getFecha_expedicion());
	            pst.setTime(18, incapacidad.getHora_expedicion());
	            pst.setString(19, incapacidad.getNumero_certificado());
	            pst.setDate(20, (java.sql.Date) incapacidad.getFecha_actual());
	            pst.setTime(21, incapacidad.getHora_actual());

	            pst.setInt(22, incapacidad.getId_incapacidad());  // Filtro por ID de incapacidad

	            int rowsAffected = pst.executeUpdate();
	            return rowsAffected > 0;  // Si la actualización afectó alguna fila, retorna true

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        } finally {
	            con.desconectar();
	        }
	    }
	

	
	
	public boolean eliminar_incapacidad_laboral(int id_incapacidad) {

	    PreparedStatement ps = null;
	    Connection con = conectar();

	    String sql = "DELETE FROM incapacidad_laboral WHERE id_incapacidad=?";

	    try {
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, id_incapacidad);

	        ps.executeUpdate();
	        return true;

	    } catch (SQLException e) {
	        System.err.println(e);
	        return false;
	    } finally {
	        try {
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException e) {
	            System.err.println(e);
	        }
	    }
	}





}
