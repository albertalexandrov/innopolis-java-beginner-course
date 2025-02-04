package ru.innopolis.homework.homework09.races;

import ru.innopolis.homework.homework09.cars.Car;

import java.util.List;

public class CasualRace extends Race {

    // todo:
    public CasualRace(int distance, String route, int prizeFund, List<Car> cars) {
        super(distance, route, prizeFund, cars);
    }

    public CasualRace(int distance, String route, int prizeFund) {
        super(distance, route, prizeFund);
    }

    public CasualRace() {
    }

    @Override
    public String toString() {
        return "CasualRace{" +
                "distance=" + getDistance() +
                ", route='" + getRoute() + '\'' +
                ", prizeFund=" + getPrizeFund() +
                ", cars=" + getCars() +
                '}';
    }

    // методы equals и hashCode наследуются от родительского класса Race
    // todo:
    //  но тогда возникает вопрос: а что если вдруг в какой либо наследник будет добавлено поле и его забудут
    //  добавить в методы equals и hashCode? легко можно забыть. как быть тогда? или в каждом наследнике все дублировать?

}
