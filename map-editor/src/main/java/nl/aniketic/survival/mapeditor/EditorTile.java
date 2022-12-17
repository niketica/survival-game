package nl.aniketic.survival.mapeditor;

import nl.aniketic.survival.game.level.TileImageManager;
import nl.aniketic.survival.game.level.TileType;

import static nl.aniketic.survival.game.common.SurvivalGameConstants.TILE_SIZE;

public class EditorTile extends AbstractEditorItem {

    private final TileType tileType;

    public EditorTile(TileImageManager tileImageManager, TileType tileType, int x, int y) {
        super(x, y, TILE_SIZE);
        this.tileType = tileType;
        image = tileImageManager.getTileImage(tileType);
    }

    public TileType getTileType() {
        return tileType;
    }
}
