package hi.flappybird;

import hi.flappybird.vinnsla.CollisionHandler;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CollisionHandlerTest extends ApplicationTest {

    @Test
    public void testCollisionDetected() {
        Rectangle bird = new Rectangle(50, 50, 30, 30);
        Rectangle obstacle = new Rectangle(60, 50, 30, 30);

        ArrayList<Rectangle> obstacles = new ArrayList<>();
        obstacles.add(obstacle);

        CollisionHandler handler = new CollisionHandler();

        boolean result = handler.collisionDetection(obstacles, bird);
        assertTrue(result, "Collision should be detected when rectangles overlap");
    }

    @Test
    public void testNoCollision() {
        Rectangle bird = new Rectangle(10, 10, 30, 30);
        Rectangle obstacle = new Rectangle(200, 200, 30, 30);

        ArrayList<Rectangle> obstacles = new ArrayList<>();
        obstacles.add(obstacle);

        CollisionHandler handler = new CollisionHandler();

        boolean result = handler.collisionDetection(obstacles, bird);
        assertFalse(result, "No collision should be detected when rectangles are far apart");
    }
}

