package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ogre extends Actor {
    private int health = 15;
    public Ogre(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "ogre";
    }
}
