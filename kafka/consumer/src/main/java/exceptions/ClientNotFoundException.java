package exceptions;

public class ClientNotFoundException extends Exception {
    public ClientNotFoundException() {
    }

    public ClientNotFoundException(String message) {
        super(message);
    }
}
