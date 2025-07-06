package ventanas;

import javax.swing.*;
import javax.swing.table.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.Window.Type;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import conexion.conexion;

@SuppressWarnings("serial")
public class asistencia_nuevo extends JFrame {

    private DefaultTableModel modeloTabla;
    private JTable tablaEmpleados;
    private JScrollPane scrollPane;
    public JButton btnguardar, btnactualizar, btnregresar;
    public JLabel lblresultado;
    public JDateChooser fecha_chooser;
    public JCheckBox chxeditar;
    private boolean columnaAsistioEditable = false;

    
    public asistencia_nuevo() {
    	getContentPane().setBackground(Color.WHITE);
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
        JPanel panelScroll = new JPanel();
        panelScroll.setLayout(null);
        panelScroll.setBackground(SystemColor.menu);
        panelScroll.setBounds(25, 129, 990, 440);
        getContentPane().add(panelScroll);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 970, 370);
        panelScroll.add(scrollPane);

        tablaEmpleados = new JTable();
        scrollPane.setViewportView(tablaEmpleados);

        lblresultado = new JLabel("");
        lblresultado.setHorizontalAlignment(SwingConstants.RIGHT);
        lblresultado.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblresultado.setBounds(744, 390, 222, 27);
        panelScroll.add(lblresultado);

        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(null);
        panelBusqueda.setBackground(SystemColor.menu);
        panelBusqueda.setBounds(25, 76, 990, 45);
        getContentPane().add(panelBusqueda);

        JLabel lblFecha = new JLabel("Fecha");
        lblFecha.setHorizontalAlignment(SwingConstants.LEFT);
        lblFecha.setForeground(Color.BLACK);
        lblFecha.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblFecha.setBounds(10, 10, 67, 26);
        panelBusqueda.add(lblFecha);

        fecha_chooser = new JDateChooser();
        fecha_chooser.setBounds(70, 10, 150, 25);
        fecha_chooser.setDateFormatString("dd-MM-yy");
        panelBusqueda.add(fecha_chooser);

        JButton btnCargar = new JButton("Cargar empleados");
        btnCargar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnCargar.setBackground(UIManager.getColor("Button.highlight"));
        btnCargar.setBounds(240, 10, 140, 25);
        panelBusqueda.add(btnCargar);

        JLabel lblTitulo = new JLabel("REGISTRO DE ASISTENCIA DIARIA");
        lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setBounds(28, 29, 514, 26);
        getContentPane().add(lblTitulo);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(null);
        panelBotones.setBackground(SystemColor.menu);
        panelBotones.setBounds(552, 20, 463, 56);
        getContentPane().add(panelBotones);

        btnregresar = new JButton("Regresar");
        btnregresar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		asistencia_tabla t = new asistencia_tabla();
        		t.setVisible(true);
        		t.setLocationRelativeTo(null);
        		t.construirTabla();
        		t.cargarFechasAsistencia();
        		dispose();
        	}
        });
        btnregresar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnregresar.setBackground(UIManager.getColor("Button.highlight"));
        btnregresar.setBounds(24, 17, 90, 23);
        panelBotones.add(btnregresar);

        btnactualizar = new JButton("Actualizar");
        btnactualizar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnactualizar.setBackground(UIManager.getColor("Button.highlight"));
        btnactualizar.setBounds(346, 17, 90, 23);
        panelBotones.add(btnactualizar);

        btnguardar = new JButton("Guardar");
        btnguardar.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnguardar.setBackground(UIManager.getColor("Button.highlight"));
        btnguardar.setBounds(346, 17, 90, 23);
        panelBotones.add(btnguardar);
        
        chxeditar = new JCheckBox("Editar registro");
        chxeditar.setFont(new Font("Tahoma", Font.PLAIN, 13));
        chxeditar.setBounds(207, 18, 116, 21);
        chxeditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                columnaAsistioEditable = chxeditar.isSelected();
                btnactualizar.setVisible(columnaAsistioEditable);

                // Esto obliga al JTable a reconstruir el modelo y así se aplica el nuevo editable
                ((AbstractTableModel) tablaEmpleados.getModel()).fireTableStructureChanged();
            }
        });
        panelBotones.add(chxeditar);
        configurarTabla();
        establecerFechaActual();

        btnCargar.addActionListener(e -> cargarEmpleados());
        btnguardar.addActionListener(e -> guardarAsistencia());
        btnactualizar.addActionListener(e -> actualizarAsistencia());

       
    }

    private void configurarTabla() {
        String[] columnas = {"No.", "ID", "Identidad", "Nombres", "Apellidos", "Correo", "Sexo", "Teléfono", "Cargo", "Área", "Asistió"};
        modeloTabla = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 10 && columnaAsistioEditable;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 10 ? Boolean.class : String.class;
            }
        };
        tablaEmpleados.setModel(modeloTabla);
        tablaEmpleados.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tablaEmpleados.setRowHeight(25);
        tablaEmpleados.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
    }

    private void establecerFechaActual() {
        java.util.Date fechaActual = new java.util.Date();
        fecha_chooser.setDate(fechaActual);
    }

    private void cargarEmpleados() {
        modeloTabla.setRowCount(0);
        conexion cn = new conexion();
        Connection con = cn.conectar();
        String sql = "SELECT id, identidad_empleado, nombres_empleado, apellidos_empleado, correo_empleado, " +
                     "sexo_empleado, tel_empleado, cargo_empleado, area_empleado FROM empleados";

        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            int numero = 1;
            while (rs.next()) {
                modeloTabla.addRow(new Object[]{
                    numero++,
                    rs.getInt("id"),  // ahora usas el PRIMARY KEY id
                    rs.getString("identidad_empleado"),
                    rs.getString("nombres_empleado"),
                    rs.getString("apellidos_empleado"),
                    rs.getString("correo_empleado"),
                    rs.getString("sexo_empleado"),
                    rs.getString("tel_empleado"),
                    rs.getString("cargo_empleado"),
                    rs.getString("area_empleado"),
                    false
                });
            }
            lblresultado.setText("Resultados: " + modeloTabla.getRowCount());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar empleados: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            cn.desconectar(con);
        }
    }

    private void guardarAsistencia() {
        if (modeloTabla.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No hay empleados cargados para registrar asistencia.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        java.util.Date fecha = fecha_chooser.getDate();
        if (fecha == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una fecha válida.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
        conexion cn = new conexion();
        Connection con = cn.conectar();
        String sql = "INSERT INTO asistencia_diaria (id_empleado, fecha, asistio) VALUES (?, ?, ?)";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                int idEmpleado = Integer.parseInt(modeloTabla.getValueAt(i, 1).toString());
                boolean asistio = (boolean) modeloTabla.getValueAt(i, 10);

                pst.setInt(1, idEmpleado);
                pst.setDate(2, fechaSQL);
                pst.setBoolean(3, asistio);
                pst.addBatch();
            }
            pst.executeBatch();
            JOptionPane.showMessageDialog(this, "Asistencia guardada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            modeloTabla.setRowCount(0);
            asistencia_tabla t = new asistencia_tabla();
            t.setVisible(true);
            t.setLocationRelativeTo(null);
            t.construirTabla();
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar asistencia: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
        	cn.desconectar(con);

        }
    }
    
    public void cargarAsistenciaPorFecha(java.sql.Date fecha) {
        modeloTabla.setRowCount(0);
        conexion cn = new conexion();
        Connection con = cn.conectar();
        String sql = "SELECT e.id, e.identidad_empleado, e.nombres_empleado, e.apellidos_empleado, " +
                     "e.correo_empleado, e.sexo_empleado, e.tel_empleado, e.cargo_empleado, e.area_empleado, a.asistio " +
                     "FROM asistencia_diaria a JOIN empleados e ON a.id_empleado = e.id WHERE a.fecha = ?";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setDate(1, fecha);
            ResultSet rs = pst.executeQuery();
            int numero = 1;
            while (rs.next()) {
                modeloTabla.addRow(new Object[]{
                    numero++,
                    rs.getInt("id"),
                    rs.getString("identidad_empleado"),
                    rs.getString("nombres_empleado"),
                    rs.getString("apellidos_empleado"),
                    rs.getString("correo_empleado"),
                    rs.getString("sexo_empleado"),
                    rs.getString("tel_empleado"),
                    rs.getString("cargo_empleado"),
                    rs.getString("area_empleado"),
                    rs.getBoolean("asistio")
                });
            }
            lblresultado.setText("Resultados: " + modeloTabla.getRowCount());
            fecha_chooser.setDate(fecha);

            columnaAsistioEditable = false;
            btnactualizar.setVisible(false);
            chxeditar.setSelected(false);
            ((AbstractTableModel) tablaEmpleados.getModel()).fireTableStructureChanged();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar detalles: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            cn.desconectar(con);
        }
    }
    
    private void actualizarAsistencia() {
        if (modeloTabla.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No hay registros cargados para actualizar.", 
                                          "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        java.util.Date fecha = fecha_chooser.getDate();
        if (fecha == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una fecha válida.", 
                                          "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());
        conexion cn = new conexion();
        Connection con = cn.conectar();
        String sql = "UPDATE asistencia_diaria SET asistio = ? WHERE id_empleado = ? AND fecha = ?";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                int idEmpleado = Integer.parseInt(modeloTabla.getValueAt(i, 1).toString());
                boolean asistio = (boolean) modeloTabla.getValueAt(i, 10);

                pst.setBoolean(1, asistio);
                pst.setInt(2, idEmpleado);
                pst.setDate(3, fechaSQL);
                pst.addBatch();
            }
            pst.executeBatch();
            JOptionPane.showMessageDialog(this, "Asistencia actualizada correctamente.", 
                                          "Éxito", JOptionPane.INFORMATION_MESSAGE);
            btnactualizar.setVisible(false);
            chxeditar.setSelected(false);
            columnaAsistioEditable = false;
            ((AbstractTableModel) tablaEmpleados.getModel()).fireTableStructureChanged();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar asistencia: " + ex.getMessage(), 
                                          "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            cn.desconectar(con);
        }
    }


    
    private void cerrar_ventana() {
		if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
} 
