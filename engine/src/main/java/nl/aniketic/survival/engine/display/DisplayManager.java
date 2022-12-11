package nl.aniketic.survival.engine.display;

import javax.swing.JFrame;
import java.awt.event.KeyListener;

public final class DisplayManager {

    private static final int SCREEN_WIDTH = 1600;
    private static final int SCREEN_HEIGHT = 1200;
    private static GamePanel gamePanel;

    public static void createDisplay(String title) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle(title);

        gamePanel = new GamePanel(SCREEN_WIDTH, SCREEN_HEIGHT);
        window.add(gamePanel);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static void updateDisplay() {
        gamePanel.repaint();
    }

    public static void addKeyListener(KeyListener keyListener) {
        gamePanel.addKeyListener(keyListener);
    }

    public static void clearGamePanel() {
        GamePanel.cleanGamePanel();
    }
}
