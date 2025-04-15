package hi.flappybird.vinnsla;

import javafx.scene.image.Image;

public class PinkBird extends Bird {

    @Override
    protected void loadFrames() {
        birdFrames.add(new Image(getClass().getResourceAsStream("/images/pinkbird1.png")));
        birdFrames.add(new Image(getClass().getResourceAsStream("/images/pinkbird2.png")));
        birdFrames.add(new Image(getClass().getResourceAsStream("/images/pinkbird3.png")));
    }
}
