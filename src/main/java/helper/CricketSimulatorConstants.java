package helper;

public class CricketSimulatorConstants {
    public static final int BALLS_PER_OVER = 6;
    public static final int OUT = 7;
    public static final int RANGE = 100;
    public static final String SUFFIX_FOR_ON_CREASE_PLAYER = "*";
    public static final String SUFFIX_FOR_COUNT = "s";
    public static final String CONFIG_PATH = "\\src\\main\\java\\config\\config.properties";
    public static final String OVER_MESSAGE_TEMPLATE = "\n\n%d over%s left. %d runs to win\n";
    public static final String BALL_MESSAGE_TEMPLATE = "\n%d.%d %s scores %d run%s";
    public static final String PLAYER_OUT_MESSAGE_TEMPLATE = "\n%d.%d %s Out";
    public static final String WON_MESSAGE_TEMPLATE = "\n\n%-5s won by %d wicket%s and %d ball%s remaining";
    public static final String LOST_MESSAGE_TEMPLATE = "\n\n%-5s Lost by %d run%s";
    public static final String PLAYER_SCORE_TEMPLATE = "\n%-5s - %d%s (%d ball%s)";
}
