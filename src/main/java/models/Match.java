package models;

import commentary.Commentary;
import gamestrategy.GameStrategy;
import rules.Rule;

import java.util.List;

import static helper.CricketSimulatorConstants.BALLS_PER_OVER;

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

    public int getWickets() {
        return wickets;
    }

    public int getRunNeededToWin() {
        return runNeededToWin;
    }

    public void simulate(List<Player> players, GameStrategy gameStrategy, Rule[] rules, ScoreBoard scoreBoard, Commentary commentary) {
        int totalScore = 0;
        while (!isMatchCompleted(totalScore, scoreBoard)) {
            commentary.generateOverMessage();
            int scoredRuns = gameStrategy.getScoredRuns(scoreBoard.getCurrentStriker());
            scoreBoard.updateScoreBoard(scoredRuns);
            commentary.generateBallMessage();
            applyRules(scoreBoard, players, rules);
        }
        commentary.generateMatchSummary(players, this.playingTeam);
    }

    private boolean isMatchCompleted(int totalScore, ScoreBoard scoreBoard) {
        return scoreBoard.getCurrentBallsPlayed() >= getTotalBalls() || scoreBoard.getCurrentWicketLeft() == 0 || totalScore > scoreBoard.getCurrentRunsToWin();
    }

    private int getTotalBalls() {
        return this.overs * BALLS_PER_OVER;
    }

    private void applyRules(ScoreBoard scoreBoard, List<Player> players, Rule[] rules) {
        for (Rule rule : rules) {
            rule.processScoreBoard(scoreBoard, players);
        }
    }
}
