package com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects;

import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.gameobjects.items.Item;
import com.codecool.dungeoncrawl.logic.gameobjects.items.Key;

public class Door extends InteractiveObject {

    public Door(Cell cell) {
        super(cell);
    }

    @Override
    public void onInteraction(Player player) {
        for (Item item : player.getInventory().getInventory()) {
            if (item instanceof Key) {
                setInteractive(false);
                player.getInventory().getInventory().remove(item);
                return;
            }
        }
    }
}
