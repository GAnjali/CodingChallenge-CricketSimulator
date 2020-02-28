package rules;

import models.Player;
import models.ScoreBoard;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PlayerOutRuleTest {
    List<Player> players;
    ScoreBoard scoreBoard;
    PlayerOutRule playerOutRule;

    @Before
    public void init() {
        players = new ArrayList<>();
        players.add(new Player("NS Nodhi", Arrays.asList(5.0, 30.0, 25.0, 10.0, 15.0, 1.0, 9.0, 5.0), 0, 0, false));
        players.add(new Player("Kirat Boli", Arrays.asList(5.0, 30.0, 25.0, 10.0, 15.0, 1.0, 9.0, 5.0), 0, 0, false));
        players.add(new Player("R Rumrah", Arrays.asList(20.0, 30.0, 15.0, 5.0, 5.0, 1.0, 4.0, 20.0), 0, 0, false));
        players.add(new Player("Shashi Henra", Arrays.asList(30.0, 25.0, 5.0, 0.0, 5.0, 1.0, 4.0, 30.0), 0, 0, false));
        scoreBoard = new ScoreBoard(players.get(0), players.get(1), 0, 4, 0, 40, false);
        playerOutRule = new PlayerOutRule();
    }

    @Test
    public void shouldGetNewStrikerWhenStrikerIsOut() {
        scoreBoard.setCurrentPlayerIsOut(true);
        scoreBoard.getCurrentStriker().setOut(true);
        playerOutRule.perform(scoreBoard, players);
        assertEquals(players.get(2), scoreBoard.getCurrentStriker());
    }

    @Test
    public void shouldGetNonStrikerAsStrikerWhenStrikerIsOutAndThereAreNoPlayerToPlay() {
        Player currentNonStriker = scoreBoard.getCurrentNonStriker();
        players.get(2).setOut(true);
        players.get(3).setOut(true);
        scoreBoard.setCurrentPlayerIsOut(true);
        scoreBoard.getCurrentStriker().setOut(true);
        playerOutRule.perform(scoreBoard, players);
        assertEquals(currentNonStriker, scoreBoard.getCurrentStriker());
    }

    @Test
    public void shouldGetNewNonStrikerWhenNonStrikerIsOut() {
        scoreBoard.setCurrentPlayerIsOut(true);
        scoreBoard.getCurrentNonStriker().setOut(true);
        playerOutRule.perform(scoreBoard, players);
        assertEquals(players.get(2), scoreBoard.getCurrentNonStriker());
    }

    @Test
    public void shouldGetEmptyNonStrikerWhenNonStrikerIsOutAndThereAreNoPlayerToPlay() {
        players.get(2).setOut(true);
        players.get(3).setOut(true);
        scoreBoard.setCurrentPlayerIsOut(true);
        scoreBoard.getCurrentNonStriker().setOut(true);
        playerOutRule.perform(scoreBoard, players);
        assertNull(scoreBoard.getCurrentNonStriker());
    }
}
