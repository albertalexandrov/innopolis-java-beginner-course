package ru.innopolis.attestations.attestation01;

import ru.innopolis.attestations.attestation01.repositories.impl.UsersRepositoryFileImpl;

public class App {
    public static void main(String[] args) {
        var repo = new UsersRepositoryFileImpl();
        repo.findAll();
    }
}
