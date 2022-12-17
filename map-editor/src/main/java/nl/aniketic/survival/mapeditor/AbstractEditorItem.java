package nl.aniketic.survival.mapeditor;

import nl.aniketic.survival.game.common.ImageUtil;

import java.awt.image.BufferedImage;

import static nl.aniketic.survival.game.common.SurvivalGameConstants.TILE_SIZE;

public class AbstractEditorItem {

    protected boolean selected;
    protected int x;
    protected int y;
    protected int size;
    protected BufferedImage image;

    public AbstractEditorItem(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.image = image;
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

    public BufferedImage getImage() {
        return image;
    }

    protected BufferedImage loadImage(String path) {
        BufferedImage image = ImageUtil.loadImage(path);
        image = ImageUtil.scaleImage(image, TILE_SIZE, TILE_SIZE);
        return image;
    }
}
