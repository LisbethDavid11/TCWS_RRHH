package reportes;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

import javax.swing.JTable;
import javax.swing.JOptionPane;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

public class reporte_impuntualidades {

    private static final float SIZE_TITULO  = 14f;
    private static final float SIZE_CUERPO  = 12f;
    private static final float SIZE_ROTULOS = 11f;

    public void generarReporteNarrativo(JTable tabla, int filaModelo, String nombre, String apellido) {
        try {
            String safeNombre   = (nombre   == null ? "" : nombre).trim().replace(" ", "_");
            String safeApellido = (apellido == null ? "" : apellido).trim().replace(" ", "_");
            String nombreArchivo = "Impuntualidad_" + safeNombre + "_" + safeApellido + "_" + LocalDate.now() + ".pdf";

            File fileToSave = validacion_ruta.obtenerRutaArchivo(nombreArchivo);
            if (fileToSave == null) return;

            PdfWriter writer  = new PdfWriter(new FileOutputStream(fileToSave));
            PdfDocument pdf   = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Encabezado con logo y datos de la institución
            encabezado_documentos encabezado = new encabezado_documentos();
            encabezado.agregarEncabezado(document);

            // Título
            document.add(new Paragraph("Reporte de Impuntualidad")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setFontSize(SIZE_TITULO)
                    .setMarginTop(15)
                    .setMarginBottom(20));

            // === Datos desde la tabla (ajusta índices a tu modelo) ===
            String idDB            = val(tabla, filaModelo, 0);   // Registro No.
            String idMarcada       = val(tabla, filaModelo, 2);   // Id de marcada
            String nombres         = val(tabla, filaModelo, 3);
            String apellidos       = val(tabla, filaModelo, 4);
            String identidad       = val(tabla, filaModelo, 5);
            String telefono        = val(tabla, filaModelo, 6);
            String correo          = val(tabla, filaModelo, 7);
            String cargo           = val(tabla, filaModelo, 8);
            String area            = val(tabla, filaModelo, 9);
            String sexo            = val(tabla, filaModelo, 10);
            String edad            = val(tabla, filaModelo, 11);
            String horaEntrada     = val(tabla, filaModelo, 12);
            String horaImpuntual   = val(tabla, filaModelo, 13);
            String tiempoInjust    = val(tabla, filaModelo, 14);
            String fechaImpuntual  = val(tabla, filaModelo, 15);
            String fechaActual     = val(tabla, filaModelo, 16);
            String horaActual      = val(tabla, filaModelo, 17);
            String motivo          = val(tabla, filaModelo, 18);
            if (motivo.isEmpty()) motivo = "Sin motivo";

            // === CUERPO EXACTO con variables ===
            String cuerpo =
                "Por este medio se deja constancia de una impuntualidad atribuida al colaborador " +
                enBlanco(nombres, "N/D") + " " + enBlanco(apellidos, "N/D") + ", identificado con el Id de marcada No. " +
                enBlanco(idMarcada, "N/D") + ", quien desempeña funciones en el área de " +
                enBlanco(area, "N/D") + " dentro del cargo de " + enBlanco(cargo, "N/D") + ". " +
                "En este sentido, se detallan sus datos generales: identidad No. " + enBlanco(identidad, "N/D") +
                ", género " + enBlanco(sexo, "N/D").toLowerCase() +
                ", con una edad de " + (edad.isEmpty() ? "N/D" : edad + " años") + ". " +
                "Su teléfono de contacto es " + enBlanco(telefono, "N/D") +
                " y correo electrónico " + enBlanco(correo, "N/D") + ". " +
                "\n\nDe acuerdo con el registro No. " + enBlanco(idDB, "N/D") +
                ", la impuntualidad se produjo el día " + enBlanco(fechaImpuntual, "N/D") + ". " +
                "La hora de ingreso establecida era " + hhmm(horaEntrada) +
                ", registrándose la impuntualidad a las " + hhmm(horaImpuntual) +
                ", acumulando un tiempo  injustificado total de " + hhmm(tiempoInjust) +
                " horas, el motivo declarado es: " + enBlanco(motivo, "N/D") + ".";

            document.add(new Paragraph(cuerpo)
                    .setFontSize(SIZE_CUERPO)
                    .setTextAlignment(TextAlignment.JUSTIFIED)
                    .setMarginBottom(20));

            document.add(new Paragraph("\n\n\n\n_________________________________              ______________________________")
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("		Firma del colaborador                                          Firma RR.HH")
                    .setFontSize(SIZE_ROTULOS)
                    .setTextAlignment(TextAlignment.CENTER));

            // Cerrar y abrir
            document.close();
            JOptionPane.showMessageDialog(null, "El reporte de impuntualidad se ha guardado correctamente");
            Desktop.getDesktop().open(fileToSave);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el PDF: " + e.getMessage());
        }
    }

    // ===== Helpers =====
    private String val(JTable t, int row, int col) {
        Object v = t.getModel().getValueAt(row, col);
        return (v == null) ? "" : v.toString().trim();
    }

    private String enBlanco(String s, String fallback) {
        return (s == null || s.trim().isEmpty()) ? fallback : s.trim();
    }

    // Convierte "HH:mm:ss" -> "HH:mm" (o deja como esté si ya viene "HH:mm")
    private String hhmm(String t) {
        if (t == null || t.trim().isEmpty()) return "N/D";
        String s = t.trim();
        // Si viene HH:mm:ss, nos quedamos con HH:mm
        if (s.matches("^\\d{2}:\\d{2}:\\d{2}$")) return s.substring(0, 5);
        return s; // ya es HH:mm o cualquier otro texto
    }
}
