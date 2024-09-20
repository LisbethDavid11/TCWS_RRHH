package ventanas;

import java.awt.Color;
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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import clases.permiso_ausencia_laboral;
import conexion.conexion;
import consultas.consultas_permiso_ausencia_laboral;
import principal.menu_principal;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class permiso_ausencia_laboral_tabla extends JFrame {
	
	public TableRowSorter<TableModel> trsfiltroCodigo;
    String filtroCodigo;
    permiso_ausencia_laboral clase_permisos = new permiso_ausencia_laboral();
    public DefaultTableModel DefaultTableModel;
    public DefaultTableModel tableModel;
    public JTable tablePermisos_ausencia_laboral;
    public conexion dbConnection;
    public JTable table;
    public JScrollPane scrollPane;
	
	public JTextField txtbuscar;
	public JComboBox<String> cbxbusquedaCargo;
	public JComboBox<String> cbxbusquedaarea;
	public JDateChooser hasta_buscar;
	public JButton btnactualizar;
	public JButton btnregresar;
	public JButton btnnuevo;
	public JButton btneliminar;
	public JDateChooser desde_buscar;
	
	private final String placeHolderText = "Id empleado, nombres, apellidos e identidad"; // Placeholder definido

	
	public permiso_ausencia_laboral_tabla() {
		getContentPane().setBackground(Color.WHITE);
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
		panel_tabla.setBounds(22, 130, 990, 440);
		getContentPane().add(panel_tabla);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 970, 420);
		panel_tabla.add(scrollPane);
		
		JPanel panelbusqueda = new JPanel();
		panelbusqueda.setLayout(null);
		panelbusqueda.setBackground(SystemColor.menu);
		panelbusqueda.setBounds(22, 77, 990, 46);
		getContentPane().add(panelbusqueda);
		
		txtbuscar = new JTextField();
		txtbuscar.setText("Buscar por nombres, apellidos, identidad, id del empleado");
		txtbuscar.setForeground(Color.GRAY);
		txtbuscar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtbuscar.setColumns(10);
		txtbuscar.setBounds(68, 10, 212, 27);
		panelbusqueda.add(txtbuscar);
		
		InputMap map = txtbuscar.getInputMap(JComponent.WHEN_FOCUSED); 
        map.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK), "null");
        txtbuscar.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent ke) {
                if (txtbuscar.getText().length() == 50)
                    ke.consume();

                if (txtbuscar.getText().equals(" ")) {
                    JOptionPane.showMessageDialog(null, "No está permitido ingresar espacios vacíos");
                    txtbuscar.setText("");
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
		
		cbxbusquedaCargo = new JComboBox<String>();
		cbxbusquedaCargo.setModel(new DefaultComboBoxModel(new String[] {"Director general", "Director", "Gerente financiero", "Administrador", "Asistente", "Cobros", "Enfermero", "Psicologo", "Supervisor", "Consejero", "Docente", "Docente auxiliar", "Soporte técnico", "Marketing", "Aseo", "Mantenimiento", "Conserje", " "}));
		cbxbusquedaCargo.setSelectedIndex(-1);
		cbxbusquedaCargo.setFont(new Font("Tahoma", Font.BOLD, 11));
		cbxbusquedaCargo.setBounds(347, 12, 111, 26);
		panelbusqueda.add(cbxbusquedaCargo);
		
		JLabel lblCargo = new JLabel("Cargo");
		lblCargo.setHorizontalAlignment(SwingConstants.LEFT);
		lblCargo.setForeground(Color.BLACK);
		lblCargo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblCargo.setBounds(290, 10, 66, 26);
		panelbusqueda.add(lblCargo);
		
		cbxbusquedaarea = new JComboBox<String>();
		cbxbusquedaarea.setModel(new DefaultComboBoxModel<>(new String[] { "Administrativa", "Financiera", "Pre basica", "Primaria", "Secundaria", "Logistica", "Aseo", "Mantenimiento", " " }));
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
		
		JLabel lblhasta = new JLabel("Hasta");
		lblhasta.setHorizontalAlignment(SwingConstants.LEFT);
		lblhasta.setForeground(Color.BLACK);
		lblhasta.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblhasta.setBounds(829, 10, 56, 26);
		panelbusqueda.add(lblhasta);
		
		hasta_buscar = new JDateChooser();
		hasta_buscar.setBackground(new Color(255, 255, 255));
		hasta_buscar.setForeground(new Color(0, 0, 0));
		hasta_buscar.setDateFormatString("dd-MM-yy");
		hasta_buscar.setBounds(879, 10, 101, 27);
		panelbusqueda.add(hasta_buscar);
		
		JPanel panelbotones = new JPanel();
		panelbotones.setLayout(null);
		panelbotones.setBackground(SystemColor.menu);
		panelbotones.setBounds(549, 21, 463, 56);
		getContentPane().add(panelbotones);
		
		btnactualizar = new JButton("Actualizar");
		btnactualizar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnactualizar = new JButton("Actualizar");
		btnactualizar.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnactualizar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
		        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		        int filaseleccionada;
		        try {
		            filaseleccionada = table.getSelectedRow();
		            if (filaseleccionada == -1) {
		                JOptionPane.showMessageDialog(null, "¡No se ha seleccionado ninguna fila!", "Advertencia", JOptionPane.WARNING_MESSAGE);
		            } else {
		                String idPermiso = table.getValueAt(filaseleccionada, 0).toString();
		                String nombres = table.getValueAt(filaseleccionada, 1).toString();
		                String apellidos = table.getValueAt(filaseleccionada, 2).toString();
		                String identidad = table.getValueAt(filaseleccionada, 3).toString();
		                String idEmpleado = table.getValueAt(filaseleccionada, 4).toString();
		                String telefono = table.getValueAt(filaseleccionada, 5).toString();
		                String correo = table.getValueAt(filaseleccionada, 6).toString();
		                String cargo = table.getValueAt(filaseleccionada, 7).toString();
		                String area = table.getValueAt(filaseleccionada, 8).toString();

		                // Horas
		                String desdeHoraStr = table.getValueAt(filaseleccionada, 9).toString();
		                Date horaDesde = timeFormat.parse(desdeHoraStr);
		                String hastaHoraStr = table.getValueAt(filaseleccionada, 10).toString();
		                Date horaHasta = timeFormat.parse(hastaHoraStr);
		                String totalHorasStr = table.getValueAt(filaseleccionada, 11).toString();
		                Date totalHoras = timeFormat.parse(totalHorasStr);

		                String motivo = table.getValueAt(filaseleccionada, 12).toString();

		                // Fechas
		                String desdeFecha = table.getValueAt(filaseleccionada, 13).toString();
		                Date fechaDesde = dateFormat.parse(desdeFecha);
		                String hastaFecha = table.getValueAt(filaseleccionada, 14).toString();
		                Date fechaHasta = dateFormat.parse(hastaFecha);

		                String totalDias = table.getValueAt(filaseleccionada, 15).toString();
		                String nombresRecibe = table.getValueAt(filaseleccionada, 16).toString();
		                String fechaRecibe = table.getValueAt(filaseleccionada, 17).toString();
		                Date fechaRecibida = dateFormat.parse(fechaRecibe);

		                permiso_ausencia_laboral_nuevo actualizar_permiso = new permiso_ausencia_laboral_nuevo();
		                actualizar_permiso.setLocationRelativeTo(null);
		                actualizar_permiso.setVisible(true);
		                actualizar_permiso.btnguardar.setVisible(false); // Desactivar botón "Guardar"
		                dispose(); // Cerrar la ventana actual

		                actualizar_permiso.txtnumero_permiso.setText(idPermiso);
		                actualizar_permiso.cbxnombres.setSelectedItem(nombres);
		                actualizar_permiso.txtapellidos.setText(apellidos);
		                actualizar_permiso.txtidentidad.setText(identidad);
		                actualizar_permiso.txtid.setText(idEmpleado);
		                actualizar_permiso.txttel.setText(telefono);
		                actualizar_permiso.txtcorreo.setText(correo);
		                actualizar_permiso.txtcargo.setText(cargo);
		                actualizar_permiso.txtarea.setText(area);

		                actualizar_permiso.date_desde.setDate(fechaDesde);
		                actualizar_permiso.date_hasta.setDate(fechaHasta);
		                actualizar_permiso.spinnerHoraInicio.setValue(horaDesde);
		                actualizar_permiso.spinnerHoraFin.setValue(horaHasta);
		                actualizar_permiso.txttotal_horas.setText(totalHorasStr);

		                actualizar_permiso.txamotivo.setText(motivo);
		                actualizar_permiso.txtnombres_recibe.setText(nombresRecibe);
		                actualizar_permiso.txtFecha.setText(dateFormat.format(fechaRecibida));
		            }
		        } catch (ParseException ex) {
		            JOptionPane.showMessageDialog(null, "Error al parsear la fecha o la hora: " + ex.getMessage(),
		                    "Error", JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(),
		                    "Error en la operación", JOptionPane.ERROR_MESSAGE);
		            ex.printStackTrace();
		        }
		    }
		});	


		
		btnactualizar.setToolTipText("Actualizar registro");
		btnactualizar.setBounds(295, 17, 75, 23);
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
		btnregresar.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnregresar.setToolTipText("Menú principal");
		btnregresar.setBounds(10, 17, 75, 23);
		panelbotones.add(btnregresar);
		
		btnnuevo = new JButton("Nuevo");
		btnnuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				permiso_ausencia_laboral_nuevo nuevo = new permiso_ausencia_laboral_nuevo();
				nuevo.setVisible(true);
				nuevo.setLocationRelativeTo(null);
				nuevo.btnactualizar.setVisible(false);
				dispose();
			}
		});
		btnnuevo.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnnuevo.setToolTipText("Nuevo empleado");
		btnnuevo.setBounds(370, 17, 75, 23);
		panelbotones.add(btnnuevo);
		
		btneliminar = new JButton("Eliminar");
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

	        	                consultas_permiso_ausencia_laboral consulta = new consultas_permiso_ausencia_laboral();
	        	                
	        	                if (consulta.eliminar_permiso(Integer.parseInt(id))) {
	        	                   
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
		btneliminar.setFont(new Font("Tahoma", Font.BOLD, 8));
		btneliminar.setToolTipText("Eliminar registro");
		btneliminar.setBounds(144, 17, 75, 23);
		panelbotones.add(btneliminar);
		
		JButton btn_ver = new JButton("Ver");
		btn_ver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verPermisoSeleccionado();
				
			}
		});
		btn_ver.setToolTipText("Eliminar registro");
		btn_ver.setFont(new Font("Tahoma", Font.BOLD, 8));
		btn_ver.setBackground(UIManager.getColor("Button.highlight"));
		btn_ver.setBounds(220, 17, 75, 23);
		panelbotones.add(btn_ver);
		
		JLabel lblPermisosAusenciaLaboral = new JLabel("PERMISOS AUSENCIA LABORAL ");
		lblPermisosAusenciaLaboral.setHorizontalAlignment(SwingConstants.LEFT);
		lblPermisosAusenciaLaboral.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblPermisosAusenciaLaboral.setBounds(25, 30, 514, 26);
		getContentPane().add(lblPermisosAusenciaLaboral);
		
		Calendar cal = Calendar.getInstance();
		cal.set(2015, Calendar.JANUARY, 1); // Inicio en enero 2015
		hasta_buscar.setMinSelectableDate(cal.getTime());

		cal.setTime(new Date()); // Fecha actual
		cal.add(Calendar.YEAR, 1); // Añadir un año al actual
		hasta_buscar.setMaxSelectableDate(cal.getTime());
		
		
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
	
	        
	
	        // Configuración del placeholder
	        txtbuscar.setText(placeHolderText);
	        txtbuscar.setForeground(Color.GRAY);
	        
	        JLabel lblDesde = new JLabel("Desde");
	        lblDesde.setHorizontalAlignment(SwingConstants.LEFT);
	        lblDesde.setForeground(Color.BLACK);
	        lblDesde.setFont(new Font("Segoe UI", Font.BOLD, 16));
	        lblDesde.setBounds(655, 10, 56, 26);
	        panelbusqueda.add(lblDesde);
	        
	        desde_buscar = new JDateChooser();
	        desde_buscar.setForeground(Color.BLACK);
	        desde_buscar.setDateFormatString("dd-MM-yy");
	        desde_buscar.setBackground(Color.WHITE);
	        desde_buscar.setBounds(707, 10, 101, 27);
	        panelbusqueda.add(desde_buscar);
	
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
	                	txtbuscar.setForeground(Color.GRAY);
	                	txtbuscar.setText(placeHolderText);
	                }
	            }
	        });
	        
	        
	//////////////////////////////// filtros de jdatechooser
	     // Agregar el listener para el JDateChooser "desde_buscar"
	        desde_buscar.getDateEditor().addPropertyChangeListener("date", evt -> aplicarFiltros());

	        // Agregar el listener para el JDateChooser "hasta_buscar"
	        hasta_buscar.getDateEditor().addPropertyChangeListener("date", evt -> aplicarFiltros());

	       
	        
		
		
	}//class
	
	
		private void cerrar_ventana() {
			if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				System.exit(0);
		}
	
	
	
		public void construirTabla() {
		    String titulos[] = { 
		        "No", "Id", "Nombres", "Apellidos", "Identidad", "Teléfono", "Correo", "Cargo", "Área", 
		        "HI", "HF", "Total horas", "Motivo", "Inicio", "Fin", "Días", 
		        "Encargado", "Fecha recibido" 
		    };
		    String informacion[][] = obtenerMatriz();
		    DefaultTableModel modeloTabla = new DefaultTableModel(informacion, titulos);
		    table = new JTable(modeloTabla);
		    scrollPane.setViewportView(table);

		    trsfiltroCodigo = new TableRowSorter<>(table.getModel()); // Inicializamos el sorter
		    table.setRowSorter(trsfiltroCodigo); // Asignamos el sorter a la tabla
		    table.getColumnModel().getColumn(0).setPreferredWidth(30);
		    table.getColumnModel().getColumn(1).setPreferredWidth(30);
		    table.getColumnModel().getColumn(15).setPreferredWidth(30);
		}

		public static String[][] obtenerMatriz() {
		    ArrayList<permiso_ausencia_laboral> miLista = buscarUsuariosConMatriz();
		    String matrizInfo[][] = new String[miLista.size()][18]; // 17 columnas
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy"); // Formato de fecha
		    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm"); // Formato para las horas HH:mm (con ceros a la izquierda)

		    for (int i = 0; i < miLista.size(); i++) {
		        permiso_ausencia_laboral permiso = miLista.get(i); // Objeto permiso_ausencia_laboral

		        matrizInfo[i][0] = String.valueOf(permiso.getId_permisos());
		        matrizInfo[i][1] = String.valueOf(permiso.getId_empleado());
		        matrizInfo[i][2] = permiso.getNombres_empleado();
		        matrizInfo[i][3] = permiso.getApellidos_empleado();
		        matrizInfo[i][4] = permiso.getIdentidad_empleado();
		        matrizInfo[i][5] = permiso.getTel_empleado();
		        matrizInfo[i][6] = permiso.getCorreo_empleado();
		        matrizInfo[i][7] = permiso.getCargo_empleado();
		        matrizInfo[i][8] = permiso.getArea_empleado();

		        Calendar calendar = Calendar.getInstance();
		        calendar.setTime(permiso.getDesde_hora());
		        LocalTime desdeHora = LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
		        calendar.setTime(permiso.getHasta_hora());
		        LocalTime hastaHora = LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
		        calendar.setTime(permiso.getTotal_horas());
		        LocalTime totalHoras = LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));

		        matrizInfo[i][9] = desdeHora.format(timeFormatter); // Hora de inicio formateada
		        matrizInfo[i][10] = hastaHora.format(timeFormatter); // Hora de fin formateada
		        matrizInfo[i][11] = totalHoras.format(timeFormatter); // Total de horas formateada

		        matrizInfo[i][12] = permiso.getMotivo_ausencia();
		        matrizInfo[i][13] = dateFormat.format(permiso.getDesde_fecha()); // Fecha desde (formateada)
		        matrizInfo[i][14] = dateFormat.format(permiso.getHasta_fecha()); // Fecha hasta (formateada)
		        matrizInfo[i][15] = String.valueOf(permiso.getTotal_fecha()); // Total de días
		        matrizInfo[i][16] = permiso.getNombres_recibe(); // Recibido por
		        matrizInfo[i][17] = dateFormat.format(permiso.getFecha_recibe()); // Fecha de elaboración (formateada)
		    }

		    return matrizInfo;
		}


		public static ArrayList<permiso_ausencia_laboral> buscarUsuariosConMatriz() {
		    conexion conex = new conexion();
		    ArrayList<permiso_ausencia_laboral> miLista = new ArrayList<>();
		    try {
		        Statement estatuto = conex.conectar().createStatement();
		        ResultSet rs = estatuto.executeQuery("SELECT * FROM permisos_ausencia_laboral");

		        while (rs.next()) {
		            permiso_ausencia_laboral permisos = new permiso_ausencia_laboral();
		            
		            permisos.setId_permisos(rs.getInt("id_permisos"));		            
		            permisos.setNombres_empleado(rs.getString("nombres_empleado"));
		            permisos.setApellidos_empleado(rs.getString("apellidos_empleado"));
		            permisos.setIdentidad_empleado(rs.getString("identidad_empleado"));
		            permisos.setId_empleado(rs.getInt("id_empleado"));
		            permisos.setTel_empleado(rs.getString("tel_empleado"));
		            permisos.setCorreo_empleado(rs.getString("correo_empleado"));
		            permisos.setCargo_empleado(rs.getString("cargo_empleado"));
		            permisos.setArea_empleado(rs.getString("area_empleado"));
		            permisos.setDesde_hora(rs.getTime("desde_hora"));
		            permisos.setHasta_hora(rs.getTime("hasta_hora"));
		            permisos.setTotal_horas(rs.getTime("total_horas"));
		            permisos.setMotivo_ausencia(rs.getString("motivo_ausencia"));
		            permisos.setDesde_fecha(rs.getDate("desde_fecha"));
		            permisos.setHasta_fecha(rs.getDate("hasta_fecha"));
		            permisos.setTotal_fecha(rs.getInt("total_fecha")); // Días totales
		            permisos.setNombres_recibe(rs.getString("nombres_recibe"));
		            permisos.setFecha_recibe(rs.getDate("fecha_recibe"));
		            
		            miLista.add(permisos);
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
	        filtroCodigo = txtbuscar.getText();
	        trsfiltroCodigo.setRowFilter(RowFilter.regexFilter("(?i)" + filtroCodigo, 2, 3, 4, 5));
	    }

    	
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
    	// Método que aplica los filtros combinados
		private void aplicarFiltros() {
		    // Obtener los valores seleccionados en los JComboBox
		    String filtroCargo = (String) cbxbusquedaCargo.getSelectedItem();
		    String filtroArea = (String) cbxbusquedaarea.getSelectedItem();
		    Date fechaDesde = desde_buscar.getDate();
		    Date fechaHasta = hasta_buscar.getDate();
		    
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
		    List<RowFilter<Object, Object>> filtros = new ArrayList<>();

		    // Filtro de Cargo si no está en blanco
		    if (filtroCargo != null && !filtroCargo.trim().isEmpty()) {
		        filtros.add(RowFilter.regexFilter("(?i)" + filtroCargo, 7)); // Columna 7 es "Cargo"
		    }

		    // Filtro de Área si no está en blanco
		    if (filtroArea != null && !filtroArea.trim().isEmpty()) {
		        filtros.add(RowFilter.regexFilter("(?i)" + filtroArea, 8)); // Columna 8 es "Área"
		    }

		    // Filtro de fechas si ambas están seleccionadas (y no están vacías)
		    if (fechaDesde != null && fechaHasta != null) {
		        filtros.add(new RowFilter<Object, Object>() {
		            @Override
		            public boolean include(Entry<? extends Object, ? extends Object> entry) {
		                try {
		                    String fechaRecibidoStr = entry.getStringValue(17); // Columna 17 es "Fecha recibido"
		                    Date fechaRecibido = dateFormat.parse(fechaRecibidoStr);
		                    
		                    // Verificar que la fecha está dentro del rango seleccionado
		                    return (fechaRecibido.equals(fechaDesde) || fechaRecibido.after(fechaDesde)) 
		                        && (fechaRecibido.equals(fechaHasta) || fechaRecibido.before(fechaHasta));
		                } catch (ParseException e) {
		                    return false; // En caso de error al parsear la fecha
		                }
		            }
		        });
		    }

		    // Si no hay filtros activos, mostrar todos los registros
		    if (filtros.isEmpty()) {
		        trsfiltroCodigo.setRowFilter(null);  // Quitar todos los filtros
		    } else {
		        // Combinar todos los filtros activos
		        RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filtros);
		        trsfiltroCodigo.setRowFilter(combinedFilter);
		    }
		}


        
        
        //////////////////////////////////////////////////////////////////////////////////
        
        private void fechaFiltros() {
            String filtroCargo = (String) cbxbusquedaCargo.getSelectedItem();
            String filtroArea = (String) cbxbusquedaarea.getSelectedItem();
            Date fechaSeleccionada = hasta_buscar.getDate();
            
            List<RowFilter<Object, Object>> filtros = new ArrayList<>();

            // Filtro de Cargo
            if (filtroCargo != null && !filtroCargo.trim().isEmpty()) {
                filtros.add(RowFilter.regexFilter("(?i)" + filtroCargo, 6)); // Columna 6 es "Cargo"
            }

            // Filtro de Área
            if (filtroArea != null && !filtroArea.trim().isEmpty()) {
                filtros.add(RowFilter.regexFilter("(?i)" + filtroArea, 7)); // Columna 7 es "Área"
            }

            // Filtro por fecha seleccionada
            if (fechaSeleccionada != null) {
                String fechaFiltro = new SimpleDateFormat("dd-MM-yy").format(fechaSeleccionada);
                filtros.add(RowFilter.regexFilter(fechaFiltro, 12, 13, 16)); // Columnas 12, 13, 16 son fechas
            }

            // Si no hay filtros activos, mostrar todos los registros
            if (filtros.isEmpty()) {
                trsfiltroCodigo.setRowFilter(null); // Mostrar todos los registros si no hay filtros
            } else {
                RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filtros);
                trsfiltroCodigo.setRowFilter(combinedFilter);
            }
        }

        
        public void verPermisoSeleccionado() {

            int filaSeleccionada = table.getSelectedRow();
            if (filaSeleccionada != -1) {

                int idPermisoSeleccionado = Integer.parseInt(table.getValueAt(filaSeleccionada, 0).toString());

                permiso_ausencia_laboral_ver permisoVer = new permiso_ausencia_laboral_ver();
                permisoVer.setVisible(true);
                permisoVer.setLocationRelativeTo(null);

                consultas_permiso_ausencia_laboral consulta = new consultas_permiso_ausencia_laboral();
                permiso_ausencia_laboral clase = consulta.obtenerPermisoPorId(idPermisoSeleccionado);

                if (clase != null) {
                    // Asignar los valores obtenidos al formulario de ver permiso
                    permisoVer.txtid_permisoVer.setText(String.valueOf(clase.getId_permisos()));
                    permisoVer.txtid_ver.setText(String.valueOf(clase.getId_empleado()));
                    permisoVer.txtidentidad_ver.setText(clase.getIdentidad_empleado());
                    permisoVer.txtnombres_ver.setText(clase.getNombres_empleado());
                    permisoVer.txtapellidos_ver.setText(clase.getApellidos_empleado());
                    permisoVer.txttelefono_ver.setText(clase.getTel_empleado());
                    permisoVer.txtcorreo_ver.setText(clase.getCorreo_empleado());
                    permisoVer.txtarea_ver.setText(clase.getArea_empleado());
                    permisoVer.txtcargo_ver.setText(clase.getCargo_empleado());
                    
                    // Formateo de las horas y fechas
                    permisoVer.txtdesdeHora_ver.setText(new SimpleDateFormat("HH:mm").format(clase.getDesde_hora()));
                    permisoVer.txthastaHora_ver.setText(new SimpleDateFormat("HH:mm").format(clase.getHasta_hora()));
                    permisoVer.txttotalHoras_ver.setText(clase.getTotal_horas().toString());

                    permisoVer.txtinicioFecha_ver.setText(new SimpleDateFormat("dd-MM-yy").format(clase.getDesde_fecha()));
                    permisoVer.txtfinalFecha_ver.setText(new SimpleDateFormat("dd-MM-yy").format(clase.getHasta_fecha()));
                    permisoVer.txttotalDias_ver.setText(String.valueOf(clase.getTotal_fecha()));

                    // Motivo de la ausencia
                    permisoVer.txamotivo_ver.setText(clase.getMotivo_ausencia());

                    // Nombres y fecha de recepción
                    permisoVer.txtnombresRecibe_ver.setText(clase.getNombres_recibe());
                    permisoVer.txtfechaRecibe_ver.setText(new SimpleDateFormat("dd-MM-yy").format(clase.getFecha_recibe()));

                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo encontrar el permiso con el ID seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un permiso para ver sus datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
}
