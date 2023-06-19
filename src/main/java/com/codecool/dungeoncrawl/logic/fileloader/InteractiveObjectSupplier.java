package com.codecool.dungeoncrawl.logic.fileloader;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.TileType;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.Chest;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.Door;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.TraversalObject;

public class InteractiveObjectSupplier {

    private InteractiveObjectSupplier() {
    }

    public static Cell door(Cell cell) {
        cell.setInteractiveObject(new Door(cell));
        cell.setType(TileType.CLOSED_DOOR);
        return cell;
    }

    public static Cell chest(Cell cell) {
        cell.setInteractiveObject(new Chest(cell));
        cell.setType(TileType.CLOSED_CHEST);
        return cell;
    }

    public static Cell boat(Cell cell) {
        cell.setInteractiveObject(new TraversalObject(cell));
        cell.setType(TileType.BOAT);
        return cell;
    }
}
