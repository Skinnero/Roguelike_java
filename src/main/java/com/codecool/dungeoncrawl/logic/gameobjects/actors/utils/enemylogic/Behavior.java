package com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.enemylogic;

import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.engine.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.ActorEnemy;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Mage;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Ogre;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.Direction;
import javafx.geometry.Pos;

public class Behavior {
    public Position goToPatrolPlace(GameMap map, Ogre ogre) {
        EnemyMovement enemyMovement = new EnemyMovement();
        FieldOfView fieldOfView = new FieldOfView();
        Position ogrePosition = ogre.getPosition();
        Position playerPosition = map.getPlayer().getPosition();
        Position patrolDestination = ogre.getPatrolDestination();
        int vector = ogrePosition.x() - patrolDestination.x();

        if (ogrePosition.x() - patrolDestination.x() == 0) {
            ogre.switchPatrol();
        }
        Position moveVector;
        if (fieldOfView.isPlayerNear(ogre, playerPosition, ogre.getFieldOfViewDistance())) {
            moveVector = vectorToPlayer(map, ogre);
            return enemyMovement.moveTowardsPlayer(map, ogre, moveVector);
        }
        if (vector > 0) {
            moveVector = Direction.LEFT.getPosition();
        } else {
            moveVector = Direction.RIGHT.getPosition();
        }
        return enemyMovement.handlePatrol(map, ogre, moveVector);
    }

    public boolean isPlayerThere(GameMap map, Position vector, Position enemyPosition) {
        Position playerPosition = map.getPlayer().getPosition();
        return enemyPosition.x() + vector.x() == playerPosition.x() &&
                enemyPosition.y() + vector.y() == playerPosition.y();
    }

    public Position guard(GameMap map, Mage mage) {
        FieldOfView fieldOfView = new FieldOfView();
        Position playerPosition = map.getPlayer().getPosition();
        Position magePosition = mage.getPosition();
        int fieldOfViewDistance = mage.getFieldOfViewDistance();

        Position vector = Position.of(playerPosition.x() - magePosition.x(), playerPosition.y() - magePosition.y());

        int dx = Integer.compare(vector.x(), 0);
        int dy = Integer.compare(vector.y(), 0);
        Position moveVector = Position.of(dx, dy);
        if (isPlayerThere(map, moveVector, magePosition)) {
            return magePosition;
        } else if (fieldOfView.isPlayerNear(mage, playerPosition, fieldOfViewDistance)) {
            return Position.of(mage.getPosition().x() + moveVector.x(), mage.getPosition().y() + moveVector.y());
        }
        return magePosition;
    }

    public Position vectorToPlayer(GameMap map, ActorEnemy actor) {
        // TODO: actorEnemies moves through object that are not walkable, they shouldn't
        Position playerPosition = map.getPlayer().getPosition();
        Position position = actor.getPosition();

        if (Math.abs(playerPosition.x() - position.x()) >= Math.abs(playerPosition.y() - position.y())) {
            if (playerPosition.x() - position.x() > 0) {
                return Direction.RIGHT.getPosition();
            }
            return Direction.LEFT.getPosition();
        }
        if (playerPosition.y() - position.y() > 0) {
            return Direction.DOWN.getPosition();
        }
        return Direction.UP.getPosition();
    }
}
