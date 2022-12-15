package nl.aniketic.survival.game.level;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

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

    private List<Node> neighbours;
    private Node connection;

    private int gValue; // Distance from this node to the start node
    private int hValue; // Distance from this node to the target node

    private Color debugColor;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        worldX = x * size;
        worldY = y * size;
        tileType = TileType.GRASS;
        collisionBody = new Rectangle(worldX, worldY, TILE_SIZE, TILE_SIZE);
        neighbours = new ArrayList<>();
        gValue = 0;
        hValue = 0;
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

    public void addNeighbour(Node node) {
        neighbours.add(node);
    }

    public List<Node> getNeighbours() {
        return neighbours;
    }

    public int getG() {
        return gValue;
    }

    public void setG(int gValue) {
        this.gValue = gValue;
    }

    public int getH() {
        return hValue;
    }

    public void setH(int hValue) {
        this.hValue = hValue;
    }

    public int getDistance(Node node) {
        int xDistance = x - node.getX();
        if (xDistance < 0) {
            xDistance *= -1;
        }

        int yDistance = y - node.getY();
        if (yDistance < 0) {
            yDistance *= -1;
        }

        return xDistance + yDistance;
    }

    public int getF() {
        return gValue + hValue;
    }

    public Node getConnection() {
        return connection;
    }

    public void setConnection(Node connection) {
        this.connection = connection;
    }

    public Color getDebugColor() {
        return debugColor;
    }

    public void setDebugColor(Color debugColor) {
        this.debugColor = debugColor;
    }
}
