package com.codecool.dungeoncrawl.fxmlController;

import com.codecool.dungeoncrawl.logic.engine.*;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.Direction;
import com.codecool.dungeoncrawl.logic.fileloader.MapLoader;
import com.codecool.dungeoncrawl.logic.ui.GameMessage;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class GameController {
    private final int TILE_SIZE = 60;
    private final int MAX_ROW = 3;
    @FXML
    private Pane inventoryPane;
    @FXML
    private Pane statsPane;
    @FXML
    private Canvas mainCanvas;
    @FXML
    private ScrollPane messageLogStashScrollPane;
    @FXML
    private VBox messageLogStash;
    @FXML
    private VBox messageLog;
    private GameMap map = MapLoader.loadMap("/tutorial.txt");
    private GameMessage gameMessage = GameMessage.getInstance();

    @FXML
    protected void handleKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.S) {
            return;
        }
        playerMovementController(keyEvent);
        playerInterfaceController(keyEvent);
    }

    private void playerMovementController(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP, W -> map.movePlayer(Direction.UP);
            case DOWN, S -> map.movePlayer(Direction.DOWN);
            case LEFT, A -> map.movePlayer(Direction.LEFT);
            case RIGHT, D -> map.movePlayer(Direction.RIGHT);
            case G -> pickUpItem(); // Grabs item from floor
            case F -> interactWithEnvironment(); // Interact with game surroundings
            case E -> map = map.getAnotherMap();
            case ESCAPE -> System.exit(0);
            case DIGIT1 -> useItem(0);
            case DIGIT2 -> useItem(1);
            case DIGIT3 -> useItem(2);
            case DIGIT4 -> useItem(3);
            case DIGIT5 -> useItem(4);
            case DIGIT6 -> useItem(5);
            case DIGIT7 -> useItem(6);
            case DIGIT8 -> useItem(7);
            case DIGIT9 -> useItem(8);
            default -> {
                return;
            }
        }
        map.moveActorEnemy();
        refresh();
    }

    private void playerInterfaceController(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case I -> showInventory();
            case Q -> showMessageLog();
            case C -> showStatistics();
        }
    }


    private void refresh() {
        GraphicsContext context = mainCanvas.getGraphicsContext2D();
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());
        Position playerPosition = map.getPlayer().getPosition();

        // left boundary of view
        int startX = Math.max(0, playerPosition.x() - (int) (mainCanvas.getWidth() / Tiles.TILE_SIZE / 2));
        // top boundary of view
        int startY = Math.max(0, playerPosition.y() - (int) (mainCanvas.getHeight() / Tiles.TILE_SIZE / 2));
        // right boundary of view
        int endX = Math.min(map.getWidth(), startX + (int) (mainCanvas.getWidth() / Tiles.TILE_SIZE));
        // bottom boundary of view
        int endY = Math.min(map.getHeight(), startY + (int) (mainCanvas.getHeight() / Tiles.TILE_SIZE));
        createMap(Position.of(startX, startY), Position.of(endX, endY));
        refreshInterface();
    }

    private void createMap(Position startingPosition, Position endingPosition) {
        GraphicsContext context = mainCanvas.getGraphicsContext2D();

        for (int x = startingPosition.x(); x < endingPosition.x(); x++) {
            for (int y = startingPosition.y(); y < endingPosition.y(); y++) {
                Cell cell = map.getCell(Position.of(x, y));
                TileId tileId = cell.getVisibleObjectId();
                Tiles.drawTile(context, tileId, x - startingPosition.x(), y - startingPosition.y());
//                if (!Tiles.isVisible(cell, map)) {
//                    Tiles.drawHiddenTile(context, x - startingPosition.x(), y - startingPosition.y());
//                }
            }
        }
    }

    private void showStatistics() {
        statsPane.setVisible(!statsPane.isVisible());
        refreshInterface();
    }

    private void showMessageLog() {
        messageLogStashScrollPane.setVisible(!messageLogStashScrollPane.isVisible());
        refreshInterface();
    }


    private void pickUpItem() {
        map.getPlayer().pickUpItem(map);
        refreshInterface();
    }

    private void interactWithEnvironment() {
        map.getPlayer().interactWithObject(map);
        refreshInterface();
    }

    private void showInventory() {
        inventoryPane.setVisible(!inventoryPane.isVisible());
        refreshInterface();
    }

    private void useItem(int inventorySlot) {
        map.getPlayer().useItem(inventorySlot);
        refreshInterface();
    }

    private void refreshMessageLogStash() {
        messageLogStash.getChildren().clear();
        for (int i = gameMessage.getLogStash().size() - 1; i >= 0; i--) {
            addMessageToVBox(messageLogStash, i);
        }
    }

    private void refreshMessageLog() {
        int SIZE_OF_MESSAGES_IN_LOG = 5;
        messageLog.getChildren().clear();
        for (int i = gameMessage.getLogStash().size() - 1; i >= 0; i--) {
            if (messageLog.getChildren().size() <= SIZE_OF_MESSAGES_IN_LOG) {
                addMessageToVBox(messageLog, i);
            }
        }
    }

    private void addMessageToVBox(VBox vBox, int messageIndex) {
        Text text = new Text(gameMessage.getLogStash().get(messageIndex));
        text.setFont(Font.font("System", 20));
        text.setFill(Color.rgb(255, 255, 255));
        vBox.getChildren().add(text);
    }

    private void refreshInventory() {
        int MAX_COLUMN = 3;
        int inventoryIndex = 0;
        Canvas inventory = (Canvas) inventoryPane.lookup("#inventory");
        GraphicsContext graphicsContext = inventory.getGraphicsContext2D();
        for (int x = 0; x < MAX_ROW; x++) {
            for (int y = 0; y < MAX_COLUMN; y++) {
                if (map.getPlayer().getInventory().size() == inventoryIndex) {
                    Tiles.drawTile(graphicsContext, TileType.EMPTY.getTileId(), x, y, TILE_SIZE);
                    return;
                }
                Tiles.drawTile(graphicsContext, map.getPlayer().getInventory().get(inventoryIndex).getTileId(), x, y, TILE_SIZE);
                inventoryIndex++;
            }
        }
    }

    private void refreshStatistics() {
        int COLUMN = 0;
        Label attackLabel = (Label) statsPane.lookup("#attackValue");
        attackLabel.setText(Integer.toString(map.getPlayer().getAttack()));
        Label healthLabel = (Label) statsPane.lookup("#healthValue");
        healthLabel.setText(Integer.toString(map.getPlayer().getHealth()));
        Label defenseLabel = (Label) statsPane.lookup("#defenseValue");
        defenseLabel.setText(Integer.toString(map.getPlayer().getDefense()));

        Canvas stats = (Canvas) statsPane.lookup("#stats");
        GraphicsContext graphicsContext = stats.getGraphicsContext2D();

        List<TileId> statsTileIds = Arrays.stream(StatisticsTileId.values()).map(StatisticsTileId::getTileId).toList();

        for (int y = 0; y < MAX_ROW; y++) {
            Tiles.drawTile(graphicsContext, statsTileIds.get(y), COLUMN, y, TILE_SIZE);
        }
    }

    private void refreshInterface() {
        refreshInventory();
        refreshStatistics();
        refreshMessageLog();
        refreshMessageLogStash();
    }

}
