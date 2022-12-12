package nl.aniketic.survival.game.gamestate;

import nl.aniketic.survival.engine.display.DisplayManager;
import nl.aniketic.survival.engine.gamestate.GameStateManager;
import nl.aniketic.survival.game.common.Direction;
import nl.aniketic.survival.game.controls.Key;
import nl.aniketic.survival.game.controls.SurvivalGameKeyHandler;
import nl.aniketic.survival.game.entity.DoorObject;
import nl.aniketic.survival.game.entity.KeyObject;
import nl.aniketic.survival.game.entity.Player;
import nl.aniketic.survival.game.level.LevelManager;
import nl.aniketic.survival.game.level.MapLoader;
import nl.aniketic.survival.game.level.Node;

import java.awt.Rectangle;

public class SurvivalGameStateManager extends GameStateManager {

    private Player player;
    private LevelManager levelManager;
    private KeyObject key;
    private DoorObject door;

    @Override
    protected void startGameState() {
        DisplayManager.createDisplay("SURVIVAL");
        DisplayManager.addKeyListener(new SurvivalGameKeyHandler());
        startNewGame();
    }

    private void startNewGame() {
        loadLevel();
        loadKey();
        loadDoor();
        loadPlayer();
    }

    private void loadLevel() {
        levelManager = new LevelManager();
        int[][] map = MapLoader.loadMap("/map/map01.txt");
        levelManager.setMap(map);
        levelManager.activate();
    }

    private void loadKey() {
        key = new KeyObject();
        Node node = levelManager.getNode(24, 14);
        key.setPosition(node);
        key.activate();
    }

    private void loadDoor() {
        door = new DoorObject();
        Node node = levelManager.getNode(26, 14);
        door.setPosition(node);
        door.activate();
    }

    private void loadPlayer() {
        player = new Player();
        player.activate();
        gameObjects.add(player);

        Node node = levelManager.getNode(23, 19);
        player.setPosition(node);
    }

    @Override
    protected void updatePreGameState() {
        updatePlayer();
        updatePlayerOffset();
    }

    private void updatePlayer() {
        int potentialWorldX = player.getWorldX();
        int potentialWorldY = player.getWorldY();
        Rectangle collisionBody = player.getCollisionBody();

        if (Key.UP.isPressed()) {
            player.setDirection(Direction.UP);
            potentialWorldY -= player.getSpeed();
            collisionBody.y -= player.getSpeed();
        }

        if (Key.DOWN.isPressed()) {
            player.setDirection(Direction.DOWN);
            potentialWorldY += player.getSpeed();
            collisionBody.y += player.getSpeed();
        }

        if (Key.LEFT.isPressed()) {
            player.setDirection(Direction.LEFT);
            potentialWorldX -= player.getSpeed();
            collisionBody.x -= player.getSpeed();
        }

        if (Key.RIGHT.isPressed()) {
            player.setDirection(Direction.RIGHT);
            potentialWorldX += player.getSpeed();
            collisionBody.x += player.getSpeed();
        }

        if (!isCollisionWithSolidNode(collisionBody)) {
            player.setWorldX(potentialWorldX);
            player.setWorldY(potentialWorldY);
        }
    }

    private boolean isCollisionWithSolidNode(Rectangle collisionBody) {
        for (Node node : levelManager.getNodes()) {
            if (node.isSolid()) {
                if (collisionBody.intersects(node.getCollisionBody())) {
                    return true;
                }
            }
        }
        return false;
    }

    private void updatePlayerOffset() {
        levelManager.setOffset(player.getWorldX(), player.getWorldY());
        key.setOffset(player.getWorldX(), player.getWorldY());
        door.setOffset(player.getWorldX(), player.getWorldY());
    }

    @Override
    protected void updateEndGameState() {

    }
}
