package com.codecool.dungeoncrawl.fxmlController;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.image.SampleModel;
import java.io.IOException;

public class PreStartController {

    @FXML
    protected void startGame(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/game-ui.fxml"));
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        TextField textField = (TextField) stage.getScene().lookup("#name");
        Player.getInstance().setName(textField.getText());
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        setNameOnDisplay(scene);
        scene.getRoot().requestFocus();
        stage.setScene(scene);
        stage.show();

        GameController controller = fxmlLoader.getController();
        controller.setGameWindow(stage);
        controller.refresh();
    }
    @FXML
    public void selectProfession(MouseEvent mouseEvent) {
        Node node = (Node) mouseEvent.getSource();
        switch (node.getId()) {
            case "mage" -> Player.getInstance().setProfession(ProfessionTileId.MAGE);
            case "warrior" -> Player.getInstance().setProfession(ProfessionTileId.WARRIOR);
            case "hunter" -> Player.getInstance().setProfession(ProfessionTileId.HUNTER);
        }
    }

    private void setNameOnDisplay(Scene scene) {
        Label label = (Label) scene.lookup("#name");
        label.setText(Player.getInstance().getName());
    }
}
