package com.codecool.dungeoncrawl.logic.gameobjects.items;

import com.codecool.dungeoncrawl.logic.ui.utils.TileId;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;
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
        player.setFieldOfViewDistance(player.getFieldOfViewDistance() + value);
        player.removeFromInventory(this);
        addMessageToLog();
    }

}
