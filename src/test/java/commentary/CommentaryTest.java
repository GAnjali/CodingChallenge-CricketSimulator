package commentary;

import models.Game;
import models.Match;
import models.Player;
import models.ScoreBoard;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CommentaryTest {

    Game game;
    Match match;
    ScoreBoard scoreBoard;
    List<Player> players;
    Commentary commentary;
    ByteArrayOutputStream outContent;
    String expectedCommentary;

    @Before
    public void init() {
        game = new Game();
        match = game.intializeMatch();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        commentary = new Commentary();
        players = new ArrayList<>();
        players.add(new Player("Kirat Boli", Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 93.0), 0, 0, false));
        players.add(new Player("NS Nodhi", Arrays.asList(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 93.0), 0, 0, false));
        scoreBoard = new ScoreBoard(players.get(0), players.get(1), 0, 4, 0, 40, false);
    }

    @Test
    public void shouldCheckOverCommentaryForGivenScoreBoard() {
        commentary.generateOverCommentary(scoreBoard);
        expectedCommentary = "\n\n4 overs left. 40 runs to win\n";
        assertEquals(expectedCommentary, outContent.toString());
    }

    @Test
    public void shouldCheckOverCommentaryForGivenScoreBoardWith1overLeft() {
        scoreBoard.setCurrentBallsPlayed(18);
        commentary.generateOverCommentary(scoreBoard);
        expectedCommentary = "\n\n1 over left. 40 runs to win\n";
        assertEquals(expectedCommentary, outContent.toString());
    }

    @Test
    public void shouldCheckBallCommentaryForGivenScoreBoard() {
        scoreBoard.setCurrentRunCount(2);
        commentary.displayBallCommentary(scoreBoard);
        expectedCommentary = "\n0.0 Kirat Boli scores 2 runs";
        assertEquals(expectedCommentary, outContent.toString());
    }

    @Test
    public void shouldCheckBallCommentaryForGivenScoreBoardWith1run() {
        commentary.displayBallCommentary(scoreBoard);
        expectedCommentary = "\n0.0 Kirat Boli scores 0 run";
        assertEquals(expectedCommentary, outContent.toString());
    }

    @Test
    public void shouldCheckBallCommentaryForGivenScoreBoardBallsXount() {
        scoreBoard.setCurrentBallsPlayed(6);
        commentary.displayBallCommentary(scoreBoard);
        expectedCommentary = "\n0.6 Kirat Boli scores 0 run";
        assertEquals(expectedCommentary, outContent.toString());
    }

    @Test
    public void shouldCheckMatchWonSummaryCommentary() {
        scoreBoard.setCurrentRunCount(40);
        scoreBoard.setCurrentBallsPlayed(20);
        commentary.wonCommentary("Bengaluru", scoreBoard);
        expectedCommentary = "\n\nBengaluru won by 4 wickets and 20 balls remaining";
        assertEquals(expectedCommentary, outContent.toString());
    }

    @Test
    public void shouldCheckMatchLostSummaryCommentary() {
        scoreBoard.setCurrentRunsToWin(20);
        scoreBoard.setCurrentWicketLeft(0);
        scoreBoard.setCurrentBallsPlayed(20);
        commentary.looseCommentary("Bengaluru", scoreBoard);
        expectedCommentary = "\n\nBengaluru Lost by 20 run needed to win and 20 balls remaining";
        assertEquals(expectedCommentary, outContent.toString());
    }

    @Test
    public void shouldCheckPlayerScoresCommentary() {
        players.get(0).setTotalBallsPlayed(5);
        players.get(0).setTotalRuns(10);
        players.get(1).setTotalBallsPlayed(15);
        players.get(1).setTotalRuns(10);
        commentary.playersScores(players, scoreBoard);
        expectedCommentary = "\nKirat Boli - 10* (5 balls)\nNS Nodhi - 10* (15 balls)";
        assertEquals(expectedCommentary, outContent.toString());
    }

    @Test
    public void shouldCheckPlayerScoresCommentaryWithPlayerNotOnCrease() {
        players.get(0).setTotalBallsPlayed(5);
        players.get(0).setTotalRuns(10);
        players.get(1).setTotalBallsPlayed(15);
        players.get(1).setTotalRuns(10);
        scoreBoard.setCurrentNonStriker(null);
        commentary.playersScores(players, scoreBoard);
        expectedCommentary = "\nKirat Boli - 10* (5 balls)\nNS Nodhi - 10 (15 balls)";
        assertEquals(expectedCommentary, outContent.toString());
    }
}
