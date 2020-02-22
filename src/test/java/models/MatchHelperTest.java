package models;

import org.junit.Before;
import org.junit.Test;
import rules.Rule;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MatchHelperTest {

    MatchHelper matchHelper;
    Match match;
    List<Player> players;
    Rule[] rules;

    @Before
    public void init() {
        matchHelper = new MatchHelper();
        match = matchHelper.createMatch();
    }

    @Test
    public void testShouldVerifyPlayersWhenInitializePlayersCalled() {
        players = matchHelper.createPlayers();
        assertEquals("Kirat Boli", players.get(0).getName());
        assertEquals("NS Nodhi", players.get(1).getName());
        assertEquals("R Rumrah", players.get(2).getName());
        assertEquals("Shashi Henra", players.get(3).getName());
    }

    @Test
    public void testShouldVerifyRulesWhenCreateRulesCalled() {
        rules = matchHelper.createRules();
        assertNotNull(rules[0]);
        assertNotNull(rules[1]);
    }

    @Test
    public void testShouldVerifyMatchWhenCreateMatchCalled() {
        match = matchHelper.createMatch();
        assertNotNull(match);
    }
}
