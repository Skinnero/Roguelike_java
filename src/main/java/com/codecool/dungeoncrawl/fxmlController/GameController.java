package com.codecool.dungeoncrawl.fxmlController;

import com.codecool.dungeoncrawl.logic.engine.*;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.Direction;
import com.codecool.dungeoncrawl.logic.fileloader.MapLoader;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameController {

    @FXML
    private Pane inventoryPane;
    @FXML
    private Pane statsPane;
    @FXML
    private Canvas canvas;
    private final int TILE_SIZE = 60;
    private final int MAX_ROW = 3;

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
        Position playerPosition = map.getPlayer().getPosition();

        // left boundary of view
        int startX = Math.max(0, playerPosition.x() - (int) (canvas.getWidth() / Tiles.TILE_SIZE / 2));
        // top boundary of view
        int startY = Math.max(0, playerPosition.y() - (int) (canvas.getHeight() / Tiles.TILE_SIZE / 2));
        // right boundary of view
        int endX = Math.min(map.getWidth(), startX + (int) (canvas.getWidth() / Tiles.TILE_SIZE));
        // bottom boundary of view
        int endY = Math.min(map.getHeight(), startY + (int) (canvas.getHeight() / Tiles.TILE_SIZE));

        createMap(Position.of(startX, startY), Position.of(endX, endY));
    }

    private void createMap(Position startingPosition, Position endingPosition) {
        GraphicsContext context = canvas.getGraphicsContext2D();

        for (int x = startingPosition.x(); x < endingPosition.x(); x++) {
            for (int y = startingPosition.y(); y < endingPosition.y(); y++) {
                Cell cell = map.getCell(Position.of(x, y));
                TileId tileId = cell.getVisibleObjectId();
                Tiles.drawTile(context, tileId, x - startingPosition.x(), y - startingPosition.y());
                if (!Tiles.isVisible(cell, map, map.getPlayer())) {
                    Tiles.drawHiddenTile(context, x - startingPosition.x(), y - startingPosition.y());
                }
            }
        }
    }

    private void showStatistics(Player player) {
        int COLUMN = 0;
        statsPane.setVisible(!statsPane.isVisible());
        Label attackLabel = (Label) statsPane.lookup("#attackValue");
        attackLabel.setText(Integer.toString(player.getAttack()));
        Label healthLabel = (Label) statsPane.lookup("#healthValue");
        healthLabel.setText(Integer.toString(player.getHealth()));
        Label defenseLabel = (Label) statsPane.lookup("#defenseValue");
        defenseLabel.setText(Integer.toString(player.getDefense()));

        Canvas stats = (Canvas) statsPane.lookup("#stats");
        GraphicsContext graphicsContext = stats.getGraphicsContext2D();

        List<TileId> statsTileIds = Arrays.stream(StatisticsTileId.values()).map(StatisticsTileId::getTileId).toList();

        for (int y = 0; y < MAX_ROW; y++) {
            Tiles.drawTile(graphicsContext, statsTileIds.get(y), COLUMN, y, TILE_SIZE);
        }

    }

    private void showInventory(Player player) {
        int MAX_COLUMN = 3;
        inventoryPane.setVisible(!inventoryPane.isVisible());
        Canvas inventory = (Canvas) inventoryPane.lookup("#inventory");
        GraphicsContext graphicsContext = inventory.getGraphicsContext2D();
        int inventoryIndex = 0;

        for (int x = 0; x < MAX_ROW; x++) {
            for (int y = 0; y < MAX_COLUMN; y++) {
                if (player.getInventory().size() == inventoryIndex) {
                    return;
                }
                Tiles.drawTile(graphicsContext, player.getInventory().get(inventoryIndex).getTileId(), x, y, TILE_SIZE);
                inventoryIndex++;
            }
        }
    }
}
