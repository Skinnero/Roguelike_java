package com.codecool.dungeoncrawl.logic.gameobjects.items;

import com.codecool.dungeoncrawl.logic.ui.utils.TileId;

import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.items.utils.ItemTileId;

public class Armor implements Item {
    private final int value = 1;
    private final TileId tileId = ItemTileId.ARMOR.getTileId();

    public Armor() {
    }

    @Override
    public TileId getTileId() {
        return tileId;

    }

    @Override
    public void onUse(Player player) {
        player.removeFromInventory(this);
        player.setDefense(player.getDefense() + value);
        addMessageToLog();
    }

}
