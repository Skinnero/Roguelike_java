package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.ui.Cell;

public class Food extends Item {
    public Food(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "food";
    }
}
