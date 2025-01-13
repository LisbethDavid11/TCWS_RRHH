package reportes;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

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
import consultas.consultas_areas;
import consultas.consultas_cargos;
import principal.menu_principal;

@SuppressWarnings("serial")
public class reporte_empleado_especial extends JFrame{
	
	public JComboBox<String> cbxcivil;
	public JComboBox<String> cbxsexo;
	public JComboBox<String> cbxrenuncia;
	public JComboBox<String> cbxcargo;
	public JComboBox<String> cbxarea;
	public JButton btncomprobante;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public reporte_empleado_especial() {
	getContentPane().setBackground(new Color(255, 255, 255));
	setType(Type.UTILITY);
	setResizable(false);
	getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 14));
	setBackground(Color.WHITE);
	setForeground(Color.BLACK);
	setBounds(100, 100, 1050, 630);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	getContentPane().setLayout(null);
	getContentPane().setLayout(null);
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	addWindowListener(new java.awt.event.WindowAdapter() {
		@Override
		public void windowClosing(java.awt.event.WindowEvent evt) {
			cerrar_ventana();
		}
		});
	
	JPanel panel_datos = new JPanel();
	panel_datos.setLayout(null);
	panel_datos.setBackground(SystemColor.menu);
	panel_datos.setBounds(32, 70, 975, 498);
	getContentPane().add(panel_datos);
	
	JLabel lblSexo = new JLabel("Sexo");
	lblSexo.setFont(new Font("Tahoma", Font.BOLD, 15));
	lblSexo.setBounds(48, 89, 166, 25);
	panel_datos.add(lblSexo);
	
	cbxsexo = new JComboBox<String>();
	cbxsexo.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino", "Otro"}));
	cbxsexo.setSelectedIndex(-1);
	cbxsexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
	cbxsexo.setBounds(47, 113, 217, 33);
	cbxsexo.setSelectedIndex(-1);;
	panel_datos.add(cbxsexo);
	
	JLabel lblDatosDel_1 = new JLabel("_______ Filtros de búsqueda para generar el reporte_________________________________________________________________");
	lblDatosDel_1.setHorizontalAlignment(SwingConstants.LEFT);
	lblDatosDel_1.setForeground(Color.GRAY);
	lblDatosDel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
	lblDatosDel_1.setBounds(31, 29, 919, 28);
	panel_datos.add(lblDatosDel_1);
	
	JLabel lblEstadoCivil = new JLabel("Estado civil");
	lblEstadoCivil.setFont(new Font("Tahoma", Font.BOLD, 15));
	lblEstadoCivil.setBounds(49, 201, 166, 25);
	panel_datos.add(lblEstadoCivil);
	
	cbxcivil = new JComboBox<String>();
	cbxcivil.setModel(new DefaultComboBoxModel(new String[] {"Soltero(a)", "Casado(a)", "Viudo(a)"}));
	cbxcivil.setSelectedIndex(-1);
	cbxcivil.setFont(new Font("Tahoma", Font.PLAIN, 14));
	cbxcivil.setBounds(48, 225, 217, 33);
	panel_datos.add(cbxcivil);
	
	JLabel lblCargo = new JLabel("Cargo");
	lblCargo.setFont(new Font("Tahoma", Font.BOLD, 15));
	lblCargo.setBounds(362, 89, 166, 25);
	panel_datos.add(lblCargo);
	
	cbxcargo = new JComboBox<String>();
	cbxcargo.setSelectedIndex(-1);
	cbxcargo.setFont(new Font("Tahoma", Font.PLAIN, 14));
	cbxcargo.setBounds(361, 113, 217, 33);
	cbxcargo.setSelectedIndex(-1);
	panel_datos.add(cbxcargo);
	
	JLabel lblrea = new JLabel("Área");
	lblrea.setFont(new Font("Tahoma", Font.BOLD, 15));
	lblrea.setBounds(362, 201, 166, 25);
	panel_datos.add(lblrea);
	
	cbxarea = new JComboBox<String>();
	cbxarea.setSelectedIndex(-1);
	cbxarea.setFont(new Font("Tahoma", Font.PLAIN, 14));
	cbxarea.setBounds(361, 225, 217, 33);
	panel_datos.add(cbxarea);
	
	JLabel lblRenunciaLaboral = new JLabel("Renuncia laboral");
	lblRenunciaLaboral.setFont(new Font("Tahoma", Font.BOLD, 15));
	lblRenunciaLaboral.setBounds(673, 89, 166, 25);
	panel_datos.add(lblRenunciaLaboral);
	
	cbxrenuncia = new JComboBox<String>();
	cbxrenuncia.setModel(new DefaultComboBoxModel(new String[] {"Si", "No"}));
	cbxrenuncia.setSelectedIndex(-1);
	cbxrenuncia.setFont(new Font("Tahoma", Font.PLAIN, 14));
	cbxrenuncia.setBounds(672, 113, 217, 33);
	panel_datos.add(cbxrenuncia);
	
	JTextArea txtrNota = new JTextArea();
	txtrNota.setForeground(Color.GRAY);
	txtrNota.setToolTipText("");
	txtrNota.setText("Nota: puede aplicar dos o más filtros de las listas a la vez, teniendo en cuenta "
			+ "que deberán existir los registros que espera en la \r\ntabla de Empleados para poder generar el reporte.");
	txtrNota.setFont(new Font("Segoe UI", Font.ITALIC, 15));
	txtrNota.setEditable(false);
	txtrNota.setBackground(SystemColor.control);
	txtrNota.setBounds(48, 398, 860, 55);
	panel_datos.add(txtrNota);
	
	btncomprobante = new JButton("Comprobante");
	btncomprobante.setToolTipText("Generar comprobante");
	btncomprobante.setFont(new Font("Tahoma", Font.BOLD, 10));
	btncomprobante.setBackground(UIManager.getColor("Button.highlight"));
	btncomprobante.setBounds(718, 233, 121, 25);
	panel_datos.add(btncomprobante);
	
	btncomprobante.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        if (cbxsexo.getSelectedIndex() == -1 && cbxcargo.getSelectedIndex() == -1 
	            && cbxarea.getSelectedIndex() == -1 && cbxrenuncia.getSelectedIndex() == -1 
	            && cbxcivil.getSelectedIndex() == -1) {
	            
	            JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un filtro para generar el reporte.", 
	                                          "Advertencia", JOptionPane.WARNING_MESSAGE);
	            return;
	        }
	        
	        StringBuilder query = new StringBuilder("SELECT * FROM empleados WHERE 1=1");
	        
	        if (cbxsexo.getSelectedIndex() != -1) {
	            String sexo = cbxsexo.getSelectedItem().toString().trim();
	            if (!sexo.isEmpty()) {
	                query.append(" AND sexo_empleado = '").append(sexo).append("'");
	            }
	        }
	        if (cbxcargo.getSelectedIndex() != -1) {
	            String cargo = cbxcargo.getSelectedItem().toString().trim();
	            if (!cargo.isEmpty()) {
	                query.append(" AND cargo_empleado = '").append(cargo).append("'");
	            }
	        }
	        if (cbxarea.getSelectedIndex() != -1) {
	            String area = cbxarea.getSelectedItem().toString().trim();
	            if (!area.isEmpty()) {
	                query.append(" AND area_empleado = '").append(area).append("'");
	            }
	        }
	        if (cbxrenuncia.getSelectedIndex() != -1) {
	            String renuncia = cbxrenuncia.getSelectedItem().toString().equals("Si") ? "renuncia_empleado IS NOT NULL" : "renuncia_empleado IS NULL";
	            query.append(" AND ").append(renuncia);
	        }
	        if (cbxcivil.getSelectedIndex() != -1) {
	            String civil = cbxcivil.getSelectedItem().toString().trim();
	            if (!civil.isEmpty()) {
	                query.append(" AND civil_empleado = '").append(civil).append("'");
	            }
	        }

	        System.out.println("Consulta generada: " + query.toString());
	        generarReporte(query.toString());
	    }
	});


	
	
	JLabel lblImpresinDeReportes = new JLabel("REPORTES ESPECIALES DE EMPLEADOS");
	lblImpresinDeReportes.setHorizontalAlignment(SwingConstants.LEFT);
	lblImpresinDeReportes.setFont(new Font("Segoe UI", Font.BOLD, 26));
	lblImpresinDeReportes.setBackground(new Color(255, 153, 0));
	lblImpresinDeReportes.setBounds(32, 24, 749, 36);
	getContentPane().add(lblImpresinDeReportes);
	
	JButton btnregresar = new JButton("Regresar");
	btnregresar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			menu_principal menu= new menu_principal();
			menu.setVisible(true);
			menu.setLocationRelativeTo(null);
			dispose();
			
			}
	});
	btnregresar.setToolTipText("Regresar a la tabla");
	btnregresar.setFont(new Font("Tahoma", Font.BOLD, 10));
	btnregresar.setBackground(UIManager.getColor("Button.highlight"));
	btnregresar.setBounds(917, 24, 90, 23);
	getContentPane().add(btnregresar);
	
	 cargarCargosEnComboBox();
	 cargarAreasEnComboBox();
	
	}
	
	
	
	public void generarReporte(String query) {
	    conexion conex = new conexion();

	    try {
	        System.out.println("Ejecutando consulta: " + query); // Depuración

	        Statement estatuto = conex.conectar().createStatement();
	        ResultSet rs = estatuto.executeQuery(query);

	        if (!rs.isBeforeFirst()) { // Verifica si hay registros
	            JOptionPane.showMessageDialog(null, "No se encontraron registros para los filtros seleccionados.",
	                                          "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
	            return;
	        }

	        // Continuar con la generación del PDF
	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setDialogTitle("Guardar reporte especial de empleados");

	        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PDF", "pdf");
	        fileChooser.setFileFilter(filter);

	        LocalDate fechaActual = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        String fechaFormateada = fechaActual.format(formatter);
	        String nombreArchivo = "Reporte_especial_empleados_" + fechaFormateada + ".pdf";
	        fileChooser.setSelectedFile(new File(nombreArchivo));

	        int userSelection = fileChooser.showSaveDialog(null);

	        if (userSelection == JFileChooser.APPROVE_OPTION) {
	            File fileToSave = fileChooser.getSelectedFile();
	            String dest = fileToSave.getAbsolutePath();

	            if (!dest.toLowerCase().endsWith(".pdf")) {
	                dest += ".pdf";
	            }

	            PdfWriter writer = new PdfWriter(dest);
	            PdfDocument pdf = new PdfDocument(writer);
	            Document document = new Document(pdf, PageSize.LEGAL.rotate());

	            encabezado_documentos encabezado = new encabezado_documentos();
	            encabezado.agregarEncabezado(document);

	            document.add(new Paragraph("Reporte especial de Empleados")
	                .setBold().setFontSize(16).setTextAlignment(TextAlignment.CENTER));
	            document.add(new Paragraph("\n"));

	            float[] columnWidths = {0.5f, 0.5f, 1.5f, 1.5f, 1.5f, 1f, 1f, 1f, 1.3f, 1.3f, 1f, 1.2f};
	            Table table = new Table(UnitValue.createPercentArray(columnWidths)).useAllAvailableWidth();

	            table.addHeaderCell(new Cell().add(new Paragraph("No.").setBold().setFontSize(10)));
	            table.addHeaderCell(new Cell().add(new Paragraph("Id marcada").setBold().setFontSize(10)));
	            table.addHeaderCell(new Cell().add(new Paragraph("Identidad").setBold().setFontSize(10)));
	            table.addHeaderCell(new Cell().add(new Paragraph("Nombres").setBold().setFontSize(10)));
	            table.addHeaderCell(new Cell().add(new Paragraph("Apellidos").setBold().setFontSize(10)));
	            table.addHeaderCell(new Cell().add(new Paragraph("Fecha de nacimiento").setBold().setFontSize(10)));
	            table.addHeaderCell(new Cell().add(new Paragraph("Sexo").setBold().setFontSize(10)));
	            table.addHeaderCell(new Cell().add(new Paragraph("Estado civil").setBold().setFontSize(10)));
	            table.addHeaderCell(new Cell().add(new Paragraph("Teléfono").setBold().setFontSize(10)));
	            table.addHeaderCell(new Cell().add(new Paragraph("Cargo").setBold().setFontSize(10)));
	            table.addHeaderCell(new Cell().add(new Paragraph("Área").setBold().setFontSize(10)));
	            table.addHeaderCell(new Cell().add(new Paragraph("Fotografía").setBold().setFontSize(10)));

	            int contador = 1;

	            while (rs.next()) {
	                table.addCell(new Cell().add(new Paragraph(String.valueOf(contador++)).setFontSize(9)));
	                table.addCell(new Cell().add(new Paragraph(rs.getString("id")).setFontSize(9)));
	                table.addCell(new Cell().add(new Paragraph(rs.getString("identidad_empleado")).setFontSize(9)));
	                table.addCell(new Cell().add(new Paragraph(rs.getString("nombres_empleado")).setFontSize(9)));
	                table.addCell(new Cell().add(new Paragraph(rs.getString("apellidos_empleado")).setFontSize(9)));
	                table.addCell(new Cell().add(new Paragraph(rs.getString("nacimiento_empleado")).setFontSize(9)));
	                table.addCell(new Cell().add(new Paragraph(rs.getString("sexo_empleado")).setFontSize(9)));
	                table.addCell(new Cell().add(new Paragraph(rs.getString("civil_empleado")).setFontSize(9)));
	                table.addCell(new Cell().add(new Paragraph(rs.getString("tel_empleado")).setFontSize(9)));
	                table.addCell(new Cell().add(new Paragraph(rs.getString("cargo_empleado")).setFontSize(9)));
	                table.addCell(new Cell().add(new Paragraph(rs.getString("area_empleado")).setFontSize(9)));

	                String rutaFoto = rs.getString("fotografia_empleado");
	                if (rutaFoto != null && !rutaFoto.isEmpty()) {
	                    try {
	                        ImageData imageData = ImageDataFactory.create(rutaFoto);
	                        Image image = new Image(imageData);
	                        image.setMaxWidth(30);
	                        image.setMaxHeight(30);
	                        table.addCell(new Cell().add(image).setPadding(2));
	                    } catch (Exception ex) {
	                        table.addCell(new Cell().add(new Paragraph("N/A").setFontSize(9)));
	                    }
	                } else {
	                    table.addCell(new Cell().add(new Paragraph("N/A").setFontSize(9)));
	                }
	            }

	            document.add(table);
	            document.close();
	            

	            JOptionPane.showMessageDialog(null, "Reporte guardado con éxito en:\n" + dest, "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        }

	    } catch (Exception ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al generar el reporte: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    } finally {
	        conex.desconectar(null);
	    }
	}

	
	private void cerrar_ventana() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	
	private void cargarCargosEnComboBox() {
	    consultas_cargos consultas = new consultas_cargos();
	    List<String> cargos = consultas.obtenerCargos();
	    cbxcargo.removeAllItems();
	    cbxcargo.addItem(" ");
	    
	    for (String cargo : cargos) {
	        cbxcargo.addItem(cargo);
	    }
	    
	    cbxcargo.setSelectedIndex(0);
	}

	

	private void cargarAreasEnComboBox() {
	    consultas_areas c = new consultas_areas();
	    List<String> areas = c.obtenerAreas();
	    cbxarea.removeAllItems();
	    cbxarea.addItem(" ");
	    
	    for (String area : areas) {
	        cbxarea.addItem(area);
	    }
	    
	    cbxarea.setSelectedIndex(0);
	}
	
	


}
