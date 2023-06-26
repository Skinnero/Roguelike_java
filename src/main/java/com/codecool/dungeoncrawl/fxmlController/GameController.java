package com.codecool.dungeoncrawl.fxmlController;

import com.codecool.dungeoncrawl.logic.engine.*;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.utils.Direction;
import com.codecool.dungeoncrawl.logic.fileloader.MapLoader;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lombok.Getter;
import lombok.Setter;

public class GameController {
    @Getter
    private GameMap gameMap = MapLoader.loadMap("/tutorial.txt");
    @Setter
    @Getter
    private GameWindow gameWindow;
    @Setter
    @Getter
    private GUIWindow guiWindow;

    @FXML
    protected void handleKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.S) {
            return;
        }
        handleNonBlockingEvents(keyEvent);
        handleBlockingEvents(keyEvent);
    }

    private void handleNonBlockingEvents(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP, W -> gameMap.movePlayer(Direction.UP);
            case DOWN, S -> gameMap.movePlayer(Direction.DOWN);
            case LEFT, A -> gameMap.movePlayer(Direction.LEFT);
            case RIGHT, D -> gameMap.movePlayer(Direction.RIGHT);
            case G -> pickUpItem(); // Grabs item from floor
            case F -> interactWithEnvironment(); // Interact with game surroundings
            case E -> gameMap = gameMap.getAnotherMap();
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
        gameMap.moveActorEnemy();
        gameWindow.refresh(gameMap);
        guiWindow.refreshInterface();
    }

    private void handleBlockingEvents(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case I -> guiWindow.showInventory();
            case Q -> guiWindow.showMessageLogStash();
            case C -> guiWindow.showStatistics();
        }
    }

    private void pickUpItem() {
        gameMap.getPlayer().pickUpItem(gameMap);
    }

    private void interactWithEnvironment() {
        gameMap.getPlayer().interactWithObject(gameMap);
    }

    private void useItem(int inventorySlot) {
        gameMap.getPlayer().useItem(inventorySlot);
    }
}
