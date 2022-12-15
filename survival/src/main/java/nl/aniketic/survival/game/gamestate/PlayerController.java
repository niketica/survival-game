package nl.aniketic.survival.game.gamestate;

import nl.aniketic.survival.game.common.Direction;
import nl.aniketic.survival.game.controls.Key;
import nl.aniketic.survival.game.entity.Player;
import nl.aniketic.survival.game.level.LevelManager;
import nl.aniketic.survival.game.level.Node;

import java.awt.Rectangle;
import java.util.List;

public class PlayerController implements EntityController<Player> {

    private final SurvivalGameStateManager survivalGameStateManager;
    private final LevelManager levelManager;

    private Player player;

    public PlayerController(SurvivalGameStateManager survivalGameStateManager,
                            LevelManager levelManager) {
        this.survivalGameStateManager = survivalGameStateManager;
        this.levelManager = levelManager;
    }

    @Override
    public void loadEntity(int x, int y) {
        player = new Player();
        player.activate();
        survivalGameStateManager.addGameObject(player);

        Node node = levelManager.getNode(x, y);
        player.setWorldPosition(node);
    }

    @Override
    public void update() {
        updatePlayer();
        survivalGameStateManager.updatePlayerOffset(player);
    }

    @Override
    public Player getEntity() {
        return player;
    }

    @Override
    public List<Player> getEntities() {
        return null;
    }

    private void updatePlayer() {
        int potentialWorldX = player.getWorldX();
        int potentialWorldY = player.getWorldY();
        Rectangle collisionBody = player.getCollisionBody();

        boolean moving = false;

        if (Key.UP.isPressed()) {
            moving = true;
            player.setDirection(Direction.UP);
            potentialWorldY -= player.getSpeed();
            collisionBody.y -= player.getSpeed();
        }

        if (Key.DOWN.isPressed()) {
            moving = true;
            player.setDirection(Direction.DOWN);
            potentialWorldY += player.getSpeed();
            collisionBody.y += player.getSpeed();
        }

        if (Key.LEFT.isPressed()) {
            moving = true;
            player.setDirection(Direction.LEFT);
            potentialWorldX -= player.getSpeed();
            collisionBody.x -= player.getSpeed();
        }

        if (Key.RIGHT.isPressed()) {
            moving = true;
            player.setDirection(Direction.RIGHT);
            potentialWorldX += player.getSpeed();
            collisionBody.x += player.getSpeed();
        }

        player.setMoving(moving);

        if (moving && !levelManager.isCollisionWithSolidNode(collisionBody)) {
            player.setWorldX(potentialWorldX);
            player.setWorldY(potentialWorldY);
            setNode(levelManager, player);
        }
    }
}