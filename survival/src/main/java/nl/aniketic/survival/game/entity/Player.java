package nl.aniketic.survival.game.entity;

import nl.aniketic.survival.engine.display.PanelComponent;
import nl.aniketic.survival.engine.gamestate.GameObject;
import nl.aniketic.survival.game.common.Direction;
import nl.aniketic.survival.game.common.ImageUtil;
import nl.aniketic.survival.game.level.Node;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import static nl.aniketic.survival.game.common.SurvivalGameConstants.SCREEN_HEIGHT;
import static nl.aniketic.survival.game.common.SurvivalGameConstants.SCREEN_WIDTH;
import static nl.aniketic.survival.game.common.SurvivalGameConstants.TILE_SIZE;

public class Player implements GameObject, PanelComponent {

    private static final int CENTER_X = SCREEN_WIDTH / 2 - TILE_SIZE / 2;
    private static final int CENTER_Y = SCREEN_HEIGHT / 2 - TILE_SIZE / 2;

    private BufferedImage[] frontFrames;
    private BufferedImage[] backFrames;
    private BufferedImage[] leftFrames;
    private BufferedImage[] rightFrames;

    private int frameUpdateRate = 24;
    private int frameUpdateCount = 0;
    private int frameIndex;

    private Direction direction;
    private Node position;
    private int worldX;
    private int worldY;

    private int speed = 3;

    public Player() {
        loadPlayerFrames();
        frameIndex = 0;
        direction = Direction.DOWN;
    }

    private void loadPlayerFrames() {
        BufferedImage front1 = loadImage("/entity/player/front_1.png");
        BufferedImage front2 = loadImage("/entity/player/front_2.png");
        frontFrames = new BufferedImage[2];
        frontFrames[0] = front1;
        frontFrames[1] = front2;
        BufferedImage back1 = loadImage("/entity/player/back_1.png");
        BufferedImage back2 = loadImage("/entity/player/back_2.png");
        backFrames = new BufferedImage[2];
        backFrames[0] = back1;
        backFrames[1] = back2;
        BufferedImage left1 = loadImage("/entity/player/left_1.png");
        BufferedImage left2 = loadImage("/entity/player/left_2.png");
        leftFrames = new BufferedImage[2];
        leftFrames[0] = left1;
        leftFrames[1] = left2;
        BufferedImage right1 = loadImage("/entity/player/right_1.png");
        BufferedImage right2 = loadImage("/entity/player/right_2.png");
        rightFrames = new BufferedImage[2];
        rightFrames[0] = right1;
        rightFrames[1] = right2;
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
        g2.drawImage(getCurrentFrame(), CENTER_X, CENTER_Y, null);
    }

    private BufferedImage getCurrentFrame() {
        switch (direction) {
            case UP:
                return backFrames[frameIndex];
            case DOWN:
                return frontFrames[frameIndex];
            case LEFT:
                return leftFrames[frameIndex];
            case RIGHT:
                return rightFrames[frameIndex];
            default:
                throw new IllegalStateException("Unknown direction: " + direction);
        }
    }

    @Override
    public void activatePanelComponent() {
        this.activate();
    }

    @Override
    public void deactivatePanelComponent() {
        this.deactivate();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Node getPosition() {
        return position;
    }

    public void setPosition(Node position) {
        this.position = position;
        setWorldX(position.getWorldX());
        setWorldY(position.getWorldY());
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public void move() {
        switch (direction) {
            case UP:
                worldY -= speed;
                break;
            case DOWN:
                worldY += speed;
                break;
            case LEFT:
                worldX -= speed;
                break;
            case RIGHT:
                worldX += speed;
                break;
            default:
                throw new IllegalStateException("Unknown direction: " + direction);
        }
    }
}
