package ventanas;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import principal.menu_principal;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class info_sistema extends JFrame{
	public info_sistema() {
		
		setResizable(false);
		getContentPane().setBackground(SystemColor.text);
		
		setType(Type.UTILITY);
		getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setBackground(Color.WHITE);
		setForeground(Color.BLACK);
		setBounds(100, 100, 1050, 630);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				cerrar_ventana();
			}
			});
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		panel.setBounds(20, 43, 995, 527);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblDerechosDeAutor = new JLabel("Información del sistema");
		lblDerechosDeAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblDerechosDeAutor.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblDerechosDeAutor.setBounds(0, 10, 995, 41);
		panel.add(lblDerechosDeAutor);
		
		JTextArea txtrCopyright = new JTextArea();
		txtrCopyright.setToolTipText("");
		txtrCopyright.setBackground(SystemColor.control);
		txtrCopyright.setEditable(false);
		txtrCopyright.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		txtrCopyright.setText("El sistema Control de empleados, permisos, incapacidades y vacaciones es una herramienta integral diseñada para el centro educativo TCWS / TCWHS. \r\nSu objetivo es gestionar de forma eficiente todos los aspectos relacionados con el personal, facilitando la administración y el manejo de información\r\nimportante. Entre las principales funcionalidades del sistema se encuentran:\r\n\r\n- Gestión de empleados: El sistema permite registrar, actualizar y visualizar los datos de los empleados, como nombres, apellidos, número de identidad, \r\nfecha de nacimiento, estado civil, cargo, área asignada, teléfono, correo electrónico y otros detalles importantes. También se incluye la gestión de las \r\nfotografías de los empleados.\r\n\r\n- Control de permisos: los empleados pueden solicitar permisos por ausencia laboral. El sistema registra las fechas de inicio y fin del permiso, las horas \r\ninvolucradas, el motivo de la solicitud, generando informes y manteniendo un control preciso sobre el historial depermisos.\r\n\r\n- Registro de incapacidades: para los empleados que presenten incapacidades, el sistema permite registrar las fechas de inicio y fin de la incapacidad,\r\n los diagnósticos médicos y otra información relevante, con el fin de llevar un control adecuado de los días no laborados por este motivo.\r\n\r\n- Administración de vacaciones: el sistema facilita la gestión de las vacaciones de los empleados, registrando los días disponibles,los días ya tomados y \r\nlos días pendientes. Además, se pueden establecer fechas de inicio y fin, así como detalles sobre si las vacaciones han sido pagadas o no.\r\n\r\nEl sistema también incluye funciones para generar informes, filtrar datos, exportación de los datos almacenados en forma de respaldos y garantizar que la\r\ninformación esté organizada de manera clara y accesible. \r\nEsta herramienta fue desarrollada y codificada por Lisbeth Beatriz David Pastrana, estudiante de la carrera de Informática Administrativa de la Universidad\r\nNacional Autónoma de Honduras UNAH campus El Paraíso, como parte de su Práctica Profesional Supervisada en el año 2024. Con este sistema, se busca\r\nmejorar la eficiencia en la gestión de recursos humanos en el centro educativo, reduciendo los errores manuales y agilizando los procesos administrativos.");
		txtrCopyright.setBounds(10, 53, 985, 464);
		panel.add(txtrCopyright);
		
		JButton btnregresar = new JButton("Regresar");
		btnregresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu_principal principal= new menu_principal();
				principal.setVisible(true);
				principal.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnregresar.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnregresar.setBackground(UIManager.getColor("Button.highlight"));
		btnregresar.setBounds(925, 10, 90, 23);
		getContentPane().add(btnregresar);
	}
	
	private void cerrar_ventana() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
}
