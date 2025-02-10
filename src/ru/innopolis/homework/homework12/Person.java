package ru.innopolis.homework.homework12;

import ru.innopolis.homework.homework12.exceptions.*;

public class Person {
    private String fullName;
    private String birthday;
    private long phoneNumber;
    private char gender;
    private int age;

    public Person(String fullName, String birthday, long phoneNumber, char gender, int age) {
        setFullName(fullName);
        setBirthday(birthday);
        setPhoneNumber(phoneNumber);
        setGender(gender);
        setAge(age);
    }

    private Person() {}

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName.isEmpty()) {
            // todo: необязательно проверять fullName != null? иначе по идее придется везде проверять...
            throw new InvalidFullNameException("Фамилия, имя, отчество должно быть непустой строкой");
        }
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        if (!birthday.matches("^\\d{2}-\\d{2}-\\d{4}$")) {
            throw new InvalidBirthdayException("Передано некорректное значение дня рождения: " + birthday);
        }
        this.birthday = birthday;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        if (!String.valueOf(phoneNumber).matches("^\\d{7}$")) {
            throw new InvalidPhoneNumber("Передано некорректное значение номера телефона: " + phoneNumber);
        }
        this.phoneNumber = phoneNumber;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        if (gender != 'm' || gender != 'f') {
            throw new InvalidGenderException("Передано некорректное значение пола: " + gender);
        }
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new InvalidAgeException("Передано некорректное значение возраста: " + age);
        }
        this.age = age;
    }
}
