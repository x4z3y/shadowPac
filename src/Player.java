import bagel.*;
import bagel.util.Point;
import bagel.util.Rectangle;
/**
 * Class that represents a player in the game
 * Used move, moveBack, renderScore, renderLives, isDead methods from LMS project-1 solutions
 */
public class Player {
    private static final Image PAC = new Image("res/pac.png");
    private static final Image PAC_OPEN = new Image("res/pacOpen.png");
    private final static Image HEART = new Image("res/heart.png");

    private final static int MOVE_SIZE = 3;
    private static final int FRENZY_MOVE_SIZE = 4;
    private final static int MAX_LIVES = 3;
    private final static int SWITCH_FRAME = 15;

    private final static int FONT_SIZE = 20;
    private final static String SCORE_STRING = "SCORE ";
    private final static int SCORE_X = 25;
    private final static int SCORE_Y = 25;
    private final static int LIVES_X = 900;
    private final static int LIVES_Y = 10;
    private final static int LIVES_OFFSET = 30;
    private final Font FONT = new Font("res/FSO8BITR.ttf", FONT_SIZE);

    private final Point respawnPoint;

    private DrawOptions rotator = new DrawOptions();
    private static boolean frenzy = false;

    private Point position;
    private Point prevPosition;

    private Image currentImage;
    private int score;
    private int lives;
    private int frames;
    private double angle;

    /**
     * Method that constructs a player
     */
    public Player(double x, double y) {
        this.position = new Point(x, y);
        this.respawnPoint = position;
        this.currentImage = PAC;
        this.lives = MAX_LIVES;
    }

    /**
     * Method that updates the player's logic
     */
    public void update (Input input, Level gameobject) {

        int currentMoveSize = frenzy ? FRENZY_MOVE_SIZE : MOVE_SIZE;

        if (input.isDown(Keys.LEFT)) {
            move(-currentMoveSize, 0);
            angle = Math.PI;
        }
        else if (input.isDown(Keys.RIGHT)) {
            move(currentMoveSize, 0);
            angle = 0.0;
        }
        else if (input.isDown(Keys.UP)) {
            move(0, -currentMoveSize);
            angle = Math.PI + Math.PI/2.0;
        }
        else if (input.isDown(Keys.DOWN)) {
            move(0, currentMoveSize);
            angle = Math.PI/2.0;
        }
        animation();
        gameobject.checkCollision(this);
        renderScore();
        renderLives();
    }

    /**
     * Method that moves the player given the direction
     */
    private void move(double xMove, double yMove){
        prevPosition = position;
        position = new Point(position.x + xMove, position.y + yMove);
    }

    public void moveBack() {
        position = prevPosition;
    }

    /**
     * Method that renders the player's score
     */
    private void renderScore(){
        FONT.drawString(SCORE_STRING + score, SCORE_X, SCORE_Y);
    }

    /**
     * Method that renders the player's lives
     */
    private void renderLives(){
        for (int i = 0; i < lives; i++){
            HEART.drawFromTopLeft(LIVES_X + (LIVES_OFFSET*i), LIVES_Y);
        }
    }

    /**
     * Method that checks if the player has 0 lives
     */
    public boolean isDead() {
        return lives == 0;
    }


    /**
     * Animation & rotation of PacMan opening and closing mouth every 15 frames
     */
    private void animation() {
        frames = (frames + 1) % (2 * SWITCH_FRAME);
        currentImage = (frames < SWITCH_FRAME) ? PAC : PAC_OPEN;
        currentImage.drawFromTopLeft(position.x, position.y, rotator.setRotation(angle));
    }

    /**
     * Method that resets the player's position to the starting location
     */
    public void resetPosition() {
        position = respawnPoint;
        currentImage = PAC;
        rotator.setRotation(0);
    }

    /**
     * Method that removes a life from the player when they collide with a ghost
     */
    public void removeLives() {
        lives--;
        resetPosition();
    }

    /**
     * Method updates a player's hitbox
     */
    public Rectangle updateHitbox() {
        return new Rectangle(position.x, position.y, PAC.getWidth(), PAC.getHeight());
    }

    /**
     * Method that adds an objects score to the player's score
     */
    public void addScore(int score) {
        this.score += score;
    }

    /**
     * Method that returns the player's score
     */
    public int getScore() {
        return score;
    }

    /**
     * Method that sets the frenzy mode
     */
    public static void setFrenzy(boolean mode) {
        frenzy = mode;
    }

}
