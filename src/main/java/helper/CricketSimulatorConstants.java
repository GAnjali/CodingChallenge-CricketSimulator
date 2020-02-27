package helper;

public class CricketSimulatorConstants {
    public static final int NO_OF_BALLS_PER_OVER = 6;
    public static final int INVALID_RUN = 7;
    public static final int RANGE = 101;
    public static final String OVER_MESSAGE_TEMPLATE = "\n\n%d over%s left. %d runs to win\n";
    public static final String BALL_MESSAGE_TEMPLATE = "\n%d.%d %s scores %d run%s";
    public static final String WON_MESSAGE_TEMPLATE = "\n\n%-5s won by %d wicket%s and %d ball%s remaining";
    public static final String LOST_MESSAGE_TEMPLATE = "\n\n%-5s Lost by %d run%s needed to win and %d ball%s remaining";
    public static final String PLAYER_SCORE_TEMPLATE = "\n%-5s - %d%s (%d ball%s)";
}
