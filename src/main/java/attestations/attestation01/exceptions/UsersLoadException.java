package attestations.attestation01.exceptions;

public class UsersLoadException extends RuntimeException {
    public UsersLoadException(String message) {
        super(message);
    }

    public UsersLoadException(String message, Throwable cause) {}
}
