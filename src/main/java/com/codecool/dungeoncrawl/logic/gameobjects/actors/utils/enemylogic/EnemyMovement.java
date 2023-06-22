package com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.enemylogic;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.engine.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.ActorEnemy;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Ogre;

public class EnemyMovement {
    public boolean isMovePossible(GameMap map, Position vector, int positionX, int positionY) {
        Cell cell = map.getCell(Position.of(positionX + vector.x(), positionY + vector.y()));
        return cell.isWalkable();
    }

    public void handlePatrol(GameMap map, Ogre ogre, Position moveVector, int positionX, int positionY) {
        EnemyMovement enemyMovement = new EnemyMovement();
        Behavior behavior = new Behavior();
        Attack attack = new Attack();
        if (behavior.isPlayerThere(map, moveVector, positionX, positionY)) {
            attack.attackPlayer(map, ogre);
        } else if (enemyMovement.isMovePossible(map, moveVector, positionX, positionY)) {
            ogre.move(map, moveVector);
        } else {
            ogre.switchPatrol();
        }
    }

    public Position moveTowardsPlayer(GameMap map, ActorEnemy actor, Position moveVector, Position actorPosition) {
        Behavior behavior = new Behavior();
        Attack attack = new Attack();
        FieldOfView fieldOfView = new FieldOfView();
        Position playerPosition = map.getPlayer().getPosition();
        int playerPositionX = playerPosition.x();
        int playerPositionY = playerPosition.y();
        int fieldOfViewDistance = actor.getFieldOfViewDistance();
        if (behavior.isPlayerThere(map, moveVector, actorPosition.x(), actorPosition.y())) {
            attack.attackPlayer(map, actor);
        } else if (fieldOfView.isPlayerNear(actor, playerPositionX, playerPositionY, fieldOfViewDistance)) {
            actor.move(map, moveVector);
        }
    }

}
