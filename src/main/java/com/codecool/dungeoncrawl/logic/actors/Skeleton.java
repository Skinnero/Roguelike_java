package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.ui.Cell;

public class Skeleton extends Actor {
    public Skeleton(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
