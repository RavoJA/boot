package ravo.jean.aime.boot.errorhandler;

public class AppResponseEntityException extends Exception {
    public AppResponseEntityException() {
    }

    public AppResponseEntityException(String message) {
        super(message);
    }

    public AppResponseEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppResponseEntityException(Throwable cause) {
        super(cause);
    }
}
