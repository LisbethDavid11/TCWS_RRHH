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

public class reporte_permiso_individual {

    public void generarReporte(String numeroPermiso, String nombreEmpleado, String apellidosEmpleado,
                               String nombreExtiende, String cargoExtiende, String motivoAusencia,
                               String fechaDesde, String totalDias, String totalHoras,
                               String nombreRecibe, String cargoRecibe) {

        try {
            // Fecha actual
            LocalDate fechaActual = LocalDate.now();
            String diaActual = String.valueOf(fechaActual.getDayOfMonth());
            String mesActual = fechaActual.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
            String añoActual = String.valueOf(fechaActual.getYear());

            // Nombre del archivo
            String nombreArchivo = "Permiso_" + nombreEmpleado + "_" + apellidosEmpleado.replaceAll("\\s+", "_") + "_" +
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
                        return; // Cancelar la operación
                    }
                }

                // Generar el PDF
                PdfWriter writer = new PdfWriter(archivo.getAbsolutePath());
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf);
                
                // Número del permiso
                document.add(new Paragraph("Permiso No. " + numeroPermiso)
                        .setFontSize(12).setBold().setTextAlignment(TextAlignment.LEFT));

                // Agregar el encabezado del documento
                encabezado_documentos encabezado = new encabezado_documentos();
                encabezado.agregarEncabezado(document);

     
                // Título
                document.add(new Paragraph("Constancia de Permiso por Ausencia Laboral")
                        .setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER));
                document.add(new Paragraph("\n"));

                // Cuerpo del documento
                String textoCuerpo = "Yo " + nombreExtiende + ", como el suscrito(a) " + cargoExtiende 
                        + " del Instituto Cristiano Bilingüe “El Mundo de los Niños”, le autorizo ausentarse del establecimiento al empleado(a): "
                        + nombreEmpleado + " " + apellidosEmpleado + ", en la fecha de " + fechaDesde 
                        + " por un término de " + totalDias + " días y/o " + totalHoras + " horas, por motivo de: " 
                        + motivoAusencia + ".\n\n"
                        + "En constancia de lo anterior, se firma esta autorización a los " + diaActual 
                        + " días del mes de " + mesActual + " del año " + añoActual + ".\n\n\n\n\n"
                        + "Recibido por: " + nombreRecibe + "\nCargo: " + cargoRecibe;

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
