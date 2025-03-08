package homeworks.homework14;

import homeworks.homework06.Person;
import homeworks.homework06.Product;

import java.util.List;
import java.util.stream.Stream;

public class PersonService {

    public List<Person> convertStringToPerson(String line) {
        if (line == null || line.isEmpty()) throw new IllegalArgumentException("Строка не должна быть пустой");
        return Stream.of(line.split(";")).map(item -> {
            String[] tokens = item.split("=");
            return new Person(tokens[0].trim(), Integer.parseInt(tokens[1].trim()));
        }).toList();
    }

    public List<Product> convertStringToProduct(String line) {
        return Stream.of(line.split(";")).map(item -> {
            String[] tokens = item.split("=");
            return new Product(tokens[0].trim(), Integer.parseInt(tokens[1].trim()));
        }).toList();
    }
}
