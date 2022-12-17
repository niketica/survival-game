package nl.aniketic.survival.mapeditor;

public enum EntityValue {
    PLAYER("/entity/soldier/soldier_1.png"),
    ZOMBIE("/entity/zombie/zombie_1.png"),
    DOOR("/entity/object/door.png"),
    CROWBAR("/entity/object/crowbar.png");

    private final String imgPath;

    EntityValue(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgPath() {
        return imgPath;
    }
}
