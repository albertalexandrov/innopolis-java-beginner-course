package ru.innopolis.attestations.attestation01.repositories.impl;

import ru.innopolis.attestations.attestation01.exceptions.UserAlreadyExistsException;
import ru.innopolis.attestations.attestation01.exceptions.UserDoesNotExistException;
import ru.innopolis.attestations.attestation01.exceptions.ValidationException;
import ru.innopolis.attestations.attestation01.models.User;
import ru.innopolis.attestations.attestation01.repositories.UsersRepository;

import javax.swing.text.html.Option;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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
            reader.lines().map(this::mapLineToUser).forEach(user -> USERS.put(user.getId(), user));
        }
    }

    private User mapLineToUser(String line) {
        String[] tokens = line.split("\\|");
        var user = new User();
        user.setId(tokens[0]);
        setCreatedAt(user, tokens[1]);
        user.setLogin(tokens[2]);
        user.setPasswords(tokens[3], tokens[4]);
        user.setLastName(tokens[5]);
        user.setFirstName(tokens[6]);
        user.setMiddleName(tokens[7]);
        setAge(user, tokens[8]);
        setWorker(user, tokens[9]);
        return user;
    }

    private void setWorker(User user, String unparsedIsWorker) {
        if (!unparsedIsWorker.equalsIgnoreCase("true") && !unparsedIsWorker.equalsIgnoreCase("false")) {
            var message = "Не удалось распарсить признак является ли пользователь сотрдуником предприятия. " +
                          "Передано значение: " + unparsedIsWorker;
            throw new ValidationException(message);
        }
        user.setWorker(Boolean.parseBoolean(unparsedIsWorker));
    }

    private void setAge(User user, String unparsedAge) {
        int age;
        try {
            age = Integer.parseInt(unparsedAge);
        } catch (NumberFormatException e) {
            throw new ValidationException("Значение возраста должно быть целым числом");
        }
        user.setAge(age);
    }

    private void setCreatedAt(User user, String unparsedCreatedAt) {
        LocalDateTime createdAt;
        try {
            createdAt = LocalDateTime.parse(unparsedCreatedAt);
        } catch (DateTimeParseException e) {
            throw new ValidationException("Не удалось распарсить время создания. Получено значение: " + unparsedCreatedAt);
        }
        user.setCreatedAt(createdAt);
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
                var writer = Files.newBufferedWriter(Paths.get(FILE_PATH), StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)
        ) {
            for (var user : USERS.values()) {
                var line = mapUserToString(user);
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(String id) {
        if (!USERS.containsKey(id)) {
            throw new UserDoesNotExistException();
        }
        return USERS.get(id);
    }

    @Override
    public List<User> findAll() {
        return USERS.values().stream().toList();
    }

    @Override
    public void update(User user) {
        USERS.put(user.getId(), user);
        saveUsers();
    }

    @Override
    public void deleteById(String id) {
        if (!USERS.containsKey(id)) {
            throw new UserDoesNotExistException();
        }
        USERS.remove(id);
        saveUsers();
    }

    @Override
    public void deleteAll() {
        USERS.clear();
        saveUsers();
    }
}
