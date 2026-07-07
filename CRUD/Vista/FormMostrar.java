package Vista;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class FormMostrar {

    public FormMostrar() {
        JFrameMostrar();
    }

    public void JFrameMostrar() {
        JFrame mostrar = new JFrame();
        mostrar.setTitle("Mostrar Libro");
        mostrar.setSize(700, 400);
        mostrar.setLayout(null);
        mostrar.setLocationRelativeTo(null);
        mostrar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mostrar.add(PanelPrincipal());
        mostrar.setVisible(true);
    }

    public JPanel PanelPrincipal() {
        JPanel Panel = new JPanel();
        Panel.setLayout(null);
        Panel.setSize(700, 400);
        Panel.add(PanelSuperior());
        Panel.add(PanelInferior());
        return Panel;
    }

    public JPanel PanelSuperior() {
        JPanel PanelSuperior = new JPanel();
        PanelSuperior.setLayout(null);
        PanelSuperior.setBackground(Color.BLUE);
        PanelSuperior.setBounds(0, 0, 700, 70);
        PanelSuperior.add(jLabel());
        return PanelSuperior;
    }

    public JPanel PanelInferior() {
        JPanel PanelInferior = new JPanel();
        PanelInferior.setLayout(null);
        PanelInferior.setBounds(0, 80, 700, 330);
        PanelInferior.setBackground(Color.GRAY);
        return PanelInferior;
    }

    public JLabel jLabel() {
        JLabel texto = new JLabel();
        texto.setText("🔎 Ingrese el ID del libro o nombre");
        texto.setBounds(155, 5, 430, 50);
        texto.setForeground(Color.WHITE);
        texto.setFont(new Font("Arial", Font.BOLD, 24));
        return texto;
    }
}
