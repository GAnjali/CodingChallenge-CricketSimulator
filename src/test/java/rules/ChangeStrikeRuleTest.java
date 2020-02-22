package rules;

import models.Player;
import models.ScoreBoard;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ChangeStrikeRuleTest {
    List<Player> players;
    ScoreBoard scoreBoard;
    ChangeStrikeRule changeStrikeRule;

    @Before
    public void init() {
        players = new ArrayList<>();
        players.add(new Player("NS Nodhi", Arrays.asList(5.0, 30.0, 25.0, 10.0, 15.0, 1.0, 9.0, 5.0), 0, 0, false));
        players.add(new Player("Kirat Boli", Arrays.asList(5.0, 30.0, 25.0, 10.0, 15.0, 1.0, 9.0, 5.0), 0, 0, false));
        players.add(new Player("R Rumrah", Arrays.asList(20.0, 30.0, 15.0, 5.0, 5.0, 1.0, 4.0, 20.0), 0, 0, false));
        players.add(new Player("Shashi Henra", Arrays.asList(30.0, 25.0, 5.0, 0.0, 5.0, 1.0, 4.0, 30.0), 0, 0, false));
        scoreBoard = new ScoreBoard(players.get(0), players.get(1), 0, 4, 0, 40, false);
        changeStrikeRule = new ChangeStrikeRule();
    }

    @Test
    public void shouldChangeStrikeWhenStrikerScores1Run() {
        Player striker = scoreBoard.getCurrentStriker();
        scoreBoard.setCurrentRunCount(1);
        scoreBoard.setCurrentBallsPlayed(1);
        changeStrikeRule.processScoreBoard(scoreBoard, players);
        assertEquals(striker, scoreBoard.getCurrentNonStriker());
    }

    @Test
    public void shouldNotChangeStrikeWhenStrikerScores2Runs() {
        Player striker = scoreBoard.getCurrentStriker();
        scoreBoard.setCurrentRunCount(2);
        scoreBoard.setCurrentBallsPlayed(1);
        changeStrikeRule.processScoreBoard(scoreBoard, players);
        assertEquals(striker, scoreBoard.getCurrentStriker());
    }

    @Test
    public void shouldChangeStrikeWhenOverCompleted() {
        Player striker = scoreBoard.getCurrentStriker();
        scoreBoard.setCurrentBallsPlayed(6);
        changeStrikeRule.processScoreBoard(scoreBoard, players);
        assertNotEquals(striker, scoreBoard.getCurrentStriker());
    }
}
