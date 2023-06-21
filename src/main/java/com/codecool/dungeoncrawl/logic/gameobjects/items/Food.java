package com.codecool.dungeoncrawl.logic.gameobjects.items;

import com.codecool.dungeoncrawl.logic.engine.TileId;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.items.utils.ItemTileId;

public class Food implements Item {

    private final TileId tileId = ItemTileId.FOOD.getTileId();

    public static int value = 2;

    public Food () {
    }

    @Override
    public TileId getTileId() {
        return tileId;
    }

    @Override
    public void onUse(Player player) {
        player.increaseHealth(value);
        player.removeFromInventory(this);
    }
}
