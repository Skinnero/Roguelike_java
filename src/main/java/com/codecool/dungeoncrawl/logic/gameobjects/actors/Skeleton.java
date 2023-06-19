package com.codecool.dungeoncrawl.logic.gameobjects.actors;

import com.codecool.dungeoncrawl.logic.engine.Cell;

public class Skeleton extends Actor {

    public Skeleton(Cell cell) {
        super(cell);
        this.setHealth(6);
    }

    @Override
    public void move(int dx, int dy) {
    }
}
