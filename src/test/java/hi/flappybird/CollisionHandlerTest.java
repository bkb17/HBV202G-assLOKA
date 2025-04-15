package hi.flappybird;
import hi.flappybird.vinnsla.CollisionHandler;
import javafx.application.Platform;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CollisionHandlerTest {

    @BeforeAll
    public static void initFX() {
        // Ensure JavaFX runtime is initialized
        Platform.startup(() -> {});
    }

    @Test
    public void testCollisionDetected() {
        // Arrange
        Rectangle bird = new Rectangle(50, 50, 30, 30); // x, y, width, height
        Rectangle obstacle = new Rectangle(60, 50, 30, 30); // overlaps with bird

        ArrayList<Rectangle> obstacles = new ArrayList<>();
        obstacles.add(obstacle);

        CollisionHandler handler = new CollisionHandler();

        // Act
        boolean result = handler.collisionDetection(obstacles, bird);

        // Assert
        assertTrue(result, "Collision should be detected when rectangles overlap");
    }

    @Test
    public void testNoCollision() {
        // Arrange
        Rectangle bird = new Rectangle(10, 10, 30, 30);
        Rectangle obstacle = new Rectangle(200, 200, 30, 30); // far away

        ArrayList<Rectangle> obstacles = new ArrayList<>();
        obstacles.add(obstacle);

        CollisionHandler handler = new CollisionHandler();

        // Act
        boolean result = handler.collisionDetection(obstacles, bird);

        // Assert
        assertFalse(result, "No collision should be detected when rectangles are far apart");
    }

    @Test
    public void testMultipleObstaclesWithOneCollision() {
        // Arrange
        Rectangle bird = new Rectangle(100, 100, 20, 20);
        ArrayList<Rectangle> obstacles = new ArrayList<>();

        obstacles.add(new Rectangle(10, 10, 30, 30));
        obstacles.add(new Rectangle(200, 200, 30, 30));
        obstacles.add(new Rectangle(100, 100, 20, 20)); // exact same position

        CollisionHandler handler = new CollisionHandler();

        // Act
        boolean result = handler.collisionDetection(obstacles, bird);

        // Assert
        assertTrue(result, "Should detect collision with one of multiple obstacles");
    }
}
