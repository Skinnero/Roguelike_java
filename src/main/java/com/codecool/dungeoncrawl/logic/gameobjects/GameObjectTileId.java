package com.codecool.dungeoncrawl.logic.gameobjects;

import com.codecool.dungeoncrawl.logic.engine.TileId;

public enum GameObjectTileId {
    PLAYER(TileId.of(27, 0)),
    MAGE(TileId.of(24, 6));

    private final TileId tileId;

    GameObjectTileId(TileId tileId) {
        this.tileId = tileId;
    }

    public TileId getTileId() {
        return tileId;
    }
}
