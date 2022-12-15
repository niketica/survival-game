package nl.aniketic.survival.game.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Player extends BaseEntity {

    public Player() {
        loadPlayerFrames();
        setBasicCollisionBody();
    }

    private void loadPlayerFrames() {
        BufferedImage left1 = loadImage("/entity/soldier/soldier_4.png");
        BufferedImage left2 = loadImage("/entity/soldier/soldier_5.png");
        BufferedImage left3 = loadImage("/entity/soldier/soldier_6.png");
        leftSprites = new BufferedImage[3];
        leftSprites[0] = left1;
        leftSprites[1] = left2;
        leftSprites[2] = left3;
        BufferedImage right1 = loadImage("/entity/soldier/soldier_1.png");
        BufferedImage right2 = loadImage("/entity/soldier/soldier_2.png");
        BufferedImage right3 = loadImage("/entity/soldier/soldier_3.png");
        rightSprites = new BufferedImage[3];
        rightSprites[0] = right1;
        rightSprites[1] = right2;
        rightSprites[2] = right3;
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        g2.drawImage(getCurrentSprite(), CENTER_X, CENTER_Y, null);
    }
}
