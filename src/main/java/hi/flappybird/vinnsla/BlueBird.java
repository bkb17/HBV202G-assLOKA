package hi.flappybird.vinnsla;
import javafx.scene.image.Image;


public class BlueBird extends Bird {
    public BlueBird(double startX, double startY) {
        super(startX, startY, new FastSpeedStrategy());
    }

    @Override
    protected void loadFrames() {
        birdFrames.add(new Image(getClass().getResource("/images/bluebird1.png").toExternalForm()));
        birdFrames.add(new Image(getClass().getResource("/images/bluebird2.png").toExternalForm()));
        birdFrames.add(new Image(getClass().getResource("/images/bluebird3.png").toExternalForm()));
    }
}



