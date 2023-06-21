package com.codecool.dungeoncrawl.logic.gameobjects.items;

import com.codecool.dungeoncrawl.logic.engine.TileId;
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
        player.addToEquipment(this);
        player.increasePerception(value);
        player.removeFromInventory(this);
        value++;
    }

}
