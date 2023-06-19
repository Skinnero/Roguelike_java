package com.codecool.dungeoncrawl.logic.gameobjects.items;

import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.engine.Cell;

public abstract class Item {
    private Cell cell;

    public Item() {

    }

    public Item(Cell cell) {
        this.cell = cell;
    }

    public abstract void onUse(Player player);

}
