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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class login_principal extends JFrame {
	public JTextField txtusuario;
	public JLabel lblfoto;
	public JLabel lblicon;
	
	ImageIcon icono_fotografia = new ImageIcon("src/imagenes/tcws_hd.png");
	ImageIcon icono_fotografia2 = new ImageIcon("src/imagenes/u1.png");
	private JPasswordField txtcontrasena;
	
	
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
		panelDatos.setBounds(499, 112, 485, 406);
		getContentPane().add(panelDatos);
		panelDatos.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(68, 166, 130, 33);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelDatos.add(lblUsuario);
		
		txtusuario = new JTextField(10);
		txtusuario.setBounds(199, 166, 212, 33);
		txtusuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDatos.add(txtusuario);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setBounds(68, 215, 130, 33);
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelDatos.add(lblContrasea);
		
		JLabel lblolvido_contrasena = new JLabel("¿Olvidó su contraseña?");
		lblolvido_contrasena.setBounds(199, 270, 212, 20);
		lblolvido_contrasena.setToolTipText("");
		lblolvido_contrasena.setHorizontalAlignment(SwingConstants.CENTER);
		lblolvido_contrasena.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelDatos.add(lblolvido_contrasena);
		
		JButton btniniciar = new JButton("Iniciar sesión");
		btniniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btniniciar.setToolTipText("Acceder");
		btniniciar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btniniciar.setBackground(UIManager.getColor("Button.highlight"));
		btniniciar.setBounds(182, 341, 130, 23);
		panelDatos.add(btniniciar);
		
		lblicon = new JLabel("");
		lblicon.setBounds(182, 21, 130, 123);
		panelDatos.add(lblicon);
		
		lblicon.setIcon(new ImageIcon(icono_fotografia2.getImage().getScaledInstance(lblicon.getWidth(),
				lblicon.getHeight(), Image.SCALE_SMOOTH))); //imagen default
		panelDatos.add(lblicon);
		
		txtcontrasena = new JPasswordField();
		txtcontrasena.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtcontrasena.setBounds(199, 217, 212, 33);
		panelDatos.add(txtcontrasena);
		
		JLabel lblnoTieneUn = new JLabel("¿No tiene un usuario?");
		lblnoTieneUn.setToolTipText("");
		lblnoTieneUn.setHorizontalAlignment(SwingConstants.CENTER);
		lblnoTieneUn.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblnoTieneUn.setBounds(698, 543, 163, 20);
		getContentPane().add(lblnoTieneUn);
		
		JButton btnregistrese = new JButton("Regístrese");
		btnregistrese.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login_registrese r = new login_registrese();
				r.setVisible(true);
				r.setLocationRelativeTo(null);
				dispose();
			}
				
		});
		btnregistrese.setToolTipText("Crea un nuevo usuario");
		btnregistrese.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnregistrese.setBackground(UIManager.getColor("Button.highlight"));
		btnregistrese.setBounds(859, 543, 125, 23);
		getContentPane().add(btnregistrese);
		
		JPanel panelFoto = new JPanel();
		panelFoto.setBackground(new Color(100, 149, 237));
		panelFoto.setBounds(0, 0, 450, 618);
		getContentPane().add(panelFoto);
		panelFoto.setLayout(null);
		
		JLabel lblSistemaControlDe = new JLabel("Sistema Control de Empleados, ");
		lblSistemaControlDe.setForeground(new Color(0, 0, 0));
		lblSistemaControlDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblSistemaControlDe.setFont(new Font("Georgia", Font.BOLD, 20));
		lblSistemaControlDe.setBounds(0, 424, 450, 58);
		panelFoto.add(lblSistemaControlDe);
		
		JLabel lblIncapacidadesYVacaciones = new JLabel("Permisos, Incapacidades y Vacaciones.");
		lblIncapacidadesYVacaciones.setForeground(new Color(0, 0, 0));
		lblIncapacidadesYVacaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblIncapacidadesYVacaciones.setFont(new Font("Georgia", Font.BOLD, 20));
		lblIncapacidadesYVacaciones.setBounds(0, 455, 450, 58);
		panelFoto.add(lblIncapacidadesYVacaciones);
		
		lblfoto = new JLabel("");
		lblfoto.setBounds(35, 56, 373, 358);
		panelFoto.add(lblfoto);
		
		lblfoto.setIcon(new ImageIcon(icono_fotografia.getImage().getScaledInstance(lblfoto.getWidth(),
				lblfoto.getHeight(), Image.SCALE_SMOOTH))); //imagen default
		panelFoto.add(lblfoto);
		
		JLabel lblbienvenido = new JLabel("Login In");
		lblbienvenido.setBounds(460, 51, 576, 51);
		getContentPane().add(lblbienvenido);
		lblbienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblbienvenido.setFont(new Font("Georgia", Font.BOLD, 33));
	}
	
	
	
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
}
