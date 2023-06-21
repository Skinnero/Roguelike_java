package com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects;

import com.codecool.dungeoncrawl.logic.engine.TileId;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.utils.InteractiveObjectTileId;

public class Chest extends InteractiveObject {
    public Chest() {
        super(InteractiveObjectTileId.CLOSED_CHEST.getTileId());
    }

    @Override
    public void interact() {
        setTileId(InteractiveObjectTileId.OPEN_CHEST.getTileId());
    }

    @Override
    public boolean isWalkable() {
        return false;
    }
}
