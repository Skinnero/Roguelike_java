package com.codecool.dungeoncrawl.fxmlController;

import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorutils.KeyArrowCoordinates;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.engine.Tiles;
import com.codecool.dungeoncrawl.logic.fileloader.MapLoader;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.List;

public class GameController {
    @FXML
    private List<ImageView> itemImages;
    @FXML
    private Pane inventoryPane;
    @FXML
    private Pane statsPane;
    @FXML
    private Canvas canvas;
    private final GameMap map = MapLoader.loadMap("/map.txt");

    @FXML
    protected void showInventory(KeyEvent event) {
        Player player = map.getPlayer();
        switch (event.getCode()) {
            case UP, W -> player.move(KeyArrowCoordinates.UP.dx, KeyArrowCoordinates.UP.dy);
            case DOWN, S -> player.move(KeyArrowCoordinates.DOWN.dx, KeyArrowCoordinates.DOWN.dy);
            case LEFT, A -> player.move(KeyArrowCoordinates.LEFT.dx, KeyArrowCoordinates.LEFT.dy);
            case RIGHT, D -> player.move(KeyArrowCoordinates.RIGHT.dx, KeyArrowCoordinates.RIGHT.dy);
            case G -> player.pickUpItem(); // Grab item from floor
            case F -> player.interactWithGameObject(); // Interact with game surrounding
//            case E -> map = player.moveToNextLevel(++mapLevel, map);
            case ESCAPE -> System.exit(0);
            case I -> {
                inventoryPane.setVisible(!inventoryPane.isVisible());
                return;
            }
            case C -> {
                statsPane.setVisible(!statsPane.isVisible());
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

        int playerX = map.getPlayer().getX();
        int playerY = map.getPlayer().getY();

        // left boundary of view
        int startX = Math.max(0, playerX - (int) (canvas.getWidth() / Tiles.TILE_WIDTH / 2));
        // top boundary of view
        int startY = Math.max(0, playerY - (int) (canvas.getHeight() / Tiles.TILE_WIDTH / 2));
        // right boundary of view
        int endX = Math.min(map.getWidth(), startX + (int) (canvas.getWidth() / Tiles.TILE_WIDTH));
        // bottom boundary of view
        int endY = Math.min(map.getHeight(), startY + (int) (canvas.getHeight() / Tiles.TILE_WIDTH));

        createMap(startX, startY, endX, endY);
    }

    private void createMap(int startX, int startY, int endX, int endY) {
        GraphicsContext context = canvas.getGraphicsContext2D();

        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                Cell cell = map.getCell(x, y);

                if (Tiles.isVisible(cell, map, map.getPlayer())) {
                    if (cell.getActor() != null) {
                        Tiles.drawTile(context, cell, x - startX, y - startY);
                    } else if (cell.getItem() != null) {
                        Tiles.drawTile(context, cell, x - startX, y - startY);
                    } else if (cell.getInteractiveObject() != null) {
                        Tiles.drawTile(context, cell, x - startX, y - startY);
                    } else {
                        Tiles.drawTile(context, cell, x - startX, y - startY);
                    }
                } else {
                    Tiles.drawHiddenTile(context, x - startX, y - startY);
                }
            }
        }
    }
}
