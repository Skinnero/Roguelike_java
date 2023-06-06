package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.ui.Cell;
import com.codecool.dungeoncrawl.logic.ui.Drawable;

public abstract class Item implements Drawable{
    private final Cell cell;

    public Item(Cell cell) {
        this.cell = cell;
        this.cell.setItem(this);
    }

    public void onUse() {
    }

    public Cell getCell() {
        return cell;
    }
}
