package com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.enemylogic;

import com.codecool.dungeoncrawl.logic.engine.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.ActorEnemy;

public class FieldOfView {
    public boolean isPlayerNear(ActorEnemy actor, int playerPositionX, int playerPositionY, int fieldOfViewDistance) {
        Position magePosition = actor.getPosition();
        int[] distance = new int[]{Math.abs(magePosition.x() - playerPositionX),
                Math.abs(magePosition.y() - playerPositionY)};
        return fieldOfViewDistance >= distance[0] && fieldOfViewDistance >= distance[1];
    }
}