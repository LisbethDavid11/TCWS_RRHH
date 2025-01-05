package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.toedter.calendar.JDateChooser;

import clases.vacaciones;
import clases.validaciones;
import conexion.conexion;
import consultas.consultas_vacaciones;




@SuppressWarnings("serial")
public class vacaciones_nuevo extends JFrame {
	public JTextField txtidentidad;
	public JTextField txtapellidos;
	public JTextField txttel;
	public JTextField txtid;
	public JTextField txtcargo;
	public JTextField txtarea;
	public JTextField txttotal_dias;
	public JTextField txtfecha_actual;
	public JTextField txthora_actual;
	public JTextField txtedad;
	public JTextField txtsexo;
	public JTextField txtcorreo;
	public JTextField txtid_tabla;
	public JTextField txtantiguedad;
	public JTextField txtdias_correspondientes;
	public JTextField txtdias_pendientes;
	public JComboBox<String> cbxnombres;
	public JDateChooser fecha_nacimiento;
	public JDateChooser fecha_inicio;
	public JDateChooser fecha_finalizacion_v;
	public JDateChooser fecha_inicio_v;
	public JRadioButton radio_si;
	public JRadioButton radio_no;
	public ButtonGroup grupoPago;
	
	public JButton btnguardar;
	public JButton btnactualizar;
	public JButton btnregresar;
	public JButton btnlimpiar;
	public JCheckBox chxeditar;
	public JTextField txtdias_disponibles;
	public JLabel lblmensaje;
	public JLabel lblultima;
	
	
	LocalDate fechaActual = LocalDate.now();
	public JLabel lblExtendidoPor;
	public JTextField txtextendido;
	public JTextField txtcargo_ext;
	
	public vacaciones_nuevo() {
		
		getContentPane().setBackground(new Color(255, 255, 255));
    	setType(Type.UTILITY);
        setResizable(false);
        setBounds(100, 100, 1050, 630);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
        JPanel panel_datos = new JPanel();
        panel_datos.setLayout(null);
        panel_datos.setBackground(SystemColor.menu);
        panel_datos.setBounds(30, 74, 975, 498);
        getContentPane().add(panel_datos);
        
        txtidentidad = new JTextField();
        txtidentidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtidentidad.setEditable(false);
        txtidentidad.setColumns(10);
        txtidentidad.setBounds(266, 61, 208, 33);
        panel_datos.add(txtidentidad);
        
        txtapellidos = new JTextField();
        txtapellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtapellidos.setEditable(false);
        txtapellidos.setColumns(10);
        txtapellidos.setBounds(31, 121, 208, 33);
        panel_datos.add(txtapellidos);
        
        JLabel lblidentidad = new JLabel("Número de identidad");
        lblidentidad.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblidentidad.setBounds(268, 37, 191, 25);
        panel_datos.add(lblidentidad);
        
        JLabel lblnombres = new JLabel("Nombres");
        lblnombres.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblnombres.setBounds(31, 37, 166, 25);
        panel_datos.add(lblnombres);
        
        JLabel lblapellidos = new JLabel("Apellidos");
        lblapellidos.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblapellidos.setBounds(31, 97, 166, 25);
        panel_datos.add(lblapellidos);
        
        JLabel lbltelefono = new JLabel("Número de teléfono");
        lbltelefono.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbltelefono.setBounds(511, 37, 200, 25);
        panel_datos.add(lbltelefono);
        
        txttel = new JTextField();
        txttel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txttel.setEditable(false);
        txttel.setColumns(10);
        txttel.setBounds(511, 60, 208, 33);
        panel_datos.add(txttel);
        
        JLabel lblarea = new JLabel("Área");
        lblarea.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblarea.setBounds(750, 97, 200, 25);
        panel_datos.add(lblarea);
        
        JLabel lblcorreo_electronico = new JLabel("Fecha de nacimiento");
        lblcorreo_electronico.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblcorreo_electronico.setBounds(513, 98, 166, 25);
        panel_datos.add(lblcorreo_electronico);
        
        JLabel lblid = new JLabel("Id empleado");
        lblid.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblid.setBounds(267, 97, 140, 25);
        panel_datos.add(lblid);
        
        txtid = new JTextField();
        txtid.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtid.setEditable(false);
        txtid.setColumns(10);
        txtid.setBounds(267, 121, 90, 33);
        panel_datos.add(txtid);
        
        JLabel lblcargo = new JLabel("Cargo");
        lblcargo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblcargo.setBounds(749, 37, 200, 25);
        panel_datos.add(lblcargo);
        
        txtcargo = new JTextField();
        txtcargo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtcargo.setEditable(false);
        txtcargo.setColumns(10);
        txtcargo.setBounds(749, 61, 200, 33);
        panel_datos.add(txtcargo);
        
        txtarea = new JTextField();
        txtarea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtarea.setEditable(false);
        txtarea.setColumns(10);
        txtarea.setBounds(750, 121, 200, 33);
        panel_datos.add(txtarea);
        
        cbxnombres = new JComboBox<String>();
        cbxnombres.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cbxnombres.setBounds(30, 61, 208, 33);
        panel_datos.add(cbxnombres);
        
        fecha_inicio_v = new JDateChooser();
        fecha_inicio_v.setDateFormatString("dd-MM-yy");
        fecha_inicio_v.setBounds(188, 434, 133, 33);
        validaciones.deshabilitarEscrituraJDateChooser(fecha_inicio_v);
        panel_datos.add(fecha_inicio_v);
        
        JLabel lbldesde = new JLabel("Fecha de inicio");
        lbldesde.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbldesde.setBounds(188, 411, 133, 25);
        panel_datos.add(lbldesde);
        
        JLabel lblhasta = new JLabel("Fecha de finalización");
        lblhasta.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblhasta.setBounds(360, 411, 168, 25);
        panel_datos.add(lblhasta);
        
        txttotal_dias = new JTextField();
        txttotal_dias.setHorizontalAlignment(SwingConstants.CENTER);
        txttotal_dias.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txttotal_dias.setEditable(false);
        txttotal_dias.setColumns(10);
        txttotal_dias.setBounds(693, 350, 44, 33);
        panel_datos.add(txttotal_dias);
        
        JLabel lbltotal1 = new JLabel("Total de días");
        lbltotal1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbltotal1.setBounds(578, 352, 105, 29);
        panel_datos.add(lbltotal1);
        
        JLabel lblDatosDel_1 = new JLabel("_______ Datos del empleado__________________________________________________________________________________");
        lblDatosDel_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblDatosDel_1.setForeground(Color.GRAY);
        lblDatosDel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDatosDel_1.setBounds(30, 10, 919, 28);
        panel_datos.add(lblDatosDel_1);
        
        JLabel lblhoy_es = new JLabel("Fecha actual");
        lblhoy_es.setForeground(SystemColor.inactiveCaptionText);
        lblhoy_es.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblhoy_es.setBounds(747, 268, 111, 25);
        panel_datos.add(lblhoy_es);
        
        txtfecha_actual = new JTextField();
        txtfecha_actual.setText("11-09-24");
        txtfecha_actual.setHorizontalAlignment(SwingConstants.CENTER);
        txtfecha_actual.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtfecha_actual.setEditable(false);
        txtfecha_actual.setColumns(10);
        txtfecha_actual.setBackground(SystemColor.menu);
        txtfecha_actual.setBounds(855, 264, 95, 33);
        panel_datos.add(txtfecha_actual);
        
        JLabel lblDatosDel_2 = new JLabel("_______ Datos de las vacaciones __________________________________________________________________________________");
        lblDatosDel_2.setHorizontalAlignment(SwingConstants.LEFT);
        lblDatosDel_2.setForeground(Color.GRAY);
        lblDatosDel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDatosDel_2.setBounds(31, 226, 919, 28);
        panel_datos.add(lblDatosDel_2);
        
        fecha_finalizacion_v = new JDateChooser();
        fecha_finalizacion_v.setDateFormatString("dd-MM-yy");
        fecha_finalizacion_v.setBounds(360, 434, 133, 33);
        validaciones.deshabilitarEscrituraJDateChooser(fecha_finalizacion_v);
        panel_datos.add(fecha_finalizacion_v);
        
        JLabel lblhoy_es_1 = new JLabel("Hora actual");
        lblhoy_es_1.setForeground(SystemColor.inactiveCaptionText);
        lblhoy_es_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblhoy_es_1.setBounds(534, 268, 111, 25);
        panel_datos.add(lblhoy_es_1);
        
        txthora_actual = new JTextField();
        txthora_actual.setHorizontalAlignment(SwingConstants.CENTER);
        txthora_actual.setFont(new Font("Tahoma", Font.BOLD, 14));
        txthora_actual.setEditable(false);
        txthora_actual.setColumns(10);
        txthora_actual.setBackground(SystemColor.menu);
        txthora_actual.setBounds(642, 264, 95, 33);
        panel_datos.add(txthora_actual);
        
        JLabel lblEdad = new JLabel("Edad");
        lblEdad.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblEdad.setBounds(407, 97, 61, 25);
        panel_datos.add(lblEdad);
        
        txtedad = new JTextField();
        txtedad.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtedad.setEditable(false);
        txtedad.setColumns(10);
        txtedad.setBounds(384, 121, 90, 33);
        panel_datos.add(txtedad);
        
        JLabel lblcorreo_electronico_1 = new JLabel("Correo electrónico");
        lblcorreo_electronico_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblcorreo_electronico_1.setBounds(266, 159, 166, 25);
        panel_datos.add(lblcorreo_electronico_1);
        
        txtsexo = new JTextField();
        txtsexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtsexo.setEditable(false);
        txtsexo.setColumns(10);
        txtsexo.setBounds(31, 183, 208, 33);
        panel_datos.add(txtsexo);
        
        txtcorreo = new JTextField();
        txtcorreo.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtcorreo.setEditable(false);
        txtcorreo.setColumns(10);
        txtcorreo.setBounds(266, 183, 208, 33);
        panel_datos.add(txtcorreo);
        
        JLabel lblcorreo_electronico_1_1 = new JLabel("Sexo");
        lblcorreo_electronico_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblcorreo_electronico_1_1.setBounds(31, 159, 90, 25);
        panel_datos.add(lblcorreo_electronico_1_1);
        
        fecha_nacimiento = new JDateChooser();
        fecha_nacimiento.setEnabled(false);
        fecha_nacimiento.setForeground(Color.BLACK);
        fecha_nacimiento.setDateFormatString("dd-MM-yy");
        fecha_nacimiento.setBounds(511, 121, 208, 33);
        panel_datos.add(fecha_nacimiento);
        
        txtid_tabla = new JTextField();
        txtid_tabla.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtid_tabla.setEditable(false);
        txtid_tabla.setColumns(10);
        txtid_tabla.setBounds(964, 10, 1, 5);
        panel_datos.add(txtid_tabla);
        
        JLabel lblcorreo_electronico_1_2 = new JLabel("Antigüedad");
        lblcorreo_electronico_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblcorreo_electronico_1_2.setBounds(31, 271, 166, 25);
        panel_datos.add(lblcorreo_electronico_1_2);
        
        txtantiguedad = new JTextField();
        txtantiguedad.setHorizontalAlignment(SwingConstants.CENTER);
        txtantiguedad.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtantiguedad.setEditable(false);
        txtantiguedad.setColumns(10);
        txtantiguedad.setBounds(31, 295, 90, 33);
        panel_datos.add(txtantiguedad);
        
        JLabel lblcorreo_electronico_1_2_1 = new JLabel("meses");
        lblcorreo_electronico_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblcorreo_electronico_1_2_1.setBounds(126, 295, 71, 25);
        panel_datos.add(lblcorreo_electronico_1_2_1);
        
        JLabel lblcorreo_electronico_2 = new JLabel("Fecha de inicio laboral");
        lblcorreo_electronico_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblcorreo_electronico_2.setBounds(513, 159, 198, 25);
        panel_datos.add(lblcorreo_electronico_2);
        
        fecha_inicio = new JDateChooser();
        fecha_inicio.setForeground(Color.BLACK);
        fecha_inicio.setEnabled(false);
        fecha_inicio.setDateFormatString("dd-MM-yy");
        fecha_inicio.setBounds(511, 182, 208, 33);
        panel_datos.add(fecha_inicio);
        
        txtdias_correspondientes = new JTextField();
        txtdias_correspondientes.setHorizontalAlignment(SwingConstants.CENTER);
        txtdias_correspondientes.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtdias_correspondientes.setEditable(false);
        //txtdias_correspondientes.setEnabled(false);
        txtdias_correspondientes.setColumns(10);
        txtdias_correspondientes.setBounds(266, 297, 90, 33);
        panel_datos.add(txtdias_correspondientes);
        
        JLabel lblcorreo_electronico_1_2_2 = new JLabel("Días correspondientes anual");
        lblcorreo_electronico_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblcorreo_electronico_1_2_2.setBounds(215, 271, 219, 25);
        panel_datos.add(lblcorreo_electronico_1_2_2);
        
        radio_si = new JRadioButton("Si");
        radio_si.setFont(new Font("Tahoma", Font.PLAIN, 15));
        radio_si.setBounds(31, 434, 53, 33);
        panel_datos.add(radio_si);
        
        radio_no = new JRadioButton("No");
        radio_no.setFont(new Font("Tahoma", Font.PLAIN, 15));
        radio_no.setBounds(94, 434, 53, 33);
        panel_datos.add(radio_no);
        
        grupoPago = new ButtonGroup();
        grupoPago.add(radio_si);
        grupoPago.add(radio_no);
        
        JLabel lblPagadas = new JLabel("Pagadas");
        lblPagadas.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblPagadas.setBounds(31, 411, 90, 25);
        panel_datos.add(lblPagadas);
        
        JLabel lbldias_pendientes = new JLabel("Días disponibles");
        lbldias_pendientes.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbldias_pendientes.setBounds(764, 354, 133, 25);
        panel_datos.add(lbldias_pendientes);
        
        txtdias_disponibles = new JTextField();
        txtdias_disponibles.setHorizontalAlignment(SwingConstants.CENTER);
        txtdias_disponibles.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtdias_disponibles.setEditable(false);
        txtdias_disponibles.setColumns(10);
        txtdias_disponibles.setBounds(906, 350, 44, 33);
        panel_datos.add(txtdias_disponibles);
        
        JLabel lblVacaciones = new JLabel("DATOS DE LAS VACACIONES");
        lblVacaciones.setHorizontalAlignment(SwingConstants.LEFT);
        lblVacaciones.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblVacaciones.setBackground(new Color(255, 153, 0));
        lblVacaciones.setBounds(30, 28, 442, 36);
        getContentPane().add(lblVacaciones);
        
        JPanel panel_botones = new JPanel();
        panel_botones.setLayout(null);
        panel_botones.setBackground(SystemColor.menu);
        panel_botones.setBounds(489, 10, 516, 65);
        getContentPane().add(panel_botones);
        
        btnguardar = new JButton("Guardar");
        btnguardar.setToolTipText("Guardar registro");
        btnguardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarVacaciones();
                calcularDiasPendientes();

            }
        });

        btnguardar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnguardar.setBackground(UIManager.getColor("Button.highlight"));
        btnguardar.setBounds(397, 17, 90, 23);
        panel_botones.add(btnguardar);
        
        btnactualizar = new JButton("Actualizar");
        btnactualizar.setToolTipText("Actualizar registro");
        btnactualizar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		actualizarVacaciones();
        		calcularDiasPendientes();

        	}
        });
        btnactualizar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnactualizar.setBackground(UIManager.getColor("Button.highlight"));
        btnactualizar.setBounds(397, 17, 90, 23);
        panel_botones.add(btnactualizar);
        
        btnlimpiar = new JButton("Limpiar");
        btnlimpiar.setToolTipText("Limpiar registro");
        btnlimpiar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		limpiarCampos();
        	}
        });
        btnlimpiar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnlimpiar.setBackground(UIManager.getColor("Button.highlight"));
        btnlimpiar.setBounds(302, 17, 90, 23);
        panel_botones.add(btnlimpiar);
        
        btnregresar = new JButton("Regresar");
        btnregresar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		vacaciones_tabla tabla = new vacaciones_tabla();
				tabla.setVisible(true);
				tabla.setLocationRelativeTo(null);
				tabla.construirTabla();
				dispose();
        		
        	}
        });
        btnregresar.setToolTipText("Regresar a la tabla");
        btnregresar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnregresar.setBackground(UIManager.getColor("Button.highlight"));
        btnregresar.setBounds(10, 17, 90, 23);
        panel_botones.add(btnregresar);
        
        lblmensaje = new JLabel("Este empleado ya tomó todos los dias que le correspondian por este año");
        lblmensaje.setForeground(Color.RED);
        lblmensaje.setFont(new Font("Tahoma", Font.PLAIN, 10));
        lblmensaje.setBounds(360, 305, 353, 25);
        lblmensaje.setVisible(false);
        panel_datos.add(lblmensaje);
        
        txtextendido = new JTextField();
        txtextendido.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtextendido.setBounds(600, 434, 154, 33);
        panel_datos.add(txtextendido);
        
        
        JLabel lblCargo_ext = new JLabel("Cargo");
        lblCargo_ext.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblCargo_ext.setBounds(796, 411, 133, 25);
        panel_datos.add(lblCargo_ext);
        
        txtcargo_ext = new JTextField();
        txtcargo_ext.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtcargo_ext.setColumns(10);
        txtcargo_ext.setBounds(796, 434, 154, 33);
        panel_datos.add(txtcargo_ext);
        
        lblExtendidoPor = new JLabel("Extendido por");
        lblExtendidoPor.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblExtendidoPor.setBounds(600, 411, 137, 25);
        panel_datos.add(lblExtendidoPor);
        
        txtdias_pendientes = new JTextField();
        txtdias_pendientes.setHorizontalAlignment(SwingConstants.CENTER);
        txtdias_pendientes.setText("0");
        txtdias_pendientes.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtdias_pendientes.setEditable(false);
        txtdias_pendientes.setColumns(10);
        txtdias_pendientes.setBounds(404, 350, 44, 33);
        txtdias_pendientes.setVisible(false);
        panel_datos.add(txtdias_pendientes);
        
        lblultima = new JLabel("Días pendientes a la última fecha de vacaciones");
        lblultima.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblultima.setBounds(31, 354, 371, 25);
        lblultima.setVisible(false);
        panel_datos.add(lblultima);
        
        chxeditar = new JCheckBox("Editar registro");
        chxeditar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (chxeditar.isSelected()) {
		        	 
		             habilitarCampos(true); 
		             btnactualizar.setVisible(true); 
		             btnlimpiar.setVisible(true);
		         } else {
		        	 
		             habilitarCampos(false); 
		             btnactualizar.setVisible(false);
		             btnlimpiar.setVisible(false);
		         }
        	}
        });
        
        chxeditar.setFont(new Font("Tahoma", Font.PLAIN, 13));
        chxeditar.setBounds(190, 17, 105, 21);
        panel_botones.add(chxeditar);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				cerrar_ventana();
			}
			});
		
		
		 cargarNombresEmpleados();

		 cbxnombres.addActionListener(e -> {
			    String nombreEmpleado = cbxnombres.getSelectedItem().toString();
			    if (nombreEmpleado == null || nombreEmpleado.trim().isEmpty()) {
			        limpiarCampos();
			    } else {
			        llenarCamposEmpleado(nombreEmpleado);
			        int idEmpleado = obtenerValorEntero(txtid.getText(), 0);
			        verificarRegistrosAnteriores(idEmpleado);
			    }
			});

		 
		 
		 //este siiii
		 fecha_inicio_v.addPropertyChangeListener(evt -> {
			    System.out.println("Evento disparado en fecha_inicio_v: " + evt.getPropertyName());
			    calcularDiasEntreFechas();
			    calcularDiasPendientes();
			});

			fecha_finalizacion_v.addPropertyChangeListener(evt -> {
			    System.out.println("Evento disparado en fecha_finalizacion_v: " + evt.getPropertyName());
			    calcularDiasEntreFechas();
			    calcularDiasPendientes();
			});



        

		asignarFechaActual();
        asignarHoraActual();
        
        
        
           int id = validateAndGetId();
           if (id == -1) {
               return;
           }
        
        
        String nombreOriginal = obtenerNombreOriginal();
        String nombreNuevo = cbxnombres.getSelectedItem().toString();

        if (!nombreOriginal.equals(nombreNuevo)) {
            actualizarDiasPendientes(nombreOriginal);
        }

        actualizarDiasPendientes(nombreNuevo);
        int diasCorrespondientes1 = Integer.parseInt(txtdias_correspondientes.getText());
        int diasTomados = obtenerDiasTomados(cbxnombres.getSelectedItem().toString()); 
        int diasPendientes = diasCorrespondientes1 - diasTomados;
        txtdias_disponibles.setText(String.valueOf(diasPendientes));
        
        
       
       /* if (diasPendientes == 0) {
            lblmensaje.setVisible(true);  // Mostrar el mensaje de advertencia
            btnguardar.setEnabled(false);  // Desactivar el botón de guardar
            btnactualizar.setEnabled(false);  // Desactivar el botón de actualizar
        } else {
            lblmensaje.setVisible(false);  // Ocultar el mensaje si tiene días pendientes
            btnguardar.setEnabled(true);   // Activar el botón de guardar
            btnactualizar.setEnabled(true);  // Activar el botón de actualizar
        }*/
        
     // Cuando se calcule el total de días tomados
        obtenerDiasTomados(nombreNuevo); // Método donde calculas el total de días de vacaciones tomados
        validarTotalDias(); // Método para calcular los días pendientes, basado en el nuevo registro

     // Verificar si el empleado ya tiene registros anteriores
        if (diasTomados > 0) {
            txtdias_pendientes.setText(String.valueOf(diasPendientes)); // Mostrar días pendientes anteriores
            txtdias_pendientes.setVisible(true);
            lblultima.setVisible(true);
        } else {
            txtdias_pendientes.setText("0"); // Si no tiene registros, establecer en 0
            txtdias_pendientes.setVisible(false);
            lblultima.setVisible(false);
        }


        
        
	
	}//class
	 
	// Método 1: Cargar nombres de empleados
	public void cargarNombresEmpleados() {
	    Connection con = null;
	    try {
	        con = new conexion().conectar();
	        String sql = "SELECT nombres_empleado FROM empleados";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();

	        cbxnombres.removeAllItems();
	        cbxnombres.addItem("");

	        while (rs.next()) {
	            cbxnombres.addItem(rs.getString("nombres_empleado"));
	        }

	        cbxnombres.setSelectedIndex(0);
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al cargar los nombres");
	    } finally {
	        if (con != null) {
	            try {
	                con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	
	private boolean tieneRegistrosPrevios(int idEmpleado) {
	    Connection con = null;
	    try {
	        con = new conexion().conectar();
	        String sql = "SELECT COUNT(*) AS total FROM vacaciones WHERE id_empleado = ?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, idEmpleado);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            int total = rs.getInt("total");
	            System.out.println("Registros previos encontrados: " + total); // Depuración
	            return total > 0; // Retorna true si hay al menos un registro
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(this, "Error al verificar registros previos: " + e.getMessage(),
	                "Error", JOptionPane.ERROR_MESSAGE);
	    } finally {
	        cerrarConexion(con);
	    }
	    return false; // Retorna false si no hay registros
	}




	
	
	// Método para calcular los días pendientes después de un nuevo registro
	private void validarTotalDias() {
	    try {
	        int diasCorrespondientes = obtenerValorEntero(txtdias_correspondientes.getText(), 0);
	        int diasTomados = obtenerValorEntero(txttotal_dias.getText(), 0);
	        int diasPendientes = obtenerValorEntero(txtdias_disponibles.getText(), diasCorrespondientes);

	        if (diasTomados > diasPendientes) {
	            JOptionPane.showMessageDialog(this, "El total de días tomados no puede exceder los días pendientes.", "Error", JOptionPane.ERROR_MESSAGE);
	            txttotal_dias.setText("0");
	            return;
	        }

	        int diasRestantes = diasPendientes - diasTomados;
	        txtdias_disponibles.setText(String.valueOf(diasRestantes));

	        if (diasRestantes == 0) {
	            lblmensaje.setVisible(true);
	            btnguardar.setEnabled(false);
	        } else {
	            lblmensaje.setVisible(false);
	            btnguardar.setEnabled(true);
	        }
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this, "Error al calcular los días pendientes. Verifique los valores ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}






	

	// Verificar si el empleado ya tiene registros anteriores y mostrar los días pendientes
	public void verificarRegistrosAnteriores(int idEmpleado) {
	    if (!tieneRegistrosPrevios(idEmpleado)) {
	        // Caso de nuevo registro
	        lblmensaje.setVisible(false); // Ocultar advertencia
	        btnguardar.setEnabled(true);  // Permitir guardar
	        btnactualizar.setEnabled(false); // No permitir actualizar
	        txtdias_disponibles.setText(txtdias_correspondientes.getText()); // Todos los días disponibles
	        txtdias_pendientes.setVisible(false); // Ocultar días pendientes
	        lblultima.setVisible(false); // Ocultar etiqueta de días pendientes
	    } else {
	        // Caso de registros previos
	        int diasPendientes = cargarDiasPendientesUltimoRegistro(idEmpleado);
	        txtdias_disponibles.setText(String.valueOf(diasPendientes));
	        txtdias_pendientes.setVisible(true);
	        lblultima.setVisible(true);
	        actualizarInterfazSegunDiasPendientes(diasPendientes);
	    }
	}






	
	
	
	
	
	public void cargarAntiguedadEmpleado(Date fechaInicio) {
	    LocalDate inicio;
	    
	    if (fechaInicio instanceof java.sql.Date) {
	        inicio = ((java.sql.Date) fechaInicio).toLocalDate();
	    } else {
	        inicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    }
	    
	    LocalDate ahora = LocalDate.now();
	    long meses = ChronoUnit.MONTHS.between(inicio, ahora);
	    txtantiguedad.setText(String.valueOf(meses));
	}
	
	
	public int obtenerUltimoAnoVacaciones(int idEmpleado) {
	    Connection con = null;
	    int ultimoAno = LocalDate.now().getYear(); // Inicializamos con el año actual por defecto

	    try {
	        con = new conexion().conectar();
	        String sql = "SELECT MAX(YEAR(fecha_inicio_v)) AS ultimo_ano FROM vacaciones WHERE id_empleado = ?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, idEmpleado);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            ultimoAno = rs.getInt("ultimo_ano");
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al obtener el último año de vacaciones" , "Error", JOptionPane.ERROR_MESSAGE);
	    } finally {
	        if (con != null) {
	            new conexion().desconectar(con);
	        }
	    }

	    return ultimoAno;
	}

	
	
	public int verificarCambioDeAno(int diasPendientes, int diasCorrespondientes, int idEmpleado) {
	    LocalDate fechaActual = LocalDate.now(); 
	    int yearActual = fechaActual.getYear();

	    // Consulta el último año registrado para este empleado
	    int ultimoAnoRegistro = obtenerUltimoAnoVacaciones(idEmpleado); 

	    if (ultimoAnoRegistro < yearActual) {
	        return diasCorrespondientes + diasPendientes;
	    }

	    return diasCorrespondientes; // Si no ha cambiado de año, solo regresamos los días correspondientes
	}

	
	
	
	// Método 3: Calcular edad
	private void calcularEdad(Date fechaNacimiento) {
	    if (fechaNacimiento != null) {
	        LocalDate nacimiento;
	        if (fechaNacimiento instanceof java.sql.Date) {
	            nacimiento = ((java.sql.Date) fechaNacimiento).toLocalDate();
	        } else {
	            nacimiento = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        }
	        LocalDate ahora = LocalDate.now();
	        txtedad.setText(String.valueOf(Period.between(nacimiento, ahora).getYears()));
	    } else {
	        txtedad.setText("");
	    }
	}




	// Método 1: Llenar campos de empleado
	public void llenarCamposEmpleado(String nombreEmpleado) {
	    Connection con = null;

	    try {
	        con = new conexion().conectar();
	        String sql = "SELECT * FROM empleados WHERE nombres_empleado = ?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, nombreEmpleado);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            // Llenar datos del empleado
	            llenarDatosEmpleado(rs);

	            // Calcular la edad
	            calcularEdad(rs.getDate("nacimiento_empleado"));

	            // Calcular antigüedad y días correspondientes
	            int antiguedadMeses = calcularAntiguedad(rs.getDate("inicio_empleado"));
	            int diasCorrespondientes = calcularDiasCorrespondientes(antiguedadMeses);
	            txtdias_correspondientes.setText(String.valueOf(diasCorrespondientes));

	            // Calcular los días disponibles
	            int idEmpleado = rs.getInt("id_empleado");
	            calcularDiasDisponibles(idEmpleado);
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(this, "Error al cargar los datos del empleado: " + e.getMessage(),
	                "Error", JOptionPane.ERROR_MESSAGE);
	    } finally {
	        cerrarConexion(con);
	    }
	}





	



	// Método 4: Calcular antigüedad
	public int calcularAntiguedad(Date fechaInicio) {
	    LocalDate inicio;
	    if (fechaInicio instanceof java.sql.Date) {
	        inicio = ((java.sql.Date) fechaInicio).toLocalDate();
	    } else {
	        inicio = fechaInicio.toInstant()
	            .atZone(ZoneId.systemDefault())
	            .toLocalDate();
	    }
	    LocalDate ahora = LocalDate.now();

	    long meses = ChronoUnit.MONTHS.between(inicio, ahora);
	    int diasCorrespondientes;

	    if (meses < 10) {
	        diasCorrespondientes = (int) meses;
	    } else if (meses < 12) {
	        diasCorrespondientes = 9;
	    } else if (meses == 12) {
	        diasCorrespondientes = 10;
	    } else if (meses < 24) {
	        diasCorrespondientes = 10 + (int) (meses - 12);
	    } else if (meses < 36) {
	        diasCorrespondientes = 12;
	    } else if (meses < 48) {
	        diasCorrespondientes = 15;
	    } else {
	        diasCorrespondientes = 20;
	    }

	    SwingUtilities.invokeLater(() -> {
	        txtantiguedad.setText(String.valueOf(meses));
	        txtdias_correspondientes.setText(String.valueOf(diasCorrespondientes));
	    });

	    return (int) meses;
	}

	// Método 5: Calcular días correspondientes según antigüedad
	private int calcularDiasCorrespondientes(int antiguedadMeses) {
	    int diasCorrespondientes = 0;

	    if (antiguedadMeses >= 48) {  // 4 años o más
	        diasCorrespondientes = 20;
	    } else if (antiguedadMeses >= 36) {  // 3 años
	        diasCorrespondientes = 15;
	    } else if (antiguedadMeses >= 24) {  // 2 años
	        diasCorrespondientes = 12;
	    } else if (antiguedadMeses >= 12) {  // 1 año
	        diasCorrespondientes = 10;
	    } else {  
	        // Asignar proporcionalmente si es menor a 1 año (0.83 días por mes)
	        diasCorrespondientes = (int) Math.ceil((antiguedadMeses / 12.0) * 10);
	    }
	    return diasCorrespondientes;
	}



	// Método 6: Obtener días tomados
	/*private int obtenerDiasTomadosPorId(int idEmpleado) {
	    int diasTomados = 0;
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        con = new conexion().conectar();
	        String sql = "SELECT SUM(total_dias) AS dias_tomados FROM vacaciones WHERE id_empleado = ?";
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, idEmpleado);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            diasTomados = rs.getInt("dias_tomados");
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(this, "Error al obtener los días tomados: " + e.getMessage(),
	                "Error", JOptionPane.ERROR_MESSAGE);
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return diasTomados;
	}*/


	
	// Método 7: Actualizar días pendientes
	private void actualizarDiasPendientes(String nombreEmpleado) {
	    try {
	        int diasCorrespondientes = obtenerValorEntero(txtdias_correspondientes.getText(), 0);
	        int diasTomados = obtenerDiasTomados(nombreEmpleado);
	        int diasPendientes = diasCorrespondientes - diasTomados;

	        if (diasPendientes < 0) diasPendientes = 0;

	        txtdias_disponibles.setText(String.valueOf(diasPendientes));
	        txtdias_pendientes.setText(String.valueOf(diasTomados));
	        lblultima.setVisible(diasTomados > 0);
	        txtdias_pendientes.setVisible(diasTomados > 0);

	        actualizarInterfazSegunDiasPendientes(diasPendientes);
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this, "Error al actualizar los días pendientes.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}


	
	private int obtenerDiasTomados(String nombreEmpleado) {
	    int diasTomados = 0;
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        con = new conexion().conectar();
	        String sql = "SELECT COALESCE(SUM(total_dias), 0) AS dias_tomados FROM vacaciones WHERE nombres_empleado = ?";
	        ps = con.prepareStatement(sql);
	        ps.setString(1, nombreEmpleado);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            diasTomados = rs.getInt("dias_tomados");
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(this, "Error al obtener los días tomados: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    } finally {
	        cerrarConexion(con);
	    }

	    return diasTomados;
	}

	
	
	
	 // Nuevo método: cargar días pendientes del último registro
	private int cargarDiasPendientesUltimoRegistro(int idEmpleado) {
	    Connection con = null;
	    int diasPendientes = 0;

	    try {
	        con = new conexion().conectar();
	        String sql = "SELECT dias_pendientes FROM vacaciones WHERE id_empleado = ? ORDER BY fecha_inicio_v DESC LIMIT 1";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, idEmpleado);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            diasPendientes = rs.getInt("dias_pendientes");
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(this, "Error al cargar los días pendientes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    } finally {
	        cerrarConexion(con);
	    }

	    return diasPendientes;
	}





	
   


 // Nuevo método: obtener valor entero seguro
	private int obtenerValorEntero(String valor, int valorPorDefecto) {
	    if (valor == null || valor.trim().isEmpty()) {
	        return valorPorDefecto;
	    }
	    try {
	        return Integer.parseInt(valor.trim());
	    } catch (NumberFormatException e) {
	        return valorPorDefecto;
	    }
	}
	
	

	// Método 9: Calcular días entre fechas
	private void calcularDiasEntreFechas() {
	    try {
	        Date fechaInicio = fecha_inicio_v.getDate();
	        Date fechaFin = fecha_finalizacion_v.getDate();

	        if (fechaInicio == null || fechaFin == null) {
	            txttotal_dias.setText("0");
	            return;
	        }

	        LocalDate localFechaInicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        LocalDate localFechaFin = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	        if (localFechaFin.isBefore(localFechaInicio)) {
	            JOptionPane.showMessageDialog(this, "La fecha de finalización debe ser posterior a la fecha de inicio.", "Error", JOptionPane.ERROR_MESSAGE);
	            txttotal_dias.setText("0");
	            return;
	        }

	        long diasEntreFechas = ChronoUnit.DAYS.between(localFechaInicio, localFechaFin) + 1;
	        txttotal_dias.setText(String.valueOf(diasEntreFechas));

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Error al calcular los días entre fechas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        txttotal_dias.setText("0");
	    }
	}


	
	private void calcularDiasPendientes() {
	    try {
	        int diasCorrespondientes = obtenerValorEntero(txtdias_correspondientes.getText(), 0);
	        int diasTomados = obtenerValorEntero(txttotal_dias.getText(), 0);
	        int diasPendientes = diasCorrespondientes - diasTomados;

	        if (diasPendientes < 0) diasPendientes = 0;

	        txtdias_disponibles.setText(String.valueOf(diasPendientes));

	        // Actualizar advertencias y botones
	        actualizarInterfazSegunDiasPendientes(diasPendientes);
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this, "Error al calcular los días pendientes.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	
	private void calcularDiasDisponibles(int idEmpleado) {
	    try {
	        int diasCorrespondientes = obtenerValorEntero(txtdias_correspondientes.getText(), 0);
	        int diasTomados = obtenerValorEntero(txttotal_dias.getText(), 0);
	        int diasPendientes = 0;

	        if (!tieneRegistrosPrevios(idEmpleado)) {
	            // Primer registro del empleado
	            diasPendientes = diasCorrespondientes;
	        } else {
	            // Registros previos: cargar días pendientes del último registro
	            diasPendientes = cargarDiasPendientesUltimoRegistro(idEmpleado);
	        }

	        // Calcular los días disponibles
	        int diasDisponibles = diasPendientes - diasTomados;
	        if (diasDisponibles < 0) diasDisponibles = 0;

	        // Actualizar los campos
	        txtdias_disponibles.setText(String.valueOf(diasDisponibles));
	        txtdias_pendientes.setText(String.valueOf(diasPendientes));
	        lblultima.setVisible(diasPendientes > 0);
	        txtdias_pendientes.setVisible(diasPendientes > 0);

	        actualizarInterfazSegunDiasPendientes(diasDisponibles);
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Error al calcular los días disponibles: " + e.getMessage(),
	                "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}



	



	
	



	public java.sql.Time validarYObtenerHora(String horaStr) {
	    try {
	        if (horaStr == null || horaStr.trim().isEmpty()) {
	            throw new IllegalArgumentException("La hora no puede estar vacía.");
	        }

	        String[] partes = horaStr.split(":");
	        if (partes.length != 2) {
	            throw new IllegalArgumentException("El formato de hora debe ser HH:mm.");
	        }

	        int horas = Integer.parseInt(partes[0]);
	        int minutos = Integer.parseInt(partes[1]);

	        if (horas < 0 || horas > 23 || minutos < 0 || minutos > 59) {
	            throw new IllegalArgumentException("La hora debe estar en el rango 00:00 a 23:59.");
	        }

	        return java.sql.Time.valueOf(horaStr + ":00"); // Formato HH:mm:ss
	    } catch (IllegalArgumentException e) {
	        JOptionPane.showMessageDialog(this, e.getMessage(), "Error en la hora", JOptionPane.ERROR_MESSAGE);
	        return null;
	    }
	}








	// Método 10: Validar campos antes de guardar/actualizar
    public boolean validarCamposVacaciones() {
        if (cbxnombres.getSelectedIndex() == -1 || cbxnombres.getSelectedItem().toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un nombre.", "Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!radio_si.isSelected() && !radio_no.isSelected()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar si las vacaciones son pagadas o no.", "Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (fecha_inicio_v.getDate() == null || fecha_finalizacion_v.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar las fechas de inicio y finalización.", "Validación", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true; // Validación básica sin comprobar días
    }






	// Método 11: Guardar vacaciones
    public void guardarVacaciones() {
        if (!validarCamposVacaciones()) {
            return; // Detener si los campos no son válidos
        }

        int idEmpleado = obtenerValorEntero(txtid.getText(), 0);
        int diasDisponibles = obtenerValorEntero(txtdias_disponibles.getText(), 0);

        if (diasDisponibles == 0 && tieneRegistrosPrevios(idEmpleado)) {
            JOptionPane.showMessageDialog(this, "El empleado ya ha utilizado todos los días disponibles.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        vacaciones claseVacaciones = new vacaciones();
        try {
            // Configurar datos del formulario en la clase de vacaciones
            claseVacaciones.setId_empleado(idEmpleado);
            claseVacaciones.setNombres_empleado(cbxnombres.getSelectedItem().toString());
            claseVacaciones.setApellidos_empleado(txtapellidos.getText());
            claseVacaciones.setIdentidad_empleado(txtidentidad.getText());
            claseVacaciones.setTel_empleado(txttel.getText());
            claseVacaciones.setCorreo_empleado(txtcorreo.getText());
            claseVacaciones.setCargo_empleado(txtcargo.getText());
            claseVacaciones.setArea_empleado(txtarea.getText());
            claseVacaciones.setNacimiento_empleado(fecha_nacimiento.getDate());
            claseVacaciones.setSexo_empleado(txtsexo.getText());
            claseVacaciones.setEdad_empleado(obtenerValorEntero(txtedad.getText(), 0));
            claseVacaciones.setAntiguedad(obtenerValorEntero(txtantiguedad.getText(), 0));
            claseVacaciones.setDias_correspondientes(obtenerValorEntero(txtdias_correspondientes.getText(), 0));
            claseVacaciones.setTotal_dias(obtenerValorEntero(txttotal_dias.getText(), 0));
            claseVacaciones.setDias_pendientes(obtenerValorEntero(txtdias_disponibles.getText(), 0));
            claseVacaciones.setPagadas(radio_si.isSelected() ? "Si" : "No");
            claseVacaciones.setFecha_inicio_v(fecha_inicio_v.getDate());
            claseVacaciones.setFecha_finalizacion_v(fecha_finalizacion_v.getDate());
            claseVacaciones.setExtendido(txtextendido.getText());
            claseVacaciones.setCargo_ext(txtcargo_ext.getText());

            // Asignar fecha y hora actual
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

            String fechaActualStr = dateFormat.format(new Date());
            String horaActualStr = timeFormat.format(new Date());

            claseVacaciones.setFecha_actual(dateFormat.parse(fechaActualStr));
            claseVacaciones.setHora_actual(Time.valueOf(horaActualStr));

            // Guardar el registro en la base de datos
            consultas_vacaciones consulta = new consultas_vacaciones();
            boolean exito = consulta.guardarVacaciones(claseVacaciones);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Vacaciones guardadas correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                calcularDiasDisponibles(idEmpleado); // Actualizar días disponibles después de guardar
                vacaciones_tabla tabla = new vacaciones_tabla();
                tabla.setVisible(true);
                tabla.setLocationRelativeTo(null);
                dispose();
                
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar las vacaciones.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar las vacaciones: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }





	// Método 12: Actualizar vacaciones
    public void actualizarVacaciones() {
        if (!validarCamposVacaciones()) {
            return; // Detener si los campos no son válidos
        }

        int idEmpleado = obtenerValorEntero(txtid.getText(), 0);

        vacaciones claseVacaciones = new vacaciones();
        try {
            // Configurar datos del formulario en la clase de vacaciones
            claseVacaciones.setId_empleado(idEmpleado);
            claseVacaciones.setNombres_empleado(cbxnombres.getSelectedItem().toString());
            claseVacaciones.setApellidos_empleado(txtapellidos.getText());
            claseVacaciones.setIdentidad_empleado(txtidentidad.getText());
            claseVacaciones.setTel_empleado(txttel.getText());
            claseVacaciones.setCorreo_empleado(txtcorreo.getText());
            claseVacaciones.setCargo_empleado(txtcargo.getText());
            claseVacaciones.setArea_empleado(txtarea.getText());
            claseVacaciones.setNacimiento_empleado(fecha_nacimiento.getDate());
            claseVacaciones.setSexo_empleado(txtsexo.getText());
            claseVacaciones.setEdad_empleado(obtenerValorEntero(txtedad.getText(), 0));
            claseVacaciones.setAntiguedad(obtenerValorEntero(txtantiguedad.getText(), 0));
            claseVacaciones.setDias_correspondientes(obtenerValorEntero(txtdias_correspondientes.getText(), 0));
            claseVacaciones.setTotal_dias(obtenerValorEntero(txttotal_dias.getText(), 0));
            claseVacaciones.setDias_pendientes(obtenerValorEntero(txtdias_disponibles.getText(), 0));
            claseVacaciones.setPagadas(radio_si.isSelected() ? "Si" : "No");
            claseVacaciones.setFecha_inicio_v(fecha_inicio_v.getDate());
            claseVacaciones.setFecha_finalizacion_v(fecha_finalizacion_v.getDate());
            claseVacaciones.setExtendido(txtextendido.getText());
            claseVacaciones.setCargo_ext(txtcargo_ext.getText());

            // Actualizar el registro en la base de datos
            consultas_vacaciones consulta = new consultas_vacaciones();
            boolean exito = consulta.actualizarVacaciones(claseVacaciones);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Vacaciones actualizadas correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                calcularDiasDisponibles(idEmpleado); // Actualizar días disponibles después de actualizar
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar las vacaciones.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar las vacaciones: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }




	// Método 14: Limpiar campos
	public void limpiarCampos() {
	    cbxnombres.setSelectedIndex(0);
	    txtapellidos.setText("");
	    txtidentidad.setText("");
	    txttel.setText("");
	    txtid.setText("");
	    txtcargo.setText("");
	    txtarea.setText("");
	    txtcorreo.setText("");
	    txtedad.setText("");
	    fecha_inicio_v.setDate(null);
	    fecha_finalizacion_v.setDate(null);
	    fecha_nacimiento.setDate(null);
	    txtantiguedad.setText("");
	    txtdias_correspondientes.setText("");
	    txttotal_dias.setText("0");
	    txtdias_disponibles.setText("0");
	    grupoPago.clearSelection();
	    txtextendido.setText(" ");
	    txtcargo_ext.setText(" ");
	}


	// Método 15: Asignar fecha actual
	public void asignarFechaActual() {
	    SwingUtilities.invokeLater(() -> {
	        LocalDate fechaActual = LocalDate.now();
	        DateTimeFormatter formateadorFecha = DateTimeFormatter.ofPattern("dd-MM-yy");
	        String fechaFormateada = fechaActual.format(formateadorFecha);
	        txtfecha_actual.setText(fechaFormateada);
	    });
	}

	// Método 16: Asignar hora actual
	public void asignarHoraActual() {
	    SwingUtilities.invokeLater(() -> {
	        LocalTime horaActual = LocalTime.now();
	        DateTimeFormatter formateadorHora = DateTimeFormatter.ofPattern("HH:mm");
	        String horaFormateada = horaActual.format(formateadorHora);
	        txthora_actual.setText(horaFormateada);  // Formato completo HH:mm:ss
	    });
	}


	// Método 17: Habilitar campos
	private void habilitarCampos(boolean habilitar) {
	    cbxnombres.setEnabled(habilitar);
	    fecha_inicio_v.setEnabled(habilitar);
	    fecha_finalizacion_v.setEnabled(habilitar);
	    radio_si.setEnabled(habilitar);
	    radio_no.setEnabled(habilitar);
	}

	// Método 18: Validar y obtener ID
	private int validateAndGetId() {
	    String input = txtid_tabla.getText();
	    if (input != null && !input.trim().isEmpty()) {
	        try {
	            return Integer.parseInt(input.trim());
	        } catch (NumberFormatException e) {
	            return -1;
	        }
	    } else {
	        return -1;
	    }
	}

	// Método 19: Obtener nombre original
	private String obtenerNombreOriginal() {
	    int id = validateAndGetId();
	    if (id == -1) {
	        return "";
	    }

	    String nombreOriginal = "";
	    Connection con = null;
	    try {
	        con = new conexion().conectar();
	        String sql = "SELECT nombres_empleado FROM vacaciones WHERE id_vacaciones = ?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            nombreOriginal = rs.getString("nombres_empleado");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (con != null) {
	            try {
	                con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return nombreOriginal;
	}
	
	private void llenarDatosEmpleado(ResultSet rs) throws SQLException {
	    txtapellidos.setText(rs.getString("apellidos_empleado"));
	    txtidentidad.setText(rs.getString("identidad_empleado"));
	    txtsexo.setText(rs.getString("sexo_empleado"));
	    txtid.setText(rs.getString("id_empleado"));
	    txtcorreo.setText(rs.getString("correo_empleado"));
	    txttel.setText(rs.getString("tel_empleado"));
	    txtcargo.setText(rs.getString("cargo_empleado"));
	    txtarea.setText(rs.getString("area_empleado"));
	    fecha_inicio.setDate(rs.getDate("inicio_empleado"));
	    fecha_nacimiento.setDate(rs.getDate("nacimiento_empleado"));
	}
	

	
	private void actualizarInterfazSegunDiasPendientes(int diasDisponibles) {
	    if (diasDisponibles <= 0) {
	        lblmensaje.setVisible(true);  // Mostrar mensaje de advertencia
	        btnguardar.setEnabled(false);  // Desactivar el botón de guardar
	        btnactualizar.setEnabled(false);  // Desactivar el botón de actualizar
	    } else {
	        lblmensaje.setVisible(false);  // Ocultar mensaje de advertencia
	        btnguardar.setEnabled(true);  // Activar el botón de guardar
	        btnactualizar.setEnabled(true);  // Activar el botón de actualizar
	    }
	}



	
	private void cerrarConexion(Connection con) {
	    if (con != null) {
	        try {
	            con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}



	// Método 20: Cerrar ventana
	private void cerrar_ventana() {
	    if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
	            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
	        System.exit(0);
	}
}