package com.codecool.dungeoncrawl.logic.gameobjects.actors.utils;

import com.codecool.dungeoncrawl.logic.ui.utils.TileId;

public enum ActorTileId {
    PLAYER(TileId.of(27, 0)),
    OGRE(TileId.of(30, 6)),
    SKELETON(TileId.of(29, 6)),
    MAGE(TileId.of(24, 6));

    private final TileId tileId;

    ActorTileId(TileId tileId) {
        this.tileId = tileId;
    }

    public TileId getTileId() {
        return tileId;
    }
}
