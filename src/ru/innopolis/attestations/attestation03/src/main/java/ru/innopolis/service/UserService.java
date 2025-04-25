package ru.innopolis.service;

import ru.innopolis.dto.UserCreateUpdateDTO;
import ru.innopolis.entity.User;

import java.util.List;

public interface UserService {

    User createUser(UserCreateUpdateDTO data);

    User getUser(Long userId);

    List<User> listUsers();

    User updateUser(Long userId, UserCreateUpdateDTO data);

    void deleteUser(Long userId);
    
}
