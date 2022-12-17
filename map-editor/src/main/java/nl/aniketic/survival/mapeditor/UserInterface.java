package nl.aniketic.survival.mapeditor;

import nl.aniketic.survival.engine.display.DisplayManager;
import nl.aniketic.survival.engine.display.PanelComponent;
import nl.aniketic.survival.engine.gamestate.GameObject;
import nl.aniketic.survival.game.level.TileImageManager;
import nl.aniketic.survival.game.level.TileType;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static nl.aniketic.survival.game.common.SurvivalGameConstants.SCREEN_HEIGHT;
import static nl.aniketic.survival.game.common.SurvivalGameConstants.SCREEN_WIDTH;
import static nl.aniketic.survival.game.common.SurvivalGameConstants.TILE_SIZE;

public class UserInterface implements PanelComponent, GameObject {

    private final TileImageManager tileImageManager;
    private final List<EditorTile> editorTiles;
    private EditorTile selectedTile;

    public UserInterface() {
        tileImageManager = new TileImageManager();
        editorTiles = new ArrayList<>();

        int offsetX = 20;
        int offsetY = TILE_SIZE * 3 - 20;
        TileType[] tileTypes = TileType.values();
        for (int i = 0; i < tileTypes.length; i++) {
            int x = i * TILE_SIZE + offsetX;
            int y = SCREEN_HEIGHT - offsetY;
            editorTiles.add(new EditorTile(tileTypes[i], x, y));
        }
    }

    @Override
    public void update() {
        this.selectedTile = editorTiles.stream().filter(EditorTile::isSelected).findAny().orElse(null);
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
        drawPanel(g2);
        drawSelectableTiles(g2);
        drawSelectedTile(g2);
    }

    private void drawPanel(Graphics2D g2) {
        g2.setColor(Color.GRAY);
        int height = TILE_SIZE * 3;
        int x = 0;
        int y = SCREEN_HEIGHT - height;
        g2.fillRect(x, y, SCREEN_WIDTH, height);
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(x, y, SCREEN_WIDTH, 10);
    }

    private void drawSelectableTiles(Graphics2D g2) {
        for (EditorTile editorTile : editorTiles) {
            int x = editorTile.getX();
            int y = editorTile.getY();
            TileType tileType = editorTile.getTileType();
            BufferedImage tileImage = tileImageManager.getTileImage(tileType);
            g2.drawImage(tileImage, x, y, null);

            if (editorTile.isSelected()) {
                g2.setColor(Color.YELLOW);
                int size = editorTile.getSize();
                g2.drawRect(x, y, size - 1, size);
            }
        }
    }

    private void drawSelectedTile(Graphics2D g2) {
        int offsetY = 40;
        int x = 20;
        int y = SCREEN_HEIGHT - (TILE_SIZE * 2 - offsetY);
        int borderThickness = 10;

        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(x - borderThickness, y - borderThickness, TILE_SIZE + borderThickness * 2,
                TILE_SIZE + borderThickness * 2);

        if (selectedTile != null) {
            BufferedImage tileImage = tileImageManager.getTileImage(selectedTile.getTileType());
            g2.drawImage(tileImage, x, y, null);
        }
    }

    public List<EditorTile> getEditorTiles() {
        return editorTiles;
    }
}
