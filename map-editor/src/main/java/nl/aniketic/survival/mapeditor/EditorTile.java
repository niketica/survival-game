package nl.aniketic.survival.mapeditor;

import nl.aniketic.survival.game.level.TileType;

import static nl.aniketic.survival.game.common.SurvivalGameConstants.TILE_SIZE;

public class EditorTile {

    private final TileType tileType;
    private boolean selected;
    private int x;
    private int y;
    private int size;

    public EditorTile(TileType tileType, int x, int y) {
        this.tileType = tileType;
        this.x = x;
        this.y = y;
        this.size = TILE_SIZE;
    }

    public TileType getTileType() {
        return tileType;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }
}
