package models;

import commentary.Commentary;
import gamestrategy.GameStrategy;
import gamestrategy.RandomWeightedGameStrategy;
import org.junit.Before;
import org.junit.Test;
import rules.ChangeStrikeRule;
import rules.PlayerOutRule;
import rules.Rule;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class MatchTest {
    List<Player> players;
    ScoreBoard scoreBoard;
    Match match;
    GameStrategy runStrategy;
    Rule[] rules;
    Commentary commentary;
    ByteArrayOutputStream outContent;

    @Before
    public void init() throws IOException {
        players = new ArrayList<>();
        match = new Match("Bengaluru", "Chennai", 4, 40, 4);
        runStrategy = new RandomWeightedGameStrategy();
        rules = new Rule[]{new PlayerOutRule(), new ChangeStrikeRule()};
        scoreBoard = new ScoreBoard(players.get(0), players.get(1), 0, 4, 0, 40, false);
        commentary = new Commentary(scoreBoard);
    }

    @Test
    public void testShouldExpectAllPlayersGettingOutWithHighProbablityOfEachPlayerGettingOut() {
        players.add(new Player("Kirat Boli", Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 93.0), 0, 0, false));
        players.add(new Player("NS Nodhi", Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 93.0), 0, 0, false));
        players.add(new Player("R Rumrah", Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 93.0), 0, 0, false));
        players.add(new Player("Shashi Henra", Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 93.0), 0, 0, false));
        match.simulate(players, runStrategy, rules, scoreBoard, commentary);
        players.forEach(player -> assertTrue(player.isOut()));
    }

    @Test
    public void testShouldExpectToWinTheMatchWithHighProbablityOfEachPlayerGettingSix() {
        players.add(new Player("Kirat Boli", Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 93.0, 2.0), 0, 0, false));
        players.add(new Player("NS Nodhi", Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 93.0, 1.0), 0, 0, false));
        players.add(new Player("R Rumrah", Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 93.0, 1.0), 0, 0, false));
        players.add(new Player("Shashi Henra", Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 93.0, 1.0), 0, 0, false));
        scoreBoard = new ScoreBoard(players.get(0), players.get(1), 0, 4, 0, 40, false);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        match.simulate(players, runStrategy, rules, scoreBoard, commentary);
        assertTrue(outContent.toString().contains("Bengaluru won"));
    }
}