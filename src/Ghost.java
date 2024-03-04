import bagel.Image;
import bagel.Input;
import bagel.util.Point;
import bagel.util.Rectangle;
import java.util.ArrayList;

/**
 * Abstract class which has methods that are used by all ghosts types
 * Used move and moveBack methods from LMS project-1 solutions
 */
public abstract class Ghost {
    private final static Image FRENZY_GHOST = new Image("res/ghostFrenzy.png");
    public final static int POINTS = 30;
    private final Image GHOST_IMAGE;
    private Image currentImage;
    private Rectangle hitbox;

    protected static final int LEFT = 0;
    protected static final int RIGHT = 1;
    protected static final int UP = 2;
    protected static final int DOWN = 3;

    private final Point respawnPoint;
    private Point position;
    private Point prevPosition;

    private double currentSpeed;
    protected double speed;
    protected double frenzySpeed = speed - 0.5;

    private static boolean frenzy = false;

    public Ghost(double x, double y, double width, double height, Image IMAGE) {
        this.hitbox = new Rectangle(new Point(x, y), width, height);
        this.position = new Point(x, y);
        this.GHOST_IMAGE = IMAGE;
        this.currentImage = GHOST_IMAGE;
        this.respawnPoint = position;

    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    /**
     * Update the ghost's position and logic
     */
    public void update(Input input, ArrayList<StationaryEntity> entities) {

        frenzyMode(frenzy);
        movement(input, false);

        for (StationaryEntity entity : entities) {
            updateHitbox();
            if ((entity.getHitbox().intersects(this.hitbox))&& entity instanceof Wall) {
                movement(input, true);
            }
        }

        currentImage.drawFromTopLeft(position.x, position.y);

    }

    /**
     * Ghost movement logic for each ghost and whether it collides with wall
     */
    protected abstract void movement(Input input, boolean collision);

    /**
     * Set frenzy mode
     */
    public static void setFrenzy(boolean mode) {
        frenzy = mode;
    }

    /**
     * Gets the hitbox of the ghost
     */
    public Rectangle getHitbox() {
        return hitbox;
    }

    protected void move(double xMove, double yMove) {
        prevPosition = position;
        position = new Point(position.x + xMove, position.y + yMove);
    }

    protected void moveBack() {
        position = prevPosition;
    }

    protected void resetPosition() {
        position = respawnPoint;
    }

    private void updateHitbox() {
        this.hitbox = new Rectangle(position, currentImage.getWidth(), currentImage.getHeight());
    }

    private void frenzyMode(boolean mode) {
        if (mode) {
            currentImage = FRENZY_GHOST;
            currentSpeed = frenzySpeed;
        } else {
            currentImage = GHOST_IMAGE;
            currentSpeed = speed;
        }
    }

}
