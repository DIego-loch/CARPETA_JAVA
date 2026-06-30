package vista.dialogos;

import controlador.LaboratorioController;
import modelo.Investigador;

import javax.swing.*;
import java.awt.*;

public class ActualizarInvestigadorDialog extends JDialog {
    private Investigador investigador;
    private JTextField txtNombre, txtGenero;
    private JPasswordField txtContrasena;
    private JButton btnGuardar, btnCancelar;
    private LaboratorioController controller;

    public ActualizarInvestigadorDialog(JFrame parent, LaboratorioController controller, Investigador investigador) {
        super(parent, "Actualizar Investigador", true);
        this.controller = controller;
        this.investigador = investigador;
        initComponents();
        cargarDatos();
        setLocationRelativeTo(parent);
        setSize(400, 250);
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        int y = 0;
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
        btnGuardar = new JButton("Actualizar");
        btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        gbc.gridx = 0; gbc.gridy = y;
        gbc.gridwidth = 2;
        add(panelBotones, gbc);

        btnGuardar.addActionListener(e -> actualizar());
        btnCancelar.addActionListener(e -> dispose());
    }

    private void cargarDatos() {
        txtNombre.setText(investigador.getNombre());
        txtGenero.setText(investigador.getGenero());
        txtContrasena.setText(investigador.getContrasena());
    }

    private void actualizar() {
        String nombre = txtNombre.getText();
        String genero = txtGenero.getText();
        String contrasena = new String(txtContrasena.getPassword());

        if (nombre.isEmpty() || genero.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        investigador.setNombre(nombre);
        investigador.setGenero(genero);
        investigador.setContrasena(contrasena);
        controller.agregarInvestigador(investigador); // Actualizar en el sistema
        JOptionPane.showMessageDialog(this, "Investigador actualizado exitosamente");
        dispose();
    }
}