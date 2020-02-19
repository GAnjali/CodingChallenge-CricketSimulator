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
}
