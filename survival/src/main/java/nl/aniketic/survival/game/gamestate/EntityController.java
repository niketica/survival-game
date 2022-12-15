package nl.aniketic.survival.game.gamestate;

import nl.aniketic.survival.game.entity.BaseEntity;
import nl.aniketic.survival.game.level.LevelManager;
import nl.aniketic.survival.game.level.Node;

import java.util.List;

public interface EntityController<T> {

    void loadEntity(int x, int y);

    void update();

    T getEntity();

    List<T> getEntities();

    default void setNode(LevelManager levelManager, BaseEntity entity) {
        int worldX = entity.getWorldX();
        int worldY = entity.getWorldY();

        Node nodeByWorldPosition = levelManager.getNodeByWorldPosition(worldX, worldY);
        entity.setPosition(nodeByWorldPosition);
    }
}
