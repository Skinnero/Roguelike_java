package com.codecool.dungeoncrawl.fxmlController;

import com.codecool.dungeoncrawl.logic.engine.*;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.Direction;
import com.codecool.dungeoncrawl.logic.fileloader.MapLoader;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class GameController {

    @FXML
    private Pane inventoryPane;
    @FXML
    private Pane statsPane;
    @FXML
    private Canvas canvas;

    private final GameMap map = MapLoader.loadMap("/map.txt");

    @FXML
    protected void keyEventController(KeyEvent event) {
        switch (event.getCode()) {
            case UP, W -> map.movePlayer(Direction.UP);
            case DOWN, S -> map.movePlayer(Direction.DOWN);
            case LEFT, A -> map.movePlayer(Direction.LEFT);
            case RIGHT, D -> map.movePlayer(Direction.RIGHT);
            case G -> map.getPlayer().pickUpItem(map); // Grabs item from floor
            case F -> map.getPlayer().interactWithObject(map); // Interact with game surroundings
//            case E -> map = player.moveToNextLevel(++mapLevel, map);
            case ESCAPE -> System.exit(0);
            case I -> {
                showInventory(map.getPlayer());
                return;
            }
            case C -> {
                showStatistics(map.getPlayer());
                return;
            }
        }
        if (event.isControlDown() && event.getCode() == KeyCode.S) {
        }
        refresh();
    }



    private void refresh() {
        GraphicsContext context = canvas.getGraphicsContext2D();

        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int playerX = map.getPlayer().getPosition().x();
        int playerY = map.getPlayer().getPosition().y();

        // left boundary of view
        int startX = Math.max(0, playerX - (int) (canvas.getWidth() / Tiles.TILE_SIZE / 2));
        // top boundary of view
        int startY = Math.max(0, playerY - (int) (canvas.getHeight() / Tiles.TILE_SIZE / 2));
        // right boundary of view
        int endX = Math.min(map.getWidth(), startX + (int) (canvas.getWidth() / Tiles.TILE_SIZE));
        // bottom boundary of view
        int endY = Math.min(map.getHeight(), startY + (int) (canvas.getHeight() / Tiles.TILE_SIZE));

        createMap(startX, startY, endX, endY);
    }

    private void createMap(int startX, int startY, int endX, int endY) {
        GraphicsContext context = canvas.getGraphicsContext2D();

        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                Cell cell = map.getCell(Position.of(x, y));
                TileId tileId = cell.getVisibleObjectId();
                Tiles.drawTile(context, tileId, x - startX, y - startY);
                if (!Tiles.isVisible(cell, map, map.getPlayer())) {
                    Tiles.drawHiddenTile(context, x - startX, y - startY);
                }
            }
        }
    }

    private void showStatistics(Player player) {
        statsPane.setVisible(!statsPane.isVisible());
        Label attackLabel = (Label) statsPane.lookup("#attackValue");
        attackLabel.setText(Integer.toString(player.getAttack()));
        Label healthLabel = (Label) statsPane.lookup("#healthValue");
        healthLabel.setText(Integer.toString(player.getHealth()));
        Label defenseLabel = (Label) statsPane.lookup("#defenseValue");
        defenseLabel.setText(Integer.toString(player.getDefense()));
    }
    private void showInventory(Player player) {
//        inventoryPane.setVisible(!inventoryPane.isVisible());
//        Canvas inventory = (Canvas) inventoryPane.lookup("#inventory");
//        GraphicsContext graphicsContext = inventory.getGraphicsContext2D();
//        for (int x = 0; x < 3; x++) {
//            for (int y = 0; y < 3; y++) {
//                if (player.getInventory().getInventory().size() >= x + y) {
//                    Tiles.drawHiddenTile(graphicsContext, x, y);
//                }
//                Tiles.drawTile(graphicsContext, player.getInventory().getInventory().get(x + y).getTileId(), x, y);
//            }
//        }
    }
}
