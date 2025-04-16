package hi.flappybird;
import hi.flappybird.vinnsla.BirdMovement;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class BirdMovementTest {

    private Rectangle bird;
    private BirdMovement birdMovement;
    private AnchorPane plane;

    @BeforeEach
    public void setUp() {
        bird = new Rectangle(20, 20); // a simple bird rectangle
        bird.setLayoutY(100); // initial Y position
        birdMovement = new BirdMovement(bird, 75);

        plane = new AnchorPane();
        plane.setPrefHeight(600);
    }

    @Test
    public void testFlyMovesBirdUp() {
        double initialY = bird.getLayoutY();
        birdMovement.fly();
        assertTrue(bird.getLayoutY() < initialY, "Bird should move up after flying.");
    }

    @Test
    public void testMoveBirdYIncreasesY() {
        birdMovement.moveBirdY(50);
        assertEquals(150, bird.getLayoutY(), 0.001);
    }

    @Test
    public void testIsBirdDeadNoCollision() {
        ArrayList<Rectangle> obstacles = new ArrayList<>();
        plane.setPrefHeight(500); // set an artificial ground height
        plane.resize(500, 500);   // also helps in some cases
        bird.setLayoutY(100);     // bird is flying above ground
        assertFalse(birdMovement.isBirdDead(obstacles, plane));
    }


    @Test
    public void testIsBirdDeadBelowPlane() {
        ArrayList<Rectangle> obstacles = new ArrayList<>();
        bird.setLayoutY(700); // below screen
        assertTrue(birdMovement.isBirdDead(obstacles, plane));
    }

    @Test
    public void testIsBirdDeadWithCollision() {
        Rectangle obstacle = new Rectangle(20, 20);
        obstacle.setLayoutX(bird.getLayoutX());
        obstacle.setLayoutY(bird.getLayoutY());
        ArrayList<Rectangle> obstacles = new ArrayList<>();
        obstacles.add(obstacle);

        // move to simulate intersecting bounds
        bird.setLayoutX(0);
        bird.setLayoutY(0);
        obstacle.setLayoutX(0);
        obstacle.setLayoutY(0);

        assertTrue(birdMovement.isBirdDead(obstacles, plane));
    }
}




