package helper;

import commentary.Commentary;
import config.Config;
import gamestrategy.GameStrategy;
import gamestrategy.RandomWeightedGameStrategy;
import models.Match;
import models.Player;
import models.ScoreBoard;
import rules.ChangeStrikeRule;
import rules.PlayerOutRule;
import rules.Rule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Initializer {
    Config config;

    public Initializer() throws IOException {
        config = new Config();
        config.loadProperties();
    }

    public List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        int noOfPlayers = Integer.parseInt(config.getValue("NO_OF_PLAYERS"));
        for (int playerIndex = 1; playerIndex <= noOfPlayers; playerIndex++)
            players.add(new Player(config.getValue("PLAYER_" + playerIndex), getFormattedList("PROBABILITY_" + playerIndex), 0, 0, false));
        return players;
    }

    private List<Double> getFormattedList(String probability) {
        String[] probabilityItems = config.getValue(probability).split(",");
        List<Double> requiredFormatOfProbability = new ArrayList<>();
        for (String item : probabilityItems)
            requiredFormatOfProbability.add(Double.parseDouble(item));
        return requiredFormatOfProbability;
    }

    public Rule[] createRules() {
        return new Rule[]{new PlayerOutRule(), new ChangeStrikeRule()};
    }

    public GameStrategy createGameStrategy() {
        return new RandomWeightedGameStrategy();
    }

    public Commentary createCommentary(ScoreBoard scoreBoard) throws IOException {
        return new Commentary(scoreBoard);
    }

    public ScoreBoard createInitialScoreBoard(List<Player> players) {
        return new ScoreBoard(players.get(0), players.get(1), 0, Integer.parseInt(config.getValue("WICKETS")), 0, Integer.parseInt(config.getValue("RUNS_NEEDED_TO_WIN")), false);
    }

    public Match createMatch(List<Player> players, GameStrategy gameStrategy, Rule[] rules, ScoreBoard scoreBoard, Commentary commentary) {
        return new Match(config.getValue("PLAYING_TEAM"), config.getValue("OPPOSING_TEAM"), Integer.parseInt(config.getValue("OVERS")), players, gameStrategy, rules, scoreBoard, commentary);
    }
}
