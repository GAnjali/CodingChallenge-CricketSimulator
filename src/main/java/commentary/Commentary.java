package commentary;

import models.Player;
import models.ScoreBoard;

import java.util.List;

public class Commentary {
    public void displayOverCommentary(ScoreBoard scoreBoard) {
        int oversLeft = 4 - (scoreBoard.getCurrentBallsPlayed() / 6);
        int runsNeededToWin = scoreBoard.getCurrentRunsToWin();
        System.out.print("\n\n" + oversLeft + " over" + getSuffixString(oversLeft) + " left. " + runsNeededToWin + " runs to win\n");
    }

    private String getSuffixString(int count) {
        if (count <= 1)
            return "";
        return "s";
    }

    public void displayBallCommentary(ScoreBoard scoreBoard) {
        int overs = scoreBoard.getCurrentBallsPlayed() / 6;
        int ballsCountOfCurrentOver = scoreBoard.getCurrentBallsPlayed() % 6;
        if (ballsCountOfCurrentOver == 0 && scoreBoard.getCurrentBallsPlayed() != 0) {
            ballsCountOfCurrentOver = 6;
            overs = overs - 1;
        }
        System.out.print("\n" + overs + "." + ballsCountOfCurrentOver + " " + scoreBoard.getCurrentStriker().getName() + " scores " + scoreBoard.getCurrentRunCount() + " run" + getSuffixString(scoreBoard.getCurrentRunCount()));
    }

    public void displayWonCommentary(String playingTeam, ScoreBoard scoreBoard) {
        System.out.print("\n\n" + playingTeam + " won by " + scoreBoard.getCurrentWicketLeft() + " wicket" + getSuffixString(scoreBoard.getCurrentWicketLeft()) + " and " + (40 - scoreBoard.getCurrentBallsPlayed()) + " ball" + getSuffixString(scoreBoard.getCurrentBallsPlayed()) + " remaining");
    }

    public void displayLostCommentary(String playingTeam, ScoreBoard scoreBoard) {
        System.out.print("\n\n" + playingTeam + " Lost by " + scoreBoard.getCurrentRunsToWin() + " run needed to win and " + (40 - scoreBoard.getCurrentBallsPlayed()) + " ball" + getSuffixString(scoreBoard.getCurrentBallsPlayed()) + " remaining");
    }

    public void displayPlayersScores(List<Player> players, ScoreBoard scoreBoard) {
        for (Player player : players) {
            System.out.print("\n" + player.getName() + " - " + player.getTotalRuns() + getPlayerOnCreaseSuffix(player, scoreBoard) + " (" + player.getTotalBallsPlayed() + " balls)");
        }
    }

    private String getPlayerOnCreaseSuffix(Player player, ScoreBoard scoreBoard) {
        if (!isPlayerOnCrease(player, scoreBoard))
            return "";
        return "*";
    }

    private boolean isPlayerOnCrease(Player player, ScoreBoard scoreBoard) {
        return (player.equals(scoreBoard.getCurrentStriker()) || player.equals(scoreBoard.getCurrentNonStriker()));
    }
}
