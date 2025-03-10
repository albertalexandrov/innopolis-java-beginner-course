package races;

import cars.Car;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class CasualRace extends Race {

    public CasualRace(int distance, String route, int prizeFund, List<Car> cars) {
        super(distance, route, prizeFund, cars);
    }

    @Override
    public String toString() {  // Lobmok не используется, тк поля базового класса им не подтягиваются
        return "CasualRace(" +
                "distance=" + getDistance() +
                ", route='" + getRoute() + '\'' +
                ", prizeFund=" + getPrizeFund() +
                ", cars=" + getCars() +
                ')';
    }

    // методы equals и hashCode наследуются от родительского класса Race

}
