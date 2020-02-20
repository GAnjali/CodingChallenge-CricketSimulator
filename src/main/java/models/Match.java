package models;

public class Match {
    private final String playingTeam;
    private final String opposingTeam;
    private final int wicketsLeft;
    private final int runNeededToWin;
    private final int oversLeft;

    public Match(String playingTeam, String opposingTeam, int wicketsLeft, int runNeededToWin, int oversLeft) {
        this.playingTeam = playingTeam;
        this.opposingTeam = opposingTeam;
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
