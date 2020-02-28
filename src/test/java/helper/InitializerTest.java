package helper;

import config.Config;
import models.Player;
import org.junit.Before;
import org.junit.Test;
import rules.Rule;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class InitializerTest {
    Initializer initializer;
    List<Player> players;
    Rule[] rules;
    Config config;

    @Before
    public void init() throws IOException {
        initializer = new Initializer();
        config = new Config();
        config.loadProperties();
    }

    private int getNoOfPlayers() {
        return Integer.parseInt(config.getValue("NO_OF_PLAYERS"));
    }

    @Test
    public void testShouldVerifyPlayersWhenInitializePlayersCalled() {
        players = initializer.createPlayers();
        for (int playerIndex = 0; playerIndex < getNoOfPlayers(); playerIndex++)
            assertEquals(config.getValue("PLAYER_" + (playerIndex + 1)), players.get(playerIndex).getName());
    }

    @Test
    public void testShouldVerifyRulesWhenCreateRulesCalled() {
        rules = initializer.createRules();
        assertNotNull(rules[0]);
        assertNotNull(rules[1]);
    }
}
