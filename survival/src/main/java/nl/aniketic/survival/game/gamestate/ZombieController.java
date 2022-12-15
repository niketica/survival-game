package nl.aniketic.survival.game.gamestate;

import nl.aniketic.survival.game.common.Direction;
import nl.aniketic.survival.game.entity.Zombie;
import nl.aniketic.survival.game.level.LevelManager;
import nl.aniketic.survival.game.level.Node;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class ZombieController implements EntityController<Zombie> {

    private final SurvivalGameStateManager survivalGameStateManager;
    private final LevelManager levelManager;

    private List<Zombie> zombies;

    public ZombieController(SurvivalGameStateManager survivalGameStateManager,
                            LevelManager levelManager) {
        this.survivalGameStateManager = survivalGameStateManager;
        this.levelManager = levelManager;
        zombies = new ArrayList<>();
    }

    @Override
    public void loadEntity(int x, int y) {
        Zombie zombie = new Zombie();
        survivalGameStateManager.addGameObject(zombie);

        Node node = levelManager.getNode(x, y);
        zombie.setWorldPosition(node);
        zombie.activate();

        addZombie(zombie);
    }

    @Override
    public void update() {
        Node playerPosition = survivalGameStateManager.getPlayerPosition();
        zombies.forEach(zombie -> moveZombie(zombie, playerPosition));
        if (zombies.isEmpty()) {
            int x = playerPosition.getX() + 20;
            int y = playerPosition.getY();
            loadEntity(x, y);
        }
    }

    private void moveZombie(Zombie zombie, Node targetPosition) {
        int targetX = targetPosition.getWorldX();
        int targetY = targetPosition.getWorldY();

        int potentialX = zombie.getWorldX();
        int potentialY = zombie.getWorldY();

        if (targetX < potentialX) {
            potentialX -= zombie.getSpeed();
        } else if (targetX > potentialX) {
            potentialX += zombie.getSpeed();
        }

        if (targetY < potentialY) {
            potentialY -= zombie.getSpeed();
        } else if (targetY > potentialY) {
            potentialY += zombie.getSpeed();
        }

        Rectangle collisionBody = zombie.getCollisionBody();
        collisionBody.x += potentialX;
        collisionBody.y += potentialY;

        boolean moving = false;
        if (!levelManager.isCollisionWithSolidNode(collisionBody)) {
            if (potentialX < zombie.getWorldX()) {
                zombie.setDirection(Direction.LEFT);
            } else {
                zombie.setDirection(Direction.RIGHT);
            }

            zombie.setWorldX(potentialX);
            zombie.setWorldY(potentialY);
            setNode(levelManager, zombie);
            moving = true;
        }
        zombie.setMoving(moving);
    }

    @Override
    public Zombie getEntity() {
        return null;
    }

    @Override
    public List<Zombie> getEntities() {
        if (zombies == null) {
            zombies = new ArrayList<>();
        }
        return zombies;
    }

    private void addZombie(Zombie zombie) {
        if (zombies == null) {
            zombies = new ArrayList<>();
        }
        zombies.add(zombie);
    }
}