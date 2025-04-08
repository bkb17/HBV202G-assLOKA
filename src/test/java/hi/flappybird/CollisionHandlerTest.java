package hi.flappybird;

import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CollisionHandlerTest {

    private CollisionHandler collisionHandler;
    private Rectangle bird;

    @BeforeEach
    void setUp() {

        collisionHandler = new CollisionHandler();
        bird = new Rectangle(20, 20);
        bird.setLayoutX(50);
        bird.setLayoutY(50);
    }

    @Test
    void testNoCollision() {
        ArrayList<Rectangle> obstacles = new ArrayList<>();
        Rectangle obstacle = new Rectangle(20, 20);
        obstacle.setLayoutX(200);
        obstacle.setLayoutY(200); // Far from the bird
        obstacles.add(obstacle);

        boolean result = collisionHandler.collisionDetection(obstacles, bird);
        assertFalse(result, "Should return false when there's no collision.");
    }

    @Test
    void testCollisionDetected() {
        ArrayList<Rectangle> obstacles = new ArrayList<>();
        Rectangle obstacle = new Rectangle(20, 20);
        obstacle.setLayoutX(50);
        obstacle.setLayoutY(50); // Same position as bird
        obstacles.add(obstacle);

        boolean result = collisionHandler.collisionDetection(obstacles, bird);
        assertTrue(result, "Should return true when there's a collision.");
    }
}
