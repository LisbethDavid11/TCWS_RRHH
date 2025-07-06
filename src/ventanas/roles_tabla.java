package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window.Type;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import consultas.consultas_roles;
import principal.menu_principal;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import ventanas.roles_nuevo;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class roles_tabla extends JFrame {
	private JTextField textField;
	public JPanel panel_1;
	
	 private JTable tablaRoles;
	    private DefaultTableModel modeloTabla;
	    private JScrollPane scrollPane;
	    
	    
	
	public roles_tabla() {
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
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				cerrar_ventana();
			}
			});
		
		
		JPanel panelbusqueda = new JPanel();
		panelbusqueda.setLayout(null);
		panelbusqueda.setBackground(SystemColor.menu);
		panelbusqueda.setBounds(22, 78, 985, 46);
		getContentPane().add(panelbusqueda);
		
		textField = new JTextField();
		textField.setText("Nombres, apellidos, identidad, estado civil y teléfono");
		textField.setForeground(Color.GRAY);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textField.setColumns(10);
		textField.setBounds(68, 10, 337, 27);
		panelbusqueda.add(textField);
		
		JLabel lblbuscar = new JLabel("Buscar");
		lblbuscar.setHorizontalAlignment(SwingConstants.LEFT);
		lblbuscar.setForeground(Color.BLACK);
		lblbuscar.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblbuscar.setBounds(10, 10, 66, 26);
		panelbusqueda.add(lblbuscar);
		
		JLabel lblRolesDePermisos = new JLabel("ROLES DE PERMISOS PARA USUARIOS");
		lblRolesDePermisos.setHorizontalAlignment(SwingConstants.LEFT);
		lblRolesDePermisos.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblRolesDePermisos.setBounds(25, 31, 496, 26);
		getContentPane().add(lblRolesDePermisos);
		
		JPanel panelbotones = new JPanel();
		panelbotones.setLayout(null);
		panelbotones.setBackground(SystemColor.menu);
		panelbotones.setBounds(549, 22, 458, 56);
		getContentPane().add(panelbotones);
		
		JButton btnMenu = new JButton("Menú");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 menu_principal menu = new menu_principal();
	             menu.setVisible(true);
	             menu.setLocationRelativeTo(null);
	             dispose();
			}
		});
		btnMenu.setToolTipText("Regresar al menú principal");
		btnMenu.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnMenu.setBackground(UIManager.getColor("Button.highlight"));
		btnMenu.setBounds(10, 17, 90, 23);
		panelbotones.add(btnMenu);
		
		JButton btnNuevoEmpleado = new JButton("Nuevo");
		btnNuevoEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roles_nuevo r = new roles_nuevo();
				r.setVisible(true);
				r.setLocationRelativeTo(null);
				r.btnactualizar.setVisible(false);
				r.chxeditar.setVisible(false);
				dispose();
			}
		});
		btnNuevoEmpleado.setToolTipText("Nuevo registro");
		btnNuevoEmpleado.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNuevoEmpleado.setBackground(UIManager.getColor("Button.highlight"));
		btnNuevoEmpleado.setBounds(358, 17, 90, 23);
		panelbotones.add(btnNuevoEmpleado);
		
		JButton btneliminar = new JButton("Eliminar");
		btneliminar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int filaSeleccionada = tablaRoles.getSelectedRow();
		        
		        if (filaSeleccionada == -1) {
		            JOptionPane.showMessageDialog(null, "Por favor, seleccione un registro para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        // Confirmar eliminación
		        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar este registro?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
		        if (confirmacion == JOptionPane.YES_OPTION) {
		            String usuario = (String) modeloTabla.getValueAt(filaSeleccionada, 1); // Columna del Usuario (ajustar índice si es necesario)

		            consultas_roles consultas = new consultas_roles();
		            boolean eliminado = consultas.eliminarRol(usuario);

		            if (eliminado) {
		                modeloTabla.removeRow(filaSeleccionada); // Eliminar de la tabla
		                JOptionPane.showMessageDialog(null, "Registro eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		            } else {
		                JOptionPane.showMessageDialog(null, "Error al eliminar el registro", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    }
		});

		btneliminar.setToolTipText("Eliminar registro");
		btneliminar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btneliminar.setBackground(UIManager.getColor("Button.highlight"));
		btneliminar.setBounds(263, 17, 90, 23);
		panelbotones.add(btneliminar);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBounds(22, 131, 990, 440);
		getContentPane().add(panel_1);
		
		
		
		
	
		
	}//metod
	

	@SuppressWarnings("serial")
	public void construirTabla() {
	    // Crear el modelo de la tabla
		 modeloTabla = new DefaultTableModel(
	        new Object[] {
	            "No.", "Usuario", "Rol", "Descripción", "Empleados", "Permisos",
	            "Incapacidades", "Vacaciones", "Cargos", "Áreas", "Reportes", "Respaldos",
	            "Usuarios", "Fecha"
	        }, 0
	    ) {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false; // No permitir edición de celdas
	        }
	    };

	    // Asignar la tabla a la variable global
	    tablaRoles = new JTable(modeloTabla);
	    tablaRoles.getTableHeader().setBackground(new Color(32, 136, 203)); // Encabezado azul
	    tablaRoles.getTableHeader().setForeground(Color.WHITE);
	    tablaRoles.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
	    tablaRoles.setFont(new Font("Segoe UI", Font.PLAIN, 12));
	    tablaRoles.setRowHeight(25);

	    // Ajustar el ancho de las columnas
	    tablaRoles.getColumnModel().getColumn(0).setPreferredWidth(30); // Número
	    tablaRoles.getColumnModel().getColumn(3).setPreferredWidth(120); // Descripción

	    // Obtener datos de la base de datos
	    consultas_roles consultas = new consultas_roles();
	    List<Object[]> datos = consultas.obtenerRoles(); // Método que retorna los roles desde la BD

	    // Formateador para la fecha
	    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yy");

	    for (Object[] fila : datos) {
	        // Convertir los valores booleanos a "Sí" o "No" y dar formato a la fecha
	        Object[] filaConvertida = new Object[fila.length];
	        for (int i = 0; i < fila.length; i++) {
	            if (fila[i] instanceof Boolean) {
	                filaConvertida[i] = (Boolean) fila[i] ? "Sí" : "No";
	            } else if (fila[i] instanceof java.sql.Timestamp || fila[i] instanceof java.sql.Date) {
	                filaConvertida[i] = dateFormatter.format(fila[i]); // Formatear fecha
	            } else {
	                filaConvertida[i] = fila[i]; // Copiar otros valores directamente
	            }
	        }
	        modeloTabla.addRow(filaConvertida); // Llenar la tabla con datos de la BD
	    }


	    JScrollPane scrollPane = new JScrollPane(tablaRoles);
	    scrollPane.setBounds(10, 10, 970, 420);
	    panel_1.add(scrollPane); 
	    
	    tablaRoles.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            if (e.getClickCount() == 2 && tablaRoles.getSelectedRow() != -1) {
	            	
	                int filaSeleccionada = tablaRoles.getSelectedRow();
	                String usuarioSeleccionado = (String) modeloTabla.getValueAt(filaSeleccionada, 1); 
	                String contrasena = (String) modeloTabla.getValueAt(filaSeleccionada, 3); 
	                String nombreRol = (String) modeloTabla.getValueAt(filaSeleccionada, 4);
	                String descripcion = (String) modeloTabla.getValueAt(filaSeleccionada, 5);

	                boolean empleados = "Sí".equals(modeloTabla.getValueAt(filaSeleccionada, 6));
	                boolean ausenciaLaboral = "Sí".equals(modeloTabla.getValueAt(filaSeleccionada, 7));
	                boolean incapacidades = "Sí".equals(modeloTabla.getValueAt(filaSeleccionada, 8));
	                boolean vacaciones = "Sí".equals(modeloTabla.getValueAt(filaSeleccionada, 9));
	                boolean cargos = "Sí".equals(modeloTabla.getValueAt(filaSeleccionada, 10));
	                boolean areas = "Sí".equals(modeloTabla.getValueAt(filaSeleccionada, 11));
	                boolean reportes = "Sí".equals(modeloTabla.getValueAt(filaSeleccionada, 12));
	                boolean respaldos = "Sí".equals(modeloTabla.getValueAt(filaSeleccionada, 13));
	                boolean usuarios = "Sí".equals(modeloTabla.getValueAt(filaSeleccionada, 13));

	                // Abrir el formulario de edición y pasar los datos
	                roles_nuevo formulario = new roles_nuevo();

	                formulario.cargarUsuarios(usuarioSeleccionado); // Seleccionar usuario en el JComboBox
	                formulario.txtcontra.setText(contrasena);
	                formulario.txtrol.setText(nombreRol);
	                formulario.txadescripcion.setText(descripcion);

	                formulario.chkempleados.setSelected(empleados);
	                formulario.chkpermisos.setSelected(ausenciaLaboral);
	                formulario.chkincapacidades.setSelected(incapacidades);
	                formulario.chkvacaciones.setSelected(vacaciones);
	                formulario.chkcargos.setSelected(cargos);
	                formulario.chkareas.setSelected(areas);
	                formulario.chkreportes.setSelected(reportes);
	                formulario.chkrespaldos.setSelected(respaldos);
	                formulario.chkusuarios.setSelected(usuarios);

	                // Deshabilitar campos inicialmente
	                formulario.habilitarCampos(false);

	                // Configurar botones
	                formulario.btnguardar.setVisible(false); // Ocultar botón guardar
	                formulario.btnactualizar.setVisible(false); // Ocultar botón actualizar inicialmente

	                // Configurar el JCheckBox para habilitar campos y mostrar botón actualizar
	                formulario.chxeditar.addActionListener(event -> {
	                    boolean editable = formulario.chxeditar.isSelected();
	                    formulario.habilitarCampos(editable);
	                    formulario.btnactualizar.setVisible(editable); // Mostrar botón actualizar solo si está en modo edición
	                });

	                formulario.setVisible(true);
	                formulario.setLocationRelativeTo(null);
	            }
	        }
	    });

	    // Actualizar el diseño
	    panel_1.revalidate();
	    panel_1.repaint();
	}


	private void cerrar_ventana() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}



	
	


	
	
	
	
}
	

