package hi.flappybird;

import hi.flappybird.vinnsla.ObstaclesHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ObstaclesHandlerTest {

    private AnchorPane plane;
    private ObstaclesHandler obstaclesHandler;

    @BeforeEach
    void setUp() {
        plane = new AnchorPane();
        obstaclesHandler = new ObstaclesHandler(plane, 600, 800); // 600px height, 800px width
    }

    @Test
    void testCreateObstacles() {
        ArrayList<Rectangle> obstacles = obstaclesHandler.createObstacles();

        assertEquals(2, obstacles.size(), "Should create two rectangles for top and bottom obstacles.");
        assertTrue(plane.getChildren().containsAll(obstacles), "Obstacles should be added to the plane.");
        assertTrue((Boolean) obstacles.get(0).getProperties().get("scoreZone"), "Top rectangle should have 'scoreZone' property.");
    }

    @Test
    void testMoveObstacles() {
        ArrayList<Rectangle> obstacles = obstaclesHandler.createObstacles();
        double initialX = obstacles.get(0).getX();

        obstaclesHandler.moveObstacles(obstacles);

        assertTrue(obstacles.get(0).getX() < initialX, "Obstacles should have moved left.");
    }

    @Test
    void testObstacleRemovalOffScreen() {
        ArrayList<Rectangle> obstacles = obstaclesHandler.createObstacles();

        // Simulate obstacles going off-screen
        for (Rectangle rect : obstacles) {
            rect.setX(-30); // Move them past the left edge
        }

        int originalCount = plane.getChildren().size();
        obstaclesHandler.moveObstacles(obstacles);

        assertTrue(obstacles.isEmpty(), "Obstacles list should be empty after moving off-screen.");
        assertTrue(plane.getChildren().size() < originalCount, "Obstacles should be removed from the plane.");
    }
}
