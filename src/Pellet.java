import bagel.Image;

/**
 * Class representing a Pellet
 */
public class Pellet extends StationaryEntity {
    private static final Image pelletImage = new Image("res/pellet.png");

    /**
     * Creates a new Pellet
     */
    public Pellet(double x, double y) {
        super(x, y, pelletImage.getWidth(), pelletImage.getHeight(), pelletImage);
    }

}
