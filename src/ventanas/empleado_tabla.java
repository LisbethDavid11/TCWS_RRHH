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
import consultas.consultas_empleado;
import principal.menu_principal;
import java.awt.SystemColor;
import javax.swing.UIManager;

@SuppressWarnings("deprecation")
public class empleado_tabla extends JFrame {

    public TableRowSorter<TableModel> trsfiltroCodigo;
    String filtroCodigo;
    empleado clase_empleado = new empleado();

    //public DefaultTableModel DefaultTableModel;
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
    public JButton btnActualizarEmpleado;
    public JComboBox<String> cbxbusquedaCargo;
    public JComboBox<String> cbxbusquedaarea;
    public JComboBox<String> cbxbusquedasexo;
    public JButton btneliminar;

    private final String placeHolderText = "Nombres, apellidos, identidad, estado civil y teléfono"; // Placeholder definido

    public JPanel panelbusqueda;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    empleado_tabla frame = new empleado_tabla();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                    frame.construirTabla(); // Llamamos a construirTabla para inicializar la tabla y el sorter
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

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
        cbxbusquedaCargo.setModel(new DefaultComboBoxModel(new String[] {"Director general", "Director", "Gerente financiero", "Administrador", "Asistente", "Cobros", "Enfermero", "Psicologo", "Supervisor", "Consejero", "Docente", "Docente auxiliar", "Soporte técnico", "Marketing", "Aseo", "Mantenimiento", "Conserje", " "}));
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
        cbxbusquedaarea.setModel(new DefaultComboBoxModel<>(new String[] { "Administrativa", "Financiera", "Pre basica", "Primaria", "Secundaria", "Logistica", "Aseo", "Mantenimiento", " " }));
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
        scrollPane.setBounds(10, 10, 970, 420);
        panel_1.add(scrollPane);

        JLabel lbltitulo = new JLabel("EMPLEADOS REGISTRADOS");
        lbltitulo.setHorizontalAlignment(SwingConstants.LEFT);
        lbltitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lbltitulo.setBounds(28, 32, 402, 26);
        contentPane.add(lbltitulo);

        JPanel panelbotones = new JPanel();
        panelbotones.setBackground(SystemColor.menu);
        panelbotones.setBounds(552, 23, 458, 56);
        contentPane.add(panelbotones);
        panelbotones.setLayout(null);

        btnActualizarEmpleado = new JButton("Actualizar");
        btnActualizarEmpleado.setBackground(UIManager.getColor("Button.highlight"));
        btnActualizarEmpleado.setFont(new Font("Tahoma", Font.BOLD, 8));
        btnActualizarEmpleado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
                int filaseleccionada;
                try {
                    filaseleccionada = table.getSelectedRow();
                    if (filaseleccionada == -1) {
                        JOptionPane.showMessageDialog(null, "¡No se ha seleccionado ninguna fila!", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    } else {
                        // Obtener los datos de la fila seleccionada
                        String id = table.getValueAt(filaseleccionada, 0).toString();
                        String id_empleado = table.getValueAt(filaseleccionada, 1).toString();
                        String identidad = table.getValueAt(filaseleccionada, 2).toString();
                        String nombres = table.getValueAt(filaseleccionada, 3).toString();
                        String apellidos = table.getValueAt(filaseleccionada, 4).toString();
                        String sexo = table.getValueAt(filaseleccionada, 5).toString();
                        
                        String fecha1 = table.getValueAt(filaseleccionada, 6).toString();
                        Date nacimiento = dateFormat.parse(fecha1);
                        
                        String estadocivil = table.getValueAt(filaseleccionada, 7).toString();
                        String direccion = table.getValueAt(filaseleccionada, 8).toString();
                        String telefono = table.getValueAt(filaseleccionada, 9).toString();
                        String correo = table.getValueAt(filaseleccionada, 10).toString();
                        String cargo = table.getValueAt(filaseleccionada, 11).toString();
                        String area = table.getValueAt(filaseleccionada, 12).toString();
                        
                        String fecha2 = table.getValueAt(filaseleccionada, 13).toString();
                        Date inicio = dateFormat.parse(fecha2);
                        
                        // Manejo de la fecha de renuncia (puede ser nula)
                        String fecha3 = table.getValueAt(filaseleccionada, 14) != null ? table.getValueAt(filaseleccionada, 14).toString() : null;
                        Date renuncia = (fecha3 != null && !fecha3.isEmpty()) ? dateFormat.parse(fecha3) : null;
                        
                        String foto = table.getValueAt(filaseleccionada, 15).toString();
                        String cuenta = table.getValueAt(filaseleccionada, 16).toString();

                        // Abrir la ventana de actualización del empleado
                        empleado_nuevo actualizar_empleado = new empleado_nuevo();
                        actualizar_empleado.setLocationRelativeTo(null);
                        actualizar_empleado.setVisible(true);
                        actualizar_empleado.btnguardar.setVisible(false);
                        dispose();

                        // Rellenar los campos en la ventana de actualización
                        actualizar_empleado.txtid.setText(id);
                        actualizar_empleado.txtid_empleado.setText(id_empleado);
                        actualizar_empleado.txtidentidad.setText(identidad);
                        actualizar_empleado.txtnombres.setText(nombres);
                        actualizar_empleado.txtapellidos.setText(apellidos);

                        // Selección del sexo
                        if (sexo.equals("Femenino")) {
                            actualizar_empleado.buttonfemenino.setSelected(true);
                        } else if (sexo.equals("Masculino")) {
                            actualizar_empleado.buttonmasculino.setSelected(true);
                        } else if (sexo.equals("Otro")) {
                            actualizar_empleado.buttonotro.setSelected(true);
                        }

                        // Establecer las fechas y otros campos
                        actualizar_empleado.fecha_nacimiento.setDate(nacimiento);
                        actualizar_empleado.cbxestado_civil.setSelectedItem(estadocivil);
                        actualizar_empleado.txadireccion.setText(direccion);
                        actualizar_empleado.txttel.setText(telefono);
                        actualizar_empleado.txtcorreo.setText(correo);
                        actualizar_empleado.cbxcargo.setSelectedItem(cargo);
                        actualizar_empleado.cbxarea.setSelectedItem(area);
                        actualizar_empleado.fecha_inicio.setDate(inicio);

                        // Establecer la fecha de renuncia solo si no es nula
                        if (renuncia != null) {
                            actualizar_empleado.fecha_renuncia.setDate(renuncia);
                        } else {
                            actualizar_empleado.fecha_renuncia.setDate(null); // Si no hay renuncia, dejar en blanco
                        }

                        // Establecer la fotografía
                        ImageIcon icono_fotografia = new ImageIcon(foto);
                        actualizar_empleado.lblfoto.setIcon(new ImageIcon(icono_fotografia.getImage().getScaledInstance(
                                actualizar_empleado.lblfoto.getWidth(), actualizar_empleado.lblfoto.getHeight(), Image.SCALE_SMOOTH)));
                        actualizar_empleado.txtruta.setText(foto);
                        actualizar_empleado.txtcuenta.setText(cuenta);
                    }

                } catch (HeadlessException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex + "\n Inténtelo nuevamente",
                            " .::Error En la Operación::.", JOptionPane.ERROR_MESSAGE);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
            }
        });


        btnActualizarEmpleado.setIcon(null);
        btnActualizarEmpleado.setToolTipText("Actualizar registro");
        btnActualizarEmpleado.setBounds(295, 17, 75, 23);
        panelbotones.add(btnActualizarEmpleado);
       

        btnMenu = new JButton("Menú");
        btnMenu.setBackground(UIManager.getColor("Button.highlight"));
        btnMenu.setFont(new Font("Tahoma", Font.BOLD, 8));
        btnMenu.setToolTipText("Menú principal");
        btnMenu.setBounds(10, 17, 75, 23);
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
        btnNuevoEmpleado.setFont(new Font("Tahoma", Font.BOLD, 8));
        btnNuevoEmpleado.setBounds(370, 17, 75, 23);
        panelbotones.add(btnNuevoEmpleado);
        btnNuevoEmpleado.setIcon(null);
        btnNuevoEmpleado.setToolTipText("Nuevo empleado");
        btnNuevoEmpleado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                empleado_nuevo nuevo = new empleado_nuevo();
                nuevo.setVisible(true);
                nuevo.setLocationRelativeTo(null);
                nuevo.btnactualizar.setVisible(false);
                dispose();
            }
        });
        
        btneliminar = new JButton("Eliminar");
        btneliminar.setBackground(UIManager.getColor("Button.highlight"));
        btneliminar.setFont(new Font("Tahoma", Font.BOLD, 8));
        btneliminar.setToolTipText("Eliminar registro");
        btneliminar.setBounds(144, 17, 75, 23);
        panelbotones.add(btneliminar);
        
        JButton btn_ver = new JButton("Ver");
        btn_ver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		verEmpleadoSeleccionado();
        		
        	}
        });
        btn_ver.setToolTipText("Eliminar registro");
        btn_ver.setFont(new Font("Tahoma", Font.BOLD, 8));
        btn_ver.setBackground(UIManager.getColor("Button.highlight"));
        btn_ver.setBounds(220, 17, 75, 23);
        panelbotones.add(btn_ver);
        btneliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    int filaSeleccionada;
        	    try {
        	      
        	        filaSeleccionada = table.getSelectedRow();
        	        if (filaSeleccionada == -1) {
        	            JOptionPane.showMessageDialog(null, "¡No se ha seleccionado ninguna fila!", "Advertencia", JOptionPane.WARNING_MESSAGE);
        	        } else {
        	            
        	            int confirmacion = JOptionPane.showConfirmDialog(null, 
        	                    "¿Está seguro de que desea eliminar el registro seleccionado?\nEsto también lo eliminará permanentemente de la base de datos.", 
        	                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        	            
        	            if (confirmacion == JOptionPane.YES_OPTION) {
        	                
        	                String id = table.getValueAt(filaSeleccionada, 0).toString();

        	                consultas_empleado consulta = new consultas_empleado();
        	                
        	                if (consulta.eliminar_empleado(Integer.parseInt(id))) {
        	                   
        	                    ((DefaultTableModel) table.getModel()).removeRow(filaSeleccionada);
        	                    JOptionPane.showMessageDialog(null, "El registro ha sido eliminado correctamente de la tabla y la base de datos.");
        	                } else {
        	                    
        	                    JOptionPane.showMessageDialog(null, "Error al eliminar el registro de la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        	                }
        	            } // Si el usuario elige "No", no se realiza ninguna acción
        	        }
        	    } catch (HeadlessException ex) {
        	        JOptionPane.showMessageDialog(null, "Error: " + ex + "\nInténtelo nuevamente", "Error en la operación", JOptionPane.ERROR_MESSAGE);
        	    }
        	}

        });

        
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Configurar ActionListener para los JComboBox
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

   

    // Inicialización de la tabla y TableRowSorter
    public void construirTabla() {
        String titulos[] = { "No", "Id", "Identidad", "Nombres", "Apellidos", "Sexo", "Fecha nacimiento", "Estado civil", "Dirección", "Teléfono", "Correo", "Cargo", "Área", "Fecha inicio", "Fecha renuncia", "Fotografía", "No.cuenta" };
        String informacion[][] = obtenerMatriz();
        DefaultTableModel modeloTabla = new DefaultTableModel(informacion, titulos);
        table = new JTable(modeloTabla);
        scrollPane.setViewportView(table);

        trsfiltroCodigo = new TableRowSorter<>(table.getModel()); // Inicializamos el sorter
        table.setRowSorter(trsfiltroCodigo); // Asignamos el sorter a la tabla
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(40);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    

 // Método que aplica los filtros combinados de los tres JComboBox
    private void aplicarFiltros() {
        // Obtener los valores seleccionados en los JComboBox
        String filtroCargo = (String) cbxbusquedaCargo.getSelectedItem();
        String filtroArea = (String) cbxbusquedaarea.getSelectedItem();
        String filtroSexo = (String) cbxbusquedasexo.getSelectedItem();

        // Crear una lista de RowFilter con tipos explícitos
        List<RowFilter<Object, Object>> filtros = new ArrayList<>();

        // Agregar filtro de Cargo si no está en blanco
        if (filtroCargo != null && !filtroCargo.trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter("(?i)" + filtroCargo, 11)); // Columna 10 es "Cargo"
        }

        // Agregar filtro de Área si no está en blanco
        if (filtroArea != null && !filtroArea.trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter("(?i)" + filtroArea, 12)); // Columna 11 es "Área"
        }

        // Agregar filtro de Sexo si no está en blanco
        if (filtroSexo != null && !filtroSexo.trim().isEmpty()) {
            filtros.add(RowFilter.regexFilter("(?i)" + filtroSexo, 5)); // Columna 4 es "Sexo"
        }

        // Si no hay filtros activos, mostramos todos los registros
        if (filtros.isEmpty()) {
            trsfiltroCodigo.setRowFilter(null); // Mostrar todos los registros si no hay filtros
        } else {
            // Aplicar los filtros combinados si hay al menos un filtro activo
            RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filtros);
            trsfiltroCodigo.setRowFilter(combinedFilter);
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////
    

    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    

    public static String[][] obtenerMatriz() {
        ArrayList<empleado> miLista = buscarUsuariosConMatriz();
        String matrizInfo[][] = new String[miLista.size()][17];

        // Formato de fecha deseado: dd-MM-yy
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yy");

        for (int i = 0; i < miLista.size(); i++) {
        	matrizInfo[i][0] = miLista.get(i).getId() + "";
            matrizInfo[i][1] = miLista.get(i).getId_empleado() + "";
            matrizInfo[i][2] = miLista.get(i).getIdentidad_empleado() + "";
            matrizInfo[i][3] = miLista.get(i).getNombres_empleado() + "";
            matrizInfo[i][4] = miLista.get(i).getApellidos_empleado() + "";
            matrizInfo[i][5] = miLista.get(i).getSexo_empleado() + "";

            // Formatear la fecha de nacimiento
            Date nacimiento = miLista.get(i).getNacimiento_empleado();
            matrizInfo[i][6] = outputFormat.format(nacimiento); // Fecha de nacimiento

            matrizInfo[i][7] = miLista.get(i).getCivil_empleado() + "";
            matrizInfo[i][8] = miLista.get(i).getDireccion_empleado() + "";
            matrizInfo[i][9] = miLista.get(i).getTel_empleado() + "";
            matrizInfo[i][10] = miLista.get(i).getCorreo_empleado() + "";
            matrizInfo[i][11] = miLista.get(i).getCargo_empleado() + "";
            matrizInfo[i][12] = miLista.get(i).getArea_empleado() + "";

            // Formatear la fecha de inicio
            Date inicio = miLista.get(i).getInicio_empleado();
            matrizInfo[i][13] = outputFormat.format(inicio); // Fecha de inicio

            // Verificar si hay fecha de renuncia
            Date renuncia = miLista.get(i).getRenuncia_empleado();
            if (renuncia != null) {
                matrizInfo[i][14] = outputFormat.format(renuncia); // Fecha de renuncia
            } else {
                matrizInfo[i][14] = ""; // Sin fecha de renuncia
            }

            matrizInfo[i][15] = miLista.get(i).getFotografia_empleado() + "";
            matrizInfo[i][16] = miLista.get(i).getCuenta_empleado() + "";
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
                empleado.setId(Integer.parseInt(rs.getString("id")));
                empleado.setId_empleado(Integer.parseInt(rs.getString("id_empleado")));
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
            conex.desconectar();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al consultar la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return miLista;
    }

    public void filtro() {
        filtroCodigo = txtb.getText();
        trsfiltroCodigo.setRowFilter(RowFilter.regexFilter("(?i)" + filtroCodigo, 1, 2, 3, 6, 8));
    }
    
    private void cerrar_ventana() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
    
    
    //Ver empleado
    public void verEmpleadoSeleccionado() {
      
        int filaSeleccionada = table.getSelectedRow();
        if (filaSeleccionada != -1) {
           
            int idEmpleadoSeleccionado = Integer.parseInt(table.getValueAt(filaSeleccionada, 1).toString());

            empleado_ver empleadoVer = new empleado_ver();
            empleadoVer.setVisible(true);
            empleadoVer.setLocationRelativeTo(null);

            consultas_empleado consulta = new consultas_empleado();
            empleado clase = consulta.obtenerEmpleadoPorId(idEmpleadoSeleccionado);

            if (clase != null) {
            	empleadoVer.txtidtabla.setText(String.valueOf(clase.getId()));
                empleadoVer.txtid_ver.setText(String.valueOf(clase.getId_empleado()));
                empleadoVer.txtidentidad_ver.setText(clase.getIdentidad_empleado());
                empleadoVer.txtnombres_ver.setText(clase.getNombres_empleado());
                empleadoVer.txtapellidos_ver.setText(clase.getApellidos_empleado());
                empleadoVer.txtnacimiento_ver.setText(new SimpleDateFormat("dd-MM-yy").format(clase.getNacimiento_empleado()));
                empleadoVer.txttelefono_ver.setText(clase.getTel_empleado());
                empleadoVer.txtsexo_ver.setText(clase.getSexo_empleado());
                empleadoVer.txtcivil_ver.setText(clase.getCivil_empleado());
                empleadoVer.txadireccion_ver.setText(clase.getDireccion_empleado());
                empleadoVer.txtcorreo_ver.setText(clase.getCorreo_empleado());
                empleadoVer.txtcuenta_ver.setText(clase.getCuenta_empleado());
                empleadoVer.txtarea_ver.setText(clase.getArea_empleado());
                empleadoVer.txtcargo_ver.setText(clase.getCargo_empleado());
                empleadoVer.txtinicio_ver.setText(new SimpleDateFormat("dd-MM-yy").format(clase.getInicio_empleado()));

                // Fecha de renuncia puede ser nula
                if (clase.getRenuncia_empleado() != null) {
                    empleadoVer.txtrenuncia_ver.setText(new SimpleDateFormat("dd-MM-yy").format(clase.getRenuncia_empleado()));
                } else {
                    empleadoVer.txtrenuncia_ver.setText("N/A");
                }

                // Cargar la fotografía si está disponible
                String rutaFoto = clase.getFotografia_empleado();
                if (rutaFoto != null && !rutaFoto.isEmpty()) {
                    ImageIcon imagen = new ImageIcon(rutaFoto);
                    empleadoVer.txtruta_ver.setText(rutaFoto);
                    empleadoVer.lblfoto_ver.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(empleadoVer.lblfoto_ver.getWidth(),
                            empleadoVer.lblfoto_ver.getHeight(), Image.SCALE_SMOOTH)));
                }

            } else {
                JOptionPane.showMessageDialog(null, "No se pudo encontrar el empleado con el ID seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un empleado para ver sus datos", "Error", JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
    

}//end
