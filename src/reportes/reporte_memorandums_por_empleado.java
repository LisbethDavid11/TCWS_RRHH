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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;

public class reporte_memorandums_por_empleado {

    private static final float SIZE_TITULO = 14f;
    private static final float SIZE_CUERPO = 11f;

    public void generarReporte() {
        conexion conex = new conexion();
        Connection conn = null;

        try {
            conn = conex.conectar();
            String sqlEmpleados = "SELECT id_empleado, nombres_empleado, apellidos_empleado FROM empleados ORDER BY nombres_empleado, apellidos_empleado";
            try (PreparedStatement psEmp = conn.prepareStatement(sqlEmpleados);
                 ResultSet rsEmp = psEmp.executeQuery()) {

                DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
                while (rsEmp.next()) {
                    int id = rsEmp.getInt("id_empleado");
                    String nom = rsEmp.getString("nombres_empleado");
                    String ape = rsEmp.getString("apellidos_empleado");
                    modelo.addElement(id + " - " + nom + " " + ape);
                }

                if (modelo.getSize() == 0) {
                    JOptionPane.showMessageDialog(null, "No hay empleados registrados.", "Sin empleados", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                JComboBox<String> cbx = new JComboBox<>(modelo);
                JOptionPane.showMessageDialog(null, cbx, "Seleccione el colaborador", JOptionPane.PLAIN_MESSAGE);

                String seleccionado = (String) cbx.getSelectedItem();
                if (seleccionado == null || seleccionado.trim().isEmpty()) return;

                String[] partes = seleccionado.split(" - ", 2);
                int idEmpleadoSeleccionado = Integer.parseInt(partes[0].trim());
                String nombreEmpleado = partes.length > 1 ? partes[1].trim() : ("ID_" + idEmpleadoSeleccionado);

                String sqlEmpleado = "SELECT nombres_empleado, apellidos_empleado, identidad_empleado, tel_empleado, cargo_empleado, area_empleado, id_empleado " +
                        "FROM empleados WHERE id_empleado = ?";
                String nombreCompleto = "";
                String identidad = "";
                String telefono = "";
                String cargo = "";
                String area = "";
                int idEmpleado = idEmpleadoSeleccionado;

                try (PreparedStatement psE = conn.prepareStatement(sqlEmpleado)) {
                    psE.setInt(1, idEmpleadoSeleccionado);
                    try (ResultSet rsE = psE.executeQuery()) {
                        if (rsE.next()) {
                            nombreCompleto = (rsE.getString("nombres_empleado") == null ? "" : rsE.getString("nombres_empleado")) +
                                    " " +
                                    (rsE.getString("apellidos_empleado") == null ? "" : rsE.getString("apellidos_empleado"));
                            identidad = rsE.getString("identidad_empleado");
                            telefono = rsE.getString("tel_empleado");
                            cargo = rsE.getString("cargo_empleado");
                            area = rsE.getString("area_empleado");
                        }
                    }
                }

                // 3) Pedir ruta con JFileChooser y controlar .pdf y sobreescritura
                String nombreArchivoDefault = "Reporte_memorandums_" + nombreCompleto.replaceAll("\\s+", "_") + ".pdf";
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Guardar reporte de memorándums");
                fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos PDF", "pdf"));
                fileChooser.setSelectedFile(new File(nombreArchivoDefault));

                File fileToSave = null;
                boolean archivoValido = false;
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
                            continue; // volver a elegir nombre
                        } else {
                            JOptionPane.showMessageDialog(null, "Generación de reporte cancelada", "Cancelado", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    } else {
                        archivoValido = true;
                    }
                }

                // 4) Preparar PDF (A4 horizontal)
                PdfWriter writer = new PdfWriter(fileToSave.getAbsolutePath());
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf, PageSize.A4.rotate());

                // Encabezado (usa tu clase)
                encabezado_documentos encabezado = new encabezado_documentos();
                encabezado.agregarEncabezado(document);

                document.add(new Paragraph("Reporte de Memorándums")
                        .setBold().setFontSize(SIZE_TITULO).setTextAlignment(TextAlignment.CENTER));
                document.add(new Paragraph("\n"));

                document.add(new Paragraph("Datos del colaborador").setBold().setFontSize(12));
                document.add(new Paragraph("Nombre completo: " + (nombreCompleto == null ? "" : nombreCompleto)).setFontSize(SIZE_CUERPO));
                document.add(new Paragraph("Identidad: " + (identidad == null ? "" : identidad)).setFontSize(SIZE_CUERPO));
                document.add(new Paragraph("Id marcadas: " + idEmpleado).setFontSize(SIZE_CUERPO));
                document.add(new Paragraph("Área de trabajo: " + (area == null ? "" : area)).setFontSize(SIZE_CUERPO));
                document.add(new Paragraph("Cargo: " + (cargo == null ? "" : cargo)).setFontSize(SIZE_CUERPO));
                document.add(new Paragraph("\n"));
                String sqlMem = "SELECT id_memorandum, fecha_actual, hora_actual, motivo_memorandum FROM memorandum WHERE id_empleado = ? ORDER BY fecha_actual DESC, hora_actual DESC";

                float[] columnas = {0.6f, 1.4f, 1.0f, 6.0f};
                Table tabla = new Table(UnitValue.createPercentArray(columnas));
                tabla.setWidth(UnitValue.createPercentValue(100));

                tabla.addHeaderCell(new Cell().add(new Paragraph("No.")).setTextAlignment(TextAlignment.CENTER));
                tabla.addHeaderCell(new Cell().add(new Paragraph("Fecha")).setTextAlignment(TextAlignment.CENTER));
                tabla.addHeaderCell(new Cell().add(new Paragraph("Hora")).setTextAlignment(TextAlignment.CENTER));
                tabla.addHeaderCell(new Cell().add(new Paragraph("Motivo")).setTextAlignment(TextAlignment.CENTER));

                int contador = 0;
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                try (PreparedStatement psM = conn.prepareStatement(sqlMem)) {
                    psM.setInt(1, idEmpleadoSeleccionado);
                    try (ResultSet rsM = psM.executeQuery()) {
                        boolean tiene = false;
                        while (rsM.next()) {
                            tiene = true;
                            contador++;
                            tabla.addCell(String.valueOf(contador));
                            Date fecha = rsM.getDate("fecha_actual");
                            Time hora = rsM.getTime("hora_actual");
                            tabla.addCell(fecha != null ? df.format(fecha) : "");
                            tabla.addCell(hora != null ? hora.toString().substring(0,5) : "");
                            tabla.addCell(rsM.getString("motivo_memorandum"));
                        }
                        if (!tiene) {
                            tabla.addCell(new Cell(1, 4).add(new Paragraph("No hay memorándums registrados.")).setTextAlignment(TextAlignment.CENTER));
                        }
                    }
                }

                document.add(tabla);
                document.add(new Paragraph("\n"));
                document.add(new Paragraph("Total de memorándums: " + contador).setBold().setFontSize(SIZE_CUERPO));
                document.close();

                JOptionPane.showMessageDialog(null, "Reporte generado correctamente:\n" + fileToSave.getAbsolutePath(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
                try {
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(fileToSave);
                    }
                } catch (IOException ioe) {
                }
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
            }
        }
    }
}
