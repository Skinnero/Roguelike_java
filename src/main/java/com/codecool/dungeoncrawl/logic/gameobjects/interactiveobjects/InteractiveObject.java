package com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects;

import com.codecool.dungeoncrawl.logic.ui.Renderable;
import com.codecool.dungeoncrawl.logic.ui.utils.TileId;

public abstract class InteractiveObject implements Renderable {
    private TileId tileId;

    public InteractiveObject(TileId tileId) {
        this.tileId = tileId;
    }

    public abstract void interact();
    public abstract boolean isWalkable();

    @Override
    public TileId getTileId() {
        return tileId;
    }

    protected void setTileId(TileId tileId) {
        this.tileId = tileId;
    }

}
