package com.codecool.dungeoncrawl.logic.ui;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.engine.Position;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Tiles {
    public static int TILE_SIZE = 32;
    private static final Image tileSet = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);

    public static class Tile {
        public final int x, y, w, h;

        Tile(TileId tileId) {
            x = tileId.x() * (TILE_SIZE + 2);
            y = tileId.y() * (TILE_SIZE + 2);
            w = TILE_SIZE;
            h = TILE_SIZE;
        }
    }

    public static void drawTile(GraphicsContext context, TileId tileId, int x, int y) {
        Tile tile = new Tile(tileId);
        context.drawImage(tileSet, tile.x, tile.y, tile.w, tile.h,
                x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
    public static void drawTile(GraphicsContext context, TileId tileId, int x, int y, int tileSize) {
        Tile tile = new Tile(tileId);
        context.drawImage(tileSet, tile.x, tile.y, tile.w, tile.h,
                x * tileSize, y * tileSize, tileSize, tileSize);
    }

    public static void drawHiddenTile(GraphicsContext context, int x, int y) {
        context.setFill(Color.BLACK);
        context.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public static boolean isVisible(Cell cell, GameMap map) {
        Position playerPosition = map.getPlayer().getPosition();
        Position cellPosition = cell.getPosition();

        int distance = Math.abs(playerPosition.x() - cellPosition.x()) + Math.abs(playerPosition.y() - cellPosition.y());

        int fieldOfView = map.getPlayer().getFieldOfView(map);
        boolean inFieldOfView = distance <= fieldOfView;

        return cell.isVisible(map) && inFieldOfView;
    }
}
