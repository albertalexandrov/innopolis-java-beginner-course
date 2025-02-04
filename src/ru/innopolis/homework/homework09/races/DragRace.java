package ru.innopolis.homework.homework09.races;

import ru.innopolis.homework.homework09.cars.Car;

import java.util.List;

public class DragRace extends Race {

    public DragRace(int distance, String route, int prizeFund, List<Car> cars) {
        super(distance, route, prizeFund, cars);
    }

    public DragRace(int distance, String route, int prizeFund) {
        super(distance, route, prizeFund);
    }

    public DragRace() {
    }

    @Override
    public String toString() {
        return "DragRace{" +
                "distance=" + getDistance() +
                ", route='" + getRoute() + '\'' +
                ", prizeFund=" + getPrizeFund() +
                ", cars=" + getCars() +
                '}';
    }

    // методы equals и hashCode наследуются от родительского класса Race

}
