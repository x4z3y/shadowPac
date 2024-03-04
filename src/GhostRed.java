import bagel.Image;
import bagel.Input;

/**
 * Class representing a Red Ghost
 */
public class GhostRed extends Ghost {
    private static final Image GHOST_RED = new Image("res/ghostRed.png");
    private static final double SPEED = 1;
    private static final double FRENZY_SPEED = 0.5;
    private static int currentDirection = RIGHT;
    public GhostRed(double x, double y) {
        super(x, y, GHOST_RED.getWidth(), GHOST_RED.getHeight(), GHOST_RED);
        this.speed = SPEED;
        this.frenzySpeed = FRENZY_SPEED;
    }

    @Override
    public void movement(Input input, boolean collision) {
        if (collision) {
            moveBack();
            currentDirection = (currentDirection == RIGHT) ? LEFT : RIGHT;
        } else {
            switch (currentDirection) {
                case LEFT:
                    move(-getCurrentSpeed(), 0);
                    break;
                case RIGHT:
                    move(getCurrentSpeed(), 0);
                    break;
                default:
                    break;
            }
        }
    }

}
