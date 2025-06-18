package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;

import javax.swing.JOptionPane;

import conexion.conexion;
import clases.injustificada;

public class consultas_injustificadas extends conexion {

    public boolean guardar_injustificada(injustificada injustificada) {
        PreparedStatement ps = null;
        Connection con = conectar();

        String sql = "INSERT INTO injustificadas (id_empleado, nombres_empleado, apellidos_empleado, identidad_empleado, tel_empleado, "+
                     "correo_empleado, cargo_empleado, area_empleado, nacimiento_empleado, sexo_empleado, edad_empleado, "+
                     "fecha_actual, hora_actual, motivo, hora_entrada, hora_ausencia, tiempo_injustificado, fecha_ausencia) "+
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(sql);

            ps.setInt(1, injustificada.getId_empleado());
            ps.setString(2, injustificada.getNombres_empleado());
            ps.setString(3, injustificada.getApellidos_empleado());
            ps.setString(4, injustificada.getIdentidad_empleado());
            ps.setString(5, injustificada.getTel_empleado());
            ps.setString(6, injustificada.getCorreo_empleado());
            ps.setString(7, injustificada.getCargo_empleado());
            ps.setString(8, injustificada.getArea_empleado());
            ps.setDate(9, injustificada.getNacimiento_empleado());
            ps.setString(10, injustificada.getSexo_empleado());
            ps.setInt(11, injustificada.getEdad_empleado());
            ps.setDate(12, injustificada.getFecha_actual());
            ps.setTime(13, injustificada.getHora_actual());
            ps.setString(14, injustificada.getMotivo());
            ps.setTime(15, injustificada.getHora_entrada());
            ps.setTime(16, injustificada.getHora_ausencia());
            ps.setTime(17, injustificada.getTiempo_injustificado());
            ps.setDate(18, injustificada.getFecha_ausencia());

            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "Error al guardar la ausencia injustificada", "Error", JOptionPane.ERROR_MESSAGE);
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

    public boolean eliminar_injustificada(int id) {
        PreparedStatement ps = null;
        Connection con = conectar();

        String sql = "DELETE FROM injustificadas WHERE id_injustificadas = ?";

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

    public injustificada obtenerInjustificadaPorId(int idInjustificada) {
        injustificada injus = null;
        Connection con = conectar();

        String sql = "SELECT * FROM injustificadas WHERE id_injustificadas = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idInjustificada);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                injus = new injustificada();
                injus.setId_injustificadas(rs.getInt("id_injustificadas"));
                injus.setId_empleado(rs.getInt("id_empleado"));
                injus.setNombres_empleado(rs.getString("nombres_empleado"));
                injus.setApellidos_empleado(rs.getString("apellidos_empleado"));
                injus.setIdentidad_empleado(rs.getString("identidad_empleado"));
                injus.setTel_empleado(rs.getString("tel_empleado"));
                injus.setCorreo_empleado(rs.getString("correo_empleado"));
                injus.setCargo_empleado(rs.getString("cargo_empleado"));
                injus.setArea_empleado(rs.getString("area_empleado"));
                injus.setNacimiento_empleado(rs.getDate("nacimiento_empleado"));
                injus.setSexo_empleado(rs.getString("sexo_empleado"));
                injus.setEdad_empleado(rs.getInt("edad_empleado"));
                injus.setFecha_actual(rs.getDate("fecha_actual"));
                injus.setHora_actual(rs.getTime("hora_actual"));
                injus.setMotivo(rs.getString("motivo"));
                injus.setHora_entrada(rs.getTime("hora_entrada"));
                injus.setHora_ausencia(rs.getTime("hora_ausencia"));
                injus.setTiempo_injustificado(rs.getTime("tiempo_injustificado"));
                injus.setFecha_ausencia(rs.getDate("fecha_ausencia"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            desconectar(con);
        }
        return injus;
    }
    
    
    public boolean actualizar_injustificada(injustificada inj) {
        PreparedStatement ps = null;
        Connection con = conectar();

        String sql = "UPDATE injustificadas SET id_empleado=?, nombres_empleado=?, apellidos_empleado=?, identidad_empleado=?, " +
                     "tel_empleado=?, correo_empleado=?, cargo_empleado=?, area_empleado=?, nacimiento_empleado=?, " +
                     "sexo_empleado=?, edad_empleado=?, fecha_actual=?, hora_actual=?, motivo=?, hora_entrada=?, " +
                     "hora_ausencia=?, tiempo_injustificado=?, fecha_ausencia=? WHERE id_injustificadas=?";

        try {
            ps = con.prepareStatement(sql);

            ps.setInt(1, inj.getId_empleado());
            ps.setString(2, inj.getNombres_empleado());
            ps.setString(3, inj.getApellidos_empleado());
            ps.setString(4, inj.getIdentidad_empleado());
            ps.setString(5, inj.getTel_empleado());
            ps.setString(6, inj.getCorreo_empleado());
            ps.setString(7, inj.getCargo_empleado());
            ps.setString(8, inj.getArea_empleado());
            ps.setDate(9, inj.getNacimiento_empleado());
            ps.setString(10, inj.getSexo_empleado());
            ps.setInt(11, inj.getEdad_empleado());
            ps.setDate(12, inj.getFecha_actual());
            ps.setTime(13, inj.getHora_actual());
            ps.setString(14, inj.getMotivo());
            ps.setTime(15, inj.getHora_entrada());
            ps.setTime(16, inj.getHora_ausencia());
            ps.setTime(17, inj.getTiempo_injustificado());
            ps.setDate(18, inj.getFecha_ausencia());
            ps.setInt(19, inj.getId_injustificadas()); // where

            return ps.executeUpdate() > 0;

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

}

