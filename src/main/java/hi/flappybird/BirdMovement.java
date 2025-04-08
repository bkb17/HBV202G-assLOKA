package hi.flappybird;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * klasi sem lætur fuglinn fljúga
 */
public class BirdMovement {

    private Rectangle bird;
    private int jumpHeight;
    CollisionHandler collisionHandler = new CollisionHandler();

    /**
     * smiðurinn
     * @param bird
     * @param jumpHeight
     */
    public BirdMovement(Rectangle bird, int jumpHeight) {
        this.bird = bird;
        this.jumpHeight = jumpHeight;
    }

    /**
     * aðferð sem lætur fuglinn fljúga
     */
    public void fly() {
        double movement = -jumpHeight;
        double currentY = bird.getLayoutY();

        if (currentY <= jumpHeight) {
            movement = -currentY;
        }

        moveBirdY(movement);
    }

    /**
     * breytir position
     * @param positionChange
     */
    public void moveBirdY(double positionChange) {
        bird.setLayoutY(bird.getLayoutY() + positionChange);
    }

    /**
     * athugar hvort fuglinn sé dauður
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
