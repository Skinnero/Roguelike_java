package com.codecool.dungeoncrawl.logic.gameobjects.actors;

import com.codecool.dungeoncrawl.logic.engine.*;

public abstract class ActorEnemy extends Actor{
    public ActorEnemy(TileId tileId, Position position) {
        super(tileId, position);
    }

    public abstract Movement planMovement(GameMap map);

    public void move(GameMap map, int dx, int dy) {
        Cell nextCell = map.getCell(Position.of(dx, dy));
        if (!nextCell.isWalkable()) {
            return;
        }
//        map.getCell(Position.of(dx,dy)).setActor(null);
//        nextCell.setActor(this);
    }
}
