package com.codecool.dungeoncrawl.logic.gameobjects.items;

import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.engine.Cell;

public class Key extends Item {
    public Key() {
    }

    public Key(Cell cell) {
        super(cell);
    }

    @Override
    public void onUse(Player player) {

    }

}
