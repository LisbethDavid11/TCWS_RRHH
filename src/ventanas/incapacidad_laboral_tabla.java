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
import consultas.consultas_areas;
import consultas.consultas_cargos;
import consultas.consultas_incapacidad_laboral;
import principal.menu_principal;
import java.awt.Window.Type;

@SuppressWarnings({ "serial", "unused" })
public class incapacidad_laboral_tabla extends JFrame {
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel modeloTabla;
    private JComboBox<String> cbxbusquedaCargo, cbxbusquedaarea;
    private JDateChooser desde_buscar, hasta_buscar;
    private JTextField txtbuscar;
    private TableRowSorter<DefaultTableModel> trsfiltroCodigo;
    public JButton btnactualizar, btnregresar, btnnuevo, btneliminar;
    public JLabel lblresultado_busqueda;

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
        scrollPane.setBounds(10, 10, 970, 370);
        panel_tabla.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        
        lblresultado_busqueda = new JLabel("");
        lblresultado_busqueda.setHorizontalAlignment(SwingConstants.RIGHT);
        lblresultado_busqueda.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblresultado_busqueda.setBounds(746, 390, 222, 27);
        panel_tabla.add(lblresultado_busqueda);

        JPanel panelbusqueda = new JPanel();
        panelbusqueda.setLayout(null);
        panelbusqueda.setBackground(SystemColor.menu);
        panelbusqueda.setBounds(25, 77, 990, 46);
        getContentPane().add(panelbusqueda);

        txtbuscar = new JTextField();
        txtbuscar.setFont(new Font("Tahoma", Font.PLAIN, 10));
        txtbuscar.setBounds(68, 10, 216, 27);
        panelbusqueda.add(txtbuscar);

        final String placeHolderText = "Nombres, apellidos, identidad y sexo";

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

        cbxbusquedaCargo = new JComboBox<String>();
		cbxbusquedaCargo.setSelectedIndex(-1);
		cbxbusquedaCargo.setFont(new Font("Tahoma", Font.BOLD, 11));
		cbxbusquedaCargo.setBounds(347, 12, 111, 26);
		panelbusqueda.add(cbxbusquedaCargo);
		
		
        JLabel lblCargo = new JLabel("Cargo");
        lblCargo.setHorizontalAlignment(SwingConstants.LEFT);
        lblCargo.setForeground(Color.BLACK);
        lblCargo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblCargo.setBounds(294, 11, 66, 26);
        panelbusqueda.add(lblCargo);

        cbxbusquedaarea = new JComboBox<String>();
		//cbxbusquedaarea.setModel(new DefaultComboBoxModel<>(new String[] { "Administrativa", "Financiera", "Pre basica", "Primaria", "Secundaria", "Logistica", "Aseo", "Mantenimiento", " " }));
		cbxbusquedaarea.setSelectedIndex(-1);
		cbxbusquedaarea.setFont(new Font("Tahoma", Font.BOLD, 11));
		cbxbusquedaarea.setBounds(516, 12, 111, 26);
		panelbusqueda.add(cbxbusquedaarea);

        JLabel lblarea = new JLabel("Área");
        lblarea.setHorizontalAlignment(SwingConstants.LEFT);
        lblarea.setForeground(Color.BLACK);
        lblarea.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblarea.setBounds(468, 10, 56, 26);
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
        panelbotones.setBounds(564, 23, 451, 56);
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
                nuevo.chxeditar.setVisible(false);
                dispose();
            }
        });
        btnnuevo.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnnuevo.setToolTipText("Nuevo registro");
        btnnuevo.setBounds(351, 17, 90, 23);
        panelbotones.add(btnnuevo);

        btneliminar = new JButton("Eliminar");
        btneliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btneliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada;
                try {
                    filaSeleccionada = table.getSelectedRow();
                    if (filaSeleccionada == -1) {
                        JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para continuar", 
                        		"Advertencia", JOptionPane.WARNING_MESSAGE);
                    } else {
                        int confirmacion = JOptionPane.showConfirmDialog(null,
                                "¿Está seguro de que desea eliminar el registro seleccionado?\nEsto también lo "
                                + "eliminará permanentemente de la base de datos.",
                                "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                        if (confirmacion == JOptionPane.YES_OPTION) {
                            String id = table.getValueAt(filaSeleccionada, 0).toString();

                            consultas_incapacidad_laboral consulta = new consultas_incapacidad_laboral();

                            if (consulta.eliminar_incapacidad_laboral(Integer.parseInt(id))) {
                                ((DefaultTableModel) table.getModel()).removeRow(filaSeleccionada);
                                JOptionPane.showMessageDialog(null, "El registro ha sido eliminado correctamente de la tabla y la base de datos.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Error al eliminar el registro de la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                } catch (HeadlessException ex) {
                    JOptionPane.showMessageDialog(null, "Error, inténtelo nuevamente", "Error en la operación", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btneliminar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btneliminar.setToolTipText("Eliminar registro");
        btneliminar.setBounds(251, 17, 90, 23);
        panelbotones.add(btneliminar);

        JLabel lblPermisosAusenciaLaboral = new JLabel("INCAPACIDADES POR AUSENCIA LABORAL");
        lblPermisosAusenciaLaboral.setHorizontalAlignment(SwingConstants.LEFT);
        lblPermisosAusenciaLaboral.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblPermisosAusenciaLaboral.setBounds(25, 30, 534, 26);
        getContentPane().add(lblPermisosAusenciaLaboral);

        construirTabla(); // Construir la tabla al iniciar
        
        cbxbusquedaCargo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicarFiltros();
            }
        });

        cbxbusquedaarea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicarFiltros();
            }
        });
        
        
        cargarAreasEnComboBox();
        cargarCargosEnComboBox();
        
        
    }//class
    
    
    private void actualizarConteoRegistros() {
        int registrosVisibles = table.getRowCount(); // Obtiene el número de filas visibles en la tabla
        lblresultado_busqueda.setText("Registros: " + registrosVisibles);
    }
    
    
    

    private void filtro() {
        String filtroTexto = txtbuscar.getText();
        if (trsfiltroCodigo != null) {
            trsfiltroCodigo.setRowFilter(RowFilter.regexFilter("(?i)" + filtroTexto, 1, 2, 3, 4));  
        }
        actualizarConteoRegistros();
        
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
        
     // Configurar propiedades de la tabla
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(32, 136, 203));
        table.getTableHeader().setForeground(Color.WHITE);

        table.getColumnModel().getColumn(0).setPreferredWidth(50); 
        table.getColumnModel().getColumn(1).setPreferredWidth(60);  
        table.getColumnModel().getColumn(10).setPreferredWidth(60); 

        trsfiltroCodigo = new TableRowSorter<>(modeloTabla);
        table.setRowSorter(trsfiltroCodigo); 

        scrollPane.setViewportView(table);
        
        actualizarConteoRegistros();

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
    
    
    private void pasarDatosAlFormulario(int fila) {
        int filaModelo = table.convertRowIndexToModel(fila);

        incapacidad_laboral_nuevo formulario = new incapacidad_laboral_nuevo();  
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

        // Procesar la hora
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

        desactivarComponentes(formulario);
        formulario.btnactualizar.setVisible(false);
        formulario.btnlimpiar.setVisible(false);
        formulario.btnguardar.setVisible(false);
        formulario.setVisible(true);
        formulario.setLocationRelativeTo(null);
    }




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

    private void aplicarFiltros() {
        String filtroCargo = (String) cbxbusquedaCargo.getSelectedItem();
        String filtroArea = (String) cbxbusquedaarea.getSelectedItem();
        List<RowFilter<Object, Object>> filtros = new ArrayList<>();

        if (filtroCargo != null && !filtroCargo.trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter("(?i)" + filtroCargo, 7)); 
        }

        if (filtroArea != null && !filtroArea.trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter("(?i)" + filtroArea, 8)); 
        }
        

        if (filtros.isEmpty()) {
            trsfiltroCodigo.setRowFilter(null); 
        } else {
        	
            RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filtros);
            trsfiltroCodigo.setRowFilter(combinedFilter);
        }

        if (desde_buscar.getDate() != null && hasta_buscar.getDate() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");

            filtros.add(new RowFilter<Object, Object>() {
                @Override
                public boolean include(Entry<? extends Object, ? extends Object> entry) {
                    try {
                        String fechaInicioStr = entry.getStringValue(12); 
                        String fechaFinStr = entry.getStringValue(13);   
                        Date fechaInicio = dateFormat.parse(fechaInicioStr);
                        Date fechaFin = dateFormat.parse(fechaFinStr);

                        Date fechaDesde = desde_buscar.getDate();
                        Date fechaHasta = hasta_buscar.getDate();

                        return (fechaInicio.equals(fechaDesde) || fechaInicio.after(fechaDesde))
                                && (fechaFin.equals(fechaHasta) || fechaFin.before(fechaHasta));
                    } catch (ParseException e) {
                        return false;
                    }
                }
            });
        }
        if (!filtros.isEmpty()) {
            RowFilter<Object, Object> filtroCombinado = RowFilter.andFilter(filtros);
            trsfiltroCodigo.setRowFilter(filtroCombinado); 
        } else {
            trsfiltroCodigo.setRowFilter(null); 
        }
        
        actualizarConteoRegistros();
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
    
    
    private void cargarCargosEnComboBox() {
	    consultas_cargos consultas = new consultas_cargos();
	    List<String> cargos = consultas.obtenerCargos();
	    cbxbusquedaCargo.removeAllItems();
	    cbxbusquedaCargo.addItem(" ");
	    
	    for (String cargo : cargos) {
	    	cbxbusquedaCargo.addItem(cargo);
	    }
	    
	    cbxbusquedaCargo.setSelectedIndex(0);
	}
    
    
    
    private void cargarAreasEnComboBox() {
	    consultas_areas consultas = new consultas_areas();
	    List<String> areas = consultas.obtenerAreas();
	    cbxbusquedaarea.removeAllItems();
	    cbxbusquedaarea.addItem(" ");
	    
	    for (String area : areas) {
	    	cbxbusquedaarea.addItem(area);
	    }
	    
	    cbxbusquedaarea.setSelectedIndex(0);
	}
    
}
