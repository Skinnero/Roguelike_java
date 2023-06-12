package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.engine.Cell;

public class Armor extends Item{

    public Armor(Cell cell) {
        super(cell);
    }

    @Override
    public void onUse(Player player) {
        player.addToEquipment(this);
    }

    @Override
    public String getTileName() {
        return "armor";
    }
}
