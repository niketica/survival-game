package nl.aniketic.survival.game.entity;

import nl.aniketic.survival.game.level.Node;

public class KeyObject extends BaseEntity {

    public KeyObject() {
        loadSprite("/entity/object/key.png");
    }

    @Override
    public void setPosition(Node position) {
        this.position = position;
        worldX = position.getWorldX();
        worldY = position.getWorldY();
    }
}
