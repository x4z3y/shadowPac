import bagel.Image;
import bagel.util.Point;
import bagel.util.Rectangle;

/**
 * Class representing entities within the game that do not move
 */
public class StationaryEntity {
    private final Image image;
    private final Point location;
    private final Rectangle hitbox;

    /**
     * Creates a new StationaryEntity
     * @param x x-coordinate of the entity
     * @param y y-coordinate of the entity
     * @param width width of the entity
     * @param height height of the entity
     * @param image image of the entity
     */
    public StationaryEntity(double x, double y, double width, double height, Image image) {
        this.location = new Point(x, y);
        this.hitbox = new Rectangle(location, width, height);
        this.image = image;
    }

    /**
     * Renders the entity
     */
    public void draw() {
        image.drawFromTopLeft(location.x, location.y);
    }

    /**
     * @return the location of the entity's hitbox
     */
    public Rectangle getHitbox() {
        return hitbox;
    }

}

