package models;

import exceptions.PlayerNotFoundException;
import gamestrategy.RandomWeightedGameStrategy;
import org.junit.Before;
import org.junit.Test;
import rules.ChangeStrikeRule;
import rules.PlayerOutRule;
import rules.Rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class MatchTest {

    List<Player> players;
    MatchStatus status;
    Match match;
    RandomWeightedGameStrategy runStrategy;
    Rule[] rules;

    @Before
    public void init() {
        players = new ArrayList<>();
        match = new Match("Bengaluru", "Chennai", 4, 40, 4);
        runStrategy = new RandomWeightedGameStrategy();
        rules = new Rule[2];
        rules[0] = new PlayerOutRule();
        rules[1] = new ChangeStrikeRule();
    }

    @Test
    public void testWithHighProbablityOfEachPlayerGettingOut() throws PlayerNotFoundException {
        players.add(new Player("Kirat Boli", Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 93.0), 0, 0, false));
        players.add(new Player("NS Nodhi", Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 93.0), 0, 0, false));
        players.add(new Player("R Rumrah", Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 93.0), 0, 0, false));
        players.add(new Player("Shashi Henra", Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 93.0), 0, 0, false));
        status = new MatchStatus(players.get(0), players.get(1), 0, 4, 0, 40, false, 0);
        match.simulate(players, runStrategy, rules);
        for (Player player : players) {
            assertTrue(player.isOut());
        }
    }
}