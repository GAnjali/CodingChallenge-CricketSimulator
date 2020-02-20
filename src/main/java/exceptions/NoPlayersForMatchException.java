package exceptions;

public class NoPlayersForMatchException extends Exception {
    private final String error;

    public NoPlayersForMatchException(){
        super();
        this.error = "Team size is insufficient";
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
