package ru.innopolis.attestations.attestation01.repositories.impl;

import ru.innopolis.attestations.attestation01.exceptions.UserAlreadyExistsException;
import ru.innopolis.attestations.attestation01.exceptions.UserDoesNotExistException;
import ru.innopolis.attestations.attestation01.models.User;
import ru.innopolis.attestations.attestation01.repositories.UsersRepository;

import javax.swing.text.html.Option;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class UsersRepositoryFileImpl implements UsersRepository {

    private static final Map<String, User> USERS = new LinkedHashMap<>();
    private static final String FILE_PATH = "src/ru/innopolis/attestations/attestation01/resources/users.txt";

    public UsersRepositoryFileImpl() {
        var file = new File(FILE_PATH);
        try {
            if (!file.createNewFile()) {
                loadUsers(file);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadUsers(File file) throws IOException {
        try (var reader = new BufferedReader(new FileReader(file))) {
            reader.lines().map(User::new).forEach(user -> USERS.put(user.getId(), user));
        }
    }

    private String mapUserToString(User user) {
        String[] values = {
                user.getId(),
                user.getCreatedAt().toString(),
                user.getLogin(),
                user.getPassword(),
                user.getConfirmPassword(),
                user.getLastName(),
                user.getFirstName(),
                user.getMiddleName(),
                Integer.toString(user.getAge()),
                Boolean.toString(user.isWorker())
        };
        return String.join("|", values);
    }

    @Override
    public void create(User user) {
        // убедимся, что пользователя не существует
        if (USERS.containsKey(user.getId())) {
            throw new UserAlreadyExistsException("sdvsdv");
        }
        // добавим в конец файла нового пользователя
        USERS.put(user.getId(), user);
        saveUsers();
    }

    private void saveUsers() {
        try (
                var writer = Files.newBufferedWriter(Paths.get(FILE_PATH), StandardOpenOption.WRITE)
        ) {
            for (var user: USERS.values()) {
                var line = mapUserToString(user);
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(String id) {
        if (USERS.isEmpty()) findAll();
        return USERS.entrySet().stream().filter(e -> e.getValue().getId().equals(id)).findFirst().get().getValue();
//                .filter(user -> user.getId().equals(id))
//                .findFirst()
//                .orElseThrow(UserDoesNotExistException::new);
    }

    @Override
    public List<User> findAll() {
//        if (USERS.isEmpty()) findAll();
//        // каждый раз читаем файл
//        try (var reader = Files.newBufferedReader(Paths.get(FILE_PATH))) {
//            List<User> users = reader.lines().map(User::new).toList();
//            USERS.addAll(users);
//        } catch (NoSuchFileException e) {
//            System.out.println("Отсутствует файл по адресу " + FILE_PATH);
//        } catch (IOException e) {
//            System.out.println("Ошибка чтения файла: " + e.getMessage());
//        }
        return Collections.unmodifiableList(USERS.values().stream().toList());
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
