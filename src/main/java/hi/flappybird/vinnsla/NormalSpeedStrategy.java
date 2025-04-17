package hi.flappybird.vinnsla;

public class NormalSpeedStrategy implements SpeedStrategy {
    @Override
    public int getJumpHeight() {
        return 10;
    }

    @Override
    public double getGravity() {
        return 0.45;
    }
}




