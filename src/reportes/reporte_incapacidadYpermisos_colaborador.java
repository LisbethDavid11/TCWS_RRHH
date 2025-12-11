package reportes;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.TextAlignment;
import conexion.conexion;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class reporte_incapacidadYpermisos_colaborador {

    public void generarReporte() {
        conexion conex = new conexion();
        Connection conn = null;

        try {
            conn = conex.conectar();

            String sqlEmpleados = "SELECT id_empleado, nombres_empleado, apellidos_empleado FROM empleados ORDER BY nombres_empleado, apellidos_empleado";
            PreparedStatement psEmp = conn.prepareStatement(sqlEmpleados);
            ResultSet rsEmp = psEmp.executeQuery();

            JComboBox<String> cbxEmpleados = new JComboBox<>();
            while (rsEmp.next()) {
                String idEmp = rsEmp.getString("id_empleado");
                String nombre = rsEmp.getString("nombres_empleado");
                String apellido = rsEmp.getString("apellidos_empleado");
                cbxEmpleados.addItem(idEmp + " - " + nombre + " " + apellido);
            }
            rsEmp.close();
            psEmp.close();

            if (cbxEmpleados.getItemCount() == 0) {
                JOptionPane.showMessageDialog(null, "No hay empleados registrados.", "Sin empleados", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(null, cbxEmpleados, "Seleccione el colaborador", JOptionPane.PLAIN_MESSAGE);

            String seleccionado = (String) cbxEmpleados.getSelectedItem();
            if (seleccionado == null || seleccionado.trim().isEmpty()) return;

            String[] partes = seleccionado.split(" - ", 2);
            String idEmpleadoSeleccionado = partes[0].trim();
            String nombreEmpleado = partes.length > 1 ? partes[1].trim() : ("ID_" + idEmpleadoSeleccionado);
            String queryEmpleado = "SELECT id_empleado, nombres_empleado, apellidos_empleado, identidad_empleado, " +
                                   "tel_empleado, cargo_empleado, area_empleado " +
                                   "FROM empleados WHERE id_empleado = ?";

            PreparedStatement psEmpleado = conn.prepareStatement(queryEmpleado);
            psEmpleado.setInt(1, Integer.parseInt(idEmpleadoSeleccionado)); // <-- Conversión de String a int
            ResultSet rsEmpleado = psEmpleado.executeQuery();

            String nombreCompleto = "";
            String identidad = "";
            String telefono = "";
            String area = "";
            String cargo = "";
            int idEmpleado = 0;

            if (rsEmpleado.next()) {
                idEmpleado = rsEmpleado.getInt("id_empleado");
                nombreCompleto = rsEmpleado.getString("nombres_empleado") + " " +
                                 rsEmpleado.getString("apellidos_empleado");
                identidad = rsEmpleado.getString("identidad_empleado");
                telefono = rsEmpleado.getString("tel_empleado");
                area = rsEmpleado.getString("area_empleado");
                cargo = rsEmpleado.getString("cargo_empleado");
            }

            rsEmpleado.close();
            psEmpleado.close();

            String nombreArchivoDefault = "Reporte_permisos_incapacidades_" + nombreEmpleado.replaceAll("\\s+", "_") + ".pdf";
            
            

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar reporte de permisos e incapacidades");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos PDF", "pdf"));
            fileChooser.setSelectedFile(new File(nombreArchivoDefault));

            boolean archivoValido = false;
            File fileToSave = null;
            while (!archivoValido) {
                int seleccion = fileChooser.showSaveDialog(null);
                if (seleccion != JFileChooser.APPROVE_OPTION) {
                    JOptionPane.showMessageDialog(null, "Generación de reporte cancelada", "Cancelado", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                fileToSave = fileChooser.getSelectedFile();
                String dest = fileToSave.getAbsolutePath();
                if (!dest.toLowerCase().endsWith(".pdf")) {
                    dest = dest + ".pdf";
                    fileToSave = new File(dest);
                }
                if (fileToSave.exists()) {
                    int resp = JOptionPane.showConfirmDialog(null,
                            "El archivo ya existe. ¿Desea sobrescribirlo?", "Archivo existente",
                            JOptionPane.YES_NO_CANCEL_OPTION);
                    if (resp == JOptionPane.YES_OPTION) {
                        archivoValido = true;
                    } else if (resp == JOptionPane.NO_OPTION) {
                        // volver a mostrar save dialog
                        continue;
                    } else {
                        JOptionPane.showMessageDialog(null, "Generación de reporte cancelada", "Cancelado", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                } else {
                    archivoValido = true;
                }
            }

            PdfWriter writer = new PdfWriter(fileToSave.getAbsolutePath());
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4.rotate());

            encabezado_documentos encabezado = new encabezado_documentos();
            encabezado.agregarEncabezado(document);
            document.add(new Paragraph("Reporte de Permisos e Incapacidades")
                    .setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("\n"));

            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
         
            Paragraph datosEmpleado = new Paragraph(
                    "Nombre del colaborador: " + nombreCompleto + "\n" +
                    "Identidad: " + identidad + "\n" +
                    "Id marcadas: " + idEmpleado + "\n" +
                    "Teléfono: " + telefono + "\n" +
                    "Área de trabajo: " + area + "\n" +
                    "Cargo: " + cargo + "\n"
            );
            datosEmpleado.setFontSize(12);
            datosEmpleado.setTextAlignment(TextAlignment.LEFT);
            document.add(datosEmpleado);

            document.add(new Paragraph("\n"));
            document.add(new Paragraph("PERMISOS POR DIA").setBold().setFontSize(12));
            document.add(new Paragraph("\n"));

            float[] columnasDia = {0.6f, 1.2f, 1.2f, 2.5f, 1f, 1f, 1f, 1f};
            Table tablaDia = new Table(UnitValue.createPercentArray(columnasDia));
            tablaDia.setWidth(UnitValue.createPercentValue(100));

            tablaDia.addHeaderCell(new Cell().add(new Paragraph("No")).setTextAlignment(TextAlignment.CENTER));
            tablaDia.addHeaderCell(new Cell().add(new Paragraph("Desde")).setTextAlignment(TextAlignment.CENTER));
            tablaDia.addHeaderCell(new Cell().add(new Paragraph("Hasta")).setTextAlignment(TextAlignment.CENTER));
            tablaDia.addHeaderCell(new Cell().add(new Paragraph("Motivo")).setTextAlignment(TextAlignment.CENTER));
            tablaDia.addHeaderCell(new Cell().add(new Paragraph("H. inicio")).setTextAlignment(TextAlignment.CENTER));
            tablaDia.addHeaderCell(new Cell().add(new Paragraph("H. fin")).setTextAlignment(TextAlignment.CENTER));
            tablaDia.addHeaderCell(new Cell().add(new Paragraph("Total Hrs")).setTextAlignment(TextAlignment.CENTER));
            tablaDia.addHeaderCell(new Cell().add(new Paragraph("Fecha recibe")).setTextAlignment(TextAlignment.CENTER));

            String sqlPermisosDia = "SELECT desde_fecha, hasta_fecha, motivo_ausencia, desde_hora, hasta_hora, total_horas, fecha_recibe " +
                    "FROM permisos_ausencia_laboral WHERE id_empleado = ? ORDER BY desde_fecha";

            try (PreparedStatement psPermDia = conn.prepareStatement(sqlPermisosDia)) {
                psPermDia.setInt(1, Integer.parseInt(idEmpleadoSeleccionado));
                try (ResultSet rsPermDia = psPermDia.executeQuery()) {
                    int contadorDia = 1;
                    boolean tieneDia = false;
                    while (rsPermDia.next()) {
                        tieneDia = true;
                        tablaDia.addCell(String.valueOf(contadorDia++));
                        java.sql.Date desdeF = rsPermDia.getDate("desde_fecha");
                        java.sql.Date hastaF = rsPermDia.getDate("hasta_fecha");
                        tablaDia.addCell(desdeF != null ? df.format(desdeF) : "");
                        tablaDia.addCell(hastaF != null ? df.format(hastaF) : "");
                        tablaDia.addCell(rsPermDia.getString("motivo_ausencia"));
                        tablaDia.addCell(rsPermDia.getString("desde_hora"));
                        tablaDia.addCell(rsPermDia.getString("hasta_hora"));
                        tablaDia.addCell(rsPermDia.getString("total_horas"));
                        java.sql.Date frec = rsPermDia.getDate("fecha_recibe");
                        tablaDia.addCell(frec != null ? df.format(frec) : "");
                    }
                    if (!tieneDia) {
                        tablaDia.addCell(new Cell(1, 8).add(new Paragraph("No hay permisos por día registrados.")).setTextAlignment(TextAlignment.CENTER));
                    }
                }
            }

            document.add(tablaDia);
            document.add(new Paragraph("\n\n"));
            document.add(new Paragraph("PERMISOS POR HORA").setBold().setFontSize(12));
            document.add(new Paragraph("\n"));

            float[] columnasHora = {0.6f, 1.2f, 2.5f, 1f, 1f, 1f, 1f};
            Table tablaHora = new Table(UnitValue.createPercentArray(columnasHora));
            tablaHora.setWidth(UnitValue.createPercentValue(100));

            tablaHora.addHeaderCell(new Cell().add(new Paragraph("No")).setTextAlignment(TextAlignment.CENTER));
            tablaHora.addHeaderCell(new Cell().add(new Paragraph("Fecha")).setTextAlignment(TextAlignment.CENTER));
            tablaHora.addHeaderCell(new Cell().add(new Paragraph("Motivo")).setTextAlignment(TextAlignment.CENTER));
            tablaHora.addHeaderCell(new Cell().add(new Paragraph("H. inicio")).setTextAlignment(TextAlignment.CENTER));
            tablaHora.addHeaderCell(new Cell().add(new Paragraph("H. fin")).setTextAlignment(TextAlignment.CENTER));
            tablaHora.addHeaderCell(new Cell().add(new Paragraph("Total Hrs")).setTextAlignment(TextAlignment.CENTER));
            tablaHora.addHeaderCell(new Cell().add(new Paragraph("Fecha recibe")).setTextAlignment(TextAlignment.CENTER));

            String sqlPermisosHora = "SELECT fecha_recibe AS fecha_permiso, motivo_ausencia, desde_hora, hasta_hora, total_horas " +
                    "FROM permisos_ausencia_hora WHERE id_empleado = ? ORDER BY fecha_recibe";

            try (PreparedStatement psPermHora = conn.prepareStatement(sqlPermisosHora)) {
                psPermHora.setInt(1, Integer.parseInt(idEmpleadoSeleccionado));
                try (ResultSet rsPermHora = psPermHora.executeQuery()) {
                    int contadorHora = 1;
                    boolean tieneHora = false;
                    while (rsPermHora.next()) {
                        tieneHora = true;
                        tablaHora.addCell(String.valueOf(contadorHora++));
                        java.sql.Date fperm = rsPermHora.getDate("fecha_permiso");
                        tablaHora.addCell(fperm != null ? df.format(fperm) : "");
                        tablaHora.addCell(rsPermHora.getString("motivo_ausencia"));
                        tablaHora.addCell(rsPermHora.getString("desde_hora"));
                        tablaHora.addCell(rsPermHora.getString("hasta_hora"));
                        tablaHora.addCell(rsPermHora.getString("total_horas"));
                        // para consistencia, mostramos la misma fecha en columna "Fecha recibe"
                        tablaHora.addCell(fperm != null ? df.format(fperm) : "");
                    }
                    if (!tieneHora) {
                        tablaHora.addCell(new Cell(1, 7).add(new Paragraph("No hay permisos por hora registrados.")).setTextAlignment(TextAlignment.CENTER));
                    }
                }
            }

            document.add(tablaHora);
            document.add(new Paragraph("\n\n"));

            document.add(new Paragraph("INCAPACIDADES").setBold().setFontSize(12));
            document.add(new Paragraph("\n"));

            float[] columnasInc = {0.6f, 1.6f, 2.5f, 1.2f, 1.2f, 1f};
            Table tablaInc = new Table(UnitValue.createPercentArray(columnasInc));
            tablaInc.setWidth(UnitValue.createPercentValue(100));

            tablaInc.addHeaderCell(new Cell().add(new Paragraph("No")).setTextAlignment(TextAlignment.CENTER));
            tablaInc.addHeaderCell(new Cell().add(new Paragraph("Riesgo")).setTextAlignment(TextAlignment.CENTER));
            tablaInc.addHeaderCell(new Cell().add(new Paragraph("Tipo")).setTextAlignment(TextAlignment.CENTER));
            tablaInc.addHeaderCell(new Cell().add(new Paragraph("Desde")).setTextAlignment(TextAlignment.CENTER));
            tablaInc.addHeaderCell(new Cell().add(new Paragraph("Hasta")).setTextAlignment(TextAlignment.CENTER));
            tablaInc.addHeaderCell(new Cell().add(new Paragraph("Total días")).setTextAlignment(TextAlignment.CENTER));

            String sqlIncapacidades = "SELECT riesgo_incapacidad, tipo_incapacidad, inicio_incapacidad, fin_incapacidad, total_dias " +
                    "FROM incapacidad_laboral WHERE id_empleado = ? ORDER BY inicio_incapacidad";

            try (PreparedStatement psIncap = conn.prepareStatement(sqlIncapacidades)) {
                psIncap.setInt(1, Integer.parseInt(idEmpleadoSeleccionado));
                try (ResultSet rsIncap = psIncap.executeQuery()) {
                    int contadorIncap = 1;
                    boolean tieneIncap = false;
                    while (rsIncap.next()) {
                        tieneIncap = true;
                        tablaInc.addCell(String.valueOf(contadorIncap++));
                        tablaInc.addCell(rsIncap.getString("riesgo_incapacidad"));
                        tablaInc.addCell(rsIncap.getString("tipo_incapacidad"));
                        java.sql.Date ini = rsIncap.getDate("inicio_incapacidad");
                        java.sql.Date fin = rsIncap.getDate("fin_incapacidad");
                        tablaInc.addCell(ini != null ? df.format(ini) : "");
                        tablaInc.addCell(fin != null ? df.format(fin) : "");
                        tablaInc.addCell(String.valueOf(rsIncap.getInt("total_dias")));
                    }
                    if (!tieneIncap) {
                        tablaInc.addCell(new Cell(1, 6).add(new Paragraph("No hay incapacidades registradas.")).setTextAlignment(TextAlignment.CENTER));
                    }
                }
            }

            document.add(tablaInc);
            document.close();

            JOptionPane.showMessageDialog(null, "Reporte generado correctamente:\n" + fileToSave.getAbsolutePath(), "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // abrir en edge
            try {
                // Ruta absoluta del archivo PDF ya guardado
                String rutaPDF = fileToSave.getAbsolutePath();

                // Comando para ejecutar Microsoft Edge
                String comando = "cmd /c start msedge \"" + rutaPDF + "\"";

                Runtime.getRuntime().exec(comando);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, 
                    "El archivo se generó, pero no se pudo abrir automáticamente en Edge.\n" + ex.getMessage(),
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
                );
            }
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error SQL: " + sqle.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            JOptionPane.showMessageDialog(null, "Archivo no encontrado: " + fnfe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                conex.desconectar(null);
            } catch (Exception e) {
                // ignorar
            }
        }
    }
}
