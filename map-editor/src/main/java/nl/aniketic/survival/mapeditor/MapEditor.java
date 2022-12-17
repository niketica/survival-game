package nl.aniketic.survival.mapeditor;

import nl.aniketic.survival.controls.EditorKeyListener;
import nl.aniketic.survival.controls.EditorMouseListener;
import nl.aniketic.survival.engine.display.DisplayManager;
import nl.aniketic.survival.engine.gamestate.GameStateManager;
import nl.aniketic.survival.game.level.Node;
import nl.aniketic.survival.game.level.TileImageManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapEditor extends GameStateManager {

    private UserInterface userInterface;
    private Map map;

    @Override
    protected void startGameState() {
        DisplayManager.createDisplay("SURVIVAL");

        EditorKeyListener keyListener = new EditorKeyListener();
        DisplayManager.addKeyListener(keyListener);

        TileImageManager tileImageManager = new TileImageManager();

        map = new Map(tileImageManager);
        map.loadMap(10, 10);
        map.activate();
        gameObjects.add(map);

        userInterface = new UserInterface(tileImageManager);
        userInterface.activate();
        gameObjects.add(userInterface);

        DisplayManager.addMouseListener(new EditorMouseListener(userInterface, map));
    }

    @Override
    protected void updatePreGameState() {
        for (Button button : userInterface.getButtons()) {
            if (button.isClicked()) {
                button.setClicked(false);

                if (button.getButtonValue() == ButtonValue.EXPORT) {
                    String stringMap = mapNodesToString();
                    try {
                        String filePath = "C:\\ws\\git\\survival-game\\map-editor\\src\\main\\resources\\map\\map01.txt";
                        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                        writer.write(stringMap);
                        writer.close();
                        System.out.println("Saved map!");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private String mapNodesToString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (List<Node> mappedRow : mapNodes()) {
            for (Node node : mappedRow) {
                stringBuilder.append(node.getTileType().getMapValue()).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private List<List<Node>> mapNodes() {
        List<List<Node>> mappedNodes = new ArrayList<>();
        for (Node node : map.getNodes()) {
            List<Node> rowNodes = null;
            for (List<Node> mappedRow : mappedNodes) {
                if (mappedRow.get(0).getY() == node.getY()) {
                    rowNodes = mappedRow;
                }
            }
            if (rowNodes == null) {
                rowNodes = new ArrayList<>();
                mappedNodes.add(rowNodes);
            }
            rowNodes.add(node);
        }
        return mappedNodes;
    }

    @Override
    protected void updateEndGameState() {

    }
}
