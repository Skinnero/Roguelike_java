package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.engine.Cell;

public class Food extends Item {

    public static int value = 2;

    public Food () {
    }

    public Food(Cell cell) {
        super(cell);
    }

    @Override
    public void onUse(Player player) {
        player.increaseHealth(value);
        player.removeFromInventory(this);
    }

    @Override
    public String getTileName() {
        return "food";
    }
}
