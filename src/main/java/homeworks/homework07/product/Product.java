package homeworks.homework07.product;

import java.util.Objects;

public class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        // инициализация через геттеры, чтобы выполнять валидацию
        setName(name);
        setPrice(price);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name.length() < 3 || name.matches("\\d+")) {
            throw new IllegalArgumentException("Недопустимое имя продукта!");
        }
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Недопустимая стоимость продукта!");
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
