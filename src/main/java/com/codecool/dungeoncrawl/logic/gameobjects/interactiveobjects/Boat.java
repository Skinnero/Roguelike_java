package com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects;

import com.codecool.dungeoncrawl.logic.engine.TileId;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.utils.InteractiveObjectTileId;

public class Boat extends InteractiveObject {

    public Boat() {
        super(InteractiveObjectTileId.BOAT.getTileId());
    }


    @Override
    public void interact() {

    }

    @Override
    public boolean isWalkable() {
        return true;
    }
}
