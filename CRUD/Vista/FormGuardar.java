package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FormGuardar {

    private JButton BtnGuardar;
    private JButton BtnCancelar;
    private JFrame FGuardar;
    private boolean campo = false;

    JTextField[] TextoEntrada = new JTextField[4];

    public FormGuardar() {
        JFrameGuardar();
    }

    public void JFrameGuardar() {
        FGuardar = new JFrame();
        FGuardar.setTitle("Agregar libro");
        FGuardar.setSize(400, 500);
        FGuardar.setLocationRelativeTo(null);
        FGuardar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        FGuardar.add(PanelCentral());

        FGuardar.setVisible(true);
    }

    public JPanel PanelCentral() {
        JPanel PanelPrincipal = new JPanel();
        PanelPrincipal.setLayout(null);

        PanelPrincipal.add(PanelPrimario());
        PanelPrincipal.add(PanelSecundario());

        return PanelPrincipal;
    }

    public JPanel PanelPrimario() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(25, 15, 340, 60);

        JLabel presentacion = new JLabel("AGREGAR LIBRO");
        presentacion.setBounds(75, 15, 260, 30);
        presentacion.setFont(new Font("Arial", Font.BOLD, 25));

        panel.add(presentacion);

        return panel;
    }

    public JPanel PanelSecundario() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Empieza debajo del primer panel
        panel.setBounds(25, 90, 340, 300);
        panel.setBackground(Color.GREEN);

        //---------------- ID ----------------

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 20, 60, 25);

        TextoEntrada[0] = new JTextField();
        TextoEntrada[0].setBounds(90, 20, 200, 25);

        panel.add(lblId);
        panel.add(TextoEntrada[0]);

        //---------------- TITULO ----------------

        JLabel lblTitulo = new JLabel("TÍTULO:");
        lblTitulo.setBounds(20, 60, 60, 25);

        TextoEntrada[1] = new JTextField();
        TextoEntrada[1].setBounds(90, 60, 200, 25);

        panel.add(lblTitulo);
        panel.add(TextoEntrada[1]);

        //---------------- AUTOR ----------------

        JLabel lblAutor = new JLabel("AUTOR:");
        lblAutor.setBounds(20, 100, 60, 25);

        TextoEntrada[2] = new JTextField();
        TextoEntrada[2].setBounds(90, 100, 200, 25);

        panel.add(lblAutor);
        panel.add(TextoEntrada[2]);

        //---------------- FECHA ----------------

        JLabel lblFecha = new JLabel("FECHA:");
        lblFecha.setBounds(20, 140, 60, 25);

        TextoEntrada[3] = new JTextField();
        TextoEntrada[3].setBounds(90, 140, 200, 25);

        panel.add(lblFecha);
        panel.add(TextoEntrada[3]);

        //----------------Botones-----------------
        BtnGuardar = new JButton();
        BtnGuardar.setText("Guardar");
        BtnGuardar.setBounds(50, 230, 120, 35);
        panel.add(BtnGuardar);

        BtnCancelar = new JButton();
        BtnCancelar.setText("Cancelar");
        BtnCancelar.setBounds(190, 230, 120, 35);
        panel.add(BtnCancelar);

        return panel;
    }

    //retornamos la opcion de guardar y cerrar
    public JButton BtnGuardarDatos() {
        return BtnGuardar;
    }

    public JButton BtnCancelarForm() {
        return BtnCancelar;
    }

    public JTextField[] DatosPrincipales() {
        return TextoEntrada;
    }

    public JFrame formulario() {
        return FGuardar;
    }

    public boolean CamposVacios() {
        for (JTextField campos : TextoEntrada) {
            if (campos.getText().trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
