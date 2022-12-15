package nl.aniketic.survival.game.level;

import nl.aniketic.survival.engine.display.PanelComponent;

import java.awt.Graphics2D;
import java.awt.Rectangle;
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
    private final List<TileType> solidTileTypes;

    private List<Node> nodes;
    private int offsetX;
    private int offsetY;

    public LevelManager() {
        nodes = new ArrayList<>();
        offsetX = 0;
        offsetY = 0;
        tileImageManager = new TileImageManager();
        solidTileTypes = new ArrayList<>();
        solidTileTypes.add(TileType.WALL);
        solidTileTypes.add(TileType.WATER);
        solidTileTypes.add(TileType.TREE);
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        for (Node node : nodes) {
            int nodeX = node.getWorldX() - offsetX;
            int nodeY = node.getWorldY() - offsetY;
            int nodeSize = node.getSize();

            BufferedImage image = tileImageManager.getTileImage(node.getTileType());
            if (nodeX + nodeSize >= 0 && nodeX <= SCREEN_WIDTH
                    && nodeY + nodeSize >= 0 && nodeY <= SCREEN_HEIGHT) {
                g2.drawImage(image, nodeX, nodeY, null);
            }
        }
    }

    public void setMap(int[][] map) {
        List<Node> nodes = new ArrayList<>();

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                Node node = new Node(col, row);
                int mapValue = map[row][col];
                TileType tileType = TileType.getByMapValue(mapValue);
                node.setTileType(tileType);
                node.setSolid(solidTileTypes.contains(tileType));
                nodes.add(node);
            }
        }

        this.nodes = nodes;
    }

    public List<Node> getNodes() {
        return new ArrayList<>(nodes);
    }

    public Node getNode(int x, int y) {
        return nodes.stream()
                .filter(n -> n.getX() == x && n.getY() == y)
                .findAny().orElse(null);
    }

    public Node getNodeByWorldPosition(int worldX, int worldY) {
        return nodes.stream()
                .filter(n -> {
                    boolean x1 = worldX >= n.getWorldX();
                    boolean x2 = worldX <= n.getWorldX() + TILE_SIZE;
                    boolean y1 = worldY >= n.getWorldY();
                    boolean y2 = worldY <= n.getWorldY() + TILE_SIZE;
                    return x1 && x2 && y1 && y2;
                })
                .findAny().orElse(null);
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public void setOffset(int x, int y) {
        this.offsetX = x - CENTER_X;
        this.offsetY = y - CENTER_Y;
    }

    public boolean isCollisionWithSolidNode(Rectangle collisionBody) {
        for (Node node : nodes) {
            if (node.isSolid()) {
                if (collisionBody.intersects(node.getCollisionBody())) {
                    return true;
                }
            }
        }
        return false;
    }
}
