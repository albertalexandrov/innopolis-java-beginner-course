package ru.innopolis.homework.homework07;

import java.util.Objects;
import java.util.Random;

public class Product {
    private String name;
    private int price;

    public Product(String name, int price) {
        // инициализация через геттеры, чтобы выполнять валидацию
        setName(name);
        setPrice(price);
    }

    public Product() {
        this(generateRandomName(), generateRandomPrice());
    }

    private static String generateRandomName() {
        Random rdm = new Random();
        int randomInt = rdm.nextInt(1000);
        return "Продукт " + randomInt;
    }

    private static int generateRandomPrice() {
        Random rdm = new Random();
        return rdm.nextInt(1000);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Название продукта не может быть пустой строкой");
        }
        this.name = name;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Стоимость продукта не может быть отрицательной");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + this.name + '\'' +
                ", price=" + this.price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return Objects.equals(this.name, product.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }
}
