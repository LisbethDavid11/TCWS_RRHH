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
                // Incluir la creación de la base de datos
                writer.println("CREATE DATABASE IF NOT EXISTS tcws1;");
                writer.println("USE tcws1;\n");

                // Obtener todas las tablas dinámicamente
                try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SHOW TABLES")) {
                    while (rs.next()) {
                        String tableName = rs.getString(1);
                        respaldarTabla(conn, writer, tableName);
                    }
                }

                writer.flush();
                JOptionPane.showMessageDialog(null, "Respaldo generado correctamente en:\n" + fileToSave.getAbsolutePath(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al guardar el archivo de respaldo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        conex.desconectar(conn);
    }

    private void respaldarTabla(Connection conn, PrintWriter writer, String nombreTabla) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            // Obtener la estructura de la tabla
            ResultSet rsCreate = stmt.executeQuery("SHOW CREATE TABLE " + nombreTabla);
            if (rsCreate.next()) {
                writer.println(rsCreate.getString(2) + ";\n");
            }

            writer.println("-- Respaldo de la tabla: " + nombreTabla + "\n");

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
        }
    }
}
