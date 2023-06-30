package com.codecool.dungeoncrawl.fxmlController;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.dao.PlayerDao;
import com.codecool.dungeoncrawl.dao.PlayerDaoJdbc;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.utils.ProfessionTileId;
import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.ui.Tiles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WelcomingStageController {

    @FXML
    protected void startNewGame(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/character-selection.fxml"));
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        setImages(scene);
        scene.getRoot().requestFocus();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void loadGame(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/loading-view.fxml"));
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);

        setStageToWindow(fxmlLoader.getController(), scene);

        scene.getRoot().requestFocus();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    protected void exitGame() {
        System.exit(0);
    }

    private void setStageToWindow(LoaderController loaderController, Scene scene) {
        VBox vBox = (VBox) scene.lookup("#loadingVbox");
        List<String> players = Arrays.asList("asd", "qwe");
        players.forEach(player -> {
            Text text = new Text("(" + players.indexOf(player) + ") " + player);
            text.setFont(Font.font("Arial", FontWeight.BOLD, 50));
            text.setStyle("-fx-fill: black;" +
                    "-fx-border-width: 2px;" +
                    "-fx-border-style: solid;" +
                    "-fx-background-color: black");
//            text.setOnMouseClicked();
            text.translateXProperty().bind(scene.widthProperty().divide(4));
            vBox.getChildren().add(text);
        });
    }


    private void setImages(Scene scene) {
        Canvas canvas = (Canvas) scene.lookup("#hunter");
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        Tiles.drawTile(graphicsContext, ProfessionTileId.HUNTER.getTileId(), Position.of(0, 0), 96);

        canvas = (Canvas) scene.lookup("#warrior");
        graphicsContext = canvas.getGraphicsContext2D();
        Tiles.drawTile(graphicsContext, ProfessionTileId.WARRIOR.getTileId(), Position.of(0, 0), 96);

        canvas = (Canvas) scene.lookup("#mage");
        graphicsContext = canvas.getGraphicsContext2D();
        Tiles.drawTile(graphicsContext, ProfessionTileId.MAGE.getTileId(), Position.of(0, 0), 96);
    }
}