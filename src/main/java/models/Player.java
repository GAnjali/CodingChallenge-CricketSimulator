package models;

import java.util.List;

public class Player {
    private final String name;
    private final List<Double> probability;
    private int totalRuns;
    private int totalBallsPlayed;
    private boolean isOut;

    public Player(String name, List<Double> probability, int totalRuns, int totalBallsPlayed, boolean isOut) {
        this.name = name;
        this.probability = probability;
        this.totalRuns = totalRuns;
        this.totalBallsPlayed = totalBallsPlayed;
        this.isOut = isOut;
    }

    public String getName() {
        return name;
    }

    public List<Double> getProbability() {
        return probability;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }

    public int getTotalBallsPlayed() {
        return totalBallsPlayed;
    }

    public void setTotalBallsPlayed(int totalBallsPlayed) {
        this.totalBallsPlayed = totalBallsPlayed;
    }

    public boolean isOut() {
        return isOut;
    }

    public void setOut(boolean out) {
        isOut = out;
    }
}
