package hi.flappybird;

import hi.flappybird.vinnsla.CollisionHandler;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class CollisionHandlerTest extends JavaFXTestBase {

    private final CollisionHandler handler = new CollisionHandler();

    @Test
    void testCollisionDetected() throws InterruptedException {
        runAndWait(() -> {
            ImageView bird = new ImageView();
            bird.setLayoutX(50);
            bird.setLayoutY(50);
            bird.setFitWidth(30);
            bird.setFitHeight(30);

            Rectangle obstacle = new Rectangle(60, 60, 30, 30);
            ArrayList<Rectangle> obstacles = new ArrayList<>();
            obstacles.add(obstacle);

            assertTrue(handler.collisionDetection(obstacles, bird));
        });
    }

    @Test
    void testNoCollision() throws InterruptedException {
        runAndWait(() -> {
            ImageView bird = new ImageView();
            bird.setLayoutX(10);
            bird.setLayoutY(10);
            bird.setFitWidth(20);
            bird.setFitHeight(20);

            Rectangle obstacle = new Rectangle(100, 100, 30, 30);
            ArrayList<Rectangle> obstacles = new ArrayList<>();
            obstacles.add(obstacle);

            assertFalse(handler.collisionDetection(obstacles, bird));
        });
    }

    @Test
    void testMultipleObstaclesWithCollision() throws InterruptedException {
        runAndWait(() -> {
            ImageView bird = new ImageView();
            bird.setLayoutX(100);
            bird.setLayoutY(100);
            bird.setFitWidth(40);
            bird.setFitHeight(40);

            ArrayList<Rectangle> obstacles = new ArrayList<>();
            obstacles.add(new Rectangle(0, 0, 20, 20));
            obstacles.add(new Rectangle(110, 110, 30, 30)); // should collide

            assertTrue(handler.collisionDetection(obstacles, bird));
        });
    }

    @Test
    void testMultipleObstaclesNoCollision() throws InterruptedException {
        runAndWait(() -> {
            ImageView bird = new ImageView();
            bird.setLayoutX(200);
            bird.setLayoutY(200);
            bird.setFitWidth(40);
            bird.setFitHeight(40);

            ArrayList<Rectangle> obstacles = new ArrayList<>();
            obstacles.add(new Rectangle(10, 10, 20, 20));
            obstacles.add(new Rectangle(50, 50, 30, 30));

            assertFalse(handler.collisionDetection(obstacles, bird));
        });
    }

    // Helper method to run JavaFX code on FX thread
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



