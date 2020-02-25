package view;

public class OutputDriver {
    public void print(String message) {
        System.out.print(message);
    }

    public void printOverMessage(int remainingOvers, String overSuffix, int runsNeededToWin) {
        print("\n\n" + remainingOvers + " over" + overSuffix + " left. " + runsNeededToWin + " runs to win\n");
    }

    public void printBallByBallMessage(int overs, int ballsCountOfCurrentOver, String playerName, int currentRunCount, String runSuffix) {
        print("\n" + overs + "." + ballsCountOfCurrentOver + " " + playerName + " scores " + currentRunCount + " run" + runSuffix);
    }

    public void printWonMessage(String team, int remainingWickets, String wicketSuffix, int remainingBalls, String ballSuffix) {
        print("\n\n" + team + " won by " + remainingWickets + " wicket" + wicketSuffix + " and " + remainingBalls + " ball" + ballSuffix + " remaining");
    }

    public void printLostMessage(String team, int runsNeededToWin, String runSuffix, int remainingBalls, String ballSuffix) {
        print("\n\n" + team + " Lost by " + runsNeededToWin + " run" + runSuffix + " needed to win and " + remainingBalls + " ball" + ballSuffix + " remaining");
    }

    public void printPlayerScore(String name, int runs, String playerOnCreaseSuffix, int ballsPlayed, String ballSuffix) {
        print("\n" + name + " - " + runs + playerOnCreaseSuffix + " (" + ballsPlayed + " ball" + ballSuffix + ")");
    }
}
