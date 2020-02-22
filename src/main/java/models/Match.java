package models;

import commentary.Commentary;
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

    public void simulate(List<Player> players, RandomWeightedGameStrategy gameStrategy, Rule[] rules, Commentary commentary) {
        int totalScore = 0;
        ScoreBoard scoreBoard = getInitialScoreBoardOfMatch(players);
        while (!isMatchCompleted(scoreBoard)) {
            displayOverCommentary(scoreBoard, commentary);
            int scoredRuns = gameStrategy.getScoredRuns(scoreBoard.getCurrentStriker());
            updateScoreBoard(scoredRuns, scoreBoard);
            commentary.displayBallCommentary(scoreBoard);
            applyRules(scoreBoard, players, rules);
            if (scoreBoard.getCurrentWicketLeft() == 0 || totalScore > scoreBoard.getCurrentRunsToWin())
                break;
        }
        displayMatchSummary(scoreBoard, players, commentary);
    }

    private ScoreBoard getInitialScoreBoardOfMatch(List<Player> players) {
        return new ScoreBoard(players.get(0), players.get(1), 0, this.wickets, 0, this.runNeededToWin, false);
    }

    private boolean isMatchCompleted(ScoreBoard scoreBoard) {
        return scoreBoard.getCurrentBallsPlayed() >= getTotalBalls();
    }

    private int getTotalBalls() {
        return this.overs * 6;
    }

    private void displayOverCommentary(ScoreBoard scoreBoard, Commentary commentary) {
        if (isOverStarts(scoreBoard))
            commentary.displayOverCommentary(scoreBoard);//commentary
    }

    private boolean isOverStarts(ScoreBoard scoreBoard) {
        return scoreBoard.getCurrentBallsPlayed() % 6 == 0;
    }

    private void updateScoreBoard(int runsScored, ScoreBoard scoreBoard) {
        if (runsScored == 7) {
            updatescoreBoardWhenStrickerGetsOut(scoreBoard.getCurrentStriker(), scoreBoard);
        } else {
            updatescoreBoardWhenStrikerScoredRuns(runsScored, scoreBoard.getCurrentStriker(), scoreBoard);
        }
        increaseBallCount(scoreBoard.getCurrentStriker(), scoreBoard);
    }

    private void updatescoreBoardWhenStrickerGetsOut(Player striker, ScoreBoard scoreBoard) {
        striker.setOut(true);
        scoreBoard.setCurrentPlayerIsOut(true);
        scoreBoard.setCurrentWicketLeft(scoreBoard.getCurrentWicketLeft() - 1);
    }


    private void updatescoreBoardWhenStrikerScoredRuns(int runsScored, Player striker, ScoreBoard scoreBoard) {
        striker.setTotalRuns(striker.getTotalRuns() + runsScored);
        scoreBoard.setCurrentRunCount(runsScored);
        scoreBoard.setCurrentRunsToWin(scoreBoard.getCurrentRunsToWin() - runsScored);
    }

    private void increaseBallCount(Player striker, ScoreBoard scoreBoard) {
        striker.setTotalBallsPlayed(striker.getTotalBallsPlayed() + 1);
        scoreBoard.setCurrentBallsPlayed(scoreBoard.getCurrentBallsPlayed() + 1);
    }

    private void applyRules(ScoreBoard scoreBoard, List<Player> players, Rule[] rules) {
        for (Rule rule : rules) {
            rule.processScoreBoard(scoreBoard, players);
        }
    }

    private void displayMatchSummary(ScoreBoard scoreBoard, List<Player> players, Commentary commentary) {
        result(scoreBoard, commentary);
        commentary.displayPlayersScores(players, scoreBoard);
    }

    private void result(ScoreBoard scoreBoard, Commentary commentary) {
        if (scoreBoard.getCurrentRunsToWin() <= 0)
            commentary.displayWonCommentary(this.playingTeam, scoreBoard);
        else
            commentary.displayLooseCommentary(this.playingTeam, scoreBoard);
    }
}
