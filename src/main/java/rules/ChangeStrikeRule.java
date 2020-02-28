package rules;

import models.Player;
import models.ScoreBoard;

import java.util.List;

import static helper.CricketSimulatorConstants.*;

public class ChangeStrikeRule implements Rule {
    @Override
    public void perform(ScoreBoard scoreBoard, List<Player> players) {
        if (needStrikeChange(scoreBoard)) {
            changeStrike(scoreBoard);
        }
    }

    private boolean needStrikeChange(ScoreBoard scoreBoard) {
        return ((isOdd(scoreBoard.getCurrentRunCount()) || isOverComplete(scoreBoard.getCurrentBallsPlayed())) && scoreBoard.getCurrentRunCount()!= OUT);
    }

    private boolean isOverComplete(int ballsPlayed) {
        return ballsPlayed % BALLS_PER_OVER == 0;
    }

    private boolean isOdd(int run) {
        return run % 2 == 1;
    }

    private void changeStrike(ScoreBoard scoreBoard) {
        if (scoreBoard.getCurrentNonStriker() != null) {
            Player nextStriker = scoreBoard.getCurrentNonStriker();
            scoreBoard.setCurrentNonStriker(scoreBoard.getCurrentStriker());
            scoreBoard.setCurrentStriker(nextStriker);
        }
    }
}
