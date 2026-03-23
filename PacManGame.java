import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PacManGame extends JPanel implements ActionListener, KeyListener {

    private Timer timer;

    private int pacmanX = 50;
    private int pacmanY = 50;
    private int size = 30;

    private int dx = 0;
    private int dy = 0;

    public PacManGame() {
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.BLACK);

        timer = new Timer(100, this); // velocidad
        timer.start();

        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar Pac-Man
        g.setColor(Color.YELLOW);
        g.fillOval(pacmanX, pacmanY, size, size);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pacmanX += dx;
        pacmanY += dy;
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -5;
            dy = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 5;
            dy = 0;
        }
        if (key == KeyEvent.VK_UP) {
            dx = 0;
            dy = -5;
        }
        if (key == KeyEvent.VK_DOWN) {
            dx = 0;
            dy = 5;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pac-Man en Java");
        PacManGame game = new PacManGame();

        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
