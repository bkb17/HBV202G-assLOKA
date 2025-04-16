package hi.flappybird;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.Test;
import hi.flappybird.vinnsla.Bird;

import static org.junit.jupiter.api.Assertions.*;

public class BirdTest {

    private static boolean javafxInitialized = false;

    // Needed to initialize JavaFX Toolkit
    @BeforeAll
    public static void initFX() throws Exception {
        if (!javafxInitialized) {
            Platform.startup(() -> {});
            javafxInitialized = true;
        }
    }

    // Minimal subclass just to test abstract Bird
    static class TestBird extends Bird {
        @Override
        protected void loadFrames() {
            birdFrames.add(new Image(getClass().getResourceAsStream("/images/pinkbird1.png")));
            birdFrames.add(new Image(getClass().getResourceAsStream("/images/pinkbird2.png")));
        }
    }

    @Test
    public void testBirdIsCreated() {
        Bird bird = new TestBird();
        assertNotNull(bird.getView());
        assertEquals(70, bird.getX());
        assertEquals(200, bird.getY());
    }

    @Test
    public void testSetY() {
        Bird bird = new TestBird();
        bird.setY(250);
        assertEquals(250, bird.getY());
    }
}