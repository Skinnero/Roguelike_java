package com.codecool.dungeoncrawl.logic.gameobjects.items;

import com.codecool.dungeoncrawl.logic.engine.Renderable;
import com.codecool.dungeoncrawl.logic.engine.TileId;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;

public abstract class Item implements Renderable {
    private final TileId tileId;

    public Item(TileId tileId) {
        this.tileId = tileId;
    }

    @Override
    public TileId getTileId() {
        return tileId;
    }

    public abstract void onUse(Player player);

}
