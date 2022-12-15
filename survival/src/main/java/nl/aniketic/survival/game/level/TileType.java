package nl.aniketic.survival.game.level;

import java.util.Arrays;

public enum TileType {
    GRASS(0),
    EARTH(1),
    SAND(2),
    WALL(3),
    WATER(4),
    TREE(5),
    STONE(6);

    private final int mapValue;

    TileType(int mapValue) {
        this.mapValue = mapValue;
    }

    public int getMapValue() {
        return mapValue;
    }

    public static TileType getByMapValue(int mapValue) {
        return Arrays.stream(TileType.values())
                .filter(t -> t.getMapValue() == mapValue)
                .findAny().orElse(null);
    }
}
