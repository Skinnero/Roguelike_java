package com.codecool.dungeoncrawl.logic.gameobjects.items;

import com.codecool.dungeoncrawl.logic.engine.TileId;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.engine.Cell;

public class Food extends Item {
    public static int value = 2;

    public Food () {
        super(TileId.of(15, 29));
    }

    @Override
    public void onUse(Player player) {
        player.increaseHealth(value);
        player.removeFromInventory(this);
    }
}
