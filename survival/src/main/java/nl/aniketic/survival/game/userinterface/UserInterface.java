package nl.aniketic.survival.game.userinterface;

import nl.aniketic.survival.engine.display.PanelComponent;
import nl.aniketic.survival.game.common.ImageUtil;
import nl.aniketic.survival.game.gamestate.SurvivalGameStateManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import static nl.aniketic.survival.game.common.SurvivalGameConstants.SCREEN_HEIGHT;
import static nl.aniketic.survival.game.common.SurvivalGameConstants.SCREEN_WIDTH;
import static nl.aniketic.survival.game.common.SurvivalGameConstants.TILE_SIZE;

public class UserInterface implements PanelComponent {

    private static final Font ARIAL_20 = new Font("Arial", Font.BOLD, 20);
    private static final Font ARIAL_40 = new Font("Arial", Font.BOLD, 40);

    private final SurvivalGameStateManager survivalGameStateManager;
    private final BufferedImage crowbar;

    private int nrOfCrowbars;

    public UserInterface(SurvivalGameStateManager survivalGameStateManager) {
        this.survivalGameStateManager = survivalGameStateManager;
        crowbar = loadImage("/entity/object/crowbar.png");
        nrOfCrowbars = 0;
    }

    protected BufferedImage loadImage(String path) {
        BufferedImage image = ImageUtil.loadImage(path);
        image = ImageUtil.scaleImage(image, TILE_SIZE / 2, TILE_SIZE / 2);
        return image;
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        paintCrowbarInInv(g2);
        drawPaused(g2);
        drawLevelClear(g2);
        drawGameOver(g2);
    }

    private void paintCrowbarInInv(Graphics2D g2) {
        int x = 20;
        int y = 20;

        g2.drawImage(crowbar, x, y, null);
        x += crowbar.getWidth();
        y += crowbar.getHeight();

        g2.setColor(Color.WHITE);
        g2.setFont(ARIAL_20);
        g2.drawString("x" + nrOfCrowbars, x, y);
    }

    private void drawPaused(Graphics2D g2) {
        if (survivalGameStateManager.isPause()) {
            g2.setColor(Color.WHITE);
            g2.setFont(ARIAL_40);
            g2.drawString("PAUSED", SCREEN_WIDTH / 2 - 20, SCREEN_HEIGHT / 2);
            g2.drawString("Press ESC to resume game", SCREEN_WIDTH / 2 - 200, SCREEN_HEIGHT / 2 + 40);
            g2.drawString("Press R to restart game", SCREEN_WIDTH / 2 - 140, SCREEN_HEIGHT / 2 + 80);
            g2.drawString("Press Q to exit game", SCREEN_WIDTH / 2 - 120, SCREEN_HEIGHT / 2 + 120);
        }
    }

    private void drawLevelClear(Graphics2D g2) {
        if (survivalGameStateManager.isLevelClear()) {
            g2.setColor(Color.WHITE);
            g2.setFont(ARIAL_40);
            g2.drawString("LEVEL CLEARED!", SCREEN_WIDTH / 2 - 100, SCREEN_HEIGHT / 2);
            g2.drawString("Press R to restart game", SCREEN_WIDTH / 2 - 140, SCREEN_HEIGHT / 2 + 40);
            g2.drawString("Press Q to exit game", SCREEN_WIDTH / 2 - 120, SCREEN_HEIGHT / 2 + 80);
        }
    }

    private void drawGameOver(Graphics2D g2) {
        if (survivalGameStateManager.isGameOver()) {
            g2.setColor(Color.WHITE);
            g2.setFont(ARIAL_40);
            g2.drawString("YOU DIED!", SCREEN_WIDTH / 2 - 100, SCREEN_HEIGHT / 2);
            g2.drawString("Press R to restart game", SCREEN_WIDTH / 2 - 140, SCREEN_HEIGHT / 2 + 40);
            g2.drawString("Press Q to exit game", SCREEN_WIDTH / 2 - 120, SCREEN_HEIGHT / 2 + 80);
        }
    }

    public void setNrOfCrowbars(int nrOfCrowbars) {
        this.nrOfCrowbars = nrOfCrowbars;
    }
}
