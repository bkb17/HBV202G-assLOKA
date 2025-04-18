package hi.flappybird.vidmot;

import hi.flappybird.vinnsla.*;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class GameSceneController implements Initializable {

    private static final double PLANE_WIDTH = 400;
    private static final double PLANE_HEIGHT = 600;
    private static final int OBSTACLE_INTERVAL = 500;

    @FXML private AnchorPane plane;
    @FXML private Label gameOverLabel;
    @FXML private Button restartButton;
    @FXML private Button backToMenuButton;
    @FXML private TextField score;

    private AnimationTimer gameLoop;
    private Bird activeBird;
    private BirdMovement birdComponent;
    private ObstaclesHandler obstaclesHandler;

    private ArrayList<Rectangle> obstacles = new ArrayList<>();
    private int gameTime = 0;
    private int scoreCounter = 0;

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupBird();
        setupGame();
    }

    private void setupBird() {
        String selected = SelectedBird.getSelectedBird();
        activeBird = "blue".equals(selected)
                ? new BlueBird(50, PLANE_HEIGHT / 2)
                : new PinkBird(50, PLANE_HEIGHT / 2);

        birdComponent = activeBird.getMovement();
        plane.getChildren().add(activeBird.getShape());
    }

    private void setupGame() {
        obstaclesHandler = new ObstaclesHandler(plane, PLANE_HEIGHT, PLANE_WIDTH);
        loadInitialObstacles();

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateGame();
            }
        };

        gameLoop.start();
        plane.setFocusTraversable(true);
        Platform.runLater(() -> plane.requestFocus());
    }

    @FXML
    private void pressed(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            birdComponent.fly();
        }
    }

    private void updateGame() {
        gameTime++;
        birdComponent.applyGravity();

        if (checkScore(obstacles, activeBird.getShape())) {
            scoreCounter++;
            score.setText(String.valueOf(scoreCounter));
        }

        obstaclesHandler.moveObstacles(obstacles);
        if (gameTime % OBSTACLE_INTERVAL == 0) {
            obstacles.addAll(obstaclesHandler.createObstacles());
        }

        if (birdComponent.isBirdDead(obstacles, plane)) {
            showGameOverUI();
        }
    }

    private void loadInitialObstacles() {
        obstacles.addAll(obstaclesHandler.createObstacles());
    }

    private void resetGame() {
        plane.getChildren().remove(activeBird.getShape());

        setupBird();

        plane.getChildren().removeAll(obstacles);
        obstacles.clear();
        gameTime = 0;
        scoreCounter = 0;
        score.setText("0");
        loadInitialObstacles();
    }


    private void showGameOverUI() {
        gameLoop.stop();
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
            Parent root = FXMLLoader.load(getClass().getResource("/hi/flappybird/main-menu.fxml"));
            Stage stage = (Stage) plane.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param obstacles
     * @param bird
     * @return
     */
    private boolean checkScore(ArrayList<Rectangle> obstacles, ImageView bird) {
        int birdX = (int) (bird.getLayoutX() + bird.getX());

        for (Rectangle obstacle : obstacles) {
            if (!Boolean.TRUE.equals(obstacle.getProperties().get("scoreZone"))) continue;

            int pipeRightEdge = (int) (obstacle.getLayoutX() + obstacle.getX() + obstacle.getWidth());

            if (birdX > pipeRightEdge && !obstacle.getProperties().containsKey("scored")) {
                obstacle.getProperties().put("scored", true);
                return true;
            }
        }

        return false;
    }
}


