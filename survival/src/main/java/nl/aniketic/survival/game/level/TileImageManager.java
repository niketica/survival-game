package nl.aniketic.survival.game.level;

import nl.aniketic.survival.game.common.ImageUtil;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import static nl.aniketic.survival.game.common.SurvivalGameConstants.TILE_SIZE;

public class TileImageManager {

    private final Map<TileType, BufferedImage> tileMap;

    public TileImageManager() {
        tileMap = new HashMap<>();
        loadImages();
    }

    public BufferedImage getTileImage(TileType tileType) {
        return tileMap.get(tileType);
    }

    private void loadImages() {
        BufferedImage grass = loadImage("/tile/grass.png");
        BufferedImage earth = loadImage("/tile/earth.png");
        BufferedImage sand = loadImage("/tile/sand.png");
        BufferedImage wall = loadImage("/tile/wall.png");
        BufferedImage water = loadImage("/tile/water.png");
        BufferedImage tree = loadImage("/tile/tree.png");
        BufferedImage stone = loadImage("/tile/stone.png");
        tileMap.put(TileType.GRASS, grass);
        tileMap.put(TileType.EARTH, earth);
        tileMap.put(TileType.SAND, sand);
        tileMap.put(TileType.WALL, wall);
        tileMap.put(TileType.WATER, water);
        tileMap.put(TileType.TREE, tree);
        tileMap.put(TileType.STONE, stone);
    }

    private BufferedImage loadImage(String path) {
        BufferedImage image = ImageUtil.loadImage(path);
        image = ImageUtil.scaleImage(image, TILE_SIZE, TILE_SIZE);
        return image;
    }
}
