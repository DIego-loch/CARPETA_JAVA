import java.awt.*;
import javax.swing.*;

public class renderizado extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Renderizar texto
        g.drawString("Vos me pelas la verga", 100, 100);

        // Renderizar un rectángulo
        g.drawRect(50, 50, 1000, 100);

        // Renderizar un círculo
        g.drawOval(300, 50, 100, 100);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Renderizado");

        renderizado panel = new renderizado();

        frame.add(panel);
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
