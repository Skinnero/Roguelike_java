package com.codecool.dungeoncrawl.logic.engine;

import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static final Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    public static final Map<String, Tile> tileMap = new HashMap<>();

    public static class Tile {
        public final int x, y, w, h;

        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("food", new Tile(15, 29));
        tileMap.put("key", new Tile(16, 23));
        tileMap.put("sword", new Tile(2, 28));
        tileMap.put("armor", new Tile(0, 23));
        tileMap.put("closed_gate", new Tile(3, 4));
        tileMap.put("open_gate", new Tile(4, 4));
        tileMap.put("ogre", new Tile(30, 6));
        tileMap.put("mage", new Tile(24, 6));
        tileMap.put("closed_chest", new Tile(8, 6));
        tileMap.put("open_chest", new Tile(9, 6));
        tileMap.put("torch", new Tile(4, 15));
        tileMap.put("water", new Tile(8, 5));
        tileMap.put("boat", new Tile(11, 19));
        tileMap.put("letter_d", new Tile(22, 30));
        tileMap.put("letter_o", new Tile(20, 31));
        tileMap.put("letter_n", new Tile(19, 31));
        tileMap.put("letter_t", new Tile(25, 31));
        tileMap.put("letter_g", new Tile(25, 30));
        tileMap.put("letter_h", new Tile(26, 30));
        tileMap.put("letter_e", new Tile(23, 30));
        tileMap.put("letter_r", new Tile(23, 31));
        tileMap.put("grass", new Tile(5, 0));
    }

    public static void drawTile(GraphicsContext context, Cell d, int x, int y) {
        Tile tile = tileMap.get(d.getType().getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }

    public static void drawHiddenTile(GraphicsContext context, int x, int y) {
        context.setFill(Color.BLACK);
        context.fillRect(x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }

    public static boolean isVisible(Cell cell, GameMap map, Player player) {
        int playerX = map.getPlayer().getX();
        int playerY = map.getPlayer().getY();
        int cellX = cell.getX();
        int cellY = cell.getY();

        int distance = Math.abs(playerX - cellX) + Math.abs(playerY - cellY);

        int fieldOfView = player.getFieldOfView(player, map);
        boolean inFieldOfView = distance <= fieldOfView;

        return cell.isVisible(player) && inFieldOfView;
    }
}
