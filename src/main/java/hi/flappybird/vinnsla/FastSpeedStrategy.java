package hi.flappybird.vinnsla;

public class FastSpeedStrategy implements SpeedStrategy {
    public int getJumpHeight() {
        return 20;
    }

    public double getGravity() {
        return 1.2;
    }
}

