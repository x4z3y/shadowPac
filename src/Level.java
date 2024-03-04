import bagel.Input;

import java.util.ArrayList;

/**
* Abstract class representing a level within ShadowPac
*/
public abstract class Level {

    protected Player player;
    protected final ArrayList<StationaryEntity> entities = new ArrayList<>();

    /**
     * Method used to read file and create objects for each Level
     */
    public abstract void readCSV();

    /**
     * Method used to update the state of the level
     */
    public abstract void update(Input input, ShadowPac game);

    /**
     * Method used to check if the level is complete
     */
    public abstract boolean isComplete();

    /**
     * Method used to check if the player with entities within the game
     */
    public abstract void checkCollision(Player player);
}
