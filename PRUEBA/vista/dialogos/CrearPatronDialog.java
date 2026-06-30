package vista.dialogos;

import controlador.LaboratorioController;
import modelo.Patron;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CrearPatronDialog extends JDialog {
    private JTextField txtCodigo, txtNombre;
    private JButton btnCargarArchivo, btnGuardar, btnCancelar;
    private JLabel lblArchivo;
    private int[][] patron;
    private LaboratorioController controller;

    public CrearPatronDialog(JFrame parent, LaboratorioController controller) {
        super(parent, "Crear Patrón", true);
        this.controller = controller;
        initComponents();
        setLocationRelativeTo(parent);
        setSize(450, 350);
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
        add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        add(txtNombre, gbc);

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
        lblArchivo.setForeground(Color.GRAY);
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
        chooser.setFileFilter(new FileNameExtensionFilter("Archivos CSV", "csv"));
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()))) {
                List<List<Integer>> filas = new ArrayList<>();
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] valores = linea.split(",");
                    List<Integer> fila = new ArrayList<>();
                    for (String v : valores) {
                        int valor = Integer.parseInt(v.trim());
                        if (valor != 0 && valor != 1) {
                            JOptionPane.showMessageDialog(this, 
                                "El patrón solo debe contener 0 y 1", 
                                "Error", 
                                JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        fila.add(valor);
                    }
                    filas.add(fila);
                }
                
                // Verificar que sea una matriz cuadrada
                int n = filas.size();
                if (n == 0) {
                    JOptionPane.showMessageDialog(this, "El archivo está vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                for (List<Integer> fila : filas) {
                    if (fila.size() != n) {
                        JOptionPane.showMessageDialog(this, "La matriz no es cuadrada", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                
                patron = new int[n][n];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        patron[i][j] = filas.get(i).get(j);
                    }
                }
                lblArchivo.setText("Archivo cargado: " + chooser.getSelectedFile().getName());
                lblArchivo.setForeground(Color.GREEN);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El archivo debe contener solo números", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void guardar() {
        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();

        if (codigo.isEmpty() || nombre.isEmpty() || patron == null) {
            JOptionPane.showMessageDialog(this, 
                "Todos los campos son obligatorios y debe cargar un archivo", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (controller.buscarPatron(codigo) != null) {
            JOptionPane.showMessageDialog(this, "El código de patrón ya existe", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Patron patronObj = new Patron(codigo, nombre, patron);
        controller.agregarPatron(patronObj);
        JOptionPane.showMessageDialog(this, "Patrón creado exitosamente");
        dispose();
    }
}