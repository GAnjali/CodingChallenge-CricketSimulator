package rules;

import models.MatchStatus;
import models.Player;

import java.util.List;

public class ChangeStrikeRule implements Rule {

    @Override
    public MatchStatus processStatus(MatchStatus status, List<Player> players) {
        if (needStrikeChange(status)) {
            changeStrike(status);
        }
        return status;
    }

    private boolean needStrikeChange(MatchStatus status) {
        return (isOdd(status.getCurrentRunCount()) || isOverComplete(status.getCurrentBallsPlayed()));
    }

    private boolean isOverComplete(int ballsPlayed) {
        return ballsPlayed % 6 == 0;
    }

    private boolean isOdd(int run) {
        return run % 2 == 1;
    }

    private void changeStrike(MatchStatus status) {
        Player striker = status.getCurrentStriker();
        Player nonStriker = status.getCurrentNonStriker();
        status.setCurrentStriker(nonStriker);
        status.setCurrentNonStriker(striker);
    }
}
