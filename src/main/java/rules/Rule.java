package rules;

import models.MatchStatus;
import models.Player;

import java.util.List;

public interface Rule {
    MatchStatus processStatus(MatchStatus status, List<Player> players);
}
