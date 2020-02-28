import commentary.Commentary;
import gamestrategy.GameStrategy;
import helper.Initializer;
import models.Match;
import models.Player;
import models.ScoreBoard;
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
        ScoreBoard scoreBoard = initializer.createIntialScoreBoard(players, match);
        Commentary commentary = initializer.createCommentary(scoreBoard);

        match.simulate(players, gameStrategy, rules, scoreBoard, commentary);
    }
}

