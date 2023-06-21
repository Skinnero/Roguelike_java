package com.codecool.dungeoncrawl.logic.gameobjects.items;

import com.codecool.dungeoncrawl.logic.engine.TileId;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;

public class Key extends Item {

    public Key() {
        super(TileId.of(16, 23));
    }

    @Override
    public void onUse(Player player) {

    }

}
