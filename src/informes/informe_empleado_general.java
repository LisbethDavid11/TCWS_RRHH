package informes;

import java.awt.Desktop;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import conexion.conexion;
import reportes.encabezado_documentos;

public class informe_empleado_general {

    public void generarInformeGeneral() {
        try (Connection conn = new conexion().conectar()) {
            String query = "SELECT e.nombres_empleado, e.apellidos_empleado, e.identidad_empleado, e.cargo_empleado, e.area_empleado, " +
                           "(SELECT COUNT(*) FROM permisos_ausencia_laboral p WHERE p.nombres_empleado = e.nombres_empleado AND p.apellidos_empleado = e.apellidos_empleado) AS total_permisos, " +
                           "(SELECT COUNT(*) FROM incapacidad_laboral i WHERE i.nombres_empleado = e.nombres_empleado AND i.apellidos_empleado = e.apellidos_empleado) AS total_incapacidades " +
                           "FROM empleados e";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            // Generar el PDF
            String fechaActual = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            String nombreArchivo = "Informe_General_Empleados_" + fechaActual + ".pdf";
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File(nombreArchivo));
            int seleccion = fileChooser.showSaveDialog(null);
            if (seleccion != JFileChooser.APPROVE_OPTION) return;

            File archivo = fileChooser.getSelectedFile();
            if (archivo.exists()) {
                int confirmacion = JOptionPane.showConfirmDialog(null, "El archivo ya existe. ¿Desea sobreescribirlo?", "Sobreescribir archivo", JOptionPane.YES_NO_OPTION);
                if (confirmacion != JOptionPane.YES_OPTION) return;
            }

            PdfWriter writer = new PdfWriter(archivo.getAbsolutePath());
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Agregar encabezado
            new encabezado_documentos().agregarEncabezado(document);

            // Título del informe
            document.add(new Paragraph("Informe General de Empleados").setFontSize(16).setBold().setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("\n"));

            // Crear tabla
            Table table = new Table(6);
            table.setWidth(520);
            table.setBorder(new SolidBorder(ColorConstants.BLACK, 1));

            // Agregar encabezados de la tabla
            table.addHeaderCell(new Cell().add(new Paragraph("Nombre").setBold().setTextAlignment(TextAlignment.CENTER)));
            table.addHeaderCell(new Cell().add(new Paragraph("Identidad").setBold().setTextAlignment(TextAlignment.CENTER)));
            table.addHeaderCell(new Cell().add(new Paragraph("Cargo").setBold().setTextAlignment(TextAlignment.CENTER)));
            table.addHeaderCell(new Cell().add(new Paragraph("Área").setBold().setTextAlignment(TextAlignment.CENTER)));
            table.addHeaderCell(new Cell().add(new Paragraph("No. Permisos").setBold().setTextAlignment(TextAlignment.CENTER)));
            table.addHeaderCell(new Cell().add(new Paragraph("No. Incapacidades").setBold().setTextAlignment(TextAlignment.CENTER)));

            //agregar datos de empleados
            while (rs.next()) {
                String nombres = rs.getString("nombres_empleado");
                String apellidos = rs.getString("apellidos_empleado");
                String identidad = rs.getString("identidad_empleado");
                String cargo = rs.getString("cargo_empleado");
                String area = rs.getString("area_empleado");
                int totalPermisos = rs.getInt("total_permisos");
                int totalIncapacidades = rs.getInt("total_incapacidades");

                table.addCell(new Cell().add(new Paragraph(nombres + " " + apellidos).setTextAlignment(TextAlignment.LEFT)));
                table.addCell(new Cell().add(new Paragraph(identidad).setTextAlignment(TextAlignment.CENTER)));
                table.addCell(new Cell().add(new Paragraph(cargo).setTextAlignment(TextAlignment.CENTER)));
                table.addCell(new Cell().add(new Paragraph(area).setTextAlignment(TextAlignment.CENTER)));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(totalPermisos)).setTextAlignment(TextAlignment.CENTER)));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(totalIncapacidades)).setTextAlignment(TextAlignment.CENTER)));
            }

            document.add(table);

            document.close();

            Desktop.getDesktop().open(archivo);

            JOptionPane.showMessageDialog(null, "El informe general se ha generado correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Ocurrió un error al generar el informe: " + e.getMessage());
        }
    }
}

