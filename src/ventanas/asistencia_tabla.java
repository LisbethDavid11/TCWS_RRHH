package ventanas;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.Window.Type;
import java.awt.event.*;
import java.sql.*;
import conexion.conexion;
import principal.menu_principal;
import reportes.reporte_asistencia_diaria;

import com.toedter.calendar.JDateChooser;

@SuppressWarnings("serial")
public class asistencia_tabla extends JFrame {

    private DefaultTableModel modeloTabla;
    private JTable tablaAsistencia;
    private JScrollPane scrollPane;
    private JButton btnMenu;
    private JLabel lblresultado;
    private JButton btnImprimir;
    private JButton btneliminar;
    private JPanel panelBusqueda;
    private JLabel lblBuscar;
    private JDateChooser fecha_chooser;

    public asistencia_tabla() {
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

        JLabel lblTitulo = new JLabel("ASISTENCIA DIARIA");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setBounds(34, 19, 504, 36);
        getContentPane().add(lblTitulo);

        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(null);
        panelTabla.setBackground(SystemColor.menu);
        panelTabla.setBounds(34, 120, 980, 452);
        getContentPane().add(panelTabla);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 960, 372);
        panelTabla.add(scrollPane);

        tablaAsistencia = new JTable();
        scrollPane.setViewportView(tablaAsistencia);

        lblresultado = new JLabel("");
        lblresultado.setHorizontalAlignment(SwingConstants.RIGHT);
        lblresultado.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblresultado.setBounds(782, 404, 174, 27);
        panelTabla.add(lblresultado);
        
        JPanel panelbotones = new JPanel();
        panelbotones.setLayout(null);
        panelbotones.setBackground(SystemColor.menu);
        panelbotones.setBounds(556, 14, 458, 56);
        getContentPane().add(panelbotones);
        
        JButton btnNuevo = new JButton("Nuevo");
        btnNuevo.setBounds(358, 18, 90, 25);
        panelbotones.add(btnNuevo);
        btnNuevo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		asistencia_nuevo n = new asistencia_nuevo();
        		n.setVisible(true);
        		n.setLocationRelativeTo(null);
        		n.btnactualizar.setVisible(false);
        		n.chxeditar.setVisible(false);
        		dispose();
        	}
        });
        btnNuevo.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnNuevo.setBackground(UIManager.getColor("Button.highlight"));
                
                        btnMenu = new JButton("Menú");
                        btnMenu.setBounds(24, 18, 90, 25);
                        panelbotones.add(btnMenu);
                        btnMenu.setFont(new Font("Tahoma", Font.BOLD, 10));
                        btnMenu.setBackground(UIManager.getColor("Button.highlight"));
                        
                        btnImprimir = new JButton("Imprimir");
                        btnImprimir.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int fila = tablaAsistencia.getSelectedRow();
                                if (fila == -1) {
                                    JOptionPane.showMessageDialog(asistencia_tabla.this,
                                            "Por favor, seleccione una fila para continuar",
                                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                                    return;
                                }

                                int filaModelo = tablaAsistencia.convertRowIndexToModel(fila);
                                // Columnas: {"No.", "Fecha", "Total empleados", "Total asistieron"}
                                String fechaStr = String.valueOf(modeloTabla.getValueAt(filaModelo, 1)); // dd-MM-yyyy

                                reporte_asistencia_diaria rep = new reporte_asistencia_diaria();
                                rep.generarReporteAsistenciaDiaria(fechaStr); // 👈 ahora solo pasamos la fecha
                            }
                        });
                        btnImprimir.setFont(new Font("Tahoma", Font.BOLD, 10));
                        btnImprimir.setBackground(UIManager.getColor("Button.highlight"));
                        btnImprimir.setBounds(160, 18, 90, 25);
                        panelbotones.add(btnImprimir);
                        
                        btneliminar = new JButton("Eliminar");
                        btneliminar.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                eliminar_asistencia_diaria();  // 👈 aquí llamas al método
                            }
                        });

                        btneliminar.setFont(new Font("Tahoma", Font.BOLD, 10));
                        btneliminar.setBackground(UIManager.getColor("Button.highlight"));
                        btneliminar.setBounds(258, 18, 90, 25);
                        panelbotones.add(btneliminar);
                        
                        panelBusqueda = new JPanel();
                        panelBusqueda.setLayout(null);
                        panelBusqueda.setBackground(SystemColor.menu);
                        panelBusqueda.setBounds(34, 65, 980, 45);
                        getContentPane().add(panelBusqueda);
                        
                        lblBuscar = new JLabel("Buscar");
                        lblBuscar.setHorizontalAlignment(SwingConstants.LEFT);
                        lblBuscar.setForeground(Color.BLACK);
                        lblBuscar.setFont(new Font("Segoe UI", Font.BOLD, 16));
                        lblBuscar.setBounds(10, 10, 67, 26);
                        panelBusqueda.add(lblBuscar);
                        
                        fecha_chooser = new JDateChooser();
                        fecha_chooser.setDateFormatString("dd-MM-yy");
                        fecha_chooser.setBounds(70, 10, 203, 25);
                        panelBusqueda.add(fecha_chooser);

        btnMenu.addActionListener(e -> {
            menu_principal menu = new menu_principal();
            menu.setVisible(true);
            menu.setLocationRelativeTo(null);
            dispose();
        });


        tablaAsistencia.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    abrirDetalles();
                }
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                cerrar_ventana();
            }
        });
        
        
        fecha_chooser.getDateEditor().addPropertyChangeListener("date", evt -> filtrarPorFecha());

        
    }

    public void construirTabla() {
        String[] columnas = {"No.", "Fecha", "Total empleados", "Total asistieron"};
        modeloTabla = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaAsistencia.setModel(modeloTabla);
        tablaAsistencia.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaAsistencia.setRowHeight(25);
        tablaAsistencia.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
    }

    public void cargarFechasAsistencia() {
        modeloTabla.setRowCount(0);
        conexion cn = new conexion();
        Connection con = cn.conectar();
        String sql = "SELECT fecha, COUNT(*) AS total_empleados, " +
                     "SUM(CASE WHEN asistio THEN 1 ELSE 0 END) AS total_asistieron " +
                     "FROM asistencia_diaria GROUP BY fecha ORDER BY fecha DESC";

        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            int numero = 1;
            while (rs.next()) {
                java.sql.Date fechaSQL = rs.getDate("fecha");
                String fechaFormateada = new java.text.SimpleDateFormat("dd-MM-yyyy").format(fechaSQL);

                modeloTabla.addRow(new Object[]{
                    numero++,
                    fechaFormateada,
                    rs.getInt("total_empleados"),
                    rs.getInt("total_asistieron")
                });
            }
            lblresultado.setText("Registros: " + modeloTabla.getRowCount());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar fechas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            cn.desconectar(con);
        }
    }



    private void abrirDetalles() {
        int fila = tablaAsistencia.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un registro.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            String fechaStr = modeloTabla.getValueAt(fila, 1).toString();
            java.util.Date fechaUtil = new java.text.SimpleDateFormat("dd-MM-yyyy").parse(fechaStr);
            java.sql.Date fecha = new java.sql.Date(fechaUtil.getTime());

            asistencia_nuevo detalle = new asistencia_nuevo();
            detalle.cargarAsistenciaPorFecha(fecha);
            detalle.btnguardar.setVisible(false);
            detalle.btnactualizar.setVisible(false);
            detalle.btnCargar.setVisible(false);
            detalle.setVisible(true);
            detalle.setLocationRelativeTo(null);
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al abrir detalles: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void filtrarPorFecha() {
        java.util.Date fechaSeleccionada = fecha_chooser.getDate();
        if (fechaSeleccionada == null) {
            // Si el usuario borra manualmente la fecha, recarga todos los registros
            cargarFechasAsistencia();
            return;
        }

        modeloTabla.setRowCount(0);
        conexion cn = new conexion();
        Connection con = cn.conectar();

        String sql = "SELECT fecha, COUNT(*) AS total_empleados, " +
                     "SUM(CASE WHEN asistio THEN 1 ELSE 0 END) AS total_asistieron " +
                     "FROM asistencia_diaria WHERE fecha = ? GROUP BY fecha ORDER BY fecha DESC";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            java.sql.Date fechaSQL = new java.sql.Date(fechaSeleccionada.getTime());
            pst.setDate(1, fechaSQL);

            ResultSet rs = pst.executeQuery();
            int numero = 1;
            while (rs.next()) {
                String fechaFormateada = new java.text.SimpleDateFormat("dd-MM-yyyy").format(rs.getDate("fecha"));

                modeloTabla.addRow(new Object[]{
                    numero++,
                    fechaFormateada,
                    rs.getInt("total_empleados"),
                    rs.getInt("total_asistieron")
                });
            }
            lblresultado.setText("Registros: " + modeloTabla.getRowCount());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al filtrar por fecha: " + e.getMessage(), 
                                          "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            cn.desconectar(con);
        }
    }
    
 // Dentro de asistencia_tabla
    public void eliminar_asistencia_diaria() {
        int fila = tablaAsistencia.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, seleccione una fila para continuar",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Tomamos la fecha en texto desde la columna 1 (índice 1) con formato dd-MM-yyyy
        String fechaStr = String.valueOf(modeloTabla.getValueAt(fila, 1)).trim();

        // Confirmación (indicando que se eliminarán TODOS los registros de esa fecha)
        int confirmar = JOptionPane.showConfirmDialog(
                this,
                "¿Desea eliminar todos los registros de asistencia del día " + fechaStr + "?\n" +
                "Esto los eliminará permanentemente de la base de datos",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        if (confirmar != JOptionPane.YES_OPTION) return;

        // Parsear la fecha dd-MM-yyyy a java.sql.Date
        java.sql.Date fechaSQL;
        try {
            java.util.Date fechaUtil = new java.text.SimpleDateFormat("dd-MM-yyyy").parse(fechaStr);
            fechaSQL = new java.sql.Date(fechaUtil.getTime());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "No se pudo interpretar la fecha seleccionada.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Eliminar en DB por fecha
        final String SQL = "DELETE FROM asistencia_diaria WHERE fecha = ?";

        conexion cx = new conexion();
        Connection con = cx.conectar();
        PreparedStatement pst = null;

        try {
            pst = con.prepareStatement(SQL);
            pst.setDate(1, fechaSQL);

            int borrados = pst.executeUpdate(); // cantidad de filas eliminadas
            if (borrados > 0) {
                JOptionPane.showMessageDialog(this,
                        "Se eliminaron " + borrados + " registro(s) de la fecha " + fechaStr + ".",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                // Si tienes un filtro activo por fecha, puedes decidir:
                // - recargar todo:
                cargarFechasAsistencia();
                // - o, si prefieres mantener el filtro del JDateChooser, llama filtrarPorFecha();
            } else {
                JOptionPane.showMessageDialog(this,
                        "No se encontró nada para eliminar en la fecha " + fechaStr + ".",
                        "Información",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(this,
                    "No se puede eliminar porque existen datos relacionados.\nDetalle: " + e.getMessage(),
                    "Restricción de integridad",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Error en la DB al eliminar: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            try { if (pst != null) pst.close(); } catch (Exception ignored) {}
            cx.desconectar(con); // Importante: tu desconectar recibe la Connection
        }
    }

  
    private void cerrar_ventana() {
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea salir del sistema?", "Salir del sistema",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
            System.exit(0);
    }
}
