package nl.aniketic.survival.engine.gamestate;

import java.util.ArrayList;
import java.util.List;

public abstract class GameStateManager {

    private GameRunnable gameRunnable;

    protected List<GameObject> gameObjects;

    public void startGame() {
        gameObjects = new ArrayList<>();
        gameRunnable = new GameRunnable(this);
        startGameState();
        Thread gameThread = new Thread(gameRunnable);
        gameThread.start();
    }

    public void update() {
        updatePreGameState();
        gameObjects.forEach(GameObject::update);
        updateEndGameState();
    }

    public void closeGame() {
        gameRunnable.stop();
    }

    protected abstract void startGameState();

    protected abstract void updatePreGameState();

    protected abstract void updateEndGameState();
}
