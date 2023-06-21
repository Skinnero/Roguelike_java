package com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects;

import com.codecool.dungeoncrawl.logic.engine.TileId;

public class Chest extends InteractiveObject {
    public Chest() {
        super(TileId.of(8, 6));
    }

    @Override
    public void interact() {
        setTileId(TileId.of(9, 6));
    }

    @Override
    public boolean isWalkable() {
        return false;
    }
}
