import bagel.Image;
import bagel.Input;
import java.util.Random;

/**
 * Class representing a Green Ghost
 */
public class GhostGreen extends Ghost {
    private static final Image GHOST_GREEN = new Image("res/ghostGreen.png");
    private static final double SPEED = 4;
    private static final double FRENZY_SPEED = 3.5;
    private static final int DIRECTIONS = 2;
    private static final int VERTICAL = 0;
    private static final int HORIZONTAL = 1;

    private static final Random rand = new Random();
    private static final int initialDirection = rand.nextInt(DIRECTIONS);

    private static int currentDirection = (initialDirection == HORIZONTAL) ? RIGHT : DOWN;

    public GhostGreen(double x, double y) {
        super(x, y, GHOST_GREEN.getWidth(), GHOST_GREEN.getHeight(), GHOST_GREEN);
        this.speed = SPEED;
        this.frenzySpeed = FRENZY_SPEED;
    }

    @Override
    public void movement(Input input, boolean collision) {
        if (collision) {
            moveBack();
            switch (initialDirection) {
                case HORIZONTAL:
                    currentDirection = (currentDirection == RIGHT) ? LEFT : RIGHT;
                    break;
                case VERTICAL:
                    currentDirection = (currentDirection == DOWN) ? UP : DOWN;
                    break;
                default:
                    break;
            }
        } else {
            switch (currentDirection) {
                case LEFT:
                    move(-getCurrentSpeed(), 0);
                    break;
                case RIGHT:
                    move(getCurrentSpeed(), 0);
                    break;
                case UP:
                    move(0, -getCurrentSpeed());
                    break;
                case DOWN:
                    move(0, getCurrentSpeed());
                    break;
                default:
                    break;
            }
        }
    }
}
