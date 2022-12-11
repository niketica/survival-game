package nl.aniketic.survival.game.entity;

import nl.aniketic.survival.engine.display.PanelComponent;
import nl.aniketic.survival.engine.gamestate.GameObject;
import nl.aniketic.survival.game.common.ImageUtil;
import nl.aniketic.survival.game.level.Node;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import static nl.aniketic.survival.game.common.SurvivalGameConstants.SCREEN_HEIGHT;
import static nl.aniketic.survival.game.common.SurvivalGameConstants.SCREEN_WIDTH;
import static nl.aniketic.survival.game.common.SurvivalGameConstants.TILE_SIZE;

public class KeyObject implements GameObject, PanelComponent {

    private static final int CENTER_X = SCREEN_WIDTH / 2 - TILE_SIZE / 2;
    private static final int CENTER_Y = SCREEN_HEIGHT / 2 - TILE_SIZE / 2;

    private BufferedImage image;

    private Node position;
    private int worldX;
    private int worldY;
    private int offsetX;
    private int offsetY;

    public KeyObject() {
        image = loadImage("/entity/object/key.png");
        worldX = 0;
        worldY = 0;
    }

    private BufferedImage loadImage(String path) {
        BufferedImage image = ImageUtil.loadImage(path);
        image = ImageUtil.scaleImage(image, TILE_SIZE, TILE_SIZE);
        return image;
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        int nodeX = worldX - offsetX;
        int nodeY = worldY - offsetY;
        int nodeSize = TILE_SIZE;
        if (nodeX + nodeSize >= 0 && nodeX <= SCREEN_WIDTH
                && nodeY + nodeSize >= 0 && nodeY <= SCREEN_HEIGHT) {
            g2.drawImage(image, nodeX, nodeY, null);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void activatePanelComponent() {
        activate();
    }

    @Override
    public void deactivatePanelComponent() {
        deactivate();
    }

    public Node getPosition() {
        return position;
    }

    public void setPosition(Node position) {
        this.position = position;
        worldX = position.getWorldX();
        worldY = position.getWorldY();
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

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX - CENTER_X;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY - CENTER_Y;
    }
}
