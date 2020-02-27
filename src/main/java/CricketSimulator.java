import commentary.Commentary;
import gamestrategy.GameStrategy;
import helper.Initializer;
import models.Match;
import models.Player;
import rules.Rule;

import java.io.IOException;
import java.util.List;

public class CricketSimulator {
    public static void main(String[] args) throws IOException {
        Initializer initializer = new Initializer();
        List<Player> players = initializer.createPlayers();
        Match match = initializer.createMatch();
        Rule[] rules = initializer.createRules();
        GameStrategy gameStrategy = initializer.createGameStrategy();
        Commentary commentary = initializer.createCommentary();

        match.simulate(players, gameStrategy, rules, commentary);
    }
}

