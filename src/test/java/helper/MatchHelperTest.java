package helper;

import models.Match;
import models.Player;
import org.junit.Before;
import org.junit.Test;
import rules.Rule;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import static helper.CricketSimulatorConstants.CONFIG_PATH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MatchHelperTest {
    Initializer initializer;
    Match match;
    List<Player> players;
    Rule[] rules;
    Properties properties;

    @Before
    public void init() throws IOException {
        initializer = new Initializer();
        match = initializer.createMatch();
        InputStream input = new FileInputStream(CONFIG_PATH);
        properties = new Properties();
        properties.load(input);
    }

    private int getNoOfPlayers() {
        return Integer.parseInt(properties.getProperty("NO_OF_PLAYERS"));
    }

    @Test
    public void testShouldVerifyPlayersWhenInitializePlayersCalled() {
        players = initializer.createPlayers();
        for (int playerIndex = 0; playerIndex < getNoOfPlayers(); playerIndex++)
            assertEquals(properties.getProperty("PLAYER_" + (playerIndex + 1)), players.get(playerIndex).getName());
    }

    @Test
    public void testShouldVerifyRulesWhenCreateRulesCalled() {
        rules = initializer.createRules();
        assertNotNull(rules[0]);
        assertNotNull(rules[1]);
    }

    @Test
    public void testShouldVerifyMatchWhenCreateMatchCalled() {
        match = initializer.createMatch();
        assertNotNull(match);
    }
}
