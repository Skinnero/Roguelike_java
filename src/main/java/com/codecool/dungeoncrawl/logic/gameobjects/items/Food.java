package com.codecool.dungeoncrawl.logic.gameobjects.items;

import com.codecool.dungeoncrawl.logic.ui.utils.TileId;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.items.utils.ItemTileId;

public class Food implements Item {

    private final TileId tileId = ItemTileId.FOOD.getTileId();

    public final int value = 2;

    public Food () {
    }

    @Override
    public TileId getTileId() {
        return tileId;
    }

    @Override
    public void onUse(Player player) {
        if (player.getHealth() <= player.MAX_HEALTH - value) {
            player.removeFromInventory(this);
            player.setHealth(player.getHealth() + value);
            addMessageToLog();
        }
    }
}
