package reportes;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

import java.awt.Desktop;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class reporte_vacaciones_individual {

    public void generarReporte(String nombreEmpleado, String apellidosEmpleado, String identidadEmpleado,
                               String cargoEmpleado, String areaEmpleado, int diasCorrespondientes,
                               String fechaInicio, String fechaFinalizacion, int totalDias, int diasPendientes,
                               String pagadas, String nombreExtiende, String cargoExtiende) {

        try {
            // Fecha actual
            LocalDate fechaActual = LocalDate.now();
            String diaActual = String.valueOf(fechaActual.getDayOfMonth());
            String mesActual = fechaActual.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
            String añoActual = String.valueOf(fechaActual.getYear());

            // Nombre del archivo
            String nombreArchivo = "Vacaciones_" + nombreEmpleado + "_" + apellidosEmpleado.replaceAll("\\s+", "_") + "_" +
                    fechaActual.format(DateTimeFormatter.ofPattern("dd-MM-yy")) + ".pdf";

            // Guardar ubicación con JFileChooser
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File(nombreArchivo));
            int seleccion = fileChooser.showSaveDialog(null);

            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File archivo = fileChooser.getSelectedFile();

                // Validar si el archivo existe
                if (archivo.exists()) {
                    int sobreescribir = JOptionPane.showConfirmDialog(null,
                            "El archivo ya existe. ¿Desea sobrescribirlo?", "Archivo existente",
                            JOptionPane.YES_NO_CANCEL_OPTION);
                    if (sobreescribir != JOptionPane.YES_OPTION) {
                        return; 
                    }
                }

                PdfWriter writer = new PdfWriter(archivo.getAbsolutePath());
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf);

                encabezado_documentos encabezado = new encabezado_documentos();
                encabezado.agregarEncabezado(document);

                // Título
                document.add(new Paragraph("Constancia de Autorización de Vacaciones")
                        .setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER));
                document.add(new Paragraph("\n"));

                // Cuerpo del documento
                String textoCuerpo = "Yo " + nombreExtiende + ", como el suscrito " + cargoExtiende
                        + " del Instituto Cristiano Bilingüe \"El Mundo de los Niños\", le autorizo vacaciones al empleado(a): "
                        + nombreEmpleado + " " + apellidosEmpleado + ", con número de identidad "
                        + identidadEmpleado + ", quien labora bajo el cargo de " + cargoEmpleado
                        + " en el área de " + areaEmpleado + ". A quien corresponden "
                        + diasCorrespondientes + " días por este año, iniciando en la fecha de " + fechaInicio
                        + " y finalizando el " + fechaFinalizacion + ", tomando un total de " + totalDias
                        + " días, con " + diasPendientes + " días pendientes para su próximo registro de vacaciones, "
                        + "por lo que las vacaciones " + pagadas + " fueron pagadas.\n\n"
                        + "En constancia de lo anterior, se firma esta autorización a los " + diaActual
                        + " días del mes de " + mesActual + " del año " + añoActual + ".\n\n\n\n"
                        + "Recibido por: " + nombreExtiende + "\nCargo: " + cargoExtiende;

                document.add(new Paragraph(textoCuerpo).setFontSize(12).setTextAlignment(TextAlignment.LEFT));

                // Cerrar el documento
                document.close();

                // Confirmación y apertura
                JOptionPane.showMessageDialog(null, "PDF generado correctamente: \n" + archivo.getAbsolutePath(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
                Desktop.getDesktop().open(archivo);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al generar el PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


