package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

import javax.swing.JOptionPane;

import clases.empleado;
import clases.permiso_ausencia_laboral;
import conexion.conexion;

public class consultas_permiso_ausencia_laboral extends conexion {
	
	public boolean consulta_guardar_permiso_ausencia_laboral(permiso_ausencia_laboral permiso_ausencia_laboral, Time desde_hora, 
            Time hasta_hora, Date desde_fecha, Date hasta_fecha, Date fecha_recibe) {
    
    PreparedStatement ps = null;
    Connection con = conectar();
    
    String sql = "INSERT INTO permisos_ausencia_laboral (nombres_empleado, apellidos_empleado, identidad_empleado, id_empleado, tel_empleado, correo_empleado, "
            + "cargo_empleado, area_empleado, desde_hora, hasta_hora, total_horas, motivo_ausencia, desde_fecha, hasta_fecha, total_fecha, nombres_recibe, fecha_recibe, nombres_extiende) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    
    try {
        ps = con.prepareStatement(sql);

        java.sql.Date sqlDateDesde = new java.sql.Date(desde_fecha.getTime());
        java.sql.Date sqlDateHasta = new java.sql.Date(hasta_fecha.getTime());
        java.sql.Date sqlDateRecibe = new java.sql.Date(fecha_recibe.getTime());
        
        ps.setString(1, permiso_ausencia_laboral.getNombres_empleado());
        ps.setString(2, permiso_ausencia_laboral.getApellidos_empleado());
        ps.setString(3, permiso_ausencia_laboral.getIdentidad_empleado());
        ps.setInt(4, permiso_ausencia_laboral.getId_empleado());
        ps.setString(5, permiso_ausencia_laboral.getTel_empleado());
        ps.setString(6, permiso_ausencia_laboral.getCorreo_empleado());
        ps.setString(7, permiso_ausencia_laboral.getCargo_empleado());
        ps.setString(8, permiso_ausencia_laboral.getArea_empleado());
        ps.setTime(9, desde_hora); 
        ps.setTime(10, hasta_hora); 
        ps.setTime(11, permiso_ausencia_laboral.getTotal_horas()); 
        ps.setString(12, permiso_ausencia_laboral.getMotivo_ausencia());
        ps.setDate(13, sqlDateDesde); 
        ps.setDate(14, sqlDateHasta); 
        ps.setInt(15, permiso_ausencia_laboral.getTotal_fecha()); 
        ps.setString(16, permiso_ausencia_laboral.getNombres_recibe()); 
        ps.setDate(17, sqlDateRecibe);
        ps.setString(18, permiso_ausencia_laboral.getNombres_extiende()); 

        ps.execute();
        return true;
    
    } catch (SQLException e) {
        System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        return false;
    
    } finally {
        try {
            if (ps != null) {
                ps.close(); 
            }
            if (con != null) {
                con.close(); 
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}

	public boolean actualizar_permiso_ausencia_laboral(permiso_ausencia_laboral permiso_ausencia_laboral, Time desde_hora, 
		    Time hasta_hora, Date desde_fecha, Date hasta_fecha, Date fecha_recibe) {
		    
		    PreparedStatement ps = null;
		    Connection con = conectar();

		    String sql = "UPDATE permisos_ausencia_laboral SET nombres_empleado=?, apellidos_empleado=?, identidad_empleado=?, "
		               + "id_empleado=?, tel_empleado=?, correo_empleado=?, cargo_empleado=?, area_empleado=?, "
		               + "desde_hora=?, hasta_hora=?, total_horas=?, motivo_ausencia=?, desde_fecha=?, hasta_fecha=?, "
		               + "total_fecha=?, nombres_recibe=?, fecha_recibe=?, nombres_extiende=? WHERE id_permisos=?";

		    try {
		        ps = con.prepareStatement(sql);

		        java.sql.Time sqlDesdeHora = new java.sql.Time(desde_hora.getTime());
		        java.sql.Time sqlHastaHora = new java.sql.Time(hasta_hora.getTime());
		        java.sql.Date sqlDesdeFecha = new java.sql.Date(desde_fecha.getTime());
		        java.sql.Date sqlHastaFecha = new java.sql.Date(hasta_fecha.getTime());
		        java.sql.Date sqlFechaRecibe = new java.sql.Date(fecha_recibe.getTime());

		        ps.setString(1, permiso_ausencia_laboral.getNombres_empleado());
		        ps.setString(2, permiso_ausencia_laboral.getApellidos_empleado());
		        ps.setString(3, permiso_ausencia_laboral.getIdentidad_empleado());
		        ps.setInt(4, permiso_ausencia_laboral.getId_empleado());
		        ps.setString(5, permiso_ausencia_laboral.getTel_empleado());
		        ps.setString(6, permiso_ausencia_laboral.getCorreo_empleado());
		        ps.setString(7, permiso_ausencia_laboral.getCargo_empleado());
		        ps.setString(8, permiso_ausencia_laboral.getArea_empleado());
		        ps.setTime(9, sqlDesdeHora);
		        ps.setTime(10, sqlHastaHora);
		        ps.setTime(11, permiso_ausencia_laboral.getTotal_horas());
		        ps.setString(12, permiso_ausencia_laboral.getMotivo_ausencia());
		        ps.setDate(13, sqlDesdeFecha);
		        ps.setDate(14, sqlHastaFecha);
		        ps.setInt(15, permiso_ausencia_laboral.getTotal_fecha());
		        ps.setString(16, permiso_ausencia_laboral.getNombres_recibe());
		        ps.setDate(17, sqlFechaRecibe);
		        ps.setInt(18, permiso_ausencia_laboral.getId_permisos());
		        ps.setString(19, permiso_ausencia_laboral.getNombres_extiende());

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

	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean eliminar_permiso(int id) {
	    PreparedStatement ps = null;
	    Connection con = conectar();
	    
	    String sql = "DELETE FROM permisos_ausencia_laboral WHERE id_permisos = ?";  
	    
	    try {
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, id);
	        
	        int rowsDeleted = ps.executeUpdate();
	        return rowsDeleted > 0;  
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
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
	
	
	// Método para obtener los datos de un permiso por su ID
    public permiso_ausencia_laboral obtenerPermisoPorId(int idPermiso) {
        conexion conex = new conexion();
        permiso_ausencia_laboral permiso = null; 

        try {
            String sql = "SELECT * FROM permisos_ausencia_laboral WHERE id_permisos = ?";
            PreparedStatement pst = conex.conectar().prepareStatement(sql);
            pst.setInt(1, idPermiso); 

            ResultSet rs = pst.executeQuery(); 

            if (rs.next()) {
                permiso = new permiso_ausencia_laboral(); 

                permiso.setId_permisos(rs.getInt("id_permisos"));
                permiso.setId_empleado(rs.getInt("id_empleado"));
                permiso.setIdentidad_empleado(rs.getString("identidad_empleado"));
                permiso.setNombres_empleado(rs.getString("nombres_empleado"));
                permiso.setApellidos_empleado(rs.getString("apellidos_empleado"));
                permiso.setTel_empleado(rs.getString("tel_empleado"));
                permiso.setCorreo_empleado(rs.getString("correo_empleado"));
                permiso.setCargo_empleado(rs.getString("cargo_empleado"));
                permiso.setArea_empleado(rs.getString("area_empleado"));
                permiso.setDesde_hora(rs.getTime("desde_hora"));
                permiso.setHasta_hora(rs.getTime("hasta_hora"));
                permiso.setTotal_horas(rs.getTime("total_horas"));
                permiso.setMotivo_ausencia(rs.getString("motivo_ausencia"));
                permiso.setDesde_fecha(rs.getDate("desde_fecha"));
                permiso.setHasta_fecha(rs.getDate("hasta_fecha"));
                permiso.setTotal_fecha(rs.getInt("total_fecha"));
                permiso.setNombres_recibe(rs.getString("nombres_recibe"));
                permiso.setFecha_recibe(rs.getDate("fecha_recibe"));
                permiso.setNombres_extiende(rs.getString("nombres_extiende"));
            }

            rs.close(); 
            pst.close();
            conex.desconectar(null); 

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al consultar los datos del empleado", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return permiso; 
    }
	
	
	
	
		

}
