package com.codecool.dungeoncrawl.logic.gameobjects.actors;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.ActorTileId;

public class Skeleton extends Actor {

    public Skeleton(Position position) {
        super(ActorTileId.SKELETON.getTileId(), position);
        this.setHealth(6);
    }


    @Override
    public <T extends Actor> void planAttack(T enemy) {

    }
}
