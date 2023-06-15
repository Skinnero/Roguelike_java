package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.actors.Mage;
import com.codecool.dungeoncrawl.logic.actors.Ogre;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actorutils.KeyArrowCoordinates;
import com.codecool.dungeoncrawl.logic.actorutils.Movement;
import com.codecool.dungeoncrawl.logic.engine.Cell;
import com.codecool.dungeoncrawl.logic.engine.GameMap;
import com.codecool.dungeoncrawl.logic.filemanagement.MapLoader;
import com.codecool.dungeoncrawl.logic.engine.Tiles;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.*;

public class Main extends Application {
    private int mapLevel = 1;
    GameMap map = MapLoader.loadMap("/map.txt");
    private final int BOARD_WIDTH = 30;
    private final int BOARD_HEIGHT = 20;
    Canvas canvas = new Canvas(
            BOARD_WIDTH * Tiles.TILE_WIDTH,
            BOARD_HEIGHT * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Text healthText = new Text();
    Text defenseText = new Text();
    Text attackText = new Text();
    Text inventoryText = new Text();
    Text equipmentText = new Text();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10, 10, 10, 10));
        ui.setStyle("-fx-background-color: #482c3c;" +
                "-fx-font-size: 20px;" +
                "-fx-font-family: Comic Sans MS;");
        // TODO: MOVE TO UI PACKAGE
        ui.add(new Text("Health: "), 0, 1);
        ui.add(healthText, 1, 1);
        ui.add(new Text("Defense: "), 0, 2);
        ui.add(defenseText, 1, 2);
        ui.add(new Text("Attack: "), 0, 3);
        ui.add(attackText, 1, 3);
        ui.add(new Text("Inventory: "), 0, 4);
        ui.add(inventoryText, 1, 4);
        ui.add(new Text("Equipped: "), 0, 5);
        ui.add(equipmentText, 1, 5);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setLeft(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(keyEvent -> {
            onKeyPressed(keyEvent, map.getPlayer());
            if (map.getPlayer().isDead()) {
                System.exit(0);
            }
            refresh();
        });
        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void getModalInventory(Stage primaryStage) {
        Stage modalStage = new Stage();
        modalStage.initOwner(primaryStage);
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initStyle(StageStyle.UTILITY);
        modalStage.setTitle("Modal window");

        VBox modalRoot = new VBox();
        Scene modalScene = new Scene(modalRoot, 200, 150);

        modalStage.setScene(modalScene);
        modalStage.showAndWait();

    }


    public void refresh() {
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

        healthText.setText(String.valueOf(map.getPlayer().getHealth()));
        attackText.setText(String.valueOf(map.getPlayer().getAttack()));
        defenseText.setText(String.valueOf(map.getPlayer().getDefense()));
        StringBuilder inventoryTextBuilder = new StringBuilder();
        map.getPlayer().getInventory().getInventory().forEach(item -> inventoryTextBuilder.append(item.getClass().getSimpleName()).append("\n"));
        inventoryText.setText(String.valueOf(inventoryTextBuilder));
        StringBuilder equipmentTextBuilder = new StringBuilder();
        map.getPlayer().getEquipment().forEach((itemClass, o) -> equipmentTextBuilder.append(o.getClass().getSimpleName()).append("\n"));
        equipmentText.setText(String.valueOf(equipmentTextBuilder));
    }

    public void onKeyPressed(KeyEvent keyEvent, Player player) {
        switch (keyEvent.getCode()) {
            case UP, W -> player.move(KeyArrowCoordinates.UP.dx, KeyArrowCoordinates.UP.dy);
            case DOWN, S -> player.move(KeyArrowCoordinates.DOWN.dx, KeyArrowCoordinates.DOWN.dy);
            case LEFT, A -> player.move(KeyArrowCoordinates.LEFT.dx, KeyArrowCoordinates.LEFT.dy);
            case RIGHT, D -> player.move(KeyArrowCoordinates.RIGHT.dx, KeyArrowCoordinates.RIGHT.dy);
            case G -> player.pickUpItem(); // Grab item from floor
            case F -> player.interactWithGameObject(); // Interact with game surrounding
            case E -> map = player.moveToNextLevel(mapLevel, map);
            case DIGIT1 -> player.useItem(0);
            case DIGIT2 -> player.useItem(1);
            case DIGIT3 -> player.useItem(2);
            case DIGIT4 -> player.useItem(3);
            case DIGIT5 -> player.useItem(4);
            case DIGIT6 -> player.useItem(5);
            case DIGIT7 -> player.useItem(6);
            case DIGIT8 -> player.useItem(7);
            case DIGIT9 -> player.useItem(8);
            case DIGIT0 -> player.useItem(9);
            case ESCAPE -> System.exit(0);
        }
        enemiesTurn();
    }

    private void createMap(int startX, int startY, int endX, int endY) {
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                Cell cell = map.getCell(x, y);

                if (Tiles.isVisible(cell, map, map.getPlayer())) {
                    if (cell.getActor() != null) {
                        Tiles.drawTile(context, cell.getActor(), x - startX, y - startY);
                    } else if (cell.getItem() != null) {
                        Tiles.drawTile(context, cell.getItem(), x - startX, y - startY);
                    } else if (cell.getGameObject() != null) {
                        Tiles.drawTile(context, cell.getGameObject(), x - startX, y - startY);
                    } else {
                        Tiles.drawTile(context, cell, x - startX, y - startY);
                    }
                } else {
                    Tiles.drawHiddenTile(context, x - startX, y - startY);
                }
            }
        }
    }

    private void enemiesTurn() {
        Movement movement = new Movement();
            for (Ogre ogre : MapLoader.ogres) {
                movement.goToPatrolPlace(map, ogre);
            }
//        try {
//            for (Mage mage : MapLoader.mages) {
//                movement.guard(map, mage);
//            }
//        } catch (Exception ignored) {
//        }
    }
}

