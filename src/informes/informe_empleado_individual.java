package informes;

import java.awt.Desktop;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
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

public class informe_empleado_individual {

    public void generarInformeIndividual() {
        try (Connection conn = new conexion().conectar()) {
            // Obtener nombres de empleados desde la base de datos
            JComboBox<String> comboBoxNombres = new JComboBox<>();
            PreparedStatement stmt = conn.prepareStatement("SELECT CONCAT(nombres_empleado, ' ', apellidos_empleado) AS nombre_completo FROM empleados");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                comboBoxNombres.addItem(rs.getString("nombre_completo"));
            }
            rs.close();

            // Mostrar lista desplegable
            int opcion = JOptionPane.showConfirmDialog(null, comboBoxNombres, "Seleccione un empleado", JOptionPane.OK_CANCEL_OPTION);
            if (opcion != JOptionPane.OK_OPTION) return;

            // Obtener el nombre seleccionado
            String nombreSeleccionado = (String) comboBoxNombres.getSelectedItem();
            if (nombreSeleccionado == null) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un empleado.");
                return;
            }

            // Obtener detalles del empleado y conteo de registros
            String queryInfoEmpleado = "SELECT nombres_empleado, apellidos_empleado, identidad_empleado, cargo_empleado, area_empleado " +
                                       "FROM empleados WHERE CONCAT(nombres_empleado, ' ', apellidos_empleado) = ?";
            PreparedStatement stmtInfo = conn.prepareStatement(queryInfoEmpleado);
            stmtInfo.setString(1, nombreSeleccionado);
            ResultSet rsInfo = stmtInfo.executeQuery();

            if (!rsInfo.next()) {
                JOptionPane.showMessageDialog(null, "No se encontraron datos del empleado seleccionado.");
                return;
            }

            String nombres = rsInfo.getString("nombres_empleado");
            String apellidos = rsInfo.getString("apellidos_empleado");
            String identidad = rsInfo.getString("identidad_empleado");
            String cargo = rsInfo.getString("cargo_empleado");
            String area = rsInfo.getString("area_empleado");
            rsInfo.close();


            // Conteo de registros en tablas
            int registrosPermisos = contarRegistros(conn, "permisos_ausencia_laboral", nombres, apellidos);
            int registrosIncapacidades = contarRegistros(conn, "incapacidad_laboral", nombres, apellidos);

            // Generar el PDF
            String fechaActual = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            String nombreArchivo = "Informe_" + nombres + "_" + apellidos + "_" + fechaActual + ".pdf";
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

            // Agregar información del empleado
            document.add(new Paragraph("Informe Individual de Empleado \n\n").setFontSize(16).setBold().setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Nombre completo: " + nombres + " " + apellidos));
            document.add(new Paragraph("Identidad No. " + identidad));
            document.add(new Paragraph("Cargo: " + cargo));
            document.add(new Paragraph("Área: " + area));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            // Tabla con conteo de registros
            Table table = new Table(2);
            table.setBorder(new SolidBorder(ColorConstants.BLACK, 1));

            Cell header1 = new Cell().add(new Paragraph("Registros autorizados").setBold().setTextAlignment(TextAlignment.CENTER));
            Cell header2 = new Cell().add(new Paragraph("Cantidad").setBold().setTextAlignment(TextAlignment.CENTER));

            table.addHeaderCell(header1);
            table.addHeaderCell(header2);

            Cell permisosCell = new Cell().add(new Paragraph("Permisos por ausencia laboral").setTextAlignment(TextAlignment.LEFT));
            Cell permisosCount = new Cell().add(new Paragraph(String.valueOf(registrosPermisos)).setTextAlignment(TextAlignment.CENTER));

            Cell incapacidadesCell = new Cell().add(new Paragraph("Incapacidades laborales").setTextAlignment(TextAlignment.LEFT));
            Cell incapacidadesCount = new Cell().add(new Paragraph(String.valueOf(registrosIncapacidades)).setTextAlignment(TextAlignment.CENTER));

            table.addCell(permisosCell);
            table.addCell(permisosCount);
            table.addCell(incapacidadesCell);
            table.addCell(incapacidadesCount);

            document.add(table);

            document.close();

            // Abrir PDF en Microsoft Edge
            Desktop.getDesktop().open(archivo);

            JOptionPane.showMessageDialog(null, "El informe se ha generado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrió un error al generar el informe: " + e.getMessage());
        }
    }

    private int contarRegistros(Connection conn, String tabla, String nombres, String apellidos) throws Exception {
        String query = "SELECT COUNT(*) FROM " + tabla + " WHERE nombres_empleado = ? AND apellidos_empleado = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, nombres);
        stmt.setString(2, apellidos);
        ResultSet rs = stmt.executeQuery();
        int count = rs.next() ? rs.getInt(1) : 0;
        rs.close();
        return count;
    }

    
}
