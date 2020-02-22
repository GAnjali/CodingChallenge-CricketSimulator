package rules;

import models.Player;
import models.ScoreBoard;

import java.util.List;

public class ChangeStrikeRule implements Rule {
    @Override
    public void processScoreBoard(ScoreBoard scoreBoard, List<Player> players) {
        if (needStrikeChange(scoreBoard)) {
            changeStrike(scoreBoard);
        }
    }

    private boolean needStrikeChange(ScoreBoard scoreBoard) {
        return (isOdd(scoreBoard.getCurrentRunCount()) || isOverComplete(scoreBoard.getCurrentBallsPlayed()));
    }

    private boolean isOverComplete(int ballsPlayed) {
        return ballsPlayed % 6 == 0;
    }

    private boolean isOdd(int run) {
        return run % 2 == 1;
    }

    private void changeStrike(ScoreBoard scoreBoard) {
        Player striker = scoreBoard.getCurrentStriker();
        Player nonStriker = scoreBoard.getCurrentNonStriker();
        scoreBoard.setCurrentStriker(nonStriker);
        scoreBoard.setCurrentNonStriker(striker);
    }
}
