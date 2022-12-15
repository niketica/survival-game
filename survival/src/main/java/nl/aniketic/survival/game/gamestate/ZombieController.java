package nl.aniketic.survival.game.gamestate;

import nl.aniketic.survival.game.entity.Zombie;
import nl.aniketic.survival.game.level.LevelManager;
import nl.aniketic.survival.game.level.Node;

public class ZombieController implements EntityController<Zombie> {

    private final SurvivalGameStateManager survivalGameStateManager;
    private final LevelManager levelManager;

    private Zombie zombie;

    public ZombieController(SurvivalGameStateManager survivalGameStateManager,
                            LevelManager levelManager) {
        this.survivalGameStateManager = survivalGameStateManager;
        this.levelManager = levelManager;
    }

    @Override
    public void loadEntity(int x, int y) {
        zombie = new Zombie();
        survivalGameStateManager.addGameObject(zombie);

        Node node = levelManager.getNode(x, y);
        zombie.setPosition(node);
        zombie.activate();

    }

    @Override
    public void update() {

    }

    @Override
    public Zombie getEntity() {
        return zombie;
    }
}
