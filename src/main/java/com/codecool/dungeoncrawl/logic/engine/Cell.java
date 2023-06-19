package com.codecool.dungeoncrawl.logic.engine;

import com.codecool.dungeoncrawl.logic.gameobjects.actors.Actor;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.InteractiveObject;
import com.codecool.dungeoncrawl.logic.gameobjects.items.Item;

import java.util.Objects;

public class Cell {
    private TileType tileType;
    private Actor actor;
    private Item item;
    private InteractiveObject interactiveObject;
    private final GameMap gameMap;
    private int x, y;

    Cell(GameMap gameMap, int x, int y, TileType tileType) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.tileType = tileType;
    }

    public boolean isWalkable() {
        if (!Objects.isNull(this.getInteractiveObject()) && this.getInteractiveObject().isInteractive()) {
            return false;
        }
        if (!this.getType().isWalkable()) {
            return false;
        }
        if (!Objects.isNull(this.getActor())) {
            if (!this.getActor().isDead()) {
                return false;
            }
        }
        return true;
    }

    public boolean isVisible(Player player){
        int playerX = player.getX();
        int playerY = player.getY();
        int cellX = this.getX();
        int cellY = this.getY();

        int distance = Math.abs(playerX - cellX) + Math.abs(playerY - cellY);

        int fieldOfView = player.getFieldOfView(player, gameMap);
        boolean inFieldOfView = distance <= fieldOfView;

        return inFieldOfView;
    }

    public TileType getType() {
        return tileType;
    }

    public void setType(TileType tileType) {
        this.tileType = tileType;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setInteractiveObject(InteractiveObject interactiveObject) {
        this.interactiveObject = interactiveObject;
    }

    public InteractiveObject getInteractiveObject() {
        return interactiveObject;
    }

    public Item getItem() {
        return item;
    }

    public Actor getActor() {
        return actor;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
