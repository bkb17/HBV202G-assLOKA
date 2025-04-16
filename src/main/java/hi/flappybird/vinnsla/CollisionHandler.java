package hi.flappybird.vinnsla;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import javafx.scene.image.ImageView;


public class CollisionHandler {

    public boolean collisionDetection(ArrayList<Rectangle> obstacles, ImageView bird) {
        for (Rectangle rectangle: obstacles) {
            if(rectangle.getBoundsInParent().intersects(bird.getBoundsInParent())){
                return true;
            }
        }
        return  false;
    }
}
