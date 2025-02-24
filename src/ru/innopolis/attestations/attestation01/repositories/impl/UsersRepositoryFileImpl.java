package ru.innopolis.attestations.attestation01.repositories.impl;

import ru.innopolis.attestations.attestation01.models.User;
import ru.innopolis.attestations.attestation01.repositories.UsersRepository;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class UsersRepositoryFileImpl implements UsersRepository {

    private static final List<User> USERS = new ArrayList<>();
    private static final String FILE_PATH = "src/ru/innopolis/attestations/attestation01/resources/users.txt";

    @Override
    public void create(User user) {
        // проверим, что пользователь не существует
        if (findById(user.getId()).isPresent()) {
            throw new RuntimeException("Пользователь ID=" + user.getId() + " уже существует");
        }
        // добавим в конец файла нового пользователя
        try (
            var writer = Files.newBufferedWriter(Paths.get(FILE_PATH), StandardOpenOption.APPEND)
        ) {
            writer.write("\n");
            writer.write(user.toString());
        } catch (IOException e) {
            var message = String.format("Не удалось сохранить пользователя ID=%s в файл: %s", user.getId(), e.getMessage());
            throw new RuntimeException(message);
        }
    }

    @Override
    public Optional<User> findById(String id) {
        if (USERS.isEmpty()) findAll();
        return USERS.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    @Override
    public List<User> findAll() {
        if (USERS.isEmpty()) {
            try (var reader = Files.newBufferedReader(Paths.get(FILE_PATH))) {
                List<User> users = reader.lines().map(User::new).toList();
                USERS.addAll(users);
            } catch (NoSuchFileException e) {
                System.out.println("Отсутствует файл по адресу " + FILE_PATH);
            } catch (IOException e) {
                System.out.println("Ошибка чтения файла: " + e.getMessage());
            };
        }
        return Collections.unmodifiableList(USERS);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public void deleteAll() {
        USERS.clear();
        try (var writer = Files.newBufferedWriter(Paths.get(FILE_PATH))) {
            writer.write("");
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
