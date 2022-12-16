package nl.aniketic.survival.game.gamestate;

import nl.aniketic.survival.game.entity.KeyObject;
import nl.aniketic.survival.game.level.LevelManager;
import nl.aniketic.survival.game.level.Node;

import java.util.List;

public class KeyController implements EntityController<KeyObject> {

    private final LevelManager levelManager;

    private KeyObject key;

    public KeyController(LevelManager levelManager) {
        this.levelManager = levelManager;
    }

    @Override
    public void loadEntity(int x, int y) {
        key = new KeyObject();
        Node node = levelManager.getNode(24, 14);
        key.setWorldPosition(node);
        key.activate();
    }

    @Override
    public void update() {

    }

    @Override
    public KeyObject getEntity() {
        return key;
    }

    @Override
    public void removeEntity(KeyObject entity) {

    }

    @Override
    public List<KeyObject> getEntities() {
        return null;
    }
}
