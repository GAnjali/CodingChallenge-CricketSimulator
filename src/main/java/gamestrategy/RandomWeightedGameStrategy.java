package gamestrategy;

import exceptions.PlayerNotFoundException;
import models.Player;
import utils.MatchUtils;

import java.util.List;
import java.util.Random;

public class RandomWeightedGameStrategy implements GameStrategy {
    private MatchUtils matchUtils = new MatchUtils();

    public int getScoredRuns(Player strikePlayer, List<Player> players) throws PlayerNotFoundException {
        double randomNumber = generateRandomNumberForPlayer(strikePlayer, players);
        List<Double> playerProbability = strikePlayer.getProbability();
        return getRandomScore(playerProbability, randomNumber);
    }

    private double generateRandomNumberForPlayer(Player strikePlayer, List<Player> players) throws PlayerNotFoundException {
        int playerIndex = matchUtils.getPlayerPosition(strikePlayer, players);
        double range = getSum(players.get(playerIndex).getProbability());
        return getRandomNumberInRange((int) range);
    }

    private double getRandomNumberInRange(int range) {
        Random random = new Random();
        return random.nextInt(range + 1);
    }

    private double getSum(List<Double> probability) {
        return probability.stream().mapToDouble(Double::doubleValue).sum();
    }

    private int getRandomScore(List<Double> playerProbability, double randomNumber) {
        double weightSum = 0;
        for (int probabilityIndex = 0; probabilityIndex < playerProbability.size(); probabilityIndex++) {
            weightSum += playerProbability.get(probabilityIndex);
            if (randomNumber <= weightSum) {
                if (probabilityIndex == playerProbability.size() - 1)
                    break;
                return probabilityIndex;
            }
        }
        return -1;
    }
}
