import bagel.Image;

/**
 * Class representing a Dot
 */
public class Dot extends StationaryEntity {
    private static final Image dotImage = new Image("res/dot.png");
    public final static int POINTS = 10;

    /**
     * Constructor for a Dot
     */
    public Dot(double x, double y) {
        super(x, y, dotImage.getWidth(), dotImage.getHeight(), dotImage);
    }
}