package ventanas;

import java.awt.Color;
import java.awt.Component;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
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

public class permiso_AL_tabla extends JFrame {
	
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

	
	public permiso_AL_tabla() {
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
		
		table = new JTable();
		scrollPane.setViewportView(table); 
		
		JPanel panelbusqueda = new JPanel();
		panelbusqueda.setLayout(null);
		panelbusqueda.setBackground(SystemColor.menu);
		panelbusqueda.setBounds(22, 77, 990, 46);
		getContentPane().add(panelbusqueda);
		
		txtbuscar = new JTextField();
		txtbuscar.setText("Nombres, apellidos, identidad, id del empleado");
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
		cbxbusquedaCargo.setModel(new DefaultComboBoxModel<String>(new String[] {"Director general", "Director", "Gerente financiero", "Administrador", "Asistente", "Cobros", "Enfermero", "Psicologo", "Supervisor", "Consejero", "Docente", "Docente auxiliar", "Soporte técnico", "Marketing", "Aseo", "Mantenimiento", "Conserje", " "}));
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
				permiso_AL_nuevo nuevo = new permiso_AL_nuevo();
				nuevo.setVisible(true);
				nuevo.setLocationRelativeTo(null);
				nuevo.btnactualizar.setVisible(false);
				nuevo.chxeditar.setVisible(false);
				dispose();
			}
		});
		btnnuevo.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnnuevo.setToolTipText("Nuevo registro");
		btnnuevo.setBounds(363, 17, 90, 23);
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
		btneliminar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btneliminar.setToolTipText("Eliminar registro");
		btneliminar.setBounds(263, 17, 90, 23);
		panelbotones.add(btneliminar);
		
		JLabel lblPermisosAusenciaLaboral = new JLabel("PERMISOS POR AUSENCIA LABORAL ");
		lblPermisosAusenciaLaboral.setHorizontalAlignment(SwingConstants.LEFT);
		lblPermisosAusenciaLaboral.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblPermisosAusenciaLaboral.setBounds(25, 30, 514, 26);
		getContentPane().add(lblPermisosAusenciaLaboral);
		
		Calendar cal = Calendar.getInstance();
		cal.set(2015, Calendar.JANUARY, 1); // Inicio en enero 2015
		hasta_buscar.setMinSelectableDate(cal.getTime());

		cal.setTime(new Date()); 
		cal.add(Calendar.YEAR, 1); 
		hasta_buscar.setMaxSelectableDate(cal.getTime());
		
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
	        
	        construirTabla();
	        
	        desde_buscar.getDateEditor().addPropertyChangeListener("date", evt -> aplicarFiltros());
	        hasta_buscar.getDateEditor().addPropertyChangeListener("date", evt -> aplicarFiltros());
	        trsfiltroCodigo = new TableRowSorter<>(table.getModel());
	        table.setRowSorter(trsfiltroCodigo);  
	        
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
	                filtro();
	            }
	        });
	   
		
	}//class
	
	
		private void cerrar_ventana() {
			if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				System.exit(0);
		}
	
	
	
		public void construirTabla() {
			
		    String[] titulos = { 
		        "No", "Id", "Nombres", "Apellidos", "Identidad", "Teléfono", "Correo", "Cargo", "Área", 
		        "HI", "HF", "Total horas", "Motivo", "Inicio", "Fin", "Días", 
		        "Encargado", "Recibido", "Extiende" 
		    };

		    String[][] informacion = obtenerMatriz(); 

	        DefaultTableModel modeloTabla = new DefaultTableModel(informacion, titulos) {
	            @Override
	            public boolean isCellEditable(int row, int column) {
	                return false; // Todas las celdas no serán editables
	            }
	        };

	        table.setModel(modeloTabla);
	        scrollPane.setViewportView(table);

	        trsfiltroCodigo = new TableRowSorter<>(modeloTabla);
	        table.setRowSorter(trsfiltroCodigo);
		    table.getColumnModel().getColumn(0).setPreferredWidth(30); 
		    table.getColumnModel().getColumn(1).setPreferredWidth(30);  
		    table.getColumnModel().getColumn(15).setPreferredWidth(30); 

		    scrollPane.setViewportView(table);

		    table.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		            if (e.getClickCount() == 2) { 
		                int filaSeleccionada = table.getSelectedRow();
		                if (filaSeleccionada != -1) {
		                    int fila = table.convertRowIndexToModel(filaSeleccionada); 
		                    
		                    String idPermiso = String.valueOf(table.getModel().getValueAt(fila, 0)); 
		                    String idEmpleado = String.valueOf(table.getModel().getValueAt(fila, 1)); 
		                    String nombres = String.valueOf(table.getModel().getValueAt(fila, 2));
		                    String apellidos = String.valueOf(table.getModel().getValueAt(fila, 3));
		                    String identidad = String.valueOf(table.getModel().getValueAt(fila, 4));
		                    String telefono = String.valueOf(table.getModel().getValueAt(fila, 5));
		                    String correo = String.valueOf(table.getModel().getValueAt(fila, 6));
		                    String cargo = String.valueOf(table.getModel().getValueAt(fila, 7));
		                    String area = String.valueOf(table.getModel().getValueAt(fila, 8));
		                    String motivo = String.valueOf(table.getModel().getValueAt(fila, 12));
		                    String desdeFecha = String.valueOf(table.getModel().getValueAt(fila, 13));
		                    String hastaFecha = String.valueOf(table.getModel().getValueAt(fila, 14));
		                    String totalFecha= String.valueOf(table.getModel().getValueAt(fila, 15));
		                    String nombresRecibe = String.valueOf(table.getModel().getValueAt(fila, 16));
		                    String fechaRecibe = String.valueOf(table.getModel().getValueAt(fila, 17));
		                    String extiende = String.valueOf(table.getModel().getValueAt(fila, 18));
		                    

		                    String desdeHora = String.valueOf(table.getModel().getValueAt(fila, 9)); 
		                    String hastaHora = String.valueOf(table.getModel().getValueAt(fila, 10)); 
		                    

		                    permiso_AL_nuevo ventanaPermiso = new permiso_AL_nuevo();

		                    ventanaPermiso.txtnumero_permiso.setText(idPermiso);  
		                    ventanaPermiso.cbxnombres.setSelectedItem(nombres);
		                    ventanaPermiso.txtapellidos.setText(apellidos);
		                    ventanaPermiso.txtidentidad.setText(identidad);
		                    ventanaPermiso.txttel.setText(telefono);
		                    ventanaPermiso.txtcorreo.setText(correo);
		                    ventanaPermiso.txtcargo.setText(cargo);
		                    ventanaPermiso.txtarea.setText(area);
		                    ventanaPermiso.txamotivo.setText(motivo);
		                    ventanaPermiso.txttotal_dias.setText(totalFecha);
		                    ventanaPermiso.txtnombres_recibe.setText(nombresRecibe);
		                    ventanaPermiso.txtextiende.setText(extiende);

		                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		                    try {
		                        Date horaInicio = timeFormat.parse(desdeHora);
		                        Date horaFin = timeFormat.parse(hastaHora);
		                        
		                        ventanaPermiso.spinnerHoraInicio.setValue(horaInicio);
		                        ventanaPermiso.spinnerHoraFin.setValue(horaFin);
		                    } catch (ParseException ex) {
		                        ex.printStackTrace();
		                    }

		                    ventanaPermiso.txttotal_horas.setText(String.valueOf(table.getModel().getValueAt(fila, 11)));

		                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
		                    try {
		                        ventanaPermiso.date_desde.setDate(dateFormat.parse(desdeFecha));
		                        ventanaPermiso.date_hasta.setDate(dateFormat.parse(hastaFecha));
		                    } catch (ParseException ex) {
		                        ex.printStackTrace();
		                    }

		                    desactivarComponentes(ventanaPermiso);
		                    cambiarColorFuenteNegro(ventanaPermiso.panel_datos);

		                    ventanaPermiso.setVisible(true);
		                    ventanaPermiso.setLocationRelativeTo(null);
		                    
		                    ventanaPermiso.btnactualizar.setVisible(false);
		                    ventanaPermiso.btnguardar.setVisible(false);
		                    ventanaPermiso.btnlimpiar.setVisible(false);

		                    dispose();
		                }
		            }
		        }
		        
		    });
		}

		public void filtro() {
	        filtroCodigo = txtbuscar.getText();
	        if (trsfiltroCodigo != null) {
	            trsfiltroCodigo.setRowFilter(RowFilter.regexFilter("(?i)" + filtroCodigo, 2, 3, 4, 5));
	        }
	    }
		

		private void desactivarComponentes(permiso_AL_nuevo ventana) {
		    ventana.cbxnombres.setEnabled(false);
		    ventana.txtapellidos.setEditable(false);
		    ventana.txtcorreo.setEditable(false);
		    ventana.txtcargo.setEditable(false);
		    ventana.txtarea.setEditable(false);
		    ventana.txttel.setEditable(false);
		    ventana.txtidentidad.setEditable(false);
		    ventana.txtid.setEditable(false);
		    ventana.date_desde.setEnabled(false);
		    ventana.date_hasta.setEnabled(false);
		    ventana.spinnerHoraInicio.setEnabled(false);
		    ventana.spinnerHoraFin.setEnabled(false);
		    ventana.txttotal_dias.setEditable(false);
		    ventana.txttotal_horas.setEditable(false);
		    ventana.txamotivo.setEditable(false);
		    ventana.txtnombres_recibe.setEditable(false);
		    ventana.txtextiende.setEditable(false);
		}
		
		private void cambiarColorFuenteNegro(JPanel panel) {
		    for (Component componente : panel.getComponents()) {
		        if (componente instanceof JTextField || componente instanceof JLabel || componente instanceof JComboBox || 
		        		componente instanceof JTextArea || componente instanceof JSpinner) {
		            componente.setForeground(Color.BLACK); 
		        }
		        if (componente instanceof JPanel) {
		            cambiarColorFuenteNegro((JPanel) componente); 
		        }
		    }
		}



		public static String[][] obtenerMatriz() {
		    ArrayList<permiso_ausencia_laboral> miLista = buscarUsuariosConMatriz();
		    String matrizInfo[][] = new String[miLista.size()][19]; 
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
		    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm"); 
		    
		    for (int i = 0; i < miLista.size(); i++) {
		        permiso_ausencia_laboral permiso = miLista.get(i); 

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

		        matrizInfo[i][9] = desdeHora.format(timeFormatter); 
		        matrizInfo[i][10] = hastaHora.format(timeFormatter); 
		        matrizInfo[i][11] = totalHoras.format(timeFormatter); 

		        matrizInfo[i][12] = permiso.getMotivo_ausencia();
		        matrizInfo[i][13] = dateFormat.format(permiso.getDesde_fecha()); 
		        matrizInfo[i][14] = dateFormat.format(permiso.getHasta_fecha()); 
		        matrizInfo[i][15] = String.valueOf(permiso.getTotal_fecha()); 
		        matrizInfo[i][16] = permiso.getNombres_recibe(); 
		        matrizInfo[i][17] = dateFormat.format(permiso.getFecha_recibe()); 
		        matrizInfo[i][18] = permiso.getNombres_extiende(); 
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
		            permisos.setTotal_fecha(rs.getInt("total_fecha")); 
		            permisos.setNombres_recibe(rs.getString("nombres_recibe"));
		            permisos.setFecha_recibe(rs.getDate("fecha_recibe"));
		            permisos.setNombres_extiende(rs.getString("nombres_extiende"));
		            
		            miLista.add(permisos);
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
		
		
		private void aplicarFiltros() {
			String filtroCargo = (String) cbxbusquedaCargo.getSelectedItem();
	        String filtroArea = (String) cbxbusquedaarea.getSelectedItem();
	        Date fechaDesde = desde_buscar.getDate();
	        Date fechaHasta = hasta_buscar.getDate();
	        
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
	        List<RowFilter<Object, Object>> filtros = new ArrayList<>();

	        if (filtroCargo != null && !filtroCargo.trim().isEmpty()) {
	            filtros.add(RowFilter.regexFilter("(?i)" + filtroCargo, 7)); 
	        }

	        if (filtroArea != null && !filtroArea.trim().isEmpty()) {
	            filtros.add(RowFilter.regexFilter("(?i)" + filtroArea, 8)); 
	        }

	        if (fechaDesde != null && fechaHasta != null) {
	            filtros.add(new RowFilter<Object, Object>() {
	                @Override
	                public boolean include(Entry<? extends Object, ? extends Object> entry) {
	                    try {
	                        String fechaRecibidoStr = entry.getStringValue(17); // Columna 17 es "Fecha recibido"
	                        Date fechaRecibido = dateFormat.parse(fechaRecibidoStr);
	                        
	                        return (fechaRecibido.equals(fechaDesde) || fechaRecibido.after(fechaDesde)) 
	                            && (fechaRecibido.equals(fechaHasta) || fechaRecibido.before(fechaHasta));
	                    } catch (ParseException e) {
	                        return false; 
	                    }
	                }
	            });
	        }
	        
	        if (filtros.isEmpty()) {
	            trsfiltroCodigo.setRowFilter(null);  
	        } else {
	            RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filtros);
	            trsfiltroCodigo.setRowFilter(combinedFilter);
	        }
	    }
	    
		

 
        
        private void fechaFiltros() {
            String filtroCargo = (String) cbxbusquedaCargo.getSelectedItem();
            String filtroArea = (String) cbxbusquedaarea.getSelectedItem();
            Date fechaSeleccionada = hasta_buscar.getDate();
            
            List<RowFilter<Object, Object>> filtros = new ArrayList<>();

            if (filtroCargo != null && !filtroCargo.trim().isEmpty()) {
                filtros.add(RowFilter.regexFilter("(?i)" + filtroCargo, 6)); 
            }
            
            if (filtroArea != null && !filtroArea.trim().isEmpty()) {
                filtros.add(RowFilter.regexFilter("(?i)" + filtroArea, 7)); 
            }

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

        
        public void cargarNombresEmpleadosEnPermisoNuevo() {
            permiso_AL_nuevo ventanaPermiso = new permiso_AL_nuevo();
            
            try {
                Connection conn = new conexion().conectar();
                String sql = "SELECT nombres_empleado FROM empleados";
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                ventanaPermiso.cbxnombres.removeAllItems(); 
                ventanaPermiso.cbxnombres.addItem(""); // Añadir un ítem vacío al inicio

                while (rs.next()) {
                    ventanaPermiso.cbxnombres.addItem(rs.getString("nombres_empleado"));
                }

                rs.close();
                pst.close();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al cargar los nombres de los empleados.");
            }

            ventanaPermiso.setLocationRelativeTo(null);
            ventanaPermiso.setVisible(true);
        }


}
