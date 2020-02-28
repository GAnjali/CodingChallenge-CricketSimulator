package models;

import static helper.CricketSimulatorConstants.*;

public class ScoreBoard {
    private Player currentStriker;
    private Player currentNonStriker;
    private int currentRunCount;
    private int currentWicketLeft;
    private int currentBallsPlayed;
    private int currentRunsToWin;
    private boolean currentPlayerIsOut;

    public ScoreBoard(Player currentStriker, Player currentNonStriker, int currentRunCount, int currentWicketLeft,
                      int currentBallsPlayed, int currentRunsToWin, boolean currentPlayerIsOut) {
        this.currentStriker = currentStriker;
        this.currentNonStriker = currentNonStriker;
        this.currentRunCount = currentRunCount;
        this.currentWicketLeft = currentWicketLeft;
        this.currentBallsPlayed = currentBallsPlayed;
        this.currentRunsToWin = currentRunsToWin;
        this.currentPlayerIsOut = currentPlayerIsOut;
    }

    public Player getCurrentStriker() {
        return currentStriker;
    }

    public void setCurrentStriker(Player currentStriker) {
        this.currentStriker = currentStriker;
    }

    public Player getCurrentNonStriker() {
        return currentNonStriker;
    }

    public void setCurrentNonStriker(Player currentNonStriker) {
        this.currentNonStriker = currentNonStriker;
    }

    public int getCurrentRunCount() {
        return currentRunCount;
    }

    public void setCurrentRunCount(int currentRunCount) {
        this.currentRunCount = currentRunCount;
    }

    public int getCurrentWicketLeft() {
        return currentWicketLeft;
    }

    public void setCurrentWicketLeft(int currentWicketLeft) {
        this.currentWicketLeft = currentWicketLeft;
    }

    public int getCurrentBallsPlayed() {
        return currentBallsPlayed;
    }

    public void setCurrentBallsPlayed(int currentBallsPlayed) {
        this.currentBallsPlayed = currentBallsPlayed;
    }

    public int getCurrentRunsToWin() {
        return currentRunsToWin;
    }

    public void setCurrentRunsToWin(int currentRunsToWin) {
        this.currentRunsToWin = currentRunsToWin;
    }

    public boolean isCurrentPlayerIsOut() {
        return currentPlayerIsOut;
    }

    public void setCurrentPlayerIsOut(boolean currentPlayerIsOut) {
        this.currentPlayerIsOut = currentPlayerIsOut;
    }

    public boolean onCrease(Player player) {
        return (player.equals(this.getCurrentStriker()) || player.equals(this.getCurrentNonStriker()));
    }

    public void updateScoreBoard(int runsScored) {
        if (runsScored == OUT) {
            updateScoreBoardWhenStrikerGetsOut(runsScored);
        } else {
            updateScoreBoardWhenStrikerScoredRuns(runsScored);
        }
        increaseBallCount();
    }

    private void updateScoreBoardWhenStrikerGetsOut(int runsScored) {
        this.getCurrentStriker().setOut(true);
        this.setCurrentPlayerIsOut(true);
        this.setCurrentRunCount(runsScored);
        this.setCurrentWicketLeft(this.getCurrentWicketLeft() - 1);
    }

    private void updateScoreBoardWhenStrikerScoredRuns(int runsScored) {
        this.getCurrentStriker().setTotalRuns(this.getCurrentStriker().getTotalRuns() + runsScored);
        this.setCurrentRunCount(runsScored);
        this.setCurrentRunsToWin(this.getCurrentRunsToWin() - runsScored);
    }

    private void increaseBallCount() {
        this.getCurrentStriker().setTotalBallsPlayed(this.getCurrentStriker().getTotalBallsPlayed() + 1);
        this.setCurrentBallsPlayed(this.getCurrentBallsPlayed() + 1);
    }

    public boolean isOverStarts() {
        return this.getCurrentBallsPlayed() % BALLS_PER_OVER == 0;
    }

    public boolean isPlayingTeamWon() {
        return this.getCurrentRunsToWin() <= 0;
    }
}
