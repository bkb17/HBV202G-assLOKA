package hi.flappybird.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller fyrir main menu
 */
public class MainMenuController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * þegar ýtt er á "PLAY" þá er farið í leikinn
     * @param event
     * @throws IOException
     */
    public void switchToGame(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/hi/flappybird/game-scene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void switchToBirdSelection(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/hi/flappybird/bird-selection.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}