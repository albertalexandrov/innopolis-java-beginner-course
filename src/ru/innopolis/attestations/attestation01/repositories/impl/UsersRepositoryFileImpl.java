package ru.innopolis.attestations.attestation01.repositories.impl;

import ru.innopolis.attestations.attestation01.models.User;
import ru.innopolis.attestations.attestation01.repositories.UsersRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class UsersRepositoryFileImpl implements UsersRepository {

    private static final List<User> USERS = new ArrayList<>();
    private static final String FILE_PATH = "/input.txt";

    @Override
    public void create(User user) {

    }

    @Override
    public User findById(String id) {
        if (USERS.isEmpty()) {
            findAll();
        }
        return USERS.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Пользователь id=" + id + " не найден"));
    }

    @Override
    public List<User> findAll() {
        if (USERS.isEmpty()) {
            try (var reader = Files.newBufferedReader(Paths.get(FILE_PATH))) {
                List<User> users = reader.lines().map(line -> new User()).toList();
                USERS.addAll(users);
            } catch (NoSuchFileException e) {
                System.out.println("Отсутствует файл по адресу " + FILE_PATH);
            } catch (IOException e) {
                System.out.println("Ошибка чтения файла: " + e.getMessage());
            };
        }
        return USERS;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public void deleteAll() {

    }
}
