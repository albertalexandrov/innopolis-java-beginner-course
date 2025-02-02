package ru.innopolis.homework.homework08;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Person {
    private String name;
    private int money;
    private List<Product> products;

    public Person(String name, int money) {
        setName(name);
        setMoney(money);
        products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Имя покутеля не может быть пустой строкой");
        }
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("Количество денег не может быть отрицательным");
        }
        this.money = money;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public void addProduct(Product product, BufferedWriter fileWriter) throws IOException {
        String output;
        if (product.getPrice() > money) {
            output = String.format("%s не может позволить себе %s\n", name, product.getName());
        }
        else {
            products.add(product);
            money -= product.getPrice();
            output = String.format("%s купил(а) %s\n", name, product.getName());
        }
        fileWriter.write(output);
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
        return Objects.equals(name, person.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
