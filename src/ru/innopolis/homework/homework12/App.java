package ru.innopolis.homework.homework12;

import ru.innopolis.homework.homework12.exceptions.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        // ДЛЯ ДЕМОНСТРАЦИИ РАБОТЫ С ИСКЛЮЧЕНИЯМИ ЧАСТЬ ВАЛИДАЦИЙ ПРОХОДИТ ЗДЕСЬ,
        // НЕКОТОРЫЕ ИЗ КОТОРЫХ МОГУТ ПОКАЗАТЬ СТРАННЫМИ, А ЧАСТЬ - В КЛАССЕ PERSON
        var person = new Person();
        var scanner = new Scanner(System.in);
        setFullName(person, scanner);
        setBirthday(person, scanner);
        setPhoneNumber(person, scanner);
        setGender(person, scanner);
        setAge(person, scanner);
        System.out.println(person);
        savePerson(person);
    }

    private static void setFullName(Person person, Scanner scanner) {
        System.out.print("Введите фамилию, имя и отчество: ");
        String message;
        while (true) {
            try {
                person.setFullName(scanner.nextLine());
                break;
            } catch (InvalidFullNameException e) {
                message = String.format("%s. Повторите ввод: ", e.getMessage());
                System.out.print(message);
            }
        }
    }

    private static void setBirthday(Person person, Scanner scanner) {
        System.out.print("Введите дату рождения: ");
        String message;
        while (true) {
            try {
                person.setBirthday(scanner.nextLine());
                break;
            } catch (InvalidBirthdayException e) {
                message = String.format("%s. Повторите ввод: ", e.getMessage());
                System.out.print(message);
            }
        }
    }

    private static void setPhoneNumber(Person person, Scanner scanner) {
        System.out.print("Введите номер телефона в виде целого числа: ");
        long phoneNumber;
        String message;
        while (true) {
            try {
                phoneNumber = Long.parseLong(scanner.nextLine());
                person.setPhoneNumber(phoneNumber);
                break;
            } catch (NumberFormatException | InvalidPhoneNumber e) {
                if (e instanceof NumberFormatException) {
                    message = "Номер телефона должен состоять только из цифр";
                } else {
                    message = e.getMessage();
                }
                message = String.format("%s. Повторите ввод: ", message);
                System.out.print(message);
            }
        }
    }

    private static void setGender(Person person, Scanner scanner) {
        System.out.print("Введите пол: ");
        String input, message;
        while (true) {
            input = scanner.nextLine();
            if (input.length() == 1) {
                try {
                    person.setGender(input.charAt(0));
                    break;
                } catch (InvalidGenderException e) {
                    message = String.format("%s. Повторите ввод: ", e.getMessage());
                    System.out.print(message);
                }
            } else {
                System.out.print("Пол должен состоять из ОДНОГО символа. Повторите ввод: ");
            }
        }
    }

    private static void setAge(Person person, Scanner scanner) {
        System.out.print("Введите возраст: ");
        int age;
        String message;
        while (true) {
            try {
                age = Integer.parseInt(scanner.nextLine());
                person.setAge(age);
                break;
            } catch (NumberFormatException | InvalidAgeException e) {
                if (e instanceof NumberFormatException) {
                    message = "Возраст должен состоять только из цифр";
                } else {
                    message = e.getMessage();
                }
                message = String.format("%s. Повторите ввод: ", message);
                System.out.print(message);
            }
        }
    }

    private static void savePerson(Person person) throws IOException {
        final var OUTPUT_FILE_PATH = "src/ru/innopolis/homework/homework12/output.txt";
        try (
                var writer = new BufferedWriter(new FileWriter(OUTPUT_FILE_PATH));
        ) {
            var row = String.format("%s %s %s %s", person.getFullName(), person.getBirthday(), person.getPhoneNumber(), person.getGender());
            writer.write(row);
        }
    }
}
