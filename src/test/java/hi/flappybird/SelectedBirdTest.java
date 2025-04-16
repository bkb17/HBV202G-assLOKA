package hi.flappybird;

import hi.flappybird.vinnsla.SelectedBird;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SelectedBirdTest {

    @AfterEach
    public void resetBird() {
        // Ensure default value is restored after each test
        SelectedBird.setSelectedBird("pink");
    }

    @Test
    public void testDefaultBirdIsPink() {
        assertEquals("pink", SelectedBird.getSelectedBird(), "Default bird should be 'pink'");
    }

    @Test
    public void testSetSelectedBirdToBlue() {
        SelectedBird.setSelectedBird("blue");
        assertEquals("blue", SelectedBird.getSelectedBird(), "Should return 'blue' after being set");
    }

    @Test
    public void testSetSelectedBirdToDolly() {
        SelectedBird.setSelectedBird("dolly");
        assertEquals("dolly", SelectedBird.getSelectedBird(), "Should return 'dolly' after being set");
    }
}
