package commentary;

import models.MatchStatus;
import models.Player;

import java.util.List;

public class Commentary {
    public void generateOverCommentary(MatchStatus status) {
        int oversLeft = 4 - (status.getCurrentBallsPlayed() / 6);
        int runsNeededToWin = status.getCurrentRunsToWin();
        System.out.print("\n\n" + oversLeft + " over" + getSuffixString(oversLeft) + " left. " + runsNeededToWin + " runs to win\n");
    }

    private String getSuffixString(int count) {
        if (count <= 1)
            return "";
        return "s";
    }

    public void generateBallCommentary(MatchStatus status) {
        int overs = status.getCurrentBallsPlayed() / 6;
        int ballsCountOfCurrentOver = status.getCurrentBallsPlayed() % 6;
        if (ballsCountOfCurrentOver == 0 && status.getCurrentBallsPlayed() != 0) {
            ballsCountOfCurrentOver = 6;
            overs = overs - 1;
        }
        System.out.print("\n" + overs + "." + ballsCountOfCurrentOver + " " + status.getCurrentStriker().getName() + " scores " + status.getCurrentRunCount() + " run" + getSuffixString(status.getCurrentRunCount()));
    }

    public void wonCommentary(String playingTeam, MatchStatus status) {
        System.out.print("\n\n" + playingTeam + " won by " + status.getCurrentWicketLeft() + " wicket" + getSuffixString(status.getCurrentWicketLeft()) + " and " + (40 - status.getCurrentBallsPlayed()) + " ball" + getSuffixString(status.getCurrentBallsPlayed()) + " remaining");
    }

    public void looseCommentary(String playingTeam, MatchStatus status) {
        System.out.print("\n\n" + playingTeam + " Lost by " + status.getCurrentRunsToWin() + " run needed to win and " + (40 - status.getCurrentBallsPlayed()) + " ball" + getSuffixString(status.getCurrentBallsPlayed()) + " remaining");
    }

    public void playersScores(List<Player> players, MatchStatus status) {
        for (Player player : players) {
            System.out.print("\n" + player.getName() + " - " + player.getTotalRuns() + getPlayerOnCreaseSuffix(player, status) + " (" + player.getTotalBallsPlayed() + " balls)");
        }
    }

    private String getPlayerOnCreaseSuffix(Player player, MatchStatus status) {
        if (!isPlayerOnCrease(player, status))
            return "";
        return "*";
    }

    private boolean isPlayerOnCrease(Player player, MatchStatus status) {
        return (player.equals(status.getCurrentStriker()) || player.equals(status.getCurrentNonStriker()));
    }
}
