package hi.flappybird;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import hi.flappybird.vinnsla.Bird;
import hi.flappybird.vinnsla.NormalSpeedStrategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BirdTest {

    private static boolean javafxInitialized = false;

    @BeforeAll
    public static void initFX() {
        if (!javafxInitialized) {
            Platform.startup(() -> {});
            javafxInitialized = true;
        }
    }

    // Minimal subclass of abstract Bird for testing
    static class TestBird extends Bird {
        public TestBird(double startX, double startY) {
            super(startX, startY, new NormalSpeedStrategy());
        }

        @Override
        protected void loadFrames() {
            birdFrames.add(new Image(getClass().getResourceAsStream("/images/pinkbird1.png")));
            birdFrames.add(new Image(getClass().getResourceAsStream("/images/pinkbird2.png")));
        }
    }

    @Test
    public void testBirdIsCreated() {
        Bird bird = new TestBird(70, 200);
        ImageView view = bird.getShape();

        assertNotNull(view);
        assertEquals(70, view.getX());
        assertEquals(200, view.getY());
    }

    @Test
    public void testSetY() {
        Bird bird = new TestBird(70, 200);
        bird.getShape().setY(250);
        assertEquals(250, bird.getShape().getY());
    }
}
