package ventanas;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

import clases.permiso_ausencia_hora;
import clases.validaciones;
import conexion.conexion;
import consultas.consultas_cargos;
import consultas.consultas_permiso_hora;
import reportes.encabezado_documentos;


@SuppressWarnings("serial")
public class permiso_hora_nuevo extends JFrame {
	public JTextField txtidentidad;
	public JTextField txtapellidos;
	public JTextField txttel;
	public JTextField txtcorreo;
	public JTextField txtid;
	public JTextField txtcargo;
	public JTextField txtarea;
	public JTextField txttotal_horas;
	public JButton btnregresar;
	public JButton btnguardar;
	public JButton btnlimpiar;
	public JButton btnactualizar;
	public JPanel panel_datos;
	public JComboBox <String> cbxnombres;
	public JSpinner spinnerHoraFin;
	public JSpinner spinnerHoraInicio;
	private JLabel lblDatosDel_1;
	
	LocalDate fechaActual = LocalDate.now();
	public JTextField txtnombres_recibe;
	public JTextField txtnumero_permiso;
	public JTextField date_actual;
	public JLabel lblDatosDel_2;
	public JTextArea txamotivo;
	public JCheckBox chxeditar;
	public JTextField txtnumero;
	public JLabel lblextiende;
	public JTextField txtextiende;
	private JLabel lblc_1;
	public JComboBox<String> cbxcargo_extiende;
	public JComboBox<String> cbxcargo_recibe;
	
	public permiso_hora_nuevo() {
		
		
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
		
		txttotal_horas = new JTextField();
		txttotal_horas.setHorizontalAlignment(SwingConstants.CENTER);
		txttotal_horas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txttotal_horas.setEditable(false);
		txttotal_horas.setColumns(10);
		txttotal_horas.setBounds(783, 275, 67, 33);
		panel_datos.add(txttotal_horas);
		
		JLabel lbltotal2 = new JLabel("Total de horas");
		lbltotal2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbltotal2.setBounds(761, 314, 126, 25);
		panel_datos.add(lbltotal2);
		
		JLabel lbldesde_1 = new JLabel("Desde");
		lbldesde_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbldesde_1.setBounds(511, 275, 61, 25);
		panel_datos.add(lbldesde_1);
		
		JLabel lblhasta_1 = new JLabel("Hasta");
		lblhasta_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblhasta_1.setBounds(511, 327, 61, 25);
		panel_datos.add(lblhasta_1);
		
		Calendar calendarHoraInicio = Calendar.getInstance();
		calendarHoraInicio.set(Calendar.HOUR_OF_DAY, 0);
		calendarHoraInicio.set(Calendar.MINUTE, 0);
		calendarHoraInicio.set(Calendar.SECOND, 0); 

		SpinnerDateModel modelHoraInicio = new SpinnerDateModel(calendarHoraInicio.getTime(), null, null, Calendar.MINUTE);
		spinnerHoraInicio = new JSpinner(modelHoraInicio);
		JSpinner.DateEditor editorHoraInicio = new JSpinner.DateEditor(spinnerHoraInicio, "HH:mm");
		spinnerHoraInicio.setEditor(editorHoraInicio);
		spinnerHoraInicio.setFont(new Font("Tahoma", Font.BOLD, 14));
		spinnerHoraInicio.setBounds(570, 273, 133, 30);
		panel_datos.add(spinnerHoraInicio);
		
		Calendar calendarHoraFin = Calendar.getInstance();
		calendarHoraFin.set(Calendar.HOUR_OF_DAY, 0);
		calendarHoraFin.set(Calendar.MINUTE, 0);
		calendarHoraFin.set(Calendar.SECOND, 0); 

		SpinnerDateModel modelHoraFin = new SpinnerDateModel(calendarHoraFin.getTime(), null, null, Calendar.MINUTE);
		spinnerHoraFin = new JSpinner(modelHoraFin);
		JSpinner.DateEditor editorHoraFin = new JSpinner.DateEditor(spinnerHoraFin, "HH:mm");
		spinnerHoraFin.setEditor(editorHoraFin);
		spinnerHoraFin.setFont(new Font("Tahoma", Font.BOLD, 14));
		spinnerHoraFin.setBounds(570, 325, 133, 30);
		panel_datos.add(spinnerHoraFin);
        
        lblDatosDel_1 = new JLabel("_______ Datos del empleado__________________________________________________________________________________");
        lblDatosDel_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblDatosDel_1.setForeground(Color.GRAY);
        lblDatosDel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDatosDel_1.setBounds(30, 10, 919, 28);
        panel_datos.add(lblDatosDel_1);
        
        JLabel lblNombreDeQuien = new JLabel("Nombre de quien recibe ");
        lblNombreDeQuien.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNombreDeQuien.setBounds(32, 383, 208, 25);
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
        txtnombres_recibe.setBounds(30, 405, 232, 33);
        panel_datos.add(txtnombres_recibe);
        
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
        txamotivo.setBounds(30, 294, 413, 79);
        panel_datos.add(txamotivo);
        
        JLabel lblmotivo = new JLabel("Motivo de ausencia");
        lblmotivo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblmotivo.setBounds(30, 269, 232, 25);
        panel_datos.add(lblmotivo);
        
        JLabel lblid_permiso = new JLabel("Permiso No.");
        lblid_permiso.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblid_permiso.setBounds(30, 234, 116, 25);
        panel_datos.add(lblid_permiso);
        
        txtnumero_permiso = new JTextField();
        txtnumero_permiso.setForeground(new Color(0, 0, 0));
        txtnumero_permiso.setBackground(SystemColor.menu);
        txtnumero_permiso.setHorizontalAlignment(SwingConstants.CENTER);
        txtnumero_permiso.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtnumero_permiso.setEditable(false);
        txtnumero_permiso.setColumns(10);
        txtnumero_permiso.setBounds(137, 230, 61, 33);
        panel_datos.add(txtnumero_permiso);
        
        JLabel lblhoy_es = new JLabel("Fecha actual:");
        lblhoy_es.setForeground(SystemColor.inactiveCaptionText);
        lblhoy_es.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblhoy_es.setBounds(222, 237, 111, 25);
        panel_datos.add(lblhoy_es);
        
        date_actual = new JTextField();
        date_actual.setText("11-09-24");
        date_actual.setHorizontalAlignment(SwingConstants.CENTER);
        date_actual.setFont(new Font("Tahoma", Font.BOLD, 14));
        date_actual.setEditable(false);
        date_actual.setColumns(10);
        date_actual.setBackground(SystemColor.menu);
        date_actual.setBounds(340, 233, 103, 33);
        panel_datos.add(date_actual);
        
        lblDatosDel_2 = new JLabel("_______ Datos del permiso__________________________________________________________________________________");
        lblDatosDel_2.setHorizontalAlignment(SwingConstants.LEFT);
        lblDatosDel_2.setForeground(Color.GRAY);
        lblDatosDel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDatosDel_2.setBounds(30, 195, 919, 28);
        panel_datos.add(lblDatosDel_2);
        
        
		
		JLabel lblRegistarPermisoPor = new JLabel("PERMISO DE AUSENCIA POR HORA");
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
		btnguardar.setToolTipText("Guardar registro");
		btnguardar.setBounds(414, 17, 90, 23);
		panel_botones.add(btnguardar);
		btnguardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar_permiso_ausencia_laboral();
				
			}
		});
		btnguardar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnguardar.setBackground(UIManager.getColor("Button.highlight"));
		
		
		
		btnactualizar = new JButton("Actualizar");
		btnactualizar.setBounds(414, 17, 90, 23);
		panel_botones.add(btnactualizar);
		btnactualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar_permiso_laboral();
			}
		});
		btnactualizar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnactualizar.setBackground(UIManager.getColor("Button.highlight"));
		
		btnlimpiar = new JButton("Limpiar");
		btnlimpiar.setToolTipText("Limpiar registro");
		btnlimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarDatos();
			}
		});
		btnlimpiar.setBounds(314, 17, 90, 23);
		panel_botones.add(btnlimpiar);
		btnlimpiar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnlimpiar.setBackground(UIManager.getColor("Button.highlight"));
		
		btnregresar = new JButton("Regresar");
		btnregresar.setToolTipText("Regresar a la tabla");
		btnregresar.setBounds(10, 17, 90, 23);
		panel_botones.add(btnregresar);
		btnregresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				permiso_hora_tabla tabla= new permiso_hora_tabla();
				tabla.setVisible(true);
				tabla.setLocationRelativeTo(null);
				tabla.construirTabla();
				dispose();
				
			}
		});
		btnregresar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnregresar.setBackground(UIManager.getColor("Button.highlight"));
		
		chxeditar = new JCheckBox("Editar registro");
		chxeditar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chxeditar.setBounds(176, 17, 132, 21);
		panel_botones.add(chxeditar);
		

		//JDateChoosers
		cargarNombresEmpleados();
		establecerRangoFechas();
		
		//JSpinners
		ChangeListener listener = new ChangeListener() {
		    @Override
		    public void stateChanged(ChangeEvent e) {
		        calcularHorasTranscurridas(); 
		    }
		};

		spinnerHoraInicio.addChangeListener(listener);
		spinnerHoraFin.addChangeListener(listener);
		
        
		date_actual.setText(formatoFecha.format(fechaActual));
		
		txtnumero = new JTextField();
		txtnumero.setHorizontalAlignment(SwingConstants.CENTER);
		txtnumero.setForeground(Color.BLACK);
		txtnumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtnumero.setEditable(false);
		txtnumero.setColumns(10);
		txtnumero.setBackground(SystemColor.menu);
		txtnumero.setBounds(955, 5, 2, 9);
		panel_datos.add(txtnumero);
		
		lblextiende = new JLabel("Nombre de quien extiende");
		lblextiende.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblextiende.setBounds(504, 383, 232, 25);
		panel_datos.add(lblextiende);
		
		txtextiende = new JTextField();
		txtextiende.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtextiende.setColumns(10);
		txtextiende.setBounds(504, 407, 232, 33);
		txtextiende.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
        	    validaciones.validarNombresyApellidos(e, txtextiende, 70);
        	}

		});
		panel_datos.add(txtextiende);
		
		JLabel lblc = new JLabel("Cargo");
		lblc.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblc.setBounds(277, 381, 166, 25);
		panel_datos.add(lblc);
		
		cbxcargo_recibe = new JComboBox<String>();
		cbxcargo_recibe.setSelectedIndex(-1);
		cbxcargo_recibe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxcargo_recibe.setBounds(276, 405, 166, 33);
		panel_datos.add(cbxcargo_recibe);
		
		lblc_1 = new JLabel("Cargo");
		lblc_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblc_1.setBounds(763, 383, 166, 25);
		panel_datos.add(lblc_1);
		
		cbxcargo_extiende = new JComboBox<String>();
		cbxcargo_extiende.setSelectedIndex(-1);
		cbxcargo_extiende.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxcargo_extiende.setBounds(763, 407, 166, 33);
		panel_datos.add(cbxcargo_extiende);
		
		
		
        inicializarFormulario(); 
        habilitarEdicion();
        cargarCargosEnComboBox();
        
       
	}//fin class
	
	
		private void cerrar_ventana() {
			if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				System.exit(0);
		}
		
		
	    private void cargarNombresEmpleados() {
	        try {
	            Connection conn = new conexion().conectar();
	            String sql = "SELECT nombres_empleado FROM empleados";
	            PreparedStatement pst = conn.prepareStatement(sql);
	            ResultSet rs = pst.executeQuery();
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

	    private void cargarDatosEmpleado(String nombre) {
	        try {
	            Connection conn = new conexion().conectar();
	            String sql = "SELECT apellidos_empleado, correo_empleado, cargo_empleado, area_empleado, "
	            		+ "tel_empleado, identidad_empleado, "
	            		+ "id_empleado FROM empleados WHERE nombres_empleado = ?";
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
	    
	    
	 // rango de fechas de los JDateChooser
	    @SuppressWarnings("unused")
		private void establecerRangoFechas() {
	        Calendar cal = Calendar.getInstance(); 
	        Date fechaActual = cal.getTime(); 

	        
	        cal.add(Calendar.MONTH, -3);
	        Date fechaMinima = cal.getTime();

	        cal.setTime(fechaActual); 
	        cal.add(Calendar.MONTH, 3); 
	        Date fechaMaxima = cal.getTime();
	    }
	    
	    
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
	            JOptionPane.showMessageDialog(null, "Error al calcular el tiempo", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	    
	 
	    
	    public boolean validarCamposPermiso() {
	        if (cbxnombres.getSelectedItem() == null || cbxnombres.getSelectedItem().toString().trim().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "El campo 'Nombres' está vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
	            return false;
	        }
	       
	        if (txttotal_horas.getText().trim().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "El campo 'Total de horas' está vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
	            return false;
	        }
	        if (txamotivo.getText().trim().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "El campo 'Motivo de ausencia' está vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
	            return false;
	        }
	        
	        if (txtnombres_recibe.getText().trim().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "El campo 'Nombre de quien recibe' está vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
	            return false;
	        }
	        if (txtextiende.getText().trim().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "El campo 'Nombre de quien extiende' está vacío", "Advertencia", JOptionPane.WARNING_MESSAGE);
	            return false;
	        }

	        return true;
	    }

	    @SuppressWarnings("unused")
		private boolean validarRangoHoras(String totalHorasStr) {
	        try {
	            String[] tiempo = totalHorasStr.split(":");
	            int horas = Integer.parseInt(tiempo[0]);
	            int minutos = Integer.parseInt(tiempo[1]);

	            if (horas < 1 || horas > 23) {
	                return false;
	            }
	            if (minutos < 0 || minutos >= 60) {
	                return false;
	            }
	        } catch (Exception e) {
	            return false; 
	        }
	        return true;
	    }

	    
	    
	    
	    public void guardar_permiso_ausencia_laboral() {
	        if (!validarCamposPermiso()) {
	            return; 
	        }

	        permiso_ausencia_hora permiso = new permiso_ausencia_hora();
	        permiso.setId_empleado(Integer.parseInt(txtid.getText()));
	        permiso.setNombres_empleado(cbxnombres.getSelectedItem().toString());
	        permiso.setApellidos_empleado(txtapellidos.getText());
	        permiso.setIdentidad_empleado(txtidentidad.getText());
	        permiso.setTel_empleado(txttel.getText());
	        permiso.setCorreo_empleado(txtcorreo.getText());
	        permiso.setCargo_empleado(txtcargo.getText());
	        permiso.setArea_empleado(txtarea.getText());
	        permiso.setMotivo_ausencia(txamotivo.getText());
	        permiso.setNombres_recibe(txtnombres_recibe.getText());
	        permiso.setNombres_extiende(txtextiende.getText());

	        // Obtener los cargos de quien recibe y quien extiende desde los JComboBox
	        String cargoRecibe = cbxcargo_recibe.getSelectedItem().toString();
	        String cargoExtiende = cbxcargo_extiende.getSelectedItem().toString();
	        
	        Date fechaRecibe = new Date();


	        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	        String horaInicioStr = timeFormat.format(spinnerHoraInicio.getValue());
	        String horaFinStr = timeFormat.format(spinnerHoraFin.getValue());

	        Time desdeHora = Time.valueOf(horaInicioStr); 
	        Time hastaHora = Time.valueOf(horaFinStr);   

	        // Total de horas
	        String totalHorasStr = txttotal_horas.getText().trim();
	        if (totalHorasStr.isEmpty() || totalHorasStr.equals("00:00")) {
	            JOptionPane.showMessageDialog(null, "El total de horas no puede estar vacío o ser 00:00", "Error", JOptionPane.ERROR_MESSAGE);
	            return; 
	        }
	        Time totalHoras = Time.valueOf(totalHorasStr + ":00");

	        permiso.setTotal_horas(totalHoras);

	        
	        consultas_permiso_hora consulta = new consultas_permiso_hora();
	        if (consulta.guardar_permiso_hora(permiso, desdeHora, hastaHora, cargoRecibe, cargoExtiende, fechaRecibe)) {
	            JOptionPane.showMessageDialog(null, "El permiso por ausencia laboral se ha registrado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            permiso_hora_tabla tabla = new permiso_hora_tabla();
	            tabla.setVisible(true);
	            tabla.setLocationRelativeTo(null);
	            tabla.construirTabla();
	            
	            
	        } else {
	            JOptionPane.showMessageDialog(null, "Error al guardar el permiso por ausencia laboral", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }



	   
	    public int obtenerUltimoIdPermiso() {
	        int ultimoId = 0;
	        conexion conex = new conexion();

	        try {
	            Statement estatuto = conex.conectar().createStatement();
	            ResultSet rs = estatuto.executeQuery("SELECT MAX(id_permisos) FROM permisos_ausencia_laboral");

	            if (rs.next()) {
	                ultimoId = rs.getInt(1); 
	            }

	            rs.close();
	            estatuto.close();
	            conex.desconectar(null);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error al obtener el último Id de permiso", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	        return ultimoId;
	    }

	    
	    public void inicializarFormulario() {
	        int nuevoIdPermiso = obtenerUltimoIdPermiso() + 1; 
	        txtnumero_permiso.setText(String.valueOf(nuevoIdPermiso)); 
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
	        txttotal_horas.setText("");
	        spinnerHoraInicio.setValue(new java.util.Date(0, 0, 0, 0, 0));  
	        spinnerHoraFin.setValue(new java.util.Date(0, 0, 0, 0, 0));     
	    
	        
	    }
	  
	    
	    @SuppressWarnings("deprecation")
		public void actualizar_permiso_laboral() {
	        try {
	            if (!validarCamposPermiso()) {
	                return; 
	            }

	            // Obtener horas desde JSpinner
	            Date horaInicioDate = (Date) spinnerHoraInicio.getValue();
	            Date horaFinDate = (Date) spinnerHoraFin.getValue();
	            Calendar calendarHoraInicio = Calendar.getInstance();
	            calendarHoraInicio.setTime(horaInicioDate);
	            Time horaInicioTime = new Time(calendarHoraInicio.get(Calendar.HOUR_OF_DAY), calendarHoraInicio.get(Calendar.MINUTE), 0);

	            Calendar calendarHoraFin = Calendar.getInstance();
	            calendarHoraFin.setTime(horaFinDate);
	            Time horaFinTime = new Time(calendarHoraFin.get(Calendar.HOUR_OF_DAY), calendarHoraFin.get(Calendar.MINUTE), 0);

	            String fechaActualTexto = date_actual.getText();
	            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yy");
	            Date fechaUtil = formatoFecha.parse(fechaActualTexto);
	            java.sql.Date fechaSQL = new java.sql.Date(fechaUtil.getTime());

	            // Crear objeto permiso
	            permiso_ausencia_hora clase = new permiso_ausencia_hora();
	            consultas_permiso_hora consulta = new consultas_permiso_hora();

	            clase.setId_permisos(Integer.parseInt(txtnumero_permiso.getText())); 
	            clase.setNombres_empleado(cbxnombres.getSelectedItem().toString());
	            clase.setApellidos_empleado(txtapellidos.getText());
	            clase.setIdentidad_empleado(txtidentidad.getText());
	            clase.setId_empleado(Integer.parseInt(txtid.getText()));
	            clase.setTel_empleado(txttel.getText());
	            clase.setCorreo_empleado(txtcorreo.getText());
	            clase.setCargo_empleado(txtcargo.getText());
	            clase.setArea_empleado(txtarea.getText());

	            clase.setDesde_hora(horaInicioTime); 
	            clase.setHasta_hora(horaFinTime);    

	            // Total horas
	            String[] tiempo = txttotal_horas.getText().split(":");
	            int horas = Integer.parseInt(tiempo[0]);
	            int minutos = Integer.parseInt(tiempo[1]);
	            Time totalHoras = new Time(horas, minutos, 0); 
	            clase.setTotal_horas(totalHoras); 

	            // Motivo y fechas
	            clase.setMotivo_ausencia(txamotivo.getText());
	            clase.setNombres_recibe(txtnombres_recibe.getText());
	            clase.setNombres_extiende(txtextiende.getText());
	            clase.setFecha_recibe(fechaSQL);

	            // Obtener cargos desde JComboBox
	            String cargoRecibe = cbxcargo_recibe.getSelectedItem().toString();
	            String cargoExtiende = cbxcargo_extiende.getSelectedItem().toString();

	            if (consulta.actualizar_permiso_hora(clase, horaInicioTime, horaFinTime, fechaSQL, cargoRecibe, cargoExtiende)) {
	                JOptionPane.showMessageDialog(null, "Permiso por ausencia laboral actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	                permiso_hora_tabla ver_permiso = new permiso_hora_tabla();
	                ver_permiso.construirTabla(); 
	                ver_permiso.setLocationRelativeTo(null);
	                ver_permiso.setVisible(true);

	                dispose(); 
	            } else {
	                JOptionPane.showMessageDialog(null, "Error, no se puede actualizar el permiso por ausencia laboral", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, "Error al actualizar el permiso: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }


	    
	    
	    @SuppressWarnings("unused")
		private boolean permisoGuardadoEnBaseDeDatos(int numeroPermiso) {
	        boolean existe = false;
	        String sql = "SELECT COUNT(*) FROM permisos_ausencia_laboral WHERE id_permisos = ?";

	        try (Connection conn = new conexion().conectar();
	             PreparedStatement pst = conn.prepareStatement(sql)) {
	            
	            pst.setInt(1, numeroPermiso);
	            try (ResultSet rs = pst.executeQuery()) {
	                if (rs.next()) {
	                    existe = rs.getInt(1) > 0;
	                }
	            }

	        } catch (SQLException ex) {
	            System.err.println("Error al verificar si el permiso ya existe en la base de datos para id permisos" + numeroPermiso);
	            ex.printStackTrace();
	        }
	        return existe;
	    }
	   
	    
	    @SuppressWarnings("unused")
		private void generarPDF() {
	        // Validar si el registro ha sido guardado antes
	        if (txtnumero_permiso.getText().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Debe guardar el registro antes de generar el comprobante.", "Advertencia", JOptionPane.WARNING_MESSAGE);
	            return;
	        }

	        // Obtener valores de los campos
	        String nombreEmpleado = cbxnombres.getSelectedItem().toString();
	        String apellidosEmpleado = txtapellidos.getText();
	        String nombreExtiende = txtextiende.getText(); // Nombre de quien extiende
	        String cargoExtiende = cbxcargo_extiende.getSelectedItem().toString(); // Cargo de quien extiende
	        String nombreRecibe = txtnombres_recibe.getText(); // Nombre de quien recibe
	        String cargoRecibe = cbxcargo_recibe.getSelectedItem().toString(); // Cargo de quien recibe
	        String motivoAusencia = txamotivo.getText();
	        String totalHoras = txttotal_horas.getText();

	        // Obtener la fecha actual
	        LocalDate fechaActual = LocalDate.now();
	        int diaActual = fechaActual.getDayOfMonth();
	        String mesActual = fechaActual.getMonth().getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
	        int añoActual = fechaActual.getYear();

	        // Configurar el nombre del archivo
	        String nombreArchivo = "Permiso_" + nombreEmpleado + " "+ apellidosEmpleado.replaceAll("\\s+", "_") + "_" +
                    fechaActual.format(DateTimeFormatter.ofPattern("dd-MM-yy")) + ".pdf";
	        
	        // Configurar el JFileChooser
	        JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setDialogTitle("Guardar constancia de permiso por ausencia laboral");
	        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos PDF", "pdf"));
	        fileChooser.setSelectedFile(new File(nombreArchivo));

	        try {
	            int userSelection = fileChooser.showSaveDialog(this);
	            if (userSelection != JFileChooser.APPROVE_OPTION) {
	                JOptionPane.showMessageDialog(null, "Generación del comprobante cancelada.", "Cancelado", JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            File fileToSave = fileChooser.getSelectedFile();
	            String rutaArchivo = fileToSave.getAbsolutePath();
	            if (!rutaArchivo.toLowerCase().endsWith(".pdf")) {
	                rutaArchivo += ".pdf";
	            }

	            // Escribir el PDF
	            PdfWriter writer = new PdfWriter(rutaArchivo);
	            PdfDocument pdf = new PdfDocument(writer);
	            Document document = new Document(pdf);

	            // Número de permiso
	            document.add(new Paragraph("No. " + txtnumero_permiso.getText())
	                .setFontSize(12).setTextAlignment(TextAlignment.LEFT));
	            
	            // Agregar encabezado
	            encabezado_documentos encabezado = new encabezado_documentos();
	            encabezado.agregarEncabezado(document);
	            
	        

	            // Título
	            document.add(new Paragraph("Constancia de permiso por ausencia laboral")
	                .setBold().setFontSize(16).setTextAlignment(TextAlignment.CENTER));
	            document.add(new Paragraph("\n"));

	            // Cuerpo del documento
	            String textoCuerpo = "Yo " + nombreExtiende + ", como el suscrito(a) " + cargoExtiende + " del instituto Cristiano Bilingüe “El Mundo de los Niños”, "
	                    + "le autorizo ausentarse del establecimiento al empleado(a): " + nombreEmpleado + " " + apellidosEmpleado
	                    + ", por un término de " + totalHoras + " horas, "
	                    + "por motivo de: " + motivoAusencia + ".\n\n"
	                    + "En constancia de lo anterior, se firma esta autorización a los " + diaActual + " días del mes de " + mesActual + " del año " + añoActual + ".\n\n\n\n\n"
	                    + "Recibido por: " + nombreRecibe + "\nCargo: " + cargoRecibe;

	            document.add(new Paragraph(textoCuerpo).setFontSize(12).setTextAlignment(TextAlignment.LEFT));

	            document.close();

	            // Mensaje de éxito y abrir el archivo
	            JOptionPane.showMessageDialog(null, "PDF generado correctamente: \n" + rutaArchivo, "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            Desktop.getDesktop().open(new File(rutaArchivo));

	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error al generar el comprobante.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }


	    
	    
	    public void desactivarCampos() {
	        cbxnombres.setEnabled(false);
	        txtapellidos.setEditable(false);
	        txtcorreo.setEditable(false);
	        txtcargo.setEditable(false);
	        txtarea.setEditable(false);
	        txttel.setEditable(false);
	        txtidentidad.setEditable(false);
	        txtid.setEditable(false);
	        spinnerHoraInicio.setEnabled(false);
	        spinnerHoraFin.setEnabled(false);
	        txttotal_horas.setEditable(false);
	        txamotivo.setEditable(false);
	        txtnombres_recibe.setEditable(false);
	        txtextiende.setEditable(false);
	    }

	    
	    
	    public void cargarNombresEmpleadosEnPermisoNuevo() {
	        permiso_hora_nuevo ventanaPermiso = new permiso_hora_nuevo();

	        try {
	            Connection conn = new conexion().conectar();
	            String sql = "SELECT nombres_empleado FROM empleados";
	            PreparedStatement pst = conn.prepareStatement(sql);
	            ResultSet rs = pst.executeQuery();

	            ventanaPermiso.cbxnombres.removeAllItems(); 
	            ventanaPermiso.cbxnombres.addItem(""); 

	            while (rs.next()) {
	                ventanaPermiso.cbxnombres.addItem(rs.getString("nombres_empleado"));
	            }

	            rs.close();
	            pst.close();
	            conn.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error al cargar los nombres de los empleados", "Error", JOptionPane.ERROR_MESSAGE);
	        }

	        ventanaPermiso.setLocationRelativeTo(null);
	        ventanaPermiso.setVisible(true);
	    }
	    
	    
	    private void habilitarEdicion() {
	        chxeditar.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (chxeditar.isSelected()) {
	                    cbxnombres.setEnabled(true);
	                    txamotivo.setEditable(true);
	                    txtnombres_recibe.setEditable(true);
	                    spinnerHoraInicio.setEnabled(true);
	                    spinnerHoraFin.setEnabled(true);
	                    btnactualizar.setVisible(true);
	                    btnlimpiar.setVisible(true);
	                    txtextiende.setEditable(true);
	                } else {
	                    cbxnombres.setEnabled(false);
	                    txamotivo.setEditable(false);
	                    txtnombres_recibe.setEditable(false);
	                    spinnerHoraInicio.setEnabled(false);
	                    spinnerHoraFin.setEnabled(false);
	                    btnactualizar.setVisible(false);
	                    btnlimpiar.setVisible(false);
	                    txtextiende.setEditable(false);
	                }
	            }
	        });
	    }
	    
	    
	    private void cargarCargosEnComboBox() {
		    consultas_cargos consultas = new consultas_cargos();
		    List<String> cargos = consultas.obtenerCargos();
		    cbxcargo_recibe.removeAllItems();
		    cbxcargo_recibe.addItem(" ");
		    

		    cbxcargo_extiende.removeAllItems();
		    cbxcargo_extiende.addItem(" ");
		    
		    for (String cargo : cargos) {
		    	cbxcargo_recibe.addItem(cargo);
		    	cbxcargo_extiende.addItem(cargo);
		    }
		    
		    cbxcargo_recibe.setSelectedIndex(0);
		    cbxcargo_extiende.setSelectedIndex(0);
		}
}//end

