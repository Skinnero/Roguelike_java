package com.codecool.dungeoncrawl.fileloader;

import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.ui.utils.TileType;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String mapFileName) {
        InputStream is = MapLoader.class.getResourceAsStream(mapFileName);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, TileType.FLOOR);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(Position.of(x, y));
                    CharOnMap.fromChar(line.charAt(x)).apply(cell);
                    map.addToGameObjectList(cell);
                }
            }
        }
        return map;
    }
}