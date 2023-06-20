package com.codecool.dungeoncrawl.logic.engine;

import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

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

    public static void drawHiddenTile(GraphicsContext context, int x, int y) {
        context.setFill(Color.BLACK);
        context.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public static boolean isVisible(Cell cell, GameMap map, Player player) {
        int playerX = map.getPlayer().getPosition().x();
        int playerY = map.getPlayer().getPosition().y();
        int cellX = cell.getX();
        int cellY = cell.getY();

        int distance = Math.abs(playerX - cellX) + Math.abs(playerY - cellY);

        int fieldOfView = player.getFieldOfView(player, map);
        boolean inFieldOfView = distance <= fieldOfView;

        return cell.isVisible(player) && inFieldOfView;
    }
}
