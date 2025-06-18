package clases;

import java.sql.Date;
import java.sql.Time;

public class injustificada {

    private int id_injustificadas;
    private int id_empleado;
    private String nombres_empleado;
    private String apellidos_empleado;
    private String identidad_empleado;
    private String tel_empleado;
    private String correo_empleado;
    private String cargo_empleado;
    private String area_empleado;
    private Date nacimiento_empleado;
    private String sexo_empleado;
    private int edad_empleado;
    private Date fecha_actual;
    private Time hora_actual;
    private String motivo;
    private Time hora_entrada;
    private Time hora_ausencia;
    private Date fecha_ausencia;
    private Time tiempo_injustificado;

    // Getters y Setters

    public Time getTiempo_injustificado() {
		return tiempo_injustificado;
	}

	public void setTiempo_injustificado(Time tiempo_injustificado) {
		this.tiempo_injustificado = tiempo_injustificado;
	}

	public int getId_injustificadas() {
        return id_injustificadas;
    }

    public void setId_injustificadas(int id_injustificadas) {
        this.id_injustificadas = id_injustificadas;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombres_empleado() {
        return nombres_empleado;
    }

    public void setNombres_empleado(String nombres_empleado) {
        this.nombres_empleado = nombres_empleado;
    }

    public String getApellidos_empleado() {
        return apellidos_empleado;
    }

    public void setApellidos_empleado(String apellidos_empleado) {
        this.apellidos_empleado = apellidos_empleado;
    }

    public String getIdentidad_empleado() {
        return identidad_empleado;
    }

    public void setIdentidad_empleado(String identidad_empleado) {
        this.identidad_empleado = identidad_empleado;
    }

    public String getTel_empleado() {
        return tel_empleado;
    }

    public void setTel_empleado(String tel_empleado) {
        this.tel_empleado = tel_empleado;
    }

    public String getCorreo_empleado() {
        return correo_empleado;
    }

    public void setCorreo_empleado(String correo_empleado) {
        this.correo_empleado = correo_empleado;
    }

    public String getCargo_empleado() {
        return cargo_empleado;
    }

    public void setCargo_empleado(String cargo_empleado) {
        this.cargo_empleado = cargo_empleado;
    }

    public String getArea_empleado() {
        return area_empleado;
    }

    public void setArea_empleado(String area_empleado) {
        this.area_empleado = area_empleado;
    }

    public Date getNacimiento_empleado() {
        return nacimiento_empleado;
    }

    public void setNacimiento_empleado(Date nacimiento_empleado) {
        this.nacimiento_empleado = nacimiento_empleado;
    }

    public String getSexo_empleado() {
        return sexo_empleado;
    }

    public void setSexo_empleado(String sexo_empleado) {
        this.sexo_empleado = sexo_empleado;
    }

    public int getEdad_empleado() {
        return edad_empleado;
    }

    public void setEdad_empleado(int edad_empleado) {
        this.edad_empleado = edad_empleado;
    }

    public Date getFecha_actual() {
        return fecha_actual;
    }

    public void setFecha_actual(Date fecha_actual) {
        this.fecha_actual = fecha_actual;
    }

    public Time getHora_actual() {
        return hora_actual;
    }

    public void setHora_actual(Time hora_actual) {
        this.hora_actual = hora_actual;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Time getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(Time hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public Time getHora_ausencia() {
        return hora_ausencia;
    }

    public void setHora_ausencia(Time hora_ausencia) {
        this.hora_ausencia = hora_ausencia;
    }

    public Date getFecha_ausencia() {
        return fecha_ausencia;
    }

    public void setFecha_ausencia(Date fecha_ausencia) {
        this.fecha_ausencia = fecha_ausencia;
    }
}
