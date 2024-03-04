import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import bagel.Input;

/**
 * Implementation of Level 1 of ShadowPac
 */
public class Level1 extends Level {
    private final static String WORLD_FILE_1 = "res/level1.csv";
    private final static int LEVEL1_MAX_SCORE = 800;

    private boolean frenzy = false;
    private int FRENZY_TIME = 1000;

    private final ArrayList<Ghost> ghosts = new ArrayList<>();
    private final ArrayList<Ghost> removedGhosts = new ArrayList<>();

    /**
     * Loads Level 1
     */
    public Level1() {
        readCSV();
    }

    @Override
    public void readCSV() {
        try (BufferedReader br =
                     new BufferedReader(new FileReader(WORLD_FILE_1))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] sections = line.split(",");
                String type = sections[0];
                int x = Integer.parseInt(sections[1]);
                int y = Integer.parseInt(sections[2]);

                switch (type) {
                    case "Player":
                        player = new Player(x, y);
                        break;
                    case "GhostRed":
                        ghosts.add(new GhostRed(x, y));
                        break;
                    case "GhostBlue":
                        ghosts.add(new GhostBlue(x, y));
                        break;
                    case "GhostGreen":
                        ghosts.add(new GhostGreen(x, y));
                        break;
                    case "GhostPink":
                        ghosts.add(new GhostPink(x, y));
                        break;
                    case "Cherry":
                        entities.add(new Cherry(x, y));
                        break;
                    case "Pellet":
                        entities.add(new Pellet(x, y));
                        break;
                    case "Wall":
                        entities.add(new Wall(x, y));
                        break;
                    case "Dot":
                        entities.add(new Dot(x, y));
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void update(Input input, ShadowPac game) {
        if (player.isDead()) {
            game.setGameOver();
        } else {
            if (frenzy) {
                FRENZY_TIME--;
                if (FRENZY_TIME == 0) {
                    frenzy = false;
                    Ghost.setFrenzy(false);
                    Player.setFrenzy(false);
                    ghosts.addAll(removedGhosts);
                }
            }

            for (StationaryEntity entity : entities) {
                entity.draw();
            }

            player.update(input, this);
            for (Ghost ghost : ghosts) {
                ghost.update(input, entities);
            }
        }

    }

    @Override
    public boolean isComplete() {
        return player.getScore() >= LEVEL1_MAX_SCORE;
    }

    @Override
    public void checkCollision(Player player) {
        for (StationaryEntity entity : entities) {

            // Collision for walls
            if (entity.getHitbox().intersects(player.updateHitbox()) && entity instanceof Wall) {
                player.moveBack();
            }

            // Collision for dots
            if (entity.getHitbox().intersects(player.updateHitbox()) && entity instanceof Dot) {
                player.addScore(Dot.POINTS);
                entities.remove(entity);
                break;
            }

            // Collision for cherries
            if (entity.getHitbox().intersects(player.updateHitbox()) && entity instanceof Cherry) {
                player.addScore(Cherry.POINTS);
                entities.remove(entity);
                break;
            }

            // Collision for pellet
            if (entity.getHitbox().intersects(player.updateHitbox()) && entity instanceof Pellet) {
                frenzy = true;
                Player.setFrenzy(true);
                Ghost.setFrenzy(true);
                entities.remove(entity);
                break;
            }
        }

        for (Ghost ghost : ghosts) {
            if (ghost.getHitbox().intersects(player.updateHitbox())) {
                if (!frenzy) {
                    // Lose a life
                    ghost.resetPosition();
                    player.removeLives();
                } else {
                    // Eat the ghost
                    ghost.resetPosition();
                    player.addScore(Ghost.POINTS);
                    removedGhosts.add(ghost);
                    ghosts.remove(ghost);
                    break;
                }
            }
        }
    }

}
