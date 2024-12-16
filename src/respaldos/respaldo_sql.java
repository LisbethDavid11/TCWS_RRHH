package respaldos;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import conexion.conexion;

public class respaldo_sql {

	public void generarRespaldo() {
	    conexion conex = new conexion();
	    Connection conn = conex.conectar();
	    
	    LocalDate fechaActual = LocalDate.now();
	    String nombreArchivo = "respaldo_" + fechaActual + ".sql";
	    
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("Guardar respaldo de base de datos");
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos SQL", "sql");
	    fileChooser.setFileFilter(filter);
	    fileChooser.setSelectedFile(new java.io.File(nombreArchivo));
	    
	    int userSelection = fileChooser.showSaveDialog(null);
	    if (userSelection == JFileChooser.APPROVE_OPTION) {
	        java.io.File fileToSave = fileChooser.getSelectedFile();
	        try (PrintWriter writer = new PrintWriter(new FileWriter(fileToSave))) {
	            // Incluir la creación de las tablas
	            writer.println("CREATE DATABASE IF NOT EXISTS tcws1;");
	            writer.println("USE tcws1;\n");
	            
	            // Respaldo de cada tabla
	            respaldarTabla(conn, writer, "empleados");
	            respaldarTabla(conn, writer, "permisos_ausencia_laboral");
	            respaldarTabla(conn, writer, "incapacidad_laboral");
	            respaldarTabla(conn, writer, "vacaciones");
	            
	            writer.flush();
	            JOptionPane.showMessageDialog(null, "Respaldo generado correctamente en:\n" + fileToSave.getAbsolutePath(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        } catch (IOException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error al guardar el archivo de respaldo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	    
	    conex.desconectar(conn);
	}

	private void respaldarTabla(Connection conn, PrintWriter writer, String nombreTabla) {
	    try (Statement stmt = conn.createStatement()) {
	        // Obtener la estructura de la tabla
	        String createTableQuery = obtenerCreateTable(nombreTabla);
	        writer.println(createTableQuery);
	        writer.println("\n-- Respaldo de la tabla: " + nombreTabla + "\n");
	        
	        // Obtener los datos de la tabla
	        ResultSet rs = stmt.executeQuery("SELECT * FROM " + nombreTabla);
	        int columnCount = rs.getMetaData().getColumnCount();
	        
	        while (rs.next()) {
	            StringBuilder insertQuery = new StringBuilder("INSERT INTO " + nombreTabla + " VALUES (");
	            for (int i = 1; i <= columnCount; i++) {
	                String value = rs.getString(i);
	                
	                // Manejar valores null de forma adecuada
	                if (value == null) {
	                    insertQuery.append("NULL");
	                } else if (rs.getMetaData().getColumnTypeName(i).equalsIgnoreCase("VARCHAR")
	                        || rs.getMetaData().getColumnTypeName(i).equalsIgnoreCase("DATE")
	                        || rs.getMetaData().getColumnTypeName(i).equalsIgnoreCase("TIME")) {
	                    // Manejar campos de tipo VARCHAR, DATE y TIME con comillas
	                    insertQuery.append("'").append(value.replace("'", "''")).append("'");
	                } else {
	                    // Para otros tipos de datos numéricos
	                    insertQuery.append(value);
	                }
	                
	                if (i < columnCount) {
	                    insertQuery.append(", ");
	                }
	            }
	            insertQuery.append(");");
	            writer.println(insertQuery.toString());
	        }
	        writer.println();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al respaldar la tabla " + nombreTabla + ": " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

    
    private String obtenerCreateTable(String nombreTabla) {
        switch (nombreTabla) {
            case "empleados":
                return "CREATE TABLE IF NOT EXISTS empleados(\n"
                        + "    id INT PRIMARY KEY auto_increment,\n"
                        + "    id_empleado INT NOT NULL,\n"
                        + "    identidad_empleado VARCHAR (20) NOT NULL,\n"
                        + "    nombres_empleado VARCHAR(100) NOT NULL,\n"
                        + "    apellidos_empleado VARCHAR(100) NOT NULL,\n"
                        + "    sexo_empleado VARCHAR(20) NOT NULL,\n"
                        + "    nacimiento_empleado DATE NOT NULL,\n"
                        + "    civil_empleado VARCHAR(20) NOT NULL,\n"
                        + "    direccion_empleado VARCHAR(200) NOT NULL,\n"
                        + "    tel_empleado VARCHAR(10) NOT NULL,\n"
                        + "    correo_empleado VARCHAR (100) NOT NULL,\n"
                        + "    cargo_empleado VARCHAR(20) NOT NULL,\n"
                        + "    area_empleado VARCHAR(20) NOT NULL,\n"
                        + "    inicio_empleado DATE NOT NULL,\n"
                        + "    renuncia_empleado DATE,\n"
                        + "    fotografia_empleado VARCHAR (200),\n"
                        + "    cuenta_empleado VARCHAR(50)\n"
                        + ");";
            case "permisos_ausencia_laboral":
                return "CREATE TABLE IF NOT EXISTS permisos_ausencia_laboral(\n"
                        + "    id_permisos INT PRIMARY KEY AUTO_INCREMENT,\n"
                        + "    nombres_empleado VARCHAR(100) NOT NULL,\n"
                        + "    apellidos_empleado VARCHAR(100) NOT NULL,\n"
                        + "    identidad_empleado VARCHAR(15) NOT NULL,\n"
                        + "    id_empleado INT NOT NULL,\n"
                        + "    tel_empleado VARCHAR(10) NOT NULL,\n"
                        + "    correo_empleado VARCHAR(100) NOT NULL,\n"
                        + "    cargo_empleado VARCHAR(30) NOT NULL,\n"
                        + "    area_empleado VARCHAR(30) NOT NULL,\n"
                        + "    desde_hora TIME NOT NULL,\n"
                        + "    hasta_hora TIME NOT NULL,\n"
                        + "    total_horas TIME NOT NULL,\n"
                        + "    motivo_ausencia VARCHAR(200) NOT NULL,\n"
                        + "    desde_fecha DATE NOT NULL,\n"
                        + "    hasta_fecha DATE NOT NULL,\n"
                        + "    total_fecha INT NOT NULL,\n"
                        + "    nombres_recibe VARCHAR(100) NOT NULL,\n"
                        + "    fecha_recibe DATE NOT NULL\n"
                        + ");";
            case "incapacidad_laboral":
                return "CREATE TABLE IF NOT EXISTS incapacidad_laboral(\n"
                        + "    id_incapacidad INT PRIMARY KEY AUTO_INCREMENT,\n"
                        + "    id_empleado INT NOT NULL,\n"
                        + "    nombres_empleado VARCHAR(100) NOT NULL,\n"
                        + "    apellidos_empleado VARCHAR(100) NOT NULL,\n"
                        + "    identidad_empleado VARCHAR(15) NOT NULL,\n"
                        + "    tel_empleado VARCHAR(10) NOT NULL,\n"
                        + "    correo_empleado VARCHAR(100) NOT NULL,\n"
                        + "    cargo_empleado VARCHAR(25) NOT NULL,\n"
                        + "    area_empleado VARCHAR(20) NOT NULL,\n"
                        + "    nacimiento_empleado DATE NOT NULL,\n"
                        + "    sexo_empleado VARCHAR(20) NOT NULL,\n"
                        + "    edad_empleado INT NOT NULL,\n"
                        + "    riesgo_incapacidad VARCHAR(200) NOT NULL,\n"
                        + "    inicio_incapacidad DATE NOT NULL,\n"
                        + "    fin_incapacidad DATE NOT NULL,\n"
                        + "    total_dias INT NOT NULL,\n"
                        + "    tipo_incapacidad VARCHAR(100) NOT NULL,\n"
                        + "    tipo_reposo VARCHAR(50) NOT NULL,\n"
                        + "    fecha_expedicion DATE NOT NULL,\n"
                        + "    hora_expedicion TIME NOT NULL,\n"
                        + "    numero_certificado VARCHAR(50) NOT NULL,\n"
                        + "    fecha_actual DATE NOT NULL,\n"
                        + "    hora_actual TIME NOT NULL\n"
                        + ");";
            case "vacaciones":
                return "CREATE TABLE IF NOT EXISTS vacaciones(\n"
                        + "    id_vacaciones INT PRIMARY KEY AUTO_INCREMENT,\n"
                        + "    id_empleado INT NOT NULL,\n"
                        + "    nombres_empleado VARCHAR(100) NOT NULL,\n"
                        + "    apellidos_empleado VARCHAR(100) NOT NULL,\n"
                        + "    identidad_empleado VARCHAR(15) NOT NULL,\n"
                        + "    tel_empleado VARCHAR(10) NOT NULL,\n"
                        + "    correo_empleado VARCHAR(100) NOT NULL,\n"
                        + "    cargo_empleado VARCHAR(25) NOT NULL,\n"
                        + "    area_empleado VARCHAR(20) NOT NULL,\n"
                        + "    nacimiento_empleado DATE NOT NULL,\n"
                        + "    sexo_empleado VARCHAR(20) NOT NULL,\n"
                        + "    edad_empleado INT NOT NULL,\n"
                        + "    fecha_actual DATE NOT NULL,\n"
                        + "    hora_actual TIME NOT NULL,\n"
                        + "    antiguedad INT NOT NULL,\n"
                        + "    dias_correspondientes INT NOT NULL,\n"
                        + "    fecha_inicio_v DATE NOT NULL,\n"
                        + "    fecha_finalizacion_v DATE NOT NULL,\n"
                        + "    total_dias INT NOT NULL,\n"
                        + "    dias_pendientes INT NOT NULL,\n"
                        + "    pagadas VARCHAR(5) NOT NULL\n"
                        + ");";
            default:
                return "";
        }
    }
}

