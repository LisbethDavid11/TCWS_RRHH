package ventanas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import clases.empleado;
import conexion.conexion;
import consultas.consultas_areas;
import consultas.consultas_cargos;
import consultas.consultas_empleado;
import principal.menu_principal;
import reportes.reporte_empleados_individual;

import java.awt.SystemColor;
import javax.swing.UIManager;

@SuppressWarnings("deprecation")
public class empleado_tabla extends JFrame {

    public TableRowSorter<TableModel> trsfiltroCodigo;
    String filtroCodigo;
    empleado clase_empleado = new empleado();
    public JLabel lblCargo;
    public DefaultTableModel tableModel;
    public JTable tableEmpleados;
    public conexion dbConnection;
    public JTextField txtb;
    public JPanel contentPane;
    public JTable table;
    public JScrollPane scrollPane;
    public JButton btnNuevoEmpleado;
    public JButton btnMenu;
    public JComboBox<String> cbxbusquedaCargo;
    public JComboBox<String> cbxbusquedaarea;
    public JComboBox<String> cbxbusquedasexo;
    public JButton btneliminar;
    public JPanel panelbusqueda;
    private final String placeHolderText = "Marcadas, Nombres, Apellidos, Identidad, y Dirección"; 
    public JLabel lblresultado_busqueda;

    public empleado_tabla() {
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

        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(Color.WHITE);
        contentPane.setBounds(0, 0, 1036, 724);
        getContentPane().add(contentPane);

        panelbusqueda = new JPanel();
        panelbusqueda.setLayout(null);
        panelbusqueda.setBackground(SystemColor.menu);
        panelbusqueda.setBounds(25, 79, 985, 46);
        contentPane.add(panelbusqueda);

        txtb = new JTextField();
        txtb.setFont(new Font("Tahoma", Font.PLAIN, 10));
        txtb.setColumns(10);
        txtb.setBounds(68, 10, 271, 27);
        panelbusqueda.add(txtb);

        InputMap map = txtb.getInputMap(JComponent.WHEN_FOCUSED); 
        map.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK), "null");
        txtb.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) {
                if (txtb.getText().length() == 50)
                    ke.consume();

                if (txtb.getText().equals(" ")) {
                    JOptionPane.showMessageDialog(null, "No está permitido ingresar espacios vacíos");
                    txtb.setText("");
                }
            }

            @Override
            public void keyPressed(KeyEvent ke) {}

            @Override
            public void keyReleased(KeyEvent ke) {
                filtro(); // Aplicar filtro al soltar la tecla
            }
        });

        JLabel lblbuscar = new JLabel("Buscar");
        lblbuscar.setHorizontalAlignment(SwingConstants.LEFT);
        lblbuscar.setForeground(Color.BLACK);
        lblbuscar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblbuscar.setBounds(10, 10, 66, 26);
        panelbusqueda.add(lblbuscar);

        cbxbusquedaCargo = new JComboBox<>();
        cbxbusquedaCargo.setFont(new Font("Tahoma", Font.BOLD, 12));
        cbxbusquedaCargo.setBounds(438, 9, 136, 26);
        cbxbusquedaCargo.setSelectedIndex(-1);
        panelbusqueda.add(cbxbusquedaCargo);

        JLabel lblCargo = new JLabel("Cargo");
        lblCargo.setHorizontalAlignment(SwingConstants.LEFT);
        lblCargo.setForeground(Color.BLACK);
        lblCargo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblCargo.setBounds(379, 7, 66, 26);
        panelbusqueda.add(lblCargo);

        cbxbusquedaarea = new JComboBox<>();
        cbxbusquedaarea.setModel(new DefaultComboBoxModel(new String[] {"", " "}));
        cbxbusquedaarea.setFont(new Font("Tahoma", Font.BOLD, 12));
        cbxbusquedaarea.setBounds(638, 9, 136, 26);
        cbxbusquedaarea.setSelectedIndex(-1);
        panelbusqueda.add(cbxbusquedaarea);

        JLabel lblarea = new JLabel("Área");
        lblarea.setHorizontalAlignment(SwingConstants.LEFT);
        lblarea.setForeground(Color.BLACK);
        lblarea.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblarea.setBounds(584, 7, 56, 26);
        panelbusqueda.add(lblarea);

        cbxbusquedasexo = new JComboBox<>();
        cbxbusquedasexo.setModel(new DefaultComboBoxModel<>(new String[] { "Masculino", "Femenino", "Otro", " " }));
        cbxbusquedasexo.setFont(new Font("Tahoma", Font.BOLD, 12));
        cbxbusquedasexo.setBounds(839, 11, 136, 26);
        cbxbusquedasexo.setSelectedIndex(-1);
        panelbusqueda.add(cbxbusquedasexo);

        JLabel lblsexo = new JLabel("Sexo");
        lblsexo.setHorizontalAlignment(SwingConstants.LEFT);
        lblsexo.setForeground(Color.BLACK);
        lblsexo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblsexo.setBounds(784, 7, 56, 26);
        panelbusqueda.add(lblsexo);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBackground(SystemColor.menu);
        panel_1.setBounds(25, 132, 990, 440);
        contentPane.add(panel_1);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 970, 370);
        panel_1.add(scrollPane);
        
        lblresultado_busqueda = new JLabel("");
        lblresultado_busqueda.setHorizontalAlignment(SwingConstants.RIGHT);
        lblresultado_busqueda.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblresultado_busqueda.setBounds(744, 390, 222, 27);
        panel_1.add(lblresultado_busqueda);

        JLabel lbltitulo = new JLabel("COLABORADORES REGISTRADOS");
        lbltitulo.setHorizontalAlignment(SwingConstants.LEFT);
        lbltitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lbltitulo.setBounds(28, 32, 526, 26);
        contentPane.add(lbltitulo);

        JPanel panelbotones = new JPanel();
        panelbotones.setBackground(SystemColor.menu);
        panelbotones.setBounds(552, 23, 458, 56);
        contentPane.add(panelbotones);
        panelbotones.setLayout(null);
       

        btnMenu = new JButton("Menú");
        btnMenu.setBackground(UIManager.getColor("Button.highlight"));
        btnMenu.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnMenu.setToolTipText("Regresar al menú principal");
        btnMenu.setBounds(10, 17, 90, 23);
        panelbotones.add(btnMenu);
        btnMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu_principal menu = new menu_principal();
                menu.setVisible(true);
                menu.setLocationRelativeTo(null);
                dispose();
            }
        });

        btnNuevoEmpleado = new JButton("Nuevo");
        btnNuevoEmpleado.setBackground(UIManager.getColor("Button.highlight"));
        btnNuevoEmpleado.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnNuevoEmpleado.setBounds(358, 17, 90, 23);
        panelbotones.add(btnNuevoEmpleado);
        btnNuevoEmpleado.setIcon(null);
        btnNuevoEmpleado.setToolTipText("Nuevo registro");
        btnNuevoEmpleado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                empleado_nuevo nuevo = new empleado_nuevo();
                nuevo.setVisible(true);
                nuevo.setLocationRelativeTo(null);
                nuevo.btnactualizar.setVisible(false);
                nuevo.txtidOriginal.setVisible(false);
                nuevo.chxeditar.setVisible(false);
                dispose();
            }
        });
        
        btneliminar = new JButton("Eliminar");
        btneliminar.setBackground(UIManager.getColor("Button.highlight"));
        btneliminar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btneliminar.setToolTipText("Eliminar registro");
        btneliminar.setBounds(263, 17, 90, 23);
        panelbotones.add(btneliminar);
        
        JButton btnImprimir = new JButton("Imprimir");
        btnImprimir.addActionListener(e -> {
            int selectedRow = table.getSelectedRow(); 
            if (selectedRow != -1) {
            	int idEmpleado = Integer.parseInt(table.getValueAt(selectedRow, 2).toString());
                reporte_empleados_individual reporte = new reporte_empleados_individual();
                reporte.generarReporteEmpleadoIndividual(idEmpleado);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para continuar", 
                		"Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });




        btnImprimir.setToolTipText("Imprimir registro");
        btnImprimir.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnImprimir.setBackground(UIManager.getColor("Button.highlight"));
        btnImprimir.setBounds(163, 18, 90, 23);
        panelbotones.add(btnImprimir);
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
        	                    "¿Está seguro de que desea eliminar el registro seleccionado?\nEsto también lo eliminará permanentemente de la base de datos", 
        	                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        	            
        	            if (confirmacion == JOptionPane.YES_OPTION) {
        	                
        	            	String id = table.getValueAt(filaSeleccionada, 1).toString(); // <- CORRECTO

        	                consultas_empleado consulta = new consultas_empleado();
        	                
        	                if (consulta.eliminar_empleado(Integer.parseInt(id))) {
        	                   
        	                    ((DefaultTableModel) table.getModel()).removeRow(filaSeleccionada);
        	                    JOptionPane.showMessageDialog(null, "El registro ha sido eliminado correctamente de la tabla y la base de datos", 
        	                    		"Éxito", JOptionPane.INFORMATION_MESSAGE );
        	                    actualizarConteoRegistros();
        	                } else {
        	                    
        	                    JOptionPane.showMessageDialog(null, "Error al eliminar el registro de la base de datos.", 
        	                    		"Error", JOptionPane.ERROR_MESSAGE);
        	                }
        	            } 
        	        }
        	    } catch (HeadlessException ex) {
        	        JOptionPane.showMessageDialog(null, "Error, inténtelo nuevamente", 
        	        		"Error en la operación", JOptionPane.ERROR_MESSAGE);
        	    }
        	}

        });

        cargarCargosEnComboBox();
        cargarAreasEnComboBox();
        
        // Configuracion para los JComboBox
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

        cbxbusquedasexo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicarFiltros();
            }
        });

        
        // Configuración del placeholder
        txtb.setText(placeHolderText);
        txtb.setForeground(Color.GRAY);

        txtb.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtb.getText().equals(placeHolderText)) {
                    txtb.setText("");
                    txtb.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtb.getText().isEmpty()) {
                    txtb.setForeground(Color.GRAY);
                    txtb.setText(placeHolderText);
                }
            }
        });
        
    }//class

    private void actualizarConteoRegistros() {
        int registrosVisibles = table.getRowCount(); // Obtiene el número de filas visibles en la tabla
        lblresultado_busqueda.setText("Registros: " + registrosVisibles);
    }

  
    
    @SuppressWarnings("serial")
	public void construirTabla() {
        // Definir títulos de las columnas
        String titulos[] = { 
        		"No", "DB", "Marcadas",  "Identidad", "Nombres", "Apellidos", "Sexo", "Nacimiento", 
            "Estado civil", "Dirección", "Teléfono", "Correo", "Cargo", "Área", 
            "Inicio", "Renuncia", "Fotografía", "No.cuenta" 
        };

        // Llamar a un método para obtener la matriz de datos desde la base de datos
        String informacion[][] = obtenerMatriz(); // Método debe estar implementado en esta clase

        // Crear modelo de tabla no editable
        DefaultTableModel modeloTabla = new DefaultTableModel(informacion, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Deshabilitar edición de celdas
            }
        };

        // Inicializar la tabla con el modelo
        table = new JTable(modeloTabla) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Celdas no editables
            }
        };

        // Configurar propiedades de la tabla
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(32, 136, 203));
        table.getTableHeader().setForeground(Color.WHITE);

        // Agregar la tabla al JScrollPane
        scrollPane.setViewportView(table); // scrollPane debe estar inicializado en la clase

        // Configurar sorter para el filtro de búsqueda
        trsfiltroCodigo = new TableRowSorter<>(table.getModel());
        table.setRowSorter(trsfiltroCodigo);

        // Ajustar el ancho de las columnas
        table.getColumnModel().getColumn(0).setPreferredWidth(30); // Número
        table.getColumnModel().getColumn(1).setPreferredWidth(40); // ID

        // Formato para las fechas
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
        
        actualizarConteoRegistros();

        // Agregar evento de doble clic
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Doble clic
                    int filaSeleccionada = table.getSelectedRow();
                    if (filaSeleccionada != -1) { // Validar que hay una fila seleccionada
                        int fila = table.convertRowIndexToModel(filaSeleccionada); // Convertir índice visual a modelo

                        try {
                            // ID visual de la tabla (No): solo para numeración, no lo uses como dato real
                            String idOriginalStr = String.valueOf(table.getModel().getValueAt(fila, 1)); // ID (clave real)

                            // Campos reales
                            String idEmpleado = String.valueOf(table.getModel().getValueAt(fila, 2));     // ID Empleado
                            String identidad = String.valueOf(table.getModel().getValueAt(fila, 3));      // Identidad
                            String nombres = String.valueOf(table.getModel().getValueAt(fila, 4));        // Nombres
                            String apellidos = String.valueOf(table.getModel().getValueAt(fila, 5));      // Apellidos
                            String sexo = String.valueOf(table.getModel().getValueAt(fila, 6));           // Sexo

                            Date fechaNacimiento = null;
                            Date fechaInicio = null;
                            Date fechaRenuncia = null;

                            try {
                                fechaNacimiento = dateFormat.parse(String.valueOf(table.getModel().getValueAt(fila, 7))); // Nacimiento
                                fechaInicio = dateFormat.parse(String.valueOf(table.getModel().getValueAt(fila, 14)));    // Inicio
                                String fechaRenunciaStr = String.valueOf(table.getModel().getValueAt(fila, 15));          // Renuncia
                                if (!fechaRenunciaStr.isEmpty() && !fechaRenunciaStr.equals("null")) {
                                    fechaRenuncia = dateFormat.parse(fechaRenunciaStr);
                                }
                            } catch (ParseException ex) {
                                JOptionPane.showMessageDialog(null, "Error al procesar las fechas", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            String estadoCivil = String.valueOf(table.getModel().getValueAt(fila, 8));
                            String direccion = String.valueOf(table.getModel().getValueAt(fila, 9));
                            String telefono = String.valueOf(table.getModel().getValueAt(fila, 10));
                            String correo = String.valueOf(table.getModel().getValueAt(fila, 11));
                            String cargo = String.valueOf(table.getModel().getValueAt(fila, 12));
                            String area = String.valueOf(table.getModel().getValueAt(fila, 13));
                            String fotografia = String.valueOf(table.getModel().getValueAt(fila, 16));
                            String cuenta = String.valueOf(table.getModel().getValueAt(fila, 17));

                            // Abrir formulario con datos
                            empleado_nuevo ventanaNuevo = new empleado_nuevo();
                            ventanaNuevo.cargarCargosEnComboBox();
                            ventanaNuevo.cargarAreasEnComboBox();

                            ventanaNuevo.ver_empleado(
                                idEmpleado, identidad, nombres, apellidos, sexo, fechaNacimiento,
                                estadoCivil, direccion, telefono, correo, cargo, area,
                                fechaInicio, fechaRenuncia, fotografia, cuenta
                            );

                            ventanaNuevo.txtidOriginal.setText(idOriginalStr);
                            ventanaNuevo.setLocationRelativeTo(null);

                            ventanaNuevo.btnguardar.setVisible(false);
                            ventanaNuevo.btnactualizar.setVisible(false);
                            ventanaNuevo.btnlimpiar.setVisible(false);

                            dispose(); // Cerrar la ventana actual
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Error al procesar los datos", "Error", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                        }

                    }
                }
            }
        });
    }

    
   
    private void aplicarFiltros() {
        String filtroCargo = (String) cbxbusquedaCargo.getSelectedItem();
        String filtroArea = (String) cbxbusquedaarea.getSelectedItem();
        String filtroSexo = (String) cbxbusquedasexo.getSelectedItem();

        List<RowFilter<Object, Object>> filtros = new ArrayList<>();

        if (filtroCargo != null && !filtroCargo.trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter("(?i)" + filtroCargo, 12)); 
        }

        if (filtroArea != null && !filtroArea.trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter("(?i)" + filtroArea, 13)); 
        }

        if (filtroSexo != null && !filtroSexo.trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter("(?i)" + filtroSexo, 6));
        }

        if (filtros.isEmpty()) {
            trsfiltroCodigo.setRowFilter(null); 
        } else {
            RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filtros);
            trsfiltroCodigo.setRowFilter(combinedFilter);
        }

        // Actualizar el conteo de registros visibles
        actualizarConteoRegistros();
    }

    
    

    public static String[][] obtenerMatriz() {
        ArrayList<empleado> miLista = buscarUsuariosConMatriz();
        String matrizInfo[][] = new String[miLista.size()][18];

        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yy");

        for (int i = 0; i < miLista.size(); i++) {
            matrizInfo[i][0] = String.valueOf(i + 1); // "No" visual
            matrizInfo[i][1] = miLista.get(i).getId() + ""; // ID (clave primaria real)
            matrizInfo[i][2] = miLista.get(i).getId_empleado() + ""; // ID Empleado

            matrizInfo[i][3] = miLista.get(i).getIdentidad_empleado();
            matrizInfo[i][4] = miLista.get(i).getNombres_empleado();
            matrizInfo[i][5] = miLista.get(i).getApellidos_empleado();
            matrizInfo[i][6] = miLista.get(i).getSexo_empleado();

            Date nacimiento = miLista.get(i).getNacimiento_empleado();
            matrizInfo[i][7] = (nacimiento != null) ? outputFormat.format(nacimiento) : "";

            matrizInfo[i][8] = miLista.get(i).getCivil_empleado();
            matrizInfo[i][9] = miLista.get(i).getDireccion_empleado();
            matrizInfo[i][10] = miLista.get(i).getTel_empleado();
            matrizInfo[i][11] = miLista.get(i).getCorreo_empleado();
            matrizInfo[i][12] = miLista.get(i).getCargo_empleado();
            matrizInfo[i][13] = miLista.get(i).getArea_empleado();

            Date inicio = miLista.get(i).getInicio_empleado();
            matrizInfo[i][14] = (inicio != null) ? outputFormat.format(inicio) : "";

            Date renuncia = miLista.get(i).getRenuncia_empleado();
            matrizInfo[i][15] = (renuncia != null) ? outputFormat.format(renuncia) : "";

            matrizInfo[i][16] = miLista.get(i).getFotografia_empleado();
            matrizInfo[i][17] = miLista.get(i).getCuenta_empleado();
        }

        return matrizInfo;
    }


    public static ArrayList<empleado> buscarUsuariosConMatriz() {
        conexion conex = new conexion();
        ArrayList<empleado> miLista = new ArrayList<>();
        try {
            Statement estatuto = conex.conectar().createStatement();
            ResultSet rs = estatuto.executeQuery("select * from empleados");

            while (rs.next()) {
                empleado empleado = new empleado();
                String idStr = rs.getString("id");
                empleado.setId((idStr != null && !idStr.isEmpty()) ? Integer.parseInt(idStr) : 0);

                String idEmpleadoStr = rs.getString("id_empleado");
                empleado.setId_empleado((idEmpleadoStr != null && !idEmpleadoStr.isEmpty()) ? Integer.parseInt(idEmpleadoStr) : 0);
                empleado.setIdentidad_empleado(rs.getString("identidad_empleado"));
                empleado.setNombres_empleado(rs.getString("nombres_empleado"));
                empleado.setApellidos_empleado(rs.getString("apellidos_empleado"));
                empleado.setSexo_empleado(rs.getString("sexo_empleado"));
                empleado.setNacimiento_empleado(rs.getDate("nacimiento_empleado"));
                empleado.setCivil_empleado(rs.getString("civil_empleado"));
                empleado.setDireccion_empleado(rs.getString("direccion_empleado"));
                empleado.setTel_empleado(rs.getString("tel_empleado"));
                empleado.setCorreo_empleado(rs.getString("correo_empleado"));
                empleado.setCargo_empleado(rs.getString("cargo_empleado"));
                empleado.setArea_empleado(rs.getString("area_empleado"));
                empleado.setInicio_empleado(rs.getDate("inicio_empleado"));
                empleado.setRenuncia_empleado(rs.getDate("renuncia_empleado"));
                empleado.setFotografia_empleado(rs.getString("fotografia_empleado"));
                empleado.setCuenta_empleado(rs.getString("cuenta_empleado"));
                miLista.add(empleado);
            }
            rs.close();
            estatuto.close();
            conex.desconectar(null);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al consultar la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return miLista;
    }

    public void filtro() {
        filtroCodigo = txtb.getText();
        trsfiltroCodigo.setRowFilter(RowFilter.regexFilter("(?i)" + filtroCodigo, 2, 3, 4, 5, 6, 8, 9));
        
        actualizarConteoRegistros();
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
}//end