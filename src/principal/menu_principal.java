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

import reportes.reporte_empleado_especial;
import reportes.reporte_empleados_activos;
import reportes.reporte_empleados_general;
import reportes.reporte_incapacidad_general;
import reportes.reporte_permisos_general;
import reportes.reporte_vacaciones_general;
import respaldos.respaldo_base_datos;
import ventanas.derechos_autor;
import ventanas.empleado_nuevo;
import ventanas.empleado_tabla;
import ventanas.incapacidad_laboral_nuevo;
import ventanas.incapacidad_laboral_tabla;
import ventanas.info_sistema;
import ventanas.permiso_AL_nuevo;
import ventanas.permiso_AL_tabla;
import ventanas.vacaciones_nuevo;
import ventanas.vacaciones_tabla;

import java.awt.Window.Type;



@SuppressWarnings("serial")
public class menu_principal extends JFrame{
	ImageIcon icono_fotografia = new ImageIcon("src/imagenes/logoTC.jpeg");
	public JLabel lblfoto;
	public JLabel relojFechaLabel;
	
	
	
	
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
		
		JMenu mnNewMenu = new JMenu("Empleados");
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);
		
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
		mnNewMenu.add(mntmNewMenuItem);
		
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
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("Permisos");
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_1);
		
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
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
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
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_2 = new JMenu("Incapacidades");
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_2);
		
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
		mnNewMenu_2.add(mntmNewMenuItem_8_2);
		
		JMenuItem mntmNewMenuItem_8_2_1 = new JMenuItem("Agregar una nueva incapacidad temporal");
		mntmNewMenuItem_8_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incapacidad_laboral_nuevo nuevo = new incapacidad_laboral_nuevo();
				nuevo.setVisible(true);
				nuevo.setLocationRelativeTo(null);
				nuevo.btnactualizar.setVisible(false);
				nuevo.chxeditar.setVisible(false);
				nuevo.btnlimpiar.setVisible(false);
				dispose();
			}
		});
		mntmNewMenuItem_8_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_2_1.setBackground(Color.WHITE);
		mnNewMenu_2.add(mntmNewMenuItem_8_2_1);
		
		JMenu mnNewMenu_2_1 = new JMenu("Vacaciones");
		mnNewMenu_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_2_1);
		
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
		mnNewMenu_2_1.add(mntmNewMenuItem_8_2_2);
		
		JMenuItem mntmNewMenuItem_8_2_1_1 = new JMenuItem("Agregar una nuevo registro de vacaciones");
		mntmNewMenuItem_8_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vacaciones_nuevo nuevo = new vacaciones_nuevo();
				nuevo.setVisible(true);
				nuevo.setLocationRelativeTo(null);
				nuevo.btnactualizar.setVisible(false);
				nuevo.chxeditar.setVisible(false);
				nuevo.chxeditar.setVisible(false);
				dispose();
			}
		});
		mntmNewMenuItem_8_2_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_2_1_1.setBackground(Color.WHITE);
		mnNewMenu_2_1.add(mntmNewMenuItem_8_2_1_1);
		
		JMenu mnNewMenu_3 = new JMenu("Reportes");
		mnNewMenu_3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Reporte de empleados general");
		mntmNewMenuItem_8.setBackground(UIManager.getColor("Button.highlight"));
		mntmNewMenuItem_8.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporte_empleados_general rp = new reporte_empleados_general();
				rp.generarReporteEmpleadosCompleto();
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_8);
		
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
		mnNewMenu_3.add(mntmNewMenuItem_8_1_1);
		mntmNewMenuItem_8_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_1.setBackground(Color.WHITE);
		mnNewMenu_3.add(mntmNewMenuItem_8_1);
		
		JMenuItem mntmNewMenuItem_8_3 = new JMenuItem("Reporte de permisos A.L. general");
		mntmNewMenuItem_8_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporte_permisos_general g = new reporte_permisos_general();
				g.generarReportePermisos();
			}
		});
		mntmNewMenuItem_8_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_3.setBackground(Color.WHITE);
		mnNewMenu_3.add(mntmNewMenuItem_8_3);
		
		JMenuItem mntmNewMenuItem_8_4 = new JMenuItem("Reporte de incapacidades general");
		mntmNewMenuItem_8_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporte_incapacidad_general i = new reporte_incapacidad_general();
				i.generarReporteIncapacidades();
			}
		});
		mntmNewMenuItem_8_4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_4.setBackground(Color.WHITE);
		mnNewMenu_3.add(mntmNewMenuItem_8_4);
		
		JMenuItem mntmNewMenuItem_8_4_1 = new JMenuItem("Reporte de vacaciones general");
		mntmNewMenuItem_8_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporte_vacaciones_general v = new reporte_vacaciones_general();
				v.generarReporteVacaciones();
			}
		});
		mntmNewMenuItem_8_4_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_4_1.setBackground(Color.WHITE);
		mnNewMenu_3.add(mntmNewMenuItem_8_4_1);
		
		JMenu mnNewMenu_5_1 = new JMenu("Respaldos");
		mnNewMenu_5_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_5_1);
		
		JMenuItem mntmNewMenuItem_5_1 = new JMenuItem("Exportar información en script");
		mntmNewMenuItem_5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				respaldo_base_datos r = new respaldo_base_datos();
				r.generarRespaldo();
			}
		});
		mntmNewMenuItem_5_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_5_1.setBackground(Color.WHITE);
		mnNewMenu_5_1.add(mntmNewMenuItem_5_1);
		
		JMenu mnNewMenu_5 = new JMenu("Ayuda");
		mnNewMenu_5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_5);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Manual de usuario");
		mntmNewMenuItem_5.setBackground(UIManager.getColor("Button.highlight"));
		mntmNewMenuItem_5.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnNewMenu_5.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_6 = new JMenu("Acerca de");
		mnNewMenu_6.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_6);
		
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
		mnNewMenu_6.add(mntmNewMenuItem_6);
		
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
		mnNewMenu_6.add(mntmNewMenuItem_4);
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JMenu mnNewMenu_4 = new JMenu("Salir");
		mnNewMenu_4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Salir del sistema");
		mntmNewMenuItem_7.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrar_ventana();
			}
		});
		mntmNewMenuItem_7.setBackground(UIManager.getColor("Button.highlight"));
		mnNewMenu_4.add(mntmNewMenuItem_7);
		
		JLabel lblNewLabel = new JLabel("Control de empleados");
		lblNewLabel.setBounds(0, 193, 497, 58);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 30));
		
		JPanel panel_foto = new JPanel();
		panel_foto.setBackground(SystemColor.menu);
		panel_foto.setBounds(495, 107, 513, 460);
		getContentPane().add(panel_foto);
		panel_foto.setLayout(null);
		
		lblfoto = new JLabel("");
		lblfoto.setBounds(23, 21, 467, 417);
		panel_foto.add(lblfoto);
		
		lblfoto.setIcon(new ImageIcon(icono_fotografia.getImage().getScaledInstance(lblfoto.getWidth(),
				lblfoto.getHeight(), Image.SCALE_SMOOTH))); //imagen default
		panel_foto.add(lblfoto);
		
		JLabel lblPermisosEIncapacidades = new JLabel("permisos, incapacidades");
		lblPermisosEIncapacidades.setHorizontalAlignment(SwingConstants.CENTER);
		lblPermisosEIncapacidades.setFont(new Font("Georgia", Font.BOLD, 30));
		lblPermisosEIncapacidades.setBounds(0, 236, 497, 58);
		getContentPane().add(lblPermisosEIncapacidades);
		
		JLabel lblTcwsTcwhs = new JLabel("TCWS / TCWHS");
		lblTcwsTcwhs.setHorizontalAlignment(SwingConstants.CENTER);
		lblTcwsTcwhs.setFont(new Font("Georgia", Font.BOLD, 30));
		lblTcwsTcwhs.setBounds(0, 330, 497, 58);
		getContentPane().add(lblTcwsTcwhs);
		
		JLabel lblCopyright = new JLabel("Copyright © 2024 Sistema Control de Empleados, Permisos, Incapacidades y Vacaciones. ");
		lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopyright.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblCopyright.setBounds(10, 514, 475, 35);
		getContentPane().add(lblCopyright);
		
		JLabel lblTodosLosDerechos = new JLabel("Todos los derechos reservados.\r\n");
		lblTodosLosDerechos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTodosLosDerechos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblTodosLosDerechos.setBounds(27, 532, 425, 35);
		getContentPane().add(lblTodosLosDerechos);
		
		relojFechaLabel = new JLabel("");
		relojFechaLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		relojFechaLabel.setBounds(812, 51, 196, 46);
		getContentPane().add(relojFechaLabel);
		
		JLabel lblYVacaciones = new JLabel("y vacaciones");
		lblYVacaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblYVacaciones.setFont(new Font("Georgia", Font.BOLD, 30));
		lblYVacaciones.setBounds(0, 281, 497, 58);
		getContentPane().add(lblYVacaciones);
		
		iniciarRelojYFecha();
		
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
}
