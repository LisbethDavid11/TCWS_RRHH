package consultas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import clases.memorandum;
import conexion.conexion;

public class consultas_memorandum {

    // ====== SQLs ======
    private static final String SQL_LISTAR =
        "SELECT id_memorandum, id_empleado, nombres_empleado, apellidos_empleado, identidad_empleado, " +
        "       tel_empleado, correo_empleado, cargo_empleado, area_empleado, nacimiento_empleado, " +
        "       sexo_empleado, edad_empleado, fecha_actual, hora_actual, motivo_memorandum " +
        "FROM memorandum ORDER BY id_memorandum DESC";

    private static final String SQL_INSERTAR =
        "INSERT INTO memorandum (" +
        " id_empleado, nombres_empleado, apellidos_empleado, identidad_empleado, tel_empleado, " +
        " correo_empleado, cargo_empleado, area_empleado, nacimiento_empleado, sexo_empleado, " +
        " edad_empleado, fecha_actual, hora_actual, motivo_memorandum) " +
        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String SQL_ACTUALIZAR =
        "UPDATE memorandum SET " +
        " id_empleado=?, nombres_empleado=?, apellidos_empleado=?, identidad_empleado=?, tel_empleado=?, " +
        " correo_empleado=?, cargo_empleado=?, area_empleado=?, nacimiento_empleado=?, sexo_empleado=?, " +
        " edad_empleado=?, fecha_actual=?, hora_actual=?, motivo_memorandum=? " +
        "WHERE id_memorandum=?";

    private static final String SQL_ELIMINAR =
        "DELETE FROM memorandum WHERE id_memorandum=?";

    private static final String SQL_BUSCAR_POR_ID =
        "SELECT id_memorandum, id_empleado, nombres_empleado, apellidos_empleado, identidad_empleado, " +
        "       tel_empleado, correo_empleado, cargo_empleado, area_empleado, nacimiento_empleado, " +
        "       sexo_empleado, edad_empleado, fecha_actual, hora_actual, motivo_memorandum " +
        "FROM memorandum WHERE id_memorandum=?";

    // ====== CRUD ======

    public List<memorandum> listar() {
        List<memorandum> lista = new ArrayList<>();
        conexion con = new conexion();
        try (Connection cn = con.conectar();
             PreparedStatement ps = cn.prepareStatement(SQL_LISTAR);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean insertar_memorandum(memorandum m) {
        conexion con = new conexion();
        try (Connection cn = con.conectar();
             PreparedStatement ps = cn.prepareStatement(SQL_INSERTAR)) {

            int i = 1;
            ps.setInt(i++, m.getId_empleado());
            ps.setString(i++, m.getNombres_empleado());
            ps.setString(i++, m.getApellidos_empleado());
            ps.setString(i++, m.getIdentidad_empleado());
            ps.setString(i++, m.getTel_empleado());
            ps.setString(i++, m.getCorreo_empleado());
            ps.setString(i++, m.getCargo_empleado());
            ps.setString(i++, m.getArea_empleado());
            ps.setDate(i++, toSqlDate(m.getNacimiento_empleado()));
            ps.setString(i++, m.getSexo_empleado());
            ps.setInt(i++, m.getEdad_empleado());
            ps.setDate(i++, toSqlDate(m.getFecha_actual()));
            ps.setTime(i++, m.getHora_actual());            // ya es java.sql.Time en tu POJO
            ps.setString(i++, m.getMotivo_memorandum());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar_memorandum(memorandum m) {
        conexion con = new conexion();
        try (Connection cn = con.conectar();
             PreparedStatement ps = cn.prepareStatement(SQL_ACTUALIZAR)) {

            int i = 1;
            ps.setInt(i++, m.getId_empleado());
            ps.setString(i++, m.getNombres_empleado());
            ps.setString(i++, m.getApellidos_empleado());
            ps.setString(i++, m.getIdentidad_empleado());
            ps.setString(i++, m.getTel_empleado());
            ps.setString(i++, m.getCorreo_empleado());
            ps.setString(i++, m.getCargo_empleado());
            ps.setString(i++, m.getArea_empleado());
            ps.setDate(i++, toSqlDate(m.getNacimiento_empleado()));
            ps.setString(i++, m.getSexo_empleado());
            ps.setInt(i++, m.getEdad_empleado());
            ps.setDate(i++, toSqlDate(m.getFecha_actual()));
            ps.setTime(i++, m.getHora_actual());
            ps.setString(i++, m.getMotivo_memorandum());
            ps.setInt(i++, m.getId_memorandum()); // WHERE

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar_memorandum(int id_memorandum) {
        conexion con = new conexion();
        try (Connection cn = con.conectar();
             PreparedStatement ps = cn.prepareStatement(SQL_ELIMINAR)) {

            ps.setInt(1, id_memorandum);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public memorandum buscar_por_id(int id_memorandum) {
        conexion con = new conexion();
        try (Connection cn = con.conectar();
             PreparedStatement ps = cn.prepareStatement(SQL_BUSCAR_POR_ID)) {

            ps.setInt(1, id_memorandum);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ====== Helpers ======

    private memorandum mapRow(ResultSet rs) throws SQLException {
        memorandum m = new memorandum();
        m.setId_memorandum(rs.getInt("id_memorandum"));
        m.setId_empleado(rs.getInt("id_empleado"));
        m.setNombres_empleado(rs.getString("nombres_empleado"));
        m.setApellidos_empleado(rs.getString("apellidos_empleado"));
        m.setIdentidad_empleado(rs.getString("identidad_empleado"));
        m.setTel_empleado(rs.getString("tel_empleado"));
        m.setCorreo_empleado(rs.getString("correo_empleado"));
        m.setCargo_empleado(rs.getString("cargo_empleado"));
        m.setArea_empleado(rs.getString("area_empleado"));
        m.setNacimiento_empleado(rs.getDate("nacimiento_empleado"));
        m.setSexo_empleado(rs.getString("sexo_empleado"));
        m.setEdad_empleado(rs.getInt("edad_empleado"));
        m.setFecha_actual(rs.getDate("fecha_actual"));
        m.setHora_actual(rs.getTime("hora_actual"));
        m.setMotivo_memorandum(rs.getString("motivo_memorandum"));
        return m;
    }

    private java.sql.Date toSqlDate(java.util.Date d) {
        return (d == null) ? null : new java.sql.Date(d.getTime());
    }
}
