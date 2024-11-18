package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;

import clases.empleado;
import conexion.conexion;


//TCWS
public class consultas_empleado extends conexion {
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean guardar_empleado(empleado empleado, Date fecha_nacimiento, Date fecha_inicio, Date fecha_renuncia) {
	    PreparedStatement ps = null;
	    Connection con = conectar();

	    String sql = "INSERT INTO empleados (id_empleado, identidad_empleado, nombres_empleado, apellidos_empleado, sexo_empleado, nacimiento_empleado, civil_empleado, direccion_empleado, "
	            + "tel_empleado, correo_empleado, cargo_empleado, area_empleado, inicio_empleado, renuncia_empleado, fotografia_empleado, cuenta_empleado) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

	    try {
	        ps = con.prepareStatement(sql);
	        java.sql.Date sqlDate = new java.sql.Date(fecha_nacimiento.getTime());
	        java.sql.Date sqlDate2 = new java.sql.Date(fecha_inicio.getTime());
	        java.sql.Date sqlDate3 = null;
	        if (fecha_renuncia != null) {
	            sqlDate3 = new java.sql.Date(fecha_renuncia.getTime());
	        }

	        ps.setInt(1, empleado.getId_empleado());
	        ps.setString(2, empleado.getIdentidad_empleado());
	        ps.setString(3, empleado.getNombres_empleado());
	        ps.setString(4, empleado.getApellidos_empleado());
	        ps.setString(5, empleado.getSexo_empleado());
	        ps.setDate(6, sqlDate);
	        ps.setString(7, empleado.getCivil_empleado());
	        ps.setString(8, empleado.getDireccion_empleado());
	        ps.setString(9, empleado.getTel_empleado());
	        ps.setString(10, empleado.getCorreo_empleado());
	        ps.setString(11, empleado.getCargo_empleado());
	        ps.setString(12, empleado.getArea_empleado());
	        ps.setDate(13, sqlDate2);
	        ps.setDate(14, sqlDate3);  // Puede ser null
	        ps.setString(15, empleado.getFotografia_empleado());
	        ps.setString(16, empleado.getCuenta_empleado());

	        ps.execute();
	        return true;

	    } catch (SQLException e) {
	        System.err.println(e);
	        return false;
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            System.err.println(e);
	        }
	    }
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean eliminar_empleado(int id) {
	    PreparedStatement ps = null;
	    Connection con = conectar();
	    
	    String sql = "DELETE FROM empleados WHERE id = ?";  
	    
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
	
	///////////////////////////////////////////////////////////////
    public empleado obtenerEmpleadoPorId(int idEmpleado) {
        conexion conex = new conexion();
        empleado empleado = null;

        try {
            String sql = "SELECT * FROM empleados WHERE id_empleado = ?";
            PreparedStatement pst = conex.conectar().prepareStatement(sql);
            pst.setInt(1, idEmpleado); 

            ResultSet rs = pst.executeQuery(); 

            if (rs.next()) {
                empleado = new empleado(); 

                empleado.setId(rs.getInt("id"));
                empleado.setId_empleado(rs.getInt("id_empleado"));
                empleado.setIdentidad_empleado(rs.getString("identidad_empleado"));
                empleado.setNombres_empleado(rs.getString("nombres_empleado"));
                empleado.setApellidos_empleado(rs.getString("apellidos_empleado"));
                empleado.setSexo_empleado(rs.getString("sexo_empleado"));
                empleado.setNacimiento_empleado(rs.getDate("nacimiento_empleado"));
                empleado.setCivil_empleado(rs.getString("civil_empleado"));
                empleado.setDireccion_empleado(rs.getString("direccion_empleado"));
                empleado.setTel_empleado(rs.getString("tel_empleado"));
                empleado.setCorreo_empleado(rs.getString("correo_empleado"));
                empleado.setCargo_empleado(rs.getString("cargo_empleado"));
                empleado.setArea_empleado(rs.getString("area_empleado"));
                empleado.setInicio_empleado(rs.getDate("inicio_empleado"));
                empleado.setRenuncia_empleado(rs.getDate("renuncia_empleado"));
                empleado.setFotografia_empleado(rs.getString("fotografia_empleado"));
                empleado.setCuenta_empleado(rs.getString("cuenta_empleado"));
            }

            rs.close(); // Cerramos ResultSet y PreparedStatement.
            pst.close();
            conex.desconectar(null); // Desconectamos la conexión.

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al consultar los datos del empleado", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return empleado; 
    }
    
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String empleadoExiste(int id_empleado, String identidad_empleado) {
        String campoDuplicado = null;
        try (Connection conn = new conexion().conectar();
             PreparedStatement pst = conn.prepareStatement("SELECT id_empleado, identidad_empleado FROM empleados WHERE id_empleado = ? OR identidad_empleado = ?")) {

            pst.setInt(1, id_empleado);
            pst.setString(2, identidad_empleado);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                if (rs.getInt("id_empleado") == id_empleado) {
                    campoDuplicado = "Id de empleado";
                } else if (rs.getString("identidad_empleado").equals(identidad_empleado)) {
                    campoDuplicado = "Identidad de empleado";
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return campoDuplicado;
    }

	    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String empleadoExisteParaActualizar(int id, int nuevoIdEmpleado, String identidad_empleado) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = conectar(); 

        String sql = "SELECT * FROM empleados WHERE (identidad_empleado = ? OR id_empleado = ?) AND id != ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, identidad_empleado);
            ps.setInt(2, nuevoIdEmpleado);
            ps.setInt(3, id); // Se excluye el empleado que se está editando

            rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getString("identidad_empleado").equals(identidad_empleado)) {
                    return "Identidad";
                } else if (rs.getInt("id_empleado") == nuevoIdEmpleado) {
                    return "Id del empleado";
                }
            }
            return null; 
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                desconectar(con); 
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
    
    public boolean actualizar_empleado(empleado emp, int idOriginal, int nuevoIdEmpleado, Date fechaNacimiento, Date fechaInicio, Date fechaRenuncia) {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = conectar();
            
            String sql = "UPDATE empleados SET " +
                         "id_empleado=?, identidad_empleado=?, nombres_empleado=?, apellidos_empleado=?, " +
                         "sexo_empleado=?, nacimiento_empleado=?, civil_empleado=?, direccion_empleado=?, " +
                         "tel_empleado=?, correo_empleado=?, cargo_empleado=?, area_empleado=?, " +
                         "inicio_empleado=?, renuncia_empleado=?, fotografia_empleado=?, cuenta_empleado=? " +
                         "WHERE id=?";
            
            ps = con.prepareStatement(sql);
            
            int i = 1;
            ps.setInt(i++, nuevoIdEmpleado);
            ps.setString(i++, emp.getIdentidad_empleado());
            ps.setString(i++, emp.getNombres_empleado());
            ps.setString(i++, emp.getApellidos_empleado());
            ps.setString(i++, emp.getSexo_empleado());
            ps.setDate(i++, new java.sql.Date(fechaNacimiento.getTime()));
            ps.setString(i++, emp.getCivil_empleado());
            ps.setString(i++, emp.getDireccion_empleado());
            ps.setString(i++, emp.getTel_empleado());
            ps.setString(i++, emp.getCorreo_empleado());
            ps.setString(i++, emp.getCargo_empleado());
            ps.setString(i++, emp.getArea_empleado());
            ps.setDate(i++, new java.sql.Date(fechaInicio.getTime()));
            ps.setDate(i++, fechaRenuncia != null ? new java.sql.Date(fechaRenuncia.getTime()) : null);
            ps.setString(i++, emp.getFotografia_empleado());
            ps.setString(i++, emp.getCuenta_empleado());
            ps.setInt(i++, idOriginal); 
            
            int resultado = ps.executeUpdate();
            return resultado > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar empleado: " + e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }



    public boolean existeIdEmpleado(int idEmpleado, int idOriginal) {
        String query = "SELECT COUNT(*) FROM empleados WHERE id_empleado = ? AND id <> ?";
        try (Connection conn = conectar(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, idEmpleado);
            stmt.setInt(2, idOriginal); // Excluir el registro del empleado actual
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0; // Si el resultado es mayor a 0, el ID existe en otro empleado
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Si no se encuentra, devuelve falso
    }


    
    public boolean existeIdentidadEmpleado(String identidadEmpleado, int idOriginal) {
        String query = "SELECT COUNT(*) FROM empleados WHERE identidad_empleado = ? AND id <> ?";
        try (Connection conn = conectar(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, identidadEmpleado);
            stmt.setInt(2, idOriginal); // Excluir el registro del empleado actual
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0; // Si el resultado es mayor a 0, la identidad existe en otro empleado
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Si no se encuentra, devuelve falso
    }




    
    public String obtenerIdentidadEmpleado(int idOriginal) {
        String identidadEmpleado = null;
        String query = "SELECT identidad_empleado FROM empleados WHERE id = ?"; // Aquí asumo que "id" es la columna de la tabla que almacena el ID original.
        try (Connection conn = conectar(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, idOriginal); // El ID original del empleado
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                identidadEmpleado = rs.getString("identidad_empleado"); // Obtener la identidad del empleado
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return identidadEmpleado; // Devuelve la identidad, o null si no se encuentra
    }



}
