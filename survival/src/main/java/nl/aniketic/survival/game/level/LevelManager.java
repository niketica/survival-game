package nl.aniketic.survival.game.level;

import nl.aniketic.survival.engine.display.PanelComponent;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static nl.aniketic.survival.game.common.SurvivalGameConstants.SCREEN_HEIGHT;
import static nl.aniketic.survival.game.common.SurvivalGameConstants.SCREEN_WIDTH;
import static nl.aniketic.survival.game.common.SurvivalGameConstants.TILE_SIZE;

public class LevelManager implements PanelComponent {

    private static final int CENTER_X = SCREEN_WIDTH / 2 - TILE_SIZE / 2;
    private static final int CENTER_Y = SCREEN_HEIGHT / 2 - TILE_SIZE / 2;

    private final TileImageManager tileImageManager;

    private List<Node> nodes;
    private int offsetX;
    private int offsetY;

    public LevelManager() {
        nodes = new ArrayList<>();
        offsetX = 0;
        offsetY = 0;
        tileImageManager = new TileImageManager();
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        for (Node node : nodes) {
            int nodeX = node.getScreenX() - offsetX;
            int nodeY = node.getScreenY() - offsetY;
            int nodeSize = node.getSize();

            BufferedImage image = tileImageManager.getTileImage(node.getTileType());
            if (nodeX + nodeSize >= 0 && nodeX <= SCREEN_WIDTH
                    && nodeY + nodeSize >= 0 && nodeY <= SCREEN_HEIGHT) {
                g2.drawImage(image, nodeX, nodeY, null);
            }
        }
    }

    public List<Node> getNodes() {
        return new ArrayList<>(nodes);
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX - CENTER_X;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY - CENTER_Y;
    }
}
