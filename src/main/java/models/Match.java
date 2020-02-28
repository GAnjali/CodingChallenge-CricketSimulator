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
    private List<Player> players;
    private GameStrategy gameStrategy;
    private Rule[] rules;
    private ScoreBoard scoreBoard;
    private Commentary commentary;

    public Match(String playingTeam, String opposingTeam, int overs, List<Player> players, GameStrategy gameStrategy, Rule[] rules, ScoreBoard scoreBoard, Commentary commentary) {
        this.playingTeam = playingTeam;
        this.opposingTeam = opposingTeam;
        this.overs = overs;
        this.players = players;
        this.gameStrategy = gameStrategy;
        this.rules = rules;
        this.scoreBoard = scoreBoard;
        this.commentary = commentary;
    }

    public void simulate() {
        int totalScore = 0;
        while (!isMatchCompleted(totalScore)) {
            commentary.generateOverMessage();
            int scoredRuns = gameStrategy.getScoredRuns(scoreBoard.getCurrentStriker());
            scoreBoard.updateScoreBoard(scoredRuns);
            commentary.generateBallMessage();
            applyRules();
        }
        commentary.generateMatchSummary(players, this.playingTeam);
    }

    private boolean isMatchCompleted(int totalScore) {
        return scoreBoard.getCurrentBallsPlayed() >= getTotalBalls() || scoreBoard.getCurrentWicketLeft() == 0 || totalScore > scoreBoard.getCurrentRunsToWin();
    }

    private int getTotalBalls() {
        return this.overs * BALLS_PER_OVER;
    }

    private void applyRules() {
        for (Rule rule : rules) {
            rule.perform(scoreBoard, players);
        }
    }
}
