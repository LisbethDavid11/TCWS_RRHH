package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import clases.empleado;
import clases.validaciones;
import consultas.consultas_empleado;


@SuppressWarnings("serial")
public class empleado_nuevo extends JFrame{
	public JTextField txtidentidad;
	public JTextField txtnombres;
	public JTextField txtapellidos;
	public JTextField txttel;
	public JTextField txtcorreo;
	public JTextField txtruta;
	public JTextField txtid_empleado;
	
	public JRadioButton buttonmasculino;
	public JRadioButton buttonfemenino;
	public JRadioButton buttonotro;
	public ButtonGroup grupoSexo;
	
	@SuppressWarnings("rawtypes")
	public JComboBox cbxcargo;
	@SuppressWarnings("rawtypes")
	public JComboBox cbxestado_civil;
	@SuppressWarnings("rawtypes")
	public JComboBox cbxarea;
	
	public JDateChooser fecha_nacimiento;
	public consultas_empleado consultas;
	public JDateChooser fecha_inicio;
	public JDateChooser fecha_renuncia;
	
	public JTextArea txadireccion;
	
	public JButton btnseleccionar_foto;
	public JButton btneliminar_foto;
	public JButton btnguardar;
	public JButton btnactualizar;
	public JButton btnlimpiar;
	public JButton btnregresar;
	public JLabel lblfoto;
	public Date fechaActual = new Date();
	public JTextField txtcuenta;
	public JPanel panel_datos;
	ImageIcon icono_fotografia = new ImageIcon("src/imagenes/camara.png");
	public JTextField txtid;
	

	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public empleado_nuevo() {
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
		
		panel_datos = new JPanel();
		panel_datos.setBackground(SystemColor.menu);
		panel_datos.setLayout(null);
		panel_datos.setBounds(25, 86, 985, 475);
		getContentPane().add(panel_datos);
		
		
        
		
		txtidentidad = new JTextField(15);
		txtidentidad.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        validaciones.validarIdentidad(e, txtidentidad);
		    }
		});

		txtidentidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtidentidad.setColumns(10);
		txtidentidad.setBounds(29, 123, 253, 33);
		panel_datos.add(txtidentidad);
		
		
		
		
		txtnombres = new JTextField();
		txtnombres.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
        	    validaciones.validarNombresyApellidos(e, txtnombres, 70);
        	}

		});
		txtnombres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtnombres.setColumns(10);
		txtnombres.setBounds(29, 190, 253, 33);
		panel_datos.add(txtnombres);
		
		txtapellidos = new JTextField();
		txtapellidos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
        	    validaciones.validarNombresyApellidos(e, txtapellidos, 70);
        	}

		});
		txtapellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtapellidos.setColumns(10);
		txtapellidos.setBounds(29, 260, 253, 33);
		panel_datos.add(txtapellidos);
		
		buttonmasculino = new JRadioButton("Masculino");
		buttonmasculino.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonmasculino.setBackground(SystemColor.menu);
		buttonmasculino.setBounds(329, 127, 99, 33);
		panel_datos.add(buttonmasculino);
		
		buttonfemenino = new JRadioButton("Femenino");
		buttonfemenino.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonfemenino.setBackground(SystemColor.menu);
		buttonfemenino.setBounds(429, 127, 95, 33);
		panel_datos.add(buttonfemenino);
		
		buttonotro = new JRadioButton("Otro");
		buttonotro.setBackground(SystemColor.menu);
		buttonotro.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buttonotro.setBounds(526, 127, 78, 33);
		panel_datos.add(buttonotro);
		
		grupoSexo = new ButtonGroup();
		grupoSexo.add(buttonmasculino);
		grupoSexo.add(buttonfemenino);
		grupoSexo.add(buttonotro);
		
		JLabel lblidentidad = new JLabel("Número de identidad");
		lblidentidad.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblidentidad.setBounds(31, 99, 251, 25);
		panel_datos.add(lblidentidad);
		
		JLabel lblnombres = new JLabel("Nombres");
		lblnombres.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblnombres.setBounds(30, 166, 166, 25);
		panel_datos.add(lblnombres);
		
		JLabel lblapellidos = new JLabel("Apellidos");
		lblapellidos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblapellidos.setBounds(29, 236, 166, 25);
		panel_datos.add(lblapellidos);
		
		JLabel lblsexo = new JLabel("Sexo");
		lblsexo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblsexo.setBounds(332, 96, 57, 25);
		panel_datos.add(lblsexo);
		
		JLabel lblfecha_nacimiento = new JLabel("Fecha de nacimiento");
		lblfecha_nacimiento.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblfecha_nacimiento.setBounds(329, 170, 217, 25);
		panel_datos.add(lblfecha_nacimiento);
		
		JLabel lbltelefono = new JLabel("Número de téfono");
		lbltelefono.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbltelefono.setBounds(29, 307, 200, 25);
		panel_datos.add(lbltelefono);
		
		txttel = new JTextField();
		txttel.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyTyped(KeyEvent e) {
		        validaciones.validarTelefono(e, txttel);
		    }
		});

		txttel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txttel.setColumns(10);
		txttel.setBounds(29, 336, 253, 33);
		panel_datos.add(txttel);
		
		JLabel lblarea = new JLabel("Área");
		lblarea.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblarea.setBounds(630, 379, 200, 25);
		panel_datos.add(lblarea);
		
		txtcorreo = new JTextField();
		txtcorreo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validaciones.validarCorreo(e, txtcorreo);
			}
		});
		txtcorreo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtcorreo.setColumns(10);
		txtcorreo.setBounds(29, 403, 253, 33);
		panel_datos.add(txtcorreo);
		
		JLabel lblcorreo_electronico = new JLabel("Correo electrónico");
		lblcorreo_electronico.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblcorreo_electronico.setBounds(30, 379, 166, 25);
		panel_datos.add(lblcorreo_electronico);
		
		JPanel panel_foto = new JPanel();
		panel_foto.setLayout(null);
		panel_foto.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_foto.setBackground(Color.WHITE);
		panel_foto.setBounds(824, 26, 125, 126);
		panel_datos.add(panel_foto);
		
		lblfoto = new JLabel("");
		lblfoto.setToolTipText("Fotografia");
		lblfoto.setForeground(Color.BLUE);
		lblfoto.setBackground(Color.WHITE);
		lblfoto.setBounds(10, 10, 106, 106);
		lblfoto.setIcon(new ImageIcon(icono_fotografia.getImage().getScaledInstance(lblfoto.getWidth(),
				lblfoto.getHeight(), Image.SCALE_SMOOTH))); //imagen default
		panel_foto.add(lblfoto);
		
		btnseleccionar_foto = new JButton("Seleccionar foto");
		btnseleccionar_foto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFileChooser seleccionador = new JFileChooser();

					FileNameExtensionFilter formatos = new FileNameExtensionFilter("JPG & JPEG & PNG", "jpg", "jpeg", "png");
					seleccionador.setFileFilter(formatos);

					int seleccion = seleccionador.showOpenDialog(null);
					seleccionador.setDialogTitle("Buscar fotografia del empleado...");

					if (seleccion == JFileChooser.APPROVE_OPTION) {
						File ruta = seleccionador.getSelectedFile();
						String ruta_fotografia = String.valueOf(ruta);
						ImageIcon imagen = null;

						try {
							imagen = new ImageIcon(ruta.toURI().toURL());
						} catch (MalformedURLException ex) {
							JOptionPane.showMessageDialog(null, "Error en el formato o al encontrar la imagen.");
							Logger.getLogger(empleado_nuevo.class.getName()).log(Level.SEVERE, null, ex);
						}
						lblfoto.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(lblfoto.getWidth(),
								lblfoto.getHeight(), Image.SCALE_SMOOTH)));
						txtruta.setText(ruta_fotografia);
					}
			}
		});
		btnseleccionar_foto.setForeground(Color.BLACK);
		btnseleccionar_foto.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnseleccionar_foto.setBackground(SystemColor.menu);
		btnseleccionar_foto.setBounds(660, 84, 136, 23);
		panel_datos.add(btnseleccionar_foto);
		
		btneliminar_foto = new JButton("Eliminar foto");
		btneliminar_foto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					txtruta.setText("");
					lblfoto.setIcon(new ImageIcon(icono_fotografia.getImage().getScaledInstance(lblfoto.getWidth(),
							lblfoto.getHeight(), Image.SCALE_SMOOTH)));
				
			}
		});
		btneliminar_foto.setForeground(Color.BLACK);
		btneliminar_foto.setFont(new Font("Tahoma", Font.BOLD, 11));
		btneliminar_foto.setBackground(SystemColor.menu);
		btneliminar_foto.setBounds(660, 117, 136, 23);
		panel_datos.add(btneliminar_foto);
		
		txtruta = new JTextField();
		txtruta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtruta.setEditable(false);
		txtruta.setColumns(10);
		txtruta.setBackground(SystemColor.menu);
		txtruta.setBounds(824, 162, 125, 20);
		panel_datos.add(txtruta);
		
		JLabel lbldireccion = new JLabel("Dirección");
		lbldireccion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbldireccion.setBounds(329, 236, 200, 25);
		panel_datos.add(lbldireccion);
		
		fecha_nacimiento = new JDateChooser();
		fecha_nacimiento.setToolTipText("Seleccione la fecha de nacimiento");
		fecha_nacimiento.setBackground(new Color(255, 255, 255));
		//fecha_nacimiento.setForeground(Color.BLACK);
		fecha_nacimiento.setBounds(328, 194, 254, 33);
		fecha_nacimiento.setDateFormatString("dd-MM-yy");
		panel_datos.add(fecha_nacimiento);
		validaciones.deshabilitarEscrituraJDateChooser(fecha_nacimiento);
		
			//Rango de años
	        Calendar minDate = Calendar.getInstance();//Establecer el rango de años (1935 - 2004)
	        minDate.set(1935, Calendar.JANUARY, 1); // Año mínimo: 1935
	        Calendar maxDate = Calendar.getInstance();
	        maxDate.set(2004, Calendar.DECEMBER, 31); // Año máximo: 2004
	        fecha_nacimiento.setSelectableDateRange(minDate.getTime(), maxDate.getTime());// Configurar el rango de fechas
		
		JLabel lbltitulo_foto = new JLabel("Fotografía");
		lbltitulo_foto.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbltitulo_foto.setBounds(630, 26, 119, 25);
		panel_datos.add(lbltitulo_foto);
		
		JLabel lblid = new JLabel("Id empleado");
		lblid.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblid.setBounds(31, 26, 251, 25);
		panel_datos.add(lblid);
		
		txtid_empleado = new JTextField();
		txtid_empleado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				validaciones.validarNumerosID(e, txtid_empleado, 3);
			}
		});
		txtid_empleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtid_empleado.setColumns(10);
		txtid_empleado.setBounds(29, 50, 119, 33);
		panel_datos.add(txtid_empleado);
		
		JLabel lblFechaDeInicio = new JLabel("Fecha de inicio");
		lblFechaDeInicio.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFechaDeInicio.setBounds(630, 178, 200, 25);
		panel_datos.add(lblFechaDeInicio);
		
		fecha_inicio = new JDateChooser();
		fecha_inicio.setToolTipText("Seleccione la fecha de inicio laboral");
		fecha_inicio.setBackground(new Color(255, 255, 255));
		//fecha_inicio.setForeground(Color.BLACK);
		fecha_inicio.setBounds(630, 203, 251, 33);
		fecha_inicio.setDateFormatString("dd-MM-yy");
		panel_datos.add(fecha_inicio);
		validaciones.deshabilitarEscrituraJDateChooser(fecha_inicio);
		
			//Rango de años
			Calendar fechaMin = Calendar.getInstance();
			fechaMin.set(2012, Calendar.JANUARY, 1);
			fecha_inicio.setMinSelectableDate(fechaMin.getTime()); // Fecha mínima permitida
			fecha_inicio.setMaxSelectableDate(fechaActual);       // Fecha máxima permitida (hoy)
			
			
		JLabel lblFechaDeRenuncia = new JLabel("Fecha de renuncia");
		lblFechaDeRenuncia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFechaDeRenuncia.setBounds(630, 246, 166, 25);
		panel_datos.add(lblFechaDeRenuncia);
		
		fecha_renuncia = new JDateChooser();
		fecha_renuncia.setToolTipText("Seleccione la fecha de renuncia");
		fecha_renuncia.setBackground(new Color(255, 255, 255));
		fecha_renuncia.setForeground(new Color(0, 0, 0));
		fecha_renuncia.setBounds(630, 269, 251, 33);
		fecha_renuncia.setDateFormatString("dd-MM-yy");
		panel_datos.add(fecha_renuncia);
		validaciones.deshabilitarEscrituraJDateChooser(fecha_renuncia);
		
			//Rango de años
			Calendar fechaMinimo = Calendar.getInstance();
			fechaMinimo.set(2012, Calendar.JANUARY, 1);
			fecha_renuncia.setMinSelectableDate(fechaMinimo.getTime()); // Fecha mínima permitida
			fecha_renuncia.setMaxSelectableDate(fechaActual);       // Fecha máxima permitida (hoy)
		
		JLabel lblcargo = new JLabel("Cargo");
		lblcargo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblcargo.setBounds(329, 377, 200, 25);
		panel_datos.add(lblcargo);
		
		cbxcargo = new JComboBox<String>();
		cbxcargo.setModel(new DefaultComboBoxModel(new String[] {"Director general", "Director", "Gerente financiero", "Administrador", "Asistente ", "Cobros", "Enfermero", "Psicologo", "Supervisor ", "Consejero", "Docente", "Docente auxiliar", "Soporte técnico", "Marketing", "Aseo ", "Mantenimiento", "Conserje"}));
		cbxcargo.setToolTipText("Seleccione");
		cbxcargo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxcargo.setBackground(Color.WHITE);
		cbxcargo.setBounds(329, 403, 253, 33);
		cbxcargo.setSelectedIndex(-1);
		panel_datos.add(cbxcargo);
		
		txadireccion = new JTextArea();
		txadireccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				validaciones.validarLongitud(e, txadireccion, 150);
				validaciones.capitalizarPrimeraLetra(txadireccion);
				
			}
		});
		txadireccion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txadireccion.setBounds(329, 262, 253, 107);
		panel_datos.add(txadireccion);
		txadireccion.setLineWrap(true); // Activa el ajuste de líneas
		txadireccion.setWrapStyleWord(true); // Ajusta el texto en palabras completas
		txadireccion.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // agrega borde
		
		cbxarea = new JComboBox<String>();
		cbxarea.setModel(new DefaultComboBoxModel(new String[] {"Administrativa", "Financiera", "Pre basica", "Primaria", "Secundaria", "Logistica", "Aseo", "Mantenimiento"}));
		cbxarea.setToolTipText("Seleccione");
		cbxarea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxarea.setBackground(Color.WHITE);
		cbxarea.setBounds(630, 414, 251, 33);
		cbxarea.setSelectedIndex(-1);
		panel_datos.add(cbxarea);
		
		JLabel lblcuenta = new JLabel("Número de cuenta bancaria");
		lblcuenta.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblcuenta.setBounds(630, 312, 251, 25);
		panel_datos.add(lblcuenta);
		
		txtcuenta = new JTextField();
		txtcuenta.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				validaciones.validarSoloNumeros(e, txtcuenta, 30);
			}
		});
		
		txtcuenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtcuenta.setColumns(10);
		txtcuenta.setBounds(630, 336, 251, 33);
		txtcuenta.setText("0");
		panel_datos.add(txtcuenta);
		
		JLabel lblestado_civil = new JLabel("Estado civil");
		lblestado_civil.setBounds(329, 26, 200, 25);
		panel_datos.add(lblestado_civil);
		lblestado_civil.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		cbxestado_civil = new JComboBox<String>();
		cbxestado_civil.setBounds(329, 53, 253, 33);
		panel_datos.add(cbxestado_civil);
		cbxestado_civil.setModel(new DefaultComboBoxModel(new String[] {"Soltero(a)", "Casado(a)", "Viudo(a)"}));
		cbxestado_civil.setToolTipText("Seleccione");
		cbxestado_civil.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxestado_civil.setSelectedIndex(-1);
		cbxestado_civil.setBackground(Color.WHITE);
		
		JPanel panel_titulo_1 = new JPanel();
		panel_titulo_1.setLayout(null);
		panel_titulo_1.setBackground(SystemColor.menu);
		panel_titulo_1.setBounds(482, 25, 528, 62);
		getContentPane().add(panel_titulo_1);
		
		btnguardar = new JButton("Guardar");
		btnguardar.setBounds(415, 17, 75, 23);
		panel_titulo_1.add(btnguardar);
		btnguardar.setToolTipText("Guardar registro");
		btnguardar.setIcon(null);
		btnguardar.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnguardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar_empleado();
			}
		});
		btnguardar.setBackground(UIManager.getColor("Button.highlight"));
		
		 btnlimpiar = new JButton("Limpiar");
		 btnlimpiar.setToolTipText("Limpiar los campos");
		 btnlimpiar.setBounds(216, 17, 75, 23);
		 panel_titulo_1.add(btnlimpiar);
		 btnlimpiar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		limpiar();
		 	}
		 });
		 btnlimpiar.setFont(new Font("Tahoma", Font.BOLD, 8));
		 btnlimpiar.setBackground(UIManager.getColor("Button.highlight"));
		 
		 btnactualizar = new JButton("Actualizar");
		 btnactualizar.setSelectedIcon(null);
		 btnactualizar.setIcon(null);
		 btnactualizar.setToolTipText("Actualizar registro");
		 btnactualizar.setBounds(314, 17, 75, 23);
		 panel_titulo_1.add(btnactualizar);
		 btnactualizar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		actualizar_empleado();
		 		
		 	}
		 });
		 btnactualizar.setFont(new Font("Tahoma", Font.BOLD, 8));
		 btnactualizar.setBackground(UIManager.getColor("Button.highlight"));
		 
		 btnregresar = new JButton("Regresar");
		 btnregresar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		empleado_tabla tabla= new empleado_tabla();
		 		tabla.setVisible(true);
		 		tabla.setLocationRelativeTo(null);
		 		tabla.construirTabla();
				dispose();
		 	}
		 });
		 btnregresar.setBackground(UIManager.getColor("Button.highlight"));
		 btnregresar.setToolTipText("Regresar a la tabla");
		 btnregresar.setFont(new Font("Tahoma", Font.BOLD, 8));
		 btnregresar.setBounds(10, 17, 75, 23);
		 panel_titulo_1.add(btnregresar);
		
		JLabel lbltitulo = new JLabel("DATOS DEL EMPLEADO");
		lbltitulo.setBounds(27, 31, 459, 33);
		getContentPane().add(lbltitulo);
		lbltitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lbltitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lbltitulo.setBackground(new Color(255, 153, 0));
		
		txtid = new JTextField();
		txtid.setBounds(445, 33, 12, -4);
		getContentPane().add(txtid);
		txtid.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtid.setEditable(false);
		txtid.setColumns(10);
		txtid.setBackground(new Color(255, 255, 255));
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				cerrar_ventana();
			}
			});
	
        	
	}///////////////////FIN CLASS/////////////////////

		private void cerrar_ventana() {
			if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				System.exit(0);
		}
		
		
		public void guardar_empleado() {
		    Date fechaSeleccionada = fecha_nacimiento.getDate();
		    Date fechaSeleccionada2 = fecha_inicio.getDate();
		    Date fechaSeleccionada3 = fecha_renuncia.getDate(); // Fecha de renuncia

		    // Verificar si hay campos vacíos
		    if (txtid_empleado.getText().equals("") || txtidentidad.getText().equals("") || 
		        txtnombres.getText().equals("") || txtapellidos.getText().equals("") || 
		        (!buttonfemenino.isSelected() && !buttonmasculino.isSelected() && !buttonotro.isSelected()) || 
		        fechaSeleccionada == null || fechaSeleccionada2 == null || 
		        txadireccion.getText().equals("") || txttel.getText().equals("") || 
		        txtcorreo.getText().equals("") || txtruta.getText().equals("") || 
		        txtcuenta.getText().equals("") || cbxestado_civil.getSelectedItem().equals("") || 
		        cbxarea.getSelectedItem().equals("") || cbxcargo.getSelectedItem().equals("")) {

		        JOptionPane.showMessageDialog(null, "Datos vacíos. Por favor, complete todos los campos para registrar al empleado.");
		    } else {
		        empleado clase = new empleado();
		        consultas_empleado consulta = new consultas_empleado();

		        // Asignar valores a la clase empleado
		        clase.setId_empleado(Integer.parseInt(txtid_empleado.getText()));
		        clase.setIdentidad_empleado(txtidentidad.getText());
		        clase.setNombres_empleado(txtnombres.getText());
		        clase.setApellidos_empleado(txtapellidos.getText());

		        // Sexo del empleado
		        if (buttonmasculino.isSelected()) {
		            clase.setSexo_empleado("Masculino");
		        } else if (buttonfemenino.isSelected()) {
		            clase.setSexo_empleado("Femenino");
		        } else if (buttonotro.isSelected()) {
		            clase.setSexo_empleado("Otro");
		        }

		        clase.setNacimiento_empleado(fecha_nacimiento.getDate());
		        clase.setCivil_empleado(cbxestado_civil.getSelectedItem().toString());
		        clase.setDireccion_empleado(txadireccion.getText());
		        clase.setTel_empleado(txttel.getText());
		        clase.setCorreo_empleado(txtcorreo.getText());
		        clase.setCargo_empleado(cbxcargo.getSelectedItem().toString());
		        clase.setArea_empleado(cbxarea.getSelectedItem().toString());
		        clase.setInicio_empleado(fecha_inicio.getDate());

		        if (fechaSeleccionada3 == null) {
		            clase.setRenuncia_empleado(null);
		        } else {
		            clase.setRenuncia_empleado(fechaSeleccionada3);
		        }

		        clase.setFotografia_empleado(txtruta.getText());
		        clase.setCuenta_empleado(txtcuenta.getText());

		        // Verificar si ya existe un empleado con los mismos datos
		        String campoDuplicado = consulta.empleadoExiste(
		            clase.getId_empleado(), 
		            clase.getIdentidad_empleado(), 
		            clase.getNombres_empleado(), 
		            clase.getApellidos_empleado(), 
		            clase.getNacimiento_empleado(), 
		            clase.getDireccion_empleado(), 
		            clase.getTel_empleado(), 
		            clase.getCorreo_empleado(), 
		            clase.getInicio_empleado(), 
		            clase.getCuenta_empleado());

		        if (campoDuplicado != null) {
		            JOptionPane.showMessageDialog(null, "Error, ya existe un empleado con el mismo " + campoDuplicado + ".", "Error de duplicado", JOptionPane.ERROR_MESSAGE);
		        } else {
		            // Intentar guardar el empleado
		            if (consulta.guardar_empleado(clase, fechaSeleccionada, fechaSeleccionada2, fechaSeleccionada3)) {
		                JOptionPane.showMessageDialog(null, "El empleado se ha registrado correctamente.", "Registro guardado", JOptionPane.INFORMATION_MESSAGE);
		                empleado_tabla tabla = new empleado_tabla();
		                tabla.setLocationRelativeTo(null);
		                tabla.setVisible(true);
		                tabla.construirTabla();
		                dispose();
		            } else {
		                JOptionPane.showMessageDialog(null, "Error, empleado no guardado.", "Error al guardar", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    }
		}




	
		public void limpiar() {
			txtid_empleado.setText("");
			txtidentidad.setText("");
			txtnombres.setText("");
			txtapellidos.setText("");
			buttonfemenino.setSelected(false);
			buttonmasculino.setSelected(false);
			buttonotro.setSelected(false);
			fecha_nacimiento.setDate(null);
			cbxestado_civil.setSelectedIndex(-1);
			txadireccion.setText("");
			txttel.setText("");
			txtcorreo.setText("");
			cbxcargo.setSelectedIndex(-1);
			cbxarea.setSelectedIndex(-1);
			fecha_inicio.setDate(null);
			fecha_renuncia.setDate(null);
			txtruta.setText("");
			txtcuenta.setText("");
			lblfoto.setIcon(new ImageIcon(icono_fotografia.getImage().getScaledInstance(lblfoto.getWidth(),
					lblfoto.getHeight(), Image.SCALE_SMOOTH)));

	
		}
	
		public void actualizar_empleado() {
		    Date fechaNacimiento = fecha_nacimiento.getDate();
		    Date fechaInicio = fecha_inicio.getDate();
		    Date fechaRenuncia = fecha_renuncia.getDate();

		    // Validación de campos vacíos
		    if (txtid_empleado.getText().isEmpty() || txtidentidad.getText().isEmpty() ||
		        txtnombres.getText().isEmpty() || txtapellidos.getText().isEmpty() ||
		        (!buttonmasculino.isSelected() && !buttonfemenino.isSelected() && !buttonotro.isSelected()) ||
		        fechaNacimiento == null || fechaInicio == null || txadireccion.getText().isEmpty() ||
		        txttel.getText().isEmpty() || txtcorreo.getText().isEmpty() || txtruta.getText().isEmpty() ||
		        txtcuenta.getText().isEmpty() || cbxestado_civil.getSelectedItem() == null ||
		        cbxarea.getSelectedItem() == null || cbxcargo.getSelectedItem() == null) {

		        JOptionPane.showMessageDialog(null, "Datos vacíos, por favor, ingrese todos los datos para actualizar el empleado");
		        return;
		    }

		    try {
		        int idOriginal = Integer.parseInt(txtid.getText()); // Este es el ID original del empleado en la base de datos
		        if (idOriginal == 0) {
		            JOptionPane.showMessageDialog(null, "Error: ID del empleado no válido.");
		            return;
		        }

		        empleado clase = new empleado();
		        consultas_empleado consulta = new consultas_empleado();

		        // Obtener el empleado original usando el ID original
		        empleado empleadoOriginal = consulta.obtenerEmpleadoPorId(idOriginal);

		        if (empleadoOriginal == null) {
		            JOptionPane.showMessageDialog(null, "Error: No se pudo encontrar el empleado con el ID original proporcionado.");
		            return;
		        }

		        // Obtener el nuevo ID del empleado del campo de texto
		        int nuevoIdEmpleado = Integer.parseInt(txtid_empleado.getText());
		        String nuevaIdentidad = txtidentidad.getText();
		        
		        boolean idModificado = nuevoIdEmpleado != empleadoOriginal.getId_empleado();
		        boolean identidadModificada = !nuevaIdentidad.equals(empleadoOriginal.getIdentidad_empleado());

		        // Verificar duplicados solo si se ha modificado la identidad o el ID
		        if (idModificado || identidadModificada) {
		            String campoDuplicado = consulta.empleadoExisteParaActualizar(idOriginal, nuevoIdEmpleado, nuevaIdentidad);
		            if (campoDuplicado != null) {
		                JOptionPane.showMessageDialog(null, "Error, ya existe un empleado con el mismo " + campoDuplicado + ".", "Error de duplicado", JOptionPane.ERROR_MESSAGE);
		                return;
		            }
		        }

		        // Asignar valores a la clase empleado
		        clase.setId(idOriginal); // Usamos el ID original para la actualización
		        clase.setId_empleado(nuevoIdEmpleado);
		        clase.setIdentidad_empleado(nuevaIdentidad);
		        clase.setNombres_empleado(txtnombres.getText());
		        clase.setApellidos_empleado(txtapellidos.getText());

		        if (buttonmasculino.isSelected()) {
		            clase.setSexo_empleado("Masculino");
		        } else if (buttonfemenino.isSelected()) {
		            clase.setSexo_empleado("Femenino");
		        } else if (buttonotro.isSelected()) {
		            clase.setSexo_empleado("Otro");
		        }

		        clase.setNacimiento_empleado(fechaNacimiento);
		        clase.setCivil_empleado(cbxestado_civil.getSelectedItem().toString());
		        clase.setDireccion_empleado(txadireccion.getText());
		        clase.setTel_empleado(txttel.getText());
		        clase.setCorreo_empleado(txtcorreo.getText());
		        clase.setCargo_empleado(cbxcargo.getSelectedItem().toString());
		        clase.setArea_empleado(cbxarea.getSelectedItem().toString());
		        clase.setInicio_empleado(fechaInicio);
		        clase.setRenuncia_empleado(fechaRenuncia);
		        clase.setFotografia_empleado(txtruta.getText());
		        clase.setCuenta_empleado(txtcuenta.getText());

		        // Llamada al método de actualización
		        if (consulta.actualizar_empleado(clase, idOriginal, nuevoIdEmpleado, fechaNacimiento, fechaInicio, fechaRenuncia)) {
		            JOptionPane.showMessageDialog(null, "El empleado se ha actualizado correctamente");
		            // Actualizar la tabla y cerrar la ventana actual
		            empleado_tabla tabla = new empleado_tabla();
		            tabla.setLocationRelativeTo(null);
		            tabla.setVisible(true);
		            tabla.construirTabla();
		            dispose();
		        } else {
		            JOptionPane.showMessageDialog(null, "Error, no se actualizó el empleado");
		        }
		    } catch (NumberFormatException e) {
		        JOptionPane.showMessageDialog(null, "Error: ID de empleado inválido");
		    } catch (Exception e) {
		        JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage());
		        e.printStackTrace();
		    }
		}
		
		
		




}
