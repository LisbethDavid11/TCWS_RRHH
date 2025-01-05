package reportes;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.TextAlignment;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import conexion.conexion;

public class reporte_empleados_general {

    public void generarReporteEmpleadosCompleto() {
        conexion conex = new conexion();

        try {
            LocalDate fechaActual = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String fechaFormateada = fechaActual.format(formatter);
            String nombreArchivo = "Reporte_general_empleados_" + fechaFormateada + ".pdf";

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar reporte general de empleados");

            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PDF", "pdf");
            fileChooser.setFileFilter(filter);

            fileChooser.setSelectedFile(new File(nombreArchivo));

            boolean archivoValido = false;
            File fileToSave = null;

            while (!archivoValido) {
                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    fileToSave = fileChooser.getSelectedFile();
                    String dest = fileToSave.getAbsolutePath();

                    if (!dest.toLowerCase().endsWith(".pdf")) {
                        dest += ".pdf";
                        fileToSave = new File(dest);
                    }

                    if (fileToSave.exists()) {
                        JOptionPane.showMessageDialog(null,
                            "El archivo ya existe. Por favor, elige un nombre diferente",
                            "Archivo existente",
                            JOptionPane.WARNING_MESSAGE);
                    } else {
                        archivoValido = true;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Generación de reporte cancelada" , "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            PdfWriter writer = new PdfWriter(fileToSave.getAbsolutePath());
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.LEGAL.rotate());

            encabezado_documentos encabezado = new encabezado_documentos();
            encabezado.agregarEncabezado(document);

            document.add(new Paragraph("Reporte completo de empleados")
                .setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("\n"));

            float[] columnWidths = {0.3f, 0.5f, 1.5f, 1.5f, 1.5f, 1f, 1f, 1f, 1f, 1.3f, 1.3f, 1f, 1.1f, 1.1f};
            Table table = new Table(UnitValue.createPercentArray(columnWidths)).useAllAvailableWidth();

            table.addHeaderCell(new Cell().add(new Paragraph("No.").setBold().setFontSize(10)));
            table.addHeaderCell(new Cell().add(new Paragraph("ID").setBold().setFontSize(10)));
            table.addHeaderCell(new Cell().add(new Paragraph("Identidad").setBold().setFontSize(10)));
            table.addHeaderCell(new Cell().add(new Paragraph("Nombres").setBold().setFontSize(10)));
            table.addHeaderCell(new Cell().add(new Paragraph("Apellidos").setBold().setFontSize(10)));
            table.addHeaderCell(new Cell().add(new Paragraph("Fecha de nacimiento").setBold().setFontSize(10)));
            table.addHeaderCell(new Cell().add(new Paragraph("Sexo").setBold().setFontSize(10)));
            table.addHeaderCell(new Cell().add(new Paragraph("Estado civil").setBold().setFontSize(10)));
            table.addHeaderCell(new Cell().add(new Paragraph("Teléfono").setBold().setFontSize(10)));
            table.addHeaderCell(new Cell().add(new Paragraph("Cargo").setBold().setFontSize(10)));
            table.addHeaderCell(new Cell().add(new Paragraph("Área").setBold().setFontSize(10)));
            table.addHeaderCell(new Cell().add(new Paragraph("Fecha de inicio").setBold().setFontSize(10)));
            table.addHeaderCell(new Cell().add(new Paragraph("Fecha de renuncia").setBold().setFontSize(10)));
            table.addHeaderCell(new Cell().add(new Paragraph("Fotografía").setBold().setFontSize(10)));

            Statement estatuto = conex.conectar().createStatement();
            ResultSet rs = estatuto.executeQuery("SELECT * FROM empleados");

            int contador = 1;

            while (rs.next()) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(contador)).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(rs.getString("id_empleado")).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(rs.getString("identidad_empleado")).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(rs.getString("nombres_empleado")).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(rs.getString("apellidos_empleado")).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(rs.getString("nacimiento_empleado")).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(rs.getString("sexo_empleado")).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(rs.getString("civil_empleado")).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(rs.getString("tel_empleado")).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(rs.getString("cargo_empleado")).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(rs.getString("area_empleado")).setFontSize(9)));
                table.addCell(new Cell().add(new Paragraph(rs.getString("inicio_empleado")).setFontSize(9)));

                // Fecha de renuncia (puede estar vacía)
             // Fecha de renuncia (puede estar vacía)
                String fechaRenuncia = rs.getString("renuncia_empleado");
                if (fechaRenuncia != null && !fechaRenuncia.isEmpty()) {
                    table.addCell(new Cell().add(new Paragraph(fechaRenuncia).setFontSize(9)));
                } else {
                    table.addCell(new Cell().add(new Paragraph("Activo(a)").setFontSize(9)));
                }


                // Fotografía del empleado
                String rutaFoto = rs.getString("fotografia_empleado");
                if (rutaFoto != null && !rutaFoto.isEmpty()) {
                    try {
                        ImageData imageData = ImageDataFactory.create(rutaFoto);
                        Image image = new Image(imageData);
                        image.setMaxWidth(30);
                        image.setMaxHeight(30);
                        table.addCell(new Cell().add(image).setPadding(2));
                    } catch (Exception ex) {
                        table.addCell(new Cell().add(new Paragraph("N/A").setFontSize(9)));
                    }
                } else {
                    table.addCell(new Cell().add(new Paragraph("N/A").setFontSize(9)));
                }

                contador++;
            }

            document.add(table);
            document.close();

            JOptionPane.showMessageDialog(null, "Reporte guardado con éxito en:\n" + fileToSave.getAbsolutePath(), "Éxito",
                JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al guardar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

        } finally {
            conex.desconectar(null);
        }
    }
}
