package hi.flappybird;

import javafx.application.Platform;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class JavaFXTestBase {
    private static final AtomicBoolean initialized = new AtomicBoolean(false);

    static {
        if (initialized.compareAndSet(false, true)) {
            CountDownLatch latch = new CountDownLatch(1);
            Platform.startup(latch::countDown);
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException("Failed to initialize JavaFX", e);
            }
        }
    }
}
