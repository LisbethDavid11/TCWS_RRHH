package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.toedter.calendar.JDateChooser;

import clases.permiso_ausencia_laboral;
import conexion.conexion;
import consultas.consultas_permiso_ausencia_laboral;
import principal.menu_principal;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import clases.validaciones;
import javax.swing.UIManager;





public class permiso_ausencia_laboral_nuevo extends JFrame {
	public JTextField txtidentidad;
	public JTextField txtapellidos;
	public JTextField txttel;
	public JTextField txtcorreo;
	public JTextField txtid;
	public JTextField txtcargo;
	public JTextField txtarea;
	public JTextField txttotal_dias;
	public JTextField txttotal_horas;
	public JDateChooser date_desde;
	public JDateChooser date_hasta;
	public JButton btnregresar;
	public JButton btnguardar;
	public JButton btnlimpiar;
	public JButton btnactualizar;
	public JPanel panel_datos;
	public JComboBox <String> cbxnombres;
	public JSpinner spinnerHoraFin;
	public JSpinner spinnerHoraInicio;
	private JLabel lblDatosDel;
	private JLabel lblDas;
	private JLabel lblDatosDel_1;
	
	LocalDate fechaActual = LocalDate.now();
	public JTextField txtnombres_recibe;
	public JTextField txtnumero_permiso;
	public JTextField txtFecha;
	public JLabel lblDatosDel_2;
	public JTextArea txamotivo;
	public JButton btncomprobante;
	
	
	public permiso_ausencia_laboral_nuevo() {
		
		
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
		
		
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yy");
        Date fechaActual = new Date();
	    
		
		panel_datos = new JPanel();
		panel_datos.setLayout(null);
		panel_datos.setBackground(SystemColor.menu);
		panel_datos.setBounds(28, 85, 975, 498);
		getContentPane().add(panel_datos);
		
	
  
  
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				cerrar_ventana();
			}
			});
		
		txtidentidad = new JTextField();
		txtidentidad.setEditable(false);
		txtidentidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtidentidad.setColumns(10);
		txtidentidad.setBounds(266, 72, 208, 33);
		panel_datos.add(txtidentidad);
		
		txtapellidos = new JTextField();
		txtapellidos.setEditable(false);
		txtapellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtapellidos.setColumns(10);
		txtapellidos.setBounds(30, 139, 208, 33);
		panel_datos.add(txtapellidos);
		
		JLabel lblidentidad = new JLabel("Número de identidad");
		lblidentidad.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblidentidad.setBounds(268, 48, 191, 25);
		panel_datos.add(lblidentidad);
		
		JLabel lblnombres = new JLabel("Nombres");
		lblnombres.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblnombres.setBounds(31, 48, 166, 25);
		panel_datos.add(lblnombres);
		
		JLabel lblapellidos = new JLabel("Apellidos");
		lblapellidos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblapellidos.setBounds(30, 115, 166, 25);
		panel_datos.add(lblapellidos);
		
		JLabel lbltelefono = new JLabel("Número de téfono");
		lbltelefono.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbltelefono.setBounds(511, 48, 200, 25);
		panel_datos.add(lbltelefono);
		
		txttel = new JTextField();
		txttel.setEditable(false);
		txttel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txttel.setColumns(10);
		txttel.setBounds(511, 71, 208, 33);
		panel_datos.add(txttel);
		
		JLabel lblarea = new JLabel("Área");
		lblarea.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblarea.setBounds(749, 115, 200, 25);
		panel_datos.add(lblarea);
		
		txtcorreo = new JTextField();
		txtcorreo.setEditable(false);
		txtcorreo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtcorreo.setColumns(10);
		txtcorreo.setBounds(511, 140, 208, 33);
		panel_datos.add(txtcorreo);
		
		JLabel lblcorreo_electronico = new JLabel("Correo electrónico");
		lblcorreo_electronico.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblcorreo_electronico.setBounds(512, 116, 166, 25);
		panel_datos.add(lblcorreo_electronico);
		
		JLabel lblid = new JLabel("Id empleado");
		lblid.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblid.setBounds(266, 115, 140, 25);
		panel_datos.add(lblid);
		
		txtid = new JTextField();
		txtid.setEditable(false);
		txtid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtid.setColumns(10);
		txtid.setBounds(266, 139, 208, 33);
		panel_datos.add(txtid);
		
		JLabel lblcargo = new JLabel("Cargo");
		lblcargo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblcargo.setBounds(749, 48, 200, 25);
		panel_datos.add(lblcargo);
		
		txtcargo = new JTextField();
		txtcargo.setEditable(false);
		txtcargo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtcargo.setColumns(10);
		txtcargo.setBounds(749, 72, 208, 33);
		panel_datos.add(txtcargo);
		
		txtarea = new JTextField();
		txtarea.setEditable(false);
		txtarea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtarea.setColumns(10);
		txtarea.setBounds(749, 139, 208, 33);
		panel_datos.add(txtarea);
		
		
		
		cbxnombres = new JComboBox<>();
		cbxnombres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxnombres.setSelectedIndex(-1);
		cbxnombres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbxnombres.getSelectedIndex() != -1) {
                    
					if (cbxnombres.getSelectedIndex() > 0) { 
	                    String nombreSeleccionado = cbxnombres.getSelectedItem().toString();
	                    cargarDatosEmpleado(nombreSeleccionado);
	                } else {
	                    
	                    limpiarCampos();
	                }
                }
			}
		});
		cbxnombres.setBounds(30, 72, 208, 33);
		panel_datos.add(cbxnombres);
		
		date_desde = new JDateChooser();
		date_desde.setBounds(567, 262, 133, 28);
		date_desde.setDateFormatString("dd-MM-yy");
		panel_datos.add(date_desde);
		validaciones.deshabilitarEscrituraJDateChooser(date_desde);
		
		JLabel lbldesde = new JLabel("Desde");
		lbldesde.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbldesde.setBounds(507, 265, 61, 25);
		panel_datos.add(lbldesde);
		
		JLabel lblhasta = new JLabel("Hasta");
		lblhasta.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblhasta.setBounds(731, 265, 61, 25);
		panel_datos.add(lblhasta);
		
		date_hasta = new JDateChooser();
		date_hasta.setBounds(791, 262, 133, 28);
		date_hasta.setDateFormatString("dd-MM-yy");
		panel_datos.add(date_hasta);
		validaciones.deshabilitarEscrituraJDateChooser(date_hasta);
		
		txttotal_dias = new JTextField();
		txttotal_dias.setHorizontalAlignment(SwingConstants.CENTER);
		txttotal_dias.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txttotal_dias.setEditable(false);
		txttotal_dias.setColumns(10);
		txttotal_dias.setBounds(725, 314, 67, 33);
		panel_datos.add(txttotal_dias);
		
		JLabel lbltotal1 = new JLabel("Total de dias");
		lbltotal1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbltotal1.setBounds(599, 318, 116, 25);
		panel_datos.add(lbltotal1);
		
		txttotal_horas = new JTextField();
		txttotal_horas.setHorizontalAlignment(SwingConstants.CENTER);
		txttotal_horas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txttotal_horas.setEditable(false);
		txttotal_horas.setColumns(10);
		txttotal_horas.setBounds(731, 434, 67, 33);
		panel_datos.add(txttotal_horas);
		
		JLabel lbltotal2 = new JLabel("Total de horas");
		lbltotal2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbltotal2.setBounds(599, 438, 126, 25);
		panel_datos.add(lbltotal2);
		
		JLabel lbldesde_1 = new JLabel("Desde");
		lbldesde_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbldesde_1.setBounds(507, 385, 61, 25);
		panel_datos.add(lbldesde_1);
		
		JLabel lblhasta_1 = new JLabel("Hasta");
		lblhasta_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblhasta_1.setBounds(731, 385, 61, 25);
		panel_datos.add(lblhasta_1);
		
		Calendar calendarHoraInicio = Calendar.getInstance();
		calendarHoraInicio.set(Calendar.HOUR_OF_DAY, 0);
		calendarHoraInicio.set(Calendar.MINUTE, 0);
		calendarHoraInicio.set(Calendar.SECOND, 0); // Asegúrate de que los segundos también se establezcan en cero

		SpinnerDateModel modelHoraInicio = new SpinnerDateModel(calendarHoraInicio.getTime(), null, null, Calendar.MINUTE);
		spinnerHoraInicio = new JSpinner(modelHoraInicio);
		JSpinner.DateEditor editorHoraInicio = new JSpinner.DateEditor(spinnerHoraInicio, "HH:mm");
		spinnerHoraInicio.setEditor(editorHoraInicio);
		spinnerHoraInicio.setFont(new Font("Tahoma", Font.BOLD, 14));
		spinnerHoraInicio.setBounds(567, 385, 133, 30);
		panel_datos.add(spinnerHoraInicio);
		
		Calendar calendarHoraFin = Calendar.getInstance();
		calendarHoraFin.set(Calendar.HOUR_OF_DAY, 0);
		calendarHoraFin.set(Calendar.MINUTE, 0);
		calendarHoraFin.set(Calendar.SECOND, 0); // Asegúrate de que los segundos también se establezcan en cero

		SpinnerDateModel modelHoraFin = new SpinnerDateModel(calendarHoraFin.getTime(), null, null, Calendar.MINUTE);
		spinnerHoraFin = new JSpinner(modelHoraFin);
		JSpinner.DateEditor editorHoraFin = new JSpinner.DateEditor(spinnerHoraFin, "HH:mm");
		spinnerHoraFin.setEditor(editorHoraFin);
		spinnerHoraFin.setFont(new Font("Tahoma", Font.BOLD, 14));
		spinnerHoraFin.setBounds(791, 383, 133, 30);
		panel_datos.add(spinnerHoraFin);
        
        lblDatosDel = new JLabel("---- Horas --------------------------------------------------------");
        lblDatosDel.setHorizontalAlignment(SwingConstants.LEFT);
        lblDatosDel.setForeground(Color.GRAY);
        lblDatosDel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDatosDel.setBounds(507, 353, 484, 28);
        panel_datos.add(lblDatosDel);
        
        lblDas = new JLabel("---- Días ----------------------------------------------------------");
        lblDas.setHorizontalAlignment(SwingConstants.LEFT);
        lblDas.setForeground(Color.GRAY);
        lblDas.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDas.setBounds(507, 224, 484, 28);
        panel_datos.add(lblDas);
        
        lblDatosDel_1 = new JLabel("_______ Datos del empleado__________________________________________________________________________________");
        lblDatosDel_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblDatosDel_1.setForeground(Color.GRAY);
        lblDatosDel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDatosDel_1.setBounds(30, 10, 919, 28);
        panel_datos.add(lblDatosDel_1);
        
        JLabel lblNombreDeQuien = new JLabel("Nombre de quien recibe");
        lblNombreDeQuien.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNombreDeQuien.setBounds(32, 409, 221, 25);
        panel_datos.add(lblNombreDeQuien);
        
        txtnombres_recibe = new JTextField();
        txtnombres_recibe.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
            	validaciones.validarNombresyApellidos(e, txtnombres_recibe, 70);
        	}
        });
        txtnombres_recibe.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtnombres_recibe.setColumns(10);
        txtnombres_recibe.setBounds(30, 431, 276, 33);
        panel_datos.add(txtnombres_recibe);
        
        btncomprobante = new JButton("Comprobante");
        btncomprobante.setFont(new Font("Tahoma", Font.BOLD, 11));
        btncomprobante.setBackground(SystemColor.menu);
        btncomprobante.setBounds(332, 432, 111, 35);
        panel_datos.add(btncomprobante);
        
        txamotivo = new JTextArea();
        txamotivo.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		validaciones.validarLongitud(e, txamotivo, 150);        	
        		validaciones.capitalizarPrimeraLetra(txamotivo);
        	}
        });
        txamotivo.setWrapStyleWord(true);
        txamotivo.setLineWrap(true);
        txamotivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txamotivo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txamotivo.setBounds(30, 291, 413, 108);
        panel_datos.add(txamotivo);
        
        JLabel lblmotivo = new JLabel("Motivo de ausencia");
        lblmotivo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblmotivo.setBounds(30, 266, 232, 25);
        panel_datos.add(lblmotivo);
        
        JLabel lblid_permiso = new JLabel("Permiso No.");
        lblid_permiso.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblid_permiso.setBounds(30, 224, 116, 25);
        panel_datos.add(lblid_permiso);
        
        txtnumero_permiso = new JTextField();
        txtnumero_permiso.setForeground(new Color(0, 0, 0));
        txtnumero_permiso.setBackground(SystemColor.menu);
        txtnumero_permiso.setHorizontalAlignment(SwingConstants.CENTER);
        txtnumero_permiso.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtnumero_permiso.setEditable(false);
        txtnumero_permiso.setColumns(10);
        txtnumero_permiso.setBounds(137, 220, 61, 33);
        panel_datos.add(txtnumero_permiso);
        
        JLabel lblhoy_es = new JLabel("Fecha actual:");
        lblhoy_es.setForeground(SystemColor.inactiveCaptionText);
        lblhoy_es.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblhoy_es.setBounds(222, 227, 111, 25);
        panel_datos.add(lblhoy_es);
        
        txtFecha = new JTextField();
        txtFecha.setText("11-09-24");
        txtFecha.setHorizontalAlignment(SwingConstants.CENTER);
        txtFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtFecha.setEditable(false);
        txtFecha.setColumns(10);
        txtFecha.setBackground(SystemColor.menu);
        txtFecha.setBounds(340, 223, 103, 33);
        panel_datos.add(txtFecha);
        
        lblDatosDel_2 = new JLabel("_______ Datos del permiso__________________________________________________________________________________");
        lblDatosDel_2.setHorizontalAlignment(SwingConstants.LEFT);
        lblDatosDel_2.setForeground(Color.GRAY);
        lblDatosDel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDatosDel_2.setBounds(30, 182, 919, 28);
        panel_datos.add(lblDatosDel_2);
        
        
		
		JLabel lblRegistarPermisoPor = new JLabel("PERMISO POR AUSENCIA LABORAL");
		lblRegistarPermisoPor.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistarPermisoPor.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblRegistarPermisoPor.setBackground(new Color(255, 153, 0));
		lblRegistarPermisoPor.setBounds(28, 39, 442, 36);
		getContentPane().add(lblRegistarPermisoPor);
		
		JPanel panel_botones = new JPanel();
		panel_botones.setLayout(null);
		panel_botones.setBackground(SystemColor.menu);
		panel_botones.setBounds(487, 21, 516, 65);
		getContentPane().add(panel_botones);
		
		btnguardar = new JButton("Guardar");
		btnguardar.setBounds(415, 17, 75, 23);
		panel_botones.add(btnguardar);
		btnguardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar_permiso_ausencia_laboral();
				
			}
		});
		btnguardar.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnguardar.setBackground(UIManager.getColor("Button.highlight"));
		
		btnactualizar = new JButton("Actualizar");
		btnactualizar.setBounds(314, 17, 75, 23);
		panel_botones.add(btnactualizar);
		btnactualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar_permiso_laboral();
			}
		});
		btnactualizar.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnactualizar.setBackground(UIManager.getColor("Button.highlight"));
		
		btnlimpiar = new JButton("Limpiar");
		btnlimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarDatos();
			}
		});
		btnlimpiar.setBounds(216, 17, 75, 23);
		panel_botones.add(btnlimpiar);
		btnlimpiar.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnlimpiar.setBackground(UIManager.getColor("Button.highlight"));
		
		btnregresar = new JButton("Regresar");
		btnregresar.setToolTipText("Regresar a la tabla");
		btnregresar.setBounds(10, 17, 75, 23);
		panel_botones.add(btnregresar);
		btnregresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				permiso_ausencia_laboral_tabla tabla= new permiso_ausencia_laboral_tabla();
				tabla.setVisible(true);
				tabla.setLocationRelativeTo(null);
				tabla.construirTabla();
				dispose();
				
			}
		});
		btnregresar.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnregresar.setBackground(UIManager.getColor("Button.highlight"));
		

		//JDateChoosers
		cargarNombresEmpleados();
		establecerRangoFechas();
		
		date_desde.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				calcularDiasEntreFechas();
			}
		});
		
		date_hasta.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				calcularDiasEntreFechas();
			}
		});
		
		//JSpinners
		// Añade ChangeListener para recalcular el tiempo transcurrido cuando ambas horas sean válidas
		ChangeListener listener = new ChangeListener() {
		    @Override
		    public void stateChanged(ChangeEvent e) {
		        calcularHorasTranscurridas(); // Recalcular cada vez que se cambien los valores
		    }
		};

		// Añadir ChangeListener a ambos spinners
		spinnerHoraInicio.addChangeListener(listener);
		spinnerHoraFin.addChangeListener(listener);
		
		//////////////////////////////////////////////////////////////////////////////////////
        
        //LocalDate fechaActual = LocalDate.now();
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        
		txtFecha.setText(formatoFecha.format(fechaActual));
        inicializarFormulario(); // Cargar el número de permiso automáticamente
        
       
	}//fin class
	
	
		private void cerrar_ventana() {
			if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				System.exit(0);
		}
		
		
		// Método para cargar nombres en el JComboBox desde la base de datos
	    private void cargarNombresEmpleados() {
	        try {
	            Connection conn = new conexion().conectar();
	            String sql = "SELECT nombres_empleado FROM empleados";
	            PreparedStatement pst = conn.prepareStatement(sql);
	            ResultSet rs = pst.executeQuery();

	            // Añadir ítem vacío al JComboBox para que aparezca inicialmente en blanco
	            cbxnombres.addItem("");  // Este es el ítem vacío

	            while (rs.next()) {
	                cbxnombres.addItem(rs.getString("nombres_empleado"));
	            }

	            rs.close();
	            pst.close();
	            conn.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }

	    // Método para cargar los datos del empleado seleccionado
	    private void cargarDatosEmpleado(String nombre) {
	        try {
	            Connection conn = new conexion().conectar();
	            String sql = "SELECT apellidos_empleado, correo_empleado, cargo_empleado, area_empleado, tel_empleado, identidad_empleado, id_empleado FROM empleados WHERE nombres_empleado = ?";
	            PreparedStatement pst = conn.prepareStatement(sql);
	            pst.setString(1, nombre);
	            ResultSet rs = pst.executeQuery();

	            if (rs.next()) {
	                txtapellidos.setText(rs.getString("apellidos_empleado"));
	                txtcorreo.setText(rs.getString("correo_empleado"));
	                txtcargo.setText(rs.getString("cargo_empleado"));
	                txtarea.setText(rs.getString("area_empleado"));
	                txttel.setText(rs.getString("tel_empleado"));
	                txtidentidad.setText(rs.getString("identidad_empleado"));
	                txtid.setText(rs.getString("id_empleado"));
	            }

	            rs.close();
	            pst.close();
	            conn.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }

	   
	    private void limpiarCampos() {
	    	txtapellidos.setText("");
	    	txtcorreo.setText("");
	        txtcargo.setText("");
	        txtarea.setText("");
	        txttel.setText("");
	        txtidentidad.setText("");
	        txtid.setText("");
	    }
	    
	    ///////////////////////////////////////////////////////////////////////////////////////
	    //Metodo para establecer el rango de fechas de los JDateChooser
	    private void establecerRangoFechas() {
	
	        Calendar cal = Calendar.getInstance();//fecha actual
	        Date fechaActual = cal.getTime();
	        
	        date_desde.setMinSelectableDate(fechaActual); //minimo hoy

	        cal.add(Calendar.MONTH, 6); // maxima tres meses despues
	        Date fechaMaxima = cal.getTime();

	        date_desde.setMaxSelectableDate(fechaMaxima);
	        date_hasta.setMinSelectableDate(fechaActual);
	        date_hasta.setMaxSelectableDate(fechaMaxima);
	    }
	    
	    
	    private void calcularDiasEntreFechas() {
	        Date fechaDesde = date_desde.getDate();
	        Date fechaHasta = date_hasta.getDate();

	        if (fechaDesde != null && fechaHasta != null) {
	            // Convertir Date a LocalDate
	            LocalDate localDateDesde = fechaDesde.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	            LocalDate localDateHasta = fechaHasta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	            long diasEntreFechas = ChronoUnit.DAYS.between(localDateDesde, localDateHasta);

	            txttotal_dias.setText(String.valueOf(diasEntreFechas));
	        } else {
	            txttotal_dias.setText("");
	        }
	    }
	    
	    // Método para calcular la diferencia entre horas
	    public void calcularHorasTranscurridas() {
	        try {
	            Date horaInicio = (Date) spinnerHoraInicio.getValue();
	            Date horaFin = (Date) spinnerHoraFin.getValue();

	            long diferenciaEnMillis = horaFin.getTime() - horaInicio.getTime();
	            if (diferenciaEnMillis <= 0) {
	                txttotal_horas.setText(""); 
	                return; 
	            }
	            long diferenciaEnMinutos = TimeUnit.MILLISECONDS.toMinutes(diferenciaEnMillis);
	            long horas = diferenciaEnMinutos / 60;
	            long minutos = diferenciaEnMinutos % 60;

	            String formatoHora = String.format("%02d:%02d", horas, minutos);
	            txttotal_horas.setText(formatoHora);
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, "Error al calcular el tiempo: " + e.getMessage());
	        }
	    }
	    
	    
	    
	    ///////////////////////////////////////////////////////////////////////////
	    //CRUD
	    public void guardar_permiso_ausencia_laboral() {
	        try {
	            
	            Date horaInicioDate = (Date) spinnerHoraInicio.getValue();
	            Date horaFinDate = (Date) spinnerHoraFin.getValue();
	            Calendar calendarHoraInicio = Calendar.getInstance();
	            calendarHoraInicio.setTime(horaInicioDate);
	            Time horaInicioTime = new Time(calendarHoraInicio.get(Calendar.HOUR_OF_DAY), calendarHoraInicio.get(Calendar.MINUTE), 0);

	            Calendar calendarHoraFin = Calendar.getInstance();
	            calendarHoraFin.setTime(horaFinDate);
	            Time horaFinTime = new Time(calendarHoraFin.get(Calendar.HOUR_OF_DAY), calendarHoraFin.get(Calendar.MINUTE), 0);

	            Date fechaSeleccionada3 = date_desde.getDate(); // Fecha desde
	            Date fechaSeleccionada4 = date_hasta.getDate();  // Fecha hasta
	       
	            String fechaActualTexto = txtFecha.getText();
	            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yy");
	            Date fechaUtil = formatoFecha.parse(fechaActualTexto);
	            java.sql.Date fechaSQL = new java.sql.Date(fechaUtil.getTime());

	            if (cbxnombres.getSelectedItem().equals("") || txtapellidos.getText().equals("") ||
	                txtidentidad.getText().equals("") || txtid.getText().equals("") ||
	                txttel.getText().equals("") || txtcorreo.getText().equals("") ||
	                txtcargo.getText().equals("") || txtarea.getText().equals("") ||
	                txttotal_horas.getText().equals("") || txamotivo.getText().equals("") ||
	                fechaSeleccionada3 == null || fechaSeleccionada4 == null ||
	                txttotal_dias.getText().equals("") || txtnombres_recibe.getText().equals("")) {

	                JOptionPane.showMessageDialog(null, "¡Datos vacíos, por favor, para guardar el permiso llene todos los campos!",
	                        "Advertencia", JOptionPane.WARNING_MESSAGE);

	            } else {
	                permiso_ausencia_laboral clase = new permiso_ausencia_laboral();
	                consultas_permiso_ausencia_laboral consulta = new consultas_permiso_ausencia_laboral();

	                clase.setNombres_empleado(cbxnombres.getSelectedItem().toString());
	                clase.setApellidos_empleado(txtapellidos.getText().toString());
	                clase.setIdentidad_empleado(txtidentidad.getText().toString());
	                clase.setId_empleado(Integer.parseInt(txtid.getText()));
	                clase.setTel_empleado(txttel.getText().toString());
	                clase.setCorreo_empleado(txtcorreo.getText().toString());
	                clase.setCargo_empleado(txtcargo.getText().toString());
	                clase.setArea_empleado(txtarea.getText().toString());
	                clase.setDesde_hora(horaInicioTime); // Almacenar la hora de inicio
	                clase.setHasta_hora(horaFinTime);    // Almacenar la hora de fin
	                
	                String[] tiempo = txttotal_horas.getText().split(":");
	                int horas = Integer.parseInt(tiempo[0]);
	                int minutos = Integer.parseInt(tiempo[1]);
	                Time totalHoras = new Time(horas, minutos, 0); // Crear objeto Time con horas y minutos
	                clase.setTotal_horas(totalHoras); // Almacenar el total de horas como TIME

	                clase.setMotivo_ausencia(txamotivo.getText().toString());
	                clase.setDesde_fecha(fechaSeleccionada3);
	                clase.setHasta_fecha(fechaSeleccionada4);
	                clase.setTotal_fecha(Integer.parseInt(txttotal_dias.getText()));
	                clase.setNombres_recibe(txtnombres_recibe.getText().toString());
	                clase.setFecha_recibe(fechaSQL);
	                
	                if (consulta.guardar_permiso_ausencia_laboral(clase, horaInicioTime, horaFinTime, fechaSeleccionada3, fechaSeleccionada4, fechaSQL)) {
	                    JOptionPane.showMessageDialog(null, "El permiso por ausencia laboral se ha registrado correctamente",
	                            "Registro guardado", JOptionPane.INFORMATION_MESSAGE);

	                    permiso_ausencia_laboral_tabla tabla = new permiso_ausencia_laboral_tabla();
	                    tabla.setLocationRelativeTo(null);
	                    tabla.setVisible(true);
	                    tabla.construirTabla();
	                    dispose();
	                } else {
	                    JOptionPane.showMessageDialog(null, "Error, permiso por ausencia laboral no guardado", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, "Error al guardar el permiso: " + e.getMessage());
	        }
	    }

	    
	    
	    /////////////////////////////////////////////////////////////////////
	    //generar ID
	    public int obtenerUltimoIdPermiso() {
	        int ultimoId = 0;
	        conexion conex = new conexion();

	        try {
	            Statement estatuto = conex.conectar().createStatement();
	            ResultSet rs = estatuto.executeQuery("SELECT MAX(id_permisos) FROM permisos_ausencia_laboral");

	            if (rs.next()) {
	                ultimoId = rs.getInt(1); // Obtiene el último ID
	            }

	            rs.close();
	            estatuto.close();
	            conex.desconectar();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error al obtener el último ID de permiso", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	        return ultimoId;
	    }

	    
	    public void inicializarFormulario() {
	        int nuevoIdPermiso = obtenerUltimoIdPermiso() + 1; // Incrementar el ID
	        txtnumero_permiso.setText(String.valueOf(nuevoIdPermiso)); // Asignar el nuevo ID
	    }
	    
	    
	    
	    @SuppressWarnings("deprecation")
		private void limpiarDatos() {
	    	cbxnombres.setSelectedIndex(-1);
	    	txtapellidos.setText("");
	    	txtcorreo.setText("");
	        txtcargo.setText("");
	        txtarea.setText("");
	        txttel.setText("");
	        txtidentidad.setText("");
	        txtid.setText("");
	        txamotivo.setText("");
	        txtnombres_recibe.setText("");
	        txttotal_dias.setText("");
	        txttotal_horas.setText("");
	        date_desde.setDate(null);
	        date_hasta.setDate(null);
	        spinnerHoraInicio.setValue(new java.util.Date(0, 0, 0, 0, 0));  // Establece el valor a '00:00'
	        spinnerHoraFin.setValue(new java.util.Date(0, 0, 0, 0, 0));     // Establece el valor a '00:00'
	    
	      
	        
	    }
	    
	    public void actualizar_permiso_laboral() {
	        try {
	            
	            Date horaInicioDate = (Date) spinnerHoraInicio.getValue();
	            Date horaFinDate = (Date) spinnerHoraFin.getValue();
	            Calendar calendarHoraInicio = Calendar.getInstance();
	            
	            calendarHoraInicio.setTime(horaInicioDate);
	            Time horaInicioTime = new Time(calendarHoraInicio.get(Calendar.HOUR_OF_DAY), calendarHoraInicio.get(Calendar.MINUTE), 0);

	            Calendar calendarHoraFin = Calendar.getInstance();
	            calendarHoraFin.setTime(horaFinDate);
	            Time horaFinTime = new Time(calendarHoraFin.get(Calendar.HOUR_OF_DAY), calendarHoraFin.get(Calendar.MINUTE), 0);

	            
	            Date fechaSeleccionada3 = date_desde.getDate(); // Fecha desde
	            Date fechaSeleccionada4 = date_hasta.getDate();  // Fecha hasta

	            // Obtener la fecha actual desde txtFecha (ya está en formato dd-MM-yy)
	            String fechaActualTexto = txtFecha.getText();
	            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yy");
	            Date fechaUtil = formatoFecha.parse(fechaActualTexto);
	            java.sql.Date fechaSQL = new java.sql.Date(fechaUtil.getTime());

	           
	            if (cbxnombres.getSelectedItem().equals("") || txtapellidos.getText().equals("") ||
	                txtidentidad.getText().equals("") || txtid.getText().equals("") ||
	                txttel.getText().equals("") || txtcorreo.getText().equals("") ||
	                txtcargo.getText().equals("") || txtarea.getText().equals("") ||
	                txttotal_horas.getText().equals("") || txamotivo.getText().equals("") ||
	                fechaSeleccionada3 == null || fechaSeleccionada4 == null ||
	                txttotal_dias.getText().equals("") || txtnombres_recibe.getText().equals("")) {

	                JOptionPane.showMessageDialog(null, "¡Datos vacíos, por favor, para actualizar el permiso llene todos los campos!",
	                        "Advertencia", JOptionPane.WARNING_MESSAGE);

	            } else {
	                permiso_ausencia_laboral clase = new permiso_ausencia_laboral();
	                consultas_permiso_ausencia_laboral consulta = new consultas_permiso_ausencia_laboral();

	                clase.setId_permisos(Integer.parseInt(txtnumero_permiso.getText()));
	                clase.setNombres_empleado(cbxnombres.getSelectedItem().toString());
	                clase.setApellidos_empleado(txtapellidos.getText().toString());
	                clase.setIdentidad_empleado(txtidentidad.getText().toString());
	                clase.setId_empleado(Integer.parseInt(txtid.getText()));
	                clase.setTel_empleado(txttel.getText().toString());
	                clase.setCorreo_empleado(txtcorreo.getText().toString());
	                clase.setCargo_empleado(txtcargo.getText().toString());
	                clase.setArea_empleado(txtarea.getText().toString());
	                clase.setDesde_hora(horaInicioTime); // Almacenar la hora de inicio
	                clase.setHasta_hora(horaFinTime);    // Almacenar la hora de fin
	                
	                String[] tiempo = txttotal_horas.getText().split(":");
	                int horas = Integer.parseInt(tiempo[0]);
	                int minutos = Integer.parseInt(tiempo[1]);
	                Time totalHoras = new Time(horas, minutos, 0); // Crear objeto Time con horas y minutos
	                clase.setTotal_horas(totalHoras); // Almacenar el total de horas como TIME

	                clase.setMotivo_ausencia(txamotivo.getText().toString());
	                clase.setDesde_fecha(fechaSeleccionada3);
	                clase.setHasta_fecha(fechaSeleccionada4);
	                clase.setTotal_fecha(Integer.parseInt(txttotal_dias.getText()));
	                clase.setNombres_recibe(txtnombres_recibe.getText().toString());
	                clase.setFecha_recibe(fechaSQL);
	                
	                if (consulta.actualizar_permiso_ausencia_laboral(clase, horaInicioTime, horaFinTime, fechaSeleccionada3, fechaSeleccionada4, fechaSQL)) {
	                    JOptionPane.showMessageDialog(null, "Permiso por ausencia laboral actualizado correctamente");
	                    permiso_ausencia_laboral_tabla ver_permiso = new permiso_ausencia_laboral_tabla();
	                    ver_permiso.setLocationRelativeTo(null);
	                    ver_permiso.setVisible(true);
	                    ver_permiso.construirTabla();
	                    dispose();
	                } else {
	                    JOptionPane.showMessageDialog(null, "Error, no se puede actualizar el permiso por ausencia laboral");
	                }
	            }
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, "Error al actualizar el permiso: " + e.getMessage());
	        }
	    }

	


}//end
