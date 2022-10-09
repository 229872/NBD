package exceptions;

public class ClientAlreadyExistsException extends Exception {
    public ClientAlreadyExistsException() {
    }

    public ClientAlreadyExistsException(String message) {
        super(message);
    }
}
