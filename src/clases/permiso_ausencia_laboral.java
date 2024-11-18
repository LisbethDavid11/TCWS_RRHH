package clases;

import java.sql.Time;
import java.util.Date;

public class permiso_ausencia_laboral {
	int id_permisos;
	String nombres_empleado;
	String apellidos_empleado;
	String identidad_empleado;
	int id_empleado;
	String tel_empleado;
	String correo_empleado;
	String cargo_empleado;
	String area_empleado;
	Time desde_hora;
	Time hasta_hora;
	Time total_horas;
	String motivo_ausencia;
	Date desde_fecha;
	Date hasta_fecha;
	int total_fecha;
	String nombres_recibe;
	Date fecha_recibe;
	String nombres_extiende;
	
	public String getNombres_extiende() {
		return nombres_extiende;
	}

	public void setNombres_extiende(String nombres_extiende) {
		this.nombres_extiende = nombres_extiende;
	}

	public int getId_permisos() {
		return id_permisos;
	}

	public void setId_permisos(int id_permisos) {
		this.id_permisos = id_permisos;
	}
	
	public String getIdentidad_empleado() {
		return identidad_empleado;
	}

	public void setIdentidad_empleado(String identidad_empleado) {
		this.identidad_empleado = identidad_empleado;
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
	
	public int getId_empleado() {
		return id_empleado;
	}

	public void setId_empleado(int id_empleado) {
		this.id_empleado = id_empleado;
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
	
	////////////////////////////////////////////////////////////////////////////
	public Time getDesde_hora() {
	    return desde_hora;
	}

	public void setDesde_hora(Time desde_hora) {
	    this.desde_hora = desde_hora;
	}
	
	public Date getHasta_hora() {
		return hasta_hora;
	}

	public void setHasta_hora(Time hasta_hora) {
		this.hasta_hora = hasta_hora;
	}
	
	
	
	public Time getTotal_horas() {
		return total_horas;
	}
	

	public void setTotal_horas(Time total_horas) {
		this.total_horas = total_horas;
	}
	
	public String getMotivo_ausencia() {
		return motivo_ausencia;
	}

	public void setMotivo_ausencia(String motivo_ausencia) {
		this.motivo_ausencia = motivo_ausencia;
	}
	
	public Date getDesde_fecha() {
		return desde_fecha;
	}

	public void setDesde_fecha(Date desde_fecha) {
		this.desde_fecha = desde_fecha;
	}
	
	public Date getHasta_fecha() {
		return hasta_fecha;
	}

	public void setHasta_fecha(Date hasta_fecha) {
		this.hasta_fecha = hasta_fecha;
	}
	
	public int getTotal_fecha() {
		return total_fecha;
	}

	public void setTotal_fecha(int total_fecha) {
		this.total_fecha = total_fecha;
	}
	
	public String getNombres_recibe() {
		return nombres_recibe;
	}

	public void setNombres_recibe(String nombres_recibe) {
		this.nombres_recibe = nombres_recibe;
	}
	
	public Date getFecha_recibe() {
		return fecha_recibe;
	}

	public void setFecha_recibe(Date fecha_recibe) {
		this.fecha_recibe = fecha_recibe;
	}

	
	
	
	
	

}
