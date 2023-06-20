package com.codecool.dungeoncrawl.logic.gameobjects.items;

import com.codecool.dungeoncrawl.logic.engine.TileId;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.engine.Cell;

public class Torch extends Item {

    public static int value = 1;


    public Torch() {
        super(TileId.of(4, 15));
    }

    @Override
    public void onUse(Player player) {
        player.addToEquipment(this);
        player.increasePerception(value);
        player.removeFromInventory(this);
        value++;
    }

}
