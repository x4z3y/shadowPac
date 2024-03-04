import bagel.Image;
import bagel.Input;
import java.util.Random;

/**
 * Class representing a Pink Ghost
 */
public class GhostPink extends Ghost {
    private static final Image GHOST_PINK = new Image("res/ghostPink.png");
    private static final double SPEED = 3;
    private static final double FRENZY_SPEED = 2.5;
    private static final int DIRECTIONS = 4;

    private final Random rand = new Random();
    private int currentDirection = rand.nextInt(DIRECTIONS);

    public GhostPink(double x, double y) {
        super(x, y, GHOST_PINK.getWidth(), GHOST_PINK.getHeight(), GHOST_PINK);
        this.speed = SPEED;
        this.frenzySpeed = FRENZY_SPEED;
    }

    @Override
    public void movement(Input input, boolean collision) {
        if (collision) {
            moveBack();
            currentDirection = rand.nextInt(DIRECTIONS);
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
