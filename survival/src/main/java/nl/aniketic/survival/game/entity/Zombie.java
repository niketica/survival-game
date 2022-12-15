package nl.aniketic.survival.game.entity;

import nl.aniketic.survival.game.level.Node;

import java.awt.image.BufferedImage;

public class Zombie extends BaseEntity {

    public Zombie() {
        loadZombieFrames();
        setBasicCollisionBody();
        currentHitPoints = 100;
    }

    private void loadZombieFrames() {
        BufferedImage left1 = loadImage("/entity/zombie/zombie_4.png");
        BufferedImage left2 = loadImage("/entity/zombie/zombie_5.png");
        BufferedImage left3 = loadImage("/entity/zombie/zombie_6.png");
        leftSprites = new BufferedImage[3];
        leftSprites[0] = left1;
        leftSprites[1] = left2;
        leftSprites[2] = left3;
        BufferedImage right1 = loadImage("/entity/zombie/zombie_1.png");
        BufferedImage right2 = loadImage("/entity/zombie/zombie_2.png");
        BufferedImage right3 = loadImage("/entity/zombie/zombie_3.png");
        rightSprites = new BufferedImage[3];
        rightSprites[0] = right1;
        rightSprites[1] = right2;
        rightSprites[2] = right3;
    }

    public void setPosition(Node position) {
        this.position = position;
        int worldX = position.getWorldX();
        int worldY = position.getWorldY();
        this.worldX = worldX;
        this.worldY = worldY;
        collisionBody.x = worldX + collisionOffset;
        collisionBody.y = worldY + collisionOffset;
    }
}
