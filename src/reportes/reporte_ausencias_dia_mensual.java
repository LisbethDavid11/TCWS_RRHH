package reportes;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import conexion.conexion;

public class reporte_ausencias_dia_mensual {

    public void generarReportePermisos(int mes) {
        conexion conex = new conexion();

        try {
            Statement estatuto = conex.conectar().createStatement();

            // Consulta filtrando por mes en la columna desde_fecha
            String query = "SELECT * FROM permisos_ausencia_laboral "
                         + "WHERE MONTH(desde_fecha) = " + mes + ";";

            ResultSet rs = estatuto.executeQuery(query);

            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null,
                    "No hay registros para ese mes.",
                    "Sin datos",
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Fecha actual para el nombre del archivo
            LocalDate hoy = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String fechaActual = hoy.format(formatter);

            String nombreArchivo = "Reporte_Ausencias_Mensual_" + fechaActual + ".pdf";

            // Guardar PDF
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar Reporte Mensual");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PDF", "pdf");
            fileChooser.setFileFilter(filter);
            fileChooser.setSelectedFile(new File(nombreArchivo));

            File fileToSave = null;
            boolean archivoValido = false;

            while (!archivoValido) {
                int seleccion = fileChooser.showSaveDialog(null);

                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    fileToSave = fileChooser.getSelectedFile();
                    String dest = fileToSave.getAbsolutePath();

                    if (!dest.toLowerCase().endsWith(".pdf")) {
                        dest += ".pdf";
                        fileToSave = new File(dest);
                    }

                    if (fileToSave.exists()) {
                        int result = JOptionPane.showConfirmDialog(null,
                                "El archivo ya existe, ¿desea sobrescribirlo?",
                                "Archivo existente",
                                JOptionPane.YES_NO_CANCEL_OPTION);

                        if (result == JOptionPane.YES_OPTION) {
                            archivoValido = true;
                        } else if (result == JOptionPane.NO_OPTION) {
                            continue;
                        } else {
                            return;
                        }
                    } else {
                        archivoValido = true;
                    }
                } else {
                    return;
                }
            }

            // Crear documento
            PdfWriter writer = new PdfWriter(fileToSave.getAbsolutePath());
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4.rotate());

            // Encabezado
            encabezado_documentos encabezado = new encabezado_documentos();
            encabezado.agregarEncabezado(document);

            document.add(new Paragraph("Reporte Mensual de Permisos por Ausencia Laboral")
                    .setFontSize(14).setBold().setTextAlignment(TextAlignment.CENTER));

            document.add(new Paragraph("\n"));

            // Configurar tabla horizontal
            float[] columnWidths = {
                    0.7f, 0.75f, 1.3f, 1.3f, 1.5f, 1.25f, 1.5f,
                    1f, 1f, 1f, 0.75f, 0.75f, 0.75f
            };

            Table table = new Table(UnitValue.createPercentArray(columnWidths));
            table.setWidth(UnitValue.createPercentValue(100));

            // Encabezados
            table.addHeaderCell("No");
            table.addHeaderCell("Id");
            table.addHeaderCell("Nombres");
            table.addHeaderCell("Apellidos");
            table.addHeaderCell("Identidad");
            table.addHeaderCell("Teléfono");
            table.addHeaderCell("Cargo");
            table.addHeaderCell("Área");
            table.addHeaderCell("Motivo");
            table.addHeaderCell("Desde Fecha");
            table.addHeaderCell("Hasta Fecha");
            table.addHeaderCell("Total Días");
            table.addHeaderCell("Total Horas");

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            while (rs.next()) {

                table.addCell(String.valueOf(rs.getInt("id_permisos")));
                table.addCell(String.valueOf(rs.getInt("id_empleado")));
                table.addCell(rs.getString("nombres_empleado"));
                table.addCell(rs.getString("apellidos_empleado"));
                table.addCell(rs.getString("identidad_empleado"));
                table.addCell(rs.getString("tel_empleado"));
                table.addCell(rs.getString("cargo_empleado"));
                table.addCell(rs.getString("area_empleado"));
                table.addCell(rs.getString("motivo_ausencia"));
                table.addCell(dateFormat.format(rs.getDate("desde_fecha")));
                table.addCell(dateFormat.format(rs.getDate("hasta_fecha")));

                // Calcular días
                java.sql.Date desde = rs.getDate("desde_fecha");
                java.sql.Date hasta = rs.getDate("hasta_fecha");
                long dias = (hasta.getTime() - desde.getTime()) / (1000 * 60 * 60 * 24);

                table.addCell(String.valueOf(dias));
                table.addCell(rs.getString("total_horas"));
            }

            document.add(table);
            document.close();

            JOptionPane.showMessageDialog(null,
                    "Reporte generado:\n" + fileToSave.getAbsolutePath(),
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
