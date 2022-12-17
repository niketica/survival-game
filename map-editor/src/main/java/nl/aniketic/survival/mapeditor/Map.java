package nl.aniketic.survival.mapeditor;

import nl.aniketic.survival.controls.Key;
import nl.aniketic.survival.engine.display.PanelComponent;
import nl.aniketic.survival.engine.gamestate.GameObject;
import nl.aniketic.survival.game.level.MapLoader;
import nl.aniketic.survival.game.level.Node;
import nl.aniketic.survival.game.level.TileImageManager;
import nl.aniketic.survival.game.level.TileType;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static nl.aniketic.survival.game.common.SurvivalGameConstants.TILE_SIZE;

public class Map implements PanelComponent, GameObject {

    private final TileImageManager tileImageManager;
    private final List<Node> nodes;
    private List<EditorEntity> entities;

    private final int keyInputCount = 1;
    private int currentKeyInputCount = keyInputCount;

    private final int offsetIncrementValue = 10;
    private int offsetX = 0;
    private int offsetY = 0;

    public Map(TileImageManager tileImageManager) {
        this.tileImageManager = tileImageManager;
        nodes = new ArrayList<>();
        entities = new ArrayList<>();
    }

    public void loadMap() {
        int[][] map = MapLoader.loadMap("maps/map01.txt");
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                int mapValue = map[row][col];
                Node node = new Node(col, row);
                node.setTileType(TileType.getByMapValue(mapValue));
                nodes.add(node);
            }
        }
    }

    @Override
    public void update() {
        if (currentKeyInputCount >= keyInputCount) {
            handleKeyInput();
        } else {
            currentKeyInputCount++;
        }
    }

    private void handleKeyInput() {
        boolean anyKeyPressed = Arrays.stream(Key.values()).anyMatch(Key::isPressed);
        if (anyKeyPressed) {
            currentKeyInputCount = 0;
        } else {
            return;
        }

        if (Key.UP.isPressed()) {
            offsetY += offsetIncrementValue;
        }
        if (Key.DOWN.isPressed()) {
            offsetY -= offsetIncrementValue;
        }
        if (Key.LEFT.isPressed()) {
            offsetX += offsetIncrementValue;
        }
        if (Key.RIGHT.isPressed()) {
            offsetX -= offsetIncrementValue;
        }
    }

    @Override
    public void activatePanelComponent() {
        activate();
    }

    @Override
    public void deactivatePanelComponent() {
        deactivate();
    }

    @Override
    public void paintComponent(Graphics2D g2) {
        g2.setColor(Color.WHITE);

        for (Node node : nodes) {
            int x = node.getX() * TILE_SIZE + offsetX;
            int y = node.getY() * TILE_SIZE + offsetY;
            BufferedImage image = tileImageManager.getTileImage(node.getTileType());
            g2.drawImage(image, x, y, null);
        }

        for (EditorEntity entity : entities) {
            int x = entity.getWorldX() + offsetX;
            int y = entity.getWorldY() + offsetY;
            g2.drawImage(entity.getImage(), x, y, null);
        }
    }

    public Node getClickedNode(int x, int y) {
        return nodes.stream().filter(n -> {
            int x1 = n.getWorldX() + offsetX;
            int x2 = x1 + TILE_SIZE;
            int y1 = n.getWorldY() + offsetY;
            int y2 = y1 + TILE_SIZE;
            return x >= x1 && x <= x2 && y >= y1 && y <= y2;
        }).findAny().orElse(null);
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public Node getNode(int x, int y) {
        return nodes.stream()
                .filter(n -> n.getX() == x && n.getY() == y)
                .findAny().orElse(null);
    }

    public List<EditorEntity> getEntities() {
        return entities;
    }

    public void addEntity(EditorEntity entity) {
        entities.add(entity);
    }
}
