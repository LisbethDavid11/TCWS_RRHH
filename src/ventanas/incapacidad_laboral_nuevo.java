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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import clases.incapacidad_temporal;
import conexion.conexion;
import consultas.consultas_incapacidad_laboral;

import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class incapacidad_laboral_nuevo extends JFrame {
    private JTextField txtidentidad;
    private JTextField txtapellidos;
    private JTextField txttel;
    private JTextField txtid_empleado;
    private JTextField txtcargo;
    private JTextField txtarea;
    private JTextField txttotal_dias;
    private JTextField txttipo;
    private JTextField txtfecha_actual;
    private JTextField txtnumero;
    private JTextField txthora_actual;
    private JTextField txtedad;
    private JTextField txtsexo;
    private JTextField txtcorreo;
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
        
        

        JLabel lblIncapacidadLaboral = new JLabel("INCAPACIDAD LABORAL");
        lblIncapacidadLaboral.setHorizontalAlignment(SwingConstants.LEFT);
        lblIncapacidadLaboral.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblIncapacidadLaboral.setBackground(new Color(255, 153, 0));
        lblIncapacidadLaboral.setBounds(31, 28, 442, 36);
        getContentPane().add(lblIncapacidadLaboral);

        JPanel panel_botones = new JPanel();
        panel_botones.setLayout(null);
        panel_botones.setBackground(SystemColor.menu);
        panel_botones.setBounds(490, 10, 516, 65);
        getContentPane().add(panel_botones);

        btnguardar = new JButton("Guardar");
        btnguardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		guardar_incapacidad();
        	}
        });
        btnguardar.setFont(new Font("Tahoma", Font.BOLD, 8));
        btnguardar.setBackground(Color.WHITE);
        btnguardar.setBounds(415, 17, 75, 23);
        panel_botones.add(btnguardar);

        btnactualizar = new JButton("Actualizar");
        btnactualizar.setFont(new Font("Tahoma", Font.BOLD, 8));
        btnactualizar.setBackground(Color.WHITE);
        btnactualizar.setBounds(314, 17, 75, 23);
        panel_botones.add(btnactualizar);

        btnlimpiar = new JButton("Limpiar");
        btnlimpiar.setFont(new Font("Tahoma", Font.BOLD, 8));
        btnlimpiar.setBackground(Color.WHITE);
        btnlimpiar.setBounds(216, 17, 75, 23);
        panel_botones.add(btnlimpiar);

        btnregresar = new JButton("Regresar");
        btnregresar.setToolTipText("Regresar a la tabla");
        btnregresar.setFont(new Font("Tahoma", Font.BOLD, 8));
        btnregresar.setBackground(Color.WHITE);
        btnregresar.setBounds(10, 17, 75, 23);
        panel_botones.add(btnregresar);
        
        JCheckBox chxeditar = new JCheckBox("Editar registro");
        chxeditar.setFont(new Font("Tahoma", Font.PLAIN, 13));
        chxeditar.setBounds(91, 38, 132, 21);
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
        fecha_inicio.setDateFormatString("dd-MM-yy");
        fecha_inicio.setBounds(436, 421, 208, 28);
        panel_datos.add(fecha_inicio);

        JLabel lbldesde = new JLabel("Fecha de inicio");
        lbldesde.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbldesde.setBounds(436, 396, 133, 25);
        panel_datos.add(lbldesde);

        JLabel lblhasta = new JLabel("Fecha de finalización");
        lblhasta.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblhasta.setBounds(742, 396, 168, 25);
        panel_datos.add(lblhasta);

        txttotal_dias = new JTextField();
        txttotal_dias.setHorizontalAlignment(SwingConstants.CENTER);
        txttotal_dias.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txttotal_dias.setEditable(false);
        txttotal_dias.setColumns(10);
        txttotal_dias.setBounds(688, 455, 67, 33);
        panel_datos.add(txttotal_dias);

        JLabel lbltotal1 = new JLabel("Total de días");
        lbltotal1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbltotal1.setBounds(579, 459, 116, 29);
        panel_datos.add(lbltotal1);

        JLabel lblDatosDel_1 = new JLabel("_______ Datos del empleado__________________________________________________________________________________");
        lblDatosDel_1.setHorizontalAlignment(SwingConstants.LEFT);
        lblDatosDel_1.setForeground(Color.GRAY);
        lblDatosDel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDatosDel_1.setBounds(30, 10, 919, 28);
        panel_datos.add(lblDatosDel_1);

        JLabel lblNombreDeQuien = new JLabel("Tipo de incapacidad");
        lblNombreDeQuien.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNombreDeQuien.setBounds(33, 430, 221, 25);
        panel_datos.add(lblNombreDeQuien);

        txttipo = new JTextField();
        txttipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txttipo.setColumns(10);
        txttipo.setBounds(31, 455, 327, 33);
        panel_datos.add(txttipo);

        txariesgo = new JTextArea();
        txariesgo.setWrapStyleWord(true);
        txariesgo.setLineWrap(true);
        txariesgo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txariesgo.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txariesgo.setBounds(31, 327, 327, 94);
        panel_datos.add(txariesgo);

        JLabel lblPresuncinDeRiesgo = new JLabel("Presunción de riesgo");
        lblPresuncinDeRiesgo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblPresuncinDeRiesgo.setBounds(31, 302, 232, 25);
        panel_datos.add(lblPresuncinDeRiesgo);

        JLabel lblid_permiso = new JLabel("Certificado No.");
        lblid_permiso.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblid_permiso.setBounds(31, 256, 125, 25);
        panel_datos.add(lblid_permiso);

        JLabel lblhoy_es = new JLabel("Fecha actual:");
        lblhoy_es.setForeground(SystemColor.inactiveCaptionText);
        lblhoy_es.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblhoy_es.setBounds(739, 268, 111, 25);
        panel_datos.add(lblhoy_es);

        txtfecha_actual = new JTextField();
        txtfecha_actual.setText("11-09-24");
        txtfecha_actual.setHorizontalAlignment(SwingConstants.CENTER);
        txtfecha_actual.setFont(new Font("Tahoma", Font.BOLD, 14));
        txtfecha_actual.setEditable(false);
        txtfecha_actual.setColumns(10);
        txtfecha_actual.setBackground(SystemColor.menu);
        txtfecha_actual.setBounds(847, 264, 103, 33);
        panel_datos.add(txtfecha_actual);

        JLabel lblDatosDel_2 = new JLabel("_______ Datos de la incapacidad__________________________________________________________________________________");
        lblDatosDel_2.setHorizontalAlignment(SwingConstants.LEFT);
        lblDatosDel_2.setForeground(Color.GRAY);
        lblDatosDel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDatosDel_2.setBounds(31, 218, 919, 28);
        panel_datos.add(lblDatosDel_2);

        txtnumero = new JTextField();
        txtnumero.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtnumero.setColumns(10);
        txtnumero.setBounds(150, 252, 208, 33);
        panel_datos.add(txtnumero);

        fecha_expedicion = new JDateChooser();
        fecha_expedicion.setDateFormatString("dd-MM-yy");
        fecha_expedicion.setBounds(436, 290, 208, 28);
        panel_datos.add(fecha_expedicion);

        JLabel lblHoraDeExpedicin = new JLabel("Fecha de expedición");
        lblHoraDeExpedicin.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblHoraDeExpedicin.setBounds(436, 264, 168, 25);
        panel_datos.add(lblHoraDeExpedicin);

        hora_expedicion = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(hora_expedicion, "HH:mm");
        hora_expedicion.setEditor(timeEditor); 
        hora_expedicion.setValue(new Date()); 
        hora_expedicion.setBounds(436, 356, 208, 30);
        panel_datos.add(hora_expedicion);

        JLabel lbldesde_1 = new JLabel("Hora de expedición");
        lbldesde_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbldesde_1.setBounds(436, 328, 157, 25);
        panel_datos.add(lbldesde_1);

        fecha_finalizacion = new JDateChooser();
        fecha_finalizacion.setDateFormatString("dd-MM-yy");
        fecha_finalizacion.setBounds(742, 421, 208, 28);
        panel_datos.add(fecha_finalizacion);

        JLabel lblhoy_es_1 = new JLabel("Hora actual:");
        lblhoy_es_1.setForeground(SystemColor.inactiveCaptionText);
        lblhoy_es_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblhoy_es_1.setBounds(739, 306, 111, 25);
        panel_datos.add(lblhoy_es_1);

        txthora_actual = new JTextField();
        txthora_actual.setText("11-09-24");
        txthora_actual.setHorizontalAlignment(SwingConstants.CENTER);
        txthora_actual.setFont(new Font("Tahoma", Font.BOLD, 14));
        txthora_actual.setEditable(false);
        txthora_actual.setColumns(10);
        txthora_actual.setBackground(SystemColor.menu);
        txthora_actual.setBounds(847, 302, 103, 33);
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
        fecha_nacimiento.setBounds(511, 128, 208, 28);
        panel_datos.add(fecha_nacimiento);

        
        
        // Agregar listener para el cálculo de días entre fecha_inicio y fecha_finalizacion
        fecha_inicio.getDateEditor().addPropertyChangeListener(evt -> calcularDias());
        fecha_finalizacion.getDateEditor().addPropertyChangeListener(evt -> calcularDias());

        // Llamada para llenar el ComboBox con nombres de empleados
        llenarComboBoxNombres();
        establecerFechaHoraActual();
        
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

    public void llenarComboBoxNombres() {
        conexion con = new conexion();
        Connection cn = con.conectar();

        String sql = "SELECT nombres_empleado FROM empleados";

        try (PreparedStatement pst = cn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            cbxnombres.removeAllItems();
            cbxnombres.addItem(""); // Espacio en blanco por defecto
            while (rs.next()) {
                cbxnombres.addItem(rs.getString("nombres_empleado"));
            }

            if (cbxnombres.getItemCount() == 1) {
                JOptionPane.showMessageDialog(null, "No se encontraron empleados en la base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al llenar el ComboBox de nombres.");
        } finally {
            con.desconectar();
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

                // Obtener y establecer la fecha de nacimiento
                Date fechaNacimiento = rs.getDate("nacimiento_empleado");
                if (fechaNacimiento != null) {
                    fecha_nacimiento.setDate(fechaNacimiento);

                    // Calcular la edad si la fecha de nacimiento no es nula
                    int edad = calcularEdad(fechaNacimiento);
                    txtedad.setText(String.valueOf(edad));
                } else {
                    fecha_nacimiento.setDate(null); // En caso de que la fecha sea nula
                    txtedad.setText(""); // Limpiar el campo de edad si no hay fecha
                }

            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron datos para el empleado seleccionado.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar los datos del empleado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (cn != null && !cn.isClosed()) {
                    con.desconectar();
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
        // Obtener las fechas seleccionadas
        Date fechaInicio = fecha_inicio.getDate();
        Date fechaFinalizacion = fecha_finalizacion.getDate();
        Date fechaExpedicion = fecha_expedicion.getDate();

        // Verificar si las fechas no son nulas
        if (fechaInicio == null || fechaFinalizacion == null || fechaExpedicion == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar las fechas de inicio, finalización y expedición.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Calcular los días transcurridos entre fechaInicio y fechaFinalizacion
        long diffInMillis = fechaFinalizacion.getTime() - fechaInicio.getTime();
        int diasTranscurridos = (int) (diffInMillis / (1000 * 60 * 60 * 24));

        // Verificar que la fecha de finalización sea posterior a la fecha de inicio
        if (diasTranscurridos <= 0) {
            JOptionPane.showMessageDialog(null, "La fecha de finalización debe ser posterior a la fecha de inicio.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Establecer los días totales
        txttotal_dias.setText(String.valueOf(diasTranscurridos));

        // Validar que todos los campos requeridos estén completos
        if (txtidentidad.getText().isEmpty() || txtapellidos.getText().isEmpty() || txtid_empleado.getText().isEmpty() ||
            txttel.getText().isEmpty() || txtcorreo.getText().isEmpty() || txtcargo.getText().isEmpty() ||
            txtarea.getText().isEmpty() || txtedad.getText().isEmpty() || txtsexo.getText().isEmpty() ||
            txariesgo.getText().isEmpty() || txttipo.getText().isEmpty() || txtnumero.getText().isEmpty()) {
            
            JOptionPane.showMessageDialog(null, "Datos vacíos. Por favor, complete todos los campos para registrar la incapacidad.", 
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Crear objeto incapacidad y asignar valores
            incapacidad_temporal incapacidad = new incapacidad_temporal();
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
            incapacidad.setInicio_incapacida(fechaInicio);
            incapacidad.setFin_incapacidad(fechaFinalizacion);
            incapacidad.setTotal_dias(diasTranscurridos);
            incapacidad.setTipo_incapacidad(txttipo.getText());
            incapacidad.setNumero_certificado(txtnumero.getText());
            incapacidad.setFecha_expedicion(fechaExpedicion);

            // Convertir la hora del JSpinner a java.sql.Time
            Date horaExpedicionDate = (Date) hora_expedicion.getValue();
            Time horaExpedicion = new Time(horaExpedicionDate.getTime());
            incapacidad.setHora_expedicion(horaExpedicion);

            // Establecer la fecha y hora actuales
            incapacidad.setFecha_actual(new Date());

            // Obtener la hora actual como java.sql.Time
            Time horaActual = new Time(System.currentTimeMillis());
            incapacidad.setHora_actual(horaActual);

            // Guardar en la base de datos
            consultas_incapacidad_laboral consulta = new consultas_incapacidad_laboral();
            
            if (consulta.guardar_incapacidad(incapacidad, fechaExpedicion, fechaInicio, fechaFinalizacion)) {
                JOptionPane.showMessageDialog(null, "Incapacidad registrada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar la incapacidad. Verifique los datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error en el formato de los números. Verifique los campos numéricos.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado. Verifique los datos.", "Error", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(null, "La fecha de finalización debe ser mayor que la de inicio.", "Error", JOptionPane.ERROR_MESSAGE);
                txttotal_dias.setText(""); 
            }
        }
    }
}
