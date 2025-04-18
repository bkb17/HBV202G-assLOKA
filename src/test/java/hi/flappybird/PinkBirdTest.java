package hi.flappybird;

import hi.flappybird.vinnsla.NormalSpeedStrategy;
import hi.flappybird.vinnsla.PinkBird;
import javafx.application.Platform;
import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class PinkBirdTest extends JavaFXTestBase {

    @Test
    void testPinkBirdInitialization() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            PinkBird pinkBird = new PinkBird(70, 200);

            assertNotNull(pinkBird.getShape(), "ImageView should be initialized");
            assertEquals(3, pinkBird.getBirdFrames().size(), "Should load 3 animation frames");

            for (Image frame : pinkBird.getBirdFrames()) {
                assertNotNull(frame, "Each frame should be a valid Image");
            }

            assertNotNull(pinkBird.getMovement(), "BirdMovement should be initialized");
            assertTrue(pinkBird.getSpeedStrategy() instanceof NormalSpeedStrategy, "Should use NormalSpeedStrategy");

            latch.countDown();
        });

        latch.await();
    }
}

