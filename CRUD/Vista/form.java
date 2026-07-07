package Vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class form {

    private static String[] titulos = Tabla.Columnas();
    private JButton btnAgregar, btnMostrar, btnEliminar, btnActualizar;
    private DefaultTableModel tabla;

    public form() {
        Formulario();
    }

    public void Formulario() {
        JFrame Form = new JFrame();
        Form.setTitle("SISTEMA DE BIBLIOTECA");
        Form.setSize(800, 450);
        Form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Form.setLocationRelativeTo(null);
        Form.add(PanelPrincipal());
        Form.setVisible(true);
    }

    public JPanel PanelPrincipal() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        //label principal de presentacion
        JLabel presentacion = new JLabel("BIENVENIDO AL SISTEMA");
        presentacion.setBounds(235, 25, 300, 25);
        presentacion.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(presentacion);
        panel.add(Botones());
        panel.add(TablaDatos());
        return panel;
    }

    public JPanel Botones() {
        JPanel botones = new JPanel();
        botones.setLayout(null);

        botones.setBounds(120, 350, 700, 40);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(10, 10, 120, 30);
        botones.add(btnAgregar);

        btnMostrar = new JButton("Mostrar");
        btnMostrar.setBounds(150, 10, 120, 30);
        botones.add(btnMostrar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(290, 10, 120, 30);
        botones.add(btnEliminar);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(430, 10, 120, 30);
        botones.add(btnActualizar);

        return botones;
    }

    public JScrollPane TablaDatos() {
        tabla = new DefaultTableModel(titulos, 0);
        JTable DatosGenerales = new JTable(tabla);
        JScrollPane scroll = new JScrollPane(DatosGenerales);
        scroll.setBounds(20, 120, 740, 200);
        return scroll;
    }

    public JButton getbtnAgregar() {
        return btnAgregar;
    }

    public JButton getbtnMostrar() {
        return btnMostrar;
    }

    public JButton getbtnEliminar() {
        return btnEliminar;
    }

    public JButton getbtnActualizar() {
        return btnActualizar;
    }

    public DefaultTableModel setTabla() {
        return tabla;
    }
}
