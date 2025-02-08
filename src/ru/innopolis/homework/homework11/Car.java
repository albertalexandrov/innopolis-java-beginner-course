package ru.innopolis.homework.homework11;

import java.util.Objects;

public class Car {
    private String number;
    private String model;
    private String color;
    private int mileage;
    private int price;

    public Car(String number, String model, String color, int mileage, int price) {
        this.number = number;
        this.model = model;
        this.color = color;
        this.mileage = mileage;
        this.price = price;
    }

    public Car() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "number='" + number + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", mileage=" + mileage +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Car car)) return false;
        return mileage == car.getMileage() && price == car.getPrice() && Objects.equals(number, car.getNumber()) && Objects.equals(model, car.getModel()) && Objects.equals(color, car.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, model, color, price, mileage);
    }
}
