package com.codecool.dungeoncrawl.logic.gameobjects.actors;

import com.codecool.dungeoncrawl.logic.engine.Cell;

public class Mage extends Actor {

    public Mage(Cell cell) {
        super(cell);
        this.setHealth(6);
    }

    @Override
    public void move(int dx, int dy) {

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