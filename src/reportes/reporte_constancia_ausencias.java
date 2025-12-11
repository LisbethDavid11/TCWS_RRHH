package reportes;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

import javax.swing.JOptionPane;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import conexion.conexion;

public class reporte_constancia_ausencias {

    private static final float SIZE_TITULO  = 14f;
    private static final float SIZE_CUERPO  = 12f;
    private static final float SIZE_ROTULOS = 11f;

    /**
     * Genera la constancia individual de ausencias injustificadas para un empleado.
     * @param idEmpleado  ID del empleado (columna empleados.id)
     * @param fechaDesde  java.util.Date inclusive
     * @param fechaHasta  java.util.Date inclusive
     */
    public void generarParaEmpleado(int idEmpleado, java.util.Date fechaDesde, java.util.Date fechaHasta) {
        if (fechaDesde == null || fechaHasta == null) {
            JOptionPane.showMessageDialog(null, "Debe indicar un rango de fechas válido.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (fechaDesde.after(fechaHasta)) {
            JOptionPane.showMessageDialog(null, "La fecha 'Desde' no puede ser mayor que la fecha 'Hasta'.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Formatos de fecha
        SimpleDateFormat fNombre = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat fMostrar = new SimpleDateFormat("dd-MM-yy");

        // Consultar datos del empleado y las fechas A_I
        Empleado emp = obtenerEmpleado(idEmpleado);
        if (emp == null) {
            JOptionPane.showMessageDialog(null, "No se encontró el empleado con ID: " + idEmpleado, "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        List<String> fechasAI = obtenerFechasAusenciasInjustificadas(idEmpleado, new java.sql.Date(fechaDesde.getTime()), new java.sql.Date(fechaHasta.getTime()));

        String archivo = String.format("Constancia_AusenciasInjustificadas_%s_%s_%s_a_%s.pdf",
                safe(emp.nombres), safe(emp.apellidos), fNombre.format(fechaDesde), fNombre.format(fechaHasta));

        File fileToSave = validacion_ruta.obtenerRutaArchivo(archivo);
        if (fileToSave == null) return;

        try {
            PdfWriter writer = new PdfWriter(new FileOutputStream(fileToSave));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Encabezado institucional
            encabezado_documentos encabezado = new encabezado_documentos();
            encabezado.agregarEncabezado(document);

            // Título
            document.add(new Paragraph("Constancia de Ausencias Injustificadas")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold()
                    .setFontSize(SIZE_TITULO)
                    .setMarginTop(15)
                    .setMarginBottom(20));

            // Cuerpo
            String periodo = "del " + fMostrar.format(fechaDesde) + " al " + fMostrar.format(fechaHasta);
            int veces = fechasAI.size();

            String intro = "Por este medio se deja constancia de las ausencias injustificadas del colaborador " +
                    emp.nombres + " " + emp.apellidos + ", identificado con DNI " + nvl(emp.identidad) + ", " +
                    "quien se desempeña como " + nvl(emp.cargo) + " en el área de " + nvl(emp.area) + ". " +
                    "El periodo considerado para esta constancia es " + periodo + ". ";

            String conteo = (veces == 0)
                    ? "Durante dicho periodo no se registran ausencias injustificadas."
                    : ("Durante dicho periodo se registraron " + veces + (veces == 1 ? " ausencia injustificada." : " ausencias injustificadas."));

            document.add(new Paragraph(intro + conteo)
                    .setFontSize(SIZE_CUERPO)
                    .setTextAlignment(TextAlignment.JUSTIFIED)
                    .setMarginBottom(12));

            if (veces > 0) {
                // Listado de fechas en línea, separado por coma
                String fechasLinea = "Las fechas correspondientes son: " + String.join(", ", fechasAI) + ".";
                document.add(new Paragraph(fechasLinea)
                        .setFontSize(SIZE_CUERPO)
                        .setTextAlignment(TextAlignment.JUSTIFIED)
                        .setMarginBottom(20));
            }

            // Firmas
            document.add(new Paragraph("\n\n_________________________________              ______________________________")
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Firma RR.HH.                                               Firma del colaborador")
                    .setFontSize(SIZE_ROTULOS)
                    .setTextAlignment(TextAlignment.CENTER));

            document.close();
            JOptionPane.showMessageDialog(null, "La constancia se ha guardado correctamente");
            Desktop.getDesktop().open(fileToSave);

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el PDF: " + e.getMessage());
        }
    }

    // ====== Consultas a BD ======

    private Empleado obtenerEmpleado(int idEmpleado) {
        conexion cn = new conexion();
        Connection con = cn.conectar();
        String sql = "SELECT identidad_empleado, nombres_empleado, apellidos_empleado, cargo_empleado, area_empleado " +
                     "FROM empleados WHERE id = ?";
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, idEmpleado);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Empleado e = new Empleado();
                e.identidad = rs.getString("identidad_empleado");
                e.nombres   = rs.getString("nombres_empleado");
                e.apellidos = rs.getString("apellidos_empleado");
                e.cargo     = rs.getString("cargo_empleado");
                e.area      = rs.getString("area_empleado");
                return e;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al consultar empleado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            cn.desconectar(con);
        }
        return null;
    }

    private List<String> obtenerFechasAusenciasInjustificadas(int idEmpleado, java.sql.Date desde, java.sql.Date hasta) {
        List<String> fechas = new ArrayList<>();
        conexion cn = new conexion();
        Connection con = cn.conectar();
        String sql = "SELECT fecha FROM asistencia_diaria WHERE id_empleado = ? AND fecha BETWEEN ? AND ? AND estado = 'A_I' ORDER BY fecha";
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yy");
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, idEmpleado);
            pst.setDate(2, desde);
            pst.setDate(3, hasta);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                java.util.Date d = rs.getDate("fecha");
                fechas.add(f.format(d));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al consultar ausencias: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            cn.desconectar(con);
        }
        return fechas;
    }

    // ====== Helpers ======

    private String safe(String s) {
        if (s == null) return "ND";
        return s.trim().replace(" ", "_");
    }
    private String nvl(String s) {
        return (s == null || s.trim().isEmpty()) ? "N/D" : s.trim();
    }

    private static class Empleado {
        String identidad;
        String nombres;
        String apellidos;
        String cargo;
        String area;
    }
}
