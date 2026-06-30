package vista.dialogos;

import controlador.LaboratorioController;
import modelo.Investigador;

import javax.swing.*;
import java.awt.*;

public class CrearInvestigadorDialog extends JDialog {
    private JTextField txtCodigo, txtNombre, txtGenero;
    private JPasswordField txtContrasena;
    private JButton btnGuardar, btnCancelar;
    private LaboratorioController controller;

    public CrearInvestigadorDialog(JFrame parent, LaboratorioController controller) {
        super(parent, "Crear Investigador", true);
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
        add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        add(txtNombre, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y;
        add(new JLabel("Género:"), gbc);
        gbc.gridx = 1;
        txtGenero = new JTextField(15);
        add(txtGenero, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y;
        add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1;
        txtContrasena = new JPasswordField(15);
        add(txtContrasena, gbc);

        y++;
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0; gbc.gridy = y;
        gbc.gridwidth = 2;
        add(panelBotones, gbc);

        btnGuardar.addActionListener(e -> guardar());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void guardar() {
        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();
        String genero = txtGenero.getText();
        String contrasena = new String(txtContrasena.getPassword());

        if (codigo.isEmpty() || nombre.isEmpty() || genero.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (controller.buscarInvestigador(codigo) != null) {
            JOptionPane.showMessageDialog(this, "El código ya existe", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Investigador inv = new Investigador(codigo, nombre, genero, contrasena);
        controller.agregarInvestigador(inv);
        JOptionPane.showMessageDialog(this, "Investigador creado exitosamente");
        dispose();
    }
}