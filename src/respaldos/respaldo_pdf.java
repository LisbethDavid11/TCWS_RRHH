package respaldos;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import reportes.encabezado_documentos;

public class respaldo_pdf {

	 public void generarReporteCompleto() {
	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setDialogTitle("Guardar respaldo en PDF");

	        LocalDate fechaActual = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        String fechaFormateada = fechaActual.format(formatter);
	        fileChooser.setSelectedFile(new File("Respaldo_" + fechaFormateada + ".pdf"));

	        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
	            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
	            try {
	                // Crear PDF
	                PdfWriter writer = new PdfWriter(ruta);
	                PdfDocument pdfDoc = new PdfDocument(writer);
	                pdfDoc.setDefaultPageSize(PageSize.LEGAL.rotate());
	                Document document = new Document(pdfDoc);
	                document.setMargins(72, 72, 72, 72); // Márgenes de 2.54 cm

	                // Encabezado solo en la primera página
	                encabezado_documentos encabezado = new encabezado_documentos();
	                encabezado.agregarEncabezado(document);
	                
	             // Agregar título
	                document.add(new Paragraph("Respaldo general del Sistema EPAV")
	                        .setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER));
	             

	                // Agregar numeración de páginas
	                agregarNumeracionPaginas(pdfDoc);

	                // Tablas existentes en la base de datos
	                List<String> tablas = Arrays.asList("areas", "cargos", "empleados", "incapacidad_laboral",
	                        "permisos_ausencia_laboral", "roles_usuarios", "usuarios", "vacaciones");

	                // Conexión a la base de datos
	                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tcws1", "root", "");

	                for (String nombreTabla : tablas) {
	                    document.add(new Paragraph("\n"));

	                    // Agregar título de la tabla
	                    agregarTituloTabla(document, nombreTabla);

	                    // Obtener datos de la tabla
	                    String query = "SELECT * FROM " + nombreTabla;
	                    PreparedStatement ps = con.prepareStatement(query);
	                    ResultSet rs = ps.executeQuery();
	                    ResultSetMetaData rsmd = rs.getMetaData();
	                    int columnas = rsmd.getColumnCount();

	                    // Configuración de la tabla
	                    float[] anchoColumnas = ajustarAnchoColumnas(nombreTabla, columnas);
	                    Table tablaPDF = new Table(UnitValue.createPercentArray(anchoColumnas)).useAllAvailableWidth();

	                    // Encabezados de tabla
	                    for (int i = 1; i <= columnas; i++) {
	                        String nombreColumna = rsmd.getColumnName(i).toLowerCase().contains("id_") ? "No." : rsmd.getColumnName(i).replace("_", "\n");
	                        tablaPDF.addHeaderCell(new Cell().add(new Paragraph(nombreColumna)
	                                .setFontSize(7)
	                                .setBold()
	                                .setTextAlignment(TextAlignment.CENTER)));
	                    }

	                    // Llenar filas con contenido
	                    while (rs.next()) {
	                        for (int i = 1; i <= columnas; i++) {
	                            String nombreColumna = rsmd.getColumnName(i);
	                            String valor = rs.getString(i);

	                            if (nombreColumna.contains("permisos_")) {
	                                valor = ("1".equals(valor)) ? "Si" : "No";
	                            } else if (nombreColumna.contains("hora") && "00:00:00".equals(valor)) {
	                                valor = "";
	                            }

	                            if (nombreColumna.equals("fotografia_empleado") && valor != null && !valor.isEmpty()) {
	                                try {
	                                    ImageData imageData = ImageDataFactory.create(valor);
	                                    Image img = new Image(imageData).scaleAbsolute(40, 40);
	                                    tablaPDF.addCell(new Cell().add(img).setTextAlignment(TextAlignment.CENTER));
	                                } catch (Exception ex) {
	                                    tablaPDF.addCell(new Cell().add(new Paragraph("N/A")
	                                            .setFontSize(7).setTextAlignment(TextAlignment.CENTER)));
	                                }
	                            } else {
	                                tablaPDF.addCell(new Cell().add(new Paragraph(valor != null ? dividirTexto(valor, 25) : "N/A")
	                                        .setFontSize(7).setTextAlignment(TextAlignment.CENTER)));
	                            }
	                        }
	                    }
	                    document.add(tablaPDF);
	                }

	                document.close();
	                con.close();

	                // Abrir PDF automáticamente
	                Desktop.getDesktop().open(new File(ruta));
	                JOptionPane.showMessageDialog(null, "Respaldo generado exitosamente");
	            } catch (Exception e) {
	                e.printStackTrace();
	                JOptionPane.showMessageDialog(null, "Error al generar el respaldo");
	            }
	        }
	    }

	    private void agregarTituloTabla(Document document, String nombreTabla) {
	        Paragraph titulo = new Paragraph("Tabla: " + nombreTabla)
	                .setBold()
	                .setUnderline()
	                .setFontSize(12)
	                .setTextAlignment(TextAlignment.CENTER);
	        document.add(titulo);
	        document.add(new Paragraph("\n"));
	    }

	    private String dividirTexto(String texto, int longitud) {
	        StringBuilder sb = new StringBuilder();
	        int index = 0;
	        while (index < texto.length()) {
	            sb.append(texto, index, Math.min(index + longitud, texto.length())).append("\n");
	            index += longitud;
	        }
	        return sb.toString();
	    }

	    private float[] ajustarAnchoColumnas(String tabla, int columnas) {
	        float[] ancho = new float[columnas];
	        Arrays.fill(ancho, 1f); // Asignar ancho proporcional
	        ancho[0] = 0.8f;        // Ajustar para la columna "No."
	        return ancho;
	    }

	    private void agregarNumeracionPaginas(PdfDocument pdfDoc) throws IOException {
	        int totalPaginas = pdfDoc.getNumberOfPages();
	        for (int i = 1; i <= totalPaginas; i++) {
	            PdfPage page = pdfDoc.getPage(i);
	            PdfCanvas canvas = new PdfCanvas(page);
	            canvas.beginText()
	                    .setFontAndSize(PdfFontFactory.createFont(), 9)
	                    .moveText(pdfDoc.getDefaultPageSize().getWidth() - 50, 30)
	                    .showText(String.format("Página %d", i))
	                    .endText();
	        }
	    }
}
