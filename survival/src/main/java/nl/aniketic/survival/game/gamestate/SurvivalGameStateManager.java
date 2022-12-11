package nl.aniketic.survival.game.gamestate;

import nl.aniketic.survival.engine.display.DisplayManager;
import nl.aniketic.survival.engine.gamestate.GameStateManager;
import nl.aniketic.survival.game.entity.Player;
import nl.aniketic.survival.game.level.LevelManager;
import nl.aniketic.survival.game.level.Node;

import java.util.ArrayList;
import java.util.List;

public class SurvivalGameStateManager extends GameStateManager {

    @Override
    protected void startGameState() {
        DisplayManager.createDisplay("SURVIVAL");
        startNewGame();
    }

    private void startNewGame() {
        loadLevel();
        loadPlayer();
    }

    private void loadLevel() {
        LevelManager levelManager = new LevelManager();
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
        Player player = new Player();
        player.activatePanelComponent();
        gameObjects.add(player);
    }

    @Override
    protected void updatePreGameState() {

    }

    @Override
    protected void updateEndGameState() {

    }
}
