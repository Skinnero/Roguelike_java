package com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.utils;

import com.codecool.dungeoncrawl.logic.engine.TileId;

public enum InteractiveObjectTileId {
    BOAT(TileId.of(11, 19)),
    OPEN_CHEST(TileId.of(9, 6)),
    CLOSED_CHEST(TileId.of(8, 6)),
    OPEN_DOOR(TileId.of(4, 4)),
    CLOSED_DOOR(TileId.of(3, 4));


    private final TileId tileId;

    InteractiveObjectTileId(TileId tileId) {
        this.tileId = tileId;
    }

    public TileId getTileId() {
        return tileId;
    }
}

