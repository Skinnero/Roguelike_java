package com.codecool.dungeoncrawl.logic.gameobjects.actors;

import com.codecool.dungeoncrawl.logic.engine.Position;
import com.codecool.dungeoncrawl.logic.engine.TileId;

public class Skeleton extends Actor {

    public Skeleton(Position position) {
        super(TileId.of(29, 6), position);
        this.setHealth(6);
    }

}
