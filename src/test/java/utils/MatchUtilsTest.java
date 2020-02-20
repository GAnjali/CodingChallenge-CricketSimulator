package utils;

import exceptions.PlayerNotFoundException;
import models.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MatchUtilsTest {

    MatchUtils matchUtils;
    Player knownPlayer;
    Player unknownPlayer;
    List<Player> players;

    @Before
    public void init() {
        matchUtils = new MatchUtils();
        players = new ArrayList<Player>();
        players.add(new Player("NS Nodhi", Arrays.asList(5.0, 30.0, 25.0, 10.0, 15.0, 1.0, 9.0, 5.0), 0, 0, false));
        players.add(new Player("Kirat Boli", Arrays.asList(5.0, 30.0, 25.0, 10.0, 15.0, 1.0, 9.0, 5.0), 0, 0, false));
        knownPlayer = new Player("Kirat Boli", Arrays.asList(5.0, 30.0, 25.0, 10.0, 15.0, 1.0, 9.0, 5.0), 0, 0, false);
        unknownPlayer = new Player("Shashi Henra", Arrays.asList(30.0, 25.0, 5.0, 0.0, 5.0, 1.0, 4.0, 30.0), 0, 0, false);
    }

    @Test
    public void shouldExpectsPositionOfPlayerWhenGetPlayerPositionIsCalled() throws PlayerNotFoundException {
        assertEquals(1, matchUtils.getPlayerPosition(knownPlayer, players));
    }

    @Test
    public void shouldThrowExceptionWhenGetPlayerPositionIsCalledWithUnknownPlayer() {
        try {
            matchUtils.getPlayerPosition(unknownPlayer, players);
        } catch (PlayerNotFoundException e) {
            assertTrue(e instanceof Exception);
        }
    }
}
