package com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies;

import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.engine.Movement;
import com.codecool.dungeoncrawl.logic.engine.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Actor;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.ActorTileId;

public class Skeleton extends ActorEnemy {
    //TODO: SKELETON ATTACKS AND REACTION TO A PLAYER ARE STILL NOT IMPLEMENTED

    public Skeleton(Position position) {
        super(ActorTileId.SKELETON.getTileId(), position);
        this.setHealth(6);
    }

    @Override
    public Movement planMovement(GameMap map) {
        return Movement.of(getPosition(), getPosition());
    }

    @Override
    public <T extends Actor> void planAttack(T enemy) {

    }

}
