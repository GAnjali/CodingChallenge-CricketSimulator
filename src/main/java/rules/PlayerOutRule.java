package rules;

import models.Player;
import models.ScoreBoard;

import java.util.List;

public class PlayerOutRule implements Rule {
    @Override
    public void perform(ScoreBoard scoreBoard, List<Player> players) {
        if (scoreBoard.isCurrentPlayerIsOut())
            applyPlayerOutRule(scoreBoard, players);
    }

    private void applyPlayerOutRule(ScoreBoard scoreBoard, List<Player> players) {
        Player newPlayer = getNewPlayer(scoreBoard, players);
        addNewStriker(scoreBoard, newPlayer);
    }

    private Player getNewPlayer(ScoreBoard scoreBoard, List<Player> players) {
        for (Player nextPlayer : players) {
            if (!nextPlayer.isOut() && !isOnCrease(scoreBoard, nextPlayer)) {
                return nextPlayer;
            }
        }
        return null;
    }

    private boolean isOnCrease(ScoreBoard scoreBoard, Player nextPlayer) {
        Player striker = scoreBoard.getCurrentStriker();
        Player nonStriker = scoreBoard.getCurrentNonStriker();
        return nextPlayer.equals(striker) || nextPlayer.equals(nonStriker);
    }

    private void addNewStriker(ScoreBoard scoreBoard, Player newPlayer) {
        if (newPlayer == null) {
            scoreBoard.setCurrentStriker(scoreBoard.getCurrentNonStriker());
            scoreBoard.setCurrentNonStriker(null);
        } else
            scoreBoard.setCurrentStriker(newPlayer);
        scoreBoard.setCurrentPlayerIsOut(false);
    }
}
