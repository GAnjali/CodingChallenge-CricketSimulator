import commentary.Commentary;
import exceptions.PlayerNotFoundException;
import gamestrategy.RandomWeightedGameStrategy;
import models.Game;
import models.Match;
import models.Player;
import rules.ChangeStrikeRule;
import rules.PlayerOutRule;
import rules.Rule;

import java.util.List;

public class CricketSimulationMain {

    public static void main(String[] args) throws PlayerNotFoundException {
        Game game = new Game();
        List<Player> players = game.intializePlayers();
        RandomWeightedGameStrategy runStrategy = new RandomWeightedGameStrategy();
        Rule[] rules = new Rule[2];
        rules[0] = new PlayerOutRule();
        rules[1] = new ChangeStrikeRule();
        Match match = game.intializeMatch();
        Commentary commentary = new Commentary();
        match.simulate(players, runStrategy, rules, commentary);
    }
}

