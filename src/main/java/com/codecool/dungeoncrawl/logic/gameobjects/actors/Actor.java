package com.codecool.dungeoncrawl.logic.gameobjects.actors;

import com.codecool.dungeoncrawl.logic.engine.*;
import lombok.Getter;
import lombok.Setter;

public abstract class Actor implements Renderable {
    @Getter
    @Setter
    private TileId tileId;
    @Getter
    @Setter
    private int health = 20;
    @Getter
    @Setter
    private int defense = 1;
    @Getter
    @Setter
    private int attack = 3;
    @Getter
    @Setter
    private Position position;

    public Actor(TileId tileId, Position position) {
        this.tileId = tileId;
        this.position = position;
    }

    public abstract <T extends Actor> void planAttack(T enemy);

    public boolean isDead() {
        return health <= 0;
    }

}
