package homeworks.homework09.cars;

import java.util.Objects;

public class Car {
    private String brand;  // марка
    private String model;  // модель
    private int year;  // год выпуска - model year
    private int power;  // мощность
    private int acceleration;  // ускорение
    private int pendant;  // подвеска
    private int durability;  // долговечность

    public Car(String brand, String model, int year, int power, int acceleration, int pendant, int durability) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.power = power;
        this.acceleration = acceleration;
        this.pendant = pendant;
        this.durability = durability;
    }

    public Car() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public int getPendant() {
        return pendant;
    }

    public void setPendant(int pendant) {
        this.pendant = pendant;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", power=" + power +
                ", acceleration=" + acceleration +
                ", pendant=" + pendant +
                ", durability=" + durability +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Car car)) return false;
        return getYear() == car.getYear() && getPower() == car.getPower() && getAcceleration() == car.getAcceleration() && getPendant() == car.getPendant() && getDurability() == car.getDurability() && Objects.equals(getBrand(), car.getBrand()) && Objects.equals(getModel(), car.getModel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBrand(), getModel(), getYear(), getPower(), getAcceleration(), getPendant(), getDurability());
    }
}
