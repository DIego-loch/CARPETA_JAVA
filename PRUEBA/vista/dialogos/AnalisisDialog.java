package vista.dialogos;

import controlador.LaboratorioController;
import modelo.*;

import javax.swing.*;
import java.awt.*;

public class AnalisisDialog extends JDialog {
    private Muestra muestra;
    private Patron patron;
    private Investigador investigador;
    private LaboratorioController controller;
    private JLabel lblResultado;
    private JPanel panelMatrizResultante;
    private JPanel panelMatrizPatron;
    private JLabel[][] labelsResultante;
    private JLabel[][] labelsPatron;
    private boolean analisisExitoso = false;
    private volatile boolean detenerHilo = false;

    public AnalisisDialog(JFrame parent, LaboratorioController controller, Muestra muestra, Patron patron,
                          Investigador investigador, JLabel lblResultadoPrincipal) {
        super(parent, "Análisis de Muestra", true);
        this.controller = controller;
        this.muestra = muestra;
        this.patron = patron;
        this.investigador = investigador;
        this.lblResultado = lblResultadoPrincipal;
        initComponents();
        setLocationRelativeTo(parent);
        setSize(800, 600);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitulo = new JLabel("Análisis en Tiempo Real", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        // Panel de Matriz Resultante
        JPanel panelResultante = new JPanel(new BorderLayout());
        panelResultante.setBorder(BorderFactory.createTitledBorder("Matriz Resultante"));
        panelMatrizResultante = new JPanel(new GridLayout(muestra.getSize(), muestra.getSize()));
        labelsResultante = new JLabel[muestra.getSize()][muestra.getSize()];
        for (int i = 0; i < muestra.getSize(); i++) {
            for (int j = 0; j < muestra.getSize(); j++) {
                labelsResultante[i][j] = new JLabel("0", JLabel.CENTER);
                labelsResultante[i][j].setOpaque(true);
                labelsResultante[i][j].setBackground(Color.WHITE);
                labelsResultante[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY));
                panelMatrizResultante.add(labelsResultante[i][j]);
            }
        }
        panelResultante.add(panelMatrizResultante, BorderLayout.CENTER);

        // Panel de Matriz Patrón
        JPanel panelPatron = new JPanel(new BorderLayout());
        panelPatron.setBorder(BorderFactory.createTitledBorder("Matriz Patrón"));
        panelMatrizPatron = new JPanel(new GridLayout(patron.getSize(), patron.getSize()));
        labelsPatron = new JLabel[patron.getSize()][patron.getSize()];
        for (int i = 0; i < patron.getSize(); i++) {
            for (int j = 0; j < patron.getSize(); j++) {
                int valor = patron.getMatriz()[i][j];
                labelsPatron[i][j] = new JLabel(String.valueOf(valor), JLabel.CENTER);
                labelsPatron[i][j].setOpaque(true);
                labelsPatron[i][j].setBackground(Color.WHITE);
                labelsPatron[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY));
                if (valor == 1) {
                    labelsPatron[i][j].setBackground(Color.BLACK);
                    labelsPatron[i][j].setForeground(Color.WHITE);
                }
                panelMatrizPatron.add(labelsPatron[i][j]);
            }
        }
        panelPatron.add(panelMatrizPatron, BorderLayout.CENTER);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(panelResultante, gbc);
        gbc.gridx = 1;
        add(panelPatron, gbc);

        // Botón de inicio
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnIniciar = new JButton("Iniciar Análisis");
        JButton btnCerrar = new JButton("Cerrar");
        panelBotones.add(btnIniciar);
        panelBotones.add(btnCerrar);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(panelBotones, gbc);

        btnIniciar.addActionListener(e -> {
            btnIniciar.setEnabled(false);
            iniciarAnalisis();
        });

        btnCerrar.addActionListener(e -> dispose());
        pack();
    }

    private void iniciarAnalisis() {
        // Calcular la matriz resultante
        double[][] matrizResultante = calcularMatrizResultante();

        // Iniciar hilo de animación
        new Thread(new Runnable() {
            @Override
            public void run() {
                int n = muestra.getSize();
                analisisExitoso = true;

                for (int i = 0; i < n && !detenerHilo; i++) {
                    for (int j = 0; j < n && !detenerHilo; j++) {
                        int valorReal = (int) matrizResultante[i][j];
                        int valorPatron = patron.getMatriz()[i][j];
                        final int fila = i;
                        final int columna = j;

                        boolean coinciden = (valorReal == valorPatron);
                        if (!coinciden) {
                            analisisExitoso = false;
                            detenerHilo = true;
                        }

                        // Actualizar la GUI
                        SwingUtilities.invokeLater(() -> {
                            // Matriz Resultante
                            labelsResultante[fila][columna].setText(String.valueOf(valorReal));
                            if (coinciden) {
                                labelsResultante[fila][columna].setBackground(Color.GREEN);
                            } else {
                                labelsResultante[fila][columna].setBackground(Color.RED);
                            }
                            // Matriz Patrón
                            labelsPatron[fila][columna].setBackground(coinciden ? Color.GREEN : Color.RED);
                        });

                        try {
                            Thread.sleep(500); // Pausa de 500ms
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                // Finalizar el análisis
                SwingUtilities.invokeLater(() -> {
                    boolean resultado = controller.realizarAnalisis(muestra, patron, investigador);
                    if (resultado) {
                        lblResultado.setText("Los resultados coinciden con " + patron.getNombre());
                        lblResultado.setForeground(Color.GREEN);
                    } else {
                        lblResultado.setText("No coincide con " + patron.getNombre());
                        lblResultado.setForeground(Color.RED);
                    }
                    JOptionPane.showMessageDialog(AnalisisDialog.this,
                        resultado ? "¡Análisis exitoso!" : "Análisis fallido",
                        "Resultado",
                        resultado ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE);
                    dispose();
                });
            }
        }).start();
    }

    private double[][] calcularMatrizResultante() {
        double[][] matriz1 = muestra.getPatron();
        int n = matriz1.length;

        // MATRIZ TEMP 1 = MATRIZ 1 * 3
        double[][] temp1 = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp1[i][j] = matriz1[i][j] * 3;
            }
        }

        // MATRIZ TEMP 2 = MATRIZ 1 * 7
        double[][] temp2 = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp2[i][j] = matriz1[i][j] * 7;
            }
        }

        // MATRIZ TEMP 3 = MATRIZ TEMP 1 * MATRIZ TEMP 2
        double[][] temp3 = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double suma = 0;
                for (int k = 0; k < n; k++) {
                    suma += temp1[i][k] * temp2[k][j];
                }
                temp3[i][j] = suma;
            }
        }

        // MATRIZ 2 = MOD(MATRIZ TEMP 3, 2)
        double[][] resultado = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                resultado[i][j] = temp3[i][j] % 2;
            }
        }

        return resultado;
    }
}