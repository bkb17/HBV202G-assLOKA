package hi.flappybird.vinnsla;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class CollisionHandler {

    public boolean collisionDetection(ArrayList<Rectangle> obstacles, Node bird) {
        for (Rectangle rectangle : obstacles) {
            if (rectangle.getBoundsInParent().intersects(bird.getBoundsInParent())) {
                return true;
            }
        }
        return false;
    }
}


