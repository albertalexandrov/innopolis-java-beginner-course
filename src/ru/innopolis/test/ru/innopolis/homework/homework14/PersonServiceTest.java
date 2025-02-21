package ru.innopolis.homework.homework14;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import ru.innopolis.homework.homework06.Person;
import ru.innopolis.homework.homework06.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {
    private static PersonService personService;

    @BeforeAll
    static void init() {
        personService = new PersonService();
    }

    @Test
    void testConvertStringToPerson1() {
        var persons = Arrays.asList(
                new Person("Павел Андреевич", 10_000),
                new Person("Анна Петровна", 2_000),
                new Person("Борис", 10)
        );
        var builder = new StringBuilder();
        for (var person : persons) {
            builder.append(person.getName());
            builder.append("=");
            builder.append(person.getMoney());
            builder.append(";");
        }
        var line = builder.toString();
        line = line.substring(0, line.length() - 1);
        var actualResult = personService.convertStringToPerson(line);
        assertEquals(persons, actualResult);
    }

    public static Stream<Arguments> testConvertStringToPerson2() {
        return Stream.of(
                Arguments.of("Павел Андреевич", 10_000),
                Arguments.of("Анна Петровна", 2_000),
                Arguments.of("Борис", 10)
        );
    }

    @ParameterizedTest
    @MethodSource
    void testConvertStringToPerson2(String name, int money) {
        var line = String.format("%s = %d", name, money);
        var actualResult = personService.convertStringToPerson(line);
        var expectedResult = List.of(new Person(name, money));
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testConvertStringToPersonWithException(String line) {
        assertThrows(IllegalArgumentException.class, () -> personService.convertStringToPerson(line));
    }

    @Test
    void testConvertStringToProduct() {
        var products = Arrays.asList(
                new Product("Хлеб", 40),
                new Product("Молоко", 60)
        );
        var builder = new StringBuilder();
        for (var person : products) {
            builder.append(person.getName());
            builder.append("=");
            builder.append(person.getPrice());
            builder.append(";");
        }
        var line = builder.toString();
        line = line.substring(0, line.length() - 1);
        var actualResult = personService.convertStringToProduct(line);
        assertEquals(products, actualResult);
    }

    @Disabled("В методе personService.convertStringToProduct удалена проверка на пустую строку и null")
    @ParameterizedTest
    @NullAndEmptySource
    void testConvertStringToProductWithException(String line) {
        assertThrows(IllegalArgumentException.class, () -> personService.convertStringToProduct(line));
    }
}