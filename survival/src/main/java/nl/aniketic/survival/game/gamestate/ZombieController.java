package nl.aniketic.survival.game.gamestate;

import nl.aniketic.survival.game.common.Direction;
import nl.aniketic.survival.game.entity.Zombie;
import nl.aniketic.survival.game.level.LevelManager;
import nl.aniketic.survival.game.level.Node;
import nl.aniketic.survival.game.pathfinding.AStarPathfindingController;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class ZombieController implements EntityController<Zombie> {

    private final SurvivalGameStateManager survivalGameStateManager;
    private final LevelManager levelManager;
    private final AStarPathfindingController pathfindingController;

    private List<Zombie> zombies;

    public ZombieController(SurvivalGameStateManager survivalGameStateManager,
                            LevelManager levelManager) {
        this.survivalGameStateManager = survivalGameStateManager;
        this.levelManager = levelManager;
        this.pathfindingController = new AStarPathfindingController();
        zombies = new ArrayList<>();
    }

    @Override
    public void loadEntity(int x, int y) {
        Node node = levelManager.getNode(x, y);
        if (node == null) {
            return;
        }

        Zombie zombie = new Zombie();
        survivalGameStateManager.addGameObject(zombie);

        zombie.setWorldPosition(node);
        zombie.activate();

        addZombie(zombie);
    }

    @Override
    public void update() {
        Node playerPosition = survivalGameStateManager.getPlayerPosition();
        zombies.forEach(zombie -> moveZombie(zombie, playerPosition));
        if (zombies.isEmpty()) {
            loadEntity(43, 19);
        }
    }

    private void moveZombie(Zombie zombie, Node targetPosition) {
        Node zombiePosition = zombie.getPosition();
        List<Node> path = pathfindingController.getPath(zombiePosition, targetPosition);

        if (path == null || path.isEmpty()) {
            zombie.setMoving(false);
            return;
        }

        path.forEach(n -> n.setDebugColor(Color.YELLOW));
        targetPosition = path.get(path.size() - 1);

        int targetX = targetPosition.getWorldX();
        int targetY = targetPosition.getWorldY();

        int potentialX = zombie.getWorldX();
        int potentialY = zombie.getWorldY();
        Rectangle collisionBody = zombie.getCollisionBody();

        if (targetX < potentialX) {
            collisionBody.x -= zombie.getSpeed();
        } else if (targetX > potentialX) {
            collisionBody.x += zombie.getSpeed();
        }

        if (targetY < potentialY) {
            collisionBody.y -= zombie.getSpeed();
        } else if (targetY > potentialY) {
            collisionBody.y += zombie.getSpeed();
        }
        boolean collisionWithSolidNode = levelManager.isCollisionWithSolidNode(collisionBody);

        if (collisionWithSolidNode) {
            collisionBody = zombie.getCollisionBody();
            if (targetX < potentialX) {
                collisionBody.x -= zombie.getSpeed();
            } else if (targetX > potentialX) {
                collisionBody.x += zombie.getSpeed();
            }
            collisionWithSolidNode = levelManager.isCollisionWithSolidNode(collisionBody);
            if (collisionWithSolidNode) {
                collisionBody = zombie.getCollisionBody();
                if (targetY < potentialY) {
                    collisionBody.y -= zombie.getSpeed();
                } else if (targetY > potentialY) {
                    collisionBody.y += zombie.getSpeed();
                }
                collisionWithSolidNode = levelManager.isCollisionWithSolidNode(collisionBody);
                if (!collisionWithSolidNode) {
                    if (targetY < potentialY) {
                        potentialY -= zombie.getSpeed();
                    } else if (targetY > potentialY) {
                        potentialY += zombie.getSpeed();
                    }
                }
            } else {
                if (targetX < potentialX) {
                    potentialX -= zombie.getSpeed();
                } else if (targetX > potentialX) {
                    potentialX += zombie.getSpeed();
                }
            }
        } else {
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
        }


        boolean moving = false;
        if (!collisionWithSolidNode) {
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
    public void removeEntity(Zombie entity) {
        survivalGameStateManager.removeGameObject(entity);
        entity.deactivate();
        zombies.remove(entity);
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
