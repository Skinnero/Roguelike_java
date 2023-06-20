package com.codecool.dungeoncrawl.logic.engine;

import com.codecool.dungeoncrawl.logic.gameobjects.actors.Actor;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorutils.Direction;

import java.awt.event.MouseEvent;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;
    private Player player;
    private List<Actor> monsters;


    public GameMap(int width, int height, TileType defaultTileType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultTileType);
            }
        }
    }

    public void movePlayer(Direction direction) {
        Movement movement = player.planMovement(direction);
        Cell nextCell = cells[movement.newPosition().x()][movement.newPosition().y()];
        Cell currentCell = cells[movement.currentPosition().x()][movement.currentPosition().y()];
        if (nextCell.isWalkable()){
            currentCell.setActor(null);
            nextCell.setActor(player);
            player.setPosition(movement.newPosition());
        }
    }

//    public void moveActors() {
//        for (Actor monster: monsters) {
//            Movement movement = monster.move(cells);
//            cells[movement.currentPosition().x()][movement.currentPosition().y()].setActor(null);
//            cells[movement.newPosition().x()][movement.newPosition().y()].setActor(monster);
//            monster.setPosition();
//        }
//    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
