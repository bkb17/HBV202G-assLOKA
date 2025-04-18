package hi.flappybird.vinnsla;

import javafx.scene.image.Image;

public class PinkBird extends Bird {

    public PinkBird(double startX, double startY) {
        super(startX, startY, new NormalSpeedStrategy());
    }

    @Override
    protected void loadFrames() {
        birdFrames.add(loadImage("/images/pinkbird1.png"));
        birdFrames.add(loadImage("/images/pinkbird2.png"));
        birdFrames.add(loadImage("/images/pinkbird3.png"));
    }


    private Image loadImage(String path) {
        var url = getClass().getResource(path);
        if (url == null) {
            throw new RuntimeException("Could not load image: " + path);
        }
        return new Image(url.toExternalForm());
    }
}



