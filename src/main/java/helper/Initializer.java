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
        for (int playerIndex = 1; playerIndex <= getNoOfPlayers(); playerIndex++)
            players.add(new Player(config.getValue("PLAYER_" + playerIndex), getFormattedList("PROBABILITY_" + playerIndex), 0, 0, false));
        return players;
    }

    private int getNoOfPlayers() {
        return Integer.parseInt(config.getValue("NO_OF_PLAYERS"));
    }

    private List<Double> getFormattedList(String probability) {
        String[] probabilityItems = config.getValue(probability).split(",");
        List<Double> requiredFormatOfProbability = new ArrayList<>();
        for (String item : probabilityItems)
            requiredFormatOfProbability.add(Double.parseDouble(item));
        return requiredFormatOfProbability;
    }

    public Match createMatch() {
        return new Match(config.getValue("PLAYING_TEAM"), config.getValue("OPPOSING_TEAM"), Integer.parseInt(config.getValue("WICKETS")), Integer.parseInt(config.getValue("RUNS_NEEDED_TO_WIN")), Integer.parseInt(config.getValue("OVERS")));
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

    public ScoreBoard createIntialScoreBoard(List<Player> players, Match match) {
        return new ScoreBoard(players.get(0), players.get(1), 0, match.getWickets(), 0, match.getRunNeededToWin(), false);
    }
}
