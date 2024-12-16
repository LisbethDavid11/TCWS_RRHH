package clases;

public class roles_usuarios {

    private int idRol;
    private String nombreUsuario;
    private String contrasena;
    private String nombreRol;
    private String descripcionRol;
    private boolean permisosEmpleados;
    private boolean permisosAusenciaLaboral;
    private boolean permisosIncapacidades;
    private boolean permisosVacaciones;
    private boolean permisosCargos;
    private boolean permisosAreas;
    private boolean permisosReportes;
    private boolean permisosRespaldos;
    private boolean permisosUsuarios;
    private String fechaCreacion;

    // Constructor vacío
    public roles_usuarios() {
    }

    // Constructor con todos los campos
    public roles_usuarios(int idRol, String nombreUsuario, String contrasena, String nombreRol, String descripcionRol,
                         boolean permisosEmpleados, boolean permisosAusenciaLaboral, boolean permisosIncapacidades,
                         boolean permisosVacaciones, boolean permisosCargos, boolean permisosAreas,
                         boolean permisosReportes, boolean permisosRespaldos, boolean permisosUsuarios, String fechaCreacion) {
        this.idRol = idRol;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombreRol = nombreRol;
        this.descripcionRol = descripcionRol;
        this.permisosEmpleados = permisosEmpleados;
        this.permisosAusenciaLaboral = permisosAusenciaLaboral;
        this.permisosIncapacidades = permisosIncapacidades;
        this.permisosVacaciones = permisosVacaciones;
        this.permisosCargos = permisosCargos;
        this.permisosAreas = permisosAreas;
        this.permisosReportes = permisosReportes;
        this.permisosRespaldos = permisosRespaldos;
        this.permisosUsuarios = permisosUsuarios;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y Setters
    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public boolean isPermisosEmpleados() {
        return permisosEmpleados;
    }

    public void setPermisosEmpleados(boolean permisosEmpleados) {
        this.permisosEmpleados = permisosEmpleados;
    }

    public boolean isPermisosAusenciaLaboral() {
        return permisosAusenciaLaboral;
    }

    public void setPermisosAusenciaLaboral(boolean permisosAusenciaLaboral) {
        this.permisosAusenciaLaboral = permisosAusenciaLaboral;
    }

    public boolean isPermisosIncapacidades() {
        return permisosIncapacidades;
    }

    public void setPermisosIncapacidades(boolean permisosIncapacidades) {
        this.permisosIncapacidades = permisosIncapacidades;
    }

    public boolean isPermisosVacaciones() {
        return permisosVacaciones;
    }

    public void setPermisosVacaciones(boolean permisosVacaciones) {
        this.permisosVacaciones = permisosVacaciones;
    }

    public boolean isPermisosCargos() {
        return permisosCargos;
    }

    public void setPermisosCargos(boolean permisosCargos) {
        this.permisosCargos = permisosCargos;
    }

    public boolean isPermisosAreas() {
        return permisosAreas;
    }

    public void setPermisosAreas(boolean permisosAreas) {
        this.permisosAreas = permisosAreas;
    }

    public boolean isPermisosReportes() {
        return permisosReportes;
    }

    public void setPermisosReportes(boolean permisosReportes) {
        this.permisosReportes = permisosReportes;
    }

    public boolean isPermisosRespaldos() {
        return permisosRespaldos;
    }

    public void setPermisosRespaldos(boolean permisosRespaldos) {
        this.permisosRespaldos = permisosRespaldos;
    }

    public boolean isPermisosUsuarios() {
        return permisosUsuarios;
    }

    public void setPermisosUsuarios(boolean permisosUsuarios) {
        this.permisosUsuarios = permisosUsuarios;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "RolesUsuarios{" +
                "idRol=" + idRol +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", nombreRol='" + nombreRol + '\'' +
                ", descripcionRol='" + descripcionRol + '\'' +
                ", permisosEmpleados=" + permisosEmpleados +
                ", permisosAusenciaLaboral=" + permisosAusenciaLaboral +
                ", permisosIncapacidades=" + permisosIncapacidades +
                ", permisosVacaciones=" + permisosVacaciones +
                ", permisosCargos=" + permisosCargos +
                ", permisosAreas=" + permisosAreas +
                ", permisosReportes=" + permisosReportes +
                ", permisosRespaldos=" + permisosRespaldos +
                ", permisosUsuarios=" + permisosUsuarios +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                '}';
    }
}

