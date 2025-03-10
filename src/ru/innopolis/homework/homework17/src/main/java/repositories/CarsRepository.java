package repositories;

import cars.Car;

import java.util.List;

public interface CarsRepository {

    void create(Car car);

    Car findById(String id);

    List<Car> findAll();

    void update(Car car);

    void deleteById(String id);

    void deleteAll();
}
