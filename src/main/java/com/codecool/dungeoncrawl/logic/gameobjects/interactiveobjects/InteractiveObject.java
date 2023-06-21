package com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects;

import com.codecool.dungeoncrawl.logic.engine.Renderable;
import com.codecool.dungeoncrawl.logic.engine.TileId;

public abstract class InteractiveObject implements Renderable {
    private TileId tileId;

    public InteractiveObject(TileId tileId) {
        this.tileId = tileId;
    }

    public abstract void interact();
    public abstract boolean isWalkable();

    @Override
    public TileId getTileId() {
        return null;
    }

    protected void setTileId(TileId tileId) {
        this.tileId = tileId;
    }

}
