package exceptions;

public class PlayerNotFoundException extends Exception {
    private final String error;

    public PlayerNotFoundException(){
        super();
        this.error = "Player Not Found";
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
