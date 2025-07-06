package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import conexion.conexion;
import principal.menu_principal;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



@SuppressWarnings("serial")
public class usuario_tabla extends JFrame {
	private TableRowSorter<DefaultTableModel> trsfiltroCodigo;
	private JTextField txtbuscar;
	public JTable tablaUsuarios;
	final String placeHolderText = "Usuario, nombres y apellidos";
	private JScrollPane scrollPane;
	public JLabel lblresultado_busqueda;
	
	public usuario_tabla() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setType(Type.UTILITY);
		setResizable(false);
		getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		setBounds(100, 100, 1050, 630);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		
		JLabel lblUsuarios = new JLabel("USUARIOS");
		lblUsuarios.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsuarios.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblUsuarios.setBounds(23, 28, 534, 26);
		getContentPane().add(lblUsuarios);
		
		

		
		JPanel panelbotones = new JPanel();
		panelbotones.setLayout(null);
		panelbotones.setBackground(SystemColor.menu);
		panelbotones.setBounds(562, 21, 451, 56);
		getContentPane().add(panelbotones);
		
		JButton btnactualizar = new JButton("Actualizar");
		btnactualizar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnactualizar.setBounds(0, 0, 0, 0);
		panelbotones.add(btnactualizar);
		
		JButton btnregresar = new JButton("Menú");
		btnregresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu_principal p = new menu_principal();
				p.setVisible(true);
				p.setLocationRelativeTo(null);
				dispose();
			
			}
		});
		btnregresar.setToolTipText("Regresar al menú principal");
		btnregresar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnregresar.setBackground(UIManager.getColor("Button.light"));
		btnregresar.setBounds(10, 17, 90, 23);
		panelbotones.add(btnregresar);
		
		JButton btnnuevo = new JButton("Nuevo");
		btnnuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuario_nuevo n = new usuario_nuevo();
				n.setVisible(true);
				n.setLocationRelativeTo(null);
				dispose();
				n.btnregresar_login.setVisible(false);
				n.btnactualizar.setVisible(false);
				n.chkeditar.setVisible(false);
			}
		});
		btnnuevo.setToolTipText("Nuevo registro");
		btnnuevo.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnnuevo.setBounds(351, 17, 90, 23);
		panelbotones.add(btnnuevo);
		
		JButton btneliminar = new JButton("Eliminar");
		btneliminar.setToolTipText("Eliminar registro");
		btneliminar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btneliminar.setBounds(251, 17, 90, 23);
		panelbotones.add(btneliminar);
		
		JPanel panelbusqueda = new JPanel();
		panelbusqueda.setLayout(null);
		panelbusqueda.setBackground(SystemColor.menu);
		panelbusqueda.setBounds(23, 75, 990, 46);
		getContentPane().add(panelbusqueda);
		
		txtbuscar = new JTextField();
		txtbuscar.setText("Usuario, nombres y apellidos");
		txtbuscar.setForeground(Color.GRAY);
		txtbuscar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtbuscar.setBounds(68, 10, 379, 27);
		panelbusqueda.add(txtbuscar);

		
		
		btneliminar.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int filaSeleccionada = tablaUsuarios.getSelectedRow();

		        if (filaSeleccionada == -1) {
		            JOptionPane.showMessageDialog(
		                usuario_tabla.this, 
		                "Debe seleccionar una fila para eliminar.", 
		                "Advertencia", 
		                JOptionPane.WARNING_MESSAGE
		            );
		            return;
		        }

		        int confirmacion = JOptionPane.showConfirmDialog(
		            usuario_tabla.this, 
		            "¿Está seguro de que desea eliminar el registro seleccionado?\nEsto también lo eliminará permanentemente de la base de datos", 
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE
		        );

		        if (confirmacion == JOptionPane.YES_OPTION) {
		            Connection con = null;
		            PreparedStatement pst = null;

		            try {
		                con = new conexion().conectar();
		                // Obtener el ID del usuario a eliminar
		                int idUsuario = Integer.parseInt(tablaUsuarios.getValueAt(filaSeleccionada, 0).toString());
		                String sql = "DELETE FROM usuarios WHERE id_usuarios = ?";
		                pst = con.prepareStatement(sql);
		                pst.setInt(1, idUsuario);

		                int resultado = pst.executeUpdate();

		                if (resultado > 0) {
		                    ((DefaultTableModel) tablaUsuarios.getModel()).removeRow(filaSeleccionada);
		                    JOptionPane.showMessageDialog(
		                        usuario_tabla.this, 
		                        "El registro ha sido eliminado correctamente de la tabla y la base de datos", 
	                    		"Éxito", JOptionPane.INFORMATION_MESSAGE 
		                    );
		                    actualizarConteoRegistros();
		                } else {
		                    JOptionPane.showMessageDialog(
		                        usuario_tabla.this, 
		                        "No se pudo eliminar el registro. Intente nuevamente.", 
		                        "Error", 
		                        JOptionPane.ERROR_MESSAGE
		                    );
		                }
		            } catch (SQLException ex) {
		                JOptionPane.showMessageDialog(
		                    usuario_tabla.this, 
		                    "Error al eliminar el registro: " + ex.getMessage(), 
		                    "Error", 
		                    JOptionPane.ERROR_MESSAGE
		                );
		            } finally {
		                try {
		                    if (pst != null) pst.close();
		                    if (con != null) con.close();
		                } catch (SQLException ex) {
		                    System.err.println("Error al cerrar la conexión: " + ex.getMessage());
		                }
		            }
		        }
		    }
		});

		
		
		JLabel lblbuscar = new JLabel("Buscar");
		lblbuscar.setHorizontalAlignment(SwingConstants.LEFT);
		lblbuscar.setForeground(Color.BLACK);
		lblbuscar.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblbuscar.setBounds(10, 10, 66, 26);
		panelbusqueda.add(lblbuscar);
		
		JPanel panel_tabla = new JPanel();
		panel_tabla.setLayout(null);
		panel_tabla.setBackground(SystemColor.menu);
		panel_tabla.setBounds(23, 130, 990, 440);
		getContentPane().add(panel_tabla);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 970, 370);
		panel_tabla.add(scrollPane);
		
		lblresultado_busqueda = new JLabel("Registros: 2");
		lblresultado_busqueda.setHorizontalAlignment(SwingConstants.RIGHT);
		lblresultado_busqueda.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblresultado_busqueda.setBounds(746, 390, 222, 27);
		panel_tabla.add(lblresultado_busqueda);
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				cerrar_ventana();
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
				
		
		scrollPane.setViewportView(tablaUsuarios);
		
		
				
	}//class
	
	
	
	
	private void cerrar_ventana() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	 private void actualizarConteoRegistros() {
	        int registrosVisibles = tablaUsuarios.getRowCount(); // Obtiene el número de filas visibles en la tabla
	        lblresultado_busqueda.setText("Registros: " + registrosVisibles);
	    }

	
	
	
	
	public void construirTabla() {
	    // Crear modelo de la tabla
	    DefaultTableModel modelo = new DefaultTableModel();
	    modelo.addColumn("No.");
	    modelo.addColumn("Usuario");
	    modelo.addColumn("Nombres");
	    modelo.addColumn("Apellidos");
	    modelo.addColumn("Correo");
	    modelo.addColumn("Contraseña");

	    
	    
	    // Inicializar la tabla con el modelo
	    tablaUsuarios = new JTable(modelo) {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false; // Celdas no editables
	        }
	    };

	    // Configurar sorter para el filtro de búsqueda
	    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
	    tablaUsuarios.setRowSorter(sorter);
	    trsfiltroCodigo = sorter;

	    // Configurar la tabla
	    tablaUsuarios.setRowHeight(25);
	    tablaUsuarios.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
	    tablaUsuarios.getTableHeader().setOpaque(false);
	    tablaUsuarios.getTableHeader().setBackground(new Color(32, 136, 203));
	    tablaUsuarios.getTableHeader().setForeground(Color.WHITE);
	    
	    tablaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(1); // Número

	    // Usar la referencia directa al JScrollPane
	    scrollPane.setViewportView(tablaUsuarios);

	    actualizarConteoRegistros();
	    
	    // Agregar el MouseListener a la tabla
	    tablaUsuarios.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            if (e.getClickCount() == 2) { // Doble clic
	                int filaSeleccionada = tablaUsuarios.getSelectedRow();
	                if (filaSeleccionada != -1) {
	                    // Obtener los datos de la fila seleccionada
	                    int idUsuario = Integer.parseInt(tablaUsuarios.getValueAt(filaSeleccionada, 0).toString());
	                    String usuario = tablaUsuarios.getValueAt(filaSeleccionada, 1).toString();
	                    String nombres = tablaUsuarios.getValueAt(filaSeleccionada, 2).toString();
	                    String apellidos = tablaUsuarios.getValueAt(filaSeleccionada, 3).toString();
	                    String correo = tablaUsuarios.getValueAt(filaSeleccionada, 4).toString();
	                    String contrasena = tablaUsuarios.getValueAt(filaSeleccionada, 5).toString();

	                    // Enviar los datos a usuario_nuevo
	                    usuario_nuevo formulario = new usuario_nuevo();
	                    formulario.cargarDatosUsuario(idUsuario, usuario, nombres, apellidos, correo, contrasena);
	                    formulario.setVisible(true);
	                    formulario.setLocationRelativeTo(null);
	                    formulario.btnguardar.setVisible(false);
	                    formulario.btnactualizar.setVisible(false);
	                    formulario.btnregresar_login.setVisible(false);

	                    dispose(); // Cerrar la ventana actual
	                }
	            }
	        }
	    });

	    // Cargar los datos desde la base de datos
	    Connection con = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;

	    try {
	        con = new conexion().conectar();
	        String sql = "SELECT id_usuarios, usuario, nombres, apellidos, correo, contrasena FROM usuarios";
	        pst = con.prepareStatement(sql);
	        rs = pst.executeQuery();

	        while (rs.next()) {
	            modelo.addRow(new Object[]{
	                rs.getInt("id_usuarios"),
	                rs.getString("usuario"),
	                rs.getString("nombres"),
	                rs.getString("apellidos"),
	                rs.getString("correo"),
	                rs.getString("contrasena")
	            });
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pst != null) pst.close();
	            if (con != null) con.close();
	        } catch (SQLException e) {
	            System.err.println("Error al cerrar la conexión: " + e.getMessage());
	        }
	    }
	}
	
	
	private void filtro() {
        String filtroTexto = txtbuscar.getText();
        if (trsfiltroCodigo != null) {
            trsfiltroCodigo.setRowFilter(RowFilter.regexFilter("(?i)" + filtroTexto, 1, 2));  
        }
        actualizarConteoRegistros();
        
    }



	
}
