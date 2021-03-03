package sample.parser.exception;

public class BracketsException extends Exception {

    public BracketsException(String message) {
        super(message);
    }

    public BracketsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BracketsException(Throwable cause) {
        super(cause);
    }
}
