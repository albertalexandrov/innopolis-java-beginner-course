package ru.innopolis.homework.homework08;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class App {

    public static void main(String[] args) {
        final String INPUT_FILE_PATH = "src/ru/innopolis/homework/homework08/input.txt";
        final String OUTPUT_FILE_PATH = "src/ru/innopolis/homework/homework08/output.txt";
        try (
                BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE_PATH));
                BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE_PATH));
        ) {
            // todo: типы переменных для reader и writer указаны верно?
            //  где бы я ни смотрел, везде прописывались BufferedReader
            //  и BufferedWrite в том числе и в лекции
            Map<String, Person> persons = parsePersons(reader.readLine());
            Map<String, Product> products = parseProducts(reader.readLine());
            parsePurchases(reader, writer, persons, products);
            writeResults(persons, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Map<String, Person> parsePersons(String input) {
        HashMap<String, Person> persons = new HashMap<>();
        for (String item : input.split(";")) {
            String[] tokens = item.split("=");
            String name = tokens[0].strip();
            int money = Integer.parseInt(tokens[1].trim());
            Person person = new Person(name, money);
            persons.put(name.toLowerCase(), person);
        }
        return persons;
    }

    private static Map<String, Product> parseProducts(String input) {
        Map<String, Product> products = new HashMap<>();
        for (String item : input.split(";")) {
            String[] tokens = item.split("=");
            String name = tokens[0].trim();
            int price = Integer.parseInt(tokens[1].trim());
            Product product = new Product(name, price);
            products.put(name.toLowerCase(), product);
        }
        return products;
    }

    private static void parsePurchases(
            BufferedReader reader,
            BufferedWriter writer,
            Map<String, Person> persons,
            Map<String, Product> products
    ) throws IOException {
        // todo: почему IDE принуждает прописывать throws IOException?
        //  это ведь не метод рейзит, а вызываемый метод reader.readLine()
        //  к тому же исключение перехватывается в вызывающем методе main
        String line;
        while (!(line = reader.readLine()).equals("END")) {
            System.out.println(line);
            String[] tokens = line.split("=");
            Person person = persons.get(tokens[0].strip().toLowerCase());
            Product product = products.get(tokens[1].strip().toLowerCase());
            person.addProduct(product, writer);
        }
    }

    private static void writeResults(Map<String, Person> persons, BufferedWriter writer) throws IOException {
        writer.write("\n");
        for (Person person : persons.values()) {
            String output;
            if (person.getProducts().isEmpty()) {
                output = String.format("%s - ничего не куплено\n", person.getName());
            } else {
                String productNames = String.join(", ", person.getProducts().stream().map(Product::getName).toList());
                output = String.format("%s - %s\n", person.getName(), productNames);
            }
            writer.write(output);
        }
    }
}