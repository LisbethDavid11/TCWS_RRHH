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
import consultas.consultas_injustificadas;

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
import javax.swing.SpinnerModel;

@SuppressWarnings({ "serial", "unused" })
public class injustificadas_nuevo extends JFrame {
    public JTextField txtidentidad;
    public JTextField txtapellidos;
    public JTextField txttel;
    public JTextField txtid_empleado;
    public JTextField txtcargo;
    public JTextField txtarea;
    public JTextField txtfecha_actual;
    public JTextField txthora_actual;
    public JTextField txtedad;
    public JTextField txtsexo;
    public JTextField txtcorreo;
    public JButton btnguardar;
    public JButton btnactualizar;
    public JButton btnlimpiar;
    public JButton btneliminar;
    public JButton btnregresar;
    public JComboBox<String> cbxnombres;
    public JTextArea txamotivo;
    public JDateChooser fecha_ausencia;
    public JSpinner hora_entrada;
    public JDateChooser fecha_nacimiento;
    public JCheckBox chxeditar;
    public JTextField txtid_incapacidad;
    public JTextField txttiempo_injustificado;
    public JSpinner hora_ausencia;

    public injustificadas_nuevo() {
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
        
        

        JLabel lblIncapacidadLaboral = new JLabel("DATOS DE LA AUSENCIA INJUSTIFICADA");
        lblIncapacidadLaboral.setHorizontalAlignment(SwingConstants.LEFT);
        lblIncapacidadLaboral.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblIncapacidadLaboral.setBackground(new Color(255, 153, 0));
        lblIncapacidadLaboral.setBounds(31, 20, 519, 36);
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
        		guardar_injustificada();
        	}
        });
        btnguardar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnguardar.setBackground(UIManager.getColor("Button.highlight"));
        btnguardar.setBounds(367, 17, 90, 23);
        panel_botones.add(btnguardar);

        btnactualizar = new JButton("Actualizar");
        btnactualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	actualizar_injustificada();
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
        		injustificadas_tabla tabla = new injustificadas_tabla();
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

        JLabel lblDatosDel_1 = new JLabel("_______ Datos del empleado__________________________________________________________________________________");
        lblDatosDel_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblDatosDel_1.setForeground(Color.GRAY);
        lblDatosDel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDatosDel_1.setBounds(30, 10, 919, 28);
        panel_datos.add(lblDatosDel_1);

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
        txamotivo.setBounds(31, 336, 299, 119);
        panel_datos.add(txamotivo);

        JLabel lblPresuncinDeRiesgo = new JLabel("Motivo de la ausencia");
        lblPresuncinDeRiesgo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblPresuncinDeRiesgo.setBounds(31, 311, 232, 25);
        panel_datos.add(lblPresuncinDeRiesgo);

        JLabel lblhoy_es = new JLabel("Fecha actual:");
        lblhoy_es.setForeground(SystemColor.inactiveCaptionText);
        lblhoy_es.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblhoy_es.setBounds(31, 260, 111, 25);
        panel_datos.add(lblhoy_es);

        txtfecha_actual = new JTextField();
        txtfecha_actual.setText("11-09-24");
        txtfecha_actual.setHorizontalAlignment(SwingConstants.CENTER);
        txtfecha_actual.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtfecha_actual.setEditable(false);
        txtfecha_actual.setColumns(10);
        txtfecha_actual.setBackground(SystemColor.menu);
        txtfecha_actual.setBounds(139, 256, 103, 33);
        panel_datos.add(txtfecha_actual);

        JLabel lblDatosDel_2 = new JLabel("_______ Datos de la ausencia_________________________________________________________________________________");
        lblDatosDel_2.setHorizontalAlignment(SwingConstants.LEFT);
        lblDatosDel_2.setForeground(Color.GRAY);
        lblDatosDel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDatosDel_2.setBounds(31, 218, 919, 28);
        panel_datos.add(lblDatosDel_2);

        fecha_ausencia = new JDateChooser();
        validaciones.deshabilitarEscrituraJDateChooser(fecha_ausencia);
        fecha_ausencia.setDateFormatString("dd-MM-yy");
        fecha_ausencia.setBounds(384, 336, 211, 33);
        panel_datos.add(fecha_ausencia);

        JLabel lblHoraDeExpedicin = new JLabel("Fecha de la ausencia");
        lblHoraDeExpedicin.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblHoraDeExpedicin.setBounds(384, 311, 168, 25);
        panel_datos.add(lblHoraDeExpedicin);

        hora_entrada = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor de_hora_entrada = new JSpinner.DateEditor(hora_entrada, "HH:mm");
        hora_entrada.setEditor(de_hora_entrada); 
        hora_entrada.setValue(new Date()); 
        hora_entrada.setBounds(384, 404, 200, 33);
        panel_datos.add(hora_entrada);

        JLabel lbldesde_1 = new JLabel("Hora de entrada");
        lbldesde_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbldesde_1.setBounds(384, 379, 157, 25);
        panel_datos.add(lbldesde_1);

        JLabel lblhoy_es_1 = new JLabel("Hora actual:");
        lblhoy_es_1.setForeground(SystemColor.inactiveCaptionText);
        lblhoy_es_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblhoy_es_1.setBounds(252, 260, 111, 25);
        panel_datos.add(lblhoy_es_1);

        txthora_actual = new JTextField();
        txthora_actual.setText("11-09-24");
        txthora_actual.setHorizontalAlignment(SwingConstants.CENTER);
        txthora_actual.setFont(new Font("Tahoma", Font.BOLD, 14));
        txthora_actual.setEditable(false);
        txthora_actual.setColumns(10);
        txthora_actual.setBackground(SystemColor.menu);
        txthora_actual.setBounds(360, 256, 103, 33);
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
        
        txtid_incapacidad = new JTextField();
        txtid_incapacidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtid_incapacidad.setEditable(false);
        txtid_incapacidad.setColumns(10);
        txtid_incapacidad.setBounds(964, 10, 1, 6);
        panel_datos.add(txtid_incapacidad);
        
        JLabel lbldesde_1_1 = new JLabel("Hora de la ausencia");
        lbldesde_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbldesde_1_1.setBounds(668, 379, 157, 25);
        panel_datos.add(lbldesde_1_1);
        
        hora_ausencia = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(hora_ausencia, "HH:mm");
        hora_ausencia.setEditor(editor);
        hora_ausencia.setValue(new Date());
        hora_ausencia.setBounds(668, 404, 200, 33);
        panel_datos.add(hora_ausencia);
        
        txttiempo_injustificado = new JTextField();
        txttiempo_injustificado.setHorizontalAlignment(SwingConstants.CENTER);
        txttiempo_injustificado.setFont(new Font("Tahoma", Font.BOLD, 14));
        txttiempo_injustificado.setEditable(false);
        txttiempo_injustificado.setColumns(10);
        txttiempo_injustificado.setBackground(SystemColor.menu);
        txttiempo_injustificado.setBounds(619, 447, 103, 33);
        panel_datos.add(txttiempo_injustificado);
        
        JLabel lblhoy_es_1_1 = new JLabel("Tiempo injustificado");
        lblhoy_es_1_1.setForeground(SystemColor.inactiveCaptionText);
        lblhoy_es_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblhoy_es_1_1.setBounds(456, 451, 166, 25);
        panel_datos.add(lblhoy_es_1_1);
        
        agregarListenersHora(); 


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
               

                txamotivo.setEditable(habilitar);
                txamotivo.setForeground(Color.BLACK);

                fecha_ausencia.setEnabled(habilitar);
                fecha_ausencia.setForeground(Color.BLACK);

                hora_entrada.setEnabled(habilitar);
                hora_entrada.setForeground(Color.BLACK);

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
        fecha_ausencia.setMinSelectableDate(fechaMin);
        fecha_ausencia.setMaxSelectableDate(fechaMax);
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
        txamotivo.setText("");
        fecha_ausencia.setDate(null);
        hora_entrada.setValue(new java.util.Date(0, 0, 0, 0, 0));
        
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
    
    
    
    public void guardar_injustificada() {
        try {
            if (cbxnombres.getSelectedIndex() <= 0 || txtid_empleado.getText().isEmpty() ||
                txtapellidos.getText().isEmpty() || txtidentidad.getText().isEmpty() || txttel.getText().isEmpty() ||
                txtcorreo.getText().isEmpty() || txtcargo.getText().isEmpty() || txtarea.getText().isEmpty() ||
                txtsexo.getText().isEmpty() || txtedad.getText().isEmpty() || fecha_nacimiento.getDate() == null ||
                fecha_ausencia.getDate() == null || txamotivo.getText().isEmpty()) {
                
                JOptionPane.showMessageDialog(null, "Debe completar todos los campos para guardar.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Cálculo de tiempo injustificado
            calcularTiempoInjustificado();
            Time horaEntrada = new Time(((Date) hora_entrada.getValue()).getTime());
            Time horaAusencia = new Time(((Date) hora_ausencia.getValue()).getTime());
            Time tiempoInjustificado = Time.valueOf(txttiempo_injustificado.getText() + ":00");

            clases.injustificada inj = new clases.injustificada();
            inj.setId_empleado(Integer.parseInt(txtid_empleado.getText()));
            inj.setNombres_empleado(cbxnombres.getSelectedItem().toString());
            inj.setApellidos_empleado(txtapellidos.getText());
            inj.setIdentidad_empleado(txtidentidad.getText());
            inj.setTel_empleado(txttel.getText());
            inj.setCorreo_empleado(txtcorreo.getText());
            inj.setCargo_empleado(txtcargo.getText());
            inj.setArea_empleado(txtarea.getText());
            //inj.setNacimiento_empleado(fecha_nacimiento.getDate());
            inj.setSexo_empleado(txtsexo.getText());
            inj.setEdad_empleado(Integer.parseInt(txtedad.getText()));
            //inj.setFecha_actual(new java.sql.Date(new Date().getTime()));
            inj.setHora_actual(new Time(System.currentTimeMillis()));
            inj.setMotivo(txamotivo.getText());
            inj.setHora_entrada(horaEntrada);
            inj.setHora_ausencia(horaAusencia);
            inj.setTiempo_injustificado(tiempoInjustificado);
            inj.setNacimiento_empleado(new java.sql.Date(fecha_nacimiento.getDate().getTime()));
            inj.setFecha_actual(new java.sql.Date(new Date().getTime()));
            inj.setFecha_ausencia(new java.sql.Date(fecha_ausencia.getDate().getTime()));

            consultas_injustificadas consulta = new consultas_injustificadas();
            if (consulta.guardar_injustificada(inj)) {
                JOptionPane.showMessageDialog(null, "Ausencia injustificada guardada correctamente.");
                injustificadas_tabla tabla = new injustificadas_tabla();
                tabla.setVisible(true);
                tabla.setLocationRelativeTo(null);
                tabla.construirTabla();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar la ausencia injustificada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


  
    
    
    public boolean validarCampos() {
        return !txamotivo.getText().isEmpty() &&
               fecha_ausencia.getDate() != null;
    }

    
    public void actualizar_injustificada() {
        try {
            if (!validarCampos()) return;

            int idInjustificada = Integer.parseInt(txtid_incapacidad.getText());

            calcularTiempoInjustificado();
            Time horaEntrada = new Time(((Date) hora_entrada.getValue()).getTime());
            Time horaAusencia = new Time(((Date) hora_ausencia.getValue()).getTime());
            Time tiempoInjustificado = Time.valueOf(txttiempo_injustificado.getText() + ":00");

            clases.injustificada inj = new clases.injustificada();
            inj.setId_injustificadas(idInjustificada);
            inj.setId_empleado(Integer.parseInt(txtid_empleado.getText()));
            inj.setNombres_empleado(cbxnombres.getSelectedItem().toString());
            inj.setApellidos_empleado(txtapellidos.getText());
            inj.setIdentidad_empleado(txtidentidad.getText());
            inj.setTel_empleado(txttel.getText());
            inj.setCorreo_empleado(txtcorreo.getText());
            inj.setCargo_empleado(txtcargo.getText());
            inj.setArea_empleado(txtarea.getText());
            //inj.setNacimiento_empleado(fecha_nacimiento.getDate());
            inj.setSexo_empleado(txtsexo.getText());
            inj.setEdad_empleado(Integer.parseInt(txtedad.getText()));
            //inj.setFecha_actual(new java.sql.Date(new Date().getTime()));
            inj.setHora_actual(new Time(System.currentTimeMillis()));
            inj.setMotivo(txamotivo.getText());
            inj.setHora_entrada(horaEntrada);
            inj.setHora_ausencia(horaAusencia);
            inj.setTiempo_injustificado(tiempoInjustificado);
            //inj.setFecha_ausencia(fecha_ausencia.getDate());
            inj.setNacimiento_empleado(new java.sql.Date(fecha_nacimiento.getDate().getTime()));
            inj.setFecha_actual(new java.sql.Date(new Date().getTime()));
            inj.setFecha_ausencia(new java.sql.Date(fecha_ausencia.getDate().getTime()));


            consultas_injustificadas consulta = new consultas_injustificadas();
            if (consulta.actualizar_injustificada(inj)) {
                JOptionPane.showMessageDialog(null, "Ausencia injustificada actualizada correctamente.");
                injustificadas_tabla tabla = new injustificadas_tabla();
                tabla.setVisible(true);
                tabla.setLocationRelativeTo(null);
                tabla.construirTabla();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar el registro.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    
    private void calcularTiempoInjustificado() {
        try {
            Date entrada = (Date) hora_entrada.getValue();
            Date ausencia = (Date) hora_ausencia.getValue(); // asegúrate de que hora_ausencia sea un atributo global

            long diferenciaMillis = ausencia.getTime() - entrada.getTime();

            if (diferenciaMillis < 0) {
                txttiempo_injustificado.setText("00:00");
                return;
            }

            long totalMinutos = diferenciaMillis / (60 * 1000);
            long horas = totalMinutos / 60;
            long minutos = totalMinutos % 60;

            String tiempoFormateado = String.format("%02d:%02d", horas, minutos);
            txttiempo_injustificado.setText(tiempoFormateado);

        } catch (Exception e) {
            txttiempo_injustificado.setText("00:00");
            System.err.println("Error al calcular el tiempo injustificado: " + e.getMessage());
        }
    }
    
    private void agregarListenersHora() {
        hora_entrada.addChangeListener(e -> calcularTiempoInjustificado());
        hora_ausencia.addChangeListener(e -> calcularTiempoInjustificado());
    }


}
