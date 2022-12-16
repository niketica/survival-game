package nl.aniketic.survival.game.gamestate;

import nl.aniketic.survival.game.entity.DoorObject;
import nl.aniketic.survival.game.level.LevelManager;
import nl.aniketic.survival.game.level.Node;

import java.util.List;

public class DoorController implements EntityController<DoorObject> {

    private final LevelManager levelManager;

    private DoorObject door;

    public DoorController(LevelManager levelManager) {
        this.levelManager = levelManager;
    }

    @Override
    public void loadEntity(int x, int y) {
        door = new DoorObject();
        Node node = levelManager.getNode(x, y);
        door.setWorldPosition(node);
        door.activate();
    }

    @Override
    public void update() {

    }

    @Override
    public DoorObject getEntity() {
        return door;
    }

    @Override
    public void removeEntity(DoorObject entity) {

    }

    @Override
    public List<DoorObject> getEntities() {
        return null;
    }
}
