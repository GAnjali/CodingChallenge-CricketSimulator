package models;

import exceptions.PlayerNotFoundException;
import gamestrategy.RandomWeightedGameStrategy;
import rules.Rule;

import java.util.List;

public class Match {
    private final String playingTeam;
    private final String opposingTeam;
    private final int overs;
    private int wickets;
    private int runNeededToWin;

    public Match(String playingTeam, String opposingTeam, int wickets, int runNeededToWin, int overs) {
        this.playingTeam = playingTeam;
        this.opposingTeam = opposingTeam;
        this.wickets = wickets;
        this.runNeededToWin = runNeededToWin;
        this.overs = overs;
    }

    public String getPlayingTeam() {
        return playingTeam;
    }

    public String getOpposingTeam() {
        return opposingTeam;
    }

    public int getWickets() {
        return wickets;
    }

    public int getRunNeededToWin() {
        return runNeededToWin;
    }

    public int getOvers() {
        return overs;
    }

    public void simulate(List<Player> players, RandomWeightedGameStrategy gameStrategy, Rule[] rules) throws PlayerNotFoundException {
        int totalScore = 0;
        MatchStatus status = getInitialStatusOfMatch(players);
        while (!isMatchCompleted(status)) {
            Player striker = status.getCurrentStriker();
            int scoredRuns = gameStrategy.getScoredRuns(striker);
            updateStatus(scoredRuns, striker, status);
            status = processNextMove(status, players, rules);
            if (status.getCurrentWicketLeft() == 0 || totalScore > status.getCurrentRunsToWin())
                break;
        }
    }

    private MatchStatus getInitialStatusOfMatch(List<Player> players) {
        return new MatchStatus(players.get(0), players.get(1), 0, 4, 0, 40, false, 0);
    }

    private boolean isMatchCompleted(MatchStatus status) {
        return status.getCurrentBallsPlayed() >= getTotalBalls();
    }

    private void updateStatus(int runsScored, Player striker, MatchStatus status) {
        if (runsScored == 7) {
            updateStatusWhenStrickerGetsOut(striker, status);
        } else {
            updateStatusWhenStrikerScoredRuns(runsScored, striker, status);
        }
        increaseBallCount(striker, status);
    }

    private void updateStatusWhenStrickerGetsOut(Player striker, MatchStatus status) {
        striker.setOut(true);
        status.setCurrentPlayerIsOut(true);
        status.setCurrentWicketLeft(status.getCurrentWicketLeft() - 1);
    }


    private void updateStatusWhenStrikerScoredRuns(int runsScored, Player striker, MatchStatus status) {
        striker.setTotalRuns(striker.getTotalRuns() + runsScored);
        status.setCurrentRunCount(status.getCurrentRunCount() + runsScored);
        status.setCurrentRunsToWin(status.getCurrentRunsToWin() - runsScored);
    }

    private void increaseBallCount(Player striker, MatchStatus status) {
        striker.setTotalBallsPlayed(striker.getTotalBallsPlayed() + 1);
        status.setCurrentBallsPlayed(status.getCurrentBallsPlayed() + 1);
    }

    private MatchStatus processNextMove(MatchStatus status, List<Player> players, Rule[] rules) throws PlayerNotFoundException {
        for (Rule rule : rules) {
            status = rule.processStatus(status, players);
        }
        return status;
    }

    private int getTotalBalls() {
        return this.overs * 6;
    }
}
