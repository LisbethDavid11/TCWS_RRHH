package principal;

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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;

import consultas.consultas_roles;
import informes.informe_empleado_general;
import informes.informe_empleado_individual;
import reportes.reporte_ausencia_hora_mensual;
import reportes.reporte_ausencias_dia_mensual;
import reportes.reporte_ausencias_injustificadas;
import reportes.reporte_empleado_especial;
import reportes.reporte_empleados_activos;
import reportes.reporte_empleados_general;
import reportes.reporte_incapacidadYpermisos_colaborador;
import reportes.reporte_incapacidad_general;
import reportes.reporte_permisos_general;
import reportes.reporte_vacaciones_general;
import respaldos.respaldo_pdf;
import respaldos.respaldo_sql;
import ventanas.areas_tabla;
import ventanas.asistencia_tabla;
import ventanas.cargos_tabla;
import ventanas.derechos_autor;
import ventanas.empleado_tabla;
import ventanas.incapacidad_laboral_tabla;
import ventanas.info_sistema;
import ventanas.impuntualidades_tabla;
import ventanas.login_principal;
import ventanas.memorandum_nuevo;
import ventanas.memorandum_tabla;
import ventanas.permiso_dia_tabla;
import ventanas.permiso_hora_tabla;
import ventanas.roles_nuevo;
import ventanas.roles_tabla;
import ventanas.usuario_nuevo;
import ventanas.usuario_tabla;
import ventanas.vacaciones_tabla;



@SuppressWarnings("serial")
public class menu_principal extends JFrame{
	
	ImageIcon icono_fotografia = new ImageIcon(getClass().getResource("/imagenes/logoTC.jpeg"));


	public JLabel lblfoto;
	public JLabel relojFechaLabel;
	private JLabel lblBienvenido;
	public JMenu menu_permisos;
	public JMenu menu_incapacidades;
	public JMenu mnOrganizacin;
	public JMenu menu_roles;
	public JMenu mnSistema;
	public JMenu mnInformes;
	public JMenu mnReportesGenerales;
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
		
		mnOrganizacin = new JMenu("Colaboradores");
		mnOrganizacin.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnOrganizacin);
		
		JMenuItem mntmNewMenuItem_2_1 = new JMenuItem("Cargos");
		mntmNewMenuItem_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargos_tabla t = new cargos_tabla();
				t.setVisible(true);
				t.setLocationRelativeTo(null);
				t.construirTabla();
				dispose();
			}
		});
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Colaboradores");
		mnOrganizacin.add(mntmNewMenuItem);
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
		mntmNewMenuItem_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_2_1.setBackground(Color.WHITE);
		mnOrganizacin.add(mntmNewMenuItem_2_1);
		
		JMenuItem mntmNewMenuItem_2_1_1 = new JMenuItem("Áreas");
		mnOrganizacin.add(mntmNewMenuItem_2_1_1);
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
		
		JMenuItem mntmNewMenuItem_8_2_2_2_1 = new JMenuItem("Memorandums");
		mntmNewMenuItem_8_2_2_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memorandum_tabla mem = new memorandum_tabla();
				mem.setVisible(true);
				mem.setLocationRelativeTo(null);
				mem.construirTabla();
				dispose();
			}
		});
		mntmNewMenuItem_8_2_2_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_2_2_2_1.setBackground(Color.WHITE);
		mnOrganizacin.add(mntmNewMenuItem_8_2_2_2_1);
		
		JMenuItem mntmNewMenuItem_8_2_2 = new JMenuItem("Vacaciones");
		mntmNewMenuItem_8_2_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_2_2.setBackground(Color.WHITE);
		mnOrganizacin.add(mntmNewMenuItem_8_2_2);
		
		JMenu menu_vacaciones_2 = new JMenu("Asistencia");
		menu_vacaciones_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(menu_vacaciones_2);
		
		JMenuItem mntmNewMenuItem_8_2_2_2 = new JMenuItem("Asistencia diaria");
		mntmNewMenuItem_8_2_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				asistencia_tabla b = new asistencia_tabla();
				b.setVisible(true);
				b.setLocationRelativeTo(null);
				b.construirTabla();
				b.cargarFechasAsistencia();
				dispose();
			}
		});
		mntmNewMenuItem_8_2_2_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_2_2_2.setBackground(Color.WHITE);
		menu_vacaciones_2.add(mntmNewMenuItem_8_2_2_2);
		
		JMenuItem mntmNewMenuItem_8_2_2_1 = new JMenuItem("Impuntualidades");
		menu_vacaciones_2.add(mntmNewMenuItem_8_2_2_1);
		mntmNewMenuItem_8_2_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				impuntualidades_tabla t = new impuntualidades_tabla();
				t.setLocationRelativeTo(null);
				t.setVisible(true);
				t.construirTabla();
				dispose();
			}
		});
		mntmNewMenuItem_8_2_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_2_2_1.setBackground(Color.WHITE);
		
		menu_permisos = new JMenu("Permisos");
		menu_permisos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(menu_permisos);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Permisos por día");
		mntmNewMenuItem_2.setBackground(UIManager.getColor("Button.highlight"));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				permiso_dia_tabla tabla = new permiso_dia_tabla();
				tabla.setVisible(true);
				tabla.setLocationRelativeTo(null);
				tabla.construirTabla();
				dispose();
			}
			
		});
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menu_permisos.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_2_2 = new JMenuItem("Permisos por hora");
		mntmNewMenuItem_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				permiso_hora_tabla tabla= new permiso_hora_tabla();
				tabla.setVisible(true);
				tabla.setLocationRelativeTo(null);
				tabla.construirTabla();
				dispose();
			}
		});
		mntmNewMenuItem_2_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_2_2.setBackground(Color.WHITE);
		menu_permisos.add(mntmNewMenuItem_2_2);
		
		menu_incapacidades = new JMenu("Incapacidades");
		menu_incapacidades.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(menu_incapacidades);
		
		JMenuItem mntmNewMenuItem_8_2 = new JMenuItem("Incapacidades IHSS");
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
		
		JMenu mnReportes = new JMenu("Reportes individuales");
		mnReportes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnReportes);
		
		JMenuItem mntmNewMenuItem_7_1_1_1 = new JMenuItem("Reporte de Permisos e Incapacidades por colaborador");
		mntmNewMenuItem_7_1_1_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        reporte_incapacidadYpermisos_colaborador reporte = new reporte_incapacidadYpermisos_colaborador();
		        reporte.generarReporte();
		    }
		});
		mntmNewMenuItem_7_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_7_1_1_1.setBackground(Color.WHITE);
		mnReportes.add(mntmNewMenuItem_7_1_1_1);

		
		JMenuItem mntmNewMenuItem_7_2 = new JMenuItem("Reporte de Ausencias injustificadas por colaborador");
		mntmNewMenuItem_7_2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            reporte_ausencias_injustificadas reporte = new reporte_ausencias_injustificadas();
		            reporte.generarReporte();
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Ocurrió un error al generar el reporte: " + ex.getMessage(),
		                    "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		mntmNewMenuItem_7_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_7_2.setBackground(Color.WHITE);
		mnReportes.add(mntmNewMenuItem_7_2);

		
		JMenuItem mntmNewMenuItem_7_1_1 = new JMenuItem("Reporte de Memorándums por colaborador");
		mntmNewMenuItem_7_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_7_1_1.setBackground(Color.WHITE);
		mnReportes.add(mntmNewMenuItem_7_1_1);
		
		JMenuItem mntmNewMenuItem_7_1_1_2 = new JMenuItem("Reporte de Perfil completo de colaborador");
		mntmNewMenuItem_7_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_7_1_1_2.setBackground(Color.WHITE);
		mnReportes.add(mntmNewMenuItem_7_1_1_2);
		
		mnReportesGenerales = new JMenu("Reportes generales");
		mnReportesGenerales.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnReportesGenerales);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Reporte de Colaboradores general");
		mntmNewMenuItem_8.setBackground(UIManager.getColor("Button.highlight"));
		mntmNewMenuItem_8.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporte_empleados_general rp = new reporte_empleados_general();
				rp.generarReporteEmpleadosCompleto();
			}
		});
		mnReportesGenerales.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_8_1 = new JMenuItem("Reporte de Colaboradores personalizado");
		mntmNewMenuItem_8_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporte_empleado_especial especial = new reporte_empleado_especial();
				especial.setVisible(true);
				especial.setLocationRelativeTo(null);
				dispose();
			}
		});
		
		JMenuItem mntmNewMenuItem_8_1_1 = new JMenuItem("Reporte de Colaboradores activos");
		mntmNewMenuItem_8_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporte_empleados_activos reporte = new reporte_empleados_activos();
		        reporte.generarReporteEmpleadosActivos();
			}
		});
		mntmNewMenuItem_8_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_1_1.setBackground(Color.WHITE);
		mnReportesGenerales.add(mntmNewMenuItem_8_1_1);
		mntmNewMenuItem_8_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_1.setBackground(Color.WHITE);
		mnReportesGenerales.add(mntmNewMenuItem_8_1);
		
		JMenuItem mntmNewMenuItem_8_3 = new JMenuItem("Reporte de Permisos por Día general");
		mntmNewMenuItem_8_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporte_permisos_general g = new reporte_permisos_general();
				g.generarReportePermisos();
			}
		});
		mntmNewMenuItem_8_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_3.setBackground(Color.WHITE);
		mnReportesGenerales.add(mntmNewMenuItem_8_3);
		
		JMenuItem mntmNewMenuItem_8_4 = new JMenuItem("Reporte de Incapacidades general");
		mntmNewMenuItem_8_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporte_incapacidad_general i = new reporte_incapacidad_general();
				i.generarReporteIncapacidades();
			}
		});
		
		JMenuItem mntmNewMenuItem_8_3_1 = new JMenuItem("Reporte de Permisos por Día mensual");
		mntmNewMenuItem_8_3_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_3_1.setBackground(Color.WHITE);
		
				mntmNewMenuItem_8_3_1.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        String[] meses = {
				            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
				            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
				        };
		
				        JComboBox<String> comboMeses = new JComboBox<>(meses);
		
				        int opcion = JOptionPane.showConfirmDialog(
				                null,
				                comboMeses,
				                "Seleccione el mes del reporte",
				                JOptionPane.OK_CANCEL_OPTION,
				                JOptionPane.QUESTION_MESSAGE
				        );
		
				        if (opcion == JOptionPane.OK_OPTION) {
				            int mesSeleccionado = comboMeses.getSelectedIndex() + 1; 
				            reporte_ausencias_dia_mensual reporte = new reporte_ausencias_dia_mensual();
				            reporte.generarReportePermisos(mesSeleccionado);
				        }
				    }
				});
				
						mnReportesGenerales.add(mntmNewMenuItem_8_3_1);
		
		JMenuItem mntmNewMenuItem_8_3_2 = new JMenuItem("Reporte de Permisos por Hora general");
		mntmNewMenuItem_8_3_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_3_2.setBackground(Color.WHITE);
		mnReportesGenerales.add(mntmNewMenuItem_8_3_2);
		
		JMenuItem mntmNewMenuItem_8_3_1_1 = new JMenuItem("Reporte de Permisos por Hora mensual");
		mntmNewMenuItem_8_3_1_1.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
			        new reporte_ausencia_hora_mensual().generarReporteHorasMensual();
			    }
		});
		mntmNewMenuItem_8_3_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_3_1_1.setBackground(Color.WHITE);
		mnReportesGenerales.add(mntmNewMenuItem_8_3_1_1);

		
		JMenuItem mntmNewMenuItem_8_4_2 = new JMenuItem("Reporte de Incapacidades mensual");
		mntmNewMenuItem_8_4_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_4_2.setBackground(Color.WHITE);
		mnReportesGenerales.add(mntmNewMenuItem_8_4_2);
		mntmNewMenuItem_8_4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_4.setBackground(Color.WHITE);
		mnReportesGenerales.add(mntmNewMenuItem_8_4);
		
		JMenuItem mntmNewMenuItem_8_4_1 = new JMenuItem("Reporte de Vacaciones general");
		mntmNewMenuItem_8_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporte_vacaciones_general v = new reporte_vacaciones_general();
				v.generarReporteVacaciones();
			}
		});
		mntmNewMenuItem_8_4_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_8_4_1.setBackground(Color.WHITE);
		mnReportesGenerales.add(mntmNewMenuItem_8_4_1);
		
		mnInformes = new JMenu("Informes");
		mnInformes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnInformes);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Informe de Colaborador individual");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 informe_empleado_individual informe = new informe_empleado_individual();
				 informe.generarInformeIndividual();
			}
		});
		mntmNewMenuItem_5.setBackground(UIManager.getColor("Button.highlight"));
		mntmNewMenuItem_5.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnInformes.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_5_2 = new JMenuItem("Informe de Colaboradores general");
		mntmNewMenuItem_5_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				informe_empleado_general general =  new informe_empleado_general();
				general.generarInformeGeneral();
			}
		});
		mntmNewMenuItem_5_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_5_2.setBackground(Color.WHITE);
		mnInformes.add(mntmNewMenuItem_5_2);
		
		mnSistema = new JMenu("Sistema");
		mnSistema.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnSistema);
		
		JMenuItem mntmNewMenuItem_6_1 = new JMenuItem("Usuarios");
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
		mnSistema.add(mntmNewMenuItem_6_1);
		
		JMenuItem mntmNewMenuItem_4_1_1_1 = new JMenuItem("Roles y permisos para usuarios");
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
		mnSistema.add(mntmNewMenuItem_4_1_1_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Derechos de autor");
		mnSistema.add(mntmNewMenuItem_4);
		mntmNewMenuItem_4.setBackground(UIManager.getColor("Button.highlight"));
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				derechos_autor derechos = new derechos_autor();
				derechos.setVisible(true);
				derechos.setLocationRelativeTo(null);
				dispose();
				
			}
		});
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Información sobre el sistema");
		mnSistema.add(mntmNewMenuItem_6);
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
		
		JMenu mnExportar = new JMenu("Exportar");
		mnExportar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnExportar);
		
		JMenuItem mntmNewMenuItem_5_1_1_1 = new JMenuItem("Respaldo en archivo PDF\r\n");
		mntmNewMenuItem_5_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_5_1_1_1.setBackground(Color.WHITE);
		mnExportar.add(mntmNewMenuItem_5_1_1_1);
		
		JMenuItem mntmNewMenuItem_5_1_2 = new JMenuItem("Respaldo en script MySQL\r\n");
		mntmNewMenuItem_5_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNewMenuItem_5_1_2.setBackground(Color.WHITE);
		mnExportar.add(mntmNewMenuItem_5_1_2);
		
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
		
		JLabel lblNewLabel = new JLabel("Sistema");
		lblNewLabel.setBounds(0, 211, 544, 58);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 45));
		
		JPanel panel_foto = new JPanel();
		panel_foto.setBackground(SystemColor.menu);
		panel_foto.setBounds(554, 112, 454, 455);
		getContentPane().add(panel_foto);
		panel_foto.setLayout(null);
		
		lblfoto = new JLabel("");
		lblfoto.setBackground(new Color(255, 255, 255));
		lblfoto.setBounds(28, 22, 395, 407);
		panel_foto.add(lblfoto);
		
		lblfoto.setIcon(new ImageIcon(icono_fotografia.getImage().getScaledInstance(lblfoto.getWidth(),
				lblfoto.getHeight(), Image.SCALE_SMOOTH))); //imagen default
		panel_foto.add(lblfoto);
		
		JLabel lblPermisosEIncapacidades = new JLabel("Recursos Humanos");
		lblPermisosEIncapacidades.setHorizontalAlignment(SwingConstants.CENTER);
		lblPermisosEIncapacidades.setFont(new Font("Georgia", Font.BOLD, 45));
		lblPermisosEIncapacidades.setBounds(0, 265, 544, 58);
		getContentPane().add(lblPermisosEIncapacidades);
		
		JLabel lblTcwsTcwhs = new JLabel("TCWS / TCWHS");
		lblTcwsTcwhs.setHorizontalAlignment(SwingConstants.CENTER);
		lblTcwsTcwhs.setFont(new Font("Georgia", Font.BOLD, 30));
		lblTcwsTcwhs.setBounds(0, 325, 544, 58);
		getContentPane().add(lblTcwsTcwhs);
		
		JLabel lblCopyright = new JLabel("Copyright © 2024 Sistema Recursos Humanos");
		lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopyright.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblCopyright.setBounds(10, 491, 534, 35);
		getContentPane().add(lblCopyright);
		
		JLabel lblTodosLosDerechos = new JLabel("Todos los derechos reservados");
		lblTodosLosDerechos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTodosLosDerechos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblTodosLosDerechos.setBounds(0, 511, 544, 35);
		getContentPane().add(lblTodosLosDerechos);
		
		relojFechaLabel = new JLabel("");
		relojFechaLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		relojFechaLabel.setBounds(804, 67, 204, 35);
		getContentPane().add(relojFechaLabel);
		
		JLabel lblVersin = new JLabel("Versión 1.2");
		lblVersin.setHorizontalAlignment(SwingConstants.CENTER);
		lblVersin.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblVersin.setBounds(0, 536, 544, 24);
		getContentPane().add(lblVersin);
		
		lblBienvenido = new JLabel("");
		lblBienvenido.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblBienvenido.setBounds(526, 67, 268, 35);
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
	
	
	
	@SuppressWarnings("unused")
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
	    //menu_empleados.setEnabled(empleados);
	    menu_permisos.setEnabled(ausenciaLaboral);
	    menu_incapacidades.setEnabled(incapacidades);
	    //menu_vacaciones.setEnabled(vacaciones);
	    mnOrganizacin.setEnabled(cargos);
	    //menu_areas.setEnabled(areas);
	    mnReportesGenerales.setEnabled(reportes);
	    //menu_respaldos.setEnabled(respaldos);
	    mnSistema.setEnabled(usuarios);
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
}
