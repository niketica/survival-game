package nl.aniketic.survival.engine.gamestate;

import nl.aniketic.survival.engine.display.DisplayManager;

public class GameRunnable implements Runnable {

    private static final int FPS = 60;

    private boolean running;
    private GameStateManager gameStateManager;

    public GameRunnable(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
    }

    @Override
    public void run() {
        double drawInterval = 1_000_000_000 / FPS; // 0.016666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        running = true;

        while (running) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {

                // 1 UPDATE: update information such as character positions
                gameStateManager.update();

                // 2 DRAW: draw the screen with the updated information
                DisplayManager.updateDisplay();

                delta--;
                drawCount++;
            }

            if (timer >= 1_000_000_000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void stop() {
        running = false;
    }
}
