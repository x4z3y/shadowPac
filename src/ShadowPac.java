import bagel.*;

/**
 * Skeleton Code for SWEN20003 Project 2, Semester 1, 2023
 *
 * Please enter your name below
 * William Zhang
 */
public class ShadowPac extends AbstractGame  {
    private final static int WINDOW_WIDTH = 1024;
    private final static int WINDOW_HEIGHT = 768;
    private final static String GAME_TITLE = "SHADOW PAC";
    private final Image BACKGROUND_IMAGE = new Image("res/background0.png");

    private final static int TITLE_FONT_SIZE = 64;
    private final static int INSTRUCTION_FONT_SIZE = 24;
    private final static int INSTRUCTION2_FONT_SIZE = 40;
    private final static int TITLE_X = 260;
    private final static int TITLE_Y = 250;
    private final static int INSTRUCTIONS_X = 320;
    private final static int INSTRUCTIONS_Y = 440;
    private final static int INSTRUCTIONS_X2 = 200;
    private final static int INSTRUCTIONS_Y2 = 350;
    private final static String INSTRUCTION_MESSAGE = "PRESS SPACE TO START\n" +"USE ARROW KEYS TO MOVE\n";
    private final static String EXTRA_INSTRUCTION_MESSAGE = "EAT THE PELLET TO ATTACK\n";
    private final static String END_MESSAGE = "GAME OVER!";
    private final static String WIN_MESSAGE = "WELL DONE!";
    private final static String LEVEL_COMPLETE_MESSAGE = "LEVEL COMPLETE!";

    private final Font TITLE_FONT = new Font("res/FSO8BITR.TTF", TITLE_FONT_SIZE);
    private final Font INSTRUCTION_FONT = new Font("res/FSO8BITR.TTF", INSTRUCTION_FONT_SIZE);
    private final Font INSTRUCTION2_FONT = new Font("res/FSO8BITR.TTF", INSTRUCTION2_FONT_SIZE);

    private final static int LEVEL_COMPLETE_FRAME = 500;
    private int counter;

    private boolean hasStarted;
    private boolean gameOver;
    private boolean level1;
    private Level currentLevel;

    public ShadowPac() {
        super(WINDOW_WIDTH, WINDOW_HEIGHT, GAME_TITLE);
        this.counter = LEVEL_COMPLETE_FRAME;
        this.hasStarted = false;
        this.gameOver = false;
        this.level1 = false;
    }

    /**
     * The entry point for the program.
     */
    public static void main(String[] args) {
        ShadowPac game = new ShadowPac();
        game.run();
    }

    /**
     * Performs a state update.
     * Allows the game to exit when the escape key is pressed.
     */
    @Override
    protected void update(Input input) {

        if (input.wasPressed(Keys.ESCAPE)){
            Window.close();
        }

        BACKGROUND_IMAGE.draw(Window.getWidth()/2.0, Window.getHeight()/2.0);

        if (!hasStarted) {
            if (!level1) {
                drawStartScreen(INSTRUCTION_MESSAGE);
            // Timer to display level complete message
            } else if (counter > 0) {
                drawMessage(LEVEL_COMPLETE_MESSAGE);
                counter--;
            } else {
                INSTRUCTION2_FONT.drawString(INSTRUCTION_MESSAGE + EXTRA_INSTRUCTION_MESSAGE,
                        INSTRUCTIONS_X2, INSTRUCTIONS_Y2);
            }
            if (input.wasPressed(Keys.SPACE)) {
                hasStarted = true;
                currentLevel = (level1 ? new Level1() : new Level0());
            }
        } else if (gameOver) {
            drawMessage(END_MESSAGE);
        } else {
            if (currentLevel.isComplete() || input.wasPressed(Keys.W)) {
                // User has completed all the levels
                if (level1) {
                    drawMessage(WIN_MESSAGE);
                // Reset the game to prepare for level 1
                } else {
                    hasStarted = false;
                    level1 = true;
                }
            } else {
                currentLevel.update(input, this);
            }
        }
    }

    /**
     * Method used to draw the start screen title and instructions
     */
    public void drawStartScreen(String instructionMessage) {
        TITLE_FONT.drawString(GAME_TITLE, TITLE_X, TITLE_Y);
        INSTRUCTION_FONT.drawString(instructionMessage, INSTRUCTIONS_X, INSTRUCTIONS_Y);
    }

    /**
     * Method used to draw end screen messages
     */
    public void drawMessage(String message) {
        TITLE_FONT.drawString(message, (Window.getWidth()/2.0 - (TITLE_FONT.getWidth(message)/2.0)),
                (Window.getHeight()/2.0 + (TITLE_FONT_SIZE/2.0)));
    }

    /**
     * Method used to set the game over state
     */
    public void setGameOver() {
        this.gameOver = true;
    }

}
