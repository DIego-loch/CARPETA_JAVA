package vista.dialogos;

import controlador.LaboratorioController;
import modelo.Muestra;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CrearMuestraDialog extends JDialog {
    private JTextField txtCodigo, txtDescripcion;
    private JButton btnCargarArchivo, btnGuardar, btnCancelar;
    private JLabel lblArchivo;
    private double[][] patron;
    private LaboratorioController controller;

    public CrearMuestraDialog(JFrame parent, LaboratorioController controller) {
        super(parent, "Crear Muestra", true);
        this.controller = controller;
        initComponents();
        setLocationRelativeTo(parent);
        setSize(400, 300);
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        int y = 0;
        gbc.gridx = 0; gbc.gridy = y;
        add(new JLabel("Código:"), gbc);
        gbc.gridx = 1;
        txtCodigo = new JTextField(15);
        add(txtCodigo, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y;
        add(new JLabel("Descripción:"), gbc);
        gbc.gridx = 1;
        txtDescripcion = new JTextField(15);
        add(txtDescripcion, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y;
        add(new JLabel("Patrón (CSV):"), gbc);
        gbc.gridx = 1;
        btnCargarArchivo = new JButton("Cargar Archivo");
        add(btnCargarArchivo, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y;
        gbc.gridwidth = 2;
        lblArchivo = new JLabel("No se ha seleccionado archivo");
        add(lblArchivo, gbc);

        y++;
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0; gbc.gridy = y;
        gbc.gridwidth = 2;
        add(panelBotones, gbc);

        btnCargarArchivo.addActionListener(e -> cargarArchivo());
        btnGuardar.addActionListener(e -> guardar());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void cargarArchivo() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("CSV Files", "csv"));
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()))) {
                List<List<Double>> filas = new ArrayList<>();
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] valores = linea.split(",");
                    List<Double> fila = new ArrayList<>();
                    for (String v : valores) {
                        fila.add(Double.parseDouble(v.trim()));
                    }
                    filas.add(fila);
                }
                // Verificar que sea una matriz cuadrada
                int n = filas.size();
                for (List<Double> fila : filas) {
                    if (fila.size() != n) {
                        JOptionPane.showMessageDialog(this, "La matriz no es cuadrada", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                patron = new double[n][n];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        patron[i][j] = filas.get(i).get(j);
                    }
                }
                lblArchivo.setText("Archivo cargado: " + chooser.getSelectedFile().getName());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void guardar() {
        String codigo = txtCodigo.getText();
        String descripcion = txtDescripcion.getText();

        if (codigo.isEmpty() || descripcion.isEmpty() || patron == null) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios y debe cargar un archivo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (controller.buscarMuestra(codigo) != null) {
            JOptionPane.showMessageDialog(this, "El código de muestra ya existe", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Muestra muestra = new Muestra(codigo, descripcion, patron);
        controller.agregarMuestra(muestra);
        JOptionPane.showMessageDialog(this, "Muestra creada exitosamente");
        dispose();
    }
}