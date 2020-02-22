package helper;

import models.Match;
import models.Player;
import rules.ChangeStrikeRule;
import rules.PlayerOutRule;
import rules.Rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatchHelper {

    public List<Player> createPlayers() {
        List<Player> players = new ArrayList<Player>();
        players.add(new Player("Kirat Boli", Arrays.asList(5.0, 30.0, 25.0, 10.0, 15.0, 1.0, 9.0, 5.0), 0, 0, false));
        players.add(new Player("NS Nodhi", Arrays.asList(10.0, 40.0, 20.0, 5.0, 10.0, 1.0, 4.0, 10.0), 0, 0, false));
        players.add(new Player("R Rumrah", Arrays.asList(20.0, 30.0, 15.0, 5.0, 5.0, 1.0, 4.0, 20.0), 0, 0, false));
        players.add(new Player("Shashi Henra", Arrays.asList(30.0, 25.0, 5.0, 0.0, 5.0, 1.0, 4.0, 30.0), 0, 0, false));
        return players;
    }

    public Match createMatch() {
        return new Match("Bengaluru", "Chennai", 4, 40, 4);
    }

    public Rule[] createRules() {
        Rule[] rules = new Rule[2];
        rules[0] = new PlayerOutRule();
        rules[1] = new ChangeStrikeRule();
        return rules;
    }
}
