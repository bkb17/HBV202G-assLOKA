package hi.flappybird.vinnsla;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class BirdMovement {

    private final ImageView bird;
    private final int jumpHeight;
    private final double gravity;
    private double velocity = 0;
    private final CollisionHandler collisionHandler = new CollisionHandler();

    /**
     * @param bird
     * @param strategy
     */
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

    /**
     * @param obstacles
     * @param plane
     * @return
     */
    public boolean isBirdDead(List<Rectangle> obstacles, AnchorPane plane) {
        return hasCollided(obstacles) || isOutOfBounds(plane);
    }

    public ImageView getBird() {
        return bird;
    }

    /**
     * @param deltaY
     */
    public void moveBirdY(int deltaY) {
        bird.setLayoutY(bird.getLayoutY() + deltaY);
    }

    /**
     * @param obstacles
     * @return
     */
    private boolean hasCollided(List<Rectangle> obstacles) {
        return collisionHandler.collisionDetection(new ArrayList<>(obstacles), bird);
    }

    /**
     * @param plane
     * @return
     */
    private boolean isOutOfBounds(AnchorPane plane) {
        return bird.getLayoutY() >= plane.getHeight();
    }
}



