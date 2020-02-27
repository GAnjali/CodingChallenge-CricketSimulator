package view;

import static helper.CricketSimulatorConstants.*;

public class OutputDriver {
    public void print(String message) {
        System.out.print(message);
    }

    public void printOverMessage(int remainingOvers, String overSuffix, int runsNeededToWin) {
        print(String.format(OVER_MESSAGE_TEMPLATE, remainingOvers, overSuffix, runsNeededToWin));
    }

    public void printBallByBallMessage(int overs, int ballsCountOfCurrentOver, String playerName, int currentRunCount, String runSuffix) {
        print(String.format(BALL_MESSAGE_TEMPLATE, overs, ballsCountOfCurrentOver, playerName, currentRunCount, runSuffix));
    }

    public void printWonMessage(String team, int remainingWickets, String wicketSuffix, int remainingBalls, String ballSuffix) {
        print(String.format(WON_MESSAGE_TEMPLATE, team, remainingWickets, wicketSuffix, remainingBalls, ballSuffix));
    }

    public void printLostMessage(String team, int runsNeededToWin, String runSuffix, int remainingBalls, String ballSuffix) {
        print(String.format(LOST_MESSAGE_TEMPLATE, team, runsNeededToWin, runSuffix, remainingBalls, ballSuffix));
    }

    public void printPlayerScore(String name, int runs, String playerOnCreaseSuffix, int ballsPlayed, String ballSuffix) {
        print(String.format(PLAYER_SCORE_TEMPLATE, name, runs, playerOnCreaseSuffix, ballsPlayed, ballSuffix));
    }
}
