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

    @FXML
    private void selectPinkBird(ActionEvent event) throws Exception {
        SelectedBird.setSelectedBird("pink");
        goToMainMenu(event);
    }

    @FXML
    private void selectBlueBird(ActionEvent event) throws Exception {
        SelectedBird.setSelectedBird("blue");
        goToMainMenu(event);
    }

    private void goToMainMenu(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/hi/flappybird/main-menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }