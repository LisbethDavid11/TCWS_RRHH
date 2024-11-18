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

public class login_registrese extends JFrame {
	public JTextField txtnombres;
	public JLabel lblfoto;
	
	ImageIcon icono_fotografia = new ImageIcon("src/imagenes/tcws_hd.png");
	ImageIcon icono_fotografia2 = new ImageIcon("src/imagenes/u1.png");
	private JTextField txtapellidos;
	private JTextField txtcorreo;
	private JTextField txtusuario;
	private JPasswordField pscontra_registro;
	private JPasswordField pscontra_confirmacion;
	
	
	public login_registrese() {
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
		panelDatos.setBounds(499, 112, 485, 432);
		getContentPane().add(panelDatos);
		panelDatos.setLayout(null);
		
		JLabel lbln = new JLabel("Nombres");
		lbln.setBounds(29, 28, 196, 33);
		lbln.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelDatos.add(lbln);
		
		txtnombres = new JTextField(10);
		txtnombres.setBounds(228, 28, 212, 33);
		txtnombres.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDatos.add(txtnombres);
		
		JLabel lbla = new JLabel("Apellidos");
		lbla.setBounds(29, 71, 196, 33);
		lbla.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelDatos.add(lbla);
		
		JButton btnguardar = new JButton("Guardar");
		btnguardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnguardar.setToolTipText("Guardar el registro");
		btnguardar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnguardar.setBackground(UIManager.getColor("Button.highlight"));
		btnguardar.setBounds(287, 377, 110, 23);
		panelDatos.add(btnguardar);
		
		txtapellidos = new JTextField(10);
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
		
		JLabel lblnota1 = new JLabel("Nota: el correo electrónico es para la");
		lblnota1.setHorizontalAlignment(SwingConstants.CENTER);
		lblnota1.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblnota1.setBounds(228, 146, 212, 33);
		panelDatos.add(lblnota1);
		
		JLabel lblnota2 = new JLabel("recuperación de la contraseña.");
		lblnota2.setHorizontalAlignment(SwingConstants.CENTER);
		lblnota2.setFont(new Font("Tahoma", Font.ITALIC, 9));
		lblnota2.setBounds(228, 163, 212, 23);
		panelDatos.add(lblnota2);
		
		pscontra_registro = new JPasswordField();
		pscontra_registro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pscontra_registro.setBounds(228, 268, 212, 33);
		panelDatos.add(pscontra_registro);
		
		pscontra_confirmacion = new JPasswordField();
		pscontra_confirmacion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pscontra_confirmacion.setBounds(228, 310, 212, 33);
		panelDatos.add(pscontra_confirmacion);
		
		JButton btnregresar = new JButton("Regresar");
		btnregresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login_principal p = new login_principal();
				p.setVisible(true);
				p.setLocationRelativeTo(null);
				dispose();
				
			}
			
			
		});
		btnregresar.setToolTipText("Regresar al Login");
		btnregresar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnregresar.setBackground(UIManager.getColor("Button.highlight"));
		btnregresar.setBounds(98, 377, 110, 23);
		panelDatos.add(btnregresar);
		
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
		
		JLabel lblbienvenido = new JLabel("Registrese");
		lblbienvenido.setBounds(460, 51, 576, 51);
		getContentPane().add(lblbienvenido);
		lblbienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblbienvenido.setFont(new Font("Georgia", Font.BOLD, 33));
		
		
	}//class
	
	
	private void cerrar_ventana() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	
	
}
