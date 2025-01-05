package principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;

import consultas.consultas_roles;
import reportes.reporte_empleado_especial;
import reportes.reporte_empleados_activos;
import reportes.reporte_empleados_general;
import reportes.reporte_incapacidad_general;
import reportes.reporte_permisos_general;
import reportes.reporte_vacaciones_general;
import respaldos.respaldo_excel;
import respaldos.respaldo_pdf;
import respaldos.respaldo_sql;
import ventanas.areas_nuevo;
import ventanas.areas_tabla;
import ventanas.cargos_nuevo;
import ventanas.cargos_tabla;
import ventanas.derechos_autor;
import ventanas.empleado_nuevo;
import ventanas.empleado_tabla;
import ventanas.incapacidad_laboral_nuevo;
import ventanas.incapacidad_laboral_tabla;
import ventanas.info_sistema;
import ventanas.login_principal;
import ventanas.usuario_nuevo;
import ventanas.usuario_tabla;
import ventanas.permiso_AL_nuevo;
import ventanas.permiso_AL_tabla;
import ventanas.roles_nuevo;
import ventanas.roles_tabla;
import ventanas.vacaciones_nuevo;
import ventanas.vacaciones_tabla;

import java.awt.Window.Type;



@SuppressWarnings("serial")
public class menu_principal extends JFrame{
	ImageIcon icono_fotografia = new ImageIcon("src/imagenes/logoTC.jpeg");
	public JLabel lblfoto;
	public JLabel relojFechaLabel;
	private JLabel lblBienvenido;
	public JMenu menu_empleados;
	public JMenu menu_permisos;
	public JMenu menu_incapacidades;
	public JMenu menu_vacaciones;
	public JMenu menu_cargos;
	public JMenu menu_roles;
	public JMenu menu_respaldos;
	public JMenu menu_usuarios;
	public JMenu menu_areas;
	public JMenu menu_ayuda;
	public JMenu menu_reportes;
	public JMenu menu_salir;
	
	
	public menu_principal() {
		setType(Type.UTILITY);
		setResizable(false);
		getContentPane().setBackground(SystemColor.text);
		getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
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
		
	
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(UIManager.getColor("Button.light"));
		menuBar.setBounds(0, 0, 1036, 46);
		getContentPane().add(menuBar);
		
		menu_empleados = new JMenu("Empleados");
		menu_empleados.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(menu_empleados);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Tabla de registros de empleados");
		mntmNewMenuItem.setBackground(UIManager.getColor("Button.highlight"));
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				empleado_tabla tabla = new empleado_tabla();
		 		tabla.setVisible(true);
		 		tabla.setLocationRelativeTo(null);
				tabla.construirTabla();
				
				dispose();
			}
		});
		menu_empleados.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Agregar un nuevo empleado");
		mntmNewMenuItem_1.setBackground(UIManager.getColor("Button.highlight"));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				empleado_nuevo nuevo = new empleado_nuevo();
				nuevo.setVisible(true);
				nuevo.setLocationRelativeTo(null);
				nuevo.btnactualizar.setVisible(false);
				nuevo.txtidOriginal.setVisible(false);
				nuevo.chxeditar.setVisible(false);
				dispose();
				
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menu_empleados.add(mntmNewMenuItem_1);
		
		menu_permisos = new JMenu("Permisos");
		menu_permisos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(menu_permisos);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Tabla de registros de permisos por ausencia laboral");
		mntmNewMenuItem_2.setBackground(UIManager.getColor("Button.highlight"));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				permiso_AL_tabla tabla = new permiso_AL_tabla();
				tabla.setVisible(true);
				tabla.setLocationRelativeTo(null);
				tabla.construirTabla();
				dispose();
			}
			
		});
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menu_permisos.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Agregar un nuevo permiso por ausencia laboral");
		mntmNewMenuItem_3.setBackground(UIManager.getColor("Button.highlight"));
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				permiso_AL_nuevo ausenciaLaboral = new permiso_AL_nuevo();
				ausenciaLaboral.setVisible(true);
				ausenciaLaboral.setLocationRelativeTo(null);
				ausenciaLaboral.btnactualizar.setVisible(false);
				ausenciaLaboral.chxeditar.setVisible(false);
				dispose();
			}
		});
		menu_permisos.add(mntmNewMenuItem_3);
		
		menu_incapacidades = new JMenu("Incapacidades");
		menu_incapacidades.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(menu_incapacidades);
		
		JMenuItem mntmNewMenuItem_8_2 = new JMenuItem("Tabla de registros de incapacidad temporal");
		mntmNewMenuItem_8_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incapacidad_laboral_tabla tabla = new incapacidad_laboral_tabla();
				tabla.setVisible(true);
				tabla.setLocationRelativeTo(null);
				tabla.construirTabla();
				dispose();
			}
		});
		mntmNewMenuItem_8_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_2.setBackground(Color.WHITE);
		menu_incapacidades.add(mntmNewMenuItem_8_2);
		
		JMenuItem mntmNewMenuItem_8_2_1 = new JMenuItem("Agregar una nueva incapacidad temporal");
		mntmNewMenuItem_8_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incapacidad_laboral_nuevo nuevo = new incapacidad_laboral_nuevo();
				nuevo.setVisible(true);
				nuevo.setLocationRelativeTo(null);
				nuevo.btnactualizar.setVisible(false);
				nuevo.chxeditar.setVisible(false);
				dispose();
			}
		});
		mntmNewMenuItem_8_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_2_1.setBackground(Color.WHITE);
		menu_incapacidades.add(mntmNewMenuItem_8_2_1);
		
		menu_vacaciones = new JMenu("Vacaciones");
		menu_vacaciones.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(menu_vacaciones);
		
		JMenuItem mntmNewMenuItem_8_2_2 = new JMenuItem("Tabla de registros de vacaciones");
		mntmNewMenuItem_8_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vacaciones_tabla tabla = new vacaciones_tabla();
				tabla.setVisible(true);
				tabla.setLocationRelativeTo(null);
				tabla.construirTabla();
				dispose();
			}
		});
		mntmNewMenuItem_8_2_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_2_2.setBackground(Color.WHITE);
		menu_vacaciones.add(mntmNewMenuItem_8_2_2);
		
		JMenuItem mntmNewMenuItem_8_2_1_1 = new JMenuItem("Agregar una nuevo registro de vacaciones");
		mntmNewMenuItem_8_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vacaciones_nuevo nuevo = new vacaciones_nuevo();
				nuevo.setVisible(true);
				nuevo.setLocationRelativeTo(null);
				nuevo.btnactualizar.setVisible(false);
				nuevo.chxeditar.setVisible(false);
				nuevo.chxeditar.setVisible(false);
				nuevo.lblmensaje.setVisible(false);
				dispose();
			}
		});
		mntmNewMenuItem_8_2_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_2_1_1.setBackground(Color.WHITE);
		menu_vacaciones.add(mntmNewMenuItem_8_2_1_1);
		
		menu_cargos = new JMenu("Cargos");
		menu_cargos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(menu_cargos);
		
		JMenuItem mntmNewMenuItem_2_1 = new JMenuItem("Tabla de registros de cargos");
		mntmNewMenuItem_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargos_tabla t = new cargos_tabla();
				t.setVisible(true);
				t.setLocationRelativeTo(null);
				t.construirTabla();
				dispose();
			}
		});
		mntmNewMenuItem_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_2_1.setBackground(Color.WHITE);
		menu_cargos.add(mntmNewMenuItem_2_1);
		
		JMenuItem mntmNewMenuItem_3_1 = new JMenuItem("Agregar un nuevo cargo");
		mntmNewMenuItem_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargos_nuevo c = new cargos_nuevo();
				c.setVisible(true);
				c.setLocationRelativeTo(null);
				c.btnactualizar.setVisible(false);
				c.chxeditar.setVisible(false);
				c.chxeditar.setVisible(false);
				dispose();
			}
		});
		mntmNewMenuItem_3_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_3_1.setBackground(Color.WHITE);
		menu_cargos.add(mntmNewMenuItem_3_1);
		
		menu_areas = new JMenu("Áreas");
		menu_areas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(menu_areas);
		
		JMenuItem mntmNewMenuItem_2_1_1 = new JMenuItem("Tabla de registros de áreas");
		mntmNewMenuItem_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				areas_tabla t = new areas_tabla();
				t.setVisible(true);
				t.setLocationRelativeTo(null);
				t.construirTabla();
				dispose();
			}
		});
		mntmNewMenuItem_2_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_2_1_1.setBackground(Color.WHITE);
		menu_areas.add(mntmNewMenuItem_2_1_1);
		
		JMenuItem mntmNewMenuItem_3_1_1 = new JMenuItem("Agregar una nueva área");
		mntmNewMenuItem_3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				areas_nuevo n = new areas_nuevo();
				n.setVisible(true);
				n.setLocationRelativeTo(null);
				n.chxeditar.setVisible(false);
				dispose();
			}
		});
		mntmNewMenuItem_3_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_3_1_1.setBackground(Color.WHITE);
		menu_areas.add(mntmNewMenuItem_3_1_1);
		
		menu_reportes = new JMenu("Reportes");
		menu_reportes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(menu_reportes);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Reporte de empleados general");
		mntmNewMenuItem_8.setBackground(UIManager.getColor("Button.highlight"));
		mntmNewMenuItem_8.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporte_empleados_general rp = new reporte_empleados_general();
				rp.generarReporteEmpleadosCompleto();
			}
		});
		menu_reportes.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_8_1 = new JMenuItem("Reporte de empleados personalizado");
		mntmNewMenuItem_8_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporte_empleado_especial especial = new reporte_empleado_especial();
				especial.setVisible(true);
				especial.setLocationRelativeTo(null);
				dispose();
			}
		});
		
		JMenuItem mntmNewMenuItem_8_1_1 = new JMenuItem("Reporte de empleados activos");
		mntmNewMenuItem_8_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporte_empleados_activos reporte = new reporte_empleados_activos();
		        reporte.generarReporteEmpleadosActivos();
			}
		});
		mntmNewMenuItem_8_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_1_1.setBackground(Color.WHITE);
		menu_reportes.add(mntmNewMenuItem_8_1_1);
		mntmNewMenuItem_8_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_1.setBackground(Color.WHITE);
		menu_reportes.add(mntmNewMenuItem_8_1);
		
		JMenuItem mntmNewMenuItem_8_3 = new JMenuItem("Reporte de permisos A.L. general");
		mntmNewMenuItem_8_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporte_permisos_general g = new reporte_permisos_general();
				g.generarReportePermisos();
			}
		});
		mntmNewMenuItem_8_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_3.setBackground(Color.WHITE);
		menu_reportes.add(mntmNewMenuItem_8_3);
		
		JMenuItem mntmNewMenuItem_8_4 = new JMenuItem("Reporte de incapacidades general");
		mntmNewMenuItem_8_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporte_incapacidad_general i = new reporte_incapacidad_general();
				i.generarReporteIncapacidades();
			}
		});
		mntmNewMenuItem_8_4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_4.setBackground(Color.WHITE);
		menu_reportes.add(mntmNewMenuItem_8_4);
		
		JMenuItem mntmNewMenuItem_8_4_1 = new JMenuItem("Reporte de vacaciones general");
		mntmNewMenuItem_8_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporte_vacaciones_general v = new reporte_vacaciones_general();
				v.generarReporteVacaciones();
			}
		});
		mntmNewMenuItem_8_4_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_4_1.setBackground(Color.WHITE);
		menu_reportes.add(mntmNewMenuItem_8_4_1);
		
		menu_respaldos = new JMenu("Respaldos");
		menu_respaldos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(menu_respaldos);
		
		JMenuItem mntmNewMenuItem_5_1 = new JMenuItem("Exportar respaldo en script MySQL\r\n");
		mntmNewMenuItem_5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				respaldo_sql r = new respaldo_sql();
				r.generarRespaldo();
			}
		});
		
		JMenuItem mntmNewMenuItem_5_1_1 = new JMenuItem("Exportar respaldo en archivo PDF\r\n");
		mntmNewMenuItem_5_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				respaldo_pdf p = new respaldo_pdf();
				p.generarReporteCompleto();
			}
		});
		mntmNewMenuItem_5_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_5_1_1.setBackground(Color.WHITE);
		menu_respaldos.add(mntmNewMenuItem_5_1_1);
		mntmNewMenuItem_5_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_5_1.setBackground(Color.WHITE);
		menu_respaldos.add(mntmNewMenuItem_5_1);
		
		menu_usuarios = new JMenu("Usuarios");
		menu_usuarios.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(menu_usuarios);
		
		JMenuItem mntmNewMenuItem_6_1 = new JMenuItem("Tabla de registros de usuarios");
		mntmNewMenuItem_6_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuario_tabla t = new usuario_tabla();
				t.setVisible(true);
				t.setLocationRelativeTo(null);
				t.construirTabla();
				dispose();
			}
		});
		mntmNewMenuItem_6_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_6_1.setBackground(Color.WHITE);
		menu_usuarios.add(mntmNewMenuItem_6_1);
		
		JMenuItem mntmNewMenuItem_4_1 = new JMenuItem("Agregar un nuevo usuario");
		mntmNewMenuItem_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuario_nuevo registro = new usuario_nuevo();
				registro.setVisible(true);
				registro.setLocationRelativeTo(null);
				dispose();
				registro.btnregresar_login.setVisible(false);
				registro.btnactualizar.setVisible(false);	
				registro.chkeditar.setVisible(false);
			}
		});
		mntmNewMenuItem_4_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_4_1.setBackground(Color.WHITE);
		menu_usuarios.add(mntmNewMenuItem_4_1);
		
		JMenuItem mntmNewMenuItem_4_1_1_1 = new JMenuItem("Tabla de roles y permisos para usuarios");
		mntmNewMenuItem_4_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roles_tabla tabla = new roles_tabla();
				tabla.setVisible(true);
				tabla.setLocationRelativeTo(null);
				tabla.construirTabla();
				dispose();
			}
		});
		mntmNewMenuItem_4_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_4_1_1_1.setBackground(Color.WHITE);
		menu_usuarios.add(mntmNewMenuItem_4_1_1_1);
		
		JMenuItem mntmNewMenuItem_4_1_1 = new JMenuItem("Agregar roles y permisos para usuarios");
		mntmNewMenuItem_4_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roles_nuevo r = new roles_nuevo();
				r.setVisible(true);
				r.setLocationRelativeTo(null);
				r.btnactualizar.setVisible(false);
				r.chxeditar.setVisible(false);
				dispose();
			}
		});
		mntmNewMenuItem_4_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_4_1_1.setBackground(Color.WHITE);
		menu_usuarios.add(mntmNewMenuItem_4_1_1);
		
		menu_ayuda = new JMenu("Ayuda");
		menu_ayuda.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(menu_ayuda);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Manual de usuarioC");
		mntmNewMenuItem_5.setBackground(UIManager.getColor("Button.highlight"));
		mntmNewMenuItem_5.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menu_ayuda.add(mntmNewMenuItem_5);
		
		JMenu menu_acerca = new JMenu("Acerca de");
		menu_acerca.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(menu_acerca);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Información sobre el sistema");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				info_sistema info = new info_sistema();
				info.setVisible(true);
				info.setLocationRelativeTo(null);
				dispose();
			}
		});
		mntmNewMenuItem_6.setBackground(UIManager.getColor("Button.highlight"));
		mntmNewMenuItem_6.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menu_acerca.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Derechos de autor");
		mntmNewMenuItem_4.setBackground(UIManager.getColor("Button.highlight"));
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				derechos_autor derechos = new derechos_autor();
				derechos.setVisible(true);
				derechos.setLocationRelativeTo(null);
				dispose();
				
			}
		});
		menu_acerca.add(mntmNewMenuItem_4);
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		menu_salir = new JMenu("Salir");
		menu_salir.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(menu_salir);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Salir del sistema");
		mntmNewMenuItem_7.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrar_ventana();
			}
		});
		
		JMenuItem mntmNewMenuItem_7_1 = new JMenuItem("Cerrar sesión");
		mntmNewMenuItem_7_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrar_sesion();				
			}
		});
		mntmNewMenuItem_7_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_7_1.setBackground(Color.WHITE);
		menu_salir.add(mntmNewMenuItem_7_1);
		mntmNewMenuItem_7.setBackground(UIManager.getColor("Button.highlight"));
		menu_salir.add(mntmNewMenuItem_7);
		
		JLabel lblNewLabel = new JLabel("Control de empleados");
		lblNewLabel.setBounds(0, 193, 504, 58);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 30));
		
		JPanel panel_foto = new JPanel();
		panel_foto.setBackground(SystemColor.menu);
		panel_foto.setBounds(504, 112, 504, 455);
		getContentPane().add(panel_foto);
		panel_foto.setLayout(null);
		
		lblfoto = new JLabel("");
		lblfoto.setBackground(new Color(255, 255, 255));
		lblfoto.setBounds(31, 24, 447, 407);
		panel_foto.add(lblfoto);
		
		lblfoto.setIcon(new ImageIcon(icono_fotografia.getImage().getScaledInstance(lblfoto.getWidth(),
				lblfoto.getHeight(), Image.SCALE_SMOOTH))); //imagen default
		panel_foto.add(lblfoto);
		
		JLabel lblPermisosEIncapacidades = new JLabel("permisos, incapacidades");
		lblPermisosEIncapacidades.setHorizontalAlignment(SwingConstants.CENTER);
		lblPermisosEIncapacidades.setFont(new Font("Georgia", Font.BOLD, 30));
		lblPermisosEIncapacidades.setBounds(0, 236, 504, 58);
		getContentPane().add(lblPermisosEIncapacidades);
		
		JLabel lblTcwsTcwhs = new JLabel("TCWS / TCWHS");
		lblTcwsTcwhs.setHorizontalAlignment(SwingConstants.CENTER);
		lblTcwsTcwhs.setFont(new Font("Georgia", Font.BOLD, 30));
		lblTcwsTcwhs.setBounds(0, 330, 504, 58);
		getContentPane().add(lblTcwsTcwhs);
		
		JLabel lblCopyright = new JLabel("Copyright © 2024 Sistema Control de Empleados, Permisos, Incapacidades y Vacaciones. ");
		lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopyright.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblCopyright.setBounds(10, 491, 487, 35);
		getContentPane().add(lblCopyright);
		
		JLabel lblTodosLosDerechos = new JLabel("Todos los derechos reservados.\r\n");
		lblTodosLosDerechos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTodosLosDerechos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblTodosLosDerechos.setBounds(0, 509, 497, 35);
		getContentPane().add(lblTodosLosDerechos);
		
		relojFechaLabel = new JLabel("");
		relojFechaLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		relojFechaLabel.setBounds(804, 67, 204, 35);
		getContentPane().add(relojFechaLabel);
		
		JLabel lblYVacaciones = new JLabel("y vacaciones");
		lblYVacaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblYVacaciones.setFont(new Font("Georgia", Font.BOLD, 30));
		lblYVacaciones.setBounds(0, 281, 504, 58);
		getContentPane().add(lblYVacaciones);
		
		JLabel lblVersin = new JLabel("Versión 1.1");
		lblVersin.setHorizontalAlignment(SwingConstants.CENTER);
		lblVersin.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblVersin.setBounds(20, 536, 477, 24);
		getContentPane().add(lblVersin);
		
		lblBienvenido = new JLabel("");
		lblBienvenido.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblBienvenido.setBounds(516, 67, 278, 35);
		getContentPane().add(lblBienvenido);
		
		iniciarRelojYFecha();
		
		
    }//class
	
	
	
	public void setNombreUsuario(String nombreUsuario) {
        if (lblBienvenido != null) { // Validar que el JLabel esté inicializado
            lblBienvenido.setText("Bienvenido, " + nombreUsuario);
        } else {
            System.err.println("lblBienvenido no está inicializado");
        }
    }

  

	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					menu_principal frame = new menu_principal();
					
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
	
	
	private void cerrar_sesion() {
	    int opcion = JOptionPane.showConfirmDialog(
	        rootPane,
	        "¿Desea cerrar su sesión?",
	        "Cerrar sesión",
	        JOptionPane.YES_NO_OPTION
	    );

	    if (opcion == JOptionPane.YES_OPTION) {
	        this.dispose();
	        login_principal login = new login_principal(); 
	        login.setVisible(true);
	        login.setLocationRelativeTo(null);
	    }
	}

	
	private void iniciarRelojYFecha() {
		
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fechaActual = formatoFecha.format(new Date());
                String horaActual = formatoHora.format(new Date());
                //relojFechaLabel.setText("Hoy es: " + fechaActual + " | Hora: " + horaActual);
                relojFechaLabel.setText(fechaActual + "       |       " + horaActual);
            }
        });
        timer.start();
    }
	
	
	
	public void cargarPermisos(String nombreUsuario) {
	    consultas_roles consultas = new consultas_roles();
	    
	    // Consulta cada permiso para este usuario
	    boolean empleados = consultas.tienePermiso(nombreUsuario, "permisos_empleados");
	    boolean ausenciaLaboral = consultas.tienePermiso(nombreUsuario, "permisos_ausencia_laboral");
	    boolean incapacidades = consultas.tienePermiso(nombreUsuario, "permisos_incapacidades");
	    boolean vacaciones = consultas.tienePermiso(nombreUsuario, "permisos_vacaciones");
	    boolean cargos = consultas.tienePermiso(nombreUsuario, "permisos_cargos");
	    boolean areas = consultas.tienePermiso(nombreUsuario, "permisos_areas");
	    boolean reportes = consultas.tienePermiso(nombreUsuario, "permisos_reportes");
	    boolean respaldos = consultas.tienePermiso(nombreUsuario, "permisos_respaldos");
	    boolean usuarios = consultas.tienePermiso(nombreUsuario, "permisos_usuarios");

	    // Deshabilitar menús según permisos
	    menu_empleados.setEnabled(empleados);
	    menu_permisos.setEnabled(ausenciaLaboral);
	    menu_incapacidades.setEnabled(incapacidades);
	    menu_vacaciones.setEnabled(vacaciones);
	    menu_cargos.setEnabled(cargos);
	    menu_areas.setEnabled(areas);
	    menu_reportes.setEnabled(reportes);
	    menu_respaldos.setEnabled(respaldos);
	    menu_usuarios.setEnabled(usuarios);
	}

	
	
	

	
	
	

}
