package vista;

import controlador.LaboratorioController;
import modelo.*;
import vista.dialogos.AnalisisDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class InvestigadorView extends JFrame {
    private Investigador investigador;
    private LaboratorioController controller;
    private JTabbedPane tabbedPane;
    private JTable tableMuestrasPendientes, tableResultados;

    public InvestigadorView(Investigador investigador) {
        this.investigador = investigador;
        this.controller = new LaboratorioController();
        initComponents();
        setTitle("Investigador - " + investigador.getNombre());
        setSize(900, 600);
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

        // Pestaña Análisis de Experimentos
        tabbedPane.addTab("Análisis de Experimentos", crearPanelAnalisis());

        // Pestaña Resultados
        tabbedPane.addTab("Resultados", crearPanelResultados());

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel crearPanelAnalisis() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel superior con selección de muestra y patrón
        JPanel panelSeleccion = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblMuestra = new JLabel("Seleccionar Muestra:");
        JComboBox<Muestra> comboMuestras = new JComboBox<>();
        JLabel lblPatron = new JLabel("Seleccionar Patrón:");
        JComboBox<Patron> comboPatrones = new JComboBox<>();
        JButton btnAnalizar = new JButton("Analizar");

        gbc.gridx = 0; gbc.gridy = 0;
        panelSeleccion.add(lblMuestra, gbc);
        gbc.gridx = 1;
        panelSeleccion.add(comboMuestras, gbc);
        gbc.gridx = 2;
        panelSeleccion.add(lblPatron, gbc);
        gbc.gridx = 3;
        panelSeleccion.add(comboPatrones, gbc);
        gbc.gridx = 4;
        panelSeleccion.add(btnAnalizar, gbc);

        // Cargar datos en combos
        cargarCombos(comboMuestras, comboPatrones);

        // Tabla de muestras pendientes
        tableMuestrasPendientes = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableMuestrasPendientes);

        // Panel de resultados
        JPanel panelResultado = new JPanel(new FlowLayout());
        JLabel lblResultado = new JLabel("Resultado del análisis: ");
        JLabel lblResultadoValor = new JLabel("");
        lblResultadoValor.setFont(new Font("Arial", Font.BOLD, 14));
        panelResultado.add(lblResultado);
        panelResultado.add(lblResultadoValor);

        panel.add(panelSeleccion, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(panelResultado, BorderLayout.SOUTH);

        // Eventos
        btnAnalizar.addActionListener(e -> {
            Muestra muestra = (Muestra) comboMuestras.getSelectedItem();
            Patron patron = (Patron) comboPatrones.getSelectedItem();
            if (muestra != null && patron != null) {
                new AnalisisDialog(this, controller, muestra, patron, investigador, lblResultadoValor).setVisible(true);
                cargarCombos(comboMuestras, comboPatrones);
                cargarDatosMuestrasPendientes();
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una muestra y un patrón", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private void cargarCombos(JComboBox<Muestra> comboMuestras, JComboBox<Patron> comboPatrones) {
        comboMuestras.removeAllItems();
        List<Muestra> muestrasAsignadas = controller.getMuestrasAsignadasAInvestigador(investigador);
        for (Muestra m : muestrasAsignadas) {
            comboMuestras.addItem(m);
        }

        comboPatrones.removeAllItems();
        for (Patron p : controller.getPatrones()) {
            comboPatrones.addItem(p);
        }

        cargarDatosMuestrasPendientes();
    }

    private void cargarDatosMuestrasPendientes() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Descripción");
        modelo.addColumn("Estado");

        List<Muestra> muestrasAsignadas = controller.getMuestrasAsignadasAInvestigador(investigador);
        for (Muestra m : muestrasAsignadas) {
            modelo.addRow(new Object[]{
                m.getCodigo(),
                m.getDescripcion(),
                m.getEstado()
            });
        }
        tableMuestrasPendientes.setModel(modelo);
    }

    private JPanel crearPanelResultados() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        tableResultados = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableResultados);

        JButton btnRefrescar = new JButton("Refrescar");
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotones.add(btnRefrescar);

        panel.add(panelBotones, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        btnRefrescar.addActionListener(e -> cargarDatosResultados());

        return panel;
    }

    private void cargarDatosIniciales() {
        cargarDatosMuestrasPendientes();
        cargarDatosResultados();
    }

    private void cargarDatosResultados() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("No. Análisis");
        modelo.addColumn("Código Muestra");
        modelo.addColumn("Código Patrón");
        modelo.addColumn("Fecha y Hora");
        modelo.addColumn("Resultado");

        for (Experimento exp : controller.getExperimentos()) {
            modelo.addRow(new Object[]{
                exp.getNumeroAnalisis(),
                exp.getCodigoMuestra(),
                exp.getCodigoPatron(),
                exp.getFechaFormateada(),
                exp.getResultadoTexto()
            });
        }
        tableResultados.setModel(modelo);
    }

    private void cerrarSesion() {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea cerrar sesión?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            new LoginView().setVisible(true);
            this.dispose();
        }
    }
}