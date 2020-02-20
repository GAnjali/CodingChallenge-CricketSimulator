package models;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {

    Game game;
    Match match;
    List<Player> players;

    @Before
    public void init() {
        game = new Game();
        players = game.intializePlayers();
        match = game.intializeMatch(players);
    }

    @Test
    public void testShouldVerifyMatchObjectWhenInitializeMatch() {
        assertEquals("Bengaluru", match.getPlayingTeam());
        assertEquals("Chennai", match.getOpposingTeam());
        assertEquals(4, match.getWicketsLeft());
        assertEquals(40, match.getRunNeededToWin());
        assertEquals(4, match.getOvers());
    }

    @Test
    public void testShouldReturnArrayListWhenInitializePlayersCalled() {
        assertTrue(players instanceof ArrayList);
    }

    @Test
    public void testShouldVerifyPlayersWhenInitializePlayersCalled() {
        assertEquals("Kirat Boli", players.get(0).getName());
        assertEquals("NS Nodhi", players.get(1).getName());
        assertEquals("R Rumrah", players.get(2).getName());
        assertEquals("Shashi Henra", players.get(3).getName());

    }
}
