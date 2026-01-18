package respaldos;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.kernel.colors.ColorConstants;

import reportes.encabezado_documentos;
import conexion.conexion;

public class respaldo_pdf {

    public void generarReporteCompleto() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar respaldo en PDF");
        
        // Filtro para archivos PDF
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PDF", "pdf");
        fileChooser.setFileFilter(filter);

        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
        String fechaFormateada = fechaActual.format(formatter);
        fileChooser.setSelectedFile(new File("Respaldo_SRRHH_" + fechaFormateada + ".pdf"));

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            
            // Asegurar que el archivo tenga extensión .pdf
            if (!fileToSave.getAbsolutePath().endsWith(".pdf")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".pdf");
            }
            
            String ruta = fileToSave.getAbsolutePath();
            
            try {
                // Crear PDF
                PdfWriter writer = new PdfWriter(ruta);
                PdfDocument pdfDoc = new PdfDocument(writer);
                pdfDoc.setDefaultPageSize(PageSize.LEGAL.rotate());
                Document document = new Document(pdfDoc);
                document.setMargins(50, 50, 50, 50);

                // Encabezado solo en la primera página
                encabezado_documentos encabezado = new encabezado_documentos();
                encabezado.agregarEncabezado(document);
                
                // Agregar título principal
                document.add(new Paragraph("Respaldo General del Sistema Recursos Humanos")
                        .setBold()
                        .setFontSize(16)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginBottom(5));
                
                // Agregar fecha y hora de generación
               /* DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                document.add(new Paragraph("Fecha de generación: " + fechaActual.format(displayFormatter))
                        .setFontSize(10)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginBottom(15));*/

                // Tablas ordenadas según la estructura reorganizada
                List<String> tablas = Arrays.asList(
                    // Catálogos
                    "cargos",
                    "areas",
                    // Seguridad
                    "usuarios",
                    "roles_usuarios",
                    // Empleados
                    "empleados",
                    // Asistencia
                    "asistencia_diaria",
                    "permisos_ausencia_hora",
                    "permisos_ausencia_laboral",
                    "injustificadas",
                    // Vacaciones e Incapacidades
                    "vacaciones",
                    "incapacidad_laboral",
                    // Gestión Disciplinaria
                    "memorandum"
                );

                // Conexión a la base de datos usando tu clase de conexión
                conexion conex = new conexion();
                Connection con = conex.conectar();

                int tablasProcesadas = 0;
                
                for (String nombreTabla : tablas) {
                    if (!tablaExiste(con, nombreTabla)) {
                        continue; // Saltar si la tabla no existe
                    }
                    
                    // Agregar separador entre tablas
                    if (tablasProcesadas > 0) {
                        document.add(new Paragraph("\n"));
                    }

                    // Agregar título de la sección según la tabla
                    agregarSeccionTabla(document, nombreTabla);

                    // Primero contar registros con una consulta separada
                    String countQuery = "SELECT COUNT(*) FROM " + nombreTabla;
                    PreparedStatement psCount = con.prepareStatement(countQuery);
                    ResultSet rsCount = psCount.executeQuery();
                    int totalRegistros = 0;
                    if (rsCount.next()) {
                        totalRegistros = rsCount.getInt(1);
                    }
                    rsCount.close();
                    psCount.close();
                    
                    // Mostrar información de la tabla
                    document.add(new Paragraph(String.format("Total de registros: %d", totalRegistros))
                            .setFontSize(9)
                            .setItalic()
                            .setMarginBottom(5));

                    // Si no hay registros, mostrar mensaje
                    if (totalRegistros == 0) {
                        document.add(new Paragraph("No hay registros en esta tabla")
                                .setFontSize(9)
                                .setItalic()
                                .setTextAlignment(TextAlignment.CENTER)
                                .setMarginBottom(10));
                        tablasProcesadas++;
                        continue;
                    }
                    
                    // Ahora obtener los datos de la tabla
                    String query = "SELECT * FROM " + nombreTabla;
                    PreparedStatement ps = con.prepareStatement(query);
                    ResultSet rs = ps.executeQuery();
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnas = rsmd.getColumnCount();

                    // Configuración de la tabla
                    float[] anchoColumnas = ajustarAnchoColumnas(nombreTabla, columnas);
                    Table tablaPDF = new Table(UnitValue.createPercentArray(anchoColumnas))
                            .useAllAvailableWidth()
                            .setMarginBottom(10);

                    // Encabezados de tabla con estilo
                    for (int i = 1; i <= columnas; i++) {
                        String nombreColumna = formatearNombreColumna(rsmd.getColumnName(i));
                        Cell headerCell = new Cell()
                                .add(new Paragraph(nombreColumna)
                                        .setFontSize(7)
                                        .setBold()
                                        .setTextAlignment(TextAlignment.CENTER))
                                .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                                .setBorder(new SolidBorder(ColorConstants.BLACK, 1));
                        tablaPDF.addHeaderCell(headerCell);
                    }

                    // Llenar filas con contenido
                    while (rs.next()) {
                        for (int i = 1; i <= columnas; i++) {
                            String nombreColumna = rsmd.getColumnName(i);
                            String valor = rs.getString(i);

                            Cell dataCell = new Cell()
                                    .setBorder(new SolidBorder(ColorConstants.GRAY, 0.5f));

                            // Manejo especial de columnas según tipo
                            if (nombreColumna.contains("permisos_")) {
                                valor = ("1".equals(valor)) ? "Sí" : "No";
                                dataCell.add(new Paragraph(valor)
                                        .setFontSize(7)
                                        .setTextAlignment(TextAlignment.CENTER));
                            } 
                            else if (nombreColumna.contains("asistio")) {
                                valor = ("1".equals(valor)) ? "Sí" : "No";
                                dataCell.add(new Paragraph(valor)
                                        .setFontSize(7)
                                        .setTextAlignment(TextAlignment.CENTER));
                            }
                            else if (nombreColumna.contains("hora") && "00:00:00".equals(valor)) {
                                dataCell.add(new Paragraph("")
                                        .setFontSize(7)
                                        .setTextAlignment(TextAlignment.CENTER));
                            } 
                            else if (nombreColumna.equals("fotografia_empleado") && valor != null && !valor.isEmpty()) {
                                try {
                                    ImageData imageData = ImageDataFactory.create(valor);
                                    com.itextpdf.layout.element.Image img = new com.itextpdf.layout.element.Image(imageData)
                                            .scaleAbsolute(40, 40);
                                    dataCell.add(img).setTextAlignment(TextAlignment.CENTER);
                                } catch (Exception ex) {
                                    dataCell.add(new Paragraph("Sin foto")
                                            .setFontSize(7)
                                            .setTextAlignment(TextAlignment.CENTER));
                                }
                            } 
                            else {
                                String textoMostrar = valor != null ? valor : "N/A";
                                if (textoMostrar.length() > 50) {
                                    textoMostrar = dividirTexto(textoMostrar, 40);
                                }
                                dataCell.add(new Paragraph(textoMostrar)
                                        .setFontSize(7)
                                        .setTextAlignment(TextAlignment.LEFT));
                            }
                            
                            tablaPDF.addCell(dataCell);
                        }
                    }
                    
                    document.add(tablaPDF);
                    tablasProcesadas++;
                }

                // Cerrar el documento primero
                document.close();
                
                // Agregar numeración de páginas después de cerrar el documento
                agregarNumeracionPaginas(pdfDoc);
                
                // Cerrar el PDF
                pdfDoc.close();
                conex.desconectar(con);

                // Abrir PDF automáticamente
                try {
                    Desktop.getDesktop().open(fileToSave);
                } catch (Exception e) {
                    // Si no se puede abrir automáticamente, solo informar
                }
                
                JOptionPane.showMessageDialog(null,
                        "Respaldo en PDF generado exitosamente\n\n" +
                        "Ubicación: " + ruta + "\n" +
                        "Tablas procesadas: " + tablasProcesadas + "\n" +
                        "Tamaño: " + String.format("%.2f KB", fileToSave.length() / 1024.0),
                        "Respaldo Completado",
                        JOptionPane.INFORMATION_MESSAGE);
                        
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Error al generar el respaldo en PDF:\n\n" + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean tablaExiste(Connection con, String nombreTabla) {
        try {
            PreparedStatement ps = con.prepareStatement("SHOW TABLES LIKE ?");
            ps.setString(1, nombreTabla);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }

    private void agregarSeccionTabla(Document document, String nombreTabla) {
        String seccion = "";
        
        // Determinar la sección según la tabla
        if (nombreTabla.equals("cargos") || nombreTabla.equals("areas")) {
            seccion = "CATÁLOGOS";
        } else if (nombreTabla.equals("usuarios") || nombreTabla.equals("roles_usuarios")) {
            seccion = "SEGURIDAD Y USUARIOS";
        } else if (nombreTabla.equals("empleados")) {
            seccion = "EMPLEADOS";
        } else if (nombreTabla.equals("asistencia_diaria") || nombreTabla.equals("permisos_ausencia_hora") 
                || nombreTabla.equals("permisos_ausencia_laboral") || nombreTabla.equals("injustificadas")) {
            seccion = "ASISTENCIA Y PERMISOS";
        } else if (nombreTabla.equals("vacaciones") || nombreTabla.equals("incapacidad_laboral")) {
            seccion = "VACACIONES E INCAPACIDADES";
        } else if (nombreTabla.equals("memorandum")) {
            seccion = "GESTIÓN DISCIPLINARIA";
        }
        
        // Solo agregar el título de sección si es relevante
        if (!seccion.isEmpty()) {
            document.add(new Paragraph(seccion)
                    .setBold()
                    .setFontSize(11)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setMarginTop(10)
                    .setMarginBottom(5));
        }
        
        // Agregar título de la tabla
        Paragraph titulo = new Paragraph("Tabla: " + formatearNombreTabla(nombreTabla))
                .setBold()
                .setUnderline()
                .setFontSize(10)
                .setTextAlignment(TextAlignment.LEFT)
                .setMarginBottom(5);
        document.add(titulo);
    }

    private String formatearNombreTabla(String nombreTabla) {
        // Convertir nombre de tabla a formato legible
        return nombreTabla.replace("_", " ").toUpperCase();
    }

    private String formatearNombreColumna(String nombreColumna) {
        // Formatear nombres de columnas
        if (nombreColumna.toLowerCase().contains("id_") || nombreColumna.toLowerCase().equals("id")) {
            return "No.";
        }
        return nombreColumna.replace("_", "\n");
    }

    private String dividirTexto(String texto, int longitud) {
        if (texto == null || texto.length() <= longitud) {
            return texto;
        }
        
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (index < texto.length()) {
            int endIndex = Math.min(index + longitud, texto.length());
            sb.append(texto, index, endIndex);
            if (endIndex < texto.length()) {
                sb.append("\n");
            }
            index += longitud;
        }
        return sb.toString();
    }

    private float[] ajustarAnchoColumnas(String tabla, int columnas) {
        float[] ancho = new float[columnas];
        
        // Asegurar que la suma sea exactamente 1.0 (100%)
        if (tabla.equals("empleados")) {
            // Para empleados: distribución proporcional
            float anchoID = 0.4f;
            float anchoRestante = 1.0f - anchoID;
            float anchoPorColumna = anchoRestante / (columnas - 1);
            
            ancho[0] = anchoID;
            for (int i = 1; i < columnas; i++) {
                ancho[i] = anchoPorColumna;
            }
        } else if (tabla.equals("asistencia_diaria")) {
            float anchoID = 0.3f;
            float anchoRestante = 1.0f - anchoID;
            float anchoPorColumna = anchoRestante / (columnas - 1);
            
            ancho[0] = anchoID;
            for (int i = 1; i < columnas; i++) {
                ancho[i] = anchoPorColumna;
            }
        } else if (tabla.equals("roles_usuarios")) {
            // Para roles con muchas columnas booleanas
            float anchoID = 0.25f;
            float anchoRestante = 1.0f - anchoID;
            float anchoPorColumna = anchoRestante / (columnas - 1);
            
            ancho[0] = anchoID;
            for (int i = 1; i < columnas; i++) {
                ancho[i] = anchoPorColumna;
            }
        } else {
            // Configuración por defecto - distribución equitativa
            float anchoID = 0.3f;
            float anchoRestante = 1.0f - anchoID;
            float anchoPorColumna = anchoRestante / (columnas - 1);
            
            ancho[0] = anchoID;
            for (int i = 1; i < columnas; i++) {
                ancho[i] = anchoPorColumna;
            }
        }
        
        // Verificar que la suma sea exactamente 1.0
        float suma = 0;
        for (float a : ancho) {
            suma += a;
        }
        
        // Ajustar si hay diferencia de redondeo
        if (Math.abs(suma - 1.0f) > 0.001f) {
            ancho[columnas - 1] += (1.0f - suma);
        }
        
        return ancho;
    }

    private void agregarNumeracionPaginas(PdfDocument pdfDoc) {
        try {
            int totalPaginas = pdfDoc.getNumberOfPages();
            
            for (int i = 1; i <= totalPaginas; i++) {
                PdfPage page = pdfDoc.getPage(i);
                
                // Crear un nuevo canvas para la página
                PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);
                
                // Obtener el tamaño de la página
                float width = page.getPageSize().getWidth();
                float height = page.getPageSize().getHeight();
                
                // Agregar numeración en la parte inferior derecha
                canvas.beginText()
                        .setFontAndSize(PdfFontFactory.createFont(), 9)
                        .moveText(width - 100, 30)
                        .showText(String.format("Página %d de %d", i, totalPaginas))
                        .endText();
                        
                canvas.release();
            }
        } catch (Exception e) {
            // Si hay algún error al agregar numeración, continuar sin ella
            System.err.println("No se pudo agregar numeración de páginas: " + e.getMessage());
        }
    }
}