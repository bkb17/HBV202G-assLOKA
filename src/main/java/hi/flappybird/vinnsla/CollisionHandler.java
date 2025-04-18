package hi.flappybird.vinnsla;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class CollisionHandler {

    /**
     * @param obstacles
     * @param bird
     * @return
     */
    public boolean collisionDetection(ArrayList<Rectangle> obstacles, Node bird) {
        for (Rectangle rectangle : obstacles) {
            if (rectangle.getBoundsInParent().intersects(bird.getBoundsInParent())) {
                return true;
            }
        }
        return false;
    }
}


