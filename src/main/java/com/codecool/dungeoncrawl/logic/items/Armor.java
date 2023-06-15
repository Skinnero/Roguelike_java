package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.engine.Cell;

public class Armor extends Item {
    public static int value = 1;

    public Armor() {

    }
    public Armor(Cell cell) {
        super(cell);
    }

    @Override
    public void onUse(Player player) {
        player.addToEquipment(this);
        player.increaseDefense(value);
        player.removeFromInventory(this);
        value++;
    }

    @Override
    public String getTileName() {
        return "armor";
    }
}
