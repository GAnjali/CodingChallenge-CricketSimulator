package helper;

import commentary.Commentary;
import gamestrategy.RandomWeightedGameStrategy;
import models.Match;
import models.Player;
import rules.ChangeStrikeRule;
import rules.PlayerOutRule;
import rules.Rule;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static helper.CricketSimulatorConstants.CONFIG_PATH;

public class MatchHelper {
    Properties properties;

    public MatchHelper() throws IOException {
        loadProperties();
    }

    private void loadProperties() throws IOException {
        InputStream input = new FileInputStream(CONFIG_PATH);
        properties = new Properties();
        properties.load(input);
    }

    public List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(new Player(properties.getProperty("Player1"), getFormattedList("Probability1"), 0, 0, false));
        players.add(new Player(properties.getProperty("Player2"), getFormattedList("Probability2"), 0, 0, false));
        players.add(new Player(properties.getProperty("Player3"), getFormattedList("Probability3"), 0, 0, false));
        players.add(new Player(properties.getProperty("Player4"), getFormattedList("Probability4"), 0, 0, false));
        return players;
    }

    private List<Double> getFormattedList(String probability) {
        String[] probabilityItems = properties.getProperty(probability).split(",");
        List<Double> requiredFormatOfProbability = new ArrayList<>();
        for (String item : probabilityItems)
            requiredFormatOfProbability.add(Double.parseDouble(item));
        return requiredFormatOfProbability;
    }

    public Match createMatch() {
        return new Match(properties.getProperty("PlayingTeam"), properties.getProperty("OpposingTeam"), Integer.parseInt(properties.getProperty("Wickets")), Integer.parseInt(properties.getProperty("RunsNeededToWin")), Integer.parseInt(properties.getProperty("Overs")));
    }

    public Rule[] createRules() {
        return new Rule[]{new PlayerOutRule(), new ChangeStrikeRule()};
    }

    public RandomWeightedGameStrategy createGameStrategy() {
        return new RandomWeightedGameStrategy();
    }

    public Commentary createCommentary() {
        return new Commentary();
    }
}
