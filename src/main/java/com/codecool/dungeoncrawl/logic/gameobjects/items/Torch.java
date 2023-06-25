package com.codecool.dungeoncrawl.logic.gameobjects.items;

import com.codecool.dungeoncrawl.logic.ui.TileId;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.items.utils.ItemTileId;

public class Torch implements Item {

    private final TileId tileId = ItemTileId.TORCH.getTileId();

    public static int value = 1;


    public Torch() {
    }

    @Override
    public TileId getTileId() {
        return tileId;
    }

    @Override
    public void onUse(Player player) {
        player.setPerception(player.getPerception() + value);
//        player.addToEquipment(this);
        player.removeFromInventory(this);
        addMessageToLog();
    }

}
