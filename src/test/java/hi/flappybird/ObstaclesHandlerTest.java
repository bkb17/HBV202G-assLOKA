package hi.flappybird;

import hi.flappybird.vinnsla.ObstaclesHandler;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class ObstaclesHandlerTest extends JavaFXTestBase {

    @Test
    void testCreateObstacles() throws InterruptedException {
        runAndWait(() -> {
            AnchorPane plane = new AnchorPane();
            double height = 600;
            double width = 800;

            ObstaclesHandler handler = new ObstaclesHandler(plane, height, width);
            ArrayList<Rectangle> obstacles = handler.createObstacles();

            assertEquals(2, obstacles.size(), "Should create 2 rectangles (top and bottom)");
            Rectangle top = obstacles.get(0);
            Rectangle bottom = obstacles.get(1);

            assertEquals(800, top.getX(), "Top rectangle should be at the right edge");
            assertEquals(800, bottom.getX(), "Bottom rectangle should be at the right edge");
            assertTrue((Boolean) top.getProperties().get("scoreZone"), "Top rectangle should have scoreZone=true");
            assertEquals(2, plane.getChildren().size(), "Plane should have 2 obstacles added");
        });
    }

    @Test
    void testMoveObstaclesAndRemoval() throws InterruptedException {
        runAndWait(() -> {
            AnchorPane plane = new AnchorPane();
            double height = 600;
            double width = 800;

            ObstaclesHandler handler = new ObstaclesHandler(plane, height, width);
            ArrayList<Rectangle> obstacles = handler.createObstacles();

            // Simulate one obstacle off-screen
            obstacles.get(0).setX(-30); // off-screen
            obstacles.get(1).setX(100); // still on-screen

            handler.moveObstacles(obstacles);

            assertEquals(1, obstacles.size(), "One obstacle should be removed");
            assertEquals(1, plane.getChildren().size(), "One obstacle should remain in the plane");
        });
    }

    private void runAndWait(Runnable action) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                action.run();
            } finally {
                latch.countDown();
            }
        });
        latch.await();
    }
}
