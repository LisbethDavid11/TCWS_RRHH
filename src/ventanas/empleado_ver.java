package ventanas;

import java.awt.Window.Type;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class empleado_ver extends JFrame{
	public JTextField txtid_ver;
	public JTextField txtidentidad_ver;
	public JTextField txtnombres_ver;
	public JTextField txtapellidos_ver;
	public JTextField txtnacimiento_ver;
	public JTextField txttelefono_ver;
	public JTextField txtsexo_ver;
	public JTextField txtcivil_ver;
	public JTextField txtcorreo_ver;
	public JTextField txtcuenta_ver;
	public JTextField txtarea_ver;
	public JTextField txtcargo_ver;
	public JTextField txtrenuncia_ver;
	public JTextField txtinicio_ver;
	public JTextField txtruta_ver;
	public JTextArea txadireccion_ver ;
	public JButton btnregresar;
	public JLabel lblfoto_ver;
	public JTextField txtidtabla;
	
	public empleado_ver() {
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JPanel panel_datos = new JPanel();
		panel_datos.setLayout(null);
		panel_datos.setBackground(SystemColor.menu);
		panel_datos.setBounds(27, 92, 985, 475);
		getContentPane().add(panel_datos);
		
		JLabel lblidentidad = new JLabel("Número de identidad");
		lblidentidad.setForeground(Color.GRAY);
		lblidentidad.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblidentidad.setBounds(46, 243, 173, 25);
		panel_datos.add(lblidentidad);
		
		JLabel lblNombresDelEmpleado = new JLabel("Nombres del empleado");
		lblNombresDelEmpleado.setForeground(Color.GRAY);
		lblNombresDelEmpleado.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombresDelEmpleado.setBounds(46, 288, 187, 25);
		panel_datos.add(lblNombresDelEmpleado);
		
		JLabel lblApellidosDelEmpleado = new JLabel("Apellidos del empleado");
		lblApellidosDelEmpleado.setForeground(Color.GRAY);
		lblApellidosDelEmpleado.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblApellidosDelEmpleado.setBounds(46, 333, 187, 25);
		panel_datos.add(lblApellidosDelEmpleado);
		
		JLabel lblsexo = new JLabel("Sexo");
		lblsexo.setForeground(Color.GRAY);
		lblsexo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblsexo.setBounds(509, 26, 200, 25);
		panel_datos.add(lblsexo);
		
		JLabel lblfecha_nacimiento = new JLabel("Fecha de nacimiento");
		lblfecha_nacimiento.setForeground(Color.GRAY);
		lblfecha_nacimiento.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblfecha_nacimiento.setBounds(46, 378, 166, 25);
		panel_datos.add(lblfecha_nacimiento);
		
		JLabel lbltelefono = new JLabel("Número de téfono");
		lbltelefono.setForeground(Color.GRAY);
		lbltelefono.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbltelefono.setBounds(47, 423, 166, 25);
		panel_datos.add(lbltelefono);
		
		JLabel lblarea = new JLabel("Área de trabajo");
		lblarea.setForeground(Color.GRAY);
		lblarea.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblarea.setBounds(509, 303, 166, 25);
		panel_datos.add(lblarea);
		
		JLabel lblcorreo_electronico = new JLabel("Correo electrónico");
		lblcorreo_electronico.setForeground(Color.GRAY);
		lblcorreo_electronico.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblcorreo_electronico.setBounds(509, 223, 166, 25);
		panel_datos.add(lblcorreo_electronico);
		
		JPanel panel_foto = new JPanel();
		panel_foto.setLayout(null);
		panel_foto.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_foto.setBackground(Color.WHITE);
		panel_foto.setBounds(243, 29, 131, 146);
		panel_datos.add(panel_foto);
		
		lblfoto_ver = new JLabel("");
		lblfoto_ver.setToolTipText("Fotografia");
		lblfoto_ver.setForeground(Color.GRAY);
		lblfoto_ver.setBackground(SystemColor.activeCaption);
		lblfoto_ver.setBounds(10, 10, 111, 126);
		panel_foto.add(lblfoto_ver);
		
		JLabel lbldireccion = new JLabel("Dirección exacta");
		lbldireccion.setForeground(Color.GRAY);
		lbldireccion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbldireccion.setBounds(509, 108, 200, 25);
		panel_datos.add(lbldireccion);
		
		JLabel lbltitulo_foto = new JLabel("Fotografía ");
		lbltitulo_foto.setForeground(Color.GRAY);
		lbltitulo_foto.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbltitulo_foto.setBounds(46, 26, 187, 25);
		panel_datos.add(lbltitulo_foto);
		
		JLabel lblIdDelEmpleado = new JLabel("Id del empleado");
		lblIdDelEmpleado.setForeground(Color.GRAY);
		lblIdDelEmpleado.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIdDelEmpleado.setBounds(44, 196, 143, 25);
		panel_datos.add(lblIdDelEmpleado);
		
		txtid_ver = new JTextField();
		txtid_ver.setForeground(Color.BLACK);
		txtid_ver.setBackground(SystemColor.menu);
		txtid_ver.setEditable(false);
		txtid_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtid_ver.setColumns(10);
		txtid_ver.setBounds(243, 198, 131, 25);
		panel_datos.add(txtid_ver);
		
		JLabel lblFechaDeInicio = new JLabel("Fecha de inicio");
		lblFechaDeInicio.setForeground(Color.GRAY);
		lblFechaDeInicio.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFechaDeInicio.setBounds(509, 383, 200, 25);
		panel_datos.add(lblFechaDeInicio);
		
		JLabel lblFechaDeRenuncia = new JLabel("Fecha de renuncia");
		lblFechaDeRenuncia.setForeground(Color.GRAY);
		lblFechaDeRenuncia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFechaDeRenuncia.setBounds(509, 423, 166, 25);
		panel_datos.add(lblFechaDeRenuncia);
		
		JLabel lblcargo = new JLabel("Cargo asignado");
		lblcargo.setForeground(Color.GRAY);
		lblcargo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblcargo.setBounds(509, 343, 148, 25);
		panel_datos.add(lblcargo);
		
		txadireccion_ver = new JTextArea();
		txadireccion_ver.setForeground(Color.BLACK);
		txadireccion_ver.setBackground(SystemColor.menu);
		txadireccion_ver.setEditable(false);
		txadireccion_ver.setWrapStyleWord(true);
		txadireccion_ver.setLineWrap(true);
		txadireccion_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txadireccion_ver.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		txadireccion_ver.setBounds(699, 108, 226, 97);
		panel_datos.add(txadireccion_ver);
		
		JLabel lblcuenta = new JLabel("No. de cuenta bancaria");
		lblcuenta.setForeground(Color.GRAY);
		lblcuenta.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblcuenta.setBounds(509, 263, 178, 25);
		panel_datos.add(lblcuenta);
		
		JLabel lblestado_civil = new JLabel("Estado civil");
		lblestado_civil.setForeground(Color.GRAY);
		lblestado_civil.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblestado_civil.setBounds(509, 68, 200, 25);
		panel_datos.add(lblestado_civil);
		
		txtidentidad_ver = new JTextField();
		txtidentidad_ver.setForeground(Color.BLACK);
		txtidentidad_ver.setBackground(SystemColor.menu);
		txtidentidad_ver.setEditable(false);
		txtidentidad_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtidentidad_ver.setColumns(10);
		txtidentidad_ver.setBounds(243, 243, 228, 25);
		panel_datos.add(txtidentidad_ver);
		
		txtnombres_ver = new JTextField();
		txtnombres_ver.setForeground(Color.BLACK);
		txtnombres_ver.setBackground(SystemColor.menu);
		txtnombres_ver.setEditable(false);
		txtnombres_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtnombres_ver.setColumns(10);
		txtnombres_ver.setBounds(243, 288, 228, 25);
		panel_datos.add(txtnombres_ver);
		
		txtapellidos_ver = new JTextField();
		txtapellidos_ver.setForeground(Color.BLACK);
		txtapellidos_ver.setBackground(SystemColor.menu);
		txtapellidos_ver.setEditable(false);
		txtapellidos_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtapellidos_ver.setColumns(10);
		txtapellidos_ver.setBounds(243, 333, 228, 25);
		panel_datos.add(txtapellidos_ver);
		
		txtnacimiento_ver = new JTextField();
		txtnacimiento_ver.setForeground(Color.BLACK);
		txtnacimiento_ver.setBackground(SystemColor.menu);
		txtnacimiento_ver.setEditable(false);
		txtnacimiento_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtnacimiento_ver.setColumns(10);
		txtnacimiento_ver.setBounds(243, 378, 228, 25);
		panel_datos.add(txtnacimiento_ver);
		
		txttelefono_ver = new JTextField();
		txttelefono_ver.setForeground(Color.BLACK);
		txttelefono_ver.setBackground(SystemColor.menu);
		txttelefono_ver.setEditable(false);
		txttelefono_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txttelefono_ver.setColumns(10);
		txttelefono_ver.setBounds(243, 423, 228, 25);
		panel_datos.add(txttelefono_ver);
		
		txtsexo_ver = new JTextField();
		txtsexo_ver.setForeground(Color.BLACK);
		txtsexo_ver.setBackground(SystemColor.menu);
		txtsexo_ver.setEditable(false);
		txtsexo_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtsexo_ver.setColumns(10);
		txtsexo_ver.setBounds(697, 28, 228, 25);
		panel_datos.add(txtsexo_ver);
		
		txtcivil_ver = new JTextField();
		txtcivil_ver.setForeground(Color.BLACK);
		txtcivil_ver.setBackground(SystemColor.menu);
		txtcivil_ver.setEditable(false);
		txtcivil_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtcivil_ver.setColumns(10);
		txtcivil_ver.setBounds(697, 68, 228, 25);
		panel_datos.add(txtcivil_ver);
		
		txtcorreo_ver = new JTextField();
		txtcorreo_ver.setForeground(Color.BLACK);
		txtcorreo_ver.setBackground(SystemColor.menu);
		txtcorreo_ver.setEditable(false);
		txtcorreo_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtcorreo_ver.setColumns(10);
		txtcorreo_ver.setBounds(697, 223, 228, 25);
		panel_datos.add(txtcorreo_ver);
		
		txtcuenta_ver = new JTextField();
		txtcuenta_ver.setForeground(Color.BLACK);
		txtcuenta_ver.setBackground(SystemColor.menu);
		txtcuenta_ver.setEditable(false);
		txtcuenta_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtcuenta_ver.setColumns(10);
		txtcuenta_ver.setBounds(697, 263, 228, 25);
		panel_datos.add(txtcuenta_ver);
		
		txtarea_ver = new JTextField();
		txtarea_ver.setForeground(Color.BLACK);
		txtarea_ver.setBackground(SystemColor.menu);
		txtarea_ver.setEditable(false);
		txtarea_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtarea_ver.setColumns(10);
		txtarea_ver.setBounds(699, 303, 228, 25);
		panel_datos.add(txtarea_ver);
		
		txtcargo_ver = new JTextField();
		txtcargo_ver.setForeground(Color.BLACK);
		txtcargo_ver.setBackground(SystemColor.menu);
		txtcargo_ver.setEditable(false);
		txtcargo_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtcargo_ver.setColumns(10);
		txtcargo_ver.setBounds(697, 343, 228, 25);
		panel_datos.add(txtcargo_ver);
		
		txtrenuncia_ver = new JTextField();
		txtrenuncia_ver.setForeground(Color.BLACK);
		txtrenuncia_ver.setBackground(SystemColor.menu);
		txtrenuncia_ver.setEditable(false);
		txtrenuncia_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtrenuncia_ver.setColumns(10);
		txtrenuncia_ver.setBounds(695, 423, 228, 25);
		panel_datos.add(txtrenuncia_ver);
		
		txtinicio_ver = new JTextField();
		txtinicio_ver.setForeground(Color.BLACK);
		txtinicio_ver.setBackground(SystemColor.menu);
		txtinicio_ver.setEditable(false);
		txtinicio_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtinicio_ver.setColumns(10);
		txtinicio_ver.setBounds(697, 383, 228, 25);
		panel_datos.add(txtinicio_ver);
		
		JPanel panel_titulo_1 = new JPanel();
		panel_titulo_1.setLayout(null);
		panel_titulo_1.setBackground(SystemColor.menu);
		panel_titulo_1.setBounds(484, 31, 528, 62);
		getContentPane().add(panel_titulo_1);
		
		btnregresar = new JButton("Regresar");
		btnregresar.setForeground(Color.BLACK);
		btnregresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				empleado_tabla tabla = new empleado_tabla();
				tabla.setVisible(true);
				tabla.setLocationRelativeTo(null);
				tabla.construirTabla();
				dispose();
			}
		});
		btnregresar.setToolTipText("Regresar a la tabla");
		btnregresar.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnregresar.setBackground(UIManager.getColor("Button.highlight"));
		btnregresar.setBounds(10, 17, 75, 23);
		panel_titulo_1.add(btnregresar);
		
		txtruta_ver = new JTextField();
		txtruta_ver.setBounds(520, 14, -2, 2);
		panel_titulo_1.add(txtruta_ver);
		txtruta_ver.setForeground(SystemColor.activeCaption);
		txtruta_ver.setBackground(SystemColor.menu);
		txtruta_ver.setEditable(false);
		txtruta_ver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtruta_ver.setColumns(10);
		
		JLabel lblNo = new JLabel("No.");
		lblNo.setForeground(Color.GRAY);
		lblNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNo.setBounds(390, 15, 35, 25);
		panel_titulo_1.add(lblNo);
		
		txtidtabla = new JTextField();
		txtidtabla.setForeground(Color.BLACK);
		txtidtabla.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtidtabla.setEditable(false);
		txtidtabla.setColumns(10);
		txtidtabla.setBackground(SystemColor.menu);
		txtidtabla.setBounds(425, 15, 43, 25);
		panel_titulo_1.add(txtidtabla);
		
		JLabel lbltitulo = new JLabel("VER DATOS DEL EMPLEADO");
		lbltitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lbltitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
		lbltitulo.setBackground(new Color(255, 153, 0));
		lbltitulo.setBounds(27, 31, 447, 33);
		getContentPane().add(lbltitulo);
		setBackground(Color.WHITE);
    	setType(Type.UTILITY);
        setResizable(false);
        setBounds(100, 100, 1050, 630);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				cerrar_ventana();
			}
			});
		
		
		
		
	}//class
	
	
	
	private void cerrar_ventana() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
}//end
