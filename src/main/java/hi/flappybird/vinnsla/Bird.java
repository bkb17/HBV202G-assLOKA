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
    protected BirdMovement movement;
    protected List<Image> birdFrames = new ArrayList<>();
    protected int currentFrame = 0;
    protected double locationX = 70;
    protected double locationY = 200;
    protected int BIRD_WIDTH = 50;
    protected int BIRD_HEIGHT = 45;
    private Timeline animation;
    private SpeedStrategy speedStrategy;

    /**
     * @param startX
     * @param startY
     * @param speedStrategy
     */
    public Bird(double startX, double startY, SpeedStrategy speedStrategy) {
        locationX = startX;
        locationY = startY;
        loadFrames();
        birdView = new ImageView(birdFrames.get(0));
        birdView.setFitWidth(BIRD_WIDTH);
        birdView.setFitHeight(BIRD_HEIGHT);
        birdView.setX(locationX);
        birdView.setY(locationY);
        movement = new BirdMovement(birdView, speedStrategy);
        startAnimation();
        this.speedStrategy = speedStrategy;
    }

    protected abstract void loadFrames();

    private void startAnimation() {
        animation = new Timeline(new KeyFrame(Duration.seconds(0.15), e -> {
            currentFrame = (currentFrame + 1) % birdFrames.size();
            birdView.setImage(birdFrames.get(currentFrame));
        }));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }

    public BirdMovement getMovement() {
        return movement;
    }

    public ImageView getShape() {
        return birdView;
    }

    public List<Image> getBirdFrames() {
        return birdFrames;
    }

    public SpeedStrategy getSpeedStrategy() {
        return speedStrategy;
    }

}

