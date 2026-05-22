import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Calculadora extends JFrame implements ActionListener {

    // 1. Declaramos los componentes visuales
    private JTextField pantalla;
    private JButton botonSuma, botonResultado;
    private double num1 = 0,
        num2 = 0,
        resultado = 0;

    // Constructor: Aquí "armamos" la ventana
    public Calculadora() {
        // Configuración de la ventana principal
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 2. Crear la pantalla de texto donde se ven los números
        pantalla = new JTextField();
        pantalla.setFont(new Font("Arial", Font.PLAIN, 24));
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        add(pantalla, BorderLayout.NORTH); // La ponemos arriba

        // 3. Crear un panel para los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 4, 5, 5)); // Rejilla de botones

        // 4. Crear un par de botones de prueba
        botonSuma = new JButton("+");
        botonResultado = new JButton("=");

        // Indicarle a los botones que escuchen cuando alguien hace "clic"
        botonSuma.addActionListener(this);
        botonResultado.addActionListener(this);

        // Añadir botones al panel
        panelBotones.add(botonSuma);
        panelBotones.add(botonResultado);

        // Añadir el panel de botones al centro de la ventana
        add(panelBotones, BorderLayout.CENTER);

        // Hacer la ventana visible
        setVisible(true);
    }

    // 5. La lógica de los clics (¿Qué pasa cuando presionas un botón?)
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonSuma) {
            num1 = Double.parseDouble(pantalla.getText());
            pantalla.setText(""); // Limpiamos pantalla para el siguiente número
        } else if (e.getSource() == botonResultado) {
            num2 = Double.parseDouble(pantalla.getText());
            resultado = num1 + num2;
            pantalla.setText(String.valueOf(resultado));
        }
    }

    public static void main(String[] args) {
        // Ejecutamos la interfaz gráfica en el hilo correcto
        SwingUtilities.invokeLater(() -> new Calculadora());
    }
}
