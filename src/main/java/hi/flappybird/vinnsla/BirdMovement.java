package hi.flappybird.vinnsla;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class BirdMovement {

    private Rectangle bird;
    private int jumpHeight;
    CollisionHandler collisionHandler = new CollisionHandler();

    /**
     * @param bird
     * @param jumpHeight
     */
    public BirdMovement(Rectangle bird, int jumpHeight) {
        this.bird = bird;
        this.jumpHeight = jumpHeight;
    }

    public void fly() {
        double movement = -jumpHeight;
        double currentY = bird.getLayoutY();

        if (currentY <= jumpHeight) {
            movement = -currentY;
        }

        moveBirdY(movement);
    }

    /**
     * @param positionChange
     */
    public void moveBirdY(double positionChange) {
        bird.setLayoutY(bird.getLayoutY() + positionChange);
    }

    /**
     * @param obstacles
     * @param plane
     * @return
     */
    public boolean isBirdDead(ArrayList<Rectangle> obstacles, AnchorPane plane){
        double birdY = bird.getLayoutY() + bird.getY();

        if(collisionHandler.collisionDetection(obstacles, bird)){
            return  true;
        }

        return birdY >= plane.getHeight();
    }
}
