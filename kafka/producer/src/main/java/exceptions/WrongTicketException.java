package exceptions;

public class WrongTicketException extends Exception {
    public WrongTicketException() {
    }

    public WrongTicketException(String message) {
        super(message);
    }
}
