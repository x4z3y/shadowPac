import bagel.Image;

/**
 * Class representing a Wall
 */
public class Wall extends StationaryEntity {
    private static final Image wallImage = new Image("res/wall.png");

    /**
     * Constructor for a Wall
     */
    public Wall(double x, double y) {
        super(x, y, wallImage.getWidth(), wallImage.getHeight(), wallImage);
    }
}
