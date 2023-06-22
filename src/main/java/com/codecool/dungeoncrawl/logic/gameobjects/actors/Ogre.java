package com.codecool.dungeoncrawl.logic.gameobjects.actors;

import com.codecool.dungeoncrawl.logic.engine.*;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.ActorTileId;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.enemylogic.Behavior;
import lombok.Getter;
import lombok.Setter;

public class Ogre extends ActorEnemy {
    @Setter
    @Getter
    private Position firstPlace;
    @Setter
    @Getter
    private Position patrolDestination;

    public Ogre(Position position) {
        super(ActorTileId.OGRE.getTileId(), position);
        this.setHealth(15);
        this.setFieldOfViewDistance(2);
        setPatrolPlaces();
    }

    @Override
    public Movement planMovement(GameMap map) {
        Behavior behavior = new Behavior();
        Position newPosition = behavior.goToPatrolPlace(map, this);
        if (isPlayerAttackable(map, getPosition(), 1)) {
            attackPlayer();
            return Movement.of(getPosition(), getPosition());
        }
        return Movement.of(getPosition(), newPosition);
    }

    @Override
    public <T extends Actor> void planAttack(T enemy) {
    }

    public void setPatrolPlaces() {
        setFirstPlace(Position.of(getPosition().y(), getPosition().x() -3));
        setPatrolDestination(Position.of(getPosition().y(), getPosition().x() + 3));
    }

    public void switchPatrol() {
        setPatrolDestination(firstPlace);
        setFirstPlace(patrolDestination);
    }



}