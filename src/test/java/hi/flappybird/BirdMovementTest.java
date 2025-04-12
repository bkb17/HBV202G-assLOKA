package hi.flappybird.vinnsla;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class BirdMovementTest {

    private Rectangle bird;
    private BirdMovement birdMovement;
    private AnchorPane plane;

    @BeforeEach
    public void setUp() {
        bird = new Rectangle(20, 20); // simulate a bird shape
        bird.setLayoutY(100); // starting Y position
        birdMovement = new BirdMovement(bird, 75);
        plane = new AnchorPane();
        plane.setPrefHeight(600); // simulate a game area
    }

    @Test
    public void testFly() {
        double initialY = bird.getLayoutY();
        birdMovement.fly();
        assertTrue(bird.getLayoutY() < initialY, "Bird should move upward after flying");
    }

    @Test
    public void testMoveBirdY() {
        birdMovement.moveBirdY(50);
        assertEquals(150, bird.getLayoutY(), "Bird Y should increase correctly");
    }

    @Test
    public void testIsBirdDead_NoCollision_NotBelowPlane() {
        ArrayList<Rectangle> obstacles = new ArrayList<>();
        assertFalse(birdMovement.isBirdDead(obstacles, plane), "Bird should not be dead");
    }

    @Test
    public void testIsBirdDead_BelowPlane() {
        bird.setLayoutY(601);
        ArrayList<Rectangle> obstacles = new ArrayList<>();
        assertTrue(birdMovement.isBirdDead(obstacles, plane), "Bird should be dead when below screen");
    }

    @Test
    public void testIsBirdDead_WithCollision() {
        Rectangle obstacle = new Rectangle(20, 20);
        obstacle.setLayoutX(bird.getLayoutX());
        obstacle.setLayoutY(bird.getLayoutY());

        ArrayList<Rectangle> obstacles = new ArrayList<>();
        obstacles.add(obstacle);

        assertTrue(birdMovement.isBirdDead(obstacles, plane), "Bird should be dead if it collides");
    }
}

