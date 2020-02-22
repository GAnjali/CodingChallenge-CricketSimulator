package gamestrategy;

import models.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class RandomWeightedGameStrategyTest {

    RandomWeightedGameStrategy randomWeightedGameStrategy;
    Player strikePlayer;
    List<Player> players;

    @Before
    public void init() {
        players = new ArrayList<Player>();
        players.add(new Player("NS Nodhi", Arrays.asList(5.0, 30.0, 25.0, 10.0, 15.0, 1.0, 9.0, 5.0), 0, 0, false));
        players.add(new Player("Kirat Boli", Arrays.asList(5.0, 30.0, 25.0, 10.0, 15.0, 1.0, 9.0, 5.0), 0, 0, false));
        strikePlayer = new Player("Kirat Boli", Arrays.asList(5.0, 30.0, 25.0, 10.0, 15.0, 1.0, 9.0, 5.0), 0, 0, false);
        randomWeightedGameStrategy = new RandomWeightedGameStrategy();
    }

    @Test
    public void shouldGetScoreWhenScoreRunCalled() {
        int value = randomWeightedGameStrategy.getScoredRuns(strikePlayer);
        assertTrue(value >= 0);
    }

    @Test
    public void shouldNotGetNegativeScoreWhenScoreRunCalled() {
        int value = randomWeightedGameStrategy.getScoredRuns(strikePlayer);
        assertNotEquals(-1, value);
    }
}
