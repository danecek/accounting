package dan.accounting8.util;

public class AccException extends Exception {

    public AccException(String message) {
        super(message);
    }

    public AccException(Exception cause) {
        super(cause);
    }

}
