import bagel.Image;
import bagel.Input;

/**
 * Class representing a Blue Ghost
 */
public class GhostBlue extends Ghost {
    private static final Image GHOST_BLUE = new Image("res/ghostBlue.png");
    private static final double SPEED = 2;
    private static final double FRENZY_SPEED = 1.5;
    private static int currentDirection = DOWN;
    public GhostBlue(double x, double y) {
        super(x, y, GHOST_BLUE.getWidth(), GHOST_BLUE.getHeight(), GHOST_BLUE);
        this.speed = SPEED;
        this.frenzySpeed = FRENZY_SPEED;
    }

    @Override
    public void movement(Input input, boolean collision) {
        if (collision) {
            moveBack();
            currentDirection = (currentDirection == DOWN) ? UP : DOWN;
        } else {
            switch (currentDirection) {
                case UP:
                    move(0, getCurrentSpeed());
                    break;
                case DOWN:
                    move(0, -getCurrentSpeed());
                    break;
                default:
                    break;
            }

        }
    }
}
