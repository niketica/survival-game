package nl.aniketic.survival.game.entity;

import nl.aniketic.survival.game.common.Direction;
import nl.aniketic.survival.game.controls.Key;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Player extends BaseEntity {

    private BufferedImage[] batSpritesLeft;
    private BufferedImage[] batSpritesRight;
    private int currentBatIndex;

    public Player() {
        loadPlayerFrames();
        setBasicCollisionBody();
        maxHitPoints = 100;
        currentHitPoints = maxHitPoints;
        currentBatIndex = 4;
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

        BufferedImage bat1 = loadImage("/entity/soldier/soldier_bat_1.png");
        BufferedImage bat2 = loadImage("/entity/soldier/soldier_bat_2.png");
        BufferedImage bat3 = loadImage("/entity/soldier/soldier_bat_3.png");
        BufferedImage bat4 = loadImage("/entity/soldier/soldier_bat_4.png");
        batSpritesRight = new BufferedImage[4];
        batSpritesRight[0] = bat1;
        batSpritesRight[1] = bat2;
        batSpritesRight[2] = bat3;
        batSpritesRight[3] = bat4;

        BufferedImage bat5 = loadImage("/entity/soldier/soldier_bat_5.png");
        BufferedImage bat6 = loadImage("/entity/soldier/soldier_bat_6.png");
        BufferedImage bat7 = loadImage("/entity/soldier/soldier_bat_7.png");
        BufferedImage bat8 = loadImage("/entity/soldier/soldier_bat_8.png");
        batSpritesLeft = new BufferedImage[4];
        batSpritesLeft[0] = bat5;
        batSpritesLeft[1] = bat6;
        batSpritesLeft[2] = bat7;
        batSpritesLeft[3] = bat8;
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        drawHitBox(g2);

        if (flickerFromHit()) {
            return;
        }
        g2.drawImage(getCurrentSprite(), CENTER_X, CENTER_Y, null);
    }

    @Override
    public void update() {
        super.update();

        if (Key.SPACE.isPressed()) {
            if (currentBatIndex >= 3) {
                currentBatIndex = 0;
                System.out.println("WACK!");
            }
        }
    }

    @Override
    protected BufferedImage getCurrentSprite() {
        if (currentBatIndex <= 3) {
            if (direction == Direction.LEFT) {
                return batSpritesLeft[currentBatIndex];
            } else {
                return batSpritesRight[currentBatIndex];
            }
        }

        return super.getCurrentSprite();
    }

    @Override
    protected void updateSprite() {
        if (spriteUpdateCount >= spriteUpdateRate) {
            spriteUpdateCount = 0;

            spriteIndex++;
            if (spriteIndex > 2) {
                spriteIndex = 0;
            }

            currentBatIndex++;
        } else {
            spriteUpdateCount++;
        }
    }

    private void drawHitBox(Graphics2D g2) {
        int hitBoxX = CENTER_X;
        int hitBoxY = CENTER_Y - 12;
        g2.setColor(Color.BLACK);
        g2.fillRect(hitBoxX, hitBoxY, 64, 10);
        g2.setColor(Color.RED);

        double percentage;
        if (currentHitPoints > 0) {
            percentage = (double)currentHitPoints / maxHitPoints;
        } else {
            percentage = 0;
        }

        g2.fillRect(hitBoxX, hitBoxY, (int) (64 * percentage), 10);
    }
}
