package clases;

import java.io.Serializable;
import java.util.Date;
import java.sql.Time;

public class memorandum implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Campos (según la tabla memorandum)
    private int id_memorandum;
    private int id_empleado;
    private String nombres_empleado;
    private String apellidos_empleado;
    private String identidad_empleado;
    private String tel_empleado;
    private String correo_empleado;
    private String cargo_empleado;
    private String area_empleado;
    private Date nacimiento_empleado;   // DATE
    private String sexo_empleado;
    private int   edad_empleado;
    private Date  fecha_actual;         // DATE
    private Time  hora_actual;          // TIME
    private String motivo_memorandum;

    // Constructor vacío
    public memorandum() { }

    // Constructor completo
    public memorandum(
            int id_memorandum,
            int id_empleado,
            String nombres_empleado,
            String apellidos_empleado,
            String identidad_empleado,
            String tel_empleado,
            String correo_empleado,
            String cargo_empleado,
            String area_empleado,
            Date nacimiento_empleado,
            String sexo_empleado,
            int edad_empleado,
            Date fecha_actual,
            Time hora_actual,
            String motivo_memorandum) {

        this.id_memorandum = id_memorandum;
        this.id_empleado = id_empleado;
        this.nombres_empleado = nombres_empleado;
        this.apellidos_empleado = apellidos_empleado;
        this.identidad_empleado = identidad_empleado;
        this.tel_empleado = tel_empleado;
        this.correo_empleado = correo_empleado;
        this.cargo_empleado = cargo_empleado;
        this.area_empleado = area_empleado;
        this.nacimiento_empleado = nacimiento_empleado;
        this.sexo_empleado = sexo_empleado;
        this.edad_empleado = edad_empleado;
        this.fecha_actual = fecha_actual;
        this.hora_actual = hora_actual;
        this.motivo_memorandum = motivo_memorandum;
    }

    // Getters y Setters (manteniendo los nombres que ya usas)

    public int getId_memorandum() { return id_memorandum; }
    public void setId_memorandum(int id_memorandum) { this.id_memorandum = id_memorandum; }

    public int getId_empleado() { return id_empleado; }
    public void setId_empleado(int id_empleado) { this.id_empleado = id_empleado; }

    public String getNombres_empleado() { return nombres_empleado; }
    public void setNombres_empleado(String nombres_empleado) { this.nombres_empleado = nombres_empleado; }

    public String getApellidos_empleado() { return apellidos_empleado; }
    public void setApellidos_empleado(String apellidos_empleado) { this.apellidos_empleado = apellidos_empleado; }

    public String getIdentidad_empleado() { return identidad_empleado; }
    public void setIdentidad_empleado(String identidad_empleado) { this.identidad_empleado = identidad_empleado; }

    public String getTel_empleado() { return tel_empleado; }
    public void setTel_empleado(String tel_empleado) { this.tel_empleado = tel_empleado; }

    public String getCorreo_empleado() { return correo_empleado; }
    public void setCorreo_empleado(String correo_empleado) { this.correo_empleado = correo_empleado; }

    public String getCargo_empleado() { return cargo_empleado; }
    public void setCargo_empleado(String cargo_empleado) { this.cargo_empleado = cargo_empleado; }

    public String getArea_empleado() { return area_empleado; }
    public void setArea_empleado(String area_empleado) { this.area_empleado = area_empleado; }

    public Date getNacimiento_empleado() { return nacimiento_empleado; }
    public void setNacimiento_empleado(Date nacimiento_empleado) { this.nacimiento_empleado = nacimiento_empleado; }

    public String getSexo_empleado() { return sexo_empleado; }
    public void setSexo_empleado(String sexo_empleado) { this.sexo_empleado = sexo_empleado; }

    public int getEdad_empleado() { return edad_empleado; }
    public void setEdad_empleado(int edad_empleado) { this.edad_empleado = edad_empleado; }

    public Date getFecha_actual() { return fecha_actual; }
    public void setFecha_actual(Date fecha_actual) { this.fecha_actual = fecha_actual; }

    public Time getHora_actual() { return hora_actual; }
    public void setHora_actual(Time hora_actual) { this.hora_actual = hora_actual; }

    public String getMotivo_memorandum() { return motivo_memorandum; }
    public void setMotivo_memorandum(String motivo_memorandum) { this.motivo_memorandum = motivo_memorandum; }

    @Override
    public String toString() {
        return "memorandum{" +
                "id_memorandum=" + id_memorandum +
                ", id_empleado=" + id_empleado +
                ", nombres_empleado='" + nombres_empleado + '\'' +
                ", apellidos_empleado='" + apellidos_empleado + '\'' +
                ", identidad_empleado='" + identidad_empleado + '\'' +
                ", tel_empleado='" + tel_empleado + '\'' +
                ", correo_empleado='" + correo_empleado + '\'' +
                ", cargo_empleado='" + cargo_empleado + '\'' +
                ", area_empleado='" + area_empleado + '\'' +
                ", nacimiento_empleado=" + nacimiento_empleado +
                ", sexo_empleado='" + sexo_empleado + '\'' +
                ", edad_empleado=" + edad_empleado +
                ", fecha_actual=" + fecha_actual +
                ", hora_actual=" + hora_actual +
                ", motivo_memorandum='" + motivo_memorandum + '\'' +
                '}';
    }
}
