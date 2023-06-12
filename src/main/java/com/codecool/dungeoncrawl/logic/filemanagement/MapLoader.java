package com.codecool.dungeoncrawl.logic.filemanagement;

import com.codecool.dungeoncrawl.logic.actors.Mage;
import com.codecool.dungeoncrawl.logic.actors.Ogre;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.CellType;
import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.gameobject.Gate;
import com.codecool.dungeoncrawl.logic.items.Armor;
import com.codecool.dungeoncrawl.logic.items.Food;
import com.codecool.dungeoncrawl.logic.items.Key;
import com.codecool.dungeoncrawl.logic.items.Sword;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.VOID);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (ObjectChar.fromChar(line.charAt(x))) {
                        case VOID -> cell.setType(CellType.VOID);
                        case WALL -> cell.setType(CellType.UNWALKABLE);
                        case FLOOR -> cell.setType(CellType.WALKABLE);
                        case SKELETON -> {
                            cell.setType(CellType.WALKABLE);
                            new Skeleton(cell);
                        }
                        case PLAYER -> {
                            cell.setType(CellType.WALKABLE);
                            map.setPlayer(new Player(cell));
                        }
                        case FOOD -> {
                            cell.setType(CellType.WALKABLE);
                            new Food(cell);
                        }
                        case KEY -> {
                            cell.setType(CellType.WALKABLE);
                            new Key(cell);
                        }
                        case SWORD -> {
                            cell.setType(CellType.WALKABLE);
                            new Sword(cell);
                        }
                        case ARMOR -> {
                            cell.setType(CellType.WALKABLE);
                            new Armor(cell);
                        }
                        case GATE -> {
                            cell.setType(CellType.WALKABLE);
                            new Gate(cell);
                        }
                        case OGRE -> {
                            cell.setType(CellType.WALKABLE);
                            new Ogre(cell);
                        }
                        case MAGE -> {
                            cell.setType(CellType.WALKABLE);
                            new Mage(cell);
                        }
                        default -> throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }
}
