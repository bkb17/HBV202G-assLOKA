package hi.flappybird.vinnsla;

import javafx.scene.image.Image;

public class BlueBird extends Bird {

    @Override
    protected void loadFrames() {
        birdFrames.add(new Image(getClass().getResourceAsStream("/images/bluebird1.png")));
        birdFrames.add(new Image(getClass().getResourceAsStream("/images/bluebird2.png")));
        birdFrames.add(new Image(getClass().getResourceAsStream("/images/bluebird3.png")));
    }
}
