package nl.aniketic.survival.game.level;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapLoader {

    public static int[][] loadMap(String filePath) {
        List<String> mapLines = getMapLines(filePath);
        int[][] newMap = mapLines.stream()
                .map(s -> s.split(" "))
                .map(s -> Arrays.stream(s).mapToInt(Integer::parseInt).toArray())
                .toArray(int[][]::new);
        return newMap;
    }

    public static List<String> getMapLines(String filePath) {
        List<String> mapLines;
        try {
            InputStream inputStream = MapLoader.class.getResourceAsStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            mapLines = bufferedReader.lines().collect(Collectors.toList());
            bufferedReader.close();
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load map.", e);
        }
        return mapLines;
    }

    public static Entities loadEntities(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file1 = new File(MapLoader.class.getResource(filePath).getFile());

        try {
            return objectMapper.readValue(file1, Entities.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private MapLoader() {
        // Hide constructor
    }

    public static class Entities {
        private List<Entity> zombies;

        public List<Entity> getZombies() {
            if (zombies == null) {
                zombies = new ArrayList<>();
            }
            return zombies;
        }

        public void setZombies(List<Entity> zombies) {
            this.zombies = zombies;
        }
    }

    public static class Entity {
        private int worldX;
        private int worldY;

        public int getWorldX() {
            return worldX;
        }

        public void setWorldX(int worldX) {
            this.worldX = worldX;
        }

        public int getWorldY() {
            return worldY;
        }

        public void setWorldY(int worldY) {
            this.worldY = worldY;
        }
    }
}
