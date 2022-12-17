package nl.aniketic.survival.mapeditor;

import nl.aniketic.survival.controls.EditorKeyListener;
import nl.aniketic.survival.controls.EditorMouseListener;
import nl.aniketic.survival.engine.display.DisplayManager;
import nl.aniketic.survival.engine.gamestate.GameStateManager;

public class MapEditor extends GameStateManager {

    private UserInterface userInterface;

    @Override
    protected void startGameState() {
        DisplayManager.createDisplay("SURVIVAL");

        EditorKeyListener keyListener = new EditorKeyListener();
        DisplayManager.addKeyListener(keyListener);

        Map map = new Map();
        map.loadMap(10, 10);
        map.activate();
        gameObjects.add(map);

        userInterface = new UserInterface();
        userInterface.activate();
        gameObjects.add(userInterface);

        DisplayManager.addMouseListener(new EditorMouseListener(userInterface));
    }

    @Override
    protected void updatePreGameState() {

    }

    @Override
    protected void updateEndGameState() {

    }
}
