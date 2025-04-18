package hi.flappybird.vinnsla;

/**
 * Hjálparklasi í Singleton-style til að geyma valda fuglategund milli sena
 */
public class SelectedBird {
    private static String selectedBird = "pink";

    /**
     * @param bird
     */
    public static void setSelectedBird(String bird) {
        selectedBird = bird;
    }

    /**
     * @return
     */
    public static String getSelectedBird() {
        return selectedBird;
    }
}
