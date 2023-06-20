package com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects;

import com.codecool.dungeoncrawl.logic.engine.TileId;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.fileloader.MapLoader;

public class Boat extends InteractiveObject {

    public Boat() {
        super(TileId.of(11, 19));
    }


    @Override
    public void interact() {

    }

    @Override
    public boolean isWalkable() {
        return false;
    }
}
