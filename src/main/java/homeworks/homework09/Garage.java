package homeworks.homework09;

import homeworks.homework09.cars.Car;
import homeworks.homework09.cars.PerformanceCar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Garage {
    private List<Car> parkedCars;

    public Garage(List<Car> parkedCars) {
        this.parkedCars = parkedCars;
    }

    public Garage() {
        this.parkedCars = new ArrayList<>();
    }

    public List<Car> getParkedCars() {
        return Collections.unmodifiableList(parkedCars);
    }

    public void setParkedCar(Car car) {
        this.parkedCars.add(car);
    }

    public void modifyCar() {
        // как на консультации
        for (Car car : parkedCars) {
            if (car instanceof PerformanceCar) {
                ((PerformanceCar) car).setAddOn("Спойлер");
            }
        }
    }

    @Override
    public String toString() {
        return "Garage{" +
                "parkedCars=" + parkedCars +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Garage garage)) return false;
        return Objects.equals(getParkedCars(), garage.getParkedCars());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getParkedCars());
    }
}
