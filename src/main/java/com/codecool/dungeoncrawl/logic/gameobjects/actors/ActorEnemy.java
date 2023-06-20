package com.codecool.dungeoncrawl.logic.gameobjects.actors;

import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.engine.Movement;
import com.codecool.dungeoncrawl.logic.engine.Position;
import com.codecool.dungeoncrawl.logic.engine.TileId;

public abstract class ActorEnemy extends Actor{
    public ActorEnemy(TileId tileId, Position position) {
        super(tileId, position);
    }

    public abstract Movement planMovement(GameMap map);

}
