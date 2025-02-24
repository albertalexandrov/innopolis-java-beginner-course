package ru.innopolis.attestations.attestation01;

import ru.innopolis.attestations.attestation01.models.User;
import ru.innopolis.attestations.attestation01.repositories.impl.UsersRepositoryFileImpl;

public class App {
    public static void main(String[] args) {
        var user = new User("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2|2023-12-25T19:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|25|true");
        var repo = new UsersRepositoryFileImpl();
        System.out.println(repo.findById("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2"));
    }
}
