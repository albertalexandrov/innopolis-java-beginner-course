package exceptions;

public class UsersLoadException extends RuntimeException {
    public UsersLoadException(String message) {
        super(message);
    }

    public UsersLoadException(String message, Throwable cause) {}
}
