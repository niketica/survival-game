package nl.aniketic.survival.game.level;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
            File file = new File(filePath);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            mapLines = bufferedReader.lines().collect(Collectors.toList());
            bufferedReader.close();
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load map.", e);
        }
        return mapLines;
    }

    public static Entities loadEntities(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);

        try {
            return objectMapper.readValue(file, Entities.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private MapLoader() {
        // Hide constructor
    }

    public static class Entities {
        private Entity player;
        private List<Entity> zombies;
        private List<Entity> doors;
        private List<Entity> crowbars;

        public Entity getPlayer() {
            return player;
        }

        public void setPlayer(Entity player) {
            this.player = player;
        }

        public List<Entity> getZombies() {
            if (zombies == null) {
                zombies = new ArrayList<>();
            }
            return zombies;
        }

        public void setZombies(List<Entity> zombies) {
            this.zombies = zombies;
        }

        public List<Entity> getDoors() {
            if (doors == null) {
                doors = new ArrayList<>();
            }
            return doors;
        }

        public void setDoors(List<Entity> doors) {
            this.doors = doors;
        }

        public List<Entity> getCrowbars() {
            if (crowbars == null) {
                crowbars = new ArrayList<>();
            }
            return crowbars;
        }

        public void setCrowbars(List<Entity> crowbars) {
            this.crowbars = crowbars;
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
