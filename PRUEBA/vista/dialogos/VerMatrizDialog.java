package vista.dialogos;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class VerMatrizDialog extends JDialog {
    private String nombreArchivo;
    private Object[][] matriz;

    public VerMatrizDialog(JFrame parent, String nombreArchivo, double[][] matriz) {
        super(parent, "Ver Matriz", true);
        this.nombreArchivo = nombreArchivo;
        this.matriz = new Object[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                this.matriz[i][j] = matriz[i][j];
            }
        }
        initComponents();
        generarHTML();
        setLocationRelativeTo(parent);
        setSize(600, 400);
    }

    public VerMatrizDialog(JFrame parent, String nombreArchivo, int[][] matriz) {
        super(parent, "Ver Matriz", true);
        this.nombreArchivo = nombreArchivo;
        this.matriz = new Object[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                this.matriz[i][j] = matriz[i][j];
            }
        }
        initComponents();
        generarHTML();
        setLocationRelativeTo(parent);
        setSize(600, 400);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textPane);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> dispose());

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnCerrar);

        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        // Mostrar el HTML en el JTextPane
        try {
            textPane.setPage(new java.io.File(nombreArchivo).toURI().toURL());
        } catch (Exception e) {
            textPane.setText("No se pudo cargar el archivo: " + e.getMessage());
        }
    }

    private void generarHTML() {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.write("<html><head><title>Matriz</title>");
            writer.write("<style>table { border-collapse: collapse; margin: 20px auto; }");
            writer.write("td { border: 1px solid black; padding: 10px; text-align: center; }</style>");
            writer.write("</head><body>");
            writer.write("<h2 style='text-align:center;'>" + nombreArchivo + "</h2>");
            writer.write("<table>");
            for (int i = 0; i < matriz.length; i++) {
                writer.write("<tr>");
                for (int j = 0; j < matriz[0].length; j++) {
                    writer.write("<td>" + matriz[i][j] + "</td>");
                }
                writer.write("</tr>");
            }
            writer.write("</table>");
            writer.write("</body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}