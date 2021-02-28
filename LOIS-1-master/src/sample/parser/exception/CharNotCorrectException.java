package sample.parser.exception;

public class CharNotCorrectException extends Exception {

    public CharNotCorrectException(String message) {
        super(message);
    }

    public CharNotCorrectException(String message, Throwable cause) {
        super(message, cause);
    }

    public CharNotCorrectException(Throwable cause) {
        super(cause);
    }
}
