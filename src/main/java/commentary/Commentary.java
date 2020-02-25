package commentary;

import models.Player;
import models.ScoreBoard;
import view.OutputDriver;

import java.util.List;

public class Commentary {
    OutputDriver outputDriver = new OutputDriver();

    public void generateOverMessage(ScoreBoard scoreBoard) {
        int oversLeft = 4 - (scoreBoard.getCurrentBallsPlayed() / 6);
        int runsNeededToWin = scoreBoard.getCurrentRunsToWin();
        outputDriver.printOverMessage(oversLeft, getSuffixString(oversLeft), runsNeededToWin);
    }

    public void generateBallByBallMessage(ScoreBoard scoreBoard) {
        int overs = scoreBoard.getCurrentBallsPlayed() / 6;
        int ballsCountOfCurrentOver = scoreBoard.getCurrentBallsPlayed() % 6;
        if (ballsCountOfCurrentOver == 0 && scoreBoard.getCurrentBallsPlayed() != 0) {
            ballsCountOfCurrentOver = 6;
            overs = overs - 1;
        }
        outputDriver.printBallByBallMessage(overs, ballsCountOfCurrentOver, scoreBoard.getCurrentStriker().getName(), scoreBoard.getCurrentRunCount(), getSuffixString(scoreBoard.getCurrentRunCount()));
    }

    public void generateWonMessage(String playingTeam, ScoreBoard scoreBoard) {
        outputDriver.printWonMessage(playingTeam, scoreBoard.getCurrentWicketLeft(), getSuffixString(scoreBoard.getCurrentWicketLeft()), 40 - scoreBoard.getCurrentBallsPlayed(), getSuffixString(40 - scoreBoard.getCurrentBallsPlayed()));
    }

    public void generateLostMessage(String playingTeam, ScoreBoard scoreBoard) {
        outputDriver.printLostMessage(playingTeam, scoreBoard.getCurrentRunsToWin(), getSuffixString(scoreBoard.getCurrentRunsToWin()), 40 - scoreBoard.getCurrentBallsPlayed(), getSuffixString(40 - scoreBoard.getCurrentBallsPlayed()));
    }

    public void generatePlayerScores(List<Player> players, ScoreBoard scoreBoard) {
        for (Player player : players) {
            outputDriver.printPlayerScore(player.getName(), player.getTotalRuns(), getPlayerOnCreaseSuffix(player, scoreBoard), player.getTotalBallsPlayed(), getSuffixString(player.getTotalBallsPlayed()));
        }
    }

    private String getPlayerOnCreaseSuffix(Player player, ScoreBoard scoreBoard) {
        return scoreBoard.onCrease(player) ? "*" : "";
    }

    private String getSuffixString(int count) {
        return count <= 1 ? "" : "s";
    }
}
