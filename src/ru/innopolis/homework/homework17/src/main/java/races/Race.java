package races;

import cars.Car;
import lombok.*;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Race {

    private int distance;
    private String route;
    private int prizeFund;
    private List<Car> cars;

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    public void addCar(Car car) {
        this.cars.add(car);
    }

}
