package ventanas;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import com.toedter.calendar.JDateChooser;

import conexion.conexion;
import clases.memorandum;                 // <-- POJO con getters/setters de la tabla memorandum
import consultas.consultas_areas;
import consultas.consultas_cargos;
import consultas.consultas_memorandum;   // <-- DAO: eliminar_memorandum(int id)
import principal.menu_principal;
import java.awt.Window.Type;

@SuppressWarnings({ "serial", "unused" })
public class memorandum_tabla extends JFrame {

    private JTable table;
    private JScrollPane scrollPane;
    private DefaultTableModel modeloTabla;
    private JComboBox<String> cbxbusquedaCargo, cbxbusquedaarea;
    private JTextField txtbuscar;
    private TableRowSorter<DefaultTableModel> trsfiltroCodigo;
    public JButton btnactualizar, btnregresar, btnnuevo, btneliminar;
    public JLabel lblresultado_busqueda;

    public memorandum_tabla() {
        getContentPane().setBackground(Color.WHITE);
        setType(Type.UTILITY);
        setResizable(false);
        setBounds(100, 100, 1050, 630);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override public void windowClosing(java.awt.event.WindowEvent evt) { cerrar_ventana(); }
        });

        JPanel panel_tabla = new JPanel();
        panel_tabla.setLayout(null);
        panel_tabla.setBackground(SystemColor.menu);
        panel_tabla.setBounds(25, 132, 990, 440);
        getContentPane().add(panel_tabla);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 970, 370);
        panel_tabla.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        lblresultado_busqueda = new JLabel("");
        lblresultado_busqueda.setHorizontalAlignment(SwingConstants.RIGHT);
        lblresultado_busqueda.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblresultado_busqueda.setBounds(746, 390, 222, 27);
        panel_tabla.add(lblresultado_busqueda);

        JPanel panelbusqueda = new JPanel();
        panelbusqueda.setLayout(null);
        panelbusqueda.setBackground(SystemColor.menu);
        panelbusqueda.setBounds(25, 77, 990, 46);
        getContentPane().add(panelbusqueda);

        txtbuscar = new JTextField();
        txtbuscar.setFont(new Font("Tahoma", Font.PLAIN, 10));
        txtbuscar.setBounds(68, 10, 235, 27);
        panelbusqueda.add(txtbuscar);

        final String placeHolderText = "Nombres, apellidos, identidad, teléfono o sexo";
        txtbuscar.setText(placeHolderText);
        txtbuscar.setForeground(Color.GRAY);

        txtbuscar.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                if (txtbuscar.getText().equals(placeHolderText)) { txtbuscar.setText(""); txtbuscar.setForeground(Color.BLACK); }
            }
            @Override public void focusLost(FocusEvent e) {
                if (txtbuscar.getText().isEmpty()) { txtbuscar.setText(placeHolderText); txtbuscar.setForeground(Color.GRAY); }
            }
        });

        txtbuscar.addKeyListener(new KeyAdapter() {
            @Override public void keyReleased(KeyEvent e) { filtro(); }
        });

        JLabel lblbuscar = new JLabel("Buscar");
        lblbuscar.setHorizontalAlignment(SwingConstants.LEFT);
        lblbuscar.setForeground(Color.BLACK);
        lblbuscar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblbuscar.setBounds(10, 10, 66, 26);
        panelbusqueda.add(lblbuscar);

        cbxbusquedaCargo = new JComboBox<>();
        cbxbusquedaCargo.setSelectedIndex(-1);
        cbxbusquedaCargo.setFont(new Font("Tahoma", Font.BOLD, 11));
        cbxbusquedaCargo.setBounds(376, 11, 111, 26);
        panelbusqueda.add(cbxbusquedaCargo);

        JLabel lblCargo = new JLabel("Cargo");
        lblCargo.setHorizontalAlignment(SwingConstants.LEFT);
        lblCargo.setForeground(Color.BLACK);
        lblCargo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblCargo.setBounds(323, 10, 66, 26);
        panelbusqueda.add(lblCargo);

        cbxbusquedaarea = new JComboBox<>();
        cbxbusquedaarea.setSelectedIndex(-1);
        cbxbusquedaarea.setFont(new Font("Tahoma", Font.BOLD, 11));
        cbxbusquedaarea.setBounds(545, 11, 111, 26);
        panelbusqueda.add(cbxbusquedaarea);

        JLabel lblarea = new JLabel("Área");
        lblarea.setHorizontalAlignment(SwingConstants.LEFT);
        lblarea.setForeground(Color.BLACK);
        lblarea.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblarea.setBounds(497, 9, 56, 26);
        panelbusqueda.add(lblarea);

        JPanel panelbotones = new JPanel();
        panelbotones.setLayout(null);
        panelbotones.setBackground(SystemColor.menu);
        panelbotones.setBounds(564, 23, 451, 56);
        getContentPane().add(panelbotones);

        btnregresar = new JButton("Menú");
        btnregresar.setBackground(UIManager.getColor("Button.highlight"));
        btnregresar.addActionListener(e -> {
            menu_principal menu = new menu_principal();
            menu.setVisible(true);
            menu.setLocationRelativeTo(null);
            dispose();
        });
        btnregresar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnregresar.setToolTipText("Regresar al menú principal");
        btnregresar.setBounds(10, 17, 90, 23);
        panelbotones.add(btnregresar);

        btneliminar = new JButton("Eliminar");
        btneliminar.addActionListener(e -> eliminarSeleccionado());
        btneliminar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btneliminar.setToolTipText("Eliminar registro");
        btneliminar.setBounds(251, 17, 90, 23);
        panelbotones.add(btneliminar);

        btnnuevo = new JButton("Nuevo");
        btnnuevo.addActionListener(e -> {
            memorandum_nuevo nuevo = new memorandum_nuevo(); // <-- tu formulario de edición/alta
            nuevo.setVisible(true);
            nuevo.setLocationRelativeTo(null);
            nuevo.btnactualizar.setVisible(false);
            nuevo.chxeditar.setVisible(false);
            dispose();
        });
        btnnuevo.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnnuevo.setToolTipText("Nuevo registro");
        btnnuevo.setBounds(351, 17, 90, 23);
        panelbotones.add(btnnuevo);

        JLabel lblTitulo = new JLabel("MEMORÁNDUM");
        lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setBounds(25, 30, 534, 26);
        getContentPane().add(lblTitulo);

        construirTabla();

        cbxbusquedaCargo.addActionListener(e -> aplicarFiltros());
        cbxbusquedaarea.addActionListener(e -> aplicarFiltros());

        cargarAreasEnComboBox();
        cargarCargosEnComboBox();
    }

    private void actualizarConteoRegistros() {
        lblresultado_busqueda.setText("Registros: " + table.getRowCount());
    }

    private void filtro() {
        String filtroTexto = txtbuscar.getText();
        if (trsfiltroCodigo != null) {
            // Columnas: 3 Nombres, 4 Apellidos, 5 Identidad, 6 Teléfono, 10 Sexo
            trsfiltroCodigo.setRowFilter(RowFilter.regexFilter("(?i)" + filtroTexto, 3, 4, 5, 6, 10));
        }
        actualizarConteoRegistros();
    }

    private void aplicarFiltros() {
        String filtroCargo = (String) cbxbusquedaCargo.getSelectedItem();
        String filtroArea  = (String) cbxbusquedaarea.getSelectedItem();
        List<RowFilter<Object, Object>> filtros = new ArrayList<>();

        if (filtroCargo != null && !filtroCargo.trim().isEmpty()) filtros.add(RowFilter.regexFilter("(?i)" + filtroCargo, 8));
        if (filtroArea  != null && !filtroArea.trim().isEmpty())  filtros.add(RowFilter.regexFilter("(?i)" + filtroArea, 9));

        if (trsfiltroCodigo != null) trsfiltroCodigo.setRowFilter(filtros.isEmpty() ? null : RowFilter.andFilter(filtros));
        actualizarConteoRegistros();
    }

    public void construirTabla() {
        String[] titulos = {
            "No", "DB", "Id Empl.", "Nombres", "Apellidos", "Identidad", "Teléfono",
            "Correo", "Cargo", "Área", "Sexo", "Edad", "Nacimiento", "Fecha Actual",
            "Hora Actual", "Motivo"
        };

        String[][] datos = obtenerDatosMemorandum();
        modeloTabla = new DefaultTableModel(datos, titulos) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };

        table.setModel(modeloTabla);
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(32, 136, 203));
        table.getTableHeader().setForeground(Color.WHITE);

        // Ajustes de ancho (chicos para No y DB)
        table.getColumnModel().getColumn(0).setPreferredWidth(30); // No
        table.getColumnModel().getColumn(1).setPreferredWidth(30); // DB
        table.getColumnModel().getColumn(2).setPreferredWidth(55); // Id Empl.

        trsfiltroCodigo = new TableRowSorter<>(modeloTabla);
        table.setRowSorter(trsfiltroCodigo);
        scrollPane.setViewportView(table);
        actualizarConteoRegistros();

        // Doble clic -> abrir formulario
        table.addMouseListener(new MouseAdapter() {
            @Override public void mousePressed(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = table.getSelectedRow();
                    if (fila != -1) pasarDatosAlFormulario(fila);
                }
            }
        });
    }

    public static String[][] obtenerDatosMemorandum() {
        ArrayList<memorandum> lista = buscarMemorandum();
        String[][] datos = new String[lista.size()][16];

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");
        SimpleDateFormat tf = new SimpleDateFormat("HH:mm:ss");

        for (int i = 0; i < lista.size(); i++) {
            memorandum m = lista.get(i);
            datos[i][0]  = String.valueOf(i + 1);                   // No
            datos[i][1]  = String.valueOf(m.getId_memorandum());    // DB
            datos[i][2]  = String.valueOf(m.getId_empleado());      // Id Empl.
            datos[i][3]  = m.getNombres_empleado();
            datos[i][4]  = m.getApellidos_empleado();
            datos[i][5]  = m.getIdentidad_empleado();
            datos[i][6]  = m.getTel_empleado();
            datos[i][7]  = m.getCorreo_empleado();
            datos[i][8]  = m.getCargo_empleado();
            datos[i][9]  = m.getArea_empleado();
            datos[i][10] = m.getSexo_empleado();
            datos[i][11] = String.valueOf(m.getEdad_empleado());
            datos[i][12] = m.getNacimiento_empleado() != null ? df.format(m.getNacimiento_empleado()) : "";
            datos[i][13] = m.getFecha_actual() != null ? df.format(m.getFecha_actual()) : "";
            datos[i][14] = m.getHora_actual() != null ? tf.format(m.getHora_actual()) : "";
            datos[i][15] = m.getMotivo_memorandum();
        }
        return datos;
    }

    public static ArrayList<memorandum> buscarMemorandum() {
        ArrayList<memorandum> lista = new ArrayList<>();
        conexion con = new conexion();
        Connection cn = con.conectar();
        String sql = "SELECT * FROM memorandum";

        try (Statement st = cn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                memorandum m = new memorandum();
                m.setId_memorandum(rs.getInt("id_memorandum"));
                m.setId_empleado(rs.getInt("id_empleado"));
                m.setNombres_empleado(rs.getString("nombres_empleado"));
                m.setApellidos_empleado(rs.getString("apellidos_empleado"));
                m.setIdentidad_empleado(rs.getString("identidad_empleado"));
                m.setTel_empleado(rs.getString("tel_empleado"));
                m.setCorreo_empleado(rs.getString("correo_empleado"));
                m.setCargo_empleado(rs.getString("cargo_empleado"));
                m.setArea_empleado(rs.getString("area_empleado"));
                m.setNacimiento_empleado(rs.getDate("nacimiento_empleado"));
                m.setSexo_empleado(rs.getString("sexo_empleado"));
                m.setEdad_empleado(rs.getInt("edad_empleado"));
                m.setFecha_actual(rs.getDate("fecha_actual"));
                m.setHora_actual(rs.getTime("hora_actual"));
                m.setMotivo_memorandum(rs.getString("motivo_memorandum"));
                lista.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private void pasarDatosAlFormulario(int fila) {
        int filaModelo = table.convertRowIndexToModel(fila);
        memorandum_nuevo frm = new memorandum_nuevo();  // <-- tu formulario

        try {
            frm.txtid_memorandum.setText(table.getValueAt(filaModelo, 1).toString()); // DB
            frm.txtid_empleado.setText(table.getValueAt(filaModelo, 2).toString());
            frm.cbxnombres.setSelectedItem(table.getValueAt(filaModelo, 3).toString());
            frm.txtapellidos.setText(table.getValueAt(filaModelo, 4).toString());
            frm.txtidentidad.setText(table.getValueAt(filaModelo, 5).toString());
            frm.txttel.setText(table.getValueAt(filaModelo, 6).toString());
            frm.txtcorreo.setText(table.getValueAt(filaModelo, 7).toString());
            frm.txtcargo.setText(table.getValueAt(filaModelo, 8).toString());
            frm.txtarea.setText(table.getValueAt(filaModelo, 9).toString());
            frm.txtsexo.setText(table.getValueAt(filaModelo, 10).toString());
            frm.txtedad.setText(table.getValueAt(filaModelo, 11).toString());

            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy");
            SimpleDateFormat tf = new SimpleDateFormat("HH:mm:ss");

            String nac  = String.valueOf(table.getValueAt(filaModelo, 12));
            if (!nac.isEmpty() && !"null".equalsIgnoreCase(nac)) frm.fecha_nacimiento.setDate(df.parse(nac));

            String fAct = String.valueOf(table.getValueAt(filaModelo, 13));
            frm.txtfecha_actual.setText(!"null".equalsIgnoreCase(fAct) ? fAct : "");

            String hAct = String.valueOf(table.getValueAt(filaModelo, 14));
            frm.txthora_actual.setText(!"null".equalsIgnoreCase(hAct) ? hAct : "");

            frm.txamotivo_memorandum.setText(String.valueOf(table.getValueAt(filaModelo, 15)));

        } catch (ParseException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al convertir fechas/horas del registro seleccionado.\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Mostrar formulario en modo lectura hasta marcar chxeditar
        frm.btnguardar.setVisible(false);
        frm.btnlimpiar.setVisible(false);
        frm.btnactualizar.setVisible(false);
        frm.setVisible(true);
        frm.setLocationRelativeTo(null);
        frm.chxeditar.setSelected(false);
        frm.chxeditar.setVisible(true);
        frm.cbxnombres.setEnabled(false);
    }

    private void eliminarSeleccionado() {
        try {
            int fila = table.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para continuar",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(null,
                    "¿Está seguro de eliminar el registro seleccionado?\n" +
                    "Esto también lo eliminará permanentemente de la base de datos.",
                    "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                int filaModelo = table.convertRowIndexToModel(fila);
                String idStr = table.getValueAt(filaModelo, 1).toString();
                consultas_memorandum dao = new consultas_memorandum();
                if (dao.eliminar_memorandum(Integer.parseInt(idStr))) {
                    ((DefaultTableModel) table.getModel()).removeRow(filaModelo);
                    JOptionPane.showMessageDialog(null, "Registro eliminado correctamente.");
                    actualizarConteoRegistros();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar en BD.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error, inténtelo nuevamente", "Error en la operación", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cerrar_ventana() {
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            System.exit(0);
    }

    private void cargarCargosEnComboBox() {
        consultas_cargos consultas = new consultas_cargos();
        List<String> cargos = consultas.obtenerCargos();
        cbxbusquedaCargo.removeAllItems();
        cbxbusquedaCargo.addItem(" ");
        for (String cargo : cargos) cbxbusquedaCargo.addItem(cargo);
        cbxbusquedaCargo.setSelectedIndex(0);
    }

    private void cargarAreasEnComboBox() {
        consultas_areas consultas = new consultas_areas();
        List<String> areas = consultas.obtenerAreas();
        cbxbusquedaarea.removeAllItems();
        cbxbusquedaarea.addItem(" ");
        for (String area : areas) cbxbusquedaarea.addItem(area);
        cbxbusquedaarea.setSelectedIndex(0);
    }
}
