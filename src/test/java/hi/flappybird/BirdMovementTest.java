package hi.flappybird;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BirdMovementTest {

    private Rectangle bird;
    private BirdMovement birdMovement;
    private AnchorPane plane;

    @BeforeEach
    public void setUp() {
        // Initializes JavaFX runtime


        bird = new Rectangle(20, 20);
        bird.setLayoutY(100);
        birdMovement = new BirdMovement(bird, 50);
        plane = new AnchorPane();
        plane.setPrefHeight(600);
    }

    @Test
    public void testFlyDecreasesY() {
        double initialY = bird.getLayoutY();
        birdMovement.fly();
        assertTrue(bird.getLayoutY() < initialY, "Bird should move up when it flies.");
    }

    @Test
    public void testFlyDoesNotGoAboveTop() {
        bird.setLayoutY(10);
        birdMovement.fly();
        assertEquals(0, bird.getLayoutY(), "Bird should not fly above the top of the screen.");
    }

    @Test
    public void testMoveBirdYChangesY() {
        double initialY = bird.getLayoutY();
        birdMovement.moveBirdY(30);
        assertEquals(initialY + 30, bird.getLayoutY(), 0.001);
    }

    @Test
    public void testIsBirdDeadWhenOutOfBounds() {
        bird.setLayoutY(600);
        ArrayList<Rectangle> obstacles = new ArrayList<>();
        assertTrue(birdMovement.isBirdDead(obstacles, plane), "Bird should be dead if it goes off the screen.");
    }

    @Test
    public void testIsBirdDeadWhenCollision() {
        Rectangle obstacle = new Rectangle(20, 20);
        obstacle.setLayoutX(0);
        obstacle.setLayoutY(100);
        ArrayList<Rectangle> obstacles = new ArrayList<>();
        obstacles.add(obstacle);

        CollisionHandler mockHandler = new CollisionHandler() {
            @Override
            public boolean collisionDetection(ArrayList<Rectangle> obstacles, Rectangle bird) {
                return true;
            }
        };
        birdMovement = new BirdMovement(bird, 50);
        birdMovement.collisionHandler = mockHandler;

        assertTrue(birdMovement.isBirdDead(obstacles, plane), "Bird should be dead if it collides.");
    }
}

