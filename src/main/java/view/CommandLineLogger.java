package view;

public class CommandLineLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.print(message);
    }
}
