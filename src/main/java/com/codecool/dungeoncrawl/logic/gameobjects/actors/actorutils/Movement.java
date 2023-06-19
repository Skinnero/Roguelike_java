package com.codecool.dungeoncrawl.logic.gameobjects.actors.actorutils;

import com.codecool.dungeoncrawl.logic.gameobjects.actors.Actor;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Mage;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Ogre;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.GameMap;

public class Movement {
    public boolean isMovePossible(GameMap map, int[] vector, int positionX, int positionY) {
        Cell Cell = map.getCell(positionX + vector[0], positionY + vector[1]);
        return !Cell.getType().getTileName().equals("unwalkable");
    }

    public boolean isPlayerThere(GameMap map, int[] vector, int positionX, int positionY) {
        int playerX = map.getPlayer().getX();
        int playerY = map.getPlayer().getY();

        return positionX + vector[0] == playerX && positionY + vector[1] == playerY;
    }

    public void setPatrolPlaces(Ogre ogre) {
        //TODO: move to ogre class? / adding to interface for more options
        int positionY = ogre.getY();
        int positionX = ogre.getX();
        int[] firstPlace = new int[]{positionY, positionX - 3};
        int[] patrolDestination = new int[]{positionY, positionX + 3};
        ogre.setFirstPlace(firstPlace);
        ogre.setPatrolDestination(patrolDestination);
    }

    public void goToPatrolPlace(GameMap map, Ogre ogre) {
        int positionX = ogre.getX();
        int positionY = ogre.getY();
        int[] patrolDestination = ogre.getPatrolDestination();
        if (positionX - patrolDestination[1] == 0) {
            switchPatrol(ogre);
            return;
        }
        int[] vector = new int[]{positionX - patrolDestination[1]};
        if (vector[0] > 0) {
            int[] moveVector = new int[]{-1, 0};
            if (isPlayerThere(map, moveVector, positionX, positionY)) {
                attackPlayer(map, ogre);
            } else if (isMovePossible(map, moveVector, positionX, positionY) || vector[0] == 0) {
                ogre.move(-1, 0);
            } else {
                switchPatrol(ogre);
            }
        } else {
            int[] moveVector = new int[]{1, 0};
            if (isPlayerThere(map, moveVector, positionX, positionY)) {
                attackPlayer(map, ogre);
            } else if (isMovePossible(map, moveVector, positionX, positionY) || vector[0] == 0) {
                ogre.move(1, 0);
            } else {
                switchPatrol(ogre);

            }
        }
    }
    public void guard(GameMap map, Mage mage) {
        int playerPositionX = map.getPlayer().getX();
        int playerPositionY = map.getPlayer().getY();
        int[] vector = new int[]{playerPositionX - mage.getX(), playerPositionY - mage.getY()};

        int dx = Integer.compare(vector[0], 0);
        int dy = Integer.compare(vector[1], 0);
        int[] moveVector = new int[]{dx, dy};
        if (isPlayerThere(map, moveVector, mage.getX(), mage.getY())) {
            attackPlayer(map, mage);
        } else if (isPlayerNear(mage, playerPositionX, playerPositionY)) {
            mage.move(dx, dy);
        }
    }

    public boolean isPlayerNear(Mage mage, int playerPositionX, int playerPositionY) {
        int[] distance = new int[]{Math.abs(mage.getX() - playerPositionX), Math.abs(mage.getY() - playerPositionY)};
        return 5 >= distance[0] && 5 >= distance[1];
    }

    private void attackPlayer(GameMap map, Actor actor) {
        int playerHp = map.getPlayer().getHealth();
        map.getPlayer().setHealth(playerHp - actor.getAttack());
        if (actor.isDead()) {
//            if (actor instanceof Mage) {
//                MapLoader.mages.remove(actor);
//            } else if (actor instanceof Ogre) {
//                MapLoader.ogres.remove(actor);
//            }
        }
    }

    private void switchPatrol(Ogre ogre) {
        int[] patrolDestination = ogre.getPatrolDestination();
        int[] firstPlace = ogre.getFirstPlace();
        ogre.setPatrolDestination(firstPlace);
        ogre.setFirstPlace(patrolDestination);
    }
}