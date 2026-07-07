package Controlador;

import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class controlador {

    private form vista;
    private FormGuardar formGuardar;

    public controlador(form vista) {
        this.vista = vista;
        eventos();
    }

    public void eventos() {
        //evento de guardar
        vista.getbtnAgregar().addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    agregarLibro();
                    Cancelar();
                }
            }
        );

        //evento de mostrar
        vista.getbtnMostrar().addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new FormMostrar();
                }
            }
        );
    }

    public void agregarLibro() {
        formGuardar = new FormGuardar();
        JTextField[] datos = formGuardar.DatosPrincipales();

        formGuardar.BtnGuardarDatos().addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent a) {
                    if (!formGuardar.CamposVacios()) {
                        vista
                            .setTabla()
                            .addRow(new Object[] {
                                datos[0].getText(),
                                datos[1].getText(),
                                datos[2].getText(),
                                datos[3].getText(),
                            });
                        for (JTextField limpiar : datos) {
                            limpiar.setText("");
                        }
                        formGuardar.formulario().dispose();
                    } else if (formGuardar.CamposVacios()) {
                        JOptionPane.showMessageDialog(
                            null,
                            "Todos los campos son obligatorios",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
        );
    }

    public void Cancelar() {
        formGuardar.BtnCancelarForm().addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    formGuardar.formulario().dispose();
                }
            }
        );
    }
}
