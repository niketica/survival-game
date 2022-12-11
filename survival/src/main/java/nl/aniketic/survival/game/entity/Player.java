package nl.aniketic.survival.game.entity;

import nl.aniketic.survival.engine.display.PanelComponent;
import nl.aniketic.survival.engine.gamestate.GameObject;
import nl.aniketic.survival.game.common.ImageUtil;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import static nl.aniketic.survival.game.common.SurvivalGameConstants.TILE_SIZE;

public class Player implements GameObject, PanelComponent {

    private BufferedImage[] frontFrames;
    private BufferedImage[] backFrames;
    private BufferedImage[] leftFrames;
    private BufferedImage[] rightFrames;

    private int frameUpdateRate = 24;
    private int frameUpdateCount = 0;
    private int frameIndex;

    public Player() {
        loadPlayerFrames();
        frameIndex = 0;
    }

    private void loadPlayerFrames() {
        BufferedImage front1 = loadImage("/entity/player/front_1.png");
        BufferedImage front2 = loadImage("/entity/player/front_2.png");
        frontFrames = new BufferedImage[2];
        frontFrames[0] = front1;
        frontFrames[1] = front2;
    }

    private BufferedImage loadImage(String path) {
        BufferedImage image = ImageUtil.loadImage(path);
        image = ImageUtil.scaleImage(image, TILE_SIZE, TILE_SIZE);
        return image;
    }

    @Override
    public void update() {
        if (frameUpdateCount >= frameUpdateRate) {
            frameUpdateCount = 0;

            frameIndex++;
            if (frameIndex > 1) {
                frameIndex = 0;
            }
        } else {
            frameUpdateCount++;
        }

    }

    @Override
    public void paintComponent(Graphics2D g2) {
        g2.drawImage(frontFrames[frameIndex], 100, 100, null);
    }

    @Override
    public void activatePanelComponent() {
        this.activate();
    }

    @Override
    public void deactivatePanelComponent() {
        this.deactivate();
    }
}
