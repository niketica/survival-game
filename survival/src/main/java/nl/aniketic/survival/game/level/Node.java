package nl.aniketic.survival.game.level;

import nl.aniketic.survival.game.common.SurvivalGameConstants;

public class Node {

    private final int size = SurvivalGameConstants.TILE_SIZE;
    private final int x;
    private final int y;
    private final int screenX;
    private final int screenY;

    private TileType tileType;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        screenX = x * size;
        screenY = y * size;
        tileType = TileType.GRASS;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public int getSize() {
        return size;
    }

    public TileType getTileType() {
        return tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }
}
