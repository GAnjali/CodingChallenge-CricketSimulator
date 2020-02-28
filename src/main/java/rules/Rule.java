package rules;

import models.ScoreBoard;
import models.Player;

import java.util.List;

public interface Rule {
    void perform(ScoreBoard status, List<Player> players);
}
