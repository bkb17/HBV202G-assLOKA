package hi.flappybird;

import hi.flappybird.vinnsla.BirdMovement;
import hi.flappybird.vinnsla.SpeedStrategy;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class BirdMovementTest extends JavaFXTestBase {

    private BirdMovement birdMovement;
    private ImageView bird;
    private SpeedStrategy mockStrategy;

    @BeforeEach
    void setUp() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            bird = new ImageView();
            bird.setLayoutY(100);

            mockStrategy = new SpeedStrategy() {
                @Override
                public int getJumpHeight() {
                    return 30;
                }

                @Override
                public double getGravity() {
                    return 2.5;
                }
            };

            birdMovement = new BirdMovement(bird, mockStrategy);
            latch.countDown();
        });
        latch.await();
    }

    @Test
    void testFly() throws InterruptedException {
        runAndWait(() -> {
            birdMovement.fly();
            birdMovement.applyGravity();
            assertTrue(bird.getLayoutY() < 100);
        });
    }

    @Test
    void testApplyGravity() throws InterruptedException {
        runAndWait(() -> {
            double initialY = bird.getLayoutY();
            birdMovement.applyGravity();
            assertTrue(bird.getLayoutY() > initialY);
        });
    }

    @Test
    void testMoveBirdY() throws InterruptedException {
        runAndWait(() -> {
            birdMovement.moveBirdY(10);
            assertEquals(110, bird.getLayoutY());
        });
    }

    @Test
    void testIsBirdDead_withCollision() throws InterruptedException {
        runAndWait(() -> {
            Rectangle obstacle = new Rectangle(0, 100, 20, 20);
            ArrayList<Rectangle> obstacles = new ArrayList<>();
            obstacles.add(obstacle);

            bird.setLayoutX(0);
            bird.setLayoutY(100);

            AnchorPane plane = new AnchorPane();
            plane.setPrefHeight(500);

            assertTrue(birdMovement.isBirdDead(obstacles, plane));
        });
    }

    @Test
    void testIsBirdDead_outOfBounds() throws InterruptedException {
        runAndWait(() -> {
            bird.setLayoutY(600);
            AnchorPane plane = new AnchorPane();
            plane.setPrefHeight(500);
            ArrayList<Rectangle> obstacles = new ArrayList<>();

            assertTrue(birdMovement.isBirdDead(obstacles, plane));
        });
    }

    private void runAndWait(Runnable action) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            action.run();
            latch.countDown();
        });
        latch.await();
    }
}

