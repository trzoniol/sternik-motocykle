package pl.sternik.trzonski.motocykle.repositories;

public class MotocyklAlreadyExistsException extends Exception {
    private static final long serialVersionUID = -4576295597218170159L;

    public MotocyklAlreadyExistsException() {     
    }

    public MotocyklAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MotocyklAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MotocyklAlreadyExistsException(String message) {
        super(message);
    }

    public MotocyklAlreadyExistsException(Throwable cause) {
        super(cause);
    }
    
}
