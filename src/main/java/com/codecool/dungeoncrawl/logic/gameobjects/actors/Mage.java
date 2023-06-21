package com.codecool.dungeoncrawl.logic.gameobjects.actors;

import com.codecool.dungeoncrawl.logic.engine.*;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.ActorTileId;

public class Mage extends ActorEnemy {

    public Mage(Position position) {
        super(ActorTileId.MAGE.getTileId(), position);
        this.setHealth(6);
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