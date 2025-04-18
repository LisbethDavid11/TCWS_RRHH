package reportes;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.TextAlignment;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import conexion.conexion;

public class reporte_incapacidad_general {

    public void generarReporteIncapacidades() {
        conexion conex = new conexion();

        try {
            // Consulta para obtener todos los registros de incapacidades junto con los datos del empleado
            Statement estatuto = conex.conectar().createStatement();
            String query = "SELECT * FROM incapacidad_laboral";
            ResultSet rs = estatuto.executeQuery(query);

            // Si no hay registros, mostrar un mensaje
            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "No hay registros de incapacidades.", "Sin registros", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Obtener la fecha actual formateada
            LocalDate fechaActual = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String fechaFormateada = fechaActual.format(formatter);

            // Nombre por defecto del archivo PDF
            String nombreArchivo = "Reporte_Incapacidades_" + fechaFormateada + ".pdf";

            // Mostrar JFileChooser para seleccionar dónde guardar el PDF
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar reporte de incapacidades");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PDF", "pdf");
            fileChooser.setFileFilter(filter);
            fileChooser.setSelectedFile(new File(nombreArchivo));

            boolean archivoValido = false;
            File fileToSave = null;

            // Validar si el archivo ya existe y solicitar un nombre válido
            while (!archivoValido) {
                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    fileToSave = fileChooser.getSelectedFile();
                    String dest = fileToSave.getAbsolutePath();

                    // Asegurarse de que la extensión sea .pdf
                    if (!dest.toLowerCase().endsWith(".pdf")) {
                        dest += ".pdf";
                        fileToSave = new File(dest);
                    }

                    // Verificar si el archivo ya existe
                    if (fileToSave.exists()) {
                        int result = JOptionPane.showConfirmDialog(null,
                                "El archivo ya existe. ¿Desea sobrescribirlo?", "Archivo existente",
                                JOptionPane.YES_NO_CANCEL_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            archivoValido = true;  // Sobrescribir archivo
                        } else if (result == JOptionPane.NO_OPTION) {
                            continue; // Permitir al usuarioC elegir un nuevo nombre
                        } else {
                            JOptionPane.showMessageDialog(null, "Generación de reporte cancelada", "Cancelado", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    } else {
                        archivoValido = true;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Generación de reporte cancelada", "Cancelado", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            // Crear el documento PDF
            PdfWriter writer = new PdfWriter(fileToSave.getAbsolutePath());
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4.rotate()); // Documento en modo horizontal para más espacio

            // Agregar encabezado
            encabezado_documentos encabezado = new encabezado_documentos();
            encabezado.agregarEncabezado(document);

            // Agregar título
            document.add(new Paragraph("Reporte general de Incapacidades")
                    .setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("\n"));

            // Crear la tabla con los encabezados de los datos de empleado y la incapacidad
            float[] columnWidths = {0.4f, 0.4f, 1f, 1f, 1f, 0.7f, 1f, 1f, 1f, 1f, 1.5f, 0.7f, 0.7f}; // Ajustar tamaños de las columnas
            Table table = new Table(UnitValue.createPercentArray(columnWidths));
            table.setWidth(UnitValue.createPercentValue(100)); // La tabla ocupará el ancho completo del documento

            // Encabezados de la tabla
            table.addHeaderCell(new Cell().add(new Paragraph("No")).setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Cell().add(new Paragraph("Id")).setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Cell().add(new Paragraph("Nombres")).setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Cell().add(new Paragraph("Apellidos")).setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Cell().add(new Paragraph("Identidad")).setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Cell().add(new Paragraph("Teléfono")).setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Cell().add(new Paragraph("Correo")).setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Cell().add(new Paragraph("Cargo")).setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Cell().add(new Paragraph("Área")).setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Cell().add(new Paragraph("Riesgo")).setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Cell().add(new Paragraph("Motivo")).setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Cell().add(new Paragraph("Desde")).setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Cell().add(new Paragraph("Hasta")).setTextAlignment(TextAlignment.CENTER));

            // Llenar la tabla con los datos de las incapacidades y empleados
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            while (rs.next()) {
                // Datos del empleado
                table.addCell(new Cell().add(new Paragraph(String.valueOf(rs.getInt("id_incapacidad")))).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(rs.getInt("id_empleado")))).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Cell().add(new Paragraph(rs.getString("nombres_empleado"))).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Cell().add(new Paragraph(rs.getString("apellidos_empleado"))).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Cell().add(new Paragraph(rs.getString("identidad_empleado"))).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Cell().add(new Paragraph(rs.getString("tel_empleado"))).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Cell().add(new Paragraph(rs.getString("correo_empleado"))).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Cell().add(new Paragraph(rs.getString("cargo_empleado"))).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Cell().add(new Paragraph(rs.getString("area_empleado"))).setTextAlignment(TextAlignment.LEFT));

                // Datos de la incapacidad
                table.addCell(new Cell().add(new Paragraph(rs.getString("riesgo_incapacidad"))).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Cell().add(new Paragraph(rs.getString("tipo_incapacidad"))).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Cell().add(new Paragraph(dateFormat.format(rs.getDate("inicio_incapacidad")))).setTextAlignment(TextAlignment.LEFT));
                table.addCell(new Cell().add(new Paragraph(dateFormat.format(rs.getDate("fin_incapacidad")))).setTextAlignment(TextAlignment.LEFT));
            }

            // Agregar la tabla al documento
            document.add(table);

            // Cerrar el documento
            document.close();
            JOptionPane.showMessageDialog(null, "Reporte generado correctamente en:\n" + fileToSave.getAbsolutePath(), "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            conex.desconectar(null);
        }
    }
}

