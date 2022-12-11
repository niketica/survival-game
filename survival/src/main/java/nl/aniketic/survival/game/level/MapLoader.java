package nl.aniketic.survival.game.level;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    private MapLoader() {
        // Hide constructor
    }
}
