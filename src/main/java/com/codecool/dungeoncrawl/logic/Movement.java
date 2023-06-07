package com.codecool.dungeoncrawl.logic;

public class Movement {
    public boolean isMovePossible (GameMap map, int[] vector) {
        int playerX = map.getPlayer().getX();
        int playerY = map.getPlayer().getY();
        Cell nextCell = map.getCell(playerX+vector[0], playerY+vector[1]);
        if (nextCell.getType().getTileName().equals("enemy") || nextCell.getType().getTileName().equals("wall")) {
            return false;
        }
        return true;
    }
    public boolean isEnemyThere (GameMap map, int[] vector) {
        int playerX = map.getPlayer().getX();
        int playerY = map.getPlayer().getY();
        Cell nextCell = map.getCell(playerX+vector[0], playerY+vector[1]);
        if (nextCell.getType().getTileName().equals("enemy")) {
            return false;
        }
        return true;
    }

}
