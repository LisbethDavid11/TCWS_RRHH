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
import javax.swing.RowFilter.Entry;
import javax.swing.table.*;
import com.toedter.calendar.JDateChooser;
import conexion.conexion;
import clases.injustificada;
import consultas.consultas_areas;
import consultas.consultas_cargos;
import consultas.consultas_injustificadas;
import principal.menu_principal;
import java.awt.Window.Type;

@SuppressWarnings({ "serial", "unused" })
public class injustificadas_tabla extends JFrame {
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel modeloTabla;
    private JComboBox<String> cbxbusquedaCargo, cbxbusquedaarea;
    private JDateChooser desde_buscar, hasta_buscar;
    private JTextField txtbuscar;
    private TableRowSorter<DefaultTableModel> trsfiltroCodigo;
    public JButton btnactualizar, btnregresar, btnnuevo, btneliminar;
    public JLabel lblresultado_busqueda;

    public injustificadas_tabla() {
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
        txtbuscar.setBounds(68, 10, 235, 27);
        panelbusqueda.add(txtbuscar);

        final String placeHolderText = "Marcadas, nombres, apellidos, identidad y sexo";

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
		cbxbusquedaCargo.setBounds(376, 11, 111, 26);
		panelbusqueda.add(cbxbusquedaCargo);
		
		
        JLabel lblCargo = new JLabel("Cargo");
        lblCargo.setHorizontalAlignment(SwingConstants.LEFT);
        lblCargo.setForeground(Color.BLACK);
        lblCargo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblCargo.setBounds(323, 10, 66, 26);
        panelbusqueda.add(lblCargo);

        cbxbusquedaarea = new JComboBox<String>();
		//cbxbusquedaarea.setModel(new DefaultComboBoxModel<>(new String[] { "Administrativa", "Financiera", "Pre basica", "Primaria", "Secundaria", "Logistica", "Aseo", "Mantenimiento", " " }));
		cbxbusquedaarea.setSelectedIndex(-1);
		cbxbusquedaarea.setFont(new Font("Tahoma", Font.BOLD, 11));
		cbxbusquedaarea.setBounds(545, 11, 111, 26);
		panelbusqueda.add(cbxbusquedaarea);

        JLabel lblarea = new JLabel("Área");
        lblarea.setHorizontalAlignment(SwingConstants.LEFT);
        lblarea.setForeground(Color.BLACK);
        lblarea.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblarea.setBounds(497, 9, 56, 26);
        panelbusqueda.add(lblarea);

        desde_buscar = new JDateChooser();
        desde_buscar.setDateFormatString("dd-MM-yy");
        desde_buscar.setBounds(718, 10, 101, 27);
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
        lblDesde.setBounds(666, 10, 56, 26);
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
                injustificadas_nuevo nuevo = new injustificadas_nuevo();
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

                            consultas_injustificadas consulta = new consultas_injustificadas();

                            if (consulta.eliminar_injustificada(Integer.parseInt(id))) {
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

        JLabel lblPermisosAusenciaLaboral = new JLabel("AUSENCIAS INJUSTIFICADAS\r\n");
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
            trsfiltroCodigo.setRowFilter(RowFilter.regexFilter("(?i)" + filtroTexto, 3, 4, 5, 6, 10));  
        }
        actualizarConteoRegistros();
        
    }
    
    
    private void aplicarFiltros() {
        String filtroCargo = (String) cbxbusquedaCargo.getSelectedItem();
        String filtroArea = (String) cbxbusquedaarea.getSelectedItem();
        List<RowFilter<Object, Object>> filtros = new ArrayList<>();

        if (filtroCargo != null && !filtroCargo.trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter("(?i)" + filtroCargo, 8)); 
        }

        if (filtroArea != null && !filtroArea.trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter("(?i)" + filtroArea, 9)); 
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
                        String fechaInicioStr = entry.getStringValue(13); 
                        String fechaFinStr = entry.getStringValue(14);   
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
    

    public void construirTabla() {
    	String[] titulos = { 
    		    "No", "DB", "Marcadas", "Nombres", "Apellidos", "Identidad", "Teléfono", 
    		    "Correo", "Cargo", "Área", "Sexo", "Edad", "Hora Entrada", "Hora Ausencia", 
    		    "Tiempo Injustificado", "Fecha Ausencia", "Fecha Actual", "Hora Actual", "Motivo"
    		};

        String[][] datos = obtenerDatosInjustificada();
        modeloTabla = new DefaultTableModel(datos, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setModel(modeloTabla);
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(32, 136, 203));
        table.getTableHeader().setForeground(Color.WHITE);

        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(30);
        table.getColumnModel().getColumn(2).setPreferredWidth(50);

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

    public static String[][] obtenerDatosInjustificada() {
        ArrayList<injustificada> lista = buscarInjustificada(); 
        String[][] datos = new String[lista.size()][22];  // Ahora 22 columnas (con "No")

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        for (int i = 0; i < lista.size(); i++) {
            injustificada injustificada = lista.get(i);

            datos[i][0] = String.valueOf(i + 1); // No
            datos[i][1] = String.valueOf(injustificada.getId_injustificadas());
            datos[i][2] = String.valueOf(injustificada.getId_empleado());
            datos[i][3] = injustificada.getNombres_empleado();
            datos[i][4] = injustificada.getApellidos_empleado();
            datos[i][5] = injustificada.getIdentidad_empleado();
            datos[i][6] = injustificada.getTel_empleado();
            datos[i][7] = injustificada.getCorreo_empleado();
            datos[i][8] = injustificada.getCargo_empleado();
            datos[i][9] = injustificada.getArea_empleado();
            datos[i][10] = injustificada.getSexo_empleado();
            datos[i][11] = String.valueOf(injustificada.getEdad_empleado());
            datos[i][12] = timeFormat.format(injustificada.getHora_entrada());     // CORREGIDO
            datos[i][13] = timeFormat.format(injustificada.getHora_ausencia());    // CORREGIDO
            datos[i][14] = timeFormat.format(injustificada.getTiempo_injustificado());
            datos[i][15] = dateFormat.format(injustificada.getFecha_ausencia());
            datos[i][16] = dateFormat.format(injustificada.getFecha_actual());
            datos[i][17] = timeFormat.format(injustificada.getHora_actual());     // opcionalmente corregido
            datos[i][18] = injustificada.getMotivo();
        }
		return datos;
    }
    
    
    

    public static ArrayList<injustificada> buscarInjustificada() {
        ArrayList<injustificada> lista = new ArrayList<>();
        conexion con = new conexion();
        Connection cn = con.conectar();
        String sql = "SELECT * FROM injustificadas";

        try (Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                injustificada inj = new injustificada();
                inj.setId_injustificadas(rs.getInt("id_injustificadas"));
                inj.setId_empleado(rs.getInt("id_empleado"));
                inj.setNombres_empleado(rs.getString("nombres_empleado"));
                inj.setApellidos_empleado(rs.getString("apellidos_empleado"));
                inj.setIdentidad_empleado(rs.getString("identidad_empleado"));
                inj.setTel_empleado(rs.getString("tel_empleado"));
                inj.setCorreo_empleado(rs.getString("correo_empleado"));
                inj.setCargo_empleado(rs.getString("cargo_empleado"));
                inj.setArea_empleado(rs.getString("area_empleado"));
                inj.setNacimiento_empleado(rs.getDate("nacimiento_empleado"));
                inj.setSexo_empleado(rs.getString("sexo_empleado"));
                inj.setEdad_empleado(rs.getInt("edad_empleado"));
                inj.setFecha_actual(rs.getDate("fecha_actual"));
                inj.setHora_actual(rs.getTime("hora_actual"));
                inj.setMotivo(rs.getString("motivo"));
                inj.setHora_entrada(rs.getTime("hora_entrada"));
                inj.setHora_ausencia(rs.getTime("hora_ausencia"));
                inj.setTiempo_injustificado(rs.getTime("tiempo_injustificado"));
                inj.setFecha_ausencia(rs.getDate("fecha_ausencia"));
                lista.add(inj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private void pasarDatosAlFormulario(int fila) {
        int filaModelo = table.convertRowIndexToModel(fila);
        injustificadas_nuevo formulario = new injustificadas_nuevo();

        try {
            formulario.txtid_incapacidad.setText(table.getValueAt(filaModelo, 1).toString());
            formulario.txtid_empleado.setText(table.getValueAt(filaModelo, 2).toString());
            formulario.cbxnombres.setSelectedItem(table.getValueAt(filaModelo, 3).toString());
            formulario.txtapellidos.setText(table.getValueAt(filaModelo, 4).toString());
            formulario.txtidentidad.setText(table.getValueAt(filaModelo, 5).toString());
            formulario.txttel.setText(table.getValueAt(filaModelo, 6).toString());
            formulario.txtcorreo.setText(table.getValueAt(filaModelo, 7).toString());
            formulario.txtcargo.setText(table.getValueAt(filaModelo, 8).toString());
            formulario.txtarea.setText(table.getValueAt(filaModelo, 9).toString());
            formulario.txtsexo.setText(table.getValueAt(filaModelo, 10).toString());
            formulario.txtedad.setText(table.getValueAt(filaModelo, 11).toString());

            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yy");
            SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");  // ✅ formato para hora

            

            // Hora entrada y ausencia
            formulario.hora_entrada.setValue(formatoHora.parse(table.getValueAt(filaModelo, 12).toString()));
            formulario.hora_ausencia.setValue(formatoHora.parse(table.getValueAt(filaModelo, 13).toString()));
            formulario.txttiempo_injustificado.setText(table.getValueAt(filaModelo, 14).toString());

            // Fechas
            String fechaAusenciaStr = table.getValueAt(filaModelo, 15).toString();
            String fechaActualStr = table.getValueAt(filaModelo, 16).toString();

            if (!fechaAusenciaStr.equals("01-01-70")) {
                formulario.fecha_ausencia.setDate(formatoFecha.parse(fechaAusenciaStr));
            }

            if (!fechaActualStr.equals("01-01-70")) {
                formulario.txtfecha_actual.setText(fechaActualStr);
            } else {
                formulario.txtfecha_actual.setText("");
            }

            formulario.txthora_actual.setText(table.getValueAt(filaModelo, 17).toString());
            formulario.txamotivo.setText(table.getValueAt(filaModelo, 18).toString());

        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al convertir fechas u horas del registro seleccionado.\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        formulario.btnguardar.setVisible(false);
        formulario.btnlimpiar.setVisible(false);
        formulario.btnactualizar.setVisible(false);
        formulario.setVisible(true);
        formulario.setLocationRelativeTo(null);
        formulario.chxeditar.setSelected(false);
        formulario.chxeditar.setVisible(true);
        formulario.cbxnombres.setEnabled(false);
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
