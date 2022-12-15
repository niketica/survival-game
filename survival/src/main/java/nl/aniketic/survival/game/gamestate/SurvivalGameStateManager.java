package nl.aniketic.survival.game.gamestate;

import nl.aniketic.survival.engine.display.DisplayManager;
import nl.aniketic.survival.engine.gamestate.GameObject;
import nl.aniketic.survival.engine.gamestate.GameStateManager;
import nl.aniketic.survival.game.controls.SurvivalGameKeyHandler;
import nl.aniketic.survival.game.entity.Player;
import nl.aniketic.survival.game.entity.Zombie;
import nl.aniketic.survival.game.level.LevelManager;
import nl.aniketic.survival.game.level.MapLoader;

public class SurvivalGameStateManager extends GameStateManager {

    private LevelManager levelManager;
    private PlayerController playerController;
    private ZombieController zombieController;
    private DoorController doorController;
    private KeyController keyController;

    @Override
    protected void startGameState() {
        DisplayManager.createDisplay("SURVIVAL");
        DisplayManager.addKeyListener(new SurvivalGameKeyHandler());
        startNewGame();
    }

    private void startNewGame() {
        levelManager = createLevelManager();

        keyController = new KeyController(levelManager);
        keyController.loadEntity(24, 14);

        doorController = new DoorController(levelManager);
        doorController.loadEntity(26, 14);

        zombieController = new ZombieController(this, levelManager);
        zombieController.loadEntity(25, 14);

        playerController = new PlayerController(this, levelManager);
        playerController.loadEntity(23, 19);
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
        Zombie zombie = zombieController.getEntity();

        if (player.getCollisionBody().intersects(zombie.getCollisionBody())) {
            player.setCurrentHitPoints(player.getCurrentHitPoints() - 10);
        }
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
        keyController.getEntity().setOffset(player.getWorldX(), player.getWorldY());
        doorController.getEntity().setOffset(player.getWorldX(), player.getWorldY());
        zombieController.getEntity().setOffset(player.getWorldX(), player.getWorldY());
    }
}
