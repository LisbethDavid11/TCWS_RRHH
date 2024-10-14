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
	public JTextField txtdias_pendientes;
	public JLabel lblmensaje;
	public JLabel lblultima;
	
	LocalDate fechaActual = LocalDate.now();
	private JTextField txtultima_fecha;
	
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
        fecha_inicio_v.setBounds(188, 434, 166, 33);
        validaciones.deshabilitarEscrituraJDateChooser(fecha_inicio_v);
        panel_datos.add(fecha_inicio_v);
        
        JLabel lbldesde = new JLabel("Fecha de inicio");
        lbldesde.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbldesde.setBounds(188, 411, 133, 25);
        panel_datos.add(lbldesde);
        
        JLabel lblhasta = new JLabel("Fecha de finalización");
        lblhasta.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblhasta.setBounds(407, 411, 168, 25);
        panel_datos.add(lblhasta);
        
        txttotal_dias = new JTextField();
        txttotal_dias.setHorizontalAlignment(SwingConstants.CENTER);
        txttotal_dias.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txttotal_dias.setEditable(false);
        txttotal_dias.setColumns(10);
        txttotal_dias.setBounds(797, 393, 44, 33);
        panel_datos.add(txttotal_dias);
        
        JLabel lbltotal1 = new JLabel("Total de días");
        lbltotal1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbltotal1.setBounds(641, 395, 105, 29);
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
        fecha_finalizacion_v.setBounds(407, 434, 166, 33);
        validaciones.deshabilitarEscrituraJDateChooser(fecha_finalizacion_v);
        panel_datos.add(fecha_finalizacion_v);
        
        JLabel lblhoy_es_1 = new JLabel("Hora actual");
        lblhoy_es_1.setForeground(SystemColor.inactiveCaptionText);
        lblhoy_es_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblhoy_es_1.setBounds(747, 307, 111, 25);
        panel_datos.add(lblhoy_es_1);
        
        txthora_actual = new JTextField();
        txthora_actual.setHorizontalAlignment(SwingConstants.CENTER);
        txthora_actual.setFont(new Font("Tahoma", Font.BOLD, 14));
        txthora_actual.setEditable(false);
        txthora_actual.setColumns(10);
        txthora_actual.setBackground(SystemColor.menu);
        txthora_actual.setBounds(855, 303, 95, 33);
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
        txtcorreo.setFont(new Font("Tahoma", Font.PLAIN, 14));
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
        
        JLabel lbldias_pendientes = new JLabel("Días pendientes");
        lbldias_pendientes.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbldias_pendientes.setBounds(641, 442, 133, 25);
        panel_datos.add(lbldias_pendientes);
        
        txtdias_pendientes = new JTextField();
        txtdias_pendientes.setHorizontalAlignment(SwingConstants.CENTER);
        txtdias_pendientes.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtdias_pendientes.setEditable(false);
        txtdias_pendientes.setColumns(10);
        txtdias_pendientes.setBounds(797, 434, 44, 33);
        panel_datos.add(txtdias_pendientes);
        
        JLabel lblVacaciones = new JLabel("VACACIONES");
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
            }
        });

        btnguardar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnguardar.setBackground(UIManager.getColor("Button.highlight"));
        btnguardar.setBounds(397, 17, 90, 23);
        panel_botones.add(btnguardar);
        
        btnactualizar = new JButton("Actualizar");
        btnactualizar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		actualizarVacaciones();
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
        
        txtultima_fecha = new JTextField();
        txtultima_fecha.setHorizontalAlignment(SwingConstants.CENTER);
        txtultima_fecha.setText("0");
        txtultima_fecha.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtultima_fecha.setEditable(false);
        txtultima_fecha.setColumns(10);
        txtultima_fecha.setBounds(404, 350, 44, 33);
        txtultima_fecha.setVisible(false);
        panel_datos.add(txtultima_fecha);
        
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
			    Object selectedItem = cbxnombres.getSelectedItem();
			    if (selectedItem == null || selectedItem.toString().trim().isEmpty()) {
			        limpiarCampos(); // Limpiar los campos si no se selecciona ningún empleado
			    } else {
			        llenarCamposEmpleado(selectedItem.toString()); // Cargar los datos del empleado seleccionado
			        resetearCamposVacaciones(); // Resetear los campos de vacaciones
			    }
			});

		
		fecha_finalizacion_v.addPropertyChangeListener(evt -> calcularDiasEntreFechas());
		fecha_inicio_v.addPropertyChangeListener(evt -> calcularDiasEntreFechas());
		
		fecha_inicio_v.addPropertyChangeListener("date", evt -> calcularDiasTotalesYActualizarPendientes());
        fecha_finalizacion_v.addPropertyChangeListener("date", evt -> calcularDiasTotalesYActualizarPendientes());



		asignarFechaActual();
        asignarHoraActual();
        
        
        calcularDiasEntreFechas();  // Método que calcula los días entre fechas
        calcularDiasPendientes();  
        
           int id = validateAndGetId();
           if (id == -1) {
               //JOptionPane.showMessageDialog(null, "El campo Id está vacío o tiene un valor no válido", "Error", JOptionPane.ERROR_MESSAGE);
               return;
           }
        
        
        String nombreOriginal = obtenerNombreOriginal();
        String nombreNuevo = cbxnombres.getSelectedItem().toString();

        if (!nombreOriginal.equals(nombreNuevo)) {
            // Realizar la actualización del nombre y los días pendientes para el nombre original
            actualizarDiasPendientes(nombreOriginal);
        }

        // Continuar con la actualización del registro con el nombre nuevo
        actualizarDiasPendientes(nombreNuevo);

        
        int diasCorrespondientes1 = Integer.parseInt(txtdias_correspondientes.getText());
        int diasTomados = obtenerDiasTomados(cbxnombres.getSelectedItem().toString()); // Método que obtiene los días tomados
        int diasPendientes = diasCorrespondientes1 - diasTomados;

        txtdias_pendientes.setText(String.valueOf(diasPendientes));
       
        if (diasPendientes == 0) {
            lblmensaje.setVisible(true);  // Mostrar el mensaje de advertencia
            btnguardar.setEnabled(false);  // Desactivar el botón de guardar
            btnactualizar.setEnabled(false);  // Desactivar el botón de actualizar
        } else {
            lblmensaje.setVisible(false);  // Ocultar el mensaje si tiene días pendientes
            btnguardar.setEnabled(true);   // Activar el botón de guardar
            btnactualizar.setEnabled(true);  // Activar el botón de actualizar
        }
        
     // Cuando se calcule el total de días tomados
        obtenerDiasTomados(nombreNuevo); // Método donde calculas el total de días de vacaciones tomados
        calcularDiasPendientes(); // Método para calcular los días pendientes, basado en el nuevo registro

     // Verificar si el empleado ya tiene registros anteriores
        if (diasTomados > 0) {
            txtultima_fecha.setText(String.valueOf(diasPendientes)); // Mostrar días pendientes anteriores
            txtultima_fecha.setVisible(true);
            lblultima.setVisible(true);
        } else {
            txtultima_fecha.setText("0"); // Si no tiene registros, establecer en 0
            txtultima_fecha.setVisible(false);
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
	        JOptionPane.showMessageDialog(null, "Error al cargar los nombres: " + e.getMessage());
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
	
	// Método para calcular los días pendientes después de un nuevo registro
	public void calcularDiasPendientes() {
	    try {
	        String totalDiasStr = txttotal_dias.getText().trim();
	        if (totalDiasStr.isEmpty()) {
	            // Si el campo está vacío, mostrar un mensaje o asignar un valor predeterminado
	            JOptionPane.showMessageDialog(null, "El campo de días totales está vacío", "Error", JOptionPane.ERROR_MESSAGE);
	            return;  // Detener la ejecución del método si el campo está vacío
	        }

	        // Convertir el texto de totalDias a entero
	        int totalDiasTomados = Integer.parseInt(totalDiasStr);

	        if (txtultima_fecha.isVisible()) {
	            String ultimaFechaStr = txtultima_fecha.getText().trim();

	            if (ultimaFechaStr.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "El campo de días pendientes de la última fecha está vacío", "Error", JOptionPane.ERROR_MESSAGE);
	                return;  // Detener la ejecución del método si el campo está vacío
	            }

	            // Convertir txtultima_fecha a entero
	            int diasPendientesAnteriores = Integer.parseInt(ultimaFechaStr);

	            // Calcular los días pendientes restantes
	            int diasPendientesRestantes = diasPendientesAnteriores - totalDiasTomados;

	            // Asegurarse de que los días pendientes no sean negativos
	            if (diasPendientesRestantes < 0) {
	                diasPendientesRestantes = 0;
	            }

	            // Asignar los días pendientes restantes al campo correspondiente
	            txtdias_pendientes.setText(String.valueOf(diasPendientesRestantes));

	        } else {
	            // Si no hay registros previos, hacer el cálculo normal con días correspondientes
	            String diasCorrespondientesStr = txtdias_correspondientes.getText().trim();
	            if (diasCorrespondientesStr.isEmpty()) {
	                return;  // Detener la ejecución del método si el campo está vacío
	            }

	            int diasCorrespondientes = Integer.parseInt(diasCorrespondientesStr);
	            int diasPendientesRestantes = diasCorrespondientes - totalDiasTomados;
	            txtdias_pendientes.setText(String.valueOf(diasPendientesRestantes));
	        }

	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Error al convertir el número. Asegúrate de que los campos no estén vacíos y tengan valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
	        e.printStackTrace();
	    }
	}




	// Verificar si el empleado ya tiene registros anteriores y mostrar los días pendientes
	public void verificarRegistrosAnteriores(int idEmpleado) {
	    int diasPendientes = cargarDiasPendientes(idEmpleado);

	    if (diasPendientes > 0) {
	        txtultima_fecha.setText(String.valueOf(diasPendientes)); // Mostrar días pendientes anteriores
	        txtultima_fecha.setVisible(true);
	        lblultima.setVisible(true);
	    } else {
	        txtultima_fecha.setText("0"); // Si no tiene registros, establecer en 0
	        txtultima_fecha.setVisible(false);
	        lblultima.setVisible(false);
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
	        JOptionPane.showMessageDialog(null, "Error al obtener el último año de vacaciones: " + e.getMessage());
	    } finally {
	        if (con != null) {
	            new conexion().desconectar();
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





	// Método 2: Llenar campos de empleado
	public void llenarCamposEmpleado(String nombreEmpleado) {
	    Connection con = null;
	    consultas_vacaciones consultaVacaciones = new consultas_vacaciones(); 

	    try {
	        con = new conexion().conectar();
	        String sql = "SELECT * FROM empleados WHERE nombres_empleado = ?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, nombreEmpleado);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
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

	            Date fechaInicioEmpleado = rs.getDate("inicio_empleado");
	            cargarAntiguedadEmpleado(fechaInicioEmpleado); // Cargar la antigüedad del empleado en meses

	            int antiguedadMeses = calcularAntiguedad(fechaInicioEmpleado); // Método que calcula la antigüedad en meses
	            int diasCorrespondientes = calcularDiasCorrespondientes(antiguedadMeses); // Método que calcula los días correspondientes
	            txtdias_correspondientes.setText(String.valueOf(diasCorrespondientes));

	            int diasTomados = consultaVacaciones.obtenerDiasTomados(rs.getInt("id_empleado")); // Obtener días tomados
	            int diasPendientes = diasCorrespondientes - diasTomados;
	            calcularEdad(fecha_nacimiento.getDate()); 
	            verificarRegistrosAnteriores(rs.getInt("id_empleado")); 

	            // Si los días pendientes son 0, mostrar el lblmensaje y no permitir guardar
	            if (diasPendientes == 0) {
	                lblmensaje.setVisible(true);
	                btnguardar.setEnabled(false); // Desactivar el botón de guardar
	            } else {
	                lblmensaje.setVisible(false);
	                btnguardar.setEnabled(true); // Activar el botón de guardar si hay días pendientes
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al cargar los datos del empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    } finally {
	        try {
	            if (con != null) con.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}

	
	private void calcularDiasTotalesYActualizarPendientes() {
	    // llamado a calcular el total de días entre las fechas seleccionadas
	    calcularDiasEntreFechas();

	    // Luego, calculo los días pendientes basados en los días totales tomados
	    calcularDiasPendientes();
	}




	// Método 3: Calcular la edad
	public void calcularEdad(Date fechaNacimiento) {
	    if (fechaNacimiento != null) {
	        LocalDate nacimiento = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        LocalDate ahora = LocalDate.now();
	        Period edad = Period.between(nacimiento, ahora);
	        txtedad.setText(String.valueOf(edad.getYears()));  
	    } else {
	        txtedad.setText("");  // Si la fecha de nacimiento es nula, dejar el campo vacío
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

	    if (antiguedadMeses >= 48) {
	        diasCorrespondientes = 20;
	    } else if (antiguedadMeses >= 36) {
	        diasCorrespondientes = 15;
	    } else if (antiguedadMeses >= 24) {
	        diasCorrespondientes = 12;
	    } else if (antiguedadMeses >= 12) {
	        diasCorrespondientes = 10;
	    } else {
	        diasCorrespondientes = antiguedadMeses;
	    }

	    return diasCorrespondientes;
	}


	// Método 6: Obtener días tomados
	public int obtenerDiasTomados(String nombreEmpleado) {
	    int diasTomados = 0;
	    try {
	        Connection con = new conexion().conectar();
	        String sql = "SELECT SUM(total_dias) AS dias_tomados FROM vacaciones WHERE nombres_empleado = ?";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, nombreEmpleado);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            diasTomados = rs.getInt("dias_tomados");
	        }

	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al obtener los días tomados: " + e.getMessage());
	    }
	    return diasTomados;
	}

	
	
	// Método 7: Actualizar días pendientes
	private void actualizarDiasPendientes(String nombreEmpleado) {
	    Connection con = null;
	    try {
	        con = new conexion().conectar();
	        
	        String sqlDiasCorrespondientes = "SELECT dias_correspondientes FROM vacaciones WHERE nombres_empleado = ? ORDER BY fecha_inicio_v DESC LIMIT 1";
	        PreparedStatement psDiasCorrespondientes = con.prepareStatement(sqlDiasCorrespondientes);
	        psDiasCorrespondientes.setString(1, nombreEmpleado);
	        ResultSet rsDiasCorrespondientes = psDiasCorrespondientes.executeQuery();

	        int diasCorrespondientes = 0;
	        if (rsDiasCorrespondientes.next()) {
	            diasCorrespondientes = rsDiasCorrespondientes.getInt("dias_correspondientes");
	        }
	        
	        String sqlDiasTomados = "SELECT SUM(total_dias) AS dias_tomados FROM vacaciones WHERE nombres_empleado = ?";
	        PreparedStatement psDiasTomados = con.prepareStatement(sqlDiasTomados);
	        psDiasTomados.setString(1, nombreEmpleado);
	        ResultSet rsDiasTomados = psDiasTomados.executeQuery();

	        int diasTomados = 0;
	        if (rsDiasTomados.next()) {
	            diasTomados = rsDiasTomados.getInt("dias_tomados");
	        }

	        int diasPendientes = diasCorrespondientes - diasTomados;

	        if (diasPendientes < 0) {
	            diasPendientes = 0;
	        }

	        String sqlUpdatePendientes = "UPDATE vacaciones SET dias_pendientes = ? WHERE nombres_empleado = ?";
	        PreparedStatement psUpdatePendientes = con.prepareStatement(sqlUpdatePendientes);
	        psUpdatePendientes.setInt(1, diasPendientes);
	        psUpdatePendientes.setString(2, nombreEmpleado);

	        psUpdatePendientes.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al actualizar los días pendientes: " + e.getMessage());
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


	// Método 8: Cargar días pendientes
	public int cargarDiasPendientes(int idEmpleado) {
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    int diasPendientes = 0;

	    try {
	        con = new conexion().conectar();

	        String sql = "SELECT SUM(total_dias) AS dias_tomados FROM vacaciones WHERE id_empleado = ?";
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, idEmpleado);
	        rs = ps.executeQuery();

	        int diasTomados = 0;
	        if (rs.next()) {
	            diasTomados = rs.getInt("dias_tomados");
	        }
	        
	        int diasCorrespondientes = Integer.parseInt(txtdias_correspondientes.getText().trim());

	        diasPendientes = diasCorrespondientes - diasTomados;

	        if (diasPendientes < 0) {
	            diasPendientes = 0;
	        }

	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al calcular los días pendientes: " + e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return diasPendientes;  
	}


	// Método 9: Calcular días entre fechas
	private void calcularDiasEntreFechas() {
	    Date fechaInicio = fecha_inicio_v.getDate();
	    Date fechaFin = fecha_finalizacion_v.getDate();

	    if (fechaInicio != null && fechaFin != null) {
	        LocalDate localFechaInicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        LocalDate localFechaFin = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        long diasEntreFechas = ChronoUnit.DAYS.between(localFechaInicio, localFechaFin);

	        if (diasEntreFechas <= 0) {
	            JOptionPane.showMessageDialog(null, "La fecha de finalización debe ser posterior a la fecha de inicio.", "Error", JOptionPane.ERROR_MESSAGE);
	            txttotal_dias.setText("0");
	            return;
	        }

	        txttotal_dias.setText(String.valueOf(diasEntreFechas));

	        if (!txtdias_correspondientes.getText().trim().isEmpty()) {
	            int diasCorrespondientes = Integer.parseInt(txtdias_correspondientes.getText().trim());
	            int diasPendientes = diasCorrespondientes - (int) diasEntreFechas;

	            txtdias_pendientes.setText(String.valueOf(diasPendientes));
	        } else {
	            JOptionPane.showMessageDialog(null, "El campo de días correspondientes está vacío.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    } else {
	        txttotal_dias.setText("0");
	    }
	}

	// Método 10: Validar campos antes de guardar/actualizar
	public boolean validarCamposVacaciones() {
	    if (cbxnombres.getSelectedIndex() == -1 || cbxnombres.getSelectedItem().toString().trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Debe seleccionar un nombre.", "Validación", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    if (!radio_si.isSelected() && !radio_no.isSelected()) {
	        JOptionPane.showMessageDialog(null, "Debe seleccionar si las vacaciones son pagadas o no.", "Validación", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    if (fecha_inicio_v.getDate() == null) {
	        JOptionPane.showMessageDialog(null, "Debe seleccionar una fecha de inicio.", "Validación", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    if (fecha_finalizacion_v.getDate() == null) {
	        JOptionPane.showMessageDialog(null, "Debe seleccionar una fecha de finalización.", "Validación", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    if (fecha_finalizacion_v.getDate().before(fecha_inicio.getDate())) {
	        JOptionPane.showMessageDialog(null, "La 'Fecha de finalización' no puede ser anterior a la fecha de inicio.", "Validación", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    return true;
	}



	// Método 11: Guardar vacaciones
	public void guardarVacaciones() {
	    if (!validarCamposVacaciones()) {
	        return;
	    }

	    vacaciones claseVacaciones = new vacaciones();

	    claseVacaciones.setId_empleado(Integer.parseInt(txtid.getText()));
	    claseVacaciones.setNombres_empleado(cbxnombres.getSelectedItem().toString());
	    claseVacaciones.setApellidos_empleado(txtapellidos.getText());
	    claseVacaciones.setIdentidad_empleado(txtidentidad.getText());
	    claseVacaciones.setTel_empleado(txttel.getText());
	    claseVacaciones.setCorreo_empleado(txtcorreo.getText());
	    claseVacaciones.setCargo_empleado(txtcargo.getText());
	    claseVacaciones.setArea_empleado(txtarea.getText());
	    claseVacaciones.setNacimiento_empleado(fecha_nacimiento.getDate());
	    claseVacaciones.setSexo_empleado(txtsexo.getText());
	    claseVacaciones.setEdad_empleado(Integer.parseInt(txtedad.getText()));
	    claseVacaciones.setAntiguedad(Integer.parseInt(txtantiguedad.getText()));
	    claseVacaciones.setDias_correspondientes(Integer.parseInt(txtdias_correspondientes.getText()));
	    claseVacaciones.setTotal_dias(Integer.parseInt(txttotal_dias.getText()));
	    claseVacaciones.setPagadas(radio_si.isSelected() ? "Si" : "No");

	    // Obtener la fecha actual y la fecha de inicio de vacaciones desde los JDateChoosers
	    Date fechaActual = new Date();  // Fecha actual
	    Date fechaInicio = fecha_inicio_v.getDate();

	    claseVacaciones.setFecha_inicio_v(fechaInicio);
	    claseVacaciones.setFecha_finalizacion_v(fecha_finalizacion_v.getDate());

	    // Validar y asignar la hora actual desde el campo txthora_actual
	    String horaActualStr = txthora_actual.getText().trim();
	    if (isValidHour(horaActualStr)) {
	        // Asegúrate de que el formato sea HH:mm:ss
	        Time horaActual = Time.valueOf(horaActualStr + ":00");
	        claseVacaciones.setHora_actual(horaActual);
	    } else {
	        JOptionPane.showMessageDialog(null, "La hora ingresada no es válida. Asegúrate de que el formato sea HH:mm.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Llamar a la clase de consultas para guardar las vacaciones
	    consultas_vacaciones consulta = new consultas_vacaciones();
	    boolean exito = consulta.guardarVacaciones(claseVacaciones, fechaActual, fechaInicio);

	    if (exito) {
	        JOptionPane.showMessageDialog(null, "Vacaciones guardadas correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        vacaciones_tabla tabla = new vacaciones_tabla();
			tabla.setVisible(true);
			tabla.setLocationRelativeTo(null);
			tabla.construirTabla();
			dispose();
	  
	        
	    } else {
	        JOptionPane.showMessageDialog(null, "Error al guardar las vacaciones", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

	
	public boolean isValidHour(String horaStr) {
	    try {
	        String[] partes = horaStr.split(":");
	        if (partes.length != 2) {
	            return false;  
	        }
	        
	        int horas = Integer.parseInt(partes[0]);
	        int minutos = Integer.parseInt(partes[1]);

	        // Verificar que las horas estén en el rango 0-23 y los minutos en el rango 0-59
	        if (horas < 0 || horas > 23 || minutos < 0 || minutos > 59) {
	            return false;
	        }

	        return true;
	    } catch (NumberFormatException e) {
	        return false; 
	    }
	}




	// Método 12: Actualizar vacaciones
	public void actualizarVacaciones() {
	    if (!validarCamposVacaciones()) {
	        return;
	    }

	    vacaciones claseVacaciones = new vacaciones();

	    claseVacaciones.setId_empleado(Integer.parseInt(txtid.getText()));
	    claseVacaciones.setNombres_empleado(cbxnombres.getSelectedItem().toString());
	    claseVacaciones.setApellidos_empleado(txtapellidos.getText());
	    claseVacaciones.setIdentidad_empleado(txtidentidad.getText());
	    claseVacaciones.setTel_empleado(txttel.getText());
	    claseVacaciones.setCorreo_empleado(txtcorreo.getText());
	    claseVacaciones.setCargo_empleado(txtcargo.getText());
	    claseVacaciones.setArea_empleado(txtarea.getText());
	    claseVacaciones.setNacimiento_empleado(fecha_nacimiento.getDate());
	    claseVacaciones.setSexo_empleado(txtsexo.getText());
	    claseVacaciones.setEdad_empleado(Integer.parseInt(txtedad.getText()));
	    claseVacaciones.setAntiguedad(Integer.parseInt(txtantiguedad.getText()));
	    claseVacaciones.setDias_correspondientes(Integer.parseInt(txtdias_correspondientes.getText()));
	    claseVacaciones.setTotal_dias(Integer.parseInt(txttotal_dias.getText()));
	    claseVacaciones.setPagadas(radio_si.isSelected() ? "Si" : "No");

	    claseVacaciones.setFecha_inicio_v(fecha_inicio_v.getDate());
	    claseVacaciones.setFecha_finalizacion_v(fecha_finalizacion_v.getDate());

	    String horaActualStr = txthora_actual.getText().trim();
	    if (!horaActualStr.isEmpty()) {
	        Time horaActual = Time.valueOf(horaActualStr + ":00"); 
	        claseVacaciones.setHora_actual(horaActual);
	    } else {
	        JOptionPane.showMessageDialog(null, "La hora actual está vacía. Por favor, revise.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    consultas_vacaciones consulta = new consultas_vacaciones();
	    boolean exito = consulta.actualizarVacaciones(this);  // Pasamos la instancia de ventana actual

	    if (exito) {
	        JOptionPane.showMessageDialog(null, "Vacaciones actualizadas correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        vacaciones_tabla tabla = new vacaciones_tabla();
	    	tabla.setVisible(true);
	    	tabla.setLocationRelativeTo(null);
	    	dispose();
	    } else {
	        JOptionPane.showMessageDialog(null, "Error al actualizar las vacaciones", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}


	// Método 13: Resetear campos de vacaciones
	private void resetearCamposVacaciones() {
	    fecha_inicio_v.setDate(null);
	    fecha_finalizacion_v.setDate(null);
	    txttotal_dias.setText("0");
	    txtdias_pendientes.setText("0");
	    grupoPago.clearSelection();
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
	    txtdias_pendientes.setText("0");
	    grupoPago.clearSelection();
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

	// Método 20: Cerrar ventana
	private void cerrar_ventana() {
	    if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
	            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
	        System.exit(0);
	}
}