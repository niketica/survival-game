package nl.aniketic.survival.game.level;

import nl.aniketic.survival.game.common.SurvivalGameConstants;

public class Node {

    private final int size = SurvivalGameConstants.TILE_SIZE;
    private final int x;
    private final int y;
    private final int worldX;
    private final int worldY;

    private TileType tileType;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        worldX = x * size;
        worldY = y * size;
        tileType = TileType.GRASS;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
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
