package respaldos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import conexion.conexion;

public class respaldo_sql {

    public void generarRespaldo() {
        conexion conex = new conexion();
        Connection conn = conex.conectar();

        // Generar nombre de archivo con fecha y hora
        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String nombreArchivo = "Respaldo_SRRHH_" + fechaActual.format(formatter) + ".sql";

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar respaldo de base de datos TCWS1");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos SQL", "sql");
        fileChooser.setFileFilter(filter);
        fileChooser.setSelectedFile(new java.io.File(nombreArchivo));

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            java.io.File fileToSave = fileChooser.getSelectedFile();
            
            // Asegurar que el archivo tenga extensión .sql
            if (!fileToSave.getAbsolutePath().endsWith(".sql")) {
                fileToSave = new java.io.File(fileToSave.getAbsolutePath() + ".sql");
            }
            
            try (PrintWriter writer = new PrintWriter(new FileWriter(fileToSave))) {
                // Encabezado del respaldo
                writer.println("-- ============================================================");
                writer.println("-- RESPALDO DE BASE DE DATOS SISTEMA RECURSOS HUMANOS");
                writer.println("-- Fecha de generación: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                writer.println("-- ============================================================\n");
                
                writer.println("DROP DATABASE IF EXISTS tcws1;");
                writer.println("CREATE DATABASE tcws1;");
                writer.println("USE tcws1;\n");

                // Deshabilitar verificaciones de llaves foráneas temporalmente
                writer.println("SET FOREIGN_KEY_CHECKS = 0;\n");

                // Orden específico de tablas para respetar dependencias
                String[] tablasOrdenadas = {
                    // 1. Catálogos
                    "cargos",
                    "areas",
                    
                    // 2. Usuarios y seguridad
                    "usuarios",
                    "roles_usuarios",
                    
                    // 3. Empleados (tabla principal)
                    "empleados",
                    
                    // 4. Asistencia y permisos
                    "asistencia_diaria",
                    "permisos_ausencia_hora",
                    "permisos_ausencia_laboral",
                    "injustificadas",
                    
                    // 5. Vacaciones e incapacidades
                    "vacaciones",
                    "incapacidad_laboral",
                    
                    // 6. Gestión disciplinaria
                    "memorandum"
                };

                // Respaldar cada tabla en el orden especificado
                for (String nombreTabla : tablasOrdenadas) {
                    if (tablaExiste(conn, nombreTabla)) {
                        respaldarTabla(conn, writer, nombreTabla);
                    }
                }

                // Reactivar verificaciones de llaves foráneas
                writer.println("SET FOREIGN_KEY_CHECKS = 1;\n");
                
                writer.println("-- ============================================================");
                writer.println("-- FIN DEL RESPALDO");
                writer.println("-- ============================================================");

                writer.flush();
                JOptionPane.showMessageDialog(null, 
                    "✓ Respaldo generado exitosamente\n\n" +
                    "Ubicación: " + fileToSave.getAbsolutePath() + "\n" +
                    "Tamaño: " + String.format("%.2f KB", fileToSave.length() / 1024.0), 
                    "Respaldo Completado", 
                    JOptionPane.INFORMATION_MESSAGE);
                    
            } catch (IOException | SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, 
                    "✗ Error al generar el respaldo:\n\n" + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }

        conex.desconectar(conn);
    }

    private boolean tablaExiste(Connection conn, String nombreTabla) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SHOW TABLES LIKE '" + nombreTabla + "'");
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    private void respaldarTabla(Connection conn, PrintWriter writer, String nombreTabla) throws SQLException {
        writer.println("-- ============================================================");
        writer.println("-- Tabla: " + nombreTabla.toUpperCase());
        writer.println("-- ============================================================\n");
        
        try (Statement stmt = conn.createStatement()) {
            // Obtener y escribir la estructura de la tabla
            ResultSet rsCreate = stmt.executeQuery("SHOW CREATE TABLE " + nombreTabla);
            if (rsCreate.next()) {
                writer.println("DROP TABLE IF EXISTS " + nombreTabla + ";");
                writer.println(rsCreate.getString(2) + ";\n");
            }

            // Contar registros
            ResultSet rsCount = stmt.executeQuery("SELECT COUNT(*) FROM " + nombreTabla);
            int totalRegistros = 0;
            if (rsCount.next()) {
                totalRegistros = rsCount.getInt(1);
            }
            
            writer.println("-- Respaldando " + totalRegistros + " registro(s)\n");

            // Obtener y escribir los datos de la tabla
            if (totalRegistros > 0) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM " + nombreTabla);
                int columnCount = rs.getMetaData().getColumnCount();

                while (rs.next()) {
                    StringBuilder insertQuery = new StringBuilder("INSERT INTO " + nombreTabla + " VALUES (");
                    
                    for (int i = 1; i <= columnCount; i++) {
                        Object value = rs.getObject(i);
                        String columnType = rs.getMetaData().getColumnTypeName(i);

                        // Manejar valores null
                        if (value == null) {
                            insertQuery.append("NULL");
                        } 
                        // Tipos de texto y fecha/hora
                        else if (columnType.equalsIgnoreCase("VARCHAR") 
                                || columnType.equalsIgnoreCase("CHAR")
                                || columnType.equalsIgnoreCase("TEXT")
                                || columnType.equalsIgnoreCase("LONGTEXT")
                                || columnType.equalsIgnoreCase("MEDIUMTEXT")
                                || columnType.equalsIgnoreCase("DATE")
                                || columnType.equalsIgnoreCase("TIME")
                                || columnType.equalsIgnoreCase("DATETIME")
                                || columnType.equalsIgnoreCase("TIMESTAMP")
                                || columnType.equalsIgnoreCase("ENUM")) {
                            // Escapar comillas simples
                            String stringValue = value.toString().replace("'", "''");
                            insertQuery.append("'").append(stringValue).append("'");
                        }
                        // Tipos booleanos
                        else if (columnType.equalsIgnoreCase("BOOLEAN") 
                                || columnType.equalsIgnoreCase("TINYINT(1)")) {
                            insertQuery.append(value);
                        }
                        // Tipos numéricos
                        else {
                            insertQuery.append(value);
                        }

                        if (i < columnCount) {
                            insertQuery.append(", ");
                        }
                    }
                    insertQuery.append(");");
                    writer.println(insertQuery.toString());
                }
            }
            
            writer.println("\n");
        }
    }
    
    // Método adicional para generar respaldo automático con selector de ubicación
    public boolean generarRespaldoAutomatico() {
        conexion conex = new conexion();
        Connection conn = conex.conectar();
        
        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String nombreArchivo = "respaldo_base_datos_" + fechaActual.format(formatter) + ".sql";
        
        // Crear JFileChooser para seleccionar ubicación
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar ubicación para respaldo automático");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos SQL", "sql");
        fileChooser.setFileFilter(filter);
        fileChooser.setSelectedFile(new java.io.File(nombreArchivo));
        
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            java.io.File fileToSave = fileChooser.getSelectedFile();
            
            // Asegurar que el archivo tenga extensión .sql
            if (!fileToSave.getAbsolutePath().endsWith(".sql")) {
                fileToSave = new java.io.File(fileToSave.getAbsolutePath() + ".sql");
            }
            
            try (PrintWriter writer = new PrintWriter(new FileWriter(fileToSave))) {
                writer.println("-- Respaldo automático generado: " + fechaActual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                writer.println("DROP DATABASE IF EXISTS tcws1;");
                writer.println("CREATE DATABASE tcws1;");
                writer.println("USE tcws1;\n");
                writer.println("SET FOREIGN_KEY_CHECKS = 0;\n");

                String[] tablasOrdenadas = {
                    "cargos", "areas", "usuarios", "roles_usuarios", "empleados",
                    "asistencia_diaria", "permisos_ausencia_hora", "permisos_ausencia_laboral",
                    "injustificadas", "vacaciones", "incapacidad_laboral", "memorandum"
                };

                for (String nombreTabla : tablasOrdenadas) {
                    if (tablaExiste(conn, nombreTabla)) {
                        respaldarTabla(conn, writer, nombreTabla);
                    }
                }

                writer.println("SET FOREIGN_KEY_CHECKS = 1;");
                writer.flush();
                
                JOptionPane.showMessageDialog(null, 
                    "Respaldo automático generado exitosamente\n\n" +
                    "Ubicación: " + fileToSave.getAbsolutePath(), 
                    "Respaldo Completado", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                conex.desconectar(conn);
                return true;
                
            } catch (IOException | SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, 
                    "Error al generar el respaldo automático:\n\n" + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                conex.desconectar(conn);
                return false;
            }
        } else {
            // Usuario canceló la operación
            conex.desconectar(conn);
            return false;
        }
    }
}