package commentary;

import config.Config;
import models.Player;
import models.ScoreBoard;
import view.Logger;

import java.util.List;

import static helper.CricketSimulatorConstants.*;

public class Commentary {
    Logger logger;
    ScoreBoard scoreBoard;
    Config config;

    public Commentary(ScoreBoard scoreBoard, Config config) {
        this.scoreBoard = scoreBoard;
        logger = new Logger();
        this.config = config;
    }

    public void generateOverMessage() {
        if (scoreBoard.isOverStarts()) {
            int oversLeft = Integer.parseInt(config.getValue("OVERS")) - (scoreBoard.getCurrentBallsPlayed() / BALLS_PER_OVER);
            int runsNeededToWin = scoreBoard.getCurrentRunsToWin();
            logger.logOverMessage(oversLeft, getSuffixString(oversLeft), runsNeededToWin);
        }
    }

    public void generateBallMessage() {
        int overs = scoreBoard.getCurrentBallsPlayed() / BALLS_PER_OVER;
        int ballsCountOfCurrentOverModulo = scoreBoard.getCurrentBallsPlayed() % BALLS_PER_OVER;
        if (ballsCountOfCurrentOverModulo == 0) {
            ballsCountOfCurrentOverModulo = BALLS_PER_OVER;
            overs--;
        }
        if (scoreBoard.getCurrentRunCount() == OUT)
            logger.logPlayerOutMessage(overs, ballsCountOfCurrentOverModulo, scoreBoard.getCurrentStriker().getName());
        else
            logger.logBallMessage(overs, ballsCountOfCurrentOverModulo, scoreBoard.getCurrentStriker().getName(), scoreBoard.getCurrentRunCount(), getSuffixString(scoreBoard.getCurrentRunCount()));
    }

    public void generateMatchSummary(List<Player> players, String playingTeam) {
        generateResult(playingTeam);
        generatePlayerScores(players);
    }

    private void generateResult(String playingTeam) {
        if (scoreBoard.isPlayingTeamWon())
            generateWonMessage(playingTeam);
        else
            generateLostMessage(playingTeam);
    }

    public void generateWonMessage(String playingTeam) {
        int totalRunsNeedToWin = Integer.parseInt(config.getValue("RUNS_NEEDED_TO_WIN"));
        logger.logWonMessage(playingTeam, scoreBoard.getCurrentWicketLeft(), getSuffixString(scoreBoard.getCurrentWicketLeft()), totalRunsNeedToWin - scoreBoard.getCurrentBallsPlayed(), getSuffixString(totalRunsNeedToWin - scoreBoard.getCurrentBallsPlayed()));
    }

    public void generateLostMessage(String playingTeam) {
        logger.logLostMessage(playingTeam, scoreBoard.getCurrentRunsToWin(), getSuffixString(scoreBoard.getCurrentRunsToWin()));
    }

    public void generatePlayerScores(List<Player> players) {
        players.forEach(player -> logger.logPlayerScore(player.getName(), player.getTotalRuns(), getPlayerOnCreaseSuffix(player, scoreBoard), player.getTotalBallsPlayed(), getSuffixString(player.getTotalBallsPlayed())));
    }

    private String getPlayerOnCreaseSuffix(Player player, ScoreBoard scoreBoard) {
        return scoreBoard.onCrease(player) ? SUFFIX_FOR_ON_CREASE_PLAYER : "";
    }

    private String getSuffixString(int count) {
        return count <= 1 ? "" : SUFFIX_FOR_COUNT;
    }
}
