package com.codecool.dungeoncrawl.logic.gameobjects.actors;

import com.codecool.dungeoncrawl.logic.engine.*;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.ActorTileId;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.enemylogic.Behavior;

public class Mage extends ActorEnemy {

    public Mage(Position position) {
        super(ActorTileId.MAGE.getTileId(), position);
        this.setHealth(6);
        this.setFieldOfViewDistance(5);
    }

    @Override
    public Movement planMovement(GameMap map) {
        Behavior behavior = new Behavior();
        Position newPosition = behavior.guard(map, this);
        if (isPlayerAttackable(map, getPosition())) {
            attackPlayer();
            return Movement.of(getPosition(), getPosition());
        }
        return Movement.of(getPosition(), newPosition);
    }

    @Override
    public <T extends Actor> void planAttack(T enemy) {

    }

}