package com.codecool.dungeoncrawl.logic.gameobjects.actors;

import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.engine.Movement;
import com.codecool.dungeoncrawl.logic.engine.Position;
import com.codecool.dungeoncrawl.logic.engine.TileId;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorutils.Direction;

public abstract class ActorPlayer extends Actor{
    public ActorPlayer(TileId tileId, Position position) {
        super(tileId, position);
    }

    public abstract Movement planMovement(Direction direction);
}
