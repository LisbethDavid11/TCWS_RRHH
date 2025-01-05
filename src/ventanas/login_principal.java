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

import com.mysql.cj.Session;

import consultas.consultas_login;
import consultas.consultas_roles;
import consultas.consultas_usuario;

import java.awt.event.ActionListener;
import java.net.PasswordAuthentication;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;




public class login_principal extends JFrame {
	public JTextField txtusuario;
	public JLabel lblfoto;
	public JLabel lblicon;
	
	ImageIcon icono_fotografia = new ImageIcon("src/imagenes/tcws_este.png");
	ImageIcon icono_fotografia2 = new ImageIcon("src/imagenes/u1.png");
	public JPasswordField txtcontrasena;
	public boolean mostrarC1 = false;
	
	
	public login_principal() {
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
		panelDatos.setBounds(528, 102, 457, 406);
		getContentPane().add(panelDatos);
		panelDatos.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(52, 174, 130, 33);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelDatos.add(lblUsuario);
		
		txtusuario = new JTextField(10);
		txtusuario.setBounds(175, 174, 235, 33);
		txtusuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDatos.add(txtusuario);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setBounds(52, 223, 130, 33);
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelDatos.add(lblContrasea);
		
		JButton btniniciar = new JButton("Iniciar sesión");
		btniniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarSesion();
			}
		});
		btniniciar.setToolTipText("Acceder");
		btniniciar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btniniciar.setBackground(UIManager.getColor("Button.light"));
		btniniciar.setBounds(173, 330, 130, 23);
		panelDatos.add(btniniciar);
		
		lblicon = new JLabel("");
		lblicon.setBounds(195, 23, 108, 123);
		panelDatos.add(lblicon);
		
		lblicon.setIcon(new ImageIcon(icono_fotografia2.getImage().getScaledInstance(lblicon.getWidth(),
				lblicon.getHeight(), Image.SCALE_SMOOTH))); //imagen default
		panelDatos.add(lblicon);
		
		txtcontrasena = new JPasswordField();
		txtcontrasena.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtcontrasena.setBounds(175, 225, 188, 33);
		panelDatos.add(txtcontrasena);
		
		JButton btnmostrar_c1 = new JButton("");
		btnmostrar_c1.setIcon(new ImageIcon(login_principal.class.getResource("/imagenes/ver.png")));
		btnmostrar_c1.setBackground(UIManager.getColor("Button.light"));
		btnmostrar_c1.setToolTipText("Ver contraseña");
		btnmostrar_c1.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnmostrar_c1.setBounds(368, 228, 42, 28);
		panelDatos.add(btnmostrar_c1);
		
		JLabel lblnoTieneUn = new JLabel("¿No tiene un usuario?");
		lblnoTieneUn.setToolTipText("");
		lblnoTieneUn.setHorizontalAlignment(SwingConstants.CENTER);
		lblnoTieneUn.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblnoTieneUn.setBounds(698, 543, 163, 20);
		getContentPane().add(lblnoTieneUn);
		
		JButton btnregistrese = new JButton("Regístrese");
		btnregistrese.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuario_nuevo r = new usuario_nuevo();
				r.setVisible(true);
				r.setLocationRelativeTo(null);
				dispose();
				r.btnregresar_tabla.setVisible(false);
				r.btnpermisos.setVisible(false);
				r.chkeditar.setVisible(false);
			}
				
		});
		btnregistrese.setToolTipText("Crea un nuevo usuario");
		btnregistrese.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnregistrese.setBackground(UIManager.getColor("Button.light"));
		btnregistrese.setBounds(859, 543, 125, 23);
		getContentPane().add(btnregistrese);
		
		JPanel panelFoto = new JPanel();
		panelFoto.setBackground(new Color(163, 189, 237));
		panelFoto.setBounds(0, 0, 472, 618);
		getContentPane().add(panelFoto);
		panelFoto.setLayout(null);
		
		JLabel lblSistemaControlDe = new JLabel("Sistema Control de Empleados, ");
		lblSistemaControlDe.setBackground(new Color(0, 0, 0));
		lblSistemaControlDe.setForeground(new Color(0, 0, 0));
		lblSistemaControlDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistemaControlDe.setFont(new Font("Georgia", Font.BOLD, 20));
		lblSistemaControlDe.setBounds(0, 435, 472, 58);
		panelFoto.add(lblSistemaControlDe);
		
		JLabel lblIncapacidadesYVacaciones = new JLabel("Permisos, Incapacidades y Vacaciones.");
		lblIncapacidadesYVacaciones.setBackground(new Color(0, 0, 0));
		lblIncapacidadesYVacaciones.setForeground(new Color(0, 0, 0));
		lblIncapacidadesYVacaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblIncapacidadesYVacaciones.setFont(new Font("Georgia", Font.BOLD, 20));
		lblIncapacidadesYVacaciones.setBounds(0, 466, 472, 58);
		panelFoto.add(lblIncapacidadesYVacaciones);
		
		lblfoto = new JLabel("");
		lblfoto.setBounds(56, 63, 349, 345);
		panelFoto.add(lblfoto);
		
		lblfoto.setIcon(new ImageIcon(icono_fotografia.getImage().getScaledInstance(lblfoto.getWidth(),
				lblfoto.getHeight(), Image.SCALE_SMOOTH))); //imagen default
		panelFoto.add(lblfoto);
		
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
		
		JLabel lblbienvenido = new JLabel("Login");
		lblbienvenido.setBounds(472, 10, 564, 82);
		getContentPane().add(lblbienvenido);
		lblbienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblbienvenido.setFont(new Font("Ebrima", Font.BOLD, 40));
		
		
		 btnmostrar_c1.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                mostrarC1 = !mostrarC1; 
	                if (mostrarC1) {
	                    txtcontrasena.setEchoChar((char) 0); 
	                } else {
	                    txtcontrasena.setEchoChar('•'); 
	                }
	            }
	        });

		 
		 
		
	}//class
	
	

	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					login_principal frame = new login_principal();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	private void cerrar_ventana() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	
	
	
	private void iniciarSesion() {
	    String usuario = txtusuario.getText();
	    String contrasena = new String(txtcontrasena.getPassword());
	    consultas_roles consultas = new consultas_roles();
	    
	    if (consultas.validarCredenciales(usuario, contrasena)) {
	        String nombreCompleto = consultas.obtenerNombreCompleto(usuario);

	        JOptionPane.showMessageDialog(this, "¡Bienvenido, " + nombreCompleto + "!", "Inicio de sesión exitoso", 
	        		JOptionPane.INFORMATION_MESSAGE);

	        menu_principal menu = new menu_principal();
	        menu.setNombreUsuario(nombreCompleto);
	        menu.cargarPermisos(usuario);
	        menu.setVisible(true);
	        menu.setLocationRelativeTo(null);
	        dispose();
	        
	    } else {
	        JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}


}
