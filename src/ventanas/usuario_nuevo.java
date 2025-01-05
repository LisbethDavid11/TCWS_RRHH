package ventanas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window.Type;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import principal.menu_principal;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.UIManager;

import clases.usuarioC;
import clases.validaciones;
import consultas.consultas_usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class usuario_nuevo extends JFrame {
	public JTextField txtnombres;
	
	ImageIcon icono_fotografia = new ImageIcon("src/imagenes/tcws_este.png");
	ImageIcon icono_fotografia2 = new ImageIcon("src/imagenes/u1.png");
	private JTextField txtapellidos;
	private JTextField txtcorreo;
	private JTextField txtusuario;
	private JPasswordField pscontra;
	private JPasswordField pscontra_conf;
	private boolean mostrarC1 = false; 
    private boolean mostrarC2 = false;
    public JCheckBox chkeditar;
    public JButton btnguardar;
    public JButton btnregresar_login;
    public JButton btnpermisos;
	public JButton btnactualizar;
	public JButton btnregresar_tabla;
	public JLabel lblid;
	
	public usuario_nuevo() {
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
		
		
		JPanel panelDatos = new JPanel();
		panelDatos.setBackground(SystemColor.menu);
		panelDatos.setBounds(512, 97, 485, 432);
		getContentPane().add(panelDatos);
		panelDatos.setLayout(null);
		
		btnguardar = new JButton("Guardar");
		btnguardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarUsuario();
			}
		});
		btnguardar.setToolTipText("Guardar el registro");
		btnguardar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnguardar.setBackground(UIManager.getColor("Button.light"));
		btnguardar.setBounds(282, 378, 110, 23);
		panelDatos.add(btnguardar);
		
		JButton btnmostrar_c2 = new JButton("");
		btnmostrar_c2.setIcon(new ImageIcon(usuario_nuevo.class.getResource("/imagenes/ver.png")));
		btnmostrar_c2.setToolTipText("Ver contraseña");
		btnmostrar_c2.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnmostrar_c2.setBounds(400, 315, 42, 28);
		panelDatos.add(btnmostrar_c2);
		
		JButton btnmostrar_c1 = new JButton("");
		btnmostrar_c1.setIcon(new ImageIcon(usuario_nuevo.class.getResource("/imagenes/ver.png")));
		btnmostrar_c1.setToolTipText("Ver contraseña");
		btnmostrar_c1.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnmostrar_c1.setBounds(400, 268, 42, 28);
		panelDatos.add(btnmostrar_c1);
		
		JLabel lbln = new JLabel("Nombres");
		lbln.setBounds(29, 28, 196, 33);
		lbln.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelDatos.add(lbln);
		
		txtnombres = new JTextField(10);
		txtnombres.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
		       validaciones.validarNombresyApellidos(e, txtnombres, 70);
			}
		});
		txtnombres.setBounds(228, 28, 212, 33);
		txtnombres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDatos.add(txtnombres);
		
		JLabel lbla = new JLabel("Apellidos");
		lbla.setBounds(29, 71, 196, 33);
		lbla.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelDatos.add(lbla);
		
		btnactualizar = new JButton("Actualizar");
		btnactualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarUsuario();
			}
		});
		btnactualizar.setToolTipText("Guardar el registro");
		btnactualizar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnactualizar.setBackground(UIManager.getColor("Button.highlight"));
		btnactualizar.setBounds(282, 378, 110, 23);
		panelDatos.add(btnactualizar);
		
		txtapellidos = new JTextField(10);
		txtapellidos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				validaciones.validarNombresyApellidos(e, txtapellidos, 70);
			}
		});
		txtapellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtapellidos.setBounds(228, 71, 212, 33);
		panelDatos.add(txtapellidos);
		
		JLabel lblc = new JLabel("Correo electrónico");
		lblc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblc.setBounds(29, 114, 198, 33);
		panelDatos.add(lblc);
		
		txtcorreo = new JTextField(10);
		txtcorreo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtcorreo.setBounds(228, 114, 212, 33);
		panelDatos.add(txtcorreo);
		
		JLabel lblus = new JLabel("Usuario");
		lblus.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblus.setBounds(29, 225, 198, 33);
		panelDatos.add(lblus);
		
		txtusuario = new JTextField(10);
		txtusuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtusuario.setBounds(228, 225, 212, 33);
		panelDatos.add(txtusuario);
		
		JLabel lblcont = new JLabel("Contraseña");
		lblcont.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblcont.setBounds(29, 267, 198, 33);
		panelDatos.add(lblcont);
		
		JLabel lblconf = new JLabel("Confirme la contraseña");
		lblconf.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblconf.setBounds(29, 310, 198, 33);
		panelDatos.add(lblconf);
		
		JLabel lbllineas = new JLabel("---- Para acceder -------------------------------------------------");
		lbllineas.setHorizontalAlignment(SwingConstants.LEFT);
		lbllineas.setForeground(Color.GRAY);
		lbllineas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbllineas.setBounds(21, 187, 454, 28);
		panelDatos.add(lbllineas);
		
		pscontra = new JPasswordField();
		pscontra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pscontra.setBounds(228, 268, 170, 33);
		panelDatos.add(pscontra);
		
		pscontra_conf = new JPasswordField();
		pscontra_conf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pscontra_conf.setBounds(228, 310, 170, 33);
		panelDatos.add(pscontra_conf);
		
		btnregresar_login = new JButton("Regresar");
		btnregresar_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login_principal p = new login_principal();
				p.setVisible(true);
				p.setLocationRelativeTo(null);
				dispose();
				
				
			}
			
			
		});
		btnregresar_login.setToolTipText("Regresar al Login");
		btnregresar_login.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnregresar_login.setBackground(UIManager.getColor("Button.highlight"));
		btnregresar_login.setBounds(128, 378, 110, 23);
		panelDatos.add(btnregresar_login);
		
		btnregresar_tabla = new JButton("Regresar");
		btnregresar_tabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuario_tabla ut = new usuario_tabla();
				ut.setVisible(true);
				ut.setLocationRelativeTo(null);
				dispose();
				ut.construirTabla();
			}
		});
		btnregresar_tabla.setToolTipText("Regresar al menú");
		btnregresar_tabla.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnregresar_tabla.setBackground(UIManager.getColor("Button.light"));
		btnregresar_tabla.setBounds(128, 378, 110, 23);
		panelDatos.add(btnregresar_tabla);
		
		JLabel lblbienvenido = new JLabel("Registro");
		lblbienvenido.setBounds(482, 36, 554, 51);
		getContentPane().add(lblbienvenido);
		lblbienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblbienvenido.setFont(new Font("Georgia", Font.BOLD, 33));
		
		btnpermisos = new JButton("Roles y permisos para el usuario");
		btnpermisos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roles_nuevo r = new roles_nuevo();
				r.setVisible(true);
				r.setLocationRelativeTo(null);
				r.chxeditar.setVisible(false);
				r.btnactualizar.setVisible(false);
				dispose();

			}
		});
		btnpermisos.setBounds(621, 545, 275, 23);
		getContentPane().add(btnpermisos);
		btnpermisos.setToolTipText("Asignar rol y permisos");
		btnpermisos.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnpermisos.setBackground(UIManager.getColor("Button.light"));
		
		JPanel panelFoto = new JPanel();
		panelFoto.setLayout(null);
		panelFoto.setBackground(new Color(163, 189, 237));
		panelFoto.setBounds(0, 0, 472, 618);
		getContentPane().add(panelFoto);
		
		JLabel lblSistemaControlDe = new JLabel("Sistema Control de Empleados, ");
		lblSistemaControlDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistemaControlDe.setForeground(Color.BLACK);
		lblSistemaControlDe.setFont(new Font("Georgia", Font.BOLD, 20));
		lblSistemaControlDe.setBackground(Color.BLACK);
		lblSistemaControlDe.setBounds(0, 435, 472, 58);
		panelFoto.add(lblSistemaControlDe);
		
		JLabel lblIncapacidadesYVacaciones = new JLabel("Permisos, Incapacidades y Vacaciones.");
		lblIncapacidadesYVacaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblIncapacidadesYVacaciones.setForeground(Color.BLACK);
		lblIncapacidadesYVacaciones.setFont(new Font("Georgia", Font.BOLD, 20));
		lblIncapacidadesYVacaciones.setBackground(Color.BLACK);
		lblIncapacidadesYVacaciones.setBounds(0, 466, 472, 58);
		panelFoto.add(lblIncapacidadesYVacaciones);
		
		JLabel lblTodosLosDerechos = new JLabel("Todos los derechos reservados.\r\n");
		lblTodosLosDerechos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTodosLosDerechos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblTodosLosDerechos.setBounds(7, 526, 465, 35);
		panelFoto.add(lblTodosLosDerechos);
		
		JLabel lblVersin = new JLabel("Versión 1.1");
		lblVersin.setHorizontalAlignment(SwingConstants.CENTER);
		lblVersin.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblVersin.setBounds(0, 548, 472, 24);
		panelFoto.add(lblVersin);
		
		JLabel lblfoto = new JLabel("");
		lblfoto.setBounds(56, 63, 349, 345);
		panelFoto.add(lblfoto);
		lblfoto.setIcon(new ImageIcon(icono_fotografia.getImage().getScaledInstance(lblfoto.getWidth(),
				lblfoto.getHeight(), Image.SCALE_SMOOTH))); //imagen default
		panelFoto.add(lblfoto);
		
		chkeditar = new JCheckBox("Editar registro");
		chkeditar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chkeditar.setBounds(866, 74, 132, 21);
		getContentPane().add(chkeditar);
		
		lblid = new JLabel("");
		lblid.setBounds(1026, 10, 0, 0);
		getContentPane().add(lblid);
		lblid.setFont(new Font("Tahoma", Font.BOLD, 5));
		
		chkeditar.addActionListener(new ActionListener() {
		     @Override
		     public void actionPerformed(ActionEvent e) {
		         if (chkeditar.isSelected()) {
		        	 
		             habilitarCampos(true); 
		             btnactualizar.setVisible(true); 
		         } else {
		        	 
		             habilitarCampos(false); 
		             btnactualizar.setVisible(false);
		         }
		     }
		 });
		
		
		
        btnmostrar_c1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarC1 = !mostrarC1; 
                if (mostrarC1) {
                    pscontra.setEchoChar((char) 0); 
                } else {
                    pscontra.setEchoChar('•'); 
                }
            }
        });

        
        btnmostrar_c2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarC2 = !mostrarC2; 
                if (mostrarC2) {
                    pscontra_conf.setEchoChar((char) 0); 
                } else {
                	pscontra_conf.setEchoChar('•'); 
                }
            }
        });  
        
        
        
        btnactualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarUsuario(); 
            }
        });
        
 
	}//class
	
	
	public void cargarDatosUsuario(int idUsuario, String usuario, String nombres, String apellidos, String correo, String contrasena) {
	    lblid.setText(String.valueOf(idUsuario)); // Almacenar el ID en el JLabel
	    txtusuario.setText(usuario);
	    txtnombres.setText(nombres);
	    txtapellidos.setText(apellidos);
	    txtcorreo.setText(correo);
	    pscontra.setText(contrasena);

	    // Deshabilitar los campos para evitar edición accidental
	    txtusuario.setEnabled(false);
	    txtnombres.setEnabled(false);
	    txtapellidos.setEnabled(false);
	    txtcorreo.setEnabled(false);
	    pscontra.setEnabled(false);
	    pscontra_conf.setEnabled(false);
	}


	
	public void habilitarCampos(boolean estado) {
	    txtusuario.setEnabled(estado);
	    txtnombres.setEnabled(estado);
	    txtapellidos.setEnabled(estado);
	    txtcorreo.setEnabled(estado);
	    pscontra.setEnabled(estado);
	    pscontra_conf.setEnabled(estado);
	}
	
	private void cerrar_ventana() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	
	public void guardarUsuario() {
	    usuarioC usuario = new usuarioC();
	    usuario.setUsuario(txtusuario.getText().trim());
	    usuario.setContrasena(new String(pscontra.getPassword()).trim());
	    usuario.setNombres(txtnombres.getText().trim());
	    usuario.setApellidos(txtapellidos.getText().trim());
	    usuario.setCorreo(txtcorreo.getText().trim());

	    String confirmarContrasena = new String(pscontra_conf.getPassword()).trim();

	    // Validar campos vacíos
	    if (usuario.getUsuario().isEmpty() || usuario.getContrasena().isEmpty() || confirmarContrasena.isEmpty() ||
	        usuario.getNombres().isEmpty() || usuario.getApellidos().isEmpty() || usuario.getCorreo().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Validar que las contraseñas coincidan
	    if (!usuario.getContrasena().equals(confirmarContrasena)) {
	        JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    consultas_usuario consultas = new consultas_usuario();

	    // Verificar si el usuario ya existe en la base de datos
	    if (consultas.usuarioExiste(usuario.getUsuario())) {
	    	//JOptionPane.showMessageDialog(this, "El usuario ya existe en otro registro", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    // Guardar usuario en la base de datos
	    boolean resultado = consultas.guardarUsuario(usuario);

	    if (resultado) {
	        JOptionPane.showMessageDialog(this, "Usuario guardado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        limpiarCampos(); // Limpiar los campos después de guardar
	    } else {
	        JOptionPane.showMessageDialog(this, "Error al registrar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}


	
	
	public void actualizarUsuario() {
	    int idUsuario = Integer.parseInt(lblid.getText().trim()); // Recuperar el ID del registro actual

	    usuarioC usuario = new usuarioC();
	    usuario.setUsuario(txtusuario.getText().trim());
	    usuario.setContrasena(new String(pscontra.getPassword()).trim());
	    usuario.setNombres(txtnombres.getText().trim());
	    usuario.setApellidos(txtapellidos.getText().trim());
	    usuario.setCorreo(txtcorreo.getText().trim());

	    String confirmarContrasena = new String(pscontra_conf.getPassword()).trim();

	    // Validar campos vacíos
	    if (usuario.getUsuario().isEmpty() || usuario.getContrasena().isEmpty() || confirmarContrasena.isEmpty() ||
	        usuario.getNombres().isEmpty() || usuario.getApellidos().isEmpty() || usuario.getCorreo().isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Validar que las contraseñas coincidan
	    if (!usuario.getContrasena().equals(confirmarContrasena)) {
	        JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    consultas_usuario consultas = new consultas_usuario();

	    // Verificar si el usuario ya existe en otro registro solo si se intenta cambiar el nombre de usuario
	    if (consultas.usuarioDuplicadoEnOtroRegistro(usuario.getUsuario(), idUsuario)) {
	        JOptionPane.showMessageDialog(this, "El usuario ya existe en otro registro", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        return;
	    }

	    // Actualizar usuario en la base de datos
	    boolean resultado = consultas.actualizarUsuario(usuario, idUsuario);

	    if (resultado) {
	        JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	    } else {
	        JOptionPane.showMessageDialog(this, "Error al actualizar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}


	
	
	private void limpiarCampos() {
	    txtusuario.setText("");
	    pscontra.setText("");
	    pscontra_conf.setText("");
	    txtnombres.setText("");
	    txtapellidos.setText("");
	    txtcorreo.setText("");
	}
}
