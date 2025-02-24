package ru.innopolis.attestations.attestation01.exceptions;

public class UserDoesNotExistException extends RuntimeException {

    public UserDoesNotExistException(String message) {
        super(message);
    }

    public UserDoesNotExistException() {
        super("Пользователя с заданным идентификатором не существует");
    }

}
