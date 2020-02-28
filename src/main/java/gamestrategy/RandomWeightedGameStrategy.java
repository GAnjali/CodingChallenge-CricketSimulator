package gamestrategy;

import models.Player;

import java.util.List;
import java.util.Random;

import static helper.CricketSimulatorConstants.RANGE;

public class RandomWeightedGameStrategy implements GameStrategy {
    private Random random;

    public RandomWeightedGameStrategy(){
        random = new Random();
    }

    @Override
    public int getScoredRuns(Player player) {
        return generateRandomRuns(player.getProbability(), generateRandomNumber());
    }

    private double generateRandomNumber() {
        return random.nextInt(RANGE);
    }

    private int generateRandomRuns(List<Double> runsProbability, double randomNumber) {
        double weight = 0;
        for (int run = 0; run < runsProbability.size(); run++) {
            weight += runsProbability.get(run);
            if (randomNumber < weight) {
                return run;
            }
        }
        return -1;
    }
}
