import bagel.Image;

/**
 * Class representing a Stationary Ghost
 */
public class StationaryGhost extends StationaryEntity {
    private static final Image ghostImage = new Image("res/ghostRed.png");

    /**
     * Constructor for a Stationary Ghost
     */
    public StationaryGhost(double x, double y) {
        super(x, y, ghostImage.getWidth(), ghostImage.getHeight(), ghostImage);
    }
}
