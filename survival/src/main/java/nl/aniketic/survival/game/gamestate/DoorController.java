package nl.aniketic.survival.game.gamestate;

import nl.aniketic.survival.game.entity.DoorObject;
import nl.aniketic.survival.game.level.LevelManager;
import nl.aniketic.survival.game.level.Node;

import java.util.ArrayList;
import java.util.List;

public class DoorController implements EntityController<DoorObject> {

    private final LevelManager levelManager;

    private List<DoorObject> doors;

    public DoorController(LevelManager levelManager) {
        this.levelManager = levelManager;
        doors = new ArrayList<>();
    }

    @Override
    public void loadEntity(int x, int y) {
        DoorObject door = new DoorObject();
        Node node = levelManager.getNode(x, y);
        door.setWorldPosition(node);
        door.activate();
        doors.add(door);
    }

    @Override
    public void update() {

    }

    @Override
    public DoorObject getEntity() {
        return null;
    }

    @Override
    public void removeEntity(DoorObject door) {
        door.deactivate();
        doors.remove(door);
    }

    @Override
    public List<DoorObject> getEntities() {
        return doors;
    }
}
