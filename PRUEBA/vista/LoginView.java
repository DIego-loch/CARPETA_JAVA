package vista;

import controlador.LaboratorioController;
import modelo.Investigador;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnLoginAdmin;
    private JButton btnLoginInvestigador;
    private LaboratorioController controller;

    public LoginView() {
        controller = new LaboratorioController();
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sistema de Gestión de Laboratorio - Login");
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitulo = new JLabel("Laboratorio Químico IPC Quimik");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        JLabel lblUsuario = new JLabel("Usuario/Código:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(lblUsuario, gbc);

        txtUsuario = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtUsuario, gbc);

        JLabel lblContrasena = new JLabel("Contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblContrasena, gbc);

        txtContrasena = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(txtContrasena, gbc);

        btnLoginAdmin = new JButton("Ingresar como Administrador");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(btnLoginAdmin, gbc);

        btnLoginInvestigador = new JButton("Ingresar como Investigador");
        gbc.gridy = 4;
        add(btnLoginInvestigador, gbc);

        // Eventos
        btnLoginAdmin.addActionListener(e -> loginAdmin());
        btnLoginInvestigador.addActionListener(e -> loginInvestigador());

        pack();
    }

    private void loginAdmin() {
        String usuario = txtUsuario.getText();
        String contrasena = new String(txtContrasena.getPassword());

        if (controller.autenticarAdmin(usuario, contrasena)) {
            JOptionPane.showMessageDialog(this, "Bienvenido Administrador");
            new AdminView().setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loginInvestigador() {
        String codigo = txtUsuario.getText();
        String contrasena = new String(txtContrasena.getPassword());

        Investigador inv = controller.autenticarInvestigador(codigo, contrasena);
        if (inv != null) {
            JOptionPane.showMessageDialog(this, "Bienvenido " + inv.getNombre());
            new InvestigadorView(inv).setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginView().setVisible(true);
        });
    }
}