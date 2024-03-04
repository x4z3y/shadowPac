import bagel.Image;

/**
 * Class representing a Cherry
 */
public class Cherry extends StationaryEntity{
    private static final Image cherryImage = new Image("res/cherry.png");
    public final static int POINTS = 20;
    public Cherry(double x, double y) {
        super(x, y, cherryImage.getWidth(), cherryImage.getHeight(), cherryImage);
    }
}
