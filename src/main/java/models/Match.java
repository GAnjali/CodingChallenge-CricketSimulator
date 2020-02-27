package models;

import commentary.Commentary;
import gamestrategy.GameStrategy;
import rules.Rule;

import java.util.List;

import static helper.CricketSimulatorConstants.NO_OF_BALLS_PER_OVER;

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

    public void simulate(List<Player> players, GameStrategy gameStrategy, Rule[] rules, Commentary commentary) {
        int totalScore = 0;
        ScoreBoard scoreBoard = getInitialScoreBoardOfMatch(players);
        while (!isMatchCompleted(totalScore, scoreBoard)) {
            commentary.generateOverMessage(scoreBoard);
            int scoredRuns = gameStrategy.getScoredRuns(scoreBoard.getCurrentStriker());
            scoreBoard.updateScoreBoard(scoredRuns);
            commentary.generateBallByBallMessage(scoreBoard);
            applyRules(scoreBoard, players, rules);
        }
        commentary.generateMatchSummary(scoreBoard, players, this.playingTeam);
    }

    private ScoreBoard getInitialScoreBoardOfMatch(List<Player> players) {
        return new ScoreBoard(players.get(0), players.get(1), 0, this.wickets, 0, this.runNeededToWin, false);
    }

    private boolean isMatchCompleted(int totalScore, ScoreBoard scoreBoard) {
        return scoreBoard.getCurrentBallsPlayed() >= getTotalBalls() || scoreBoard.getCurrentWicketLeft() == 0 || totalScore > scoreBoard.getCurrentRunsToWin();
    }

    private int getTotalBalls() {
        return this.overs * NO_OF_BALLS_PER_OVER;
    }

    private void applyRules(ScoreBoard scoreBoard, List<Player> players, Rule[] rules) {
        for (Rule rule : rules) {
            rule.processScoreBoard(scoreBoard, players);
        }
    }
}
