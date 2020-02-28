package commentary;

import models.Player;
import models.ScoreBoard;
import view.OutputDriver;

import java.util.List;

import static helper.CricketSimulatorConstants.BALLS_PER_OVER;
import static helper.CricketSimulatorConstants.OUT;

public class Commentary {
    OutputDriver outputDriver = new OutputDriver();

    public void generateOverMessage(ScoreBoard scoreBoard) {
        if (scoreBoard.isOverStarts()) {
            int oversLeft = 4 - (scoreBoard.getCurrentBallsPlayed() / BALLS_PER_OVER);
            int runsNeededToWin = scoreBoard.getCurrentRunsToWin();
            outputDriver.printOverMessage(oversLeft, getSuffixString(oversLeft), runsNeededToWin);
        }
    }

    public void generateBallByBallMessage(ScoreBoard scoreBoard) {
        int overs = scoreBoard.getCurrentBallsPlayed() / BALLS_PER_OVER;
        int ballsCountOfCurrentOver = scoreBoard.getCurrentBallsPlayed() % BALLS_PER_OVER;
        if (ballsCountOfCurrentOver == 0 && scoreBoard.getCurrentBallsPlayed() != 0) {
            ballsCountOfCurrentOver = BALLS_PER_OVER;
            overs = overs - 1;
        }
        if (scoreBoard.getCurrentRunCount() == OUT)
            outputDriver.printOutMessage(overs, ballsCountOfCurrentOver, scoreBoard.getCurrentStriker().getName());
        else
            outputDriver.printBallByBallMessage(overs, ballsCountOfCurrentOver, scoreBoard.getCurrentStriker().getName(), scoreBoard.getCurrentRunCount(), getSuffixString(scoreBoard.getCurrentRunCount()));
    }

    public void generateMatchSummary(ScoreBoard scoreBoard, List<Player> players, String playingTeam) {
        generateResult(scoreBoard, playingTeam);
        generatePlayerScores(players, scoreBoard);
    }

    private void generateResult(ScoreBoard scoreBoard, String playingTeam) {
        if (scoreBoard.isPlayingTeamWon())
            generateWonMessage(playingTeam, scoreBoard);
        else
            generateLostMessage(playingTeam, scoreBoard);
    }

    public void generateWonMessage(String playingTeam, ScoreBoard scoreBoard) {
        outputDriver.printWonMessage(playingTeam, scoreBoard.getCurrentWicketLeft(), getSuffixString(scoreBoard.getCurrentWicketLeft()), 40 - scoreBoard.getCurrentBallsPlayed(), getSuffixString(40 - scoreBoard.getCurrentBallsPlayed()));
    }

    public void generateLostMessage(String playingTeam, ScoreBoard scoreBoard) {
        outputDriver.printLostMessage(playingTeam, scoreBoard.getCurrentRunsToWin(), getSuffixString(scoreBoard.getCurrentRunsToWin()), 40 - scoreBoard.getCurrentBallsPlayed(), getSuffixString(40 - scoreBoard.getCurrentBallsPlayed()));
    }

    public void generatePlayerScores(List<Player> players, ScoreBoard scoreBoard) {
        players.forEach(player -> outputDriver.printPlayerScore(player.getName(), player.getTotalRuns(), getPlayerOnCreaseSuffix(player, scoreBoard), player.getTotalBallsPlayed(), getSuffixString(player.getTotalBallsPlayed())));
    }

    private String getPlayerOnCreaseSuffix(Player player, ScoreBoard scoreBoard) {
        return scoreBoard.onCrease(player) ? "*" : "";
    }

    private String getSuffixString(int count) {
        return count <= 1 ? "" : "s";
    }
}
