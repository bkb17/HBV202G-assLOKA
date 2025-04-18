package hi.flappybird;

import hi.flappybird.vinnsla.BlueBird;
import javafx.application.Platform;
import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class BlueBirdTest extends JavaFXTestBase {

    @Test
    void testBlueBirdInitialization() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            BlueBird blueBird = new BlueBird(70, 200);

            assertNotNull(blueBird.getShape(), "ImageView should be initialized");
            assertEquals(50, blueBird.getShape().getFitWidth());
            assertEquals(45, blueBird.getShape().getFitHeight());

            assertEquals(3, blueBird.getBirdFrames().size(), "Should have 3 animation frames");
            for (Image img : blueBird.getBirdFrames()) {
                assertNotNull(img, "Image frame should not be null");
            }

            assertNotNull(blueBird.getMovement(), "BirdMovement should be initialized");

            latch.countDown();
        });

        latch.await();
    }
}

