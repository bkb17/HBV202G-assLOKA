package hi.flappybird.vinnsla;

/**
 * Singleton-style helper to store the selected bird type across scenes.
 */
public class SelectedBird {
    private static String selectedBird = "pink";

    public static void setSelectedBird(String bird) {
        selectedBird = bird;
    }

    public static String getSelectedBird() {
        return selectedBird;
    }
}
