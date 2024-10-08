package ventanas;


import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import clases.vacaciones;
import conexion.conexion;
import principal.menu_principal;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class vacaciones_tabla extends JFrame {
    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel modeloTabla;
    private JComboBox<String> cbxbusquedaCargo, cbxbusquedaarea;
    private JDateChooser desde_buscar, hasta_buscar;
    private JTextField txtbuscar;
    private TableRowSorter<DefaultTableModel> trsfiltroCodigo;
    private JLabel lblVacaciones;
    private JPanel panelbotones;
    private JButton btnregresar;
    public JButton btnnuevo;
    public JButton btneliminar;
    

    @SuppressWarnings("unchecked")
	public vacaciones_tabla() {
    	//formulario.btnregresar.setBackground(UIManager.getColor("Button.highlight"));
        getContentPane().setBackground(new Color(255, 255, 255));
        setType(Type.UTILITY);
        setResizable(false);
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
        
        JPanel panel_tabla = new JPanel();
        panel_tabla.setLayout(null);
        panel_tabla.setBackground(SystemColor.menu);
        panel_tabla.setBounds(25, 132, 990, 440);
        getContentPane().add(panel_tabla);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 970, 420);
        panel_tabla.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        JPanel panelbusqueda = new JPanel();
        panelbusqueda.setLayout(null);
        panelbusqueda.setBackground(SystemColor.menu);
        panelbusqueda.setBounds(25, 77, 990, 46);
        getContentPane().add(panelbusqueda);

        txtbuscar = new JTextField();
        txtbuscar.setFont(new Font("Tahoma", Font.PLAIN, 10));
        txtbuscar.setBounds(68, 10, 230, 27);
        panelbusqueda.add(txtbuscar);

        final String placeHolderText = "Id empleado, nombres, apellidos e identidad";

        txtbuscar.setText(placeHolderText);
        txtbuscar.setForeground(Color.GRAY);

        txtbuscar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtbuscar.getText().equals(placeHolderText)) {
                    txtbuscar.setText(""); 
                    txtbuscar.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtbuscar.getText().isEmpty()) {
                    txtbuscar.setText(placeHolderText);  
                    txtbuscar.setForeground(Color.GRAY);  
                }
            }
        });

        txtbuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filtro();
            }
        });

        JLabel lblbuscar = new JLabel("Buscar");
        lblbuscar.setHorizontalAlignment(SwingConstants.LEFT);
        lblbuscar.setForeground(Color.BLACK);
        lblbuscar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblbuscar.setBounds(10, 10, 66, 26);
        panelbusqueda.add(lblbuscar);

        cbxbusquedaCargo = new JComboBox<String>();
        cbxbusquedaCargo.setModel(new DefaultComboBoxModel(new String[] {"Director general", "Director", "Gerente financiero", "Administrador", "Asistente", "Cobros", "Enfermero", "Psicologo", "Supervisor", "Consejero", "Docente", "Docente auxiliar", "Soporte técnico", "Marketing", "Aseo", "Mantenimiento", "Conserje", " "}));
		cbxbusquedaCargo.setSelectedIndex(-1);
        cbxbusquedaCargo.setFont(new Font("Tahoma", Font.BOLD, 11));
        cbxbusquedaCargo.setBounds(365, 12, 111, 26);
        panelbusqueda.add(cbxbusquedaCargo);
        cbxbusquedaCargo.addActionListener(e -> aplicarFiltros());

        JLabel lblCargo = new JLabel("Cargo");
        lblCargo.setHorizontalAlignment(SwingConstants.LEFT);
        lblCargo.setForeground(Color.BLACK);
        lblCargo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblCargo.setBounds(308, 10, 66, 26);
        panelbusqueda.add(lblCargo);

        cbxbusquedaarea = new JComboBox<String>();
        cbxbusquedaarea.setModel(new DefaultComboBoxModel<>(new String[] { "Administrativa", "Financiera", "Pre basica", "Primaria", "Secundaria", "Logistica", "Aseo", "Mantenimiento", " " }));
		cbxbusquedaarea.setSelectedIndex(-1);
        cbxbusquedaarea.setFont(new Font("Tahoma", Font.BOLD, 11));
        cbxbusquedaarea.setBounds(534, 12, 111, 26);
        panelbusqueda.add(cbxbusquedaarea);
        cbxbusquedaarea.addActionListener(e -> aplicarFiltros());

        JLabel lblarea = new JLabel("Área");
        lblarea.setHorizontalAlignment(SwingConstants.LEFT);
        lblarea.setForeground(Color.BLACK);
        lblarea.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblarea.setBounds(486, 10, 56, 26);
        panelbusqueda.add(lblarea);

        desde_buscar = new JDateChooser();
        desde_buscar.setDateFormatString("dd-MM-yy");
        desde_buscar.setBounds(707, 10, 101, 27);
        panelbusqueda.add(desde_buscar);
        desde_buscar.getDateEditor().addPropertyChangeListener(evt -> aplicarFiltros());

        hasta_buscar = new JDateChooser();
        hasta_buscar.setDateFormatString("dd-MM-yy");
        hasta_buscar.setBounds(879, 10, 101, 27);
        panelbusqueda.add(hasta_buscar);
        hasta_buscar.getDateEditor().addPropertyChangeListener(evt -> aplicarFiltros());

        JLabel lblDesde = new JLabel("Desde");
        lblDesde.setHorizontalAlignment(SwingConstants.LEFT);
        lblDesde.setForeground(Color.BLACK);
        lblDesde.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblDesde.setBounds(655, 10, 56, 26);
        panelbusqueda.add(lblDesde);

        JLabel lblHasta = new JLabel("Hasta");
        lblHasta.setHorizontalAlignment(SwingConstants.LEFT);
        lblHasta.setForeground(Color.BLACK);
        lblHasta.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblHasta.setBounds(829, 10, 56, 26);
        panelbusqueda.add(lblHasta);
        
        lblVacaciones = new JLabel("VACACIONES");
        lblVacaciones.setHorizontalAlignment(SwingConstants.LEFT);
        lblVacaciones.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblVacaciones.setBounds(25, 30, 517, 26);
        getContentPane().add(lblVacaciones);
        
        panelbotones = new JPanel();
        panelbotones.setLayout(null);
        panelbotones.setBackground(SystemColor.menu);
        panelbotones.setBounds(552, 21, 463, 56);
        getContentPane().add(panelbotones);
        
        btnregresar = new JButton("Menú");
        btnregresar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		menu_principal menu = new menu_principal();
        		menu.setVisible(true);
        		menu.setLocationRelativeTo(null);
        		dispose();
        	}
        });
        btnregresar.setToolTipText("Regresar al menú principal");
        btnregresar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnregresar.setBackground(UIManager.getColor("Button.highlight"));
        btnregresar.setBounds(10, 17, 90, 23);
        panelbotones.add(btnregresar);
        
        btnnuevo = new JButton("Nuevo");
        btnnuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vacaciones_nuevo formulario = new vacaciones_nuevo();
                formulario.setVisible(true);
                formulario.setLocationRelativeTo(null);
                formulario.chxeditar.setVisible(false);
                setVisible(false);
            }
        });

        btnnuevo.setToolTipText("Nuevo registro");
        btnnuevo.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnnuevo.setBounds(363, 17, 90, 23);
        panelbotones.add(btnnuevo);
        
        btneliminar = new JButton("Eliminar");
        btneliminar.setToolTipText("Eliminar registro");
        btneliminar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btneliminar.setBounds(263, 17, 90, 23);
        panelbotones.add(btneliminar);

        construirTabla(); 
    }

    private void filtro() {
        String filtroTexto = txtbuscar.getText();
        if (trsfiltroCodigo != null) {
            trsfiltroCodigo.setRowFilter(RowFilter.regexFilter("(?i)" + filtroTexto, 2, 3, 4));  // Filtro en columnas Nombres, Apellidos, Identidad
        }
    }

    public void construirTabla() {
        String[] titulos = { 
            "No", "Id", "Nombres", "Apellidos", "Identidad", "Teléfono", "Correo", "Cargo", "Área",
            "Sexo", "Edad", "Correspondientes", "Inicio", "Fin", "Tomados", "Pendientes", "Fecha", 
            "Hora", "Pagadas"
        };

        String[][] datos = obtenerDatosVacaciones(); 
        modeloTabla = new DefaultTableModel(datos, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        table.setModel(modeloTabla);

        table.getColumnModel().getColumn(0).setPreferredWidth(40);  
        table.getColumnModel().getColumn(1).setPreferredWidth(40);  
        table.getColumnModel().getColumn(10).setPreferredWidth(50);
        

        trsfiltroCodigo = new TableRowSorter<>(modeloTabla);
        table.setRowSorter(trsfiltroCodigo); 

        scrollPane.setViewportView(table);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {  
                    int filaSeleccionada = table.getSelectedRow();
                    if (filaSeleccionada != -1) { 
                        pasarDatosAlFormulario(filaSeleccionada);  
                    }
                }
            }
        });
    }

    public static String[][] obtenerDatosVacaciones() {
        ArrayList<vacaciones> lista = buscarVacaciones(); 
        String[][] datos = new String[lista.size()][19]; 

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");

        for (int i = 0; i < lista.size(); i++) {
            vacaciones vacacion = lista.get(i);

            datos[i][0] = String.valueOf(vacacion.getId_vacaciones());
            datos[i][1] = String.valueOf(vacacion.getId_empleado());
            datos[i][2] = vacacion.getNombres_empleado();
            datos[i][3] = vacacion.getApellidos_empleado();
            datos[i][4] = vacacion.getIdentidad_empleado();
            datos[i][5] = vacacion.getTel_empleado();
            datos[i][6] = vacacion.getCorreo_empleado();
            datos[i][7] = vacacion.getCargo_empleado();
            datos[i][8] = vacacion.getArea_empleado();
            datos[i][9] = vacacion.getSexo_empleado();
            datos[i][10] = String.valueOf(vacacion.getEdad_empleado());
            datos[i][11] = String.valueOf(vacacion.getDias_correspondientes());
            datos[i][12] = dateFormat.format(vacacion.getFecha_inicio_v());
            datos[i][13] = dateFormat.format(vacacion.getFecha_finalizacion_v());
            datos[i][14] = String.valueOf(vacacion.getTotal_dias());
            datos[i][15] = String.valueOf(vacacion.getDias_pendientes());
            datos[i][16] = dateFormat.format(vacacion.getFecha_actual());
            datos[i][17] = vacacion.getHora_actual().toString();
            datos[i][18] = vacacion.getPagadas();
        }

        return datos;  
    }
    
    public static ArrayList<vacaciones> buscarVacaciones() {
        ArrayList<vacaciones> lista = new ArrayList<>();
        conexion con = new conexion();
        Connection cn = con.conectar();

        String sql = "SELECT * FROM vacaciones";

        try (Statement stmt = cn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                vacaciones vacacion = new vacaciones();
                
                vacacion.setId_vacaciones(rs.getInt("id_vacaciones"));
                vacacion.setId_empleado(rs.getInt("id_empleado"));
                vacacion.setNombres_empleado(rs.getString("nombres_empleado"));
                vacacion.setApellidos_empleado(rs.getString("apellidos_empleado"));
                vacacion.setIdentidad_empleado(rs.getString("identidad_empleado"));
                vacacion.setTel_empleado(rs.getString("tel_empleado"));
                vacacion.setCorreo_empleado(rs.getString("correo_empleado"));
                vacacion.setCargo_empleado(rs.getString("cargo_empleado"));
                vacacion.setArea_empleado(rs.getString("area_empleado"));
                vacacion.setSexo_empleado(rs.getString("sexo_empleado"));
                vacacion.setEdad_empleado(rs.getInt("edad_empleado"));
                vacacion.setDias_correspondientes(rs.getInt("dias_correspondientes"));
                vacacion.setFecha_inicio_v(rs.getDate("fecha_inicio_v"));
                vacacion.setFecha_finalizacion_v(rs.getDate("fecha_finalizacion_v"));
                vacacion.setTotal_dias(rs.getInt("total_dias"));
                vacacion.setDias_pendientes(rs.getInt("dias_pendientes"));
                vacacion.setFecha_actual(rs.getDate("fecha_actual"));
                vacacion.setHora_actual(rs.getTime("hora_actual"));
                vacacion.setPagadas(rs.getString("pagadas"));

                lista.add(vacacion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener los registros de vacaciones: " + e.getMessage());
        } finally {
            if (cn != null) {
                con.desconectar();
            }
        }

        return lista;
    }


    


    private void pasarDatosAlFormulario(int fila) {
    	vacaciones_nuevo formulario = new vacaciones_nuevo();
        int filaModelo = table.convertRowIndexToModel(fila);  

        if (formulario == null || !formulario.isShowing()) {
            formulario = new vacaciones_nuevo(); 

            formulario.txtid_tabla.setText(table.getModel().getValueAt(filaModelo, 0).toString());  // Aquí se asigna el id_vacaciones a txtid_tabla
            formulario.cbxnombres.setSelectedItem(table.getModel().getValueAt(filaModelo, 2).toString());
            formulario.txtapellidos.setText(table.getModel().getValueAt(filaModelo, 3).toString());
            formulario.txtidentidad.setText(table.getModel().getValueAt(filaModelo, 4).toString());
            formulario.txttel.setText(table.getModel().getValueAt(filaModelo, 5).toString());
            formulario.txtcorreo.setText(table.getModel().getValueAt(filaModelo, 6).toString());
            formulario.txtcargo.setText(table.getModel().getValueAt(filaModelo, 7).toString());
            formulario.txtarea.setText(table.getModel().getValueAt(filaModelo, 8).toString());
            formulario.txtsexo.setText(table.getModel().getValueAt(filaModelo, 9).toString());
            formulario.txtedad.setText(table.getModel().getValueAt(filaModelo, 10).toString());

            formulario.txtid.setText(table.getModel().getValueAt(filaModelo, 1).toString());

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
            String fechaInicioStr = table.getModel().getValueAt(filaModelo, 12).toString();
            String fechaFinalizacionStr = table.getModel().getValueAt(filaModelo, 13).toString();

            try {
                if (fechaInicioStr != null && fechaInicioStr.length() >= 8) {
                    formulario.fecha_inicio_v.setDate(dateFormat.parse(fechaInicioStr));
                }

                if (fechaFinalizacionStr != null && fechaFinalizacionStr.length() >= 8) {
                    formulario.fecha_finalizacion_v.setDate(dateFormat.parse(fechaFinalizacionStr));
                }
            } catch (ParseException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al convertir la fecha. Verifique el formato de las fechas.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            String pagadas = table.getModel().getValueAt(filaModelo, 17).toString();
            if ("Si".equalsIgnoreCase(pagadas)) {
                formulario.radio_si.setSelected(true);
            } else if ("No".equalsIgnoreCase(pagadas)) {
                formulario.radio_no.setSelected(true);
            }

            formulario.setVisible(true);
            formulario.setLocationRelativeTo(null);
            formulario.btnactualizar.setVisible(false);
            formulario.btnguardar.setVisible(false);
            formulario.btnlimpiar.setVisible(false);
            formulario.cbxnombres.setEnabled(false);
            formulario.fecha_finalizacion_v.setEnabled(false);
            formulario.fecha_inicio_v.setEnabled(false);
            formulario.radio_si.setEnabled(false);
            formulario.radio_no.setEnabled(false);
        } else {
            formulario.toFront();
            formulario.requestFocus();
        }
    }


    
        private void aplicarFiltros() {
            List<RowFilter<Object, Object>> filtros = new ArrayList<>();

            String textoFiltro = txtbuscar.getText();
            if (textoFiltro != null && !textoFiltro.trim().isEmpty() && !textoFiltro.equals("Id empleado, nombres, apellidos e identidad")) {
                filtros.add(RowFilter.regexFilter("(?i)" + textoFiltro, 1, 2, 3, 4)); 
            }

            String cargoSeleccionado = cbxbusquedaCargo.getSelectedItem() != null ? cbxbusquedaCargo.getSelectedItem().toString() : "";
            if (cargoSeleccionado != null && !cargoSeleccionado.trim().isEmpty()) {
                filtros.add(RowFilter.regexFilter(cargoSeleccionado, 7));
            }

            String areaSeleccionada = cbxbusquedaarea.getSelectedItem() != null ? cbxbusquedaarea.getSelectedItem().toString() : "";
            if (areaSeleccionada != null && !areaSeleccionada.trim().isEmpty()) {
                filtros.add(RowFilter.regexFilter(areaSeleccionada, 8)); 
            }

            Date fechaDesde = desde_buscar.getDate();
            Date fechaHasta = hasta_buscar.getDate();

            if (fechaDesde != null && fechaHasta != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");

                filtros.add(RowFilter.dateFilter(RowFilter.ComparisonType.AFTER, fechaDesde, 12)); // Fecha de inicio

                filtros.add(RowFilter.dateFilter(RowFilter.ComparisonType.BEFORE, fechaHasta, 13)); // Fecha de finalización
            }

            if (filtros.isEmpty()) {
                trsfiltroCodigo.setRowFilter(null); 
            } else {
                RowFilter<Object, Object> combinedFilter = RowFilter.andFilter(filtros);
                trsfiltroCodigo.setRowFilter(combinedFilter);
            }
        }






    private void cerrar_ventana() {
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            System.exit(0);
    }
}
