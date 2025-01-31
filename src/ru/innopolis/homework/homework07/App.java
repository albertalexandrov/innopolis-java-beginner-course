package ru.innopolis.homework.homework07;

import ru.innopolis.homework.homework07.product.Product;

import java.util.HashMap;
import java.util.Scanner;

import static java.util.stream.Collectors.toList;

public class App {
    public static void main(String[] args) {
        System.out.print("Введите информацию о покупателях: ");
        HashMap<String, Person> persons = getPersons();
        System.out.print("Введите информацию о продуктах: ");
        HashMap<String, Product> products = getProducts();
        System.out.println("Введите покупки в формате <имя покупателя>:<название продукта> (введите END, чтобы прекратить ввод):");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("END")) {
                System.out.println("Ввод завершен.");
                break;
            }
            if (input.isEmpty()) {
                System.out.println("Была введена пустая строка. Повторите ввод.");
                continue;
            }
            // в условиях задачи как таковой разделитель между именем покупателя и
            // названием продукта не прописан. поэтому для надежности используем тире
            String[] tokens = input.split("-");
            String personName = tokens[0].trim().toLowerCase();
            String productName = tokens[1].trim().toLowerCase();
            Person person = persons.get(personName);
            if (person == null) {
                System.out.printf("Покупатель %s не найден. Повторите ввод.\n", personName);
                continue;
            }
            Product product = products.get(productName);
            if (product == null) {
                System.out.printf("Продукт %s не найден. Повторите ввод.\n", productName);
                continue;
            }
            person.addProduct(product);
        }
        System.out.println("Итоги покупок:");
        for (Person person : persons.values()) {
            if (person.getProducts().isEmpty()) {
                System.out.printf("%s - ничего не куплено\n", person.getName());
            } else {
                String productNames = String.join(", ", person.getProducts().stream().map(o -> o.getName()).collect(toList()));
                System.out.printf("%s - %s\n", person.getName(), productNames);
            }
        }
    }

    private static HashMap<String, Person> getPersons() {  //
        // возвращает маппинг имени покупателя на объект покупателя
        // маппинг нужен для того, чтобы было удобно искать покупателя по имени
        // пример ввода с консоли: Павел Андреевич = 10000; Анна Петровна = 2000; Борис = 10
        HashMap<String, Person> persons = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        for (String item : input.split(";")) {
            String[] parts = item.split("=");
            String name = parts[0].trim();
            int money = Integer.parseInt(parts[1].trim());
            Person person = new Person(name, money);
            persons.put(name.toLowerCase(), person);
        }
        return persons;
    }

    private static HashMap<String, Product> getProducts() {
        // возвращает маппинг названия продукта на объект продукта
        // маппинг нужен для того, чтобы было удобно искать продукты по названию
        // пример ввода с консоли: Хлеб = 40; Молоко = 60; Торт = 1000; Кофе растворимый = 879; Масло = 150
        HashMap<String, Product> products = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        for (String item : input.split(";")) {
            String[] parts = item.split("=");
            String name = parts[0].trim();
            int price = Integer.parseInt(parts[1].trim());
            Product product = new Product(name, price);
            products.put(name.toLowerCase(), product);
        }
        return products;
    }
}
