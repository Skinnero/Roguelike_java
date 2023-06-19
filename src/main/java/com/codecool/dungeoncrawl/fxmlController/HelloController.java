package com.codecool.dungeoncrawl.fxmlController;

import com.codecool.dungeoncrawl.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Stage stage;


    @FXML
    protected void startNewGame(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/game-ui.fxml"));
        stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        scene.getRoot().requestFocus();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void exitGame() {
        System.exit(0);
    }
}