package reportes;

import java.awt.Desktop;
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

public class reporte_ausencia_hora_mensual {

    public void generarReporteHorasMensual() {

        conexion conex = new conexion();

        // ==============================
        //   SELECCIÓN DE MES
        // ==============================

        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", 
                          "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        JComboBox<String> comboMes = new JComboBox<>(meses);

        int opcion = JOptionPane.showConfirmDialog(
                null, comboMes, "Seleccione el mes del reporte",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (opcion != JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(null, "Reporte cancelado");
            return;
        }

        int mesSeleccionado = comboMes.getSelectedIndex() + 1; // Enero = 1

        try {
            Statement estatuto = conex.conectar().createStatement();

            // ==============================
            //     CONSULTA POR MES
            // ==============================
            String query =
                "SELECT * FROM permisos_ausencia_hora " +
                "WHERE MONTH(fecha_recibe) = " + mesSeleccionado;

            ResultSet rs = estatuto.executeQuery(query);

            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null,
                    "No hay registros del mes seleccionado.",
                    "Sin registros",
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Fecha actual
            LocalDate ahora = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String fechaFormateada = ahora.format(formatter);

            String nombreArchivo = 
                "Reporte_Permisos_Ausencia_Hora_Mensual_" + meses[mesSeleccionado - 1] + "_" + fechaFormateada + ".pdf";

            // ==============================
            //    SELECCIONAR GUARDADO
            // ==============================

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar reporte mensual");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PDF", "pdf");
            fileChooser.setFileFilter(filter);
            fileChooser.setSelectedFile(new File(nombreArchivo));

            File archivo = null;
            boolean valido = false;

            while (!valido) {
                int seleccion = fileChooser.showSaveDialog(null);

                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    archivo = fileChooser.getSelectedFile();
                    String path = archivo.getAbsolutePath();

                    if (!path.toLowerCase().endsWith(".pdf")) {
                        archivo = new File(path + ".pdf");
                    }

                    if (archivo.exists()) {
                        int overwrite = JOptionPane.showConfirmDialog(null,
                                "El archivo ya existe. ¿Desea sobrescribirlo?",
                                "Confirmar",
                                JOptionPane.YES_NO_CANCEL_OPTION);

                        if (overwrite == JOptionPane.YES_OPTION) {
                            valido = true;
                        } else if (overwrite == JOptionPane.NO_OPTION) {
                            continue;
                        } else {
                            return;
                        }
                    } else {
                        valido = true;
                    }
                } else {
                    return;
                }
            }

            // ==============================
            //        CREAR PDF
            // ==============================

            PdfWriter writer = new PdfWriter(archivo.getAbsolutePath());
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4.rotate());

            // ENCABEZADO
            encabezado_documentos encabezado = new encabezado_documentos();
            encabezado.agregarEncabezado(document);

            // TÍTULO
            document.add(new Paragraph(
                    "Reporte Mensual de Permisos por Ausencia - Horas\nMes: " + meses[mesSeleccionado - 1])
                    .setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER));

            document.add(new Paragraph("\n"));

            // ==============================
            //      TABLA DE DATOS
            // ==============================

            float[] columnas = {0.7f, 1.3f, 1.3f, 1.6f, 1f, 1f, 1f, 1.3f, 1.3f};
            Table table = new Table(UnitValue.createPercentArray(columnas));
            table.setWidth(UnitValue.createPercentValue(100));

            // ENCABEZADOS
            table.addHeaderCell(new Cell().add(new Paragraph("No")).setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Cell().add(new Paragraph("Nombres")).setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Cell().add(new Paragraph("Apellidos")).setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Cell().add(new Paragraph("Motivo")).setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Cell().add(new Paragraph("Desde Hora")).setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Cell().add(new Paragraph("Hasta Hora")).setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Cell().add(new Paragraph("Total Horas")).setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Cell().add(new Paragraph("Recibe")).setTextAlignment(TextAlignment.CENTER));
            table.addHeaderCell(new Cell().add(new Paragraph("Fecha Recibe")).setTextAlignment(TextAlignment.CENTER));

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            while (rs.next()) {
                table.addCell(String.valueOf(rs.getInt("id_permisos")));
                table.addCell(rs.getString("nombres_empleado"));
                table.addCell(rs.getString("apellidos_empleado"));
                table.addCell(rs.getString("motivo_ausencia"));
                table.addCell(rs.getString("desdeHora"));
                table.addCell(rs.getString("hastaHora"));
                table.addCell(rs.getString("total_horas"));
                table.addCell(rs.getString("nombres_recibe"));
                table.addCell(dateFormat.format(rs.getDate("fecha_recibe")));
            }

            document.add(table);
            document.close();

            JOptionPane.showMessageDialog(null,
                "Reporte generado correctamente:\n" + archivo.getAbsolutePath());

            // ==============================
            //     ABRIR PDF EN EDGE
            // ==============================

            try {
                Desktop.getDesktop().browse(archivo.toURI());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                    "No se pudo abrir automáticamente el PDF.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                "Error al generar el reporte: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            conex.desconectar(null);
        }
    }
}
