package nl.aniketic.survival.game.gamestate;

import nl.aniketic.survival.game.common.Direction;
import nl.aniketic.survival.game.controls.Key;
import nl.aniketic.survival.game.entity.Crowbar;
import nl.aniketic.survival.game.entity.DoorObject;
import nl.aniketic.survival.game.entity.Player;
import nl.aniketic.survival.game.level.LevelManager;
import nl.aniketic.survival.game.level.Node;
import nl.aniketic.survival.game.sound.SoundControllerUtil;
import nl.aniketic.survival.game.sound.SoundFx;

import java.awt.Rectangle;
import java.util.List;

public class PlayerController implements EntityController<Player> {

    private final SurvivalGameStateManager survivalGameStateManager;
    private final LevelManager levelManager;

    private Player player;

    private int batCooldown = 120;
    private int currentBatCooldownCount = batCooldown;

    private int crowbarsInInv = 0;

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

        if (Key.SPACE.isPressed() && currentBatCooldownCount >= batCooldown) {
            currentBatCooldownCount = 0;
            survivalGameStateManager.processBatHit(player.getBatHitBox());
        }

        if (currentBatCooldownCount < batCooldown) {
            currentBatCooldownCount++;
        }
    }

    @Override
    public Player getEntity() {
        return player;
    }

    @Override
    public void removeEntity(Player entity) {

    }

    @Override
    public List<Player> getEntities() {
        return null;
    }

    private void updatePlayer() {
        updateMovement();

        Crowbar crowbar = survivalGameStateManager.collisionWithCrowbar(player.getCollisionBody());
        if (crowbar != null) {
            System.out.println("Crowbar get!");
            survivalGameStateManager.removeCrowbar(crowbar);
            crowbarsInInv++;
            SoundControllerUtil.play(SoundFx.PICKUP);
        }
    }

    private void updateMovement() {
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

        boolean collisionWithSolidNode = levelManager.isCollisionWithSolidNode(collisionBody);

        DoorObject door = survivalGameStateManager.collisionWithDoor(collisionBody);
        boolean collisionWithDoor = door != null;
        if (door != null && crowbarsInInv > 0) {
            crowbarsInInv--;
            collisionWithDoor = false;
            survivalGameStateManager.removeDoor(door);
            SoundControllerUtil.play(SoundFx.DOOR);
        }

        if (moving && !collisionWithSolidNode && !collisionWithDoor) {
            player.setWorldX(potentialWorldX);
            player.setWorldY(potentialWorldY);
            setNode(levelManager, player);
        }
    }
}
