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

public class permiso_ausencia_laboral_ver extends JFrame{
	public JTextField txtid_ver;
	public JTextField txtidentidad_ver;
	public JTextField txtnombres_ver;
	public JTextField txtapellidos_ver;
	public JTextField txttelefono_ver;
	public JTextField txtdesdeHora_ver;
	public JTextField txthastaHora_ver;
	public JTextField txtcorreo_ver;
	public JTextField txtarea_ver;
	public JTextField txtcargo_ver;
	public JTextField txttotalDias_ver;
	public JTextField txtfinalFecha_ver;
	public JTextField txtruta_ver;
	public JButton btnregresar;
	public JTextField txttotalHoras_ver;
	public JTextField txtfechaRecibe_ver;
	public JTextField txtnombresRecibe_ver;
	public JTextField txtinicioFecha_ver;
	public JTextArea txamotivo_ver;
	public JTextField txtid_permisoVer;
	
	public permiso_ausencia_laboral_ver() {
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
		lblidentidad.setBounds(47, 80, 173, 25);
		panel_datos.add(lblidentidad);
		
		JLabel lblNombresDelEmpleado = new JLabel("Nombres del empleado");
		lblNombresDelEmpleado.setForeground(Color.GRAY);
		lblNombresDelEmpleado.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNombresDelEmpleado.setBounds(47, 130, 187, 25);
		panel_datos.add(lblNombresDelEmpleado);
		
		JLabel lblApellidosDelEmpleado = new JLabel("Apellidos del empleado");
		lblApellidosDelEmpleado.setForeground(Color.GRAY);
		lblApellidosDelEmpleado.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblApellidosDelEmpleado.setBounds(47, 180, 187, 25);
		panel_datos.add(lblApellidosDelEmpleado);
		
		JLabel lbldesde = new JLabel("Desde la hora");
		lbldesde.setForeground(Color.GRAY);
		lbldesde.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbldesde.setBounds(509, 30, 166, 25);
		panel_datos.add(lbldesde);
		
		JLabel lbltelefono = new JLabel("Número de téfono");
		lbltelefono.setForeground(Color.GRAY);
		lbltelefono.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbltelefono.setBounds(47, 230, 166, 25);
		panel_datos.add(lbltelefono);
		
		JLabel lblarea = new JLabel("Área de trabajo");
		lblarea.setForeground(Color.GRAY);
		lblarea.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblarea.setBounds(47, 330, 166, 25);
		panel_datos.add(lblarea);
		
		JLabel lblcorreo_electronico = new JLabel("Correo electrónico");
		lblcorreo_electronico.setForeground(Color.GRAY);
		lblcorreo_electronico.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblcorreo_electronico.setBounds(47, 280, 166, 25);
		panel_datos.add(lblcorreo_electronico);
		
		JLabel lbldireccion = new JLabel("Con un total de ");
		lbldireccion.setForeground(Color.GRAY);
		lbldireccion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbldireccion.setBounds(509, 110, 178, 25);
		panel_datos.add(lbldireccion);
		
		JLabel lblIdDelEmpleado = new JLabel("Id del empleado");
		lblIdDelEmpleado.setForeground(Color.GRAY);
		lblIdDelEmpleado.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblIdDelEmpleado.setBounds(47, 30, 143, 25);
		panel_datos.add(lblIdDelEmpleado);
		
		txtid_ver = new JTextField();
		txtid_ver.setForeground(Color.BLACK);
		txtid_ver.setBackground(SystemColor.menu);
		txtid_ver.setEditable(false);
		txtid_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtid_ver.setColumns(10);
		txtid_ver.setBounds(246, 30, 131, 25);
		panel_datos.add(txtid_ver);
		
		JLabel lblFechaDeInicio = new JLabel("Fecha de inicio");
		lblFechaDeInicio.setForeground(Color.GRAY);
		lblFechaDeInicio.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFechaDeInicio.setBounds(509, 240, 166, 25);
		panel_datos.add(lblFechaDeInicio);
		
		JLabel lblFechaDeRenuncia = new JLabel("Fecha de final");
		lblFechaDeRenuncia.setForeground(Color.GRAY);
		lblFechaDeRenuncia.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFechaDeRenuncia.setBounds(509, 280, 166, 25);
		panel_datos.add(lblFechaDeRenuncia);
		
		JLabel lblcargo = new JLabel("Cargo asignado");
		lblcargo.setForeground(Color.GRAY);
		lblcargo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblcargo.setBounds(47, 380, 148, 25);
		panel_datos.add(lblcargo);
		
		JLabel lblcuenta = new JLabel("Motivo de la ausencia");
		lblcuenta.setForeground(Color.GRAY);
		lblcuenta.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblcuenta.setBounds(509, 150, 178, 25);
		panel_datos.add(lblcuenta);
		
		JLabel lblestado_civil = new JLabel("Hasta la hora");
		lblestado_civil.setForeground(Color.GRAY);
		lblestado_civil.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblestado_civil.setBounds(509, 70, 154, 25);
		panel_datos.add(lblestado_civil);
		
		txtidentidad_ver = new JTextField();
		txtidentidad_ver.setForeground(Color.BLACK);
		txtidentidad_ver.setBackground(SystemColor.menu);
		txtidentidad_ver.setEditable(false);
		txtidentidad_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtidentidad_ver.setColumns(10);
		txtidentidad_ver.setBounds(246, 80, 228, 25);
		panel_datos.add(txtidentidad_ver);
		
		txtnombres_ver = new JTextField();
		txtnombres_ver.setForeground(Color.BLACK);
		txtnombres_ver.setBackground(SystemColor.menu);
		txtnombres_ver.setEditable(false);
		txtnombres_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtnombres_ver.setColumns(10);
		txtnombres_ver.setBounds(246, 130, 228, 25);
		panel_datos.add(txtnombres_ver);
		
		txtapellidos_ver = new JTextField();
		txtapellidos_ver.setForeground(Color.BLACK);
		txtapellidos_ver.setBackground(SystemColor.menu);
		txtapellidos_ver.setEditable(false);
		txtapellidos_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtapellidos_ver.setColumns(10);
		txtapellidos_ver.setBounds(246, 180, 228, 25);
		panel_datos.add(txtapellidos_ver);
		
		txttelefono_ver = new JTextField();
		txttelefono_ver.setForeground(Color.BLACK);
		txttelefono_ver.setBackground(SystemColor.menu);
		txttelefono_ver.setEditable(false);
		txttelefono_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txttelefono_ver.setColumns(10);
		txttelefono_ver.setBounds(246, 230, 228, 25);
		panel_datos.add(txttelefono_ver);
		
		txtdesdeHora_ver = new JTextField();
		txtdesdeHora_ver.setForeground(Color.BLACK);
		txtdesdeHora_ver.setBackground(SystemColor.menu);
		txtdesdeHora_ver.setEditable(false);
		txtdesdeHora_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtdesdeHora_ver.setColumns(10);
		txtdesdeHora_ver.setBounds(697, 30, 131, 25);
		panel_datos.add(txtdesdeHora_ver);
		
		txthastaHora_ver = new JTextField();
		txthastaHora_ver.setForeground(Color.BLACK);
		txthastaHora_ver.setBackground(SystemColor.menu);
		txthastaHora_ver.setEditable(false);
		txthastaHora_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txthastaHora_ver.setColumns(10);
		txthastaHora_ver.setBounds(697, 70, 131, 25);
		panel_datos.add(txthastaHora_ver);
		
		txtcorreo_ver = new JTextField();
		txtcorreo_ver.setForeground(Color.BLACK);
		txtcorreo_ver.setBackground(SystemColor.menu);
		txtcorreo_ver.setEditable(false);
		txtcorreo_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtcorreo_ver.setColumns(10);
		txtcorreo_ver.setBounds(246, 280, 228, 25);
		panel_datos.add(txtcorreo_ver);
		
		txtarea_ver = new JTextField();
		txtarea_ver.setForeground(Color.BLACK);
		txtarea_ver.setBackground(SystemColor.menu);
		txtarea_ver.setEditable(false);
		txtarea_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtarea_ver.setColumns(10);
		txtarea_ver.setBounds(246, 330, 228, 25);
		panel_datos.add(txtarea_ver);
		
		txtcargo_ver = new JTextField();
		txtcargo_ver.setForeground(Color.BLACK);
		txtcargo_ver.setBackground(SystemColor.menu);
		txtcargo_ver.setEditable(false);
		txtcargo_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtcargo_ver.setColumns(10);
		txtcargo_ver.setBounds(246, 380, 228, 25);
		panel_datos.add(txtcargo_ver);
		
		txttotalDias_ver = new JTextField();
		txttotalDias_ver.setForeground(Color.BLACK);
		txttotalDias_ver.setBackground(SystemColor.menu);
		txttotalDias_ver.setEditable(false);
		txttotalDias_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txttotalDias_ver.setColumns(10);
		txttotalDias_ver.setBounds(697, 320, 133, 25);
		panel_datos.add(txttotalDias_ver);
		
		txtfinalFecha_ver = new JTextField();
		txtfinalFecha_ver.setForeground(Color.BLACK);
		txtfinalFecha_ver.setBackground(SystemColor.menu);
		txtfinalFecha_ver.setEditable(false);
		txtfinalFecha_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtfinalFecha_ver.setColumns(10);
		txtfinalFecha_ver.setBounds(697, 280, 131, 25);
		panel_datos.add(txtfinalFecha_ver);
		
		txttotalHoras_ver = new JTextField();
		txttotalHoras_ver.setForeground(Color.BLACK);
		txttotalHoras_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txttotalHoras_ver.setEditable(false);
		txttotalHoras_ver.setColumns(10);
		txttotalHoras_ver.setBackground(SystemColor.menu);
		txttotalHoras_ver.setBounds(697, 110, 131, 25);
		panel_datos.add(txttotalHoras_ver);
		
		JLabel lblHoras = new JLabel("horas");
		lblHoras.setForeground(Color.GRAY);
		lblHoras.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblHoras.setBounds(851, 108, 59, 25);
		panel_datos.add(lblHoras);
		
		txamotivo_ver = new JTextArea();
		txamotivo_ver.setWrapStyleWord(true);
		txamotivo_ver.setLineWrap(true);
		txamotivo_ver.setForeground(Color.BLACK);
		txamotivo_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txamotivo_ver.setEditable(false);
		txamotivo_ver.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		txamotivo_ver.setBackground(SystemColor.menu);
		txamotivo_ver.setBounds(697, 150, 226, 73);
		panel_datos.add(txamotivo_ver);
		
		JLabel lblConUnTotal = new JLabel("Con un total de ");
		lblConUnTotal.setForeground(Color.GRAY);
		lblConUnTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblConUnTotal.setBounds(509, 320, 166, 25);
		panel_datos.add(lblConUnTotal);
		
		JLabel lblDas = new JLabel("días");
		lblDas.setForeground(Color.GRAY);
		lblDas.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDas.setBounds(851, 320, 59, 25);
		panel_datos.add(lblDas);
		
		JLabel lblRecibidoPor = new JLabel("Recibido por");
		lblRecibidoPor.setForeground(Color.GRAY);
		lblRecibidoPor.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRecibidoPor.setBounds(509, 360, 166, 25);
		panel_datos.add(lblRecibidoPor);
		
		JLabel lblFecha = new JLabel("Fecha ");
		lblFecha.setForeground(Color.GRAY);
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFecha.setBounds(509, 400, 166, 25);
		panel_datos.add(lblFecha);
		
		txtfechaRecibe_ver = new JTextField();
		txtfechaRecibe_ver.setForeground(Color.BLACK);
		txtfechaRecibe_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtfechaRecibe_ver.setEditable(false);
		txtfechaRecibe_ver.setColumns(10);
		txtfechaRecibe_ver.setBackground(SystemColor.menu);
		txtfechaRecibe_ver.setBounds(697, 400, 226, 25);
		panel_datos.add(txtfechaRecibe_ver);
		
		txtnombresRecibe_ver = new JTextField();
		txtnombresRecibe_ver.setForeground(Color.BLACK);
		txtnombresRecibe_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtnombresRecibe_ver.setEditable(false);
		txtnombresRecibe_ver.setColumns(10);
		txtnombresRecibe_ver.setBackground(SystemColor.menu);
		txtnombresRecibe_ver.setBounds(697, 360, 224, 25);
		panel_datos.add(txtnombresRecibe_ver);
		
		txtinicioFecha_ver = new JTextField();
		txtinicioFecha_ver.setForeground(Color.BLACK);
		txtinicioFecha_ver.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtinicioFecha_ver.setEditable(false);
		txtinicioFecha_ver.setColumns(10);
		txtinicioFecha_ver.setBackground(SystemColor.menu);
		txtinicioFecha_ver.setBounds(697, 240, 131, 25);
		panel_datos.add(txtinicioFecha_ver);
		
		JPanel panel_titulo_1 = new JPanel();
		panel_titulo_1.setLayout(null);
		panel_titulo_1.setBackground(SystemColor.menu);
		panel_titulo_1.setBounds(484, 31, 528, 62);
		getContentPane().add(panel_titulo_1);
		
		btnregresar = new JButton("Regresar");
		btnregresar.setForeground(Color.BLACK);
		btnregresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				permiso_ausencia_laboral_tabla tabla = new permiso_ausencia_laboral_tabla();
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
		
		txtid_permisoVer = new JTextField();
		txtid_permisoVer.setForeground(Color.BLACK);
		txtid_permisoVer.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtid_permisoVer.setEditable(false);
		txtid_permisoVer.setColumns(10);
		txtid_permisoVer.setBackground(SystemColor.menu);
		txtid_permisoVer.setBounds(441, 18, 35, 25);
		panel_titulo_1.add(txtid_permisoVer);
		
		JLabel lblNo = new JLabel("No.");
		lblNo.setForeground(Color.GRAY);
		lblNo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNo.setBounds(409, 18, 35, 25);
		panel_titulo_1.add(lblNo);
		
		
		JLabel lbltitulo = new JLabel("VER DATOS DEL PERMISO");
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
