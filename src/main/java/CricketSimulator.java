import commentary.Commentary;
import gamestrategy.GameStrategy;
import helper.MatchHelper;
import models.Match;
import models.Player;
import rules.Rule;

import java.util.List;

public class CricketSimulator {
    public static void main(String[] args) {
        MatchHelper matchHelper = new MatchHelper();
        List<Player> players = matchHelper.createPlayers();
        Match match = matchHelper.createMatch();
        Rule[] rules = matchHelper.createRules();
        GameStrategy gameStrategy = matchHelper.createGameStrategy();
        Commentary commentary = matchHelper.createCommentary();

        match.simulate(players, gameStrategy, rules, commentary);
    }
}

