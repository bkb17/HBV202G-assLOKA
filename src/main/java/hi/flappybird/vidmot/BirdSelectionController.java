package hi.flappybird.vidmot;

import hi.flappybird.vinnsla.SelectedBird;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class BirdSelectionController {

    /**
     * @param event
     * @throws Exception
     */
    @FXML
    private void selectPinkBird(ActionEvent event) throws Exception {
        handleBirdSelection("pink", event);
    }

    /**
     * @param event
     * @throws Exception
     */
    @FXML
    private void selectBlueBird(ActionEvent event) throws Exception {
        handleBirdSelection("blue", event);
    }

    /**
     * @param birdColor
     * @param event
     * @throws Exception
     */
    private void handleBirdSelection(String birdColor, ActionEvent event) throws Exception {
        SelectedBird.setSelectedBird(birdColor);
        switchToScene("/hi/flappybird/main-menu.fxml", event);
    }

    /**
     * @param fxmlPath
     * @param event
     * @throws Exception
     */
    private void switchToScene(String fxmlPath, ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
