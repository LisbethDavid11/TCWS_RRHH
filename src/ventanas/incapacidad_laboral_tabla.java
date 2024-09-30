package ventanas;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;
import com.toedter.calendar.JDateChooser;
import conexion.conexion;
import clases.incapacidad_laboral;
import consultas.consultas_incapacidad_laboral;
import principal.menu_principal;
import java.awt.Window.Type;

public class incapacidad_laboral_tabla extends JFrame {
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel modeloTabla;
    private JComboBox<String> cbxbusquedaCargo, cbxbusquedaarea;
    private JDateChooser desde_buscar, hasta_buscar;
    private JTextField txtbuscar;
    private TableRowSorter<DefaultTableModel> trsfiltroCodigo;
    public JButton btnactualizar, btnregresar, btnnuevo, btneliminar;

    public incapacidad_laboral_tabla() {
    	getContentPane().setBackground(new Color(255, 255, 255));
    	setType(Type.UTILITY);
        setResizable(false);
        setBounds(100, 100, 1050, 630);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				cerrar_ventana();
			}
			});
		
		
        
        JPanel panel_tabla = new JPanel();
        panel_tabla.setLayout(null);
        panel_tabla.setBackground(SystemColor.menu);
        panel_tabla.setBounds(25, 132, 990, 440);
        getContentPane().add(panel_tabla);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 970, 420);
        panel_tabla.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        JPanel panelbusqueda = new JPanel();
        panelbusqueda.setLayout(null);
        panelbusqueda.setBackground(SystemColor.menu);
        panelbusqueda.setBounds(25, 77, 990, 46);
        getContentPane().add(panelbusqueda);

        txtbuscar = new JTextField();
        txtbuscar.setFont(new Font("Tahoma", Font.PLAIN, 10));
        txtbuscar.setBounds(68, 10, 230, 27);
        panelbusqueda.add(txtbuscar);

        final String placeHolderText = "Id empleado, nombres, apellidos e identidad";

        txtbuscar.setText(placeHolderText);
        txtbuscar.setForeground(Color.GRAY);

        txtbuscar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtbuscar.getText().equals(placeHolderText)) {
                    txtbuscar.setText(""); 
                    txtbuscar.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtbuscar.getText().isEmpty()) {
                    txtbuscar.setText(placeHolderText);  
                    txtbuscar.setForeground(Color.GRAY);  
                }
            }
        });

        txtbuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtro();
            }
        });

        JLabel lblbuscar = new JLabel("Buscar");
        lblbuscar.setHorizontalAlignment(SwingConstants.LEFT);
        lblbuscar.setForeground(Color.BLACK);
        lblbuscar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblbuscar.setBounds(10, 10, 66, 26);
        panelbusqueda.add(lblbuscar);

        cbxbusquedaCargo = new JComboBox<>(new String[] { "Director", "Administrador", "Docente", "Supervisor", "Mantenimiento" });
        cbxbusquedaCargo.setSelectedIndex(-1);
        cbxbusquedaCargo.setFont(new Font("Tahoma", Font.BOLD, 11));
        cbxbusquedaCargo.setBounds(365, 12, 111, 26);
        panelbusqueda.add(cbxbusquedaCargo);
        cbxbusquedaCargo.addActionListener(e -> aplicarFiltros());

        JLabel lblCargo = new JLabel("Cargo");
        lblCargo.setHorizontalAlignment(SwingConstants.LEFT);
        lblCargo.setForeground(Color.BLACK);
        lblCargo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblCargo.setBounds(308, 10, 66, 26);
        panelbusqueda.add(lblCargo);

        cbxbusquedaarea = new JComboBox<>(new String[] { "Administrativa", "Financiera", "Docencia", "Mantenimiento" });
        cbxbusquedaarea.setSelectedIndex(-1);
        cbxbusquedaarea.setFont(new Font("Tahoma", Font.BOLD, 11));
        cbxbusquedaarea.setBounds(534, 12, 111, 26);
        panelbusqueda.add(cbxbusquedaarea);
        cbxbusquedaarea.addActionListener(e -> aplicarFiltros());

        JLabel lblarea = new JLabel("Área");
        lblarea.setHorizontalAlignment(SwingConstants.LEFT);
        lblarea.setForeground(Color.BLACK);
        lblarea.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblarea.setBounds(486, 10, 56, 26);
        panelbusqueda.add(lblarea);

        desde_buscar = new JDateChooser();
        desde_buscar.setDateFormatString("dd-MM-yy");
        desde_buscar.setBounds(707, 10, 101, 27);
        panelbusqueda.add(desde_buscar);
        desde_buscar.getDateEditor().addPropertyChangeListener(evt -> aplicarFiltros());

        hasta_buscar = new JDateChooser();
        hasta_buscar.setDateFormatString("dd-MM-yy");
        hasta_buscar.setBounds(879, 10, 101, 27);
        panelbusqueda.add(hasta_buscar);
        hasta_buscar.getDateEditor().addPropertyChangeListener(evt -> aplicarFiltros());

        JLabel lblDesde = new JLabel("Desde");
        lblDesde.setHorizontalAlignment(SwingConstants.LEFT);
        lblDesde.setForeground(Color.BLACK);
        lblDesde.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblDesde.setBounds(655, 10, 56, 26);
        panelbusqueda.add(lblDesde);

        JLabel lblHasta = new JLabel("Hasta");
        lblHasta.setHorizontalAlignment(SwingConstants.LEFT);
        lblHasta.setForeground(Color.BLACK);
        lblHasta.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblHasta.setBounds(829, 10, 56, 26);
        panelbusqueda.add(lblHasta);

        // Panel de botones
        JPanel panelbotones = new JPanel();
        panelbotones.setLayout(null);
        panelbotones.setBackground(SystemColor.menu);
        panelbotones.setBounds(552, 23, 463, 56);
        getContentPane().add(panelbotones);

        btnactualizar = new JButton("Actualizar");
        btnactualizar.setFont(new Font("Tahoma", Font.PLAIN, 9));
        panelbotones.add(btnactualizar);

        btnregresar = new JButton("Menú");
        btnregresar.setBackground(UIManager.getColor("Button.highlight"));
        btnregresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu_principal menu = new menu_principal();
                menu.setVisible(true);
                menu.setLocationRelativeTo(null);
                dispose();
            }
        });
        btnregresar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnregresar.setToolTipText("Regresar al menú principal");
        btnregresar.setBounds(10, 17, 90, 23);
        panelbotones.add(btnregresar);

        btnnuevo = new JButton("Nuevo");
        btnnuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                incapacidad_laboral_nuevo nuevo = new incapacidad_laboral_nuevo();
                nuevo.setVisible(true);
                nuevo.setLocationRelativeTo(null);
                nuevo.btnactualizar.setVisible(false);
                nuevo.btnlimpiar.setVisible(false);
                nuevo.chxeditar.setVisible(false);
                dispose();
            }
        });
        btnnuevo.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnnuevo.setToolTipText("Nuevo registro");
        btnnuevo.setBounds(363, 17, 90, 23);
        panelbotones.add(btnnuevo);

        btneliminar = new JButton("Eliminar");
        /*btneliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada;
                try {
                    filaSeleccionada = table.getSelectedRow();
                    if (filaSeleccionada == -1) {
                        JOptionPane.showMessageDialog(null, "¡No se ha seleccionado ninguna fila!", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    } else {
                        int confirmacion = JOptionPane.showConfirmDialog(null,
                                "¿Está seguro de que desea eliminar el registro seleccionado?\nEsto también lo eliminará permanentemente de la base de datos.",
                                "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

                        if (confirmacion == JOptionPane.YES_OPTION) {
                            String id = table.getValueAt(filaSeleccionada, 0).toString();

                            consultas_incapacidad_laboral consulta = new consultas_incapacidad_laboral();

                            if (consulta.eliminar_incapacidad(Integer.parseInt(id))) {
                                ((DefaultTableModel) table.getModel()).removeRow(filaSeleccionada);
                                JOptionPane.showMessageDialog(null, "El registro ha sido eliminado correctamente de la tabla y la base de datos.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al eliminar el registro de la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } catch (HeadlessException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente", "Error en la operación", JOptionPane.ERROR_MESSAGE);
                }
            }
        });*/
        btneliminar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btneliminar.setToolTipText("Eliminar registro");
        btneliminar.setBounds(263, 17, 90, 23);
        panelbotones.add(btneliminar);

        JLabel lblPermisosAusenciaLaboral = new JLabel("INCAPACIDAD LABORAL");
        lblPermisosAusenciaLaboral.setHorizontalAlignment(SwingConstants.LEFT);
        lblPermisosAusenciaLaboral.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblPermisosAusenciaLaboral.setBounds(25, 23, 514, 26);
        getContentPane().add(lblPermisosAusenciaLaboral);

        construirTabla(); // Construir la tabla al iniciar
    }

    private void filtro() {
        String filtroTexto = txtbuscar.getText();
        if (trsfiltroCodigo != null) {
            trsfiltroCodigo.setRowFilter(RowFilter.regexFilter("(?i)" + filtroTexto, 2, 3, 4));  // Filtro en columnas Nombres, Apellidos, Identidad
        }
    }

    public void construirTabla() {
        String[] titulos = { 
            "No", "Id", "Nombres", "Apellidos", "Identidad", "Teléfono", "Correo", "Cargo", "Área",
            "Sexo", "Edad", "Riesgo", "Inicio", "Fin", "Total días", 
            "Incapacidad", "Reposo", "F. Expedición", "H. Expedición", "N° Certificado", 
            "Fecha Actual", "Hora Actual"
        };

        String[][] datos = obtenerDatosIncapacidad(); 
        modeloTabla = new DefaultTableModel(datos, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  
            }
        };
        table.setModel(modeloTabla);

        table.getColumnModel().getColumn(0).setPreferredWidth(50); 
        table.getColumnModel().getColumn(1).setPreferredWidth(60);  
        table.getColumnModel().getColumn(10).setPreferredWidth(60); 

        trsfiltroCodigo = new TableRowSorter<>(modeloTabla);
        table.setRowSorter(trsfiltroCodigo); 

        scrollPane.setViewportView(table);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {  
                    int filaSeleccionada = table.getSelectedRow();
                    if (filaSeleccionada != -1) { 
                        pasarDatosAlFormulario(filaSeleccionada);  
                    }
                }
            }
        });
    }



    public static String[][] obtenerDatosIncapacidad() {
        ArrayList<incapacidad_laboral> lista = buscarIncapacidades(); 
        String[][] datos = new String[lista.size()][22]; 

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");

        for (int i = 0; i < lista.size(); i++) {
            incapacidad_laboral incapacidad = lista.get(i);

            datos[i][0] = String.valueOf(incapacidad.getId_incapacidad());
            datos[i][1] = String.valueOf(incapacidad.getId_empleado());
            datos[i][2] = incapacidad.getNombres_empleado();
            datos[i][3] = incapacidad.getApellidos_empleado();
            datos[i][4] = incapacidad.getIdentidad_empleado();
            datos[i][5] = incapacidad.getTel_empleado();
            datos[i][6] = incapacidad.getCorreo_empleado();
            datos[i][7] = incapacidad.getCargo_empleado();
            datos[i][8] = incapacidad.getArea_empleado();
            datos[i][9] = incapacidad.getSexo_empleado();
            datos[i][10] = String.valueOf(incapacidad.getEdad_empleado());
            datos[i][11] = incapacidad.getRiesgo_incapacidad();
            datos[i][12] = dateFormat.format(incapacidad.getInicio_incapacidad());
            datos[i][13] = dateFormat.format(incapacidad.getFin_incapacidad());
            datos[i][14] = String.valueOf(incapacidad.getTotal_dias());
            datos[i][15] = incapacidad.getTipo_incapacidad();
            datos[i][16] = incapacidad.getTipo_reposo();
            datos[i][17] = dateFormat.format(incapacidad.getFecha_expedicion());
            datos[i][18] = incapacidad.getHora_expedicion().toString();
            datos[i][19] = incapacidad.getNumero_certificado();
            datos[i][20] = dateFormat.format(incapacidad.getFecha_actual());
            datos[i][21] = incapacidad.getHora_actual().toString();
        }

        	return datos;  
    }
    
    
    // Declarar la ventana como variable estática para controlar si está abierta
    private static incapacidad_laboral_nuevo formulario = null;
    
    private void pasarDatosAlFormulario(int fila) {
        int filaModelo = table.convertRowIndexToModel(fila);  

        if (formulario == null || !formulario.isShowing()) {
            formulario = new incapacidad_laboral_nuevo();  

            formulario.cbxnombres.setSelectedItem(table.getModel().getValueAt(filaModelo, 2).toString());
            formulario.txtapellidos.setText(table.getModel().getValueAt(filaModelo, 3).toString());
            formulario.txtidentidad.setText(table.getModel().getValueAt(filaModelo, 4).toString());
            formulario.txttel.setText(table.getModel().getValueAt(filaModelo, 5).toString());
            formulario.txtcorreo.setText(table.getModel().getValueAt(filaModelo, 6).toString());
            formulario.txtcargo.setText(table.getModel().getValueAt(filaModelo, 7).toString());
            formulario.txtarea.setText(table.getModel().getValueAt(filaModelo, 8).toString());
            formulario.txtsexo.setText(table.getModel().getValueAt(filaModelo, 9).toString());
            formulario.txtedad.setText(table.getModel().getValueAt(filaModelo, 10).toString());
            formulario.txariesgo.setText(table.getModel().getValueAt(filaModelo, 11).toString());

            formulario.txtid_incapacidad.setText(table.getModel().getValueAt(filaModelo, 0).toString());

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
            try {
                formulario.fecha_inicio.setDate(dateFormat.parse(table.getModel().getValueAt(filaModelo, 12).toString()));
                formulario.fecha_finalizacion.setDate(dateFormat.parse(table.getModel().getValueAt(filaModelo, 13).toString()));
                formulario.fecha_expedicion.setDate(dateFormat.parse(table.getModel().getValueAt(filaModelo, 17).toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            formulario.txttotal_dias.setText(table.getModel().getValueAt(filaModelo, 14).toString());
            formulario.txttipo.setText(table.getModel().getValueAt(filaModelo, 15).toString());
            formulario.txtreposo.setText(table.getModel().getValueAt(filaModelo, 16).toString());
            formulario.txtnumero.setText(table.getModel().getValueAt(filaModelo, 19).toString());

            String horaExpedicionStr = table.getModel().getValueAt(filaModelo, 18).toString();
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            try {
                Date horaExpedicion = timeFormat.parse(horaExpedicionStr);
                formulario.hora_expedicion.setValue(horaExpedicion);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            formulario.txtfecha_actual.setText(table.getModel().getValueAt(filaModelo, 20).toString());
            formulario.txthora_actual.setText(table.getModel().getValueAt(filaModelo, 21).toString());

            formulario.setVisible(true);
            formulario.setLocationRelativeTo(null);
            formulario.btnactualizar.setVisible(false);  
            formulario.btnguardar.setVisible(false);    
            formulario.btnlimpiar.setVisible(false);
        } else {
            formulario.toFront();
            formulario.requestFocus();
        }
    }

    // Simulación del método de búsqueda en la base de datos
    public static ArrayList<incapacidad_laboral> buscarIncapacidades() {
        ArrayList<incapacidad_laboral> lista = new ArrayList<>();
        conexion con = new conexion();
        Connection cn = con.conectar();

        try (Statement stmt = cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = stmt.executeQuery("SELECT * FROM incapacidad_laboral")) {

            while (rs.next()) {
                incapacidad_laboral incapacidad = new incapacidad_laboral();
                incapacidad.setId_incapacidad(rs.getInt("id_incapacidad"));
                incapacidad.setId_empleado(rs.getInt("id_empleado"));
                incapacidad.setNombres_empleado(rs.getString("nombres_empleado"));
                incapacidad.setApellidos_empleado(rs.getString("apellidos_empleado"));
                incapacidad.setIdentidad_empleado(rs.getString("identidad_empleado"));
                incapacidad.setTel_empleado(rs.getString("tel_empleado"));
                incapacidad.setCorreo_empleado(rs.getString("correo_empleado"));
                incapacidad.setCargo_empleado(rs.getString("cargo_empleado"));
                incapacidad.setArea_empleado(rs.getString("area_empleado"));
                incapacidad.setSexo_empleado(rs.getString("sexo_empleado"));
                incapacidad.setEdad_empleado(rs.getInt("edad_empleado"));
                incapacidad.setRiesgo_incapacidad(rs.getString("riesgo_incapacidad"));
                incapacidad.setInicio_incapacidad(rs.getDate("inicio_incapacidad"));
                incapacidad.setFin_incapacidad(rs.getDate("fin_incapacidad"));
                incapacidad.setTotal_dias(rs.getInt("total_dias"));
                incapacidad.setTipo_incapacidad(rs.getString("tipo_incapacidad"));
                incapacidad.setTipo_reposo(rs.getString("tipo_reposo"));
                incapacidad.setFecha_expedicion(rs.getDate("fecha_expedicion"));
                incapacidad.setHora_expedicion(rs.getTime("hora_expedicion"));
                incapacidad.setNumero_certificado(rs.getString("numero_certificado"));
                incapacidad.setFecha_actual(rs.getDate("fecha_actual"));
                incapacidad.setHora_actual(rs.getTime("hora_actual"));

                lista.add(incapacidad);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Método para aplicar filtros
    private void aplicarFiltros() {
        List<RowFilter<Object, Object>> filtros = new ArrayList<>();
        if (cbxbusquedaCargo.getSelectedItem() != null && !cbxbusquedaCargo.getSelectedItem().toString().isEmpty()) {
            filtros.add(RowFilter.regexFilter(cbxbusquedaCargo.getSelectedItem().toString(), 7)); // Columna de cargo
        }
        if (cbxbusquedaarea.getSelectedItem() != null && !cbxbusquedaarea.getSelectedItem().toString().isEmpty()) {
            filtros.add(RowFilter.regexFilter(cbxbusquedaarea.getSelectedItem().toString(), 8)); // Columna de área
        }
        if (desde_buscar.getDate() != null && hasta_buscar.getDate() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
            String fechaDesde = dateFormat.format(desde_buscar.getDate());
            String fechaHasta = dateFormat.format(hasta_buscar.getDate());
            filtros.add(RowFilter.dateFilter(RowFilter.ComparisonType.AFTER, desde_buscar.getDate(), 12)); // Inicio incapacidad
            filtros.add(RowFilter.dateFilter(RowFilter.ComparisonType.BEFORE, hasta_buscar.getDate(), 13)); // Fin incapacidad
        }

        if (!filtros.isEmpty()) {
            trsfiltroCodigo.setRowFilter(RowFilter.andFilter(filtros));
        } else {
            trsfiltroCodigo.setRowFilter(null); 
        }
    }

    
    private void desactivarComponentes(incapacidad_laboral_nuevo formulario) {
        formulario.cbxnombres.setEnabled(false);
        formulario.txtapellidos.setEditable(false);
        formulario.txtidentidad.setEditable(false);
        formulario.txttel.setEditable(false);
        formulario.txtcorreo.setEditable(false);
        formulario.txtcargo.setEditable(false);
        formulario.txtarea.setEditable(false);
        formulario.txtsexo.setEditable(false);
        formulario.txtedad.setEditable(false);
        formulario.txariesgo.setEditable(false);
        formulario.fecha_inicio.setEnabled(false);
        formulario.fecha_finalizacion.setEnabled(false);
        formulario.txttotal_dias.setEditable(false);
        formulario.txttipo.setEditable(false);
        formulario.txtreposo.setEditable(false);
        formulario.fecha_expedicion.setEnabled(false);
        formulario.hora_expedicion.setEnabled(false);
        formulario.txtnumero.setEditable(false);
        formulario.txtfecha_actual.setEditable(false);
        formulario.txthora_actual.setEditable(false);
    }
    
    private void cerrar_ventana() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
    
}
