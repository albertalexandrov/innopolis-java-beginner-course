package ru.innopolis.attestations.attestation01.repositories;

import ru.innopolis.attestations.attestation01.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository {

    void create(User user);

    User findById(String id);

    List<User> findAll();

    void update(User user);

    void deleteById(String id);

    void deleteAll();
}
