package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window.Type;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.SystemColor;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import clases.incapacidad_laboral;
import clases.validaciones;
import conexion.conexion;
import consultas.consultas_areas;
import consultas.consultas_cargos;
import consultas.consultas_incapacidad_laboral;

import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings({ "serial", "unused" })
public class incapacidad_laboral_nuevo extends JFrame {
    public JTextField txtidentidad;
    public JTextField txtapellidos;
    public JTextField txttel;
    public JTextField txtid_empleado;
    public JTextField txtcargo;
    public JTextField txtarea;
    public JTextField txttotal_dias;
    public JTextField txttipo;
    public JTextField txtfecha_actual;
    public JTextField txtnumero;
    public JTextField txthora_actual;
    public JTextField txtedad;
    public JTextField txtsexo;
    public JTextField txtcorreo;
    public JDateChooser fecha_finalizacion;
    public JDateChooser fecha_inicio;
    public JButton btnguardar;
    public JButton btnactualizar;
    public JButton btnlimpiar;
    public JButton btneliminar;
    public JButton btnregresar;
    public JComboBox<String> cbxnombres;
    public JTextArea txariesgo;
    public JDateChooser fecha_expedicion;
    public JSpinner hora_expedicion;
    public JDateChooser fecha_nacimiento;
    public JTextField txtreposo;
    public JCheckBox chxeditar;
    public JTextField txtid_incapacidad;

    public incapacidad_laboral_nuevo() {
        getContentPane().setBackground(new Color(255, 255, 255));
        setType(Type.UTILITY);
        setResizable(false);
        getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setBounds(100, 100, 1050, 630);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                cerrar_ventana();
            }
        });
        
        

        JLabel lblIncapacidadLaboral = new JLabel("DATOS DE LA INCAPACIDAD ");
        lblIncapacidadLaboral.setHorizontalAlignment(SwingConstants.LEFT);
        lblIncapacidadLaboral.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblIncapacidadLaboral.setBackground(new Color(255, 153, 0));
        lblIncapacidadLaboral.setBounds(31, 20, 497, 36);
        getContentPane().add(lblIncapacidadLaboral);

        JPanel panel_botones = new JPanel();
        panel_botones.setLayout(null);
        panel_botones.setBackground(SystemColor.menu);
        panel_botones.setBounds(538, 10, 468, 65);
        getContentPane().add(panel_botones);

        btnguardar = new JButton("Guardar");
        btnguardar.setToolTipText("Guardar registro");
        btnguardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		guardar_incapacidad();
        	}
        });
        btnguardar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnguardar.setBackground(UIManager.getColor("Button.highlight"));
        btnguardar.setBounds(367, 17, 90, 23);
        panel_botones.add(btnguardar);

        btnactualizar = new JButton("Actualizar");
        btnactualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	actualizarIncapacidad();
            }
        });


        btnactualizar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnactualizar.setBackground(UIManager.getColor("Button.highlight"));
        btnactualizar.setBounds(367, 17, 90, 23);
        panel_botones.add(btnactualizar);

        btnlimpiar = new JButton("Limpiar");
        btnlimpiar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		limpiarTodosCampos();
        	}
        });
        btnlimpiar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnlimpiar.setBackground(UIManager.getColor("Button.highlight"));
        btnlimpiar.setBounds(272, 17, 90, 23);
        panel_botones.add(btnlimpiar);

        btnregresar = new JButton("Regresar");
        btnregresar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		incapacidad_laboral_tabla tabla = new incapacidad_laboral_tabla();
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
        
        chxeditar = new JCheckBox("Editar registro");
        chxeditar.setFont(new Font("Tahoma", Font.PLAIN, 13));
        chxeditar.setBounds(160, 17, 105, 21);
        panel_botones.add(chxeditar);

        JPanel panel_datos = new JPanel();
        panel_datos.setLayout(null);
        panel_datos.setBackground(SystemColor.menu);
        panel_datos.setBounds(31, 74, 975, 498);
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
        txtapellidos.setBounds(31, 128, 208, 33);
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
        lblapellidos.setBounds(31, 104, 166, 25);
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
        lblarea.setBounds(750, 104, 200, 25);
        panel_datos.add(lblarea);

        JLabel lblcorreo_electronico = new JLabel("Fecha de nacimiento");
        lblcorreo_electronico.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblcorreo_electronico.setBounds(513, 105, 166, 25);
        panel_datos.add(lblcorreo_electronico);

        JLabel lblid = new JLabel("Id empleado");
        lblid.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblid.setBounds(267, 104, 140, 25);
        panel_datos.add(lblid);

        txtid_empleado = new JTextField();
        txtid_empleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtid_empleado.setEditable(false);
        txtid_empleado.setColumns(10);
        txtid_empleado.setBounds(267, 128, 90, 33);
        panel_datos.add(txtid_empleado);

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
        txtarea.setBounds(750, 128, 200, 33);
        panel_datos.add(txtarea);

        cbxnombres = new JComboBox<>();
        cbxnombres.setSelectedIndex(-1);
        cbxnombres.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cbxnombres.getSelectedIndex() > 0) {
                    String nombreSeleccionado = (String) cbxnombres.getSelectedItem();
                    cargarDatosEmpleado(nombreSeleccionado);
                } else {
                    limpiarCampos();
                }
            }
        });
        cbxnombres.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cbxnombres.setBounds(30, 61, 208, 33);
        panel_datos.add(cbxnombres);
        

        fecha_inicio = new JDateChooser();
        validaciones.deshabilitarEscrituraJDateChooser(fecha_inicio);
        fecha_inicio.setDateFormatString("dd-MM-yy");
        fecha_inicio.setBounds(361, 421, 208, 33);
        panel_datos.add(fecha_inicio);

        JLabel lbldesde = new JLabel("Fecha de inicio");
        lbldesde.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbldesde.setBounds(361, 398, 133, 25);
        panel_datos.add(lbldesde);

        JLabel lblhasta = new JLabel("Fecha de finalización");
        lblhasta.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblhasta.setBounds(679, 398, 168, 25);
        panel_datos.add(lblhasta);

        txttotal_dias = new JTextField();
        txttotal_dias.setHorizontalAlignment(SwingConstants.CENTER);
        txttotal_dias.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txttotal_dias.setEditable(false);
        txttotal_dias.setColumns(10);
        txttotal_dias.setBounds(615, 457, 44, 33);
        panel_datos.add(txttotal_dias);

        JLabel lbltotal1 = new JLabel("Total de días");
        lbltotal1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbltotal1.setBounds(511, 459, 116, 29);
        panel_datos.add(lbltotal1);

        JLabel lblDatosDel_1 = new JLabel("_______ Datos del empleado__________________________________________________________________________________");
        lblDatosDel_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblDatosDel_1.setForeground(Color.GRAY);
        lblDatosDel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDatosDel_1.setBounds(30, 10, 919, 28);
        panel_datos.add(lblDatosDel_1);

        JLabel lblNombreDeQuien = new JLabel("Tipo de incapacidad");
        lblNombreDeQuien.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNombreDeQuien.setBounds(33, 359, 221, 25);
        panel_datos.add(lblNombreDeQuien);

        txttipo = new JTextField();
        txttipo.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		validaciones.validarSoloLetras(e, txttipo);
        	}
        });
        txttipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txttipo.setColumns(10);
        txttipo.setBounds(31, 384, 268, 33);
        panel_datos.add(txttipo);

        txariesgo = new JTextArea();
        txariesgo.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		validaciones.validarLongitud(e, txariesgo, 150);        	
        		validaciones.capitalizarPrimeraLetra(txariesgo);
        	}
        });
        txariesgo.setWrapStyleWord(true);
        txariesgo.setLineWrap(true);
        txariesgo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txariesgo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txariesgo.setBounds(31, 277, 268, 74);
        panel_datos.add(txariesgo);

        JLabel lblPresuncinDeRiesgo = new JLabel("Presunción de riesgo");
        lblPresuncinDeRiesgo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblPresuncinDeRiesgo.setBounds(31, 252, 232, 25);
        panel_datos.add(lblPresuncinDeRiesgo);

        JLabel lblid_permiso = new JLabel("N° Certificado");
        lblid_permiso.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblid_permiso.setBounds(361, 274, 125, 25);
        panel_datos.add(lblid_permiso);

        JLabel lblhoy_es = new JLabel("Fecha actual:");
        lblhoy_es.setForeground(SystemColor.inactiveCaptionText);
        lblhoy_es.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblhoy_es.setBounds(518, 256, 111, 25);
        panel_datos.add(lblhoy_es);

        txtfecha_actual = new JTextField();
        txtfecha_actual.setText("11-09-24");
        txtfecha_actual.setHorizontalAlignment(SwingConstants.CENTER);
        txtfecha_actual.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtfecha_actual.setEditable(false);
        txtfecha_actual.setColumns(10);
        txtfecha_actual.setBackground(SystemColor.menu);
        txtfecha_actual.setBounds(626, 252, 103, 33);
        panel_datos.add(txtfecha_actual);

        JLabel lblDatosDel_2 = new JLabel("_______ Datos de la incapacidad__________________________________________________________________________________");
        lblDatosDel_2.setHorizontalAlignment(SwingConstants.LEFT);
        lblDatosDel_2.setForeground(Color.GRAY);
        lblDatosDel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDatosDel_2.setBounds(31, 218, 919, 28);
        panel_datos.add(lblDatosDel_2);

        txtnumero = new JTextField();
        txtnumero.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		validaciones.validarLetrasYNumeros(e, txtnumero);
        	}
        });
        txtnumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtnumero.setColumns(10);
        txtnumero.setBounds(361, 300, 211, 33);
        panel_datos.add(txtnumero);

        fecha_expedicion = new JDateChooser();
        validaciones.deshabilitarEscrituraJDateChooser(fecha_expedicion);
        fecha_expedicion.setDateFormatString("dd-MM-yy");
        fecha_expedicion.setBounds(361, 359, 211, 33);
        panel_datos.add(fecha_expedicion);

        JLabel lblHoraDeExpedicin = new JLabel("Fecha de expedición");
        lblHoraDeExpedicin.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblHoraDeExpedicin.setBounds(361, 335, 168, 25);
        panel_datos.add(lblHoraDeExpedicin);

        hora_expedicion = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(hora_expedicion, "HH:mm");
        hora_expedicion.setEditor(timeEditor); 
        hora_expedicion.setValue(new Date()); 
        hora_expedicion.setBounds(679, 358, 200, 33);
        panel_datos.add(hora_expedicion);

        JLabel lbldesde_1 = new JLabel("Hora de expedición");
        lbldesde_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbldesde_1.setBounds(679, 330, 157, 25);
        panel_datos.add(lbldesde_1);

        fecha_finalizacion = new JDateChooser();
        validaciones.deshabilitarEscrituraJDateChooser(fecha_finalizacion);
        fecha_finalizacion.setDateFormatString("dd-MM-yy");
        fecha_finalizacion.setBounds(679, 421, 208, 33);
        panel_datos.add(fecha_finalizacion);

        JLabel lblhoy_es_1 = new JLabel("Hora actual:");
        lblhoy_es_1.setForeground(SystemColor.inactiveCaptionText);
        lblhoy_es_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblhoy_es_1.setBounds(739, 256, 111, 25);
        panel_datos.add(lblhoy_es_1);

        txthora_actual = new JTextField();
        txthora_actual.setText("11-09-24");
        txthora_actual.setHorizontalAlignment(SwingConstants.CENTER);
        txthora_actual.setFont(new Font("Tahoma", Font.BOLD, 14));
        txthora_actual.setEditable(false);
        txthora_actual.setColumns(10);
        txthora_actual.setBackground(SystemColor.menu);
        txthora_actual.setBounds(847, 252, 103, 33);
        panel_datos.add(txthora_actual);

        JLabel lblEdad = new JLabel("Edad");
        lblEdad.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblEdad.setBounds(407, 104, 61, 25);
        panel_datos.add(lblEdad);

        txtedad = new JTextField();
        txtedad.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtedad.setEditable(false);
        txtedad.setColumns(10);
        txtedad.setBounds(384, 128, 90, 33);
        panel_datos.add(txtedad);

        JLabel lblcorreo_electronico_1 = new JLabel("Correo electrónico");
        lblcorreo_electronico_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblcorreo_electronico_1.setBounds(602, 179, 166, 25);
        panel_datos.add(lblcorreo_electronico_1);

        txtsexo = new JTextField();
        txtsexo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtsexo.setEditable(false);
        txtsexo.setColumns(10);
        txtsexo.setBounds(266, 175, 208, 33);
        panel_datos.add(txtsexo);

        txtcorreo = new JTextField();
        txtcorreo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtcorreo.setEditable(false);
        txtcorreo.setColumns(10);
        txtcorreo.setBounds(750, 175, 200, 33);
        panel_datos.add(txtcorreo);

        JLabel lblcorreo_electronico_1_1 = new JLabel("Sexo");
        lblcorreo_electronico_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblcorreo_electronico_1_1.setBounds(196, 179, 90, 25);
        panel_datos.add(lblcorreo_electronico_1_1);

        fecha_nacimiento = new JDateChooser();
        fecha_nacimiento.setDateFormatString("dd-MM-yy");
        //fecha_nacimiento.setEditable(false);
        fecha_nacimiento.setEnabled(false);
        fecha_nacimiento.setBounds(511, 128, 208, 33);

        fecha_nacimiento.setForeground(Color.BLACK);
        panel_datos.add(fecha_nacimiento);
        
        JLabel lblNombreDeQuien_1 = new JLabel("Tipo de reposo");
        lblNombreDeQuien_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNombreDeQuien_1.setBounds(31, 425, 133, 25);
        panel_datos.add(lblNombreDeQuien_1);
        
        txtreposo = new JTextField();
        txtreposo.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		validaciones.validarSoloLetras(e, txtreposo);
        	}
        });
        txtreposo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtreposo.setColumns(10);
        txtreposo.setBounds(31, 450, 268, 33);
        panel_datos.add(txtreposo);
        
        txtid_incapacidad = new JTextField();
        txtid_incapacidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtid_incapacidad.setEditable(false);
        txtid_incapacidad.setColumns(10);
        txtid_incapacidad.setBounds(964, 10, 1, 6);
        panel_datos.add(txtid_incapacidad);

        fecha_inicio.getDateEditor().addPropertyChangeListener(evt -> calcularDias());
        fecha_finalizacion.getDateEditor().addPropertyChangeListener(evt -> calcularDias());

        llenarComboBoxNombres();
        establecerFechaHoraActual();
        establecerRangoFechas();
        
        
        // Escuchador para el JCheckBox chxeditar
        chxeditar.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                boolean habilitar = chxeditar.isSelected(); 
                
                cbxnombres.setEnabled(habilitar);
                cbxnombres.setForeground(Color.BLACK);
                
                txttipo.setEditable(habilitar); 
                txttipo.setForeground(Color.BLACK);

                txariesgo.setEditable(habilitar);
                txariesgo.setForeground(Color.BLACK);

                txtnumero.setEditable(habilitar); 
                txtnumero.setForeground(Color.BLACK);

                txtreposo.setEditable(habilitar); // Permitir escritura
                txtreposo.setForeground(Color.BLACK);

                fecha_expedicion.setEnabled(habilitar);
                fecha_expedicion.setForeground(Color.BLACK);

                fecha_inicio.setEnabled(habilitar);
                fecha_inicio.setForeground(Color.BLACK);

                fecha_finalizacion.setEnabled(habilitar);
                fecha_finalizacion.setForeground(Color.BLACK);

                hora_expedicion.setEnabled(habilitar);
                hora_expedicion.setForeground(Color.BLACK);

                btnlimpiar.setVisible(habilitar);
                btnactualizar.setVisible(habilitar);
            }
        });
        
    }
    
    
    // Método para establecer el rango de fechas de los JDateChooser
    private void establecerRangoFechas() {
        Calendar fechaActual = Calendar.getInstance();

        Calendar fechaMinima = (Calendar) fechaActual.clone();
        Calendar fechaMaxima = (Calendar) fechaActual.clone();

        fechaMinima.add(Calendar.MONTH, -3);
        fechaMaxima.add(Calendar.MONTH, 3);

        Date fechaMin = fechaMinima.getTime();
        Date fechaMax = fechaMaxima.getTime();
        fecha_expedicion.setMinSelectableDate(fechaMin);
        fecha_expedicion.setMaxSelectableDate(fechaMax);

        fecha_inicio.setMinSelectableDate(fechaMin);
        fecha_inicio.setMaxSelectableDate(fechaMax);

        fecha_finalizacion.setMinSelectableDate(fechaMin);
        fecha_finalizacion.setMaxSelectableDate(fechaMax);
    }


    private void cerrar_ventana() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	

    public void limpiarCampos() {
        txtidentidad.setText("");
        txtapellidos.setText("");
        txtid_empleado.setText("");
        txttel.setText("");
        txtcorreo.setText("");
        txtcargo.setText("");
        txtarea.setText("");
        txtedad.setText("");
        txtsexo.setText("");
        fecha_nacimiento.setDate(null);
    }
    
    
    @SuppressWarnings("deprecation")
	public void limpiarTodosCampos() {
    	cbxnombres.setSelectedIndex(-1);
    	txtidentidad.setText("");
        txtapellidos.setText("");
        txtid_empleado.setText("");
        txttel.setText("");
        txtcorreo.setText("");
        txtcargo.setText("");
        txtarea.setText("");
        txtedad.setText("");
        txtsexo.setText("");
        fecha_nacimiento.setDate(null);
        txariesgo.setText("");
        txttipo.setText("");
        txtreposo.setText("");
        fecha_expedicion.setDate(null);
        fecha_inicio.setDate(null);
        fecha_finalizacion.setDate(null);
        txtnumero.setText("");
        hora_expedicion.setValue(new java.util.Date(0, 0, 0, 0, 0));
        txttotal_dias.setText("");
        
        
    }

    public void llenarComboBoxNombres() {
        conexion con = new conexion();
        Connection cn = con.conectar();

        String sql = "SELECT nombres_empleado FROM empleados";

        try (PreparedStatement pst = cn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            cbxnombres.removeAllItems();
            cbxnombres.addItem(""); 
            while (rs.next()) {
                cbxnombres.addItem(rs.getString("nombres_empleado"));
            }

            if (cbxnombres.getItemCount() == 1) {
                JOptionPane.showMessageDialog(null, "No se encontraron empleados en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al llenar la lista de Nombres" , "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            con.desconectar(cn);
        }
    }

    public void cargarDatosEmpleado(String nombreEmpleado) {
        conexion con = new conexion();
        Connection cn = con.conectar();

        String sql = "SELECT identidad_empleado, apellidos_empleado, id_empleado, tel_empleado, correo_empleado, cargo_empleado, area_empleado, nacimiento_empleado, sexo_empleado FROM empleados WHERE nombres_empleado = ?";

        try (PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setString(1, nombreEmpleado);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                txtidentidad.setText(rs.getString("identidad_empleado"));
                txtapellidos.setText(rs.getString("apellidos_empleado"));
                txtid_empleado.setText(rs.getString("id_empleado"));
                txttel.setText(rs.getString("tel_empleado"));
                txtcorreo.setText(rs.getString("correo_empleado"));
                txtcargo.setText(rs.getString("cargo_empleado"));
                txtarea.setText(rs.getString("area_empleado"));
                txtsexo.setText(rs.getString("sexo_empleado"));

                Date fechaNacimiento = rs.getDate("nacimiento_empleado");
                if (fechaNacimiento != null) {
                    fecha_nacimiento.setDate(fechaNacimiento);

                    int edad = calcularEdad(fechaNacimiento);
                    txtedad.setText(String.valueOf(edad));
                } else {
                    fecha_nacimiento.setDate(null); 
                    txtedad.setText(""); 
                    System.out.println("Fecha de nacimiento cargada: " + fechaNacimiento);

                }

            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron datos para el empleado seleccionado", 
                		"Información", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar los datos del empleado", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (cn != null && !cn.isClosed()) {
                    con.desconectar(cn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public int calcularEdad(Date fechaNacimiento) {
        Calendar nacimiento = Calendar.getInstance();
        nacimiento.setTime(fechaNacimiento);

        Calendar actual = Calendar.getInstance();
        int edad = actual.get(Calendar.YEAR) - nacimiento.get(Calendar.YEAR);

        // Ajustar la edad si el cumpleaños aún no ha ocurrido este año
        if (actual.get(Calendar.MONTH) < nacimiento.get(Calendar.MONTH) ||
            (actual.get(Calendar.MONTH) == nacimiento.get(Calendar.MONTH) &&
             actual.get(Calendar.DAY_OF_MONTH) < nacimiento.get(Calendar.DAY_OF_MONTH))) {
            edad--;
        }

        return edad;
    }

    
    
    public void establecerFechaHoraActual() {
        LocalDate fechaActual = LocalDate.now();
        LocalTime horaActual = LocalTime.now();

        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

        txtfecha_actual.setText(fechaActual.format(formatoFecha));
        txthora_actual.setText(horaActual.format(formatoHora));
        
    }
    
    
    
    public void guardar_incapacidad() {
        Date fechaInicio = fecha_inicio.getDate();
        Date fechaFinalizacion = fecha_finalizacion.getDate();
        Date fechaExpedicion = fecha_expedicion.getDate();
        Date fechaNacimiento = fecha_nacimiento.getDate(); 

        if (fechaNacimiento == null) {
            JOptionPane.showMessageDialog(null, "El campo 'Fecha de nacimiento' no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (fechaInicio == null || fechaFinalizacion == null || fechaExpedicion == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar las fechas de inicio, finalización y expedición", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDate localFechaInicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localFechaFinalizacion = fechaFinalizacion.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long diasTranscurridos = java.time.temporal.ChronoUnit.DAYS.between(localFechaInicio, localFechaFinalizacion);

        if (diasTranscurridos < 0) {
            JOptionPane.showMessageDialog(null, "La fecha de finalización debe ser posterior a la fecha de inicio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        txttotal_dias.setText(String.valueOf(diasTranscurridos));

        if (txtidentidad.getText().isEmpty() || txtapellidos.getText().isEmpty() || txtid_empleado.getText().isEmpty() ||
            txttel.getText().isEmpty() || txtcorreo.getText().isEmpty() || txtcargo.getText().isEmpty() ||
            txtarea.getText().isEmpty() || txtedad.getText().isEmpty() || txtsexo.getText().isEmpty() ||
            txariesgo.getText().isEmpty() || txttipo.getText().isEmpty() || txtnumero.getText().isEmpty() || txtreposo.getText().isEmpty()) {
            
            JOptionPane.showMessageDialog(null, "Datos vacíos. Por favor, complete todos los campos para registrar la incapacidad.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            incapacidad_laboral incapacidad = new incapacidad_laboral();
            incapacidad.setId_empleado(Integer.parseInt(txtid_empleado.getText()));
            incapacidad.setNombres_empleado(cbxnombres.getSelectedItem().toString());
            incapacidad.setApellidos_empleado(txtapellidos.getText());
            incapacidad.setIdentidad_empleado(txtidentidad.getText());
            incapacidad.setTel_empleado(txttel.getText());
            incapacidad.setCorreo_empleado(txtcorreo.getText());
            incapacidad.setCargo_empleado(txtcargo.getText());
            incapacidad.setArea_empleado(txtarea.getText());
            incapacidad.setEdad_empleado(Integer.parseInt(txtedad.getText()));
            incapacidad.setRiesgo_incapacidad(txariesgo.getText());
            incapacidad.setInicio_incapacidad(fechaInicio);
            incapacidad.setFin_incapacidad(fechaFinalizacion);
            incapacidad.setTotal_dias((int) diasTranscurridos);
            incapacidad.setTipo_incapacidad(txttipo.getText());
            incapacidad.setNumero_certificado(txtnumero.getText());
            incapacidad.setFecha_expedicion(fechaExpedicion);
            incapacidad.setNacimiento_empleado(fechaNacimiento); 
            incapacidad.setSexo_empleado(txtsexo.getText()); 
            Date horaExpedicionDate = (Date) hora_expedicion.getValue();
            Time horaExpedicion = new Time(horaExpedicionDate.getTime());
            incapacidad.setHora_expedicion(horaExpedicion);
            incapacidad.setFecha_actual(new Date());
            Time horaActual = new Time(System.currentTimeMillis());
            incapacidad.setHora_actual(horaActual);
            incapacidad.setTipo_reposo(txtreposo.getText());

            consultas_incapacidad_laboral consulta = new consultas_incapacidad_laboral();
            
            if (consulta.guardar_incapacidad(incapacidad, fechaExpedicion, fechaInicio, fechaFinalizacion)) {
                JOptionPane.showMessageDialog(null, "La incapacidad laboral se ha guardado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
                incapacidad_laboral_tabla tabla = new incapacidad_laboral_tabla();
                tabla.setVisible(true);
                tabla.setLocationRelativeTo(null);
                tabla.construirTabla();
                dispose();
                
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar la incapacidad, verifique los datos", 
                		"Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error en el formato de los números. Verifique los campos numéricos", 
            		"Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado. Verifique los datos", 
            		"Error", JOptionPane.ERROR_MESSAGE);
            
            e.printStackTrace();
        }
    }

    public void calcularDias() {
        Date fechaSeleccionadaInicio = fecha_inicio.getDate();
        Date fechaSeleccionadaFin = fecha_finalizacion.getDate();

        if (fechaSeleccionadaInicio != null && fechaSeleccionadaFin != null) {
            long diffInMillies = Math.abs(fechaSeleccionadaFin.getTime() - fechaSeleccionadaInicio.getTime());
            long diffInDays = diffInMillies / (1000 * 60 * 60 * 24); 
            
            txttotal_dias.setText(String.valueOf(diffInDays));

            if (diffInDays <= 0) {
                txttotal_dias.setText(""); 
            }
        }
    }
    
    
    public boolean validarCampos() {
        return !txttipo.getText().isEmpty() &&
               !txariesgo.getText().isEmpty() &&
               !txtnumero.getText().isEmpty() &&
               !txtreposo.getText().isEmpty() &&
               fecha_expedicion.getDate() != null &&
               fecha_inicio.getDate() != null &&
               fecha_finalizacion.getDate() != null;
    }

    
    public void actualizarIncapacidad() {
        try {
            if (!validarCampos()) {
                return; 
            }

	            String idIncapacidadStr = txtid_incapacidad.getText().trim();
	            String idEmpleadoStr = txtid_empleado.getText().trim();
	            int idIncapacidad = Integer.parseInt(idIncapacidadStr);
	            int idEmpleado = Integer.parseInt(idEmpleadoStr);
	
	            String nombresEmpleado = cbxnombres.getSelectedItem().toString().trim();
	            String apellidosEmpleado = txtapellidos.getText().trim();
	            String identidadEmpleado = txtidentidad.getText().trim();
	            String telefonoEmpleado = txttel.getText().trim();
	            String correoEmpleado = txtcorreo.getText().trim();
	            String cargoEmpleado = txtcargo.getText().trim();
	            String areaEmpleado = txtarea.getText().trim();
	            String sexoEmpleado = txtsexo.getText().trim();
	            int edadEmpleado = Integer.parseInt(txtedad.getText().trim());
	            String riesgoIncapacidad = txariesgo.getText().trim();
	            String tipoIncapacidad = txttipo.getText().trim();
	            String tipoReposo = txtreposo.getText().trim();
	            String numeroCertificado = txtnumero.getText().trim();
	
	            incapacidad_laboral incapacidadActualizada = new incapacidad_laboral();
	            incapacidadActualizada.setId_incapacidad(idIncapacidad);
	            incapacidadActualizada.setId_empleado(idEmpleado);
	            incapacidadActualizada.setNombres_empleado(nombresEmpleado);
	            incapacidadActualizada.setApellidos_empleado(apellidosEmpleado);
	            incapacidadActualizada.setIdentidad_empleado(identidadEmpleado);
	            incapacidadActualizada.setTel_empleado(telefonoEmpleado);
	            incapacidadActualizada.setCorreo_empleado(correoEmpleado);
	            incapacidadActualizada.setCargo_empleado(cargoEmpleado);
	            incapacidadActualizada.setArea_empleado(areaEmpleado);
	            incapacidadActualizada.setSexo_empleado(sexoEmpleado);
	            incapacidadActualizada.setEdad_empleado(edadEmpleado);
	            incapacidadActualizada.setRiesgo_incapacidad(riesgoIncapacidad);
	            incapacidadActualizada.setTipo_incapacidad(tipoIncapacidad);
	            incapacidadActualizada.setTipo_reposo(tipoReposo);
	            incapacidadActualizada.setNumero_certificado(numeroCertificado);
	
	            LocalDate localFechaInicio = fecha_inicio.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	            LocalDate localFechaFin = fecha_finalizacion.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	
	            long diasTranscurridos = java.time.temporal.ChronoUnit.DAYS.between(localFechaInicio, localFechaFin);

            if (diasTranscurridos < 0) {
                JOptionPane.showMessageDialog(null, "La fecha de finalización debe ser posterior a la fecha de inicio",
                		"Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            incapacidadActualizada.setTotal_dias((int) diasTranscurridos);
	            txttotal_dias.setText(String.valueOf(diasTranscurridos));
	
	            java.sql.Date sqlFechaNacimiento = new java.sql.Date(fecha_nacimiento.getDate().getTime());
	            java.sql.Date sqlFechaInicio = java.sql.Date.valueOf(localFechaInicio);
	            java.sql.Date sqlFechaFin = java.sql.Date.valueOf(localFechaFin);
	            java.sql.Date sqlFechaExpedicion = new java.sql.Date(fecha_expedicion.getDate().getTime());
	
	            incapacidadActualizada.setNacimiento_empleado(sqlFechaNacimiento);
	            incapacidadActualizada.setInicio_incapacidad(sqlFechaInicio);
	            incapacidadActualizada.setFin_incapacidad(sqlFechaFin);
	            incapacidadActualizada.setFecha_expedicion(sqlFechaExpedicion);
	        
	            java.sql.Time sqlHoraExpedicion = new java.sql.Time(((Date) hora_expedicion.getValue()).getTime());
	            incapacidadActualizada.setHora_expedicion(sqlHoraExpedicion);
	
	            Date fechaActual = new Date();
	            java.sql.Date sqlFechaActual = new java.sql.Date(fechaActual.getTime());
	            java.sql.Time sqlHoraActual = new java.sql.Time(fechaActual.getTime());
	
	            incapacidadActualizada.setFecha_actual(sqlFechaActual);
	            incapacidadActualizada.setHora_actual(sqlHoraActual);
	
	            consultas_incapacidad_laboral consulta = new consultas_incapacidad_laboral();
	        
            if (consulta.actualizarIncapacidad(incapacidadActualizada)) {
                JOptionPane.showMessageDialog(null, "La incapacidad laboral se ha actualizado correctamente", 
                		"Éxito", JOptionPane.INFORMATION_MESSAGE);
                incapacidad_laboral_tabla tabla = new incapacidad_laboral_tabla();
                tabla.setVisible(true);
                tabla.setLocationRelativeTo(null);
                tabla.construirTabla(); 
                dispose(); 
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar la incapacidad laboral", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            	JOptionPane.showMessageDialog(null, "Id de empleado no válido o vacío", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
        		JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado", "Error", JOptionPane.ERROR_MESSAGE);
        		ex.printStackTrace();
        }
    }
    
   

}
