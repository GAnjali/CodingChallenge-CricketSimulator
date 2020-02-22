package rules;

import models.MatchStatus;
import models.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ChangeStrikeRuleTest {
    List<Player> players;
    MatchStatus status;
    ChangeStrikeRule changeStrikeRule;

    @Before
    public void init() {
        players = new ArrayList<>();
        players.add(new Player("NS Nodhi", Arrays.asList(5.0, 30.0, 25.0, 10.0, 15.0, 1.0, 9.0, 5.0), 0, 0, false));
        players.add(new Player("Kirat Boli", Arrays.asList(5.0, 30.0, 25.0, 10.0, 15.0, 1.0, 9.0, 5.0), 0, 0, false));
        players.add(new Player("R Rumrah", Arrays.asList(20.0, 30.0, 15.0, 5.0, 5.0, 1.0, 4.0, 20.0), 0, 0, false));
        players.add(new Player("Shashi Henra", Arrays.asList(30.0, 25.0, 5.0, 0.0, 5.0, 1.0, 4.0, 30.0), 0, 0, false));
        status = new MatchStatus(players.get(0), players.get(1), 0, 4, 0, 40, false);
        changeStrikeRule = new ChangeStrikeRule();
    }

    @Test
    public void shouldChangeStrikeWhenStrikerScores1Run() {
        Player striker = status.getCurrentStriker();
        status.setCurrentRunCount(1);
        status.setCurrentBallsPlayed(1);
        changeStrikeRule.processStatus(status, players);
        assertEquals(striker, status.getCurrentNonStriker());
    }

    @Test
    public void shouldNotChangeStrikeWhenStrikerScores2Runs() {
        Player striker = status.getCurrentStriker();
        status.setCurrentRunCount(2);
        status.setCurrentBallsPlayed(1);
        changeStrikeRule.processStatus(status, players);
        assertEquals(striker, status.getCurrentStriker());
    }

    @Test
    public void shouldChangeStrikeWhenOverCompleted() {
        Player striker = status.getCurrentStriker();
        status.setCurrentBallsPlayed(6);
        changeStrikeRule.processStatus(status, players);
        assertNotEquals(striker, status.getCurrentStriker());
    }
}
