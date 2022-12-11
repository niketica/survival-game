package nl.aniketic.survival.game.gamestate;

import nl.aniketic.survival.engine.display.DisplayManager;
import nl.aniketic.survival.engine.gamestate.GameStateManager;
import nl.aniketic.survival.game.common.Direction;
import nl.aniketic.survival.game.controls.Key;
import nl.aniketic.survival.game.controls.SurvivalGameKeyHandler;
import nl.aniketic.survival.game.entity.Player;
import nl.aniketic.survival.game.level.LevelManager;
import nl.aniketic.survival.game.level.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SurvivalGameStateManager extends GameStateManager {

    private Player player;
    private LevelManager levelManager;

    @Override
    protected void startGameState() {
        DisplayManager.createDisplay("SURVIVAL");
        DisplayManager.addKeyListener(new SurvivalGameKeyHandler());
        startNewGame();
    }

    private void startNewGame() {
        loadLevel();
        loadPlayer();
    }

    private void loadLevel() {
        levelManager = new LevelManager();
        levelManager.activate();

        Node node1 = new Node(0, 0);
        Node node2 = new Node(1, 0);
        Node node3 = new Node(0, 1);
        Node node4 = new Node(1, 1);
        List<Node> nodes = new ArrayList<>();
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);

        levelManager.setNodes(nodes);
    }

    private void loadPlayer() {
        player = new Player();
        player.activatePanelComponent();
        gameObjects.add(player);

        Node node = levelManager.getNodes().get(0);
        player.setPosition(node);
    }

    @Override
    protected void updatePreGameState() {
        updatePlayer();
        updateLevel();
    }

    private void updatePlayer() {
        Key pressedKey = Arrays.stream(Key.values())
                .filter(Key::isPressed)
                .findAny().orElse(null);

        if (pressedKey != null) {
            switch (pressedKey) {
                case UP:
                    player.setDirection(Direction.UP);
                    player.move();
                    break;
                case DOWN:
                    player.setDirection(Direction.DOWN);
                    player.move();
                    break;
                case LEFT:
                    player.setDirection(Direction.LEFT);
                    player.move();
                    break;
                case RIGHT:
                    player.setDirection(Direction.RIGHT);
                    player.move();
                    break;
            }
        }
    }

    private void updateLevel() {
        levelManager.setOffsetX(player.getWorldX());
        levelManager.setOffsetY(player.getWorldY());
    }

    @Override
    protected void updateEndGameState() {

    }
}
