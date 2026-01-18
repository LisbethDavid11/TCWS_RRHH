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

public class reporte_ausencias_hora_mensual {

    public void generarReportePermisosPorHora(int mes) {

        conexion conex = new conexion();

        try {
            Statement estatuto = conex.conectar().createStatement();

            // 🔹 Consulta por mes usando fecha_recibe
            String query = "SELECT * FROM permisos_ausencia_hora "
                         + "WHERE MONTH(fecha_recibe) = " + mes + " "
                         + "ORDER BY fecha_recibe ASC";

            ResultSet rs = estatuto.executeQuery(query);

            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null,
                        "No hay permisos por hora registrados en ese mes.",
                        "Sin datos",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Fecha actual
            LocalDate hoy = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String fechaActual = hoy.format(formatter);

            String nombreArchivo = "Reporte_Ausencias_Por_Hora_Mensual_" + fechaActual + ".pdf";

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar Reporte Mensual por Hora");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos PDF", "pdf"));
            fileChooser.setSelectedFile(new File(nombreArchivo));

            File fileToSave;
            int seleccion = fileChooser.showSaveDialog(null);

            if (seleccion != JFileChooser.APPROVE_OPTION) return;

            fileToSave = fileChooser.getSelectedFile();
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

            document.add(new Paragraph("Reporte Mensual de Permisos por Hora")
                    .setBold()
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.CENTER));

            document.add(new Paragraph("\n"));

            // 🔹 Tabla
            float[] columnWidths = {
                    0.5f, 0.8f, 1.5f, 1.5f, 1.5f, 1.2f,
                    1.2f, 1.2f, 1.2f, 1.2f, 1.2f, 1.5f
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
            table.addHeaderCell("Desde Hora");
            table.addHeaderCell("Hasta Hora");
            table.addHeaderCell("Total Horas");
            table.addHeaderCell("Motivo");

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
                table.addCell(rs.getString("desde_hora"));
                table.addCell(rs.getString("hasta_hora"));
                table.addCell(rs.getString("total_horas"));
                table.addCell(rs.getString("motivo_ausencia"));
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
