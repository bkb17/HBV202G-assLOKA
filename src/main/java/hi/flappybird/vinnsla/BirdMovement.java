package hi.flappybird.vinnsla;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class BirdMovement {

    private final ImageView bird;
    private final int jumpHeight;
    private final double gravity;
    private double velocity = 0;

    private final CollisionHandler collisionHandler = new CollisionHandler();

    public BirdMovement(ImageView bird, SpeedStrategy strategy) {
        this.bird = bird;
        this.jumpHeight = strategy.getJumpHeight();
        this.gravity = strategy.getGravity();
    }

    public void fly() {
        velocity = -jumpHeight;
    }

    public void applyGravity() {
        velocity += gravity;
        bird.setLayoutY(bird.getLayoutY() + velocity);
    }

    public boolean isBirdDead(ArrayList<javafx.scene.shape.Rectangle> obstacles, AnchorPane plane) {
        double birdY = bird.getLayoutY();
        return collisionHandler.collisionDetection(obstacles, bird) || birdY >= plane.getHeight();
    }

    public ImageView getBird() {
        return bird;
    }
}


