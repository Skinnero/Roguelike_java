package com.codecool.dungeoncrawl.logic.gameobjects.actors;

import com.codecool.dungeoncrawl.logic.engine.*;

public abstract class Actor implements Renderable {
    private final TileId tileId;
    private int health = 20;
    private int defense = 1;
    private int attack = 3;
    private Position position;

    public Actor(TileId tileId, Position position) {
        this.tileId = tileId;
        this.position = position;
    }

    public abstract <T extends Actor> void planAttack(T enemy);

    public boolean isDead() {
        return health <= 0;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
    public TileId getTileId() {
        return tileId;
    }

    public int getDefense() {
        return defense;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

}
