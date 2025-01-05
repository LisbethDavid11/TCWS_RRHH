package respaldos;

import javax.swing.*;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.awt.*;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class respaldo_excel {

    public void generarRespaldoExcel() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar respaldo Excel");

        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String nombreArchivo = "Respaldo_Excel_" + fechaActual.format(formatter) + ".xlsx";

        fileChooser.setSelectedFile(new File(nombreArchivo));

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            if (!archivo.getName().toLowerCase().endsWith(".xlsx")) {
                archivo = new File(archivo.getAbsolutePath() + ".xlsx");
            }

            // Confirmar sobreescritura si existe
            if (archivo.exists()) {
                int opcion = JOptionPane.showConfirmDialog(null, "El archivo ya existe. ¿Desea sobrescribirlo?",
                        "Confirmar", JOptionPane.YES_NO_OPTION);
                if (opcion != JOptionPane.YES_OPTION) {
                    return;
                }
            }

            try (XSSFWorkbook workbook = new XSSFWorkbook();
                 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tcws1", "root", "mendoza11.");
                 FileOutputStream fileOut = new FileOutputStream(archivo)) {

                // Obtener todas las tablas de la base de datos
                DatabaseMetaData metaData = con.getMetaData();
                ResultSet tablas = metaData.getTables(null, null, null, new String[]{"TABLE"});

                while (tablas.next()) {
                    String nombreTabla = tablas.getString("TABLE_NAME");
                    crearHojaTabla(con, workbook, nombreTabla);
                }

                // Escribir archivo Excel
                workbook.write(fileOut);
                Desktop.getDesktop().open(archivo);
                JOptionPane.showMessageDialog(null, "Respaldo generado exitosamente");

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al generar el respaldo: " + e.getMessage());
            }
        }
    }

    private void crearHojaTabla(Connection con, XSSFWorkbook workbook, String nombreTabla) throws SQLException {
        XSSFSheet hoja = workbook.createSheet(nombreTabla);
        String query = "SELECT * FROM " + nombreTabla;

        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnas = rsmd.getColumnCount();

            // Encabezados
            XSSFRow headerRow = hoja.createRow(0);
            for (int i = 1; i <= columnas; i++) {
                Cell cell = headerRow.createCell(i - 1);
                cell.setCellValue(rsmd.getColumnName(i));
            }

            // Llenar datos
            int rowNum = 1;
            while (rs.next()) {
                XSSFRow fila = hoja.createRow(rowNum++);
                for (int i = 1; i <= columnas; i++) {
                    Cell cell = fila.createCell(i - 1);
                    String valor = rs.getString(i);

                    // Manejar imágenes si la columna es de imagen
                    if (rsmd.getColumnName(i).equalsIgnoreCase("fotografia_empleado") && valor != null && !valor.isEmpty()) {
                        agregarImagenExcel(workbook, hoja, valor, rowNum - 1, i - 1);
                    } else {
                        cell.setCellValue(valor != null ? valor : "");
                    }
                }
            }
        }
    }

    private void agregarImagenExcel(XSSFWorkbook workbook, XSSFSheet hoja, String rutaImagen, int row, int col) {
        try (InputStream inputStream = new FileInputStream(rutaImagen)) {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);

            Drawing<?> drawing = hoja.createDrawingPatriarch();
            XSSFClientAnchor anchor = workbook.getCreationHelper().createClientAnchor();
            anchor.setCol1(col);
            anchor.setRow1(row);

            Picture picture = drawing.createPicture(anchor, pictureIdx);
            picture.resize(1, 1); // Ajustar tamaño de imagen
        } catch (IOException e) {
            System.err.println("Error al agregar imagen: " + e.getMessage());
        }
    }
}
