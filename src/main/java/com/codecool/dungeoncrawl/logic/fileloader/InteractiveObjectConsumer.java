package com.codecool.dungeoncrawl.logic.fileloader;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.TileType;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.Chest;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.Door;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.Boat;

public class InteractiveObjectConsumer {

    private InteractiveObjectConsumer() {
    }

    public static Cell door(Cell cell) {
        cell.setInteractiveObject(new Door());
        return cell;
    }

    public static Cell chest(Cell cell) {
        cell.setInteractiveObject(new Chest());
        return cell;
    }

    public static Cell boat(Cell cell) {
        cell.setInteractiveObject(new Boat());
        return cell;
    }
}
