package rules;

import exceptions.PlayerNotFoundException;
import models.MatchStatus;
import models.Player;
import utils.MatchUtils;

import java.util.List;

public class PlayerOutRule implements Rule {
    MatchUtils matchUtils = new MatchUtils();

    @Override
    public MatchStatus processStatus(MatchStatus status, List<Player> players) throws PlayerNotFoundException {
        if (status.isCurrentPlayerIsOut())
            applyPlayerOutRule(status, players);
        return status;
    }

    private void applyPlayerOutRule(MatchStatus status, List<Player> players) throws PlayerNotFoundException {
        Player newPlayer = getNewPlayer(status, players);
        if (isStrikerOut(status)) {
            addNewStriker(status, newPlayer, players);
        } else if (isNonStrikerOut(status)) {
            addNewNonStriker(status, newPlayer, players);
        }
    }

    private Player getNewPlayer(MatchStatus status, List<Player> players) {
        for (Player nextPlayer : players) {
            if (!nextPlayer.isOut() && !isOnCrease(status, nextPlayer)) {
                return nextPlayer;
            }
        }
        return null;
    }

    private boolean isOnCrease(MatchStatus status, Player nextPlayer) {
        Player striker = status.getCurrentStriker();
        Player nonStriker = status.getCurrentNonStriker();
        return nextPlayer.equals(striker) || nextPlayer.equals(nonStriker);
    }

    private boolean isStrikerOut(MatchStatus status) {
        return status.getCurrentStriker().isOut();
    }

    private boolean isNonStrikerOut(MatchStatus status) {
        return status.getCurrentNonStriker().isOut();
    }

    private void addNewStriker(MatchStatus status, Player newPlayer, List<Player> players) throws PlayerNotFoundException {
        if (newPlayer == null)
            updateStriker(status.getCurrentNonStriker(), status, players);
        else
            updateStriker(newPlayer, status, players);
    }

    private void addNewNonStriker(MatchStatus status, Player newPlayer, List<Player> players) throws PlayerNotFoundException {
        status.setCurrentNonStriker(newPlayer);
        status.setCurrentPlayerIsOut(false);
        if (newPlayer != null)
            status.setCurrentPlayerPosition(matchUtils.getPlayerPosition(newPlayer, players));
    }

    private void updateStriker(Player newPlayer, MatchStatus status, List<Player> players) throws PlayerNotFoundException {
        status.setCurrentStriker(newPlayer);
        status.setCurrentPlayerIsOut(false);
        status.setCurrentPlayerPosition(matchUtils.getPlayerPosition(newPlayer, players));
    }
}
