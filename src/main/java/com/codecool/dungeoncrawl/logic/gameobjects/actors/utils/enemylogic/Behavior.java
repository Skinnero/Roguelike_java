package com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.enemylogic;

import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.engine.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.ActorEnemy;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Mage;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Ogre;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.Direction;

public class Behavior {
    public Position goToPatrolPlace(GameMap map, Ogre ogre) {
        EnemyMovement enemyMovement = new EnemyMovement();
        FieldOfView fieldOfView = new FieldOfView();
        Position ogrePosition = ogre.getPosition();
        Position playerPosition = map.getPlayer().getPosition();
        int positionX = ogrePosition.x();
        int positionY = ogrePosition.y();
        Position patrolDestination = ogre.getPatrolDestination();
        int vector = positionX - patrolDestination.y();

        if (positionX - patrolDestination.y() == 0) {
            ogre.switchPatrol();
        }
        Position moveVector;
        if(fieldOfView.isPlayerNear(ogre, playerPosition.x(), playerPosition.y(), ogre.getFieldOfViewDistance())) {
            moveVector = vectorToPlayer(map, ogre);
            enemyMovement.moveTowardsPlayer(map, ogre, moveVector, ogrePosition);
        }
        if (vector > 0) {
            moveVector = Direction.LEFT.getPosition();
            return moveVector;
//            enemyMovement.handlePatrol(map, ogre, moveVector, positionX, positionY);
        } else {
            moveVector = Direction.RIGHT.getPosition();
            return moveVector;
//            enemyMovement.handlePatrol(map, ogre, moveVector, positionX, positionY);
        }
    }

    public boolean isPlayerThere(GameMap map, Position vector, int positionX, int positionY) {
        Position playerPosition = map.getPlayer().getPosition();
        int playerX = playerPosition.x();
        int playerY = playerPosition.y();
        return positionX + vector.x() == playerX && positionY + vector.y() == playerY;
    }

    public void guard(GameMap map, Mage mage) {
        FieldOfView fieldOfView = new FieldOfView();
        Attack attack = new Attack();
        Position playerPosition = map.getPlayer().getPosition();
        Position magePosition = mage.getPosition();
        int fieldOfViewDistance = mage.getFieldOfViewDistance();

        Position vector = Position.of(playerPosition.x() - magePosition.x(), playerPosition.y() - magePosition.y());

        int dx = Integer.compare(vector.x(), 0);
        int dy = Integer.compare(vector.y(), 0);
        Position moveVector = Position.of(dx, dy);
        if (isPlayerThere(map, moveVector, magePosition.x(), magePosition.y())) {
            attack.attackPlayer(map, mage);
        } else if (fieldOfView.isPlayerNear(mage, playerPosition.x(), playerPosition.y(), fieldOfViewDistance)) {
            mage.move(map, moveVector);
        }
    }

    public Position vectorToPlayer(GameMap map, ActorEnemy actor) {
        Position playerPosition = map.getPlayer().getPosition();
        Position position = actor.getPosition();
        Position moveVector;
        if(Math.abs(playerPosition.x()-position.x()) >= Math.abs(playerPosition.y()-position.y())) {
            if(playerPosition.x()-position.x() > 0) {
                moveVector = Direction.RIGHT.getPosition();
            } else {
                moveVector = Direction.LEFT.getPosition();
            }
        } else {
            if(playerPosition.y()-position.y() > 0) {
                moveVector = Direction.DOWN.getPosition();
            } else {
                moveVector = Direction.UP.getPosition();
            }
        }
        return moveVector;
    }
}
