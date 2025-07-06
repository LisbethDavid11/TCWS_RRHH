package reportes;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

import javax.swing.*;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class reporte_permiso_hora {

    public void generarReportePermisoHora(
            String nombreEmpleado, String apellidosEmpleado,
            String nombreExtiende, String cargoExtiende,
            String motivoAusencia, String fechaDesde,
            String totalDias, String totalHoras,
            String nombreRecibe, String cargoRecibe
    ) {
        try {
            // Nombre predeterminado del archivo
            String nombreArchivo = "PermisoHora_" + nombreEmpleado + "_" + apellidosEmpleado + "_" + LocalDate.now() + ".pdf";

            //validacion de la ruta del pdf
            File fileToSave = validacion_ruta.obtenerRutaArchivo(nombreArchivo);
            if (fileToSave == null) return; // Usuario canceló
            
            PdfWriter writer = new PdfWriter(new FileOutputStream(fileToSave));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Encabezado con logo y título
            encabezado_documentos encabezado = new encabezado_documentos();
            encabezado.agregarEncabezado(document);

           

            // Título
            document.add(new Paragraph("Constancia de Permiso de Ausencia por Hora")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setFontSize(14)
                    .setMarginTop(15)
                    .setMarginBottom(20));

            // Redacción del cuerpo
            String cuerpo = "Yo " + nombreExtiende + ", como el suscrito(a) " + cargoExtiende +
                    " del Instituto Cristiano Bilingüe “El Mundo de los Niños”, le autorizo ausentarse del establecimiento al colaborador(a): " +
                    nombreEmpleado + " " + apellidosEmpleado + ", en la fecha de " + fechaDesde +
                    " por un término de " + totalHoras + " hora(s), por motivo de:\n" + motivoAusencia + ".";

            document.add(new Paragraph(cuerpo)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.JUSTIFIED)
                    .setMarginBottom(15));

            // Fecha formal
            LocalDate hoy = LocalDate.now();
            String mesNombre = hoy.getMonth().getDisplayName(java.time.format.TextStyle.FULL, new Locale("es")).toLowerCase();
            String fechaFinal = "En constancia de lo anterior, se firma esta autorización a los " +
                    hoy.getDayOfMonth() + " días del mes de " + mesNombre + " del año " + hoy.getYear() + ".";

            document.add(new Paragraph(fechaFinal)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.JUSTIFIED)
                    .setMarginBottom(40));

            // Firmas
            document.add(new Paragraph("\n\n_________________________________			  ______________________________")
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Firma y sello de autoridad inmediata    					    	  Firma del solicitante")
                    .setFontSize(11)
                    .setTextAlignment(TextAlignment.CENTER));

            // Recibido por
            document.add(new Paragraph("\n\n\n\nRecibido por: " + nombreRecibe)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.LEFT));
            document.add(new Paragraph("Cargo: " + cargoRecibe)
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.LEFT));
            
            document.add(new Paragraph("\n______________________________")
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Firma y sello de recibido")
                    .setFontSize(11)
                    .setTextAlignment(TextAlignment.CENTER));

            document.close();

            JOptionPane.showMessageDialog(null, "El permiso por hora se ha guardado correctamente");
            Desktop.getDesktop().open(fileToSave);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el PDF: " + e.getMessage());
        }
    }
}
