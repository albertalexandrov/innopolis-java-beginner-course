package homeworks.homework09.cars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PerformanceCar extends Car {
    private List<String> addOns;  // todo: почему в задании прописывается массив строк, а не список, который динамический как и количество аддонов

    public PerformanceCar(String brand, String model, int year, int power, int acceleration, int pendant, int durability, List<String> addOns) {
        super(brand, model, year, (int)(power * 1.5), acceleration, (int)(pendant * 0.75), durability);
        this.addOns = addOns;
    }

    public PerformanceCar(String brand, String model, int year, int power, int acceleration, int pendant, int durability) {
        this(brand, model, year, (int)(power * 1.5), acceleration, (int)(pendant * 0.75), durability, new ArrayList<>());
    }

    public PerformanceCar() {
    }

    public List<String> getAddOns() {
        return Collections.unmodifiableList(addOns);
    }

    public void setAddOn(String addOn) {
        addOns.add(addOn);
    }

    @Override
    public String toString() {
        return "PerformanceCar{" +
                "brand='" + getBrand() + '\'' +
                ", model='" + getModel() + '\'' +
                ", year=" + getYear() +
                ", power=" + getPower() +
                ", acceleration=" + getAcceleration() +
                ", pendant=" + getPendant() +
                ", durability=" + getDurability() +
                ", addOns=" + addOns +
                '}';
    }
}
