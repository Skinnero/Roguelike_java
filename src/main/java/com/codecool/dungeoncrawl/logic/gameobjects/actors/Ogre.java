package com.codecool.dungeoncrawl.logic.gameobjects.actors;

import com.codecool.dungeoncrawl.logic.engine.*;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.ActorTileId;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.enemylogic.Behavior;
import lombok.Getter;
import lombok.Setter;

public class Ogre extends ActorEnemy {
    @Setter
    @Getter
    private int[] firstPlace;
    @Setter
    @Getter
    private int[] patrolDestination;

    public Ogre(Position position) {
        super(ActorTileId.OGRE.getTileId(), position);
        this.setHealth(15);
        this.setFieldOfViewDistance(2);
    }

    @Override
    public Movement planMovement(GameMap map) {
        return null;
    }

    @Override
    public <T extends Actor> void planAttack(T enemy) {
    }

    public void setPatrolPlaces() {
        Position ogrePosition = getPosition();
        int positionY = ogrePosition.y();
        int positionX = ogrePosition.x();
        int[] firstPlace = new int[]{positionY, positionX - 3};
        int[] patrolDestination = new int[]{positionY, positionX + 3};
        setFirstPlace(firstPlace);
        setPatrolDestination(patrolDestination);
    }

    public void switchPatrol() {
        int[] patrolDestination = getPatrolDestination();
        int[] firstPlace = getFirstPlace();
        setPatrolDestination(firstPlace);
        setFirstPlace(patrolDestination);
    }
}