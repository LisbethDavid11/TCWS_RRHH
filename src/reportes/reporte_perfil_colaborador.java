package reportes;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import conexion.conexion;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class reporte_perfil_colaborador {

    private static final float SIZE_TITULO = 14f;
    private static final float SIZE_CUERPO = 11f;

    public void generarReporte() {
        conexion conex = new conexion();
        Connection conn = null;

        try {
            conn = conex.conectar();

            String sqlEmpleados = "SELECT id_empleado, nombres_empleado, apellidos_empleado FROM empleados ORDER BY nombres_empleado, apellidos_empleado";
            PreparedStatement psEmp = conn.prepareStatement(sqlEmpleados);
            ResultSet rsEmp = psEmp.executeQuery();

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
            int idEmpleado = Integer.parseInt(seleccionado.split(" - ")[0].trim());

            // 2) Datos del empleado
            String sqlEmpleado = "SELECT * FROM empleados WHERE id_empleado = ?";
            PreparedStatement psE = conn.prepareStatement(sqlEmpleado);
            psE.setInt(1, idEmpleado);
            ResultSet rsE = psE.executeQuery();

            String nombreCompleto = "", identidad = "", telefono = "", cargo = "", area = "", rutaFoto = "";
            if (rsE.next()) {
                nombreCompleto = rsE.getString("nombres_empleado") + " " + rsE.getString("apellidos_empleado");
                identidad = rsE.getString("identidad_empleado");
                telefono = rsE.getString("tel_empleado");
                cargo = rsE.getString("cargo_empleado");
                area = rsE.getString("area_empleado");
                rutaFoto = rsE.getString("fotografia_empleado");
            }

            // 3) JFileChooser para guardar PDF
            String nombreArchivoDefault = "Perfil_colaborador_" + nombreCompleto.replaceAll("\\s+", "_") + ".pdf";
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar reporte de perfil de colaborador");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos PDF", "pdf"));
            fileChooser.setSelectedFile(new File(nombreArchivoDefault));

            File fileToSave = null;
            boolean archivoValido = false;
            while (!archivoValido) {
                int seleccion = fileChooser.showSaveDialog(null);
                if (seleccion != JFileChooser.APPROVE_OPTION) return;
                fileToSave = fileChooser.getSelectedFile();
                if (!fileToSave.getAbsolutePath().toLowerCase().endsWith(".pdf")) {
                    fileToSave = new File(fileToSave.getAbsolutePath() + ".pdf");
                }
                if (fileToSave.exists()) {
                    int resp = JOptionPane.showConfirmDialog(null,
                            "El archivo ya existe. ¿Desea sobrescribirlo?", "Archivo existente",
                            JOptionPane.YES_NO_CANCEL_OPTION);
                    if (resp == JOptionPane.YES_OPTION) archivoValido = true;
                    else if (resp == JOptionPane.NO_OPTION) continue;
                    else return;
                } else archivoValido = true;
            }

            PdfWriter writer = new PdfWriter(fileToSave.getAbsolutePath());
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4.rotate());

            encabezado_documentos encabezado = new encabezado_documentos();
            encabezado.agregarEncabezado(document);

            document.add(new Paragraph("Perfil Completo de Colaborador")
                    .setBold().setFontSize(SIZE_TITULO).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("\n"));

            if (rutaFoto != null && !rutaFoto.isEmpty()) {
                try {
                    ImageData data = ImageDataFactory.create(rutaFoto);
                    Image img = new Image(data);
                    img.setWidth(70);
                    img.setHeight(70);
                    img.setMarginBottom(10);
                    document.add(img);
                } catch (Exception ex) {
                }
            }

            Table tablaDatos = new Table(UnitValue.createPercentArray(new float[]{10,30})).setWidth(UnitValue.createPercentValue(80));
            agregarCelda(tablaDatos, "Nombre completo:", nombreCompleto);
            agregarCelda(tablaDatos, "Identidad:", identidad);
            agregarCelda(tablaDatos, "Teléfono:", telefono);
            agregarCelda(tablaDatos, "Área de trabajo:", area);
            agregarCelda(tablaDatos, "Cargo:", cargo);
            document.add(tablaDatos);
            document.add(new Paragraph("\n"));

            // Tablas de registros
            agregarTablaSeccion(conn, document, "Permisos por día",
                    "SELECT desde_fecha AS Fecha_inicio, hasta_fecha AS Fecha_fin, total_fecha AS Total_dias, motivo_ausencia AS Motivo " +
                            "FROM permisos_ausencia_laboral WHERE id_empleado = ? ORDER BY desde_fecha DESC", idEmpleado);

            agregarTablaSeccion(conn, document, "Permisos por hora",
                    "SELECT desde_hora AS Desde, hasta_hora AS Hasta, total_horas AS Total_horas, motivo_ausencia AS Motivo, fecha_recibe AS Fecha " +
                            "FROM permisos_ausencia_hora WHERE id_empleado = ? ORDER BY fecha_recibe DESC", idEmpleado);

            agregarTablaSeccion(conn, document, "Incapacidades",
                    "SELECT inicio_incapacidad AS Desde, fin_incapacidad AS Hasta, total_dias AS Total_dias, tipo_incapacidad AS Motivo " +
                            "FROM incapacidad_laboral WHERE id_empleado = ? ORDER BY inicio_incapacidad DESC", idEmpleado);

            agregarTablaSeccion(conn, document, "Vacaciones",
                    "SELECT fecha_inicio_v AS Inicio, fecha_finalizacion_v AS Fin, total_dias AS Total_dias, dias_pendientes AS Dias_pendientes " +
                            "FROM vacaciones WHERE id_empleado = ? ORDER BY fecha_inicio_v DESC", idEmpleado);

            agregarTablaSeccion(conn, document, "Ausencias injustificadas",
                    "SELECT fecha_ausencia AS Fecha, motivo AS Motivo, tiempo_injustificado AS Tiempo " +
                            "FROM injustificadas WHERE id_empleado = ? ORDER BY fecha_ausencia DESC", idEmpleado);

            agregarTablaSeccion(conn, document, "Memorándums",
                    "SELECT fecha_actual AS Fecha, hora_actual AS Hora, motivo_memorandum AS Motivo " +
                            "FROM memorandum WHERE id_empleado = ? ORDER BY fecha_actual DESC, hora_actual DESC", idEmpleado);

            document.close();
            JOptionPane.showMessageDialog(null, "Reporte generado correctamente:\n" + fileToSave.getAbsolutePath(),
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

            try { if (Desktop.isDesktopSupported()) Desktop.getDesktop().open(fileToSave); } catch (IOException ioe) {}

        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try { conex.desconectar(conn); } catch (Exception e) {}
        }
    }

    private void agregarCelda(Table tabla, String etiqueta, String valor) {
        tabla.addCell(new Cell().add(new Paragraph(etiqueta)).setBold());
        tabla.addCell(new Cell().add(new Paragraph(valor != null ? valor : "—")));
    }

    private void agregarTablaSeccion(Connection conn, Document document, String titulo, String sql, int idEmpleado) throws SQLException {
        document.add(new Paragraph("\n" + titulo).setBold().setFontSize(12));

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEmpleado);
            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData md = rs.getMetaData();
                int columnas = md.getColumnCount();

                float[] anchos = new float[columnas + 2]; 
                anchos[0] = 5f; // No.
                anchos[1] = 15f; // Fecha Registro
                float restante = 100f - 25f; // resto para columnas restantes
                for (int i = 2; i < anchos.length; i++) {
                    anchos[i] = restante / (columnas);
                }

                Table tabla = new Table(UnitValue.createPercentArray(anchos))
                        .setWidth(UnitValue.createPercentValue(100));

                // Encabezados
                tabla.addHeaderCell(new Cell().add(new Paragraph("No.")).setTextAlignment(TextAlignment.CENTER).setBold());
                tabla.addHeaderCell(new Cell().add(new Paragraph("Fecha Registro")).setTextAlignment(TextAlignment.CENTER).setBold());
                for (int i = 1; i <= columnas; i++) {
                    tabla.addHeaderCell(new Cell().add(new Paragraph(md.getColumnLabel(i)))
                            .setTextAlignment(TextAlignment.CENTER)
                            .setBold());
                }

                boolean tieneDatos = false;
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                int contador = 1;
                while (rs.next()) {
                    tieneDatos = true;
                    // Columna No.
                    tabla.addCell(new Cell().add(new Paragraph(String.valueOf(contador)))
                            .setTextAlignment(TextAlignment.CENTER));

                    // Columna Fecha Registro: intentamos tomar la primera columna tipo DATE si existe
                    Object fechaRegistro = null;
                    for (int i = 1; i <= columnas; i++) {
                        if (rs.getMetaData().getColumnType(i) == Types.DATE) {
                            fechaRegistro = rs.getObject(i);
                            break;
                        }
                    }
                    if (fechaRegistro instanceof Date) {
                        fechaRegistro = df.format((Date) fechaRegistro);
                    }
                    tabla.addCell(new Cell().add(new Paragraph(fechaRegistro != null ? fechaRegistro.toString() : "—"))
                            .setTextAlignment(TextAlignment.CENTER));

                    // Resto de columnas
                    for (int i = 1; i <= columnas; i++) {
                        Object val = rs.getObject(i);
                        if (val instanceof Date) val = df.format((Date) val);
                        tabla.addCell(new Cell().add(new Paragraph(val != null ? val.toString() : "—"))
                                .setTextAlignment(TextAlignment.LEFT));
                    }
                    contador++;
                }

                if (!tieneDatos) {
                    tabla.addCell(new Cell(1, columnas + 2)
                            .add(new Paragraph("No hay registros."))
                            .setTextAlignment(TextAlignment.CENTER));
                }

                document.add(tabla);
            }
        }
    }

    
}
