package ru.innopolis.attestations.attestation01;

import ru.innopolis.attestations.attestation01.models.User;
import ru.innopolis.attestations.attestation01.repositories.impl.UsersRepositoryFileImpl;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        var user1 = new User("1218a3cb-4ac9-4b3b-8a65-c424e129b914|2023-12-25T19:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|25|true");
//        var user2 = new User("1218a3cb-4ac9-4b3b-8a65-c424e129b914|2023-12-25T19:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|25|true");
//        var user3 = new User("1318a3cb-4ac9-4b3b-8a65-c424e129b914|2023-12-25T19:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|25|true");
        var repo = new UsersRepositoryFileImpl();
        user1.setAge(99);
        repo.update(user1);
//        repo.create(user);
//        System.out.println(repo.findAll());
//        System.out.println(repo.findById("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2"));
    }
}
