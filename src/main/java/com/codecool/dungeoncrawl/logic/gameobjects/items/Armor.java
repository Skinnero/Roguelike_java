package com.codecool.dungeoncrawl.logic.gameobjects.items;

import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.engine.Cell;

public class Armor extends Item {

    public Armor() {

    }
    public Armor(Cell cell) {
        super(cell);
    }

    @Override
    public void onUse(Player player) {
        player.addToEquipment(this);
        player.removeFromInventory(this);
    }

}
