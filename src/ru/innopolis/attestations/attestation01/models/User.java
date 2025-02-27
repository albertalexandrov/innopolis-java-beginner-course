package ru.innopolis.attestations.attestation01.models;

import java.time.LocalDateTime;

public class User {
    private String id, login, password, confirmPassword, lastName, firstName, middleName;
    private LocalDateTime createdAt;
    private int age;
    private boolean isWorker;

    public User() {}

    public User(String line) {
        String[] tokens = line.split("\\|");
        this.id = tokens[0];
        setCreatedAt(LocalDateTime.parse(tokens[1]));
        this.login = tokens[2];
        this.password = tokens[3];
        this.confirmPassword = tokens[4];
        this.lastName = tokens[5];
        this.firstName = tokens[6];
        this.middleName = tokens[7];
        this.age = Integer.parseInt(tokens[8]);
        this.isWorker = Boolean.parseBoolean(tokens[9]);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        if (createdAt == null) {
            setCreatedAt();
        } else {
            this.createdAt = createdAt;
        }
    }

    public void setCreatedAt() {
        createdAt = LocalDateTime.now();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isWorker() {
        return isWorker;
    }

    public void setWorker(boolean worker) {
        isWorker = worker;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", createdAt=" + createdAt +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", age=" + age +
                ", isWorker=" + isWorker +
                '}';
    }
}
