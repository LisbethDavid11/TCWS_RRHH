package reportes;

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

import javax.swing.JOptionPane;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class reporte_asistencia_diaria {

    /**
     * Genera el PDF con la lista de asistencia por fecha seleccionada.
     * Muestra columnas: Nombres, Apellidos, Cargo, Área, Asistencia (Sí/No).
     * @param fechaStrTabla_ddMMyyyy Fecha proveniente de la tabla (formato dd-MM-yyyy)
     */
    public void generarReporteAsistenciaDiaria(String fechaStrTabla_ddMMyyyy) {
        // 1) Nombre de archivo con nomenclatura solicitada
        String fechaSanitizada = sanitizarParaNombre(fechaStrTabla_ddMMyyyy);
        String nombreArchivo = "asistencia_diaria_" + fechaSanitizada + ".pdf";

        // 2) Diálogo de guardado con tus validaciones previas
        File fileToSave = validacion_ruta.obtenerRutaArchivo(nombreArchivo);
        if (fileToSave == null) return; // Usuario canceló

        // 3) Convertir dd-MM-yyyy -> java.sql.Date
        java.sql.Date fechaSQL;
        try {
            java.util.Date fechaUtil = new SimpleDateFormat("dd-MM-yyyy").parse(fechaStrTabla_ddMMyyyy);
            fechaSQL = new java.sql.Date(fechaUtil.getTime());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo interpretar la fecha seleccionada.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 4) Consultar la BD (LEFT JOIN para no perder registros si falta match en empleados)
        conexion cx = new conexion();
        Connection cn = cx.conectar();

        String sql =
            "SELECT a.id_empleado, " +
            "       e.nombres_empleado, e.apellidos_empleado, " +
            "       e.cargo_empleado, e.area_empleado, a.asistio " +
            "FROM asistencia_diaria a " +
            "LEFT JOIN empleados e ON a.id_empleado = e.id " + // FK definida a empleados(id)
            "WHERE a.fecha = ? " +
            "ORDER BY COALESCE(e.apellidos_empleado, ''), COALESCE(e.nombres_empleado, '')";

        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = cn.prepareStatement(sql);
            pst.setDate(1, fechaSQL);
            rs = pst.executeQuery();

            // 5) Crear PDF horizontal (A4 landscape)
            try (FileOutputStream fos = new FileOutputStream(fileToSave);
                 PdfWriter writer = new PdfWriter(fos);
                 PdfDocument pdf = new PdfDocument(writer);
                 Document document = new Document(pdf, PageSize.A4.rotate())) {

                document.setMargins(36, 36, 36, 36);

                // Encabezado institucional (usa tu clase existente)
                encabezado_documentos encabezado = new encabezado_documentos();
                encabezado.agregarEncabezado(document);

                // Título y subtítulo
                document.add(new Paragraph("REPORTE DE ASISTENCIA DIARIA")
                        .setBold()
                        .setFontSize(16)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginTop(10)
                        .setMarginBottom(6));

                document.add(new Paragraph("Fecha de asistencia: " + fechaStrTabla_ddMMyyyy)
                        .setFontSize(12)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginBottom(12));

                // Tabla principal (Nombres, Apellidos, Cargo, Área, Asistencia)
                Table table = new Table(UnitValue.createPercentArray(new float[]{24, 24, 18, 18, 16}));
                table.setWidth(UnitValue.createPercentValue(100));

                table.addHeaderCell(encabezadoCell("Nombres"));
                table.addHeaderCell(encabezadoCell("Apellidos"));
                table.addHeaderCell(encabezadoCell("Cargo"));
                table.addHeaderCell(encabezadoCell("Área"));
                table.addHeaderCell(encabezadoCell("Asistencia"));

                int total = 0;
                int asistieron = 0;

                while (rs.next()) {
                    int idEmp = rs.getInt("id_empleado");
                    String nombres   = nvl(rs.getString("nombres_empleado"));
                    String apellidos = nvl(rs.getString("apellidos_empleado"));
                    String cargo     = nvl(rs.getString("cargo_empleado"));
                    String area      = nvl(rs.getString("area_empleado"));
                    boolean asistio  = rs.getBoolean("asistio"); // TINYINT(1)/BOOLEAN

                    // Si el LEFT JOIN no encontró datos en empleados, ayuda visual para depurar
                    if (nombres.isEmpty() && apellidos.isEmpty()) {
                        nombres = "ID " + idEmp + " (sin datos en empleados)";
                    }

                    table.addCell(celda(nombres,   TextAlignment.LEFT));
                    table.addCell(celda(apellidos, TextAlignment.LEFT));
                    table.addCell(celda(cargo,     TextAlignment.LEFT));
                    table.addCell(celda(area,      TextAlignment.LEFT));
                    table.addCell(celda(asistio ? "Sí" : "No", TextAlignment.CENTER));

                    total++;
                    if (asistio) asistieron++;
                }

                if (total == 0) {
                    document.add(new Paragraph("No hay registros de asistencia para la fecha seleccionada.")
                            .setFontSize(12)
                            .setTextAlignment(TextAlignment.CENTER)
                            .setMarginTop(20));
                } else {
                    document.add(table);

                    int faltaron = Math.max(0, total - asistieron);
                    double porcentaje = total > 0 ? (asistieron * 100.0 / total) : 0.0;

                    document.add(new Paragraph("\nResumen").setBold().setFontSize(12));
                    document.add(new Paragraph(
                            "Total de empleados: " + total +
                            "    |    Asistieron: " + asistieron +
                            "    |    Faltaron: " + faltaron +
                            "    |    % Asistencia: " + String.format("%.2f", porcentaje) + "%"
                    ).setFontSize(11));
                }

                // Pie con fecha/hora de generación
                String generadoEl = LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
                document.add(new Paragraph("\nDocumento generado el: " + generadoEl)
                        .setFontSize(10)
                        .setTextAlignment(TextAlignment.RIGHT));
            }

            JOptionPane.showMessageDialog(null,
                    "El reporte de asistencia diaria se ha guardado correctamente.");

            // 6) Abrir en Edge (fallback a visor predeterminado)
            abrirEnEdgeOVisor(fileToSave);

        } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al generar el PDF: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try { if (rs  != null) rs.close(); } catch (Exception ignored) {}
            try { if (pst != null) pst.close(); } catch (Exception ignored) {}
            cx.desconectar(cn); // tu desconectar(Connection)
        }
    }

    // ===== Helpers de estilo y utilitarios =====

    private Cell encabezadoCell(String txt) {
        return new Cell()
                .add(new Paragraph(txt).setBold())
                .setTextAlignment(TextAlignment.CENTER);
    }

    private Cell celda(String txt, TextAlignment align) {
        return new Cell().add(new Paragraph(txt))
                .setTextAlignment(align);
    }

    private String nvl(String s) {
        return (s == null) ? "" : s;
    }

    private String sanitizarParaNombre(String nombre) {
        String n = Normalizer.normalize(nombre, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");           // quitar acentos
        n = n.replaceAll("[\\\\/:*?\"<>|]", "-");    // inválidos Windows
        n = n.replaceAll("\\s+", "_");              // espacios -> _
        return n;
    }

    private void abrirEnEdgeOVisor(File file) {
        try {
            String path = file.getAbsolutePath();
            new ProcessBuilder("cmd", "/c", "start", "msedge", "\"" + path + "\"")
                    .inheritIO()
                    .start()
                    .waitFor();
        } catch (Exception edgeFail) {
            try {
                Desktop.getDesktop().open(file);
            } catch (Exception anyFail) {
                JOptionPane.showMessageDialog(null,
                        "No se pudo abrir el archivo automáticamente.\nRuta: " + file.getAbsolutePath(),
                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
