package hi.flappybird.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class MainMenuController {

    /**
     * þegar ýtt er á "PLAY" þá er farið í leikinn
     */
    public void switchToGame(ActionEvent event) throws IOException {
        switchScene(event, "/hi/flappybird/game-scene.fxml");
    }

    /**
     * þegar ýtt er á "Birds" þá er hægt að velja fugl
     */
    @FXML
    private void switchToBirdSelection(ActionEvent event) throws IOException {
        switchScene(event, "/hi/flappybird/bird-selection.fxml");
    }

    private void switchScene(ActionEvent event, String fxmlPath) throws IOException {
        var url = getClass().getResource(fxmlPath);
        if (url == null) {
            throw new RuntimeException("Could not find FXML: " + fxmlPath);
        }

        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
