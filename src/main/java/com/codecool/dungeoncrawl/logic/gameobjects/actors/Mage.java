package com.codecool.dungeoncrawl.logic.gameobjects.actors;

import com.codecool.dungeoncrawl.logic.engine.*;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.ActorTileId;

public class Mage extends ActorEnemy {

    public Mage(Position position) {
        super(ActorTileId.MAGE.getTileId(), position);
        this.setHealth(6);
        this.setFieldOfViewDistance(5);
    }

    @Override
    public Movement planMovement(GameMap map) {
        return null;
    }

    @Override
    public <T extends Actor> void planAttack(T enemy) {

    }

}