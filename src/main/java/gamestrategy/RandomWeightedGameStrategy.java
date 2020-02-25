package gamestrategy;

import models.Player;

import java.util.List;
import java.util.Random;

public class RandomWeightedGameStrategy implements GameStrategy {
    public int getScoredRuns(Player strikePlayer) {
        return generateRandomRuns(strikePlayer.getProbability(), generateRandomNumber(strikePlayer));
    }

    private double generateRandomNumber(Player strikePlayer) {
        Random random = new Random();
        return random.nextInt((int) getSum(strikePlayer.getProbability()) + 1);
    }

    private double getSum(List<Double> probability) {
        return probability.stream().mapToDouble(Double::doubleValue).sum();
    }

    private int generateRandomRuns(List<Double> probability, double randomNumber) {
        double weight = 0;
        for (int run = 0; run < probability.size(); run++) {
            weight += probability.get(run);
            if (randomNumber < weight) {
                return run;
            }
        }
        return -1;
    }
}
