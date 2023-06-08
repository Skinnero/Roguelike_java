package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.Drawable;

public abstract class Item implements Drawable{
    private final Cell cell;

    public Item(Cell cell) {
        this.cell = cell;
        this.cell.setItem(this);
    }

    public void onUse(Player player) {
    }

    public void removeItemFromMap() {
        this.cell.setItem(null);
    }

    public Cell getCell() {
        return cell;
    }

}
