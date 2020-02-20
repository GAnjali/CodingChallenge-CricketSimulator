package models;

public class Match {
    private final String playingTeam;
    private final String opposingTeam;
    private final int overs;
    private int wicketsLeft;
    private int runNeededToWin;
    private Player stricker;
    private Player nonStricker;
    private int runsCount;
    private int ballsPlayed;

    public Match(String playingTeam, String opposingTeam, Player stricker, Player nonStricker, int runsCount, int ballsPlayed, int wicketsLeft, int runNeededToWin, int overs) {
        this.playingTeam = playingTeam;
        this.opposingTeam = opposingTeam;
        this.stricker = stricker;
        this.nonStricker = nonStricker;
        this.runsCount = runsCount;
        this.ballsPlayed = ballsPlayed;
        this.wicketsLeft = wicketsLeft;
        this.runNeededToWin = runNeededToWin;
        this.overs = overs;
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

    public int getOvers() {
        return overs;
    }
}
