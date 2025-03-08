package attestations.attestation01.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException() {
        super("Пользователя с заданным идентификатором не существует");
    }

}
