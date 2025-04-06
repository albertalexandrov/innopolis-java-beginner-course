package ru.innopolis.service;

import ru.innopolis.dto.CreateUpdateUserDto;
import ru.innopolis.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(long userId);

    List<User> findAll();

    User createUser(CreateUpdateUserDto data);

    Optional<User> updateUser(long userId, CreateUpdateUserDto data);

    void deleteUser(long userId);

}
