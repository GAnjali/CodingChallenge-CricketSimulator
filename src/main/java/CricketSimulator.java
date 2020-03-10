import helper.Initializer;
import models.Match;

import java.io.IOException;

public class CricketSimulator {
    public static void main(String[] args) throws IOException {
        Initializer initializer = new Initializer();
        Match match = initializer.initializeMatch();

        match.simulate();
    }
}

