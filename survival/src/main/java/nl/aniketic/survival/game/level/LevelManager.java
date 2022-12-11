package nl.aniketic.survival.game.level;

import nl.aniketic.survival.engine.display.PanelComponent;
import nl.aniketic.survival.game.common.SurvivalGameConstants;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import static nl.aniketic.survival.game.common.SurvivalGameConstants.SCREEN_HEIGHT;
import static nl.aniketic.survival.game.common.SurvivalGameConstants.SCREEN_WIDTH;

public class LevelManager implements PanelComponent {

    private List<Node> nodes;
    private int offsetX;
    private int offsetY;

    public LevelManager() {
        nodes = new ArrayList<>();
        offsetX = 0;
        offsetY = 0;
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        for (Node node : nodes) {
            int nodeX = node.getScreenX() - offsetX;
            int nodeY = node.getScreenY() - offsetY;
            int nodeSize = node.getSize();

            g2.setColor(Color.GREEN);
            if (nodeX + nodeSize >= 0 && nodeX <= SCREEN_WIDTH
                    && nodeY + nodeSize >= 0 && nodeY <= SCREEN_HEIGHT) {
                g2.fillRect(nodeX, nodeY, nodeSize, nodeSize);
            }
        }
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }
}
