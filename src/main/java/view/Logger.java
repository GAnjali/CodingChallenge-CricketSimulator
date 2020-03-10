package view;

import static helper.CricketSimulatorConstants.*;

public interface Logger {
    void log(String message);

    default void logOverMessage(int remainingOvers, String overSuffix, int runsNeededToWin) {
        log(String.format(OVER_MESSAGE_TEMPLATE, remainingOvers, overSuffix, runsNeededToWin));
    }

    default void logBallMessage(int overs, int ballsCountOfCurrentOver, String playerName, int currentRunCount, String runSuffix) {
        log(String.format(BALL_MESSAGE_TEMPLATE, overs, ballsCountOfCurrentOver, playerName, currentRunCount, runSuffix));
    }

    default void logWonMessage(String team, int remainingWickets, String wicketSuffix, int remainingBalls, String ballSuffix) {
        log(String.format(WON_MESSAGE_TEMPLATE, team, remainingWickets, wicketSuffix, remainingBalls, ballSuffix));
    }

    default void logLostMessage(String team, int runsNeededToWin, String runSuffix) {
        log(String.format(LOST_MESSAGE_TEMPLATE, team, runsNeededToWin, runSuffix));
    }

    default void logPlayerScore(String name, int runs, String playerOnCreaseSuffix, int ballsPlayed, String ballSuffix) {
        log(String.format(PLAYER_SCORE_TEMPLATE, name, runs, playerOnCreaseSuffix, ballsPlayed, ballSuffix));
    }

    default void logPlayerOutMessage(int overs, int ballsCountOfCurrentOver, String name) {
        log(String.format(PLAYER_OUT_MESSAGE_TEMPLATE, overs, ballsCountOfCurrentOver, name));
    }
}
