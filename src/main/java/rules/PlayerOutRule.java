package rules;

import models.Player;
import models.ScoreBoard;

import java.util.List;

public class PlayerOutRule implements Rule {
    @Override
    public void processScoreBoard(ScoreBoard scoreBoard, List<Player> players) {
        if (scoreBoard.isCurrentPlayerIsOut())
            applyPlayerOutRule(scoreBoard, players);
    }

    private void applyPlayerOutRule(ScoreBoard scoreBoard, List<Player> players) {
        Player newPlayer = getNewPlayer(scoreBoard, players);
        if (isStrikerOut(scoreBoard)) {
            addNewStriker(scoreBoard, newPlayer);
        } else if (isNonStrikerOut(scoreBoard)) {
            addNewNonStriker(scoreBoard, newPlayer);
        }
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

    private boolean isStrikerOut(ScoreBoard scoreBoard) {
        return scoreBoard.getCurrentStriker().isOut();
    }

    private boolean isNonStrikerOut(ScoreBoard scoreBoard) {
        return scoreBoard.getCurrentNonStriker().isOut();
    }

    private void addNewStriker(ScoreBoard scoreBoard, Player newPlayer) {
        if (newPlayer == null)
            updateStriker(scoreBoard.getCurrentNonStriker(), scoreBoard);
        else
            updateStriker(newPlayer, scoreBoard);
    }

    private void addNewNonStriker(ScoreBoard scoreBoard, Player newPlayer) {
        scoreBoard.setCurrentNonStriker(newPlayer);
        scoreBoard.setCurrentPlayerIsOut(false);
    }

    private void updateStriker(Player newPlayer, ScoreBoard scoreBoard) {
        scoreBoard.setCurrentStriker(newPlayer);
        scoreBoard.setCurrentPlayerIsOut(false);
    }
}
