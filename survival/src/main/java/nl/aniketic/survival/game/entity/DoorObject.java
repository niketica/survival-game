package nl.aniketic.survival.game.entity;

import nl.aniketic.survival.game.level.Node;

import java.awt.Rectangle;

import static nl.aniketic.survival.game.common.SurvivalGameConstants.TILE_SIZE;

public class DoorObject extends BaseEntity {

    public DoorObject() {
        loadSprite("/entity/object/door.png");
    }

    @Override
    public void setWorldPosition(Node position) {
        this.position = position;
        worldX = position.getWorldX();
        worldY = position.getWorldY();
        collisionBody = new Rectangle(worldX, worldY, TILE_SIZE, TILE_SIZE);
    }
}
