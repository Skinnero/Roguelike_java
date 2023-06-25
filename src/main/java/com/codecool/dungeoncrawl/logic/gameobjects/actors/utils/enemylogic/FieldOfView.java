package com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.enemylogic;

import com.codecool.dungeoncrawl.logic.engine.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actorenemies.ActorEnemy;

public class FieldOfView {
    public boolean isPlayerNear(ActorEnemy actor, Position playerPosition, int fieldOfViewDistance) {
        Position magePosition = actor.getPosition();
        Position distance = Position.of(Math.abs(magePosition.x() - playerPosition.x()),
                Math.abs(magePosition.y() - playerPosition.y()));
        return fieldOfViewDistance >= distance.x() && fieldOfViewDistance >= distance.y();
    }
}