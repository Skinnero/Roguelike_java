package com.codecool.dungeoncrawl.logic.gameobjects.actors;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.Position;
import com.codecool.dungeoncrawl.logic.engine.TileId;
import com.codecool.dungeoncrawl.logic.engine.TileType;

public class Skeleton extends Actor {

    public Skeleton(Position position) {
        super(TileId.of(29, 6), position);
        this.setHealth(6);
    }

}
