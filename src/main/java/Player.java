import java.util.List;

public class Player {
    private final String name;
    private final List<Double> probability;
    private int totalRuns;
    private int totalBallsPlayed;

    public Player(String name, List<Double> probability, int totalRuns) {
        this.name = name;
        this.probability = probability;
        this.totalRuns = totalRuns;
    }

    public Player(String name, List<Double> probability) {
        this.name = name;
        this.probability = probability;
    }
}
