package nl.aniketic.survival.game.gamestate;

import nl.aniketic.survival.engine.display.DisplayManager;
import nl.aniketic.survival.engine.gamestate.GameObject;
import nl.aniketic.survival.engine.gamestate.GameStateManager;
import nl.aniketic.survival.game.controls.SurvivalGameKeyHandler;
import nl.aniketic.survival.game.entity.Crowbar;
import nl.aniketic.survival.game.entity.DoorObject;
import nl.aniketic.survival.game.entity.Player;
import nl.aniketic.survival.game.entity.Zombie;
import nl.aniketic.survival.game.level.LevelManager;
import nl.aniketic.survival.game.level.MapLoader;
import nl.aniketic.survival.game.level.Node;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class SurvivalGameStateManager extends GameStateManager {

    private LevelManager levelManager;
    private PlayerController playerController;
    private ZombieController zombieController;
    private DoorController doorController;
    private CrowbarController crowbarController;

    @Override
    protected void startGameState() {
        DisplayManager.createDisplay("SURVIVAL");
        DisplayManager.addKeyListener(new SurvivalGameKeyHandler());
        startNewGame();
    }

    private void startNewGame() {
        levelManager = createLevelManager();
        MapLoader.Entities entities = MapLoader.loadEntities("/map/entities01.json");
        if (entities == null) {
            throw new IllegalStateException("Could not load entities.");
        }

        doorController = new DoorController(levelManager);
        entities.getDoors().forEach(door -> doorController.loadEntity(door.getWorldX(), door.getWorldY()));

        crowbarController = new CrowbarController(levelManager);
        entities.getCrowbars().forEach(crowbar -> crowbarController.loadEntity(crowbar.getWorldX(), crowbar.getWorldY()));

        zombieController = new ZombieController(this, levelManager);
        entities.getZombies().forEach(zombie -> zombieController.loadEntity(zombie.getWorldX(), zombie.getWorldY()));

        playerController = new PlayerController(this, levelManager);
        MapLoader.Entity player = entities.getPlayer();
        playerController.loadEntity(player.getWorldX(), player.getWorldY());
    }

    private LevelManager createLevelManager() {
        LevelManager levelManager = new LevelManager();
        int[][] map = MapLoader.loadMap("/map/map01.txt");
        levelManager.setMap(map);
        levelManager.activate();

        return levelManager;
    }

    @Override
    protected void updatePreGameState() {
        playerController.update();
        zombieController.update();

        Player player = playerController.getEntity();

        zombieController.getEntities().forEach(zombie -> {
            if (player.getCollisionBody().intersects(zombie.getCollisionBody())) {
                player.setCurrentHitPoints(player.getCurrentHitPoints() - 10);
            }
        });
    }

    @Override
    protected void updateEndGameState() {

    }

    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    public void updatePlayerOffset(Player player) {
        levelManager.setOffset(player.getWorldX(), player.getWorldY());
        doorController.getEntities().forEach(door -> door.setOffset(player.getWorldX(), player.getWorldY()));
        zombieController.getEntities().forEach(zombie -> zombie.setOffset(player.getWorldX(), player.getWorldY()));
        crowbarController.getEntities().forEach(crowbar -> crowbar.setOffset(player.getWorldX(), player.getWorldY()));
        playerController.getEntity().setOffset(player.getWorldX(), player.getWorldY());
    }

    public Node getPlayerPosition() {
        return playerController.getEntity().getPosition();
    }

    public void processBatHit(Rectangle batHitBox) {
        System.out.println("WACK!");
        List<Zombie> zombiesToCleanup = new ArrayList<>();

        for (Zombie zombie : zombieController.getEntities()) {
            if (batHitBox.intersects(zombie.getCollisionBody())) {
                System.out.println("OUCH!");
                zombie.setCurrentHitPoints(zombie.getCurrentHitPoints() - 50);

                if (zombie.getCurrentHitPoints() <= 0) {
                    zombiesToCleanup.add(zombie);
                }
            }
        }

        zombiesToCleanup.forEach(zombie -> zombieController.removeEntity(zombie));
    }

    public DoorObject collisionWithDoor(Rectangle collisionBody) {
        for (DoorObject door : doorController.getEntities()) {
            if (collisionBody.intersects(door.getCollisionBody())) {
                return door;
            }
        }

        return null;
    }

    public void removeDoor(DoorObject door) {
        doorController.removeEntity(door);
    }

    public Crowbar collisionWithCrowbar(Rectangle collisionBody) {
        for (Crowbar crowbar : crowbarController.getEntities()) {
            if (collisionBody.intersects(crowbar.getCollisionBody())) {
                return crowbar;
            }
        }

        return null;
    }

    public void removeCrowbar(Crowbar crowbar) {
        crowbarController.removeEntity(crowbar);
    }
}
