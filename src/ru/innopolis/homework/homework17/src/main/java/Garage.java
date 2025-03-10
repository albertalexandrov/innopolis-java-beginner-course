import cars.Car;
import cars.PerformanceCar;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@ToString
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class Garage {

    private List<Car> parkedCars;

    public List<Car> getParkedCars() {
        return Collections.unmodifiableList(parkedCars);
    }

    public void addParkedCar(Car car) {
        this.parkedCars.add(car);
    }

    public void modifyCar() {
        // как на консультации
        for (Car car : parkedCars) {
            if (car instanceof PerformanceCar) {
                ((PerformanceCar) car).addAddOn("Спойлер");
            }
        }
    }

}
