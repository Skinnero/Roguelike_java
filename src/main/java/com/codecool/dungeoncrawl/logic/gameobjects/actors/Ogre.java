package com.codecool.dungeoncrawl.logic.gameobjects.actors;

import com.codecool.dungeoncrawl.logic.engine.*;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.ActorTileId;

public class Ogre extends ActorEnemy {
    private int[] firstPlace;
    private int[] patrolDestination;

    public Ogre(Position position) {
        super(ActorTileId.OGRE.getTileId(), position);
        this.setHealth(15);
    }


    public void setFirstPlace(int[] firstPlace) {
        this.firstPlace = firstPlace;
    }

    public void setPatrolDestination(int[] patrolDestination) {
        this.patrolDestination = patrolDestination;
    }

    public int[] getPatrolDestination() {
        return patrolDestination;
    }

    public int[] getFirstPlace() {
        return firstPlace;
    }

    @Override
    public Movement planMovement(GameMap map) {
        return null;
    }

    @Override
    public <T extends Actor> void planAttack(T enemy) {

    }


//    public void move(int dx, int dy) {
//        Cell nextCell = getCell().getNeighbor(dx, dy);
//        if (nextCell.getType() == CellType.UNWALKABLE) {
//            return;
//        } else if (!Objects.isNull(nextCell.getGameObject()) && nextCell.getGameObject().isInteractive()) {
//            return;
//        }
//        getCell().setActor(null);
//        nextCell.setActor(this);
//        setCell(nextCell);
//    }
}