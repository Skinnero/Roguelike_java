package com.codecool.dungeoncrawl.logic.gameobjects.actors;

import com.codecool.dungeoncrawl.logic.engine.Position;
import com.codecool.dungeoncrawl.logic.engine.TileId;

public abstract class ActorPlayer extends Actor{
    public ActorPlayer(TileId tileId, Position position) {
        super(tileId, position);
    }
}
