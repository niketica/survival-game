package nl.aniketic.survival.mapeditor;

import nl.aniketic.survival.controls.EditorKeyListener;
import nl.aniketic.survival.controls.Key;
import nl.aniketic.survival.engine.display.PanelComponent;
import nl.aniketic.survival.engine.gamestate.GameObject;
import nl.aniketic.survival.game.level.Node;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static nl.aniketic.survival.game.common.SurvivalGameConstants.TILE_SIZE;

public class Map implements PanelComponent, GameObject {

    private final List<Node> nodes;

    private final int keyInputCount = 10;
    private int currentKeyInputCount = keyInputCount;

    private final int offsetIncrementValue = 10;
    private int offsetX = 0;
    private int offsetY = 0;

    public Map() {
        nodes = new ArrayList<>();
    }

    public void loadMap(int width, int height) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Node node = new Node(x, y);
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
            g2.drawRect(x, y, TILE_SIZE, TILE_SIZE);
        }
    }
}
