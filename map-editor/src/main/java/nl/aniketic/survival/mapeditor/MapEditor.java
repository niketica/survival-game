package nl.aniketic.survival.mapeditor;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.aniketic.survival.controls.EditorKeyListener;
import nl.aniketic.survival.controls.EditorMouseListener;
import nl.aniketic.survival.engine.display.DisplayManager;
import nl.aniketic.survival.engine.gamestate.GameStateManager;
import nl.aniketic.survival.game.level.MapLoader;
import nl.aniketic.survival.game.level.Node;
import nl.aniketic.survival.game.level.TileImageManager;

import java.io.BufferedWriter;
import java.io.File;
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
        map.loadMap();
        map.activate();
        gameObjects.add(map);

        userInterface = new UserInterface(tileImageManager);
        userInterface.activate();
        gameObjects.add(userInterface);

        DisplayManager.addMouseListener(new EditorMouseListener(userInterface, map));

        loadEntities();
    }

    private void loadEntities() {
        MapLoader.Entities loadedEntities = MapLoader.loadEntities("maps/entities01.json");
        if (loadedEntities == null) {
            throw new IllegalStateException("Could not load entities.");
        }
        MapLoader.Entity player = loadedEntities.getPlayer();
        if (player != null) {
            map.addEntity(createEditorEntity(player, EntityValue.PLAYER));
        }

        for (MapLoader.Entity entity : loadedEntities.getCrowbars()) {
            map.addEntity(createEditorEntity(entity, EntityValue.CROWBAR));
        }

        for (MapLoader.Entity entity : loadedEntities.getDoors()) {
            map.addEntity(createEditorEntity(entity, EntityValue.DOOR));
        }

        for (MapLoader.Entity entity : loadedEntities.getZombies()) {
            map.addEntity(createEditorEntity(entity, EntityValue.ZOMBIE));
        }
    }

    private EditorEntity createEditorEntity(MapLoader.Entity player, EntityValue entityValue) {
        Node node = map.getNode(player.getWorldX(), player.getWorldY());
        EditorEntity entity = new EditorEntity(player.getWorldX(), player.getWorldY(), entityValue);
        entity.setWorldX(node.getWorldX());
        entity.setWorldY(node.getWorldY());
        return entity;
    }

    @Override
    protected void updatePreGameState() {
        for (Button button : userInterface.getButtons()) {
            if (button.isClicked()) {
                button.setClicked(false);

                if (button.getButtonValue() == ButtonValue.EXPORT) {
                    exportLevel();
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

    private void exportLevel() {
        saveMap();
        saveEntities();
    }

    private void saveMap() {
        String stringMap = mapNodesToString();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("maps/map01.txt"));
            writer.write(stringMap);
            writer.close();
            System.out.println("Saved map!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveEntities() {
        MapLoader.Entities entities = new MapLoader.Entities();

        List<EditorEntity> mapEntities = map.getEntities();
        for (EditorEntity editorEntity : mapEntities) {
            MapLoader.Entity entity = new MapLoader.Entity();
            entity.setWorldX(editorEntity.getX());
            entity.setWorldY(editorEntity.getY());
            EntityValue entityValue = editorEntity.getEntity();
            switch (entityValue) {
                case PLAYER:
                    entities.setPlayer(entity);
                    break;
                case DOOR:
                    entities.getDoors().add(entity);
                    break;
                case CROWBAR:
                    entities.getCrowbars().add(entity);
                    break;
                case ZOMBIE:
                    entities.getZombies().add(entity);
                    break;
            }
        }


        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File("maps/entities01.json"), entities);
            System.out.println("Saved entities!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
