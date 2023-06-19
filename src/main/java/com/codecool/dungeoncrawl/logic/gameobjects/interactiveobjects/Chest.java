package com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects;

import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.gameobjects.items.Food;

public class Chest extends InteractiveObject {
    public Chest(Cell cell) {
        super(cell);
    }
    @Override
    public void onInteraction(Player player) {
        player.addToInventory(new Food());
        setInteractive(false);
    }
}
