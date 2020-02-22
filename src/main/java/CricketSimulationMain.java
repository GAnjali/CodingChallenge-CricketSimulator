import commentary.Commentary;
import gamestrategy.RandomWeightedGameStrategy;
import models.Match;
import models.MatchHelper;
import models.Player;
import rules.Rule;

import java.util.List;

public class CricketSimulationMain {

    public static void main(String[] args) {
        MatchHelper matchHelper = new MatchHelper();
        List<Player> players = matchHelper.createPlayers();
        Match match = matchHelper.createMatch();
        Rule[] rules = matchHelper.createRules();

        RandomWeightedGameStrategy gameStrategy = new RandomWeightedGameStrategy();
        Commentary commentary = new Commentary();

        match.simulate(players, gameStrategy, rules, commentary);
    }
}

