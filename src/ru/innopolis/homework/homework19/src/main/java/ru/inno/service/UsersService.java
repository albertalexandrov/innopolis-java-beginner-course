package ru.inno.service;

import lombok.RequiredArgsConstructor;
import ru.inno.model.User;
import org.springframework.stereotype.Service;
import ru.inno.repository.UsersRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    public User getUser(long id) {
        return usersRepository.findById(id).orElseThrow();
    }

    public List<User> getUsers() {
        return usersRepository.findAll();
    }

    public void deleteAllUsers() {
        usersRepository.deleteAll();
    }

}
