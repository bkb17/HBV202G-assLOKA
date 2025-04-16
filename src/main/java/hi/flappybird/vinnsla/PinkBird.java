package hi.flappybird.vinnsla;
import javafx.scene.image.Image;


public class PinkBird extends Bird {
    public PinkBird(double startX, double startY) {
        super(startX, startY, new NormalSpeedStrategy());
    }

    @Override
    protected void loadFrames() {
        birdFrames.add(new Image(getClass().getResource("/images/pinkbird1.png").toExternalForm()));
        birdFrames.add(new Image(getClass().getResource("/images/pinkbird2.png").toExternalForm()));
        birdFrames.add(new Image(getClass().getResource("/images/pinkbird3.png").toExternalForm()));
    }
}


