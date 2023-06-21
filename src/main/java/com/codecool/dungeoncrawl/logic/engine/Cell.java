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
    private final Position position;

    Cell(GameMap gameMap, Position position, TileType tileType) {
        this.gameMap = gameMap;
        this.position = position;
        this.tileType = tileType;
    }

    public TileId getVisibleObjectId() {
        if (Objects.nonNull(this.getActor())) {
            return this.getActor().getTileId();
        }
        if (Objects.nonNull(this.getItem())) {
            return this.getItem().getTileId();
        }
        if (Objects.nonNull(this.getInteractiveObject())) {
            return this.getInteractiveObject().getTileId();
        }
        return tileType.getTileId();
    }

    public boolean isWalkable() {
        if (!this.tileType.isWalkable()) {
            return false;
        }
        if (Objects.nonNull(this.getActor())) {
            return false;
        }
        if (Objects.nonNull(this.getInteractiveObject()) && !this.getInteractiveObject().isWalkable()) {
            return false;
        }
        return true;
    }

    public boolean isVisible(Player player) {
        int playerX = player.getPosition().x();
        int playerY = player.getPosition().y();
        int cellX = position.x();
        int cellY = position.y();

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
        return gameMap.getCell(Position.of(position.x() + dx, position.y() + dy));
    }

    public Position getPosition() {
        return position;
    }

}
