import exceptions.NoPlayersForMatchException;
import models.Game;
import models.Match;
import models.Player;

import java.util.List;

public class CricketSimulationMain {

    public static void main(String[] args) throws NoPlayersForMatchException {
        Game game = new Game();
        List<Player> players = game.intializePlayers();
        Match match = game.intializeMatch(players);
    }
}

