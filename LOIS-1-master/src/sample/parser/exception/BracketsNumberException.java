package sample.parser.exception;

public class BracketsNumberException extends Exception {

    public BracketsNumberException(String message) {
        super(message);
    }

    public BracketsNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public BracketsNumberException(Throwable cause) {
        super(cause);
    }
}
