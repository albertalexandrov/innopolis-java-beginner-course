package ru.innopolis.homework.homework07;

import ru.innopolis.homework.homework07.product.DiscountProduct;
import ru.innopolis.homework.homework07.product.Product;

import java.util.*;
import java.util.function.Predicate;

public class App {
    public static void main(String[] args) {
        System.out.println("Введите информацию о продуктах (чтобы прекратить ввод, введите END):");
        Scanner scanner = new Scanner(System.in);
        List<Product> products = new ArrayList<>();

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("END")) {
                System.out.println("Ввод завершен.");
                break;
            }

            if (userInput.isEmpty()) {
                System.out.println("Была введена пустая строка. Повторите ввод.");
                continue;
            }

            try {
                createProduct(userInput, products);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        if (!products.isEmpty()) {
            printRegularProducts(products);
            printDiscountProducts(products);
        }
    }

    private static void createProduct(String userInput, List<Product> products) {
        Random random = new Random();
        String[] tokens = userInput.split("=");
        String name = tokens[0].strip();
        tokens = tokens[1].split(",");
        double price = Double.parseDouble(tokens[0]);
        Product product;
        if (tokens.length == 2) {
            double discount = Double.parseDouble(tokens[1].substring(0, tokens[1].length() - 1));
            boolean applyDiscount = random.nextBoolean();
            product = new DiscountProduct(name, price, discount, applyDiscount);
        } else {
            product = new Product(name, price);
        }
        products.add(product);
    }

    private static void printDiscountProducts(List<Product> products) {
        Predicate<Product> predicate = (product -> product instanceof DiscountProduct);
        printProducts(products, predicate, "Акционные продукты");
    }

    private static void printRegularProducts(List<Product> products) {
        Predicate<Product> predicate = (product -> !(product instanceof DiscountProduct));
        printProducts(products, predicate, "Обычные продукты");
    }

    private static void printProducts(List<Product> products, Predicate<Product> predicate, String title) {
        List<String> names = products.stream()
                .filter(predicate)
                .map(Product::getName)
                .toList();
        System.out.printf("%s: %s\n", title, String.join(", ", names));
    }
}
