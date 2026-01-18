package reportes;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import conexion.conexion;

public class reporte_incapacidad_mensual {

    public void generarReporteIncapacidades(int mes) {

        conexion conex = new conexion();

        try {
            Statement estatuto = conex.conectar().createStatement();

            // 🔹 Consulta mensual
            String query = "SELECT * FROM incapacidad_laboral "
                         + "WHERE MONTH(fecha_actual) = " + mes + " "
                         + "ORDER BY fecha_actual ASC";

            ResultSet rs = estatuto.executeQuery(query);

            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null,
                        "No hay incapacidades registradas en ese mes.",
                        "Sin datos",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Fecha actual para el nombre del archivo
            LocalDate hoy = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String fechaActual = hoy.format(formatter);

            String nombreArchivo = "Reporte_Incapacidades_Mensual_" + fechaActual + ".pdf";

            // Guardar archivo
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar Reporte de Incapacidades");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos PDF", "pdf"));
            fileChooser.setSelectedFile(new File(nombreArchivo));

            int seleccion = fileChooser.showSaveDialog(null);
            if (seleccion != JFileChooser.APPROVE_OPTION) return;

            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getName().toLowerCase().endsWith(".pdf")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".pdf");
            }

            // Documento PDF
            PdfWriter writer = new PdfWriter(fileToSave);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4.rotate());

            // Encabezado institucional
            encabezado_documentos encabezado = new encabezado_documentos();
            encabezado.agregarEncabezado(document);

            document.add(new Paragraph("Reporte Mensual de Incapacidades Laborales")
                    .setBold()
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.CENTER));

            document.add(new Paragraph("\n"));

            // 🔹 Tabla
            float[] columnWidths = {
                    0.5f, 0.8f, 1.4f, 1.4f, 1.4f, 1.2f,
                    1.2f, 1.2f, 1.2f, 1.2f, 1f, 1.3f, 1.2f
            };

            Table table = new Table(UnitValue.createPercentArray(columnWidths))
                    .setWidth(UnitValue.createPercentValue(100));

            // Encabezados
            table.addHeaderCell("No.");
            table.addHeaderCell("ID Emp.");
            table.addHeaderCell("Nombres");
            table.addHeaderCell("Apellidos");
            table.addHeaderCell("Identidad");
            table.addHeaderCell("Teléfono");
            table.addHeaderCell("Cargo");
            table.addHeaderCell("Área");
            table.addHeaderCell("Inicio");
            table.addHeaderCell("Fin");
            table.addHeaderCell("Total Días");
            table.addHeaderCell("Tipo Incapacidad");
            table.addHeaderCell("Riesgo");

            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");

            int contador = 1;

            while (rs.next()) {

                table.addCell(String.valueOf(contador++));
                table.addCell(rs.getString("id_empleado"));
                table.addCell(rs.getString("nombres_empleado"));
                table.addCell(rs.getString("apellidos_empleado"));
                table.addCell(rs.getString("identidad_empleado"));
                table.addCell(rs.getString("tel_empleado"));
                table.addCell(rs.getString("cargo_empleado"));
                table.addCell(rs.getString("area_empleado"));
                table.addCell(formatoFecha.format(rs.getDate("inicio_incapacidad")));
                table.addCell(formatoFecha.format(rs.getDate("fin_incapacidad")));
                table.addCell(rs.getString("total_dias"));
                table.addCell(rs.getString("tipo_incapacidad"));
                table.addCell(rs.getString("riesgo_incapacidad"));
            }

            document.add(table);
            document.close();

            JOptionPane.showMessageDialog(null,
                    "Reporte generado correctamente:\n" + fileToSave.getAbsolutePath(),
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            // abrir en edge
            try {
                // Ruta absoluta del archivo PDF ya guardado
                String rutaPDF = fileToSave.getAbsolutePath();

                // Comando para ejecutar Microsoft Edge
                String comando = "cmd /c start msedge \"" + rutaPDF + "\"";

                Runtime.getRuntime().exec(comando);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, 
                    "El archivo se generó, pero no se pudo abrir automáticamente en Edge.\n" + ex.getMessage(),
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
                );
            }


        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } finally {
            conex.desconectar(null);
        }
    }
}
