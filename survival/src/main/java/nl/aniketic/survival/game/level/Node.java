package nl.aniketic.survival.game.level;

import java.awt.Rectangle;

import static nl.aniketic.survival.game.common.SurvivalGameConstants.TILE_SIZE;

public class Node {

    private final int size = TILE_SIZE;
    private final int x;
    private final int y;
    private final int worldX;
    private final int worldY;

    private TileType tileType;

    private boolean solid;

    private Rectangle collisionBody;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        worldX = x * size;
        worldY = y * size;
        tileType = TileType.GRASS;
        collisionBody = new Rectangle(worldX, worldY, TILE_SIZE, TILE_SIZE);
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

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public Rectangle getCollisionBody() {
        return collisionBody;
    }
}
