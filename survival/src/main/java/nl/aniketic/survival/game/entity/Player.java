package nl.aniketic.survival.game.entity;

import nl.aniketic.survival.game.common.Direction;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends BaseEntity {

    private BufferedImage[] batSpritesLeft;
    private BufferedImage[] batSpritesRight;
    private BufferedImage[] batEffectSpritesRight;
    private BufferedImage[] batEffectSpritesLeft;
    private int currentBatIndex;

    private Rectangle batHitBox;

    public Player() {
        loadPlayerFrames();
        setBasicCollisionBody();
        maxHitPoints = 100;
        currentHitPoints = maxHitPoints;
        currentBatIndex = 4;
        batHitBox = new Rectangle(0, 0, 32, 44);
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
        batSpritesRight[0] = bat2;
        batSpritesRight[1] = bat4;
        batSpritesRight[2] = bat4;
        batSpritesRight[3] = bat1;

        BufferedImage bat5 = loadImage("/entity/soldier/soldier_bat_5.png");
        BufferedImage bat6 = loadImage("/entity/soldier/soldier_bat_6.png");
        BufferedImage bat7 = loadImage("/entity/soldier/soldier_bat_7.png");
        BufferedImage bat8 = loadImage("/entity/soldier/soldier_bat_8.png");
        batSpritesLeft = new BufferedImage[4];
        batSpritesLeft[0] = bat6;
        batSpritesLeft[1] = bat8;
        batSpritesLeft[2] = bat8;
        batSpritesLeft[3] = bat5;

        BufferedImage batFx1 = loadImage("/effects/bat/bat_effect_1.png");
        BufferedImage batFx2 = loadImage("/effects/bat/bat_effect_2.png");
        BufferedImage batFx3 = loadImage("/effects/bat/bat_effect_3.png");
        BufferedImage batFx4 = loadImage("/effects/bat/bat_effect_4.png");
        batEffectSpritesRight = new BufferedImage[4];
        batEffectSpritesRight[0] = batFx1;
        batEffectSpritesRight[1] = batFx2;
        batEffectSpritesRight[2] = batFx3;
        batEffectSpritesRight[3] = batFx4;

        BufferedImage batFx5 = loadImage("/effects/bat/bat_effect_5.png");
        BufferedImage batFx6 = loadImage("/effects/bat/bat_effect_6.png");
        BufferedImage batFx7 = loadImage("/effects/bat/bat_effect_7.png");
        BufferedImage batFx8 = loadImage("/effects/bat/bat_effect_8.png");
        batEffectSpritesLeft = new BufferedImage[4];
        batEffectSpritesLeft[0] = batFx5;
        batEffectSpritesLeft[1] = batFx6;
        batEffectSpritesLeft[2] = batFx7;
        batEffectSpritesLeft[3] = batFx8;
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        drawHitBox(g2);

        if (flickerFromHit()) {
            return;
        }
        g2.drawImage(getCurrentSprite(), CENTER_X, CENTER_Y, null);
        drawBatFx(g2);
    }

    private void drawBatFx(Graphics2D g2) {
        if (currentBatIndex < 4) {
//            paintWithPlayerOffset(g2, getBatHitBox());
            BufferedImage batFx;
            int xOffset;
            if (direction == Direction.LEFT) {
                batFx = batEffectSpritesLeft[currentBatIndex];
                xOffset = -collisionBody.width;
            } else {
                batFx = batEffectSpritesRight[currentBatIndex];
                xOffset = collisionBody.width;
            }
            g2.drawImage(batFx, CENTER_X + xOffset, CENTER_Y, null);
        }
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    protected BufferedImage getCurrentSprite() {
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
            percentage = (double) currentHitPoints / maxHitPoints;
        } else {
            percentage = 0;
        }

        g2.fillRect(hitBoxX, hitBoxY, (int) (64 * percentage), 10);
    }

    public Rectangle getBatHitBox() {
        batHitBox.y = worldY + 20;

        if (direction == Direction.LEFT) {
            batHitBox.x = worldX - batHitBox.width + collisionOffset;
        } else {
            batHitBox.x = worldX + collisionBody.width;
        }

        return new Rectangle(batHitBox);
    }

    public void setCurrentBatIndex(int currentBatIndex) {
        this.currentBatIndex = currentBatIndex;
    }
}
