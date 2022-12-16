package nl.aniketic.survival.game.gamestate;

import nl.aniketic.survival.game.entity.BaseEntity;
import nl.aniketic.survival.game.level.LevelManager;
import nl.aniketic.survival.game.level.Node;

import java.awt.Color;
import java.util.List;

import static nl.aniketic.survival.game.common.SurvivalGameConstants.TILE_SIZE;

public interface EntityController<T> {

    void loadEntity(int x, int y);

    void update();

    T getEntity();

    void removeEntity(T entity);

    List<T> getEntities();

    default void setNode(LevelManager levelManager, BaseEntity entity) {
        int worldX = entity.getWorldX();
        int worldY = entity.getWorldY();

        Node nodeByWorldPosition = levelManager.getNodeByWorldPosition(worldX + TILE_SIZE / 2, worldY + TILE_SIZE - entity.getCollisionOffset());
        entity.setPosition(nodeByWorldPosition);
        nodeByWorldPosition.setDebugColor(Color.RED);
    }
}
