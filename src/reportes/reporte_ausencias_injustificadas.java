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

public class reporte_ausencias_injustificadas {

    public void generarReporte() {
        conexion conex = new conexion();
        Connection conn = null;

        try {
            conn = conex.conectar();

            // 1) Cargar empleados en JComboBox
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

            // Mostrar JComboBox dentro de un diálogo simple para seleccionar empleado
            JOptionPane.showMessageDialog(null, cbxEmpleados, "Seleccione el colaborador", JOptionPane.PLAIN_MESSAGE);

            String seleccionado = (String) cbxEmpleados.getSelectedItem();
            if (seleccionado == null || seleccionado.trim().isEmpty()) return;

            // Obtener id y nombre completo
            String[] partes = seleccionado.split(" - ", 2);
            String idEmpleadoSeleccionado = partes[0].trim();
            String nombreEmpleado = partes.length > 1 ? partes[1].trim() : ("ID_" + idEmpleadoSeleccionado);

            // CONSULTA PARA OBTENER LOS DATOS GENERALES DEL EMPLEADO
            String queryEmpleado = "SELECT id_empleado, nombres_empleado, apellidos_empleado, identidad_empleado, " +
                                   "tel_empleado, cargo_empleado, area_empleado " +
                                   "FROM empleados WHERE id_empleado = ?";

            PreparedStatement psEmpleado = conn.prepareStatement(queryEmpleado);
            psEmpleado.setInt(1, Integer.parseInt(idEmpleadoSeleccionado));
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

            // 2) Pedir ruta con JFileChooser
            String nombreArchivoDefault = "Reporte_ausencias_injustificadas_" + nombreEmpleado.replaceAll("\\s+", "_") + ".pdf";
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar reporte de ausencias injustificadas");
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
                        continue;
                    } else {
                        JOptionPane.showMessageDialog(null, "Generación de reporte cancelada", "Cancelado", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                } else {
                    archivoValido = true;
                }
            }

            // 3) Crear documento PDF
            PdfWriter writer = new PdfWriter(fileToSave.getAbsolutePath());
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4.rotate());

            // Encabezado (si tienes tu clase de encabezado)
            encabezado_documentos encabezado = new encabezado_documentos();
            encabezado.agregarEncabezado(document);

            // Título
            document.add(new Paragraph("Reporte de Ausencias Injustificadas" /*+ nombreEmpleado*/)
                    .setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("\n"));

            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

            // Información básica del empleado
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

            document.add(new Paragraph("AUSENCIAS INJUSTIFICADAS").setBold().setFontSize(12));
            document.add(new Paragraph("\n"));

            float[] columnas = {0.6f, 1.2f, 1.2f, 1f, 1f, 1f, 1f};
            Table tabla = new Table(UnitValue.createPercentArray(columnas));
            tabla.setWidth(UnitValue.createPercentValue(100));

            tabla.addHeaderCell(new Cell().add(new Paragraph("No")).setTextAlignment(TextAlignment.CENTER));
            tabla.addHeaderCell(new Cell().add(new Paragraph("Fecha")).setTextAlignment(TextAlignment.CENTER));
            tabla.addHeaderCell(new Cell().add(new Paragraph("Hora entrada")).setTextAlignment(TextAlignment.CENTER));
            tabla.addHeaderCell(new Cell().add(new Paragraph("Hora ausencia")).setTextAlignment(TextAlignment.CENTER));
            tabla.addHeaderCell(new Cell().add(new Paragraph("Tiempo")).setTextAlignment(TextAlignment.CENTER));
            tabla.addHeaderCell(new Cell().add(new Paragraph("Motivo")).setTextAlignment(TextAlignment.CENTER));
            //tabla.addHeaderCell(new Cell().add(new Paragraph("Edad")).setTextAlignment(TextAlignment.CENTER));

            String sqlInjustificadas = "SELECT fecha_ausencia, hora_entrada, hora_ausencia, tiempo_injustificado, motivo, edad_empleado " +
                                       "FROM injustificadas WHERE id_empleado = ? ORDER BY fecha_ausencia";

            int totalRegistros = 0;
            try (PreparedStatement ps = conn.prepareStatement(sqlInjustificadas)) {
                ps.setInt(1, Integer.parseInt(idEmpleadoSeleccionado));
                try (ResultSet rs = ps.executeQuery()) {
                    boolean tieneRegistros = false;
                    int contador = 1;
                    while (rs.next()) {
                        tieneRegistros = true;
                        totalRegistros++;
                        tabla.addCell(String.valueOf(contador++));
                        java.sql.Date fecha = rs.getDate("fecha_ausencia");
                        tabla.addCell(fecha != null ? df.format(fecha) : "");
                        tabla.addCell(rs.getString("hora_entrada"));
                        tabla.addCell(rs.getString("hora_ausencia"));
                        tabla.addCell(rs.getString("tiempo_injustificado"));
                        tabla.addCell(rs.getString("motivo"));
                        //tabla.addCell(String.valueOf(rs.getInt("edad_empleado")));
                    }
                    if (!tieneRegistros) {
                        tabla.addCell(new Cell(1, 7).add(new Paragraph("No hay ausencias injustificadas registradas.")).setTextAlignment(TextAlignment.CENTER));
                    }
                }
            }

            document.add(tabla);
            document.add(new Paragraph("\n"));

            // RESUMEN
            document.add(new Paragraph("Total de ausencias injustificadas = " + totalRegistros)
                    .setBold().setFontSize(12));

            // Cerrar documento
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
