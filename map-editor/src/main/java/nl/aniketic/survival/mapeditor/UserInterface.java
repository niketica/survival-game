package nl.aniketic.survival.mapeditor;

import nl.aniketic.survival.engine.display.PanelComponent;
import nl.aniketic.survival.engine.gamestate.GameObject;
import nl.aniketic.survival.game.level.TileImageManager;
import nl.aniketic.survival.game.level.TileType;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static nl.aniketic.survival.game.common.SurvivalGameConstants.SCREEN_HEIGHT;
import static nl.aniketic.survival.game.common.SurvivalGameConstants.SCREEN_WIDTH;
import static nl.aniketic.survival.game.common.SurvivalGameConstants.TILE_SIZE;

public class UserInterface implements PanelComponent, GameObject {

    public static final int WIDTH = SCREEN_WIDTH;
    public static final int HEIGHT = TILE_SIZE * 3;
    public static final int X_POS = 0;
    public static final int Y_POS = SCREEN_HEIGHT - HEIGHT;

    private static final Font ARIAL_20 = new Font("Arial", Font.PLAIN, 20);

    private final TileImageManager tileImageManager;
    private final List<EditorTile> editorTiles;
    private final List<EditorEntity> editorEntities;
    private AbstractEditorItem selectedEditorItem;

    private List<Button> buttons;

    public UserInterface(TileImageManager tileImageManager) {
        this.tileImageManager = tileImageManager;
        editorTiles = new ArrayList<>();
        editorEntities = new ArrayList<>();
        buttons = new ArrayList<>();

        createEditorTiles();
        createEditorEntities();
        createEditorButtons();
    }

    private void createEditorTiles() {
        int offsetX = 20;
        int offsetY = 20;
        TileType[] tileTypes = TileType.values();
        for (int i = 0; i < tileTypes.length; i++) {
            int x = X_POS + (i * TILE_SIZE) + offsetX;
            int y = Y_POS + offsetY;
            editorTiles.add(new EditorTile(tileImageManager, tileTypes[i], x, y));
        }
    }

    private void createEditorEntities() {
        int offsetX = 20;
        int offsetY = TILE_SIZE + 40;
        EntityValue[] entityValues = EntityValue.values();
        for (int i=0; i<entityValues.length; i++) {
            EntityValue entityValue = entityValues[i];
            int x = X_POS + i * TILE_SIZE + offsetX;
            int y = Y_POS + offsetY;
            editorEntities.add(new EditorEntity(x, y, entityValue));
        }
    }

    private void createEditorButtons() {
        int width = 100;
        int height = 40;
        int x = WIDTH - width - 20;
        int y = Y_POS + HEIGHT - height - 20;
        buttons.add(new Button(x, y, width, height, ButtonValue.EXPORT));
    }

    @Override
    public void update() {
        AbstractEditorItem selected;
        selected = editorTiles.stream().filter(EditorTile::isSelected).findAny().orElse(null);
        if (selected == null) {
            selected = editorEntities.stream().filter(EditorEntity::isSelected).findAny().orElse(null);
        }
        this.selectedEditorItem = selected;
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
        drawSelectableEntities(g2);

        for (Button button : buttons) {
            drawButton(g2, button);
        }
    }

    private void drawPanel(Graphics2D g2) {
        g2.setColor(Color.GRAY);
        int height = TILE_SIZE * 3;
        g2.fillRect(X_POS, Y_POS, WIDTH, height);
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(X_POS, Y_POS, WIDTH, 10);
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
        int offsetX = -100;
        int offsetY = 40;
        int x = X_POS + WIDTH + offsetX;
        int y = Y_POS + offsetY;
        int borderThickness = 10;

        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(x - borderThickness, y - borderThickness, TILE_SIZE + borderThickness * 2,
                TILE_SIZE + borderThickness * 2);

        if (selectedEditorItem != null) {
            g2.drawImage(selectedEditorItem.getImage(), x, y, null);
        }
    }

    public List<EditorTile> getEditorTiles() {
        return editorTiles;
    }

    private void drawButton(Graphics2D g2, Button button) {
        int x = button.getX();
        int y = button.getY();
        int width = button.getWidth();
        int height = button.getHeight();
        String text = button.getText();

        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(x, y, width, height);
        g2.setColor(Color.GRAY);
        g2.fillRect(x + 2, y + 2, width - 4, height - 4);

        g2.setFont(ARIAL_20);
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 10, y + height - 10);
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public AbstractEditorItem getSelectedItem() {
        return selectedEditorItem;
    }

    private void drawSelectableEntities(Graphics2D g2) {
        for (EditorEntity entity : editorEntities) {
            g2.setColor(Color.DARK_GRAY);
            g2.drawRect(entity.getX(), entity.getY(), 64, 64);
            g2.drawImage(entity.getImage(), entity.getX(), entity.getY(), null);
        }
    }

    public List<EditorEntity> getEditorEntities() {
        return editorEntities;
    }

    public List<AbstractEditorItem> getEditorItem() {
        ArrayList<AbstractEditorItem> list = new ArrayList<>(this.editorTiles);
        list.addAll(editorEntities);
        return list;
    }
}
