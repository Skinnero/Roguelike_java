package com.codecool.dungeoncrawl.fxmlController;

import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.engine.Movement;
import com.codecool.dungeoncrawl.logic.engine.Position;
import com.codecool.dungeoncrawl.logic.ui.TileId;
import com.codecool.dungeoncrawl.logic.ui.Tiles;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.Setter;

public class GameWindow {
    private final Canvas mainCanvas;

    public GameWindow(Scene mainScene) {
        this.mainCanvas = (Canvas) mainScene.lookup("#mainCanvas");
    }

    protected void refresh(GameMap gameMap) {
        GraphicsContext context = mainCanvas.getGraphicsContext2D();
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());
        Position playerPosition = gameMap.getPlayer().getPosition();

        // left boundary of view
        int startX = Math.max(0, playerPosition.x() - (int) (mainCanvas.getWidth() / Tiles.TILE_SIZE / 2));
        // top boundary of view
        int startY = Math.max(0, playerPosition.y() - (int) (mainCanvas.getHeight() / Tiles.TILE_SIZE / 2));
        // right boundary of view
        int endX = Math.min(gameMap.getWidth(), startX + (int) (mainCanvas.getWidth() / Tiles.TILE_SIZE));
        // bottom boundary of view
        int endY = Math.min(gameMap.getHeight(), startY + (int) (mainCanvas.getHeight() / Tiles.TILE_SIZE));
        createMap(Movement.of(Position.of(startX, startY), Position.of(endX, endY)), gameMap);
    }

    private void createMap(Movement movement, GameMap gameMap) {
        GraphicsContext context = mainCanvas.getGraphicsContext2D();
        for (int x = movement.currentPosition().x(); x < movement.newPosition().x(); x++) {
            for (int y = movement.currentPosition().y(); y < movement.newPosition().y(); y++) {
                Cell cell = gameMap.getCell(Position.of(x, y));
                TileId tileId = cell.getVisibleObjectId();
                Tiles.drawTile(context, tileId, x - movement.currentPosition().x(), y - movement.currentPosition().y());
                if (!Tiles.isVisible(cell, gameMap)) {
                    Tiles.drawHiddenTile(context, x - movement.currentPosition().x(), y - movement.currentPosition().y());
                }
            }
        }
    }
}
