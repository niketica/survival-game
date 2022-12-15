package nl.aniketic.survival.game.gamestate;

import nl.aniketic.survival.game.entity.KeyObject;
import nl.aniketic.survival.game.level.LevelManager;
import nl.aniketic.survival.game.level.Node;

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
        key.setPosition(node);
        key.activate();
    }

    @Override
    public void update() {

    }

    @Override
    public KeyObject getEntity() {
        return key;
    }
}
