package utils;

import exceptions.PlayerNotFoundException;
import models.Player;

import java.util.List;

public class MatchUtils {
    public int getPlayerPosition(Player player, List<Player> players) throws PlayerNotFoundException {
        for (int position = 0; position < players.size(); position++)
            if (players.get(position).getName().equals(player.getName()))
                return position;
        throw new PlayerNotFoundException();
    }
}
