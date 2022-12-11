package nl.aniketic.survival.game.level;

import nl.aniketic.survival.game.common.SurvivalGameConstants;

public class Node {

    private int size = SurvivalGameConstants.TILE_SIZE;
    private int x;
    private int y;
    private int screenX;
    private int screenY;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        screenX = x * size;
        screenY = y * size;
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
}
