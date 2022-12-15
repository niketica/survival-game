package nl.aniketic.survival.game.entity;

import nl.aniketic.survival.engine.display.PanelComponent;
import nl.aniketic.survival.engine.gamestate.GameObject;
import nl.aniketic.survival.game.common.Direction;
import nl.aniketic.survival.game.common.ImageUtil;
import nl.aniketic.survival.game.level.Node;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import static nl.aniketic.survival.game.common.SurvivalGameConstants.SCREEN_HEIGHT;
import static nl.aniketic.survival.game.common.SurvivalGameConstants.SCREEN_WIDTH;
import static nl.aniketic.survival.game.common.SurvivalGameConstants.TILE_SIZE;

public class BaseEntity implements GameObject, PanelComponent {

    protected static final int CENTER_X = SCREEN_WIDTH / 2 - TILE_SIZE / 2;
    protected static final int CENTER_Y = SCREEN_HEIGHT / 2 - TILE_SIZE / 2;

    protected Rectangle collisionBody;
    protected int collisionOffset;

    protected BufferedImage[] leftSprites;
    protected BufferedImage[] rightSprites;

    protected final int spriteUpdateRate = 12;
    protected int spriteUpdateCount = 0;
    protected int spriteIndex = 0;

    protected Direction direction = Direction.RIGHT;
    protected Node position;
    protected int worldX;
    protected int worldY;
    protected int offsetX;
    protected int offsetY;

    protected int speed = 3;
    protected boolean moving;

    protected int maxHitPoints = -1;
    protected int currentHitPoints = -1;

    protected int iFrames = 120;
    protected int currentIFrameCount = iFrames;

    @Override
    public void paintComponent(Graphics2D g2) {
        int nodeX = worldX - offsetX;
        int nodeY = worldY - offsetY;
        int nodeSize = TILE_SIZE;
        if (nodeX + nodeSize >= 0 && nodeX <= SCREEN_WIDTH
                && nodeY + nodeSize >= 0 && nodeY <= SCREEN_HEIGHT) {
            g2.drawImage(getCurrentSprite(), nodeX, nodeY, null);
        }
    }

    @Override
    public void update() {
        updateSprite();
        updateIFrames();
    }

    @Override
    public void activatePanelComponent() {
        activate();
    }

    @Override
    public void deactivatePanelComponent() {
        deactivate();
    }

    public void setWorldPosition(Node position) {
        this.position = position;
        int worldX = position.getWorldX();
        int worldY = position.getWorldY();
        this.worldX = worldX;
        this.worldY = worldY;
        collisionBody.x = worldX + collisionOffset;
        collisionBody.y = worldY + collisionOffset;
    }

    public void setPosition(Node position) {
        this.position = position;
    }

    public void setDirection(Direction direction) {
        if (direction == Direction.UP || direction == Direction.DOWN) {
            return;
        }
        this.direction = direction;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
        collisionBody.x = worldX + collisionOffset;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
        collisionBody.y = worldY + collisionOffset;
    }

    public int getSpeed() {
        return speed;
    }

    public Rectangle getCollisionBody() {
        return new Rectangle(collisionBody);
    }

    public void setOffset(int x, int y) {
        this.offsetX = x - CENTER_X;
        this.offsetY = y - CENTER_Y;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    /**
     * Sets collision body to roughly 70% of TILE_SIZE
     */
    protected void setBasicCollisionBody() {
        int collisionSize = (int) (TILE_SIZE * 0.7);
        if (collisionSize % 2 > 0) {
            collisionSize--;
        }
        collisionOffset = (TILE_SIZE - collisionSize) / 2;
        collisionBody = new Rectangle(0, 0, collisionSize, collisionSize);
    }

    protected BufferedImage loadImage(String path) {
        BufferedImage image = ImageUtil.loadImage(path);
        image = ImageUtil.scaleImage(image, TILE_SIZE, TILE_SIZE);
        return image;
    }

    protected void loadSprite(String path) {
        rightSprites = new BufferedImage[1];
        rightSprites[0] = loadImage(path);
    }

    protected BufferedImage getCurrentSprite() {
        int i = 0;
        if (moving) {
            i = spriteIndex;
        }

        switch (direction) {
            case LEFT:
                return leftSprites[i];
            case RIGHT:
                return rightSprites[i];
            default:
                throw new IllegalStateException("Unknown direction: " + direction);
        }
    }

    protected void updateSprite() {
        if (spriteUpdateCount >= spriteUpdateRate) {
            spriteUpdateCount = 0;

            spriteIndex++;
            if (spriteIndex > 2) {
                spriteIndex = 0;
            }
        } else {
            spriteUpdateCount++;
        }
    }

    protected void updateIFrames() {
        if (currentIFrameCount < iFrames) {
            currentIFrameCount++;
        }
    }

    public int getCurrentHitPoints() {
        return currentHitPoints;
    }

    public void setCurrentHitPoints(int hitPoints) {
        if (currentIFrameCount >= iFrames) {
            this.currentHitPoints = hitPoints;
            currentIFrameCount = 0;
            System.out.println("BRAINS!!!");
        }
    }

    protected boolean flickerFromHit() {
        return currentIFrameCount < iFrames && currentIFrameCount % 8 > 0;
    }

    public Node getPosition() {
        return position;
    }

    public int getCollisionOffset() {
        return collisionOffset;
    }
}
