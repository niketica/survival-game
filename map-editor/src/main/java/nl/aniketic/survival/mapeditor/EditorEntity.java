package nl.aniketic.survival.mapeditor;

import java.awt.image.BufferedImage;

import static nl.aniketic.survival.game.common.SurvivalGameConstants.TILE_SIZE;

public class EditorEntity extends AbstractEditorItem {

    private final EntityValue entity;

    private int worldX;
    private int worldY;

    public EditorEntity(int x, int y, EntityValue entity) {
        super(x, y, TILE_SIZE);
        this.entity = entity;
        image = loadImage(entity.getImgPath());
    }

    public EntityValue getEntity() {
        return entity;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }
}
