import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import bagel.Input;

/**
 * Implementation of Level 0 of ShadowPac
 */
public class Level0 extends Level {
    private final static String WORLD_FILE_0 = "res/level0.csv";
    private final static int LEVEL0_MAX_SCORE = 1210;

    /**
     * Loads Level 0
     */
    public Level0() {
        readCSV();
    }

    @Override
    public void readCSV() {
        try (BufferedReader br =
                     new BufferedReader(new FileReader(WORLD_FILE_0))) {
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
                    case "Ghost":
                        entities.add(new StationaryGhost(x, y));
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
            player.update(input, this);
            for (StationaryEntity entity : entities) {
                entity.draw();
            }
        }
    }

    @Override
    public boolean isComplete() {
        return (player.getScore() == LEVEL0_MAX_SCORE);
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

            // Collision for ghosts
            if (entity.getHitbox().intersects(player.updateHitbox()) && entity instanceof StationaryGhost) {
                player.removeLives();
                break;
            }
        }
    }

}
