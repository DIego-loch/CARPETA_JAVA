package vista.dialogos;

import controlador.LaboratorioController;
import modelo.Investigador;
import modelo.Muestra;
import modelo.Patron;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CargarCSVDialog extends JDialog {
    private LaboratorioController controller;
    private TipoCarga tipo;
    private JTextArea textAreaLog;
    private JButton btnCargar, btnCerrar;
    private JProgressBar progressBar;

    public enum TipoCarga {
        INVESTIGADORES, MUESTRAS, PATRONES
    }

    public CargarCSVDialog(JFrame parent, LaboratorioController controller, TipoCarga tipo) {
        super(parent, "Carga Masiva CSV - " + tipo.name(), true);
        this.controller = controller;
        this.tipo = tipo;
        initComponents();
        setLocationRelativeTo(parent);
        setSize(600, 500);
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // Panel superior
        JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel lblInstrucciones = new JLabel("Seleccione un archivo CSV para cargar:");
        JButton btnSeleccionar = new JButton("Seleccionar Archivo");
        
        JPanel panelSeleccion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSeleccion.add(lblInstrucciones);
        panelSeleccion.add(btnSeleccionar);
        panelSuperior.add(panelSeleccion, BorderLayout.NORTH);

        // Área de log
        textAreaLog = new JTextArea();
        textAreaLog.setEditable(false);
        textAreaLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(textAreaLog);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Registro de Carga"));

        // Progress Bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        // Panel inferior
        JPanel panelInferior = new JPanel(new BorderLayout(10, 10));
        panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        btnCargar = new JButton("Cargar");
        btnCerrar = new JButton("Cerrar");
        btnCargar.setEnabled(false);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnCargar);
        panelBotones.add(btnCerrar);
        
        panelInferior.add(progressBar, BorderLayout.NORTH);
        panelInferior.add(panelBotones, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);

        // Eventos
        btnSeleccionar.addActionListener(e -> seleccionarArchivo());
        btnCargar.addActionListener(e -> cargarArchivo());
        btnCerrar.addActionListener(e -> dispose());
    }

    private void seleccionarArchivo() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Archivos CSV", "csv"));
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            btnCargar.setEnabled(true);
            textAreaLog.append("Archivo seleccionado: " + chooser.getSelectedFile().getName() + "\n");
            textAreaLog.append("Presione 'Cargar' para iniciar la carga masiva.\n\n");
        }
    }

    private void cargarArchivo() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Archivos CSV", "csv"));
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            btnCargar.setEnabled(false);
            new Thread(() -> {
                try {
                    procesarArchivo(chooser.getSelectedFile().getAbsolutePath());
                } catch (Exception e) {
                    SwingUtilities.invokeLater(() -> {
                        textAreaLog.append("ERROR: " + e.getMessage() + "\n");
                        JOptionPane.showMessageDialog(this, "Error al cargar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    });
                } finally {
                    SwingUtilities.invokeLater(() -> {
                        btnCargar.setEnabled(true);
                        progressBar.setValue(0);
                    });
                }
            }).start();
        }
    }

    private void procesarArchivo(String ruta) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            int lineCount = 0;
            int successCount = 0;
            int errorCount = 0;
            List<String> lineas = new ArrayList<>();
            
            // Contar líneas para la barra de progreso
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
            
            int total = lineas.size();
            
            for (int i = 0; i < total; i++) {
                linea = lineas.get(i);
                lineCount++;
                final int progress = (i * 100) / total;
                SwingUtilities.invokeLater(() -> progressBar.setValue(progress));
                
                try {
                    switch (tipo) {
                        case INVESTIGADORES:
                            procesarInvestigador(linea);
                            break;
                        case MUESTRAS:
                            procesarMuestra(linea);
                            break;
                        case PATRONES:
                            procesarPatron(linea);
                            break;
                    }
                    successCount++;
                } catch (Exception e) {
                    errorCount++;
                    final String errorLine = linea;
                    final String errorMsg = e.getMessage();
                    SwingUtilities.invokeLater(() -> {
                        textAreaLog.append("Línea " + lineCount + ": " + errorLine + "\n");
                        textAreaLog.append("  Error: " + errorMsg + "\n\n");
                    });
                }
            }
            
            final int finalSuccess = successCount;
            final int finalError = errorCount;
            SwingUtilities.invokeLater(() -> {
                textAreaLog.append("\n=== RESUMEN DE CARGA ===\n");
                textAreaLog.append("Total de registros: " + total + "\n");
                textAreaLog.append("Cargados exitosamente: " + finalSuccess + "\n");
                textAreaLog.append("Con errores: " + finalError + "\n");
                progressBar.setValue(100);
                JOptionPane.showMessageDialog(this, 
                    "Carga masiva completada\n" +
                    "Éxitos: " + finalSuccess + "\n" +
                    "Errores: " + finalError,
                    "Resultado de Carga",
                    JOptionPane.INFORMATION_MESSAGE);
            });
        }
    }

    private void procesarInvestigador(String linea) {
        String[] partes = linea.split(",");
        if (partes.length != 4) {
            throw new IllegalArgumentException("Formato inválido. Se espera: codigo,nombre,genero,contrasena");
        }
        
        String codigo = partes[0].trim();
        String nombre = partes[1].trim();
        String genero = partes[2].trim();
        String contrasena = partes[3].trim();
        
        if (codigo.isEmpty() || nombre.isEmpty() || genero.isEmpty() || contrasena.isEmpty()) {
            throw new IllegalArgumentException("Todos los campos son obligatorios");
        }
        
        if (controller.buscarInvestigador(codigo) != null) {
            throw new IllegalArgumentException("El código " + codigo + " ya existe");
        }
        
        Investigador inv = new Investigador(codigo, nombre, genero, contrasena);
        controller.agregarInvestigador(inv);
    }

    private void procesarMuestra(String linea) {
        String[] partes = linea.split(";");
        if (partes.length != 3) {
            throw new IllegalArgumentException("Formato inválido. Se espera: codigo;descripcion;patron");
        }
        
        String codigo = partes[0].trim();
        String descripcion = partes[1].trim();
        String patronStr = partes[2].trim();
        
        if (codigo.isEmpty() || descripcion.isEmpty() || patronStr.isEmpty()) {
            throw new IllegalArgumentException("Todos los campos son obligatorios");
        }
        
        if (controller.buscarMuestra(codigo) != null) {
            throw new IllegalArgumentException("El código " + codigo + " ya existe");
        }
        
        // Procesar el patrón
        String[] valores = patronStr.split(";");
        int n = (int) Math.sqrt(valores.length);
        if (n * n != valores.length) {
            throw new IllegalArgumentException("El patrón no forma una matriz cuadrada");
        }
        
        double[][] patron = new double[n][n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                try {
                    patron[i][j] = Double.parseDouble(valores[idx++].trim());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Valor no numérico en el patrón: " + valores[idx-1]);
                }
            }
        }
        
        Muestra muestra = new Muestra(codigo, descripcion, patron);
        controller.agregarMuestra(muestra);
    }

    private void procesarPatron(String linea) {
        String[] partes = linea.split(";");
        if (partes.length != 3) {
            throw new IllegalArgumentException("Formato inválido. Se espera: codigo;nombre;patron");
        }
        
        String codigo = partes[0].trim();
        String nombre = partes[1].trim();
        String patronStr = partes[2].trim();
        
        if (codigo.isEmpty() || nombre.isEmpty() || patronStr.isEmpty()) {
            throw new IllegalArgumentException("Todos los campos son obligatorios");
        }
        
        if (controller.buscarPatron(codigo) != null) {
            throw new IllegalArgumentException("El código " + codigo + " ya existe");
        }
        
        // Procesar el patrón
        String[] valores = patronStr.split(";");
        int n = (int) Math.sqrt(valores.length);
        if (n * n != valores.length) {
            throw new IllegalArgumentException("El patrón no forma una matriz cuadrada");
        }
        
        int[][] patron = new int[n][n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                try {
                    int valor = Integer.parseInt(valores[idx++].trim());
                    if (valor != 0 && valor != 1) {
                        throw new IllegalArgumentException("El patrón solo debe contener 0 y 1");
                    }
                    patron[i][j] = valor;
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Valor no numérico en el patrón: " + valores[idx-1]);
                }
            }
        }
        
        Patron patronObj = new Patron(codigo, nombre, patron);
        controller.agregarPatron(patronObj);
    }
}