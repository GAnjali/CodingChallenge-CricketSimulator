package rules;

import exceptions.PlayerNotFoundException;
import models.MatchStatus;
import models.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PlayerOutRuleTest {

    List<Player> players;
    MatchStatus status;
    PlayerOutRule playerOutRule;

    @Before
    public void init() {
        players = new ArrayList<>();
        players.add(new Player("NS Nodhi", Arrays.asList(5.0, 30.0, 25.0, 10.0, 15.0, 1.0, 9.0, 5.0), 0, 0, false));
        players.add(new Player("Kirat Boli", Arrays.asList(5.0, 30.0, 25.0, 10.0, 15.0, 1.0, 9.0, 5.0), 0, 0, false));
        players.add(new Player("R Rumrah", Arrays.asList(20.0, 30.0, 15.0, 5.0, 5.0, 1.0, 4.0, 20.0), 0, 0, false));
        players.add(new Player("Shashi Henra", Arrays.asList(30.0, 25.0, 5.0, 0.0, 5.0, 1.0, 4.0, 30.0), 0, 0, false));
        status = new MatchStatus(players.get(0), players.get(1), 0, 4, 0, 40, false);
        playerOutRule = new PlayerOutRule();
    }

    @Test
    public void shouldGetNewStrikerWhenStrikerIsOut() throws PlayerNotFoundException {
        status.setCurrentPlayerIsOut(true);
        status.getCurrentStriker().setOut(true);
        playerOutRule.processStatus(status, players);
        assertEquals(players.get(2), status.getCurrentStriker());
    }

    @Test
    public void shouldGetNonStrikerAsStrikerWhenStrikerIsOutAndThereAreNoPlayerToPlay() throws PlayerNotFoundException {
        Player currentNonStriker = status.getCurrentNonStriker();
        players.get(2).setOut(true);
        players.get(3).setOut(true);
        status.setCurrentPlayerIsOut(true);
        status.getCurrentStriker().setOut(true);
        playerOutRule.processStatus(status, players);
        assertEquals(currentNonStriker, status.getCurrentStriker());
    }

    @Test
    public void shouldGetNewNonStrikerWhenNonStrikerIsOut() throws PlayerNotFoundException {
        status.setCurrentPlayerIsOut(true);
        status.getCurrentNonStriker().setOut(true);
        playerOutRule.processStatus(status, players);
        assertEquals(players.get(2), status.getCurrentNonStriker());
    }

    @Test
    public void shouldGetEmptyNonStrikerWhenNonStrikerIsOutAndThereAreNoPlayerToPlay() throws PlayerNotFoundException {
        players.get(2).setOut(true);
        players.get(3).setOut(true);
        status.setCurrentPlayerIsOut(true);
        status.getCurrentNonStriker().setOut(true);
        playerOutRule.processStatus(status, players);
        assertNull(status.getCurrentNonStriker());
    }
}
