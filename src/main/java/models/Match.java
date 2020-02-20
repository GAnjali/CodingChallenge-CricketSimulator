package models;

public class Match {
    private final String playingTeam;
    private final String opposingTeam;
    private final int wicketsLeft;
    private final int runNeededToWin;
    private final int oversLeft;
    private Player stricker;
    private Player nonStricker;
    private int runsCount;
    private int ballsPlayed;

    public Match(String playingTeam, String opposingTeam, Player stricker, Player nonStricker, int runsCount, int ballsPlayed, int wicketsLeft, int runNeededToWin, int oversLeft) {
        this.playingTeam = playingTeam;
        this.opposingTeam = opposingTeam;
        this.stricker = stricker;
        this.nonStricker = nonStricker;
        this.runsCount = runsCount;
        this.ballsPlayed = ballsPlayed;
        this.wicketsLeft = wicketsLeft;
        this.runNeededToWin = runNeededToWin;
        this.oversLeft = oversLeft;
    }

    public String getPlayingTeam() {
        return playingTeam;
    }

    public String getOpposingTeam() {
        return opposingTeam;
    }

    public int getWicketsLeft() {
        return wicketsLeft;
    }

    public int getRunNeededToWin() {
        return runNeededToWin;
    }

    public int getOversLeft() {
        return oversLeft;
    }
}
