package reportes;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;


public class encabezado_documentos {
	
    public void agregarEncabezado(Document document) {
        try {
            
            String logoPath = "src/imagenes/logoTC.jpeg"; 
            Image img = new Image(ImageDataFactory.create(logoPath));
            img.setWidth(90);
            img.setHeight(90);

            // Obtener el tamaño de la página actual
            float pageWidth = document.getPdfDocument().getDefaultPageSize().getWidth();
            float pageHeight = document.getPdfDocument().getDefaultPageSize().getHeight();

            if (pageWidth > pageHeight) { // Si la página es horizontal
                img.setFixedPosition(pageWidth - 130, pageHeight - 110); 
            } else { // Si la página es vertical
                img.setFixedPosition(pageWidth - 120, pageHeight - 110);
            }

            document.add(img);

            Paragraph header = new Paragraph("Centro Educativo Cristiano Bilingüe \n “El Mundo de los Niños” \n TCWS/TCWHS")
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setBold();

            Paragraph address = new Paragraph("Col. La Ceibita, \n Danli, El Paraiso, Honduras")
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER);

            Paragraph phone = new Paragraph("Teléfono: 9671-0574")
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER);
            
            Paragraph correo = new Paragraph("Correo: info@tcws.edu.hn")
                    .setFontSize(12)
                    .setTextAlignment(TextAlignment.CENTER);

            document.add(header);
            document.add(address);
            document.add(phone);
            document.add(correo);
            document.add(new Paragraph("\n"));  // Espacio extra después del encabezado

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


