package models;

import exceptions.NoPlayersForMatchException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    public Match intializeMatch(List<Player> players) throws NoPlayersForMatchException {
        if (players.isEmpty())
            throw new NoPlayersForMatchException();
        return new Match("Bengaluru", "Chennai", players.get(0), players.get(1), 0, 0, 4, 40, 4);
    }

    public List<Player> intializePlayers() {
        List<Player> players = new ArrayList<Player>();
        players.add(new Player("Kirat Boli", Arrays.asList(5.0, 30.0, 25.0, 10.0, 15.0, 1.0, 9.0, 5.0), 0, 0, false));
        players.add(new Player("NS Nodhi", Arrays.asList(10.0, 40.0, 20.0, 5.0, 10.0, 1.0, 4.0, 10.0), 0, 0, false));
        players.add(new Player("R Rumrah", Arrays.asList(20.0, 30.0, 15.0, 5.0, 5.0, 1.0, 4.0, 20.0), 0, 0, false));
        players.add(new Player("Shashi Henra", Arrays.asList(30.0, 25.0, 5.0, 0.0, 5.0, 1.0, 4.0, 30.0), 0, 0, false));
        return players;
    }
}
