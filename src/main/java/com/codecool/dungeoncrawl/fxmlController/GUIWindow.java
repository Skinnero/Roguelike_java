package com.codecool.dungeoncrawl.fxmlController;

import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.ui.GameMessage;
import com.codecool.dungeoncrawl.logic.ui.TileId;
import com.codecool.dungeoncrawl.logic.ui.TileType;
import com.codecool.dungeoncrawl.logic.ui.Tiles;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

public class GUIWindow {
    private final int TILE_SIZE = 60;
    private final int MAX_ROW = 3;
    private final GameMessage gameMessage = GameMessage.getInstance();
    private final Pane inventoryPane;
    private final Pane statsPane;
    private final ScrollPane messageLogStashScrollPane;
    private final VBox messageLogStash;
    private final VBox messageLog;
    @Setter
    private GameMap gameMap;


    public GUIWindow(Scene mainScene) {
        this.inventoryPane = (Pane) mainScene.lookup("#inventoryPane");
        this.statsPane = (Pane) mainScene.lookup("#statsPane");
        this.messageLogStashScrollPane = (ScrollPane) mainScene.lookup("#messageLogStashScrollPane");
        this.messageLogStash = (VBox) messageLogStashScrollPane.getContent().lookup("#messageLogStash");
        this.messageLog = (VBox) mainScene.lookup("#messageLog");
    }

    protected void showMessageLogStash() {
        messageLogStashScrollPane.setVisible(!messageLogStashScrollPane.isVisible());
        refreshInterface();
    }

    protected void showStatistics() {
        statsPane.setVisible(!statsPane.isVisible());
        refreshInterface();
    }

    protected void showInventory() {
        inventoryPane.setVisible(!inventoryPane.isVisible());
        refreshInterface();
    }

    private void refreshInventory() {
        int MAX_COLUMN = 3;
        int inventoryIndex = 0;
        Canvas inventory = (Canvas) inventoryPane.lookup("#inventory");
        GraphicsContext graphicsContext = inventory.getGraphicsContext2D();
        for (int x = 0; x < MAX_ROW; x++) {
            for (int y = 0; y < MAX_COLUMN; y++) {
                if (gameMap.getPlayer().getInventory().size() == inventoryIndex) {
                    Tiles.drawTile(graphicsContext, TileType.EMPTY.getTileId(), x, y, TILE_SIZE);
                    return;
                }
                Tiles.drawTile(graphicsContext, gameMap.getPlayer().getInventory().get(inventoryIndex).getTileId(), x, y, TILE_SIZE);
                inventoryIndex++;
            }
        }
    }

    private void refreshStatistics() {
        int COLUMN = 0;
        Label attackLabel = (Label) statsPane.lookup("#attackValue");
        attackLabel.setText(Integer.toString(gameMap.getPlayer().getAttack()));
        Label healthLabel = (Label) statsPane.lookup("#healthValue");
        healthLabel.setText(Integer.toString(gameMap.getPlayer().getHealth()));
        Label defenseLabel = (Label) statsPane.lookup("#defenseValue");
        defenseLabel.setText(Integer.toString(gameMap.getPlayer().getDefense()));

        Canvas stats = (Canvas) statsPane.lookup("#stats");
        GraphicsContext graphicsContext = stats.getGraphicsContext2D();

        List<TileId> statsTileIds = Arrays.stream(StatisticsTileId.values()).map(StatisticsTileId::getTileId).toList();

        for (int y = 0; y < MAX_ROW; y++) {
            Tiles.drawTile(graphicsContext, statsTileIds.get(y), COLUMN, y, TILE_SIZE);
        }
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

    protected void refreshInterface() {
        refreshMessageLog();
        refreshInventory();
        refreshStatistics();
        refreshMessageLogStash();
    }
}
