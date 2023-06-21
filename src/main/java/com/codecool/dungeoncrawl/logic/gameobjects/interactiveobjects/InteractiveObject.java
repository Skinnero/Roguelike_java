package com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects;

import com.codecool.dungeoncrawl.logic.engine.Renderable;
import com.codecool.dungeoncrawl.logic.engine.TileId;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.engine.Cell;

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
