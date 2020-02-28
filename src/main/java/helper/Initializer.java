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
import java.util.Properties;

public class Initializer {
    Config config;
    Properties properties;

    public Initializer() throws IOException {
        config = new Config();
        properties = config.loadProperties();
    }

    public List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        for (int playerIndex = 1; playerIndex <= getNoOfPlayers(); playerIndex++)
            players.add(new Player(properties.getProperty("PLAYER_" + playerIndex), getFormattedList("PROBABILITY_" + playerIndex), 0, 0, false));
        return players;
    }

    private int getNoOfPlayers() {
        return Integer.parseInt(properties.getProperty("NO_OF_PLAYERS"));
    }

    private List<Double> getFormattedList(String probability) {
        String[] probabilityItems = properties.getProperty(probability).split(",");
        List<Double> requiredFormatOfProbability = new ArrayList<>();
        for (String item : probabilityItems)
            requiredFormatOfProbability.add(Double.parseDouble(item));
        return requiredFormatOfProbability;
    }

    public Match createMatch() {
        return new Match(properties.getProperty("PLAYING_TEAM"), properties.getProperty("OPPOSING_TEAM"), Integer.parseInt(properties.getProperty("WICKETS")), Integer.parseInt(properties.getProperty("RUNS_NEEDED_TO_WIN")), Integer.parseInt(properties.getProperty("OVERS")));
    }

    public Rule[] createRules() {
        return new Rule[]{new PlayerOutRule(), new ChangeStrikeRule()};
    }

    public GameStrategy createGameStrategy() {
        return new RandomWeightedGameStrategy();
    }

    public Commentary createCommentary(ScoreBoard scoreBoard) {
        return new Commentary(scoreBoard);
    }

    public ScoreBoard createIntialScoreBoard(List<Player> players, Match match) {
        return new ScoreBoard(players.get(0), players.get(1), 0, match.getWickets(), 0, match.getRunNeededToWin(), false);
    }
}
