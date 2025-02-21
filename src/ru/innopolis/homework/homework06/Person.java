package ru.innopolis.homework.homework06;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Person {
    private String name;
    private int money;
    private ArrayList<Product> products;

    public Person(String name, int money) {
        // инициализация через геттеры, чтобы выполнять валидацию
        setName(name);
        setMoney(money);
        this.products = new ArrayList<>();
    }

    public Person() {
        this(generateRandomName(), generateRandomMoney());
    }

    private static String generateRandomName() {
        Random rdm = new Random();
        int randomInt = rdm.nextInt(1000);
        return "Покупатель " + randomInt;
    }

    private static int generateRandomMoney() {
        Random rdm = new Random();
        return rdm.nextInt(10000);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Имя покутеля не может быть пустой строкой");
        }
        this.name = name;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("Количество денег не может быть отрицательным");
        }
        this.money = money;
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    public void addProduct(Product product) {
        if (product.getPrice() > this.money) {
            System.out.printf("%s не может позволить себе %s\n", this.name, product.getName());
        }
        else {
            this.products.add(product);
            this.money -= product.getPrice();
            System.out.printf("%s купил(а) %s\n", this.name, product.getName());
        }

    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person person)) return false;
        return Objects.equals(this.name, person.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }
}
