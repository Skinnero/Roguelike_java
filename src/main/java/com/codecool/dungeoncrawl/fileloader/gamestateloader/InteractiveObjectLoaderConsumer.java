package com.codecool.dungeoncrawl.fileloader.gamestateloader;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.Boat;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.Chest;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.Door;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.InteractiveObject;
import com.codecool.dungeoncrawl.model.BaseModel;
import com.codecool.dungeoncrawl.model.InteractiveObjectModel;

public class InteractiveObjectLoaderConsumer {
    public static void door(BaseModel baseModel, Cell cell) {
        InteractiveObjectModel interactiveObjectModel = (InteractiveObjectModel) baseModel;
        Door door = new Door(Position.of(cell.getPosition().x(), cell.getPosition().y()));
        if (interactiveObjectModel.isInteracted()) {
            door.setOpen(true);
            door.setLocked(false);
        }
        cell.setInteractiveObject(door);
    }

    public static void chest(BaseModel baseModel, Cell cell) {
        InteractiveObjectModel interactiveObjectModel = (InteractiveObjectModel) baseModel;
        Chest chest = new Chest(Position.of(cell.getPosition().x(), cell.getPosition().y()));
        if (interactiveObjectModel.isInteracted()) {
            chest.setOpen();
        }
        cell.setInteractiveObject(chest);
    }

    public static void boat(BaseModel baseModel, Cell cell) {
        cell.setInteractiveObject(new Boat(Position.of(cell.getPosition().x(), cell.getPosition().y())));
    }
}
