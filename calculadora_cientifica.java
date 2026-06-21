import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class calculadora_cientifica {

    public static void main(String[] args) {
        // 1. Crear la ventana (JFrame)
        JFrame ventana = new JFrame("Mi Aplicación con Zed");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(400, 200);

        // 2. Crear los componentes
        JLabel etiqueta = new JLabel("Escribe algo:");
        JTextField campoTexto = new JTextField(15);
        JButton boton = new JButton("Saludar");
        JLabel resultado = new JLabel("Aquí aparecerá tu saludo");

        // 3. Ajustar la acción del botón (¡Esto es lo divertido!)
        boton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nombre = campoTexto.getText();
                    if (!nombre.isEmpty()) {
                        resultado.setText("¡Hola, " + nombre + "!");
                    } else {
                        resultado.setText("¡Escribe tu nombre primero!");
                    }
                }
            }
        );

        // 4. Crear un panel y ajustar su diseño para organizar todo
        JPanel panel = new JPanel();
        panel.add(etiqueta);
        panel.add(campoTexto);
        panel.add(boton);
        panel.add(resultado);

        // 5. Añadir el panel a la ventana
        ventana.add(panel);

        // 6. Hacer visible la ventana
        ventana.setVisible(true);
    }
}
