package com.codecool.dungeoncrawl.logic.filemanagement;

import com.codecool.dungeoncrawl.logic.actors.Mage;
import com.codecool.dungeoncrawl.logic.actors.Ogre;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;
import com.codecool.dungeoncrawl.logic.actorutils.Movement;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.CellType;
import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.gameobject.Chest;
import com.codecool.dungeoncrawl.logic.gameobject.Gate;
import com.codecool.dungeoncrawl.logic.gameobject.TraversalObject;
import com.codecool.dungeoncrawl.logic.items.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MapLoader {
    public static List<Ogre> ogres = new ArrayList<>();
    public static List<Mage> mages = new ArrayList<>();
    private static final String BOAT = "boat";
    public static GameMap loadMap(String mapFileName) {
        Movement movement = new Movement();
        InputStream is = MapLoader.class.getResourceAsStream(mapFileName);
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
                            Ogre ogre = new Ogre(cell);
                            movement.setPatrolPlaces(ogre);
                            ogres.add(ogre);
                        }
                        case MAGE -> {
                            cell.setType(CellType.WALKABLE);
                            mages.add(new Mage(cell));
                        }
                        case TORCH -> {
                            cell.setType(CellType.WALKABLE);
                            new Torch(cell);
                        }
                        case CHEST -> {
                            cell.setType(CellType.UNWALKABLE);
                            new Chest(cell);
                        }
                        case WATER -> {
                            cell.setType(CellType.WATER);
                        }
                        case BOAT -> {
                            cell.setType(CellType.WALKABLE);
                            new TraversalObject(cell, BOAT);
                        }
                        case LETTER_D -> cell.setType(CellType.LETTER_D);
                        case LETTER_O -> cell.setType(CellType.LETTER_O);
                        case LETTER_N -> cell.setType(CellType.LETTER_N);
                        case LETTER_T -> cell.setType(CellType.LETTER_T);
                        case LETTER_G -> cell.setType(CellType.LETTER_G);
                        case LETTER_H -> cell.setType(CellType.LETTER_H);
                        case LETTER_E -> cell.setType(CellType.LETTER_E);
                        case LETTER_R -> cell.setType(CellType.LETTER_R);
                        case GRASS -> cell.setType(CellType.GRASS);

                        default -> throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }
}
