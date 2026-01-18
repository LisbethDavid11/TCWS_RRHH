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
import javax.swing.UIManager;

import principal.menu_principal;

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
		txtrCopyright.setText("\r\nEl Sistema de Recursos Humanos es una aplicación desarrollada para el centro educativo TCWS/TCWHS, diseñada específicamente para la generación\r\ny gestión de reportes administrativos relacionados con el control del personal. Su objetivo principal es organizar, almacenar y administrar eficientemente la\r\ninformación de los colaboradores, así como los procesos de permisos, incapacidades, vacaciones, asistencia y control administrativo.\r\n\r\nEn primer lugar, el sistema permite realizar diversos registros fundamentales, tales como cargos, áreas del centro educativo y colaboradores tanto docentes\r\ncomo administrativos. Posteriormente, a partir de esta información almacenada en la base de datos, se generan reportes detallados que incluyen:\r\ndatos de colaboradores, permisos por ausencia laboral (por días u horas), incapacidades, vacaciones, asistencia diaria, ausencias injustificadas y\r\nmemorándums.\r\n\r\nAsimismo, los reportes pueden filtrarse por fechas, meses o criterios específicos, presentándose de forma organizada y estructurada. De igual manera, se\r\ngeneran en formato PDF para facilitar su visualización, almacenamiento e impresión. Por consiguiente, esta herramienta contribuye a mejorar el control y\r\nseguimiento del recurso humano, proporcionando información clara y confiable que facilita el análisis, el control interno y la toma de decisiones\r\nadministrativas del centro educativo.\r\n\r\nEsta herramienta fue desarrollada y codificada por Lisbeth Beatriz David Pastrana, estudiante de la Licenciatura en Informática Administrativa de la\r\nUniversidad Nacional Autónoma de Honduras (UNAH), campus El Paraíso, como parte de su Práctica Profesional Supervisada en 2024.\r\n\r\nEn consecuencia, su propósito es mejorar la eficiencia en la gestión de recursos humanos, reducir errores manuales y agilizar los procesos administrativos\r\ndel centro educativo.");
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
