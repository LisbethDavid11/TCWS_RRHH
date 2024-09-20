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
	public boolean actualizar_empleado(empleado empleado, int nuevoIdEmpleado, Date fecha_nacimiento, Date fecha_inicio, Date fecha_renuncia) {
	    PreparedStatement ps = null;
	    Connection con = conectar();

	    try {
	        // Verificar si el nuevo id_empleado ya está en uso por otro registro
	        if (nuevoIdEmpleado != empleado.getId()) {
	            String verificarSql = "SELECT COUNT(*) FROM empleados WHERE id_empleado = ? AND id <> ?";
	            ps = con.prepareStatement(verificarSql);
	            ps.setInt(1, empleado.getId());
	            ps.setInt(2, empleado.getId_empleado());
	            ResultSet rs = ps.executeQuery();

	            if (rs.next() && rs.getInt(1) > 0) {
	                JOptionPane.showMessageDialog(null, "Error: El nuevo ID de empleado ya está en uso.");
	                return false;
	            }
	        }

	        // Proceder a la actualización
	        String sql = "UPDATE empleados SET id_empleado=?, identidad_empleado=?, nombres_empleado=?, apellidos_empleado=?, sexo_empleado=?, "
	                + "nacimiento_empleado=?, civil_empleado=?, direccion_empleado=?, tel_empleado=?, correo_empleado=?, "
	                + "cargo_empleado=?, area_empleado=?, inicio_empleado=?, renuncia_empleado=?, fotografia_empleado=?, "
	                + "cuenta_empleado=? WHERE id = ?";

	        ps = con.prepareStatement(sql);
	        ps.setInt(1, nuevoIdEmpleado);
	        ps.setString(2, empleado.getIdentidad_empleado());
	        ps.setString(3, empleado.getNombres_empleado());
	        ps.setString(4, empleado.getApellidos_empleado());
	        ps.setString(5, empleado.getSexo_empleado());
	        ps.setDate(6, new java.sql.Date(fecha_nacimiento.getTime()));
	        ps.setString(7, empleado.getCivil_empleado());
	        ps.setString(8, empleado.getDireccion_empleado());
	        ps.setString(9, empleado.getTel_empleado());
	        ps.setString(10, empleado.getCorreo_empleado());
	        ps.setString(11, empleado.getCargo_empleado());
	        ps.setString(12, empleado.getArea_empleado());
	        ps.setDate(13, new java.sql.Date(fecha_inicio.getTime()));
	        ps.setDate(14, fecha_renuncia != null ? new java.sql.Date(fecha_renuncia.getTime()) : null);
	        ps.setString(15, empleado.getFotografia_empleado());
	        ps.setString(16, empleado.getCuenta_empleado());
	        ps.setInt(17, empleado.getId());

	        int rowsUpdated = ps.executeUpdate();
	        return rowsUpdated > 0;

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
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	public boolean eliminar_empleado(int id) {
	    PreparedStatement ps = null;
	    Connection con = conectar();
	    
	    String sql = "DELETE FROM empleados WHERE id = ?";  
	    
	    try {
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, id);
	        
	        int rowsDeleted = ps.executeUpdate();
	        return rowsDeleted > 0;  // Devuelve true si se eliminó al menos un registro
	        
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
	
	// Método para obtener los datos de un empleado por su ID
    public empleado obtenerEmpleadoPorId(int idEmpleado) {
        conexion conex = new conexion();
        empleado empleado = null; // Inicializamos como null, para verificar si no se encuentra ningún empleado.

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
            conex.desconectar(); // Desconectamos la conexión.

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al consultar los datos del empleado", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return empleado; 
    }
    
    
    
    public String empleadoExiste(int id_empleado, String identidad_empleado, String nombres_empleado, String apellidos_empleado, 
            Date nacimiento_empleado, String direccion_empleado, String tel_empleado, 
            String correo_empleado, Date inicio_empleado, String cuenta_empleado) {
PreparedStatement ps = null;
ResultSet rs = null;
Connection con = conectar(); // Asumiendo que ya tienes un método conectar()

String sql = "SELECT * FROM empleados WHERE id_empleado = ? OR identidad_empleado = ? OR nombres_empleado = ? OR " +
"apellidos_empleado = ? OR nacimiento_empleado = ? OR direccion_empleado = ? OR " +
"tel_empleado = ? OR correo_empleado = ? OR inicio_empleado = ? OR cuenta_empleado = ?";

try {
ps = con.prepareStatement(sql);
ps.setInt(1, id_empleado);
ps.setString(2, identidad_empleado);
ps.setString(3, nombres_empleado);
ps.setString(4, apellidos_empleado);
ps.setDate(5, new java.sql.Date(nacimiento_empleado.getTime())); // Convertir Date a java.sql.Date
ps.setString(6, direccion_empleado);
ps.setString(7, tel_empleado);
ps.setString(8, correo_empleado);
ps.setDate(9, new java.sql.Date(inicio_empleado.getTime())); // Convertir Date a java.sql.Date
ps.setString(10, cuenta_empleado);

rs = ps.executeQuery();

// Si hay un resultado, verificar cuál campo está duplicado
if (rs.next()) {
if (rs.getInt("id_empleado") == id_empleado) {
return "ID del empleado";
} else if (rs.getString("identidad_empleado").equals(identidad_empleado)) {
return "Identidad";
} else if (rs.getString("nombres_empleado").equals(nombres_empleado) && rs.getString("apellidos_empleado").equals(apellidos_empleado)) {
return "Nombre y Apellido";
} else if (rs.getDate("nacimiento_empleado").equals(new java.sql.Date(nacimiento_empleado.getTime()))) {
return "Fecha de nacimiento";
} else if (rs.getString("direccion_empleado").equals(direccion_empleado)) {
return "Dirección";
} else if (rs.getString("tel_empleado").equals(tel_empleado)) {
return "Teléfono";
} else if (rs.getString("correo_empleado").equals(correo_empleado)) {
return "Correo electrónico";
} else if (rs.getDate("inicio_empleado").equals(new java.sql.Date(inicio_empleado.getTime()))) {
return "Fecha de inicio";
} else if (rs.getString("cuenta_empleado").equals(cuenta_empleado)) {
return "Cuenta bancaria";
}
}
return null; // No hay duplicados
} catch (SQLException e) {
e.printStackTrace();
return null;
} finally {
try {
if (rs != null) rs.close();
if (ps != null) ps.close();
desconectar(); // Asumiendo que ya tienes un método desconectar()
} catch (SQLException e) {
e.printStackTrace();
}
}
}
    
    
    public String empleadoExisteParaActualizar(int id, int nuevoIdEmpleado, String identidad_empleado) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = conectar(); // Asumiendo que ya tienes un método conectar()

        String sql = "SELECT * FROM empleados WHERE (identidad_empleado = ? OR id_empleado = ?) AND id != ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, identidad_empleado);
            ps.setInt(2, nuevoIdEmpleado);
            ps.setInt(3, id); // Excluir el registro actual de la búsqueda

            rs = ps.executeQuery();

            // Si hay un resultado, verificar cuál campo está duplicado
            if (rs.next()) {
                if (rs.getString("identidad_empleado").equals(identidad_empleado)) {
                    return "Identidad";
                } else if (rs.getInt("id_empleado") == nuevoIdEmpleado) {
                    return "ID del empleado";
                }
            }
            return null; // No hay duplicados
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                desconectar(); // Asumiendo que ya tienes un método desconectar()
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    public boolean actualizar_empleado(empleado emp, int idOriginal, int nuevoIdEmpleado, Date fechaNacimiento, Date fechaInicio, Date fechaRenuncia) {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = conectar(); // Asumiendo que tienes un método 'conectar()' que devuelve una conexión
            
            String sql = "UPDATE empleados SET " +
                         "id_empleado=?, identidad_empleado=?, nombres_empleado=?, apellidos_empleado=?, " +
                         "sexo_empleado=?, nacimiento_empleado=?, civil_empleado=?, direccion_empleado=?, " +
                         "tel_empleado=?, correo_empleado=?, cargo_empleado=?, area_empleado=?, " +
                         "inicio_empleado=?, renuncia_empleado=?, fotografia_empleado=?, cuenta_empleado=? " +
                         "WHERE id=?";
            
            ps = con.prepareStatement(sql);
            
            // Establecer los valores de los parámetros
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
            ps.setInt(i++, idOriginal); // Usar el ID original para la cláusula WHERE
            
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





    
    


   
    









}
