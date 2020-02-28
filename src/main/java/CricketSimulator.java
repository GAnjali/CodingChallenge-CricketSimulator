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
        Rule[] rules = initializer.createRules();
        GameStrategy gameStrategy = initializer.createGameStrategy();
        ScoreBoard scoreBoard = initializer.createInitialScoreBoard(players);
        Commentary commentary = initializer.createCommentary(scoreBoard);
        Match match = initializer.createMatch(players, gameStrategy, rules, scoreBoard, commentary);

        match.simulate();
    }
}

