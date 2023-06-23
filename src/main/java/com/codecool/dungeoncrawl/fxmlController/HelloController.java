package com.codecool.dungeoncrawl.fxmlController;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.engine.Tiles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import java.io.IOException;
public class HelloController {

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
    protected void exitGame() {
        System.exit(0);
    }

    private void setImages(Scene scene) {
        Canvas canvas = (Canvas) scene.lookup("#hunter");
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        Tiles.drawTile(graphicsContext, ProfessionTileId.HUNTER.getTileId(), 0, 0, 96);

        canvas = (Canvas) scene.lookup("#warrior");
        graphicsContext = canvas.getGraphicsContext2D();
        Tiles.drawTile(graphicsContext, ProfessionTileId.WARRIOR.getTileId(), 0, 0, 96);

        canvas = (Canvas) scene.lookup("#mage");
        graphicsContext = canvas.getGraphicsContext2D();
        Tiles.drawTile(graphicsContext, ProfessionTileId.MAGE.getTileId(), 0, 0, 96);

    }
}