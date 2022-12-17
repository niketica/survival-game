package nl.aniketic.survival.mapeditor;

import nl.aniketic.survival.controls.EditorKeyListener;
import nl.aniketic.survival.controls.EditorMouseListener;
import nl.aniketic.survival.engine.display.DisplayManager;
import nl.aniketic.survival.engine.gamestate.GameStateManager;
import nl.aniketic.survival.game.level.TileImageManager;

public class MapEditor extends GameStateManager {

    private UserInterface userInterface;

    @Override
    protected void startGameState() {
        DisplayManager.createDisplay("SURVIVAL");

        EditorKeyListener keyListener = new EditorKeyListener();
        DisplayManager.addKeyListener(keyListener);

        TileImageManager tileImageManager = new TileImageManager();

        Map map = new Map(tileImageManager);
        map.loadMap(10, 10);
        map.activate();
        gameObjects.add(map);

        userInterface = new UserInterface(tileImageManager);
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
