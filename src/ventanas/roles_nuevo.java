package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Window.Type;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.UIManager;

import consultas.consultas_roles;
import principal.menu_principal;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class roles_nuevo extends JFrame{
	
	public JTextField txtcontra;
	public JTextField txtusuario;
	public JTextField txtrol;
	public JTextArea txadescripcion;
	public JComboBox cbxusuario;

	public JCheckBox chkempleados;
	public JCheckBox chkpermisos;
	public JCheckBox chkincapacidades;
	public JCheckBox chkvacaciones;
	public JCheckBox chkreportes;
	public JCheckBox chkusuarios;
	public JCheckBox chkrespaldos;
	public JCheckBox chkareas;
	public JCheckBox chkcargos;
	public JCheckBox chxeditar;
	public JButton btnguardar;
	public JButton btnregresar;
	public JButton btnactualizar;
	public JButton btnlimpiar;
	public JLabel txtid;
	
	
	private consultas_roles consultas;
	
	public roles_nuevo() {
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
		
		
		JPanel panel_rol = new JPanel();
		panel_rol.setBackground(SystemColor.menu);
		panel_rol.setBounds(34, 89, 472, 482);
		getContentPane().add(panel_rol);
		panel_rol.setLayout(null);
		
		JLabel lblNombreDelUsuario = new JLabel("Nombre del usuario");
		lblNombreDelUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreDelUsuario.setBounds(32, 73, 162, 33);
		panel_rol.add(lblNombreDelUsuario);
		
		txtcontra = new JTextField(10);
		txtcontra.setEditable(false);
		txtcontra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtcontra.setBounds(214, 135, 241, 33);
		panel_rol.add(txtcontra);
		
		cbxusuario = new JComboBox();
		cbxusuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 mostrarContrasena();
			}
		});
		cbxusuario.setBounds(214, 73, 243, 33);
		panel_rol.add(cbxusuario);
		
		JLabel lblcontra = new JLabel("Contraseña");
		lblcontra.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblcontra.setBounds(32, 137, 162, 33);
		panel_rol.add(lblcontra);
		
		JLabel lblNombreDelRol = new JLabel("Nombre del rol");
		lblNombreDelRol.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNombreDelRol.setBounds(32, 195, 162, 33);
		panel_rol.add(lblNombreDelRol);
		
		txtrol = new JTextField(10);
		txtrol.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtrol.setBounds(214, 193, 241, 33);
		panel_rol.add(txtrol);
		
		JLabel lblDescripcin = new JLabel("Descripción del rol");
		lblDescripcin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescripcin.setBounds(32, 254, 162, 33);
		panel_rol.add(lblDescripcin);
		
		txadescripcion = new JTextArea();
		txadescripcion.setBounds(214, 252, 241, 146);
		panel_rol.add(txadescripcion);
		
		JLabel lblDatosDel_1_2 = new JLabel("_______ Asignación del rol ________________________________\r\n");
		lblDatosDel_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblDatosDel_1_2.setForeground(Color.GRAY);
		lblDatosDel_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDatosDel_1_2.setBounds(10, 23, 445, 28);
		panel_rol.add(lblDatosDel_1_2);
		
		JPanel panel_permisos = new JPanel();
		panel_permisos.setBackground(SystemColor.menu);
		panel_permisos.setBounds(528, 89, 468, 482);
		getContentPane().add(panel_permisos);
		panel_permisos.setLayout(null);
		
		JLabel lblDatosDel_1_1 = new JLabel("_______ Asignación de los permisos _________________________\r\n\r\n");
		lblDatosDel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblDatosDel_1_1.setForeground(Color.GRAY);
		lblDatosDel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDatosDel_1_1.setBounds(10, 21, 448, 28);
		panel_permisos.add(lblDatosDel_1_1);
		
		chkempleados = new JCheckBox("Empleados");
		chkempleados.setFont(new Font("Tahoma", Font.BOLD, 14));
		chkempleados.setBounds(43, 66, 110, 21);
		panel_permisos.add(chkempleados);
		
		chkpermisos = new JCheckBox("Permisos por ausencia laboral");
		chkpermisos.setFont(new Font("Tahoma", Font.BOLD, 14));
		chkpermisos.setBounds(43, 111, 254, 21);
		panel_permisos.add(chkpermisos);
		
		chkincapacidades = new JCheckBox("Incapacidades");
		chkincapacidades.setFont(new Font("Tahoma", Font.BOLD, 14));
		chkincapacidades.setBounds(43, 156, 254, 21);
		panel_permisos.add(chkincapacidades);
		
		chkvacaciones = new JCheckBox("Vacaciones");
		chkvacaciones.setFont(new Font("Tahoma", Font.BOLD, 14));
		chkvacaciones.setBounds(43, 201, 254, 21);
		panel_permisos.add(chkvacaciones);
		
		chkcargos = new JCheckBox("Cargos");
		chkcargos.setFont(new Font("Tahoma", Font.BOLD, 14));
		chkcargos.setBounds(43, 246, 254, 21);
		panel_permisos.add(chkcargos);
		
		chkareas = new JCheckBox("Áreas");
		chkareas.setFont(new Font("Tahoma", Font.BOLD, 14));
		chkareas.setBounds(43, 291, 254, 21);
		panel_permisos.add(chkareas);
		
		chkreportes = new JCheckBox("Reportes");
		chkreportes.setFont(new Font("Tahoma", Font.BOLD, 14));
		chkreportes.setBounds(43, 336, 254, 21);
		panel_permisos.add(chkreportes);
		
		chkrespaldos = new JCheckBox("Respaldos");
		chkrespaldos.setFont(new Font("Tahoma", Font.BOLD, 14));
		chkrespaldos.setBounds(43, 381, 254, 21);
		panel_permisos.add(chkrespaldos);
		
		chkusuarios = new JCheckBox("Usuarios");
		chkusuarios.setFont(new Font("Tahoma", Font.BOLD, 14));
		chkusuarios.setBounds(43, 426, 254, 21);
		panel_permisos.add(chkusuarios);
		
		JPanel panel_botones = new JPanel();
		panel_botones.setLayout(null);
		panel_botones.setBackground(SystemColor.menu);
		panel_botones.setBounds(528, 10, 472, 65);
		getContentPane().add(panel_botones);
		
		 btnguardar = new JButton("Guardar");
		btnguardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarRol();
			}
		});
		btnguardar.setToolTipText("Guardar registro");
		btnguardar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnguardar.setBackground(UIManager.getColor("Button.light"));
		btnguardar.setBounds(377, 17, 90, 23);
		panel_botones.add(btnguardar);
		
		 btnactualizar = new JButton("Actualizar");
		 btnactualizar.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		actualizarRol();
		 	}
		 });
		btnactualizar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnactualizar.setBackground(UIManager.getColor("Button.light"));
		btnactualizar.setBounds(377, 17, 90, 23);
		panel_botones.add(btnactualizar);
		
		 btnlimpiar = new JButton("Limpiar");
		btnlimpiar.setToolTipText("Limpiar registro");
		btnlimpiar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnlimpiar.setBackground(UIManager.getColor("Button.light"));
		btnlimpiar.setBounds(282, 17, 90, 23);
		panel_botones.add(btnlimpiar);
		
		 btnregresar = new JButton("Regresar");
		btnregresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roles_tabla t = new roles_tabla();
				t.setVisible(true);
				t.setLocationRelativeTo(null);
				t.construirTabla();
				dispose();
			}
		});
		btnregresar.setToolTipText("Regresar a la tabla");
		btnregresar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnregresar.setBackground(UIManager.getColor("Button.light"));
		btnregresar.setBounds(47, 17, 90, 23);
		panel_botones.add(btnregresar);
		
		chxeditar = new JCheckBox("Editar registro");
		chxeditar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chxeditar.setBounds(164, 17, 105, 21);
		panel_botones.add(chxeditar);
		
		JLabel lblRolesYPermisos = new JLabel("ROLES Y PERMISOS PARA USUARIOS");
		lblRolesYPermisos.setHorizontalAlignment(SwingConstants.LEFT);
		lblRolesYPermisos.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lblRolesYPermisos.setBackground(new Color(255, 153, 0));
		lblRolesYPermisos.setBounds(51, 28, 467, 36);
		getContentPane().add(lblRolesYPermisos);
		
		 txtid = new JLabel("");
		txtid.setBounds(506, 5, 0, 0);
		getContentPane().add(txtid);
		
		consultas = new consultas_roles(); // Inicialización correcta
        cargarUsuarios(null);
		
	}
	
	
	
	public void cargarUsuarios(String usuarioSeleccionado) {
	    cbxusuario.removeAllItems(); // Limpia el JComboBox
	    cbxusuario.addItem("Seleccione un usuario"); // Opción por defecto

	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        con = consultas.conectar();

	        // Consulta para obtener todos los usuarios, incluyendo el usuario seleccionado
	        String sql = "SELECT usuario FROM usuarios";
	        ps = con.prepareStatement(sql);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            String usuario = rs.getString("usuario");
	            cbxusuario.addItem(usuario); // Agrega cada usuario al JComboBox
	        }

	        // Seleccionar el usuario actual si existe en el JComboBox
	        if (usuarioSeleccionado != null) {
	            cbxusuario.setSelectedItem(usuarioSeleccionado);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (con != null) consultas.desconectar(con);
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}




	    private void mostrarContrasena() {
	        String usuarioSeleccionado = (String) cbxusuario.getSelectedItem();

	        if (usuarioSeleccionado != null && !usuarioSeleccionado.equals(" ")) {
	            String contrasena = consultas.obtenerContrasenaUsuario(usuarioSeleccionado);
	            txtcontra.setText(contrasena);
	        } else {
	            txtcontra.setText(""); // Limpia el campo si no hay usuario seleccionado
	        }
	    }
	    
	    
	    
	    public void guardarRol() {
	        String nombreUsuario = (String) cbxusuario.getSelectedItem(); 
	        String contrasena = txtcontra.getText(); 
	        String nombreRol = txtrol.getText(); 
	        String descripcionRol = txadescripcion.getText(); 
	        boolean empleados = chkempleados.isSelected();
	        boolean ausenciaLaboral = chkpermisos.isSelected();
	        boolean incapacidades = chkincapacidades.isSelected();
	        boolean vacaciones = chkvacaciones.isSelected();
	        boolean cargos = chkcargos.isSelected();
	        boolean areas = chkareas.isSelected();
	        boolean reportes = chkreportes.isSelected();
	        boolean respaldos = chkrespaldos.isSelected();
	        boolean usuarios = chkusuarios.isSelected();
	        String fechaCreacion = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 

	        if (nombreUsuario == null || nombreUsuario.equals("Seleccione un usuario")) {
	            JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario válido", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        if (contrasena == null || contrasena.trim().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "La contraseña no puede estar vacía", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        if (nombreRol == null || nombreRol.trim().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Debe ingresar un nombre para el rol", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        JOptionPane.showMessageDialog(null, "Registro guardado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	       
	        boolean guardado = consultas.guardarRol(nombreUsuario, contrasena, nombreRol, descripcionRol, empleados,
	                ausenciaLaboral, incapacidades, vacaciones, cargos, areas, reportes, respaldos, usuarios);

	        if (guardado) {
	            JOptionPane.showMessageDialog(null, "El rol se guardó correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        } else {
	            JOptionPane.showMessageDialog(null, "No se pudo guardar el rol.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	    
	    
	    public void habilitarCampos(boolean habilitar) {
	        // Habilitar o deshabilitar campos de texto
	        txtrol.setEnabled(habilitar);
	        txadescripcion.setEnabled(habilitar);

	        // Habilitar o deshabilitar los JCheckBox
	        chkempleados.setEnabled(habilitar);
	        chkpermisos.setEnabled(habilitar);
	        chkincapacidades.setEnabled(habilitar);
	        chkvacaciones.setEnabled(habilitar);
	        chkcargos.setEnabled(habilitar);
	        chkareas.setEnabled(habilitar);
	        chkreportes.setEnabled(habilitar);
	        chkrespaldos.setEnabled(habilitar);
	        chkusuarios.setEnabled(habilitar);

	        // Habilitar o deshabilitar el JComboBox para usuarios
	        cbxusuario.setEnabled(habilitar);

	        // La contraseña solo se habilita si se cambia el usuario en el JComboBox
	        cbxusuario.addActionListener(event -> {
	            if (cbxusuario.isEnabled()) {
	                txtcontra.setEnabled(true);
	            }
	        });

	        // Botón actualizar solo visible cuando los campos están habilitados
	        btnactualizar.setVisible(habilitar);
	    }
	    
	    
	    
	    public void actualizarRol() {
	        if (cbxusuario.getSelectedIndex() == 0) { // Verificar si se seleccionó un usuario válido
	            JOptionPane.showMessageDialog(this, "Por favor, seleccione un usuario válido.");
	            return;
	        }

	        // Capturar datos del formulario
	        String usuario = cbxusuario.getSelectedItem().toString();
	        String contrasena = txtcontra.getText().trim();
	        String nombreRol = txtrol.getText().trim();
	        String descripcion = txadescripcion.getText().trim();
	        boolean empleados = chkempleados.isSelected();
	        boolean permisos = chkpermisos.isSelected();
	        boolean incapacidades = chkincapacidades.isSelected();
	        boolean vacaciones = chkvacaciones.isSelected();
	        boolean cargos = chkcargos.isSelected();
	        boolean areas = chkareas.isSelected();
	        boolean reportes = chkreportes.isSelected();
	        boolean respaldos = chkrespaldos.isSelected();
	        boolean usuarios = chkusuarios.isSelected();

	        // Validar campos obligatorios
	        if (contrasena.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "La contraseña no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }
	        if (nombreRol.isEmpty() || descripcion.isEmpty()) {
	            JOptionPane.showMessageDialog(this, "Todos los campos obligatorios deben ser completados.", "Error", JOptionPane.ERROR_MESSAGE);
	            return;
	        }

	        // Llamar a la consulta para actualizar
	        consultas_roles consultas = new consultas_roles();
	        boolean actualizado = consultas.actualizarRol(
	            usuario,
	            contrasena,
	            nombreRol,
	            descripcion,
	            empleados,
	            permisos,
	            incapacidades,
	            vacaciones,
	            cargos,
	            areas,
	            reportes,
	            respaldos,
	            usuarios
	        );

	        if (actualizado) {
	            JOptionPane.showMessageDialog(this, "El rol fue actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	            dispose(); // Cerrar el formulario
	        } else {
	            JOptionPane.showMessageDialog(this, "Error al actualizar el rol. Intente nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
	    
	    
	    private void cerrar_ventana() {
			if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				System.exit(0);
		}


	    
	    
}
