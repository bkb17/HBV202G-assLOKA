package hi.flappybird;

import hi.flappybird.vinnsla.ObstaclesHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ObstaclesHandlerTest extends ApplicationTest {

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

        Rectangle offScreen = new Rectangle(-50, 0, 25, 100);
        Rectangle visible = new Rectangle(100, 0, 25, 100);

        ArrayList<Rectangle> obstacles = new ArrayList<>();
        obstacles.add(offScreen);
        obstacles.add(visible);
    }
}

