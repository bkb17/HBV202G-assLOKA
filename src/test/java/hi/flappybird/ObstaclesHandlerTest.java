package hi.flappybird;

import hi.flappybird.vinnsla.ObstaclesHandler;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ObstaclesHandlerTest {

    private static boolean initialized = false;

    @BeforeAll
    public static void initFX() {
        if (!initialized) {
            Platform.startup(() -> {});
            initialized = true;
        }
    }

    @Test
    public void testCreateObstacles_AddsTwoRectangles() {
        AnchorPane plane = new AnchorPane();
        ObstaclesHandler handler = new ObstaclesHandler(plane, 600, 400);

        ArrayList<Rectangle> obstacles = handler.createObstacles();

        assertEquals(2, obstacles.size(), "Should create two rectangles");
        assertTrue(plane.getChildren().containsAll(obstacles), "Rectangles should be added to plane");
    }

    @Test
    public void testMoveObstacles_RemovesOutOfScreen() {
        AnchorPane plane = new AnchorPane();
        ObstaclesHandler handler = new ObstaclesHandler(plane, 600, 400);

        // Create an obstacle far off screen
        Rectangle offScreen = new Rectangle(-50, 0, 25, 100);
        Rectangle visible = new Rectangle(100, 0, 25, 100);

        ArrayList<Rectangle> obstacles = new ArrayList<>();
        obstacles.add(offScreen);
        obstacles.add(visible);

        plane.getChildren().addAll(offScreen, visible);

        handler.moveObstacles(obstacles);

        // offScreen should be removed
        assertEquals(1, obstacles.size(), "Only one obstacle should remain");
        assertFalse(obstacles.contains(offScreen), "Off-screen obstacle should be removed");
        assertFalse(plane.getChildren().contains(offScreen), "Plane should not contain off-screen obstacle");
    }

    @Test
    public void testMoveObstacles_ShiftsPosition() {
        AnchorPane plane = new AnchorPane();
        ObstaclesHandler handler = new ObstaclesHandler(plane, 600, 400);

        Rectangle r = new Rectangle(100, 0, 25, 100);
        ArrayList<Rectangle> obstacles = new ArrayList<>();
        obstacles.add(r);
        plane.getChildren().add(r);

        double originalX = r.getX();

        handler.moveObstacles(obstacles);

        assertTrue(r.getX() < originalX, "Obstacle should move left");
    }
}

