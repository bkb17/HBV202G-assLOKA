package hi.flappybird.vinnsla;

public class FastSpeedStrategy implements SpeedStrategy {
    @Override
    public int getJumpHeight() {
        return 15;
    }

    @Override
    public double getGravity() {
        return 1.1;
    }
}

