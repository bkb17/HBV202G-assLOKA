package hi.flappybird.vinnsla;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public abstract class Bird {

    protected ImageView birdView;
    protected List<Image> birdFrames = new ArrayList<>();
    protected int currentFrame = 0;
    protected double locationX = 70;
    protected double locationY = 200;
    protected int BIRD_WIDTH = 50;
    protected int BIRD_HEIGHT = 45;
    private Timeline animation;

    public Bird() {
        loadFrames(); // implemented by subclasses
        birdView = new ImageView(birdFrames.get(0));
        birdView.setFitWidth(BIRD_WIDTH);
        birdView.setFitHeight(BIRD_HEIGHT);
        birdView.setX(locationX);
        birdView.setY(locationY);

        startAnimation();
    }

    protected abstract void loadFrames(); // this will be different for each bird type

    private void startAnimation() {
        animation = new Timeline(new KeyFrame(Duration.seconds(0.15), e -> {
            currentFrame = (currentFrame + 1) % birdFrames.size();
            birdView.setImage(birdFrames.get(currentFrame));
        }));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    public ImageView getView() {
        return birdView;
    }

    public void setY(double y) {
        locationY = y;
        birdView.setY(y);
    }

    public double getY() {
        return birdView.getY();
    }

    public double getX() {
        return birdView.getX();
    }

    public ImageView getImageView() {
        return birdView;
    }
}
