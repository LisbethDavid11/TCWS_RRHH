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
	
	
	@SuppressWarnings("unchecked")
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
	//cbxcargo.setModel(new DefaultComboBoxModel(new String[] {"Director general", "Director", "Gerente financiero", "Administrador", "Asistente ", "Cobros", "Enfermero", "Psicologo", "Supervisor ", "Consejero", "Docente", 
															//"Docente auxiliar", "Soporte técnico", "Marketing", "Aseo ", "Mantenimiento", "Conserje"}));
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
	cbxarea.setModel(new DefaultComboBoxModel(new String[] {"Administrativa", "Financiera", "Pre basica", "Primaria", "Secundaria", "Logistica", "Aseo", "Mantenimiento"}));
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
	btncomprobante.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    	
	        // Verificar si al menos un JComboBox tiene un valor seleccionado
	        if (cbxsexo.getSelectedIndex() == -1 && cbxcargo.getSelectedIndex() == -1 
	            && cbxarea.getSelectedIndex() == -1 && cbxrenuncia.getSelectedIndex() == -1 
	            && cbxcivil.getSelectedIndex() == -1) {
	            
	            JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un filtro para generar el reporte", 
	                                          "Advertencia", JOptionPane.WARNING_MESSAGE);
	            return;
	        }
	        
	        // Construir la consulta SQL según los filtros seleccionados
	        StringBuilder query = new StringBuilder("SELECT * FROM empleados WHERE renuncia_empleado IS NULL");

	        if (cbxsexo.getSelectedIndex() != -1) {
	            query.append(" AND sexo_empleado = '").append(cbxsexo.getSelectedItem().toString()).append("'");
	        }
	        if (cbxcargo.getSelectedIndex() != -1) {
	            query.append(" AND cargo_empleado = '").append(cbxcargo.getSelectedItem().toString()).append("'");
	        }
	        if (cbxarea.getSelectedIndex() != -1) {
	            query.append(" AND area_empleado = '").append(cbxarea.getSelectedItem().toString()).append("'");
	        }
	        if (cbxrenuncia.getSelectedIndex() != -1) {
	            String renuncia = cbxrenuncia.getSelectedItem().toString().equals("Si") ? "IS NOT NULL" : "IS NULL";
	            query.append(" AND renuncia_empleado ").append(renuncia);
	        }
	        if (cbxcivil.getSelectedIndex() != -1) {
	            query.append(" AND civil_empleado = '").append(cbxcivil.getSelectedItem().toString()).append("'");
	        }

	        // Aquí haces la conexión y la ejecución de la consulta, generando el reporte en PDF según los filtros
	        generarReporte(query.toString());
	    }
	});


	btncomprobante.setToolTipText("Generar comprobante");
	btncomprobante.setFont(new Font("Tahoma", Font.BOLD, 10));
	btncomprobante.setBackground(UIManager.getColor("Button.highlight"));
	btncomprobante.setBounds(718, 233, 121, 25);
	panel_datos.add(btncomprobante);
	
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
	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setDialogTitle("Guardar reporte especial de empleados");

	        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos PDF", "pdf");
	        fileChooser.setFileFilter(filter);

	        // Establecer el nombre predeterminado del archivo con la fecha actual
	        LocalDate fechaActual = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        String fechaFormateada = fechaActual.format(formatter);
	        String nombreArchivo = "Reporte_especial_empleados_" + fechaFormateada + ".pdf";
	        fileChooser.setSelectedFile(new File(nombreArchivo));

	        int userSelection = fileChooser.showSaveDialog(null);

	        if (userSelection == JFileChooser.APPROVE_OPTION) {
	            File fileToSave = fileChooser.getSelectedFile();
	            String dest = fileToSave.getAbsolutePath();

	            // Asegurarse de que la extensión sea .pdf
	            if (!dest.toLowerCase().endsWith(".pdf")) {
	                dest += ".pdf";
	                fileToSave = new File(dest);
	            }

	            // Verificar si el archivo ya existe
	            if (fileToSave.exists()) {
	                int overwriteOption = JOptionPane.showConfirmDialog(null,
	                        "El archivo ya existe. ¿Deseas reemplazarlo?",
	                        "Archivo existente",
	                        JOptionPane.YES_NO_OPTION);

	                if (overwriteOption != JOptionPane.YES_OPTION) {
	                    JOptionPane.showMessageDialog(null, "Por favor, elige otro nombre de archivo.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
	                    return;
	                }
	            }

	            // Proceder a generar el reporte si el archivo es válido
	            PdfWriter writer = new PdfWriter(dest);
	            PdfDocument pdf = new PdfDocument(writer);
	            Document document = new Document(pdf, PageSize.LEGAL.rotate());

	            // Agregar encabezado
	            encabezado_documentos encabezado = new encabezado_documentos();
	            encabezado.agregarEncabezado(document);

	            // Título del reporte
	            document.add(new Paragraph("Reporte especial de empleados")
	                .setBold().setFontSize(16).setTextAlignment(TextAlignment.CENTER));
	            document.add(new Paragraph("\n"));

	            // Ejecutar la consulta
	            Statement estatuto = conex.conectar().createStatement();
	            ResultSet rs = estatuto.executeQuery(query);

	            // Definir columnas de la tabla
	            float[] columnWidths = {0.5f, 0.5f, 1.5f, 1.5f, 1.5f, 1f, 1f, 1f, 1.3f, 1.3f, 1f, 1.2f};
	            Table table = new Table(UnitValue.createPercentArray(columnWidths)).useAllAvailableWidth();
	            table.addHeaderCell(new Cell().add(new Paragraph("No.").setBold().setFontSize(10)));
	            table.addHeaderCell(new Cell().add(new Paragraph("Id").setBold().setFontSize(10)));
	            table.addHeaderCell(new Cell().add(new Paragraph("Identidad").setBold().setFontSize(10)));
	            table.addHeaderCell(new Cell().add(new Paragraph("Nombres").setBold().setFontSize(10)));
	            table.addHeaderCell(new Cell().add(new Paragraph("Apellidos").setBold().setFontSize(10)));
	            table.addHeaderCell(new Cell().add(new Paragraph("Fecha de Nacimiento").setBold().setFontSize(10)));
	            table.addHeaderCell(new Cell().add(new Paragraph("Sexo").setBold().setFontSize(10)));
	            table.addHeaderCell(new Cell().add(new Paragraph("Estado Civil").setBold().setFontSize(10)));
	            table.addHeaderCell(new Cell().add(new Paragraph("Teléfono").setBold().setFontSize(10)));
	            table.addHeaderCell(new Cell().add(new Paragraph("Cargo").setBold().setFontSize(10)));
	            table.addHeaderCell(new Cell().add(new Paragraph("Área").setBold().setFontSize(10)));
	            table.addHeaderCell(new Cell().add(new Paragraph("Fotografía").setBold().setFontSize(10)));

	            // Iterar sobre los resultados y llenar la tabla
	            while (rs.next()) {
	            	table.addCell(new Cell().add(new Paragraph(rs.getString("id")).setFontSize(9)));
	                table.addCell(new Cell().add(new Paragraph(rs.getString("id_empleado")).setFontSize(9)));
	                table.addCell(new Cell().add(new Paragraph(rs.getString("identidad_empleado")).setFontSize(9)));
	                table.addCell(new Cell().add(new Paragraph(rs.getString("nombres_empleado")).setFontSize(9)));
	                table.addCell(new Cell().add(new Paragraph(rs.getString("apellidos_empleado")).setFontSize(9)));
	                table.addCell(new Cell().add(new Paragraph(rs.getString("nacimiento_empleado")).setFontSize(9)));
	                table.addCell(new Cell().add(new Paragraph(rs.getString("sexo_empleado")).setFontSize(9)));
	                table.addCell(new Cell().add(new Paragraph(rs.getString("civil_empleado")).setFontSize(9)));
	                table.addCell(new Cell().add(new Paragraph(rs.getString("tel_empleado")).setFontSize(9)));
	                table.addCell(new Cell().add(new Paragraph(rs.getString("cargo_empleado")).setFontSize(9)));
	                table.addCell(new Cell().add(new Paragraph(rs.getString("area_empleado")).setFontSize(9)));

	                // Agregar fotografía si existe
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
