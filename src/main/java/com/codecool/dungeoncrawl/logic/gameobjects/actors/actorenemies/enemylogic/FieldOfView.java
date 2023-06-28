package com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies.enemylogic;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Actor;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorenemies.ActorEnemy;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;
import com.codecool.dungeoncrawl.logic.ui.utils.TileType;

import java.util.ArrayList;
import java.util.List;

public class FieldOfView {
    public boolean isPlayerNear(GameMap map, ActorEnemy actor) {
        List<Cell> visibleCells = fieldOfView(map, actor);
        for (Cell cell : visibleCells) {
            if (cell.getActor() instanceof Player) {
                return true;
            }
        }
        return false;
    }

    public List<Cell> fieldOfView(GameMap map, Actor actor) {
        List<Cell> visibleCells = new ArrayList<>();
        for (int i = 1; i < 180; i++) {
            double linerFunctionFactor = calculateLinearFunctionFactor(i);
            visibleCells = calculateLineOfView(map, actor, linerFunctionFactor, visibleCells);

        }
        return visibleCells;
    }

    public List<Cell> calculateLineOfView(GameMap map, Actor actor, double linerFunctionFactor, List<Cell> visibleCells) {
        Position actorPosition = actor.getPosition();
        double x = 0;
        double y;
        double step = calculateStep(linerFunctionFactor);
        do {
            x = x + step;
            y = x * linerFunctionFactor;
            Position cellPosition = Position.of(actorPosition.x() + (int) Math.floor(x),
                    actorPosition.y() + (int) Math.floor(y));
            addCellToList(visibleCells, map.getCell(cellPosition));
            if (map.getCell(cellPosition).getTileType() == TileType.WALL) {
                break;
            }
        }
        while (Math.abs(x) <= actor.getFieldOfViewDistance() && Math.abs(y) <= actor.getFieldOfViewDistance());
        x = 0;
        do {
            x = x - step;
            y = x * linerFunctionFactor;
            Position cellPosition = Position.of(actorPosition.x() + (int) Math.floor(x),
                    actorPosition.y() + (int) Math.floor(y));
            addCellToList(visibleCells, map.getCell(cellPosition));
            if (map.getCell(cellPosition).getTileType() == TileType.WALL) {
                break;
            }
        }
        while (x >= -actor.getFieldOfViewDistance() && y >= -actor.getFieldOfViewDistance());
        return visibleCells;
    }

    public void addCellToList(List<Cell> visibleCells, Cell newCell) {
        if (!visibleCells.contains(newCell)) {
            visibleCells.add(newCell);
        }
    }

    public double calculateStep(double linerFunctionFactor) {
        if (Math.abs(linerFunctionFactor) < 1) {
            return linerFunctionFactor / 10;
        }
        return 1 / (linerFunctionFactor * 5);
    }

    public double calculateLinearFunctionFactor(int degree) {
        return Math.tan(Math.toRadians(degree));
    }

}