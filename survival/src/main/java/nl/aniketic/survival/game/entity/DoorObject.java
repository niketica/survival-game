package nl.aniketic.survival.game.entity;

import nl.aniketic.survival.game.level.Node;

public class DoorObject extends BaseEntity {

    public DoorObject() {
        loadSprite("/entity/object/door.png");
    }

    @Override
    public void setWorldPosition(Node position) {
        this.position = position;
        worldX = position.getWorldX();
        worldY = position.getWorldY();
    }
}
