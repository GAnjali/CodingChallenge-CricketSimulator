package commentary;

import config.Config;
import models.Player;
import models.ScoreBoard;
import view.OutputDriver;

import java.io.IOException;
import java.util.List;

import static helper.CricketSimulatorConstants.*;

public class Commentary {
    OutputDriver outputDriver;
    ScoreBoard scoreBoard;
    Config config;

    public Commentary(ScoreBoard scoreBoard) throws IOException {
        this.scoreBoard = scoreBoard;
        outputDriver = new OutputDriver();
        config = new Config();
        config.loadProperties();
    }

    public void generateOverMessage() {
        if (scoreBoard.isOverStarts()) {
            int oversLeft = Integer.parseInt(config.getValue("OVERS")) - (scoreBoard.getCurrentBallsPlayed() / BALLS_PER_OVER);
            int runsNeededToWin = scoreBoard.getCurrentRunsToWin();
            outputDriver.printOverMessage(oversLeft, getSuffixString(oversLeft), runsNeededToWin);
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
            outputDriver.printOutMessage(overs, ballsCountOfCurrentOverModulo, scoreBoard.getCurrentStriker().getName());
        else
            outputDriver.printBallByBallMessage(overs, ballsCountOfCurrentOverModulo, scoreBoard.getCurrentStriker().getName(), scoreBoard.getCurrentRunCount(), getSuffixString(scoreBoard.getCurrentRunCount()));
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
        outputDriver.printWonMessage(playingTeam, scoreBoard.getCurrentWicketLeft(), getSuffixString(scoreBoard.getCurrentWicketLeft()), totalRunsNeedToWin - scoreBoard.getCurrentBallsPlayed(), getSuffixString(totalRunsNeedToWin - scoreBoard.getCurrentBallsPlayed()));
    }

    public void generateLostMessage(String playingTeam) {
        outputDriver.printLostMessage(playingTeam, scoreBoard.getCurrentRunsToWin(), getSuffixString(scoreBoard.getCurrentRunsToWin()));
    }

    public void generatePlayerScores(List<Player> players) {
        players.forEach(player -> outputDriver.printPlayerScore(player.getName(), player.getTotalRuns(), getPlayerOnCreaseSuffix(player, scoreBoard), player.getTotalBallsPlayed(), getSuffixString(player.getTotalBallsPlayed())));
    }

    private String getPlayerOnCreaseSuffix(Player player, ScoreBoard scoreBoard) {
        return scoreBoard.onCrease(player) ? SUFFIX_FOR_ON_CREASE_PLAYER : "";
    }

    private String getSuffixString(int count) {
        return count <= 1 ? "" : SUFFIX_FOR_COUNT;
    }
}
