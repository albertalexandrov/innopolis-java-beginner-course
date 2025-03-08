package homeworks.homework09.races;

import homeworks.homework09.cars.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Race {
    private int distance;
    private String route;
    private int prizeFund;
    private List<Car> cars;

    public Race(int distance, String route, int prizeFund, List<Car> cars) {
        this.distance = distance;
        this.route = route;
        this.prizeFund = prizeFund;
        this.cars = cars;
    }

    public Race(int distance, String route, int prizeFund) {
        this(distance, route, prizeFund, new ArrayList<>());
    }

    public Race() {
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public int getPrizeFund() {
        return prizeFund;
    }

    public void setPrizeFund(int prizeFund) {
        this.prizeFund = prizeFund;
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    public void setCar(Car car) {
        this.cars.add(car);
    }

    @Override
    public String toString() {
        return "Race{" +
                "distance=" + distance +
                ", route='" + route + '\'' +
                ", prizeFund=" + prizeFund +
                ", cars=" + cars +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Race race)) return false;
        return getDistance() == race.getDistance() && getPrizeFund() == race.getPrizeFund() && Objects.equals(getRoute(), race.getRoute()) && Objects.equals(getCars(), race.getCars());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDistance(), getRoute(), getPrizeFund(), getCars());
    }
}
