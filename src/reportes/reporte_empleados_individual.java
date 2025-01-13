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
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.TextAlignment;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import conexion.conexion;

public class reporte_empleados_individual {

    public void generarReporteEmpleadoIndividual(int idEmpleado) {
        conexion conex = new conexion();

        try {
            // Ejecutar la consulta para obtener los datos del empleado seleccionado
            Statement estatuto = conex.conectar().createStatement();
            String query = "SELECT * FROM empleados WHERE id_empleado = " + idEmpleado;
            ResultSet rs = estatuto.executeQuery(query);

            if (rs.next()) {
                // Obtener datos del empleado
                String nombres = rs.getString("nombres_empleado");
                String apellidos = rs.getString("apellidos_empleado");
                String identidad = rs.getString("identidad_empleado");
                String fechaNacimiento = rs.getString("nacimiento_empleado");
                String sexo = rs.getString("sexo_empleado");
                String estadoCivil = rs.getString("civil_empleado");
                String telefono = rs.getString("tel_empleado");
                String correo = rs.getString("correo_empleado");
                String cargo = rs.getString("cargo_empleado");
                String area = rs.getString("area_empleado");
                String fechaInicio = rs.getString("inicio_empleado");
                String fechaRenuncia = rs.getString("renuncia_empleado") != null ? rs.getString("renuncia_empleado") : "Activo";
                String rutaFoto = rs.getString("fotografia_empleado");

                // Obtener la fecha actual formateada
                LocalDate fechaActual = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String fechaFormateada = fechaActual.format(formatter);

                // Generar el nombre del archivo con nombre, apellidos y fecha actual
                String nombreArchivo = "Reporte_" + nombres.replace(" ", "_") + "_" + apellidos.replace(" ", "_") + "_" + fechaFormateada + ".pdf";

                // Mostrar JFileChooser para que el usuarioC seleccione dónde guardar el PDF
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Guardar");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PDF", "pdf");
                fileChooser.setFileFilter(filter);
                fileChooser.setSelectedFile(new File(nombreArchivo));

                boolean archivoValido = false;
                File fileToSave = null;

                // Validar si el archivo ya existe y solicitar un nombre válido
                while (!archivoValido) {
                    int userSelection = fileChooser.showSaveDialog(null);

                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        fileToSave = fileChooser.getSelectedFile();
                        String dest = fileToSave.getAbsolutePath();

                        // Asegurarse de que la extensión sea .pdf
                        if (!dest.toLowerCase().endsWith(".pdf")) {
                            dest += ".pdf";
                            fileToSave = new File(dest);
                        }

                        // Verificar si el archivo ya existe
                        if (fileToSave.exists()) {
                            int result = JOptionPane.showConfirmDialog(null,
                                    "El archivo ya existe. ¿Desea sobrescribirlo?", "Archivo existente",
                                    JOptionPane.YES_NO_CANCEL_OPTION);
                            if (result == JOptionPane.YES_OPTION) {
                                archivoValido = true;  // Sobrescribir archivo
                            } else if (result == JOptionPane.NO_OPTION) {
                                // Permitir al usuarioC elegir un nuevo nombre
                                continue;
                            } else {
                                // Cancelar la operación
                                JOptionPane.showMessageDialog(null, "Generación de reporte cancelada", "Cancelado", JOptionPane.WARNING_MESSAGE);
                                return;
                            }
                        } else {
                            archivoValido = true;
                        }
                    } else {
                        // Si el usuarioC cancela
                        JOptionPane.showMessageDialog(null, "Generación de reporte cancelada", "Cancelado", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                // Crear el documento PDF
                PdfWriter writer = new PdfWriter(fileToSave.getAbsolutePath());
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf, PageSize.A4); // Tamaño carta vertical

                // Agregar encabezado
                encabezado_documentos encabezado = new encabezado_documentos();
                encabezado.agregarEncabezado(document);
                
                // Agregar titulo
                document.add(new Paragraph("Reporte de Empleado")
                        .setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER));
                document.add(new Paragraph("\n"));

                // Crear tabla para datos y fotografía
                float[] columnWidths = {2, 1}; // Dos columnas: una para los datos, otra para la imagen
                Table table = new Table(UnitValue.createPercentArray(columnWidths));
                table.setWidth(UnitValue.createPercentValue(100)); // La tabla ocupará el ancho completo del documento

                // Columna izquierda: datos del empleado
                Cell datosEmpleado = new Cell();
                datosEmpleado.add(new Paragraph("ID del empleado:           " + idEmpleado));
                datosEmpleado.add(new Paragraph("Identidad:                 " + identidad));
                datosEmpleado.add(new Paragraph("Nombres:                   " + nombres));
                datosEmpleado.add(new Paragraph("Apellidos:                 " + apellidos));
                datosEmpleado.add(new Paragraph("Fecha de nacimiento:       " + fechaNacimiento));
                datosEmpleado.add(new Paragraph("Sexo:                      " + sexo));
                datosEmpleado.add(new Paragraph("Estado civil:              " + estadoCivil));
                datosEmpleado.add(new Paragraph("Teléfono:                  " + telefono));
                datosEmpleado.add(new Paragraph("Correo:                    " + correo));
                datosEmpleado.add(new Paragraph("Cargo:                     " + cargo));
                datosEmpleado.add(new Paragraph("Área:                      " + area));
                datosEmpleado.add(new Paragraph("Fecha de inicio:           " + fechaInicio));
                datosEmpleado.add(new Paragraph("Fecha de renuncia:         " + fechaRenuncia));
                datosEmpleado.setBorder(null); // Quitar bordes de la celda
                table.addCell(datosEmpleado);

                // Columna derecha: fotografía del empleado
                if (rutaFoto != null && !rutaFoto.isEmpty()) {
                    try {
                        ImageData imageData = ImageDataFactory.create(rutaFoto);
                        Image image = new Image(imageData);
                        image.setWidth(100);
                        image.setHeight(100);
                        Cell fotoEmpleado = new Cell().add(image);
                        fotoEmpleado.setBorder(null); // Quitar bordes de la celda
                        fotoEmpleado.setTextAlignment(TextAlignment.CENTER); // Alinear la foto al centro de la celda
                        table.addCell(fotoEmpleado);
                    } catch (Exception ex) {
                        table.addCell(new Paragraph("Fotografía no disponible"));
                    }
                } else {
                    table.addCell(new Paragraph("Fotografía no disponible"));
                }

                // Agregar la tabla al documento
                document.add(table);

                // Cerrar el documento
                document.close();
                JOptionPane.showMessageDialog(null, "Reporte generado correctamente en:\n" + fileToSave.getAbsolutePath(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró al empleado con el ID: " + idEmpleado, "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            conex.desconectar(null);
        }
    }
}
