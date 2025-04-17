package hi.flappybird.vidmot;
import hi.flappybird.vinnsla.*;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.TextField;
import javafx.application.Platform;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import hi.flappybird.vinnsla.FastSpeedStrategy;
import hi.flappybird.vinnsla.NormalSpeedStrategy;



public class GameSceneController implements Initializable {

    AnimationTimer gameLoop;

    @FXML
    private AnchorPane plane;

    @FXML
    private Label gameOverLabel;

    @FXML
    private Bird activeBird;

    @FXML
    private Button restartButton;

    @FXML
    private Button backToMenuButton;


    @FXML
    private TextField score;

    private double accelerationTime = 0;
    private int gameTime = 0;
    private int scoreCounter = 0;
    ImageView bird;

    private BirdMovement birdComponent;
    private ObstaclesHandler obstaclesHandler;

    ArrayList<Rectangle> obstacles = new ArrayList<>();

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String selected = SelectedBird.getSelectedBird();

        if ("blue".equals(selected)) {
            activeBird = new BlueBird(50, plane.getHeight() / 2);
        } else {
            activeBird = new PinkBird(50, plane.getHeight() / 2);
        }

        birdComponent = activeBird.getMovement();
        plane.getChildren().add(activeBird.getShape());

        setupGame();

    }
    private void setupGame() {
        double planeHeight = 600;
        double planeWidth = 400;
        obstaclesHandler = new ObstaclesHandler(plane, planeHeight, planeWidth);

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
            }
        };

        load();
        gameLoop.start();

        plane.setFocusTraversable(true);
        Platform.runLater(() -> plane.requestFocus());
    }
    /**
     * lætur fuglinn fljúga þegar ýtt er á space
     * @param event
     */
    @FXML
    void pressed(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            birdComponent.fly();
        }
    }

    private void update() {
        gameTime++;

        birdComponent.applyGravity(); // use gravity and velocity

        if (pointChecker(obstacles, activeBird.getShape())) {
            scoreCounter++;
            score.setText(String.valueOf(scoreCounter));
        }

        obstaclesHandler.moveObstacles(obstacles);
        if (gameTime % 500 == 0) {
            obstacles.addAll(obstaclesHandler.createObstacles());
        }

        if (birdComponent.isBirdDead(obstacles, plane)) {
            gameOver();
        }
    }


    private void load(){

        obstacles.addAll(obstaclesHandler.createObstacles());
    }

    private void resetGame(){
        activeBird.getShape().setY(0);
        plane.getChildren().removeAll(obstacles);
        obstacles.clear();
        gameTime = 0;
        accelerationTime = 0;
        scoreCounter = 0;
        score.setText(String.valueOf(scoreCounter));

        load();

    }
    private void gameOver() {
        gameLoop.stop(); //pása leikinn
        gameOverLabel.setVisible(true);
        restartButton.setVisible(true);
        backToMenuButton.setVisible(true);
    }
    @FXML
    private void restartGame() {
        resetGame();
        gameOverLabel.setVisible(false);
        restartButton.setVisible(false);
        backToMenuButton.setVisible(false);
        gameLoop.start();

        Platform.runLater(() -> plane.requestFocus());

    }
    @FXML
    private void backToMenu() {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/hi/flappybird/main-menu.fxml"));
            javafx.scene.Parent root = loader.load();
            javafx.stage.Stage stage = (javafx.stage.Stage) plane.getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean pointChecker(ArrayList<Rectangle> obstacles, ImageView bird) {
        int birdX = (int) (bird.getLayoutX() + bird.getX());

        for (Rectangle obstacle : obstacles) {

            if (!Boolean.TRUE.equals(obstacle.getProperties().get("scoreZone"))) continue;

            int pipeRightEdge = (int) (obstacle.getLayoutX() + obstacle.getX() + obstacle.getWidth());


            if (birdX > pipeRightEdge &&
                    !obstacle.getProperties().containsKey("scored")) {

                obstacle.getProperties().put("scored", true);
                return true;
            }
        }

        return false;
    }

}


