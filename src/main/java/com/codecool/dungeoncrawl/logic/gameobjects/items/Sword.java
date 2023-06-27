package com.codecool.dungeoncrawl.logic.gameobjects.items;

import com.codecool.dungeoncrawl.logic.ui.utils.TileId;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.items.utils.ItemTileId;

public class Sword implements Item {
    private final int value = 1;

    private final TileId tileId = ItemTileId.SWORD.getTileId();

    public Sword() {
    }

    @Override
    public TileId getTileId() {
        return tileId;
    }

    @Override
    public void onUse(Player player) {
//        player.addToEquipment(this);
        player.removeFromInventory(this);
        player.setAttack(player.getAttack() + value);
        addMessageToLog();
    }
}
