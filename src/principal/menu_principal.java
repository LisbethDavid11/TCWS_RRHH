package principal;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import ventanas.derechos_autor;
import ventanas.empleado_tabla;
import ventanas.permiso_ausencia_laboral_nuevo;
import ventanas.permiso_ausencia_laboral_tabla;
import ventanas.empleado_nuevo;
import javax.swing.UIManager;



public class menu_principal extends JFrame{
	ImageIcon icono_fotografia = new ImageIcon("src/imagenes/logoTC.jpeg");
	public JLabel lblfoto;
	
	
	
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
	
	public menu_principal() {
		setResizable(false);
		getContentPane().setBackground(SystemColor.text);
		
		setType(Type.UTILITY);
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
		
		JMenu mnNewMenu = new JMenu("Empleado");
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Registros de empleados");
		mntmNewMenuItem.setBackground(UIManager.getColor("Button.highlight"));
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 13));
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
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Nuevo empleado");
		mntmNewMenuItem_1.setBackground(UIManager.getColor("Button.highlight"));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				empleado_nuevo nuevo = new empleado_nuevo();
				nuevo.setVisible(true);
				nuevo.setLocationRelativeTo(null);
				nuevo.btnactualizar.setVisible(false);
				dispose();
				
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("Permisos");
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Registros de permisos por ausencia laboral");
		mntmNewMenuItem_2.setBackground(UIManager.getColor("Button.highlight"));
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				permiso_ausencia_laboral_tabla tabla = new permiso_ausencia_laboral_tabla();
				tabla.setVisible(true);
				tabla.setLocationRelativeTo(null);
				tabla.construirTabla();
				dispose();
			}
			
		});
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Nuevo permiso por ausencia laboral");
		mntmNewMenuItem_3.setBackground(UIManager.getColor("Button.highlight"));
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				permiso_ausencia_laboral_nuevo ausenciaLaboral = new permiso_ausencia_laboral_nuevo();
				ausenciaLaboral.setVisible(true);
				ausenciaLaboral.setLocationRelativeTo(null);
				ausenciaLaboral.btnactualizar.setVisible(false);
				dispose();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_2 = new JMenu("Incapacidades");
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_2);
		
		JMenu mnNewMenu_3 = new JMenu("Reportes");
		mnNewMenu_3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_3);
		
		JMenu mnNewMenu_5 = new JMenu("Ayuda");
		mnNewMenu_5.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_5);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Manual de usuario");
		mntmNewMenuItem_5.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mnNewMenu_5.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_6 = new JMenu("Acerca de");
		mnNewMenu_6.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_6);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Información sobre el sistema");
		mntmNewMenuItem_6.setBackground(UIManager.getColor("Button.highlight"));
		mntmNewMenuItem_6.setFont(new Font("Segoe UI", Font.PLAIN, 13));
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
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		
		JMenu mnNewMenu_4 = new JMenu("Salir");
		mnNewMenu_4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Salir del sistema");
		mntmNewMenuItem_7.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrar_ventana();
			}
		});
		mntmNewMenuItem_7.setBackground(UIManager.getColor("Button.highlight"));
		mnNewMenu_4.add(mntmNewMenuItem_7);
		
		JLabel lblNewLabel = new JLabel("Control de empleados");
		lblNewLabel.setBounds(49, 206, 373, 58);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 30));
		
		JPanel panel_foto = new JPanel();
		panel_foto.setBackground(SystemColor.menu);
		panel_foto.setBounds(478, 69, 523, 498);
		getContentPane().add(panel_foto);
		panel_foto.setLayout(null);
		
		lblfoto = new JLabel("");
		lblfoto.setBounds(23, 20, 477, 455);
		panel_foto.add(lblfoto);
		
		lblfoto.setIcon(new ImageIcon(icono_fotografia.getImage().getScaledInstance(lblfoto.getWidth(),
				lblfoto.getHeight(), Image.SCALE_SMOOTH))); //imagen default
		panel_foto.add(lblfoto);
		
		JLabel lblPermisosEIncapacidades = new JLabel("permisos e incapacidades");
		lblPermisosEIncapacidades.setHorizontalAlignment(SwingConstants.CENTER);
		lblPermisosEIncapacidades.setFont(new Font("Georgia", Font.BOLD, 30));
		lblPermisosEIncapacidades.setBounds(27, 249, 415, 58);
		getContentPane().add(lblPermisosEIncapacidades);
		
		JLabel lblTcwsTcwhs = new JLabel("TCWS & TCWHS");
		lblTcwsTcwhs.setHorizontalAlignment(SwingConstants.CENTER);
		lblTcwsTcwhs.setFont(new Font("Georgia", Font.BOLD, 30));
		lblTcwsTcwhs.setBounds(27, 294, 415, 58);
		getContentPane().add(lblTcwsTcwhs);
		
		JLabel lblCopyright = new JLabel("Copyright © 2024 Sistema Control de Empleados, Permisos e Incapacidades. ");
		lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopyright.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblCopyright.setBounds(27, 514, 425, 35);
		getContentPane().add(lblCopyright);
		
		JLabel lblTodosLosDerechos = new JLabel("Todos los derechos reservados.\r\n");
		lblTodosLosDerechos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTodosLosDerechos.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		lblTodosLosDerechos.setBounds(27, 532, 425, 35);
		getContentPane().add(lblTodosLosDerechos);
		
    }
        
		
		
	
	
	
	private void cerrar_ventana() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
}