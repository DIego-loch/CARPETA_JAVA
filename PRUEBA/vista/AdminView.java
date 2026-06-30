package vista;

import controlador.LaboratorioController;
import modelo.*;
import vista.dialogos.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminView extends JFrame {
    private JTabbedPane tabbedPane;
    private LaboratorioController controller;
    private JTable tableInvestigadores, tableMuestras, tablePatrones;

    public AdminView() {
        controller = new LaboratorioController();
        initComponents();
        setTitle("Administración - Laboratorio Químico");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cargarDatosIniciales();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Barra de menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menuSistema = new JMenu("Sistema");
        JMenuItem menuCerrarSesion = new JMenuItem("Cerrar Sesión");
        menuCerrarSesion.addActionListener(e -> cerrarSesion());
        menuSistema.add(menuCerrarSesion);
        menuBar.add(menuSistema);
        setJMenuBar(menuBar);

        tabbedPane = new JTabbedPane();

        // Pestaña Investigadores
        tabbedPane.addTab("Investigadores", crearPanelInvestigadores());

        // Pestaña Muestras
        tabbedPane.addTab("Muestras", crearPanelMuestras());

        // Pestaña Asignación de Experimentos
        tabbedPane.addTab("Asignación de Experimentos", crearPanelAsignacion());

        // Pestaña Patrones
        tabbedPane.addTab("Patrones", crearPanelPatrones());

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel crearPanelInvestigadores() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnCrear = new JButton("Crear Investigador");
        JButton btnCargarCSV = new JButton("Cargar CSV");
        JButton btnActualizar = new JButton("Actualizar Investigador");
        JButton btnEliminar = new JButton("Eliminar Investigador");
        JButton btnRefrescar = new JButton("Refrescar");

        panelBotones.add(btnCrear);
        panelBotones.add(btnCargarCSV);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnRefrescar);

        // Tabla
        tableInvestigadores = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableInvestigadores);

        panel.add(panelBotones, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Eventos
        btnCrear.addActionListener(e -> mostrarDialogoCrearInvestigador());
        btnCargarCSV.addActionListener(e -> cargarCSVInvestigadores());
        btnActualizar.addActionListener(e -> mostrarDialogoActualizarInvestigador());
        btnEliminar.addActionListener(e -> eliminarInvestigador());
        btnRefrescar.addActionListener(e -> cargarDatosInvestigadores());

        return panel;
    }

    private JPanel crearPanelMuestras() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnCrear = new JButton("Crear Muestra");
        JButton btnCargarCSV = new JButton("Cargar CSV");
        JButton btnVerMatriz = new JButton("Ver Matriz");
        JButton btnRefrescar = new JButton("Refrescar");

        panelBotones.add(btnCrear);
        panelBotones.add(btnCargarCSV);
        panelBotones.add(btnVerMatriz);
        panelBotones.add(btnRefrescar);

        tableMuestras = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableMuestras);

        panel.add(panelBotones, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        btnCrear.addActionListener(e -> mostrarDialogoCrearMuestra());
        btnCargarCSV.addActionListener(e -> cargarCSVMuestras());
        btnVerMatriz.addActionListener(e -> verMatrizMuestra());
        btnRefrescar.addActionListener(e -> cargarDatosMuestras());

        return panel;
    }

    private JPanel crearPanelAsignacion() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblMuestra = new JLabel("Seleccionar Muestra:");
        JComboBox<Muestra> comboMuestras = new JComboBox<>();
        JLabel lblInvestigador = new JLabel("Seleccionar Investigador:");
        JComboBox<Investigador> comboInvestigadores = new JComboBox<>();
        JButton btnAsignar = new JButton("Asignar");

        gbc.gridx = 0; gbc.gridy = 0;
        panelCentral.add(lblMuestra, gbc);
        gbc.gridx = 1;
        panelCentral.add(comboMuestras, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        panelCentral.add(lblInvestigador, gbc);
        gbc.gridx = 1;
        panelCentral.add(comboInvestigadores, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        panelCentral.add(btnAsignar, gbc);

        // Actualizar combos
        actualizarCombosAsignacion(comboMuestras, comboInvestigadores);

        btnAsignar.addActionListener(e -> {
            Muestra muestra = (Muestra) comboMuestras.getSelectedItem();
            Investigador inv = (Investigador) comboInvestigadores.getSelectedItem();
            if (muestra != null && inv != null) {
                if (controller.asignarMuestraAInvestigador(muestra.getCodigo(), inv.getCodigo())) {
                    JOptionPane.showMessageDialog(this, "Muestra asignada exitosamente");
                    actualizarCombosAsignacion(comboMuestras, comboInvestigadores);
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo asignar la muestra", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(panelCentral, BorderLayout.NORTH);
        return panel;
    }

    private void actualizarCombosAsignacion(JComboBox<Muestra> comboMuestras, JComboBox<Investigador> comboInvestigadores) {
        comboMuestras.removeAllItems();
        for (Muestra m : controller.getMuestrasDisponibles()) {
            comboMuestras.addItem(m);
        }
        comboInvestigadores.removeAllItems();
        for (Investigador inv : controller.getInvestigadores()) {
            comboInvestigadores.addItem(inv);
        }
    }

    private JPanel crearPanelPatrones() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnCrear = new JButton("Crear Patrón");
        JButton btnCargarCSV = new JButton("Cargar CSV");
        JButton btnVerMatriz = new JButton("Ver Matriz");
        JButton btnEliminar = new JButton("Eliminar Patrón");
        JButton btnRefrescar = new JButton("Refrescar");

        panelBotones.add(btnCrear);
        panelBotones.add(btnCargarCSV);
        panelBotones.add(btnVerMatriz);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnRefrescar);

        tablePatrones = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablePatrones);

        panel.add(panelBotones, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        btnCrear.addActionListener(e -> mostrarDialogoCrearPatron());
        btnCargarCSV.addActionListener(e -> cargarCSVPatrones());
        btnVerMatriz.addActionListener(e -> verMatrizPatron());
        btnEliminar.addActionListener(e -> eliminarPatron());
        btnRefrescar.addActionListener(e -> cargarDatosPatrones());

        return panel;
    }

    // Métodos para cargar datos en tablas
    private void cargarDatosIniciales() {
        cargarDatosInvestigadores();
        cargarDatosMuestras();
        cargarDatosPatrones();
    }

    private void cargarDatosInvestigadores() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Género");
        modelo.addColumn("Experimentos");

        for (Investigador inv : controller.getInvestigadores()) {
            modelo.addRow(new Object[]{
                inv.getCodigo(),
                inv.getNombre(),
                inv.getGenero(),
                inv.getExperimentosRealizados()
            });
        }
        tableInvestigadores.setModel(modelo);
    }

    private void cargarDatosMuestras() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Descripción");
        modelo.addColumn("Estado");
        modelo.addColumn("Investigador Asignado");

        for (Muestra m : controller.getMuestras()) {
            String investigador = m.getInvestigadorAsignado() != null ?
                m.getInvestigadorAsignado().getNombre() : "No asignado";
            modelo.addRow(new Object[]{
                m.getCodigo(),
                m.getDescripcion(),
                m.getEstado(),
                investigador
            });
        }
        tableMuestras.setModel(modelo);
    }

    private void cargarDatosPatrones() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Tamaño");

        for (Patron p : controller.getPatrones()) {
            modelo.addRow(new Object[]{
                p.getCodigo(),
                p.getNombre(),
                p.getSize() + "x" + p.getSize()
            });
        }
        tablePatrones.setModel(modelo);
    }

    // Métodos para diálogos
    private void mostrarDialogoCrearInvestigador() {
        new CrearInvestigadorDialog(this, controller).setVisible(true);
        cargarDatosInvestigadores();
    }

    private void mostrarDialogoActualizarInvestigador() {
        String codigo = JOptionPane.showInputDialog(this, "Ingrese el código del investigador a actualizar:");
        if (codigo != null) {
            Investigador inv = controller.buscarInvestigador(codigo);
            if (inv != null) {
                new ActualizarInvestigadorDialog(this, controller, inv).setVisible(true);
                cargarDatosInvestigadores();
            } else {
                JOptionPane.showMessageDialog(this, "Investigador no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarInvestigador() {
        String codigo = JOptionPane.showInputDialog(this, "Ingrese el código del investigador a eliminar:");
        if (codigo != null) {
            if (controller.eliminarInvestigador(codigo)) {
                JOptionPane.showMessageDialog(this, "Investigador eliminado exitosamente");
                cargarDatosInvestigadores();
            } else {
                JOptionPane.showMessageDialog(this, "Investigador no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mostrarDialogoCrearMuestra() {
        new CrearMuestraDialog(this, controller).setVisible(true);
        cargarDatosMuestras();
    }

    private void verMatrizMuestra() {
        int row = tableMuestras.getSelectedRow();
        if (row >= 0) {
            String codigo = tableMuestras.getValueAt(row, 0).toString();
            Muestra muestra = controller.buscarMuestra(codigo);
            if (muestra != null) {
                new VerMatrizDialog(this, "Muestra_" + muestra.getCodigo() + ".html", muestra.getPatron()).setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una muestra");
        }
    }

    private void mostrarDialogoCrearPatron() {
        new CrearPatronDialog(this, controller).setVisible(true);
        cargarDatosPatrones();
    }

    private void verMatrizPatron() {
        int row = tablePatrones.getSelectedRow();
        if (row >= 0) {
            String codigo = tablePatrones.getValueAt(row, 0).toString();
            Patron patron = controller.buscarPatron(codigo);
            if (patron != null) {
                new VerMatrizDialog(this, "Patron_" + patron.getCodigo() + ".html", patron.getMatriz()).setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un patrón");
        }
    }

    private void eliminarPatron() {
        String codigo = JOptionPane.showInputDialog(this, "Ingrese el código del patrón a eliminar:");
        if (codigo != null) {
            if (controller.eliminarPatron(codigo)) {
                JOptionPane.showMessageDialog(this, "Patrón eliminado exitosamente");
                cargarDatosPatrones();
            } else {
                JOptionPane.showMessageDialog(this, "Patrón no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Métodos para cargar CSV
    private void cargarCSVInvestigadores() {
        // Implementar la carga de CSV de investigadores
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            // Aquí implementar la lectura del CSV
            JOptionPane.showMessageDialog(this, "Funcionalidad de carga CSV en desarrollo");
        }
    }

    private void cargarCSVMuestras() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            // Implementar la lectura del CSV de muestras
            JOptionPane.showMessageDialog(this, "Funcionalidad de carga CSV en desarrollo");
        }
    }

    private void cargarCSVPatrones() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            // Implementar la lectura del CSV de patrones
            JOptionPane.showMessageDialog(this, "Funcionalidad de carga CSV en desarrollo");
        }
    }

    private void cerrarSesion() {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea cerrar sesión?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            new LoginView().setVisible(true);
            this.dispose();
        }
    }
}