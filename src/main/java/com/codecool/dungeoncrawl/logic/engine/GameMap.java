package com.codecool.dungeoncrawl.logic.engine;

import com.codecool.dungeoncrawl.Dao.GameDatabaseManager;
import com.codecool.dungeoncrawl.logic.fileloader.MapLoader;
import com.codecool.dungeoncrawl.logic.gameobjects.actorenemies.ActorEnemy;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.ActorPlayer;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.Direction;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.InteractiveObject;
import com.codecool.dungeoncrawl.logic.gameobjects.items.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameMap {
    @Getter
    private int width;
    @Getter
    private int height;
    @Getter
    private Cell[][] cells;
    @Getter
    @Setter
    private Player player;
    @Getter
    @Setter
    private int mapLevel = 0;
    private List<ActorEnemy> monsters = new ArrayList<>();
    private List<Item> items = new ArrayList<>();
    private List<InteractiveObject> interactiveObjects = new ArrayList<>();


    public GameMap(int width, int height, TileType defaultTileType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(Position.of(x, y), defaultTileType);
            }
        }
    }

    public void movePlayer(Direction direction) {
        Movement movement = player.planMovement(direction);
        Cell nextCell = cells[movement.newPosition().x()][movement.newPosition().y()];
        Cell currentCell = cells[movement.currentPosition().x()][movement.currentPosition().y()];
        if (Objects.nonNull(nextCell.getActor())) {
            player.planAttack(nextCell.getActor());
            if (nextCell.getActor().isDead()) {
                monsters.remove((ActorEnemy) nextCell.getActor());
                nextCell.setActor(null);
            }
        }
        if (nextCell.isWalkable()) {
            currentCell.setActor(null);
            nextCell.setActor(player);
            player.setPosition(movement.newPosition());
        }
    }

    public void moveActorEnemy() {
        for (ActorEnemy monster : monsters) {
            Movement movement = monster.planMovement(this);
            cells[movement.currentPosition().x()][movement.currentPosition().y()].setActor(null);
            cells[movement.newPosition().x()][movement.newPosition().y()].setActor(monster);
            monster.setPosition(movement.newPosition());
        }
    }

    public void addToGameObjectList(Cell cell) {
        if (cell.getActor() instanceof ActorPlayer) {
            this.player = (Player) cell.getActor();
        } else if (Objects.nonNull(cell.getActor())) {
            monsters.add((ActorEnemy) cell.getActor());
        } else if (Objects.nonNull(cell.getItem())) {
            items.add(cell.getItem());
        } else if (Objects.nonNull(cell.getInteractiveObject())) {
            interactiveObjects.add(cell.getInteractiveObject());
        }
    }

    public Cell getCell(Position position) {
        return cells[position.x()][position.y()];
    }

    public GameMap getAnotherMap() {
        return MapLoader.loadMap("/map" + ++mapLevel + ".txt");
    }

    public Cell getPlayerCell() {
        return getCell(player.getPosition());
    }

    public <T extends Item> void removeItemFromGameObjectList(T item) {
        items.remove(item);
    }

    public void save() {
        GameDatabaseManager gameDatabaseManager = new GameDatabaseManager();
        gameDatabaseManager.setup();

    }

}
