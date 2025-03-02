package ru.innopolis.attestations.attestation01.exceptions;

import ru.innopolis.attestations.attestation01.models.User;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
