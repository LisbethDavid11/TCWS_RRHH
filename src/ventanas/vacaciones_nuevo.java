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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

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

import conexion.conexion;




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
	
	public JButton btnguardar;
	public JButton btnactualizar;
	public JButton btnregresar;
	public JButton btnlimpiar;
	public JCheckBox chxeditar;
	public JTextField txtdias_pendientes;
	
	LocalDate fechaActual = LocalDate.now();
	
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
        fecha_inicio_v.setBounds(31, 374, 166, 33);
        panel_datos.add(fecha_inicio_v);
        
        JLabel lbldesde = new JLabel("Fecha de inicio");
        lbldesde.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbldesde.setBounds(31, 351, 133, 25);
        panel_datos.add(lbldesde);
        
        JLabel lblhasta = new JLabel("Fecha de finalización");
        lblhasta.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblhasta.setBounds(266, 351, 168, 25);
        panel_datos.add(lblhasta);
        
        txttotal_dias = new JTextField();
        txttotal_dias.setHorizontalAlignment(SwingConstants.CENTER);
        txttotal_dias.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txttotal_dias.setEditable(false);
        txttotal_dias.setColumns(10);
        txttotal_dias.setBounds(215, 428, 44, 33);
        panel_datos.add(txttotal_dias);
        
        JLabel lbltotal1 = new JLabel("Total de días");
        lbltotal1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbltotal1.setBounds(111, 430, 105, 29);
        panel_datos.add(lbltotal1);
        
        JLabel lblDatosDel_1 = new JLabel("_______ Datos del empleado__________________________________________________________________________________");
        lblDatosDel_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblDatosDel_1.setForeground(Color.GRAY);
        lblDatosDel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDatosDel_1.setBounds(30, 10, 919, 28);
        panel_datos.add(lblDatosDel_1);
        
        JLabel lblhoy_es = new JLabel("Fecha actual:");
        lblhoy_es.setForeground(SystemColor.inactiveCaptionText);
        lblhoy_es.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblhoy_es.setBounds(508, 278, 111, 25);
        panel_datos.add(lblhoy_es);
        
        txtfecha_actual = new JTextField();
        txtfecha_actual.setText("11-09-24");
        txtfecha_actual.setHorizontalAlignment(SwingConstants.CENTER);
        txtfecha_actual.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtfecha_actual.setEditable(false);
        txtfecha_actual.setColumns(10);
        txtfecha_actual.setBackground(SystemColor.menu);
        txtfecha_actual.setBounds(616, 274, 95, 33);
        panel_datos.add(txtfecha_actual);
        
        JLabel lblDatosDel_2 = new JLabel("_______ Datos de las vacaciones __________________________________________________________________________________");
        lblDatosDel_2.setHorizontalAlignment(SwingConstants.LEFT);
        lblDatosDel_2.setForeground(Color.GRAY);
        lblDatosDel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDatosDel_2.setBounds(31, 226, 919, 28);
        panel_datos.add(lblDatosDel_2);
        
        fecha_finalizacion_v = new JDateChooser();
        fecha_finalizacion_v.setDateFormatString("dd-MM-yy");
        fecha_finalizacion_v.setBounds(266, 374, 166, 33);
        panel_datos.add(fecha_finalizacion_v);
        
        JLabel lblhoy_es_1 = new JLabel("Hora actual:");
        lblhoy_es_1.setForeground(SystemColor.inactiveCaptionText);
        lblhoy_es_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblhoy_es_1.setBounds(731, 282, 111, 25);
        panel_datos.add(lblhoy_es_1);
        
        txthora_actual = new JTextField();
        txthora_actual.setHorizontalAlignment(SwingConstants.CENTER);
        txthora_actual.setFont(new Font("Tahoma", Font.BOLD, 14));
        txthora_actual.setEditable(false);
        txthora_actual.setColumns(10);
        txthora_actual.setBackground(SystemColor.menu);
        txthora_actual.setBounds(831, 278, 84, 33);
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
        fecha_nacimiento.setForeground(Color.BLACK);
        fecha_nacimiento.setEnabled(false);
        fecha_nacimiento.setDateFormatString("dd-MM-yy");
        fecha_nacimiento.setBounds(511, 121, 208, 33);
        panel_datos.add(fecha_nacimiento);
        
        txtid_tabla = new JTextField();
        txtid_tabla.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtid_tabla.setEditable(false);
        txtid_tabla.setColumns(10);
        txtid_tabla.setBounds(956, 10, 9, 9);
        panel_datos.add(txtid_tabla);
        
        JLabel lblcorreo_electronico_1_2 = new JLabel("Antigüedad");
        lblcorreo_electronico_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblcorreo_electronico_1_2.setBounds(31, 271, 166, 25);
        panel_datos.add(lblcorreo_electronico_1_2);
        
        txtantiguedad = new JTextField();
        txtantiguedad.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtantiguedad.setEditable(false);
        txtantiguedad.setColumns(10);
        txtantiguedad.setBounds(31, 295, 90, 33);
        panel_datos.add(txtantiguedad);
        
        JLabel lblcorreo_electronico_1_2_1 = new JLabel("meses");
        lblcorreo_electronico_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblcorreo_electronico_1_2_1.setBounds(126, 295, 71, 25);
        panel_datos.add(lblcorreo_electronico_1_2_1);
        
        JLabel lblcorreo_electronico_2 = new JLabel("Fecha de inicio");
        lblcorreo_electronico_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblcorreo_electronico_2.setBounds(513, 159, 166, 25);
        panel_datos.add(lblcorreo_electronico_2);
        
        fecha_inicio = new JDateChooser();
        fecha_inicio.setForeground(Color.BLACK);
        fecha_inicio.setEnabled(false);
        fecha_inicio.setDateFormatString("dd-MM-yy");
        fecha_inicio.setBounds(511, 182, 208, 33);
        panel_datos.add(fecha_inicio);
        
        txtdias_correspondientes = new JTextField();
        txtdias_correspondientes.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtdias_correspondientes.setEditable(false);
        //txtdias_correspondientes.setEnabled(false);
        txtdias_correspondientes.setColumns(10);
        txtdias_correspondientes.setBounds(266, 297, 90, 33);
        panel_datos.add(txtdias_correspondientes);
        
        JLabel lblcorreo_electronico_1_2_2 = new JLabel("Días correspondientes");
        lblcorreo_electronico_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblcorreo_electronico_1_2_2.setBounds(266, 273, 208, 25);
        panel_datos.add(lblcorreo_electronico_1_2_2);
        
        radio_si = new JRadioButton("Si");
        radio_si.setFont(new Font("Tahoma", Font.PLAIN, 14));
        radio_si.setBounds(726, 374, 53, 33);
        panel_datos.add(radio_si);
        
        radio_no = new JRadioButton("No");
        radio_no.setFont(new Font("Tahoma", Font.PLAIN, 14));
        radio_no.setBounds(789, 374, 53, 33);
        panel_datos.add(radio_no);
        
        JLabel lblPagadas = new JLabel("Pagadas");
        lblPagadas.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblPagadas.setBounds(726, 351, 90, 25);
        panel_datos.add(lblPagadas);
        
        JLabel lblcorreo_electronico_1_2_3 = new JLabel("Días pendientes");
        lblcorreo_electronico_1_2_3.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblcorreo_electronico_1_2_3.setBounds(518, 350, 166, 25);
        panel_datos.add(lblcorreo_electronico_1_2_3);
        
        txtdias_pendientes = new JTextField();
        txtdias_pendientes.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtdias_pendientes.setEditable(false);
        txtdias_pendientes.setColumns(10);
        txtdias_pendientes.setBounds(518, 374, 90, 33);
        panel_datos.add(txtdias_pendientes);
        
        JLabel lblVacaciones = new JLabel("VACACIONES");
        lblVacaciones.setHorizontalAlignment(SwingConstants.LEFT);
        lblVacaciones.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblVacaciones.setBackground(new Color(255, 153, 0));
        lblVacaciones.setBounds(37, 21, 442, 36);
        getContentPane().add(lblVacaciones);
        
        JPanel panel_botones = new JPanel();
        panel_botones.setLayout(null);
        panel_botones.setBackground(SystemColor.menu);
        panel_botones.setBounds(489, 10, 516, 65);
        getContentPane().add(panel_botones);
        
        btnguardar = new JButton("Guardar");
        btnguardar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnguardar.setBackground(UIManager.getColor("Button.highlight"));
        btnguardar.setBounds(397, 17, 90, 23);
        panel_botones.add(btnguardar);
        
        btnactualizar = new JButton("Actualizar");
        btnactualizar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnactualizar.setBackground(Color.WHITE);
        btnactualizar.setBounds(397, 17, 90, 23);
        panel_botones.add(btnactualizar);
        
        btnlimpiar = new JButton("Limpiar");
        btnlimpiar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnlimpiar.setBackground(UIManager.getColor("Button.highlight"));
        btnlimpiar.setBounds(302, 17, 90, 23);
        panel_botones.add(btnlimpiar);
        
        btnregresar = new JButton("Regresar");
        btnregresar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
        btnregresar.setToolTipText("Regresar a la tabla");
        btnregresar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnregresar.setBackground(UIManager.getColor("Button.highlight"));
        btnregresar.setBounds(10, 17, 90, 23);
        panel_botones.add(btnregresar);
        
        chxeditar = new JCheckBox("Editar registro");
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
		    String seleccionado = (String) cbxnombres.getSelectedItem();
		    
		    if (seleccionado == null || seleccionado.trim().isEmpty()) {
		        limpiarCampos();
		    } else {
		        llenarCamposEmpleado(seleccionado);
		    }
		});

		
		fecha_finalizacion_v.addPropertyChangeListener(evt -> calcularDiasEntreFechas());
		fecha_inicio_v.addPropertyChangeListener(evt -> calcularDiasEntreFechas());


		asignarFechaActual();
        asignarHoraActual();
		

		
		
		
	}//class
	
    
	private void cerrar_ventana() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	public void cargarNombresEmpleados() {
	    Connection con = null;
	    try {
	        con = new conexion().conectar();

	        String sql = "SELECT nombres_empleado FROM empleados";
	        PreparedStatement ps = con.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        
	        cbxnombres.removeAllItems();
	        cbxnombres.addItem("");  // Este será el primer elemento vacío

	        while (rs.next()) {
	            cbxnombres.addItem(rs.getString("nombres_empleado"));
	        }
	        
	        cbxnombres.setSelectedIndex(-1);
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error al cargar los nombres: " + e.getMessage());
	    } finally {
	        if (con != null) {
	            new conexion().desconectar();
	        }
	    }
	}

	public void llenarCamposEmpleado(String nombreEmpleado) {
	    Connection con = null;
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

	            calcularEdad(rs.getDate("nacimiento_empleado"));
	            
	            int antiguedadMeses = calcularAntiguedad(rs.getDate("inicio_empleado"));
	            
	            calcularDiasCorrespondientes(antiguedadMeses);
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al obtener los datos del empleado: " + e.getMessage());
	    } finally {
	        if (con != null) {
	            new conexion().desconectar();
	        }
	    }
	}

	public void calcularEdad(Date fechaNacimiento) {
	    if (fechaNacimiento instanceof java.sql.Date) {
	        fechaNacimiento = new Date(fechaNacimiento.getTime()); 
	    }
	    
	    LocalDate nacimiento = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    LocalDate ahora = LocalDate.now();
	    Period edad = Period.between(nacimiento, ahora);
	    txtedad.setText(String.valueOf(edad.getYears()));
	}



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


	public void calcularDiasCorrespondientes(int antiguedadMeses) {
	    int diasCorrespondientes = 0;

	    if (antiguedadMeses >= 48) {
	        diasCorrespondientes = 20;
	    } else if (antiguedadMeses >= 36) {
	        diasCorrespondientes = 15;
	    } else if (antiguedadMeses >= 24) {
	        diasCorrespondientes = 12;
	    } else if (antiguedadMeses >= 12) {
	        diasCorrespondientes = 10;
	    }

	    txtdias_correspondientes.setText(String.valueOf(diasCorrespondientes));
	}

	private void calcularDiasEntreFechas() {
	    Date fechaInicio = fecha_inicio_v.getDate();  // Obtiene un java.util.Date
	    Date fechaFin = fecha_finalizacion_v.getDate();  // También un java.util.Date

	    if (fechaInicio != null && fechaFin != null) {
	        LocalDate localFechaInicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        LocalDate localFechaFin = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	        long diasEntreFechas = ChronoUnit.DAYS.between(localFechaInicio, localFechaFin);

	        txttotal_dias.setText(String.valueOf(diasEntreFechas));

	        // Calcular los días pendientes (días correspondientes - días tomados)
	        int diasCorrespondientes = Integer.parseInt(txtdias_correspondientes.getText());
	        int diasPendientes = diasCorrespondientes - (int) diasEntreFechas;

	        txtdias_pendientes.setText(String.valueOf(diasPendientes));

	    } else {
	        txttotal_dias.setText("");
	    }
	}
	
	public void limpiarCampos() {
	    txtapellidos.setText("");
	    txtidentidad.setText("");
	    txtsexo.setText("");
	    txtid.setText("");
	    txtcorreo.setText("");
	    txttel.setText("");
	    txtcargo.setText("");
	    txtarea.setText("");
	    txtedad.setText("");
	    fecha_inicio.setDate(null);  
	    fecha_nacimiento.setDate(null);  
	    fecha_inicio_v.setDate(null);
	    fecha_finalizacion_v.setDate(null);
	    txtantiguedad.setText("");
	    txtdias_correspondientes.setText("");
	    txttotal_dias.setText("");
	    txtdias_pendientes.setText("");
	}

	
	
    public void asignarFechaActual() {
        SwingUtilities.invokeLater(() -> {
            LocalDate fechaActual = LocalDate.now();
            DateTimeFormatter formateadorFecha = DateTimeFormatter.ofPattern("dd-MM-yy");
            String fechaFormateada = fechaActual.format(formateadorFecha);
            txtfecha_actual.setText(fechaFormateada);
        });
    }

    public void asignarHoraActual() {
        SwingUtilities.invokeLater(() -> {
            LocalTime horaActual = LocalTime.now();
            DateTimeFormatter formateadorHora = DateTimeFormatter.ofPattern("HH:mm");
            String horaFormateada = horaActual.format(formateadorHora);
            txthora_actual.setText(horaFormateada);
        });
    }


	

	    
	    
	    




	
	
}//end
