package com.codecool.dungeoncrawl.logic.gameobjects.actors;

import com.codecool.dungeoncrawl.logic.engine.Cell;

public abstract class Actor {
    private Cell cell;
    private int health = 20;
    private int defense = 1;
    private int attack = 3;

    public Actor() {

    }

    public Actor(Cell cell) {
        this.cell = cell;
    }

    public abstract void move(int dx, int dy);

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

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Cell getCell() {
        return cell;
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

    public int getX() {
        return getCell().getX();
    }

    public int getY() {
        return getCell().getY();
    }
}
