package races;

import cars.Car;

import java.util.List;

public class DriftRace extends Race {

    public DriftRace(int distance, String route, int prizeFund, List<Car> cars) {
        super(distance, route, prizeFund, cars);
    }

    public DriftRace(int distance, String route, int prizeFund) {
        super(distance, route, prizeFund);
    }

    public DriftRace() {
    }

    @Override
    public String toString() {
        return "DriftRace{" +
                "distance=" + getDistance() +
                ", route='" + getRoute() + '\'' +
                ", prizeFund=" + getPrizeFund() +
                ", cars=" + getCars() +
                '}';
    }

    // методы equals и hashCode наследуются от родительского класса Race

}
