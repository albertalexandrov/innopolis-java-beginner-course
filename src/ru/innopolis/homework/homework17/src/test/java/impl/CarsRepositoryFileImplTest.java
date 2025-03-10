package impl;

import cars.Car;
import cars.PerformanceCar;
import exceptions.CarNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.impl.CarsRepositoryFileImpl;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CarsRepositoryFileImplTest {

    CarsRepositoryFileImpl repo;

    @BeforeEach
    void setUp() {
        repo = new CarsRepositoryFileImpl();
        repo.deleteAll();
    }

    @AfterEach
    void tearDown() {
        repo.deleteAll();
    }

    static Car createCar() {
        PerformanceCar car = new PerformanceCar();
        car.setId("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2");
        car.setBrand("Ferrari");
        car.setModel("F12 Berlinetta");
        car.setYear(2022);
        car.setPower(490);
        car.setAcceleration(2);
        car.setPendant(10);
        car.setDurability(20);
        car.setAddOns(Arrays.asList("addOn1", "addOn2", "addOn3"));
        return car;
    }

    @Test
    void create() throws IOException {
        Car car = createCar();
        repo.create(car);
        try (var reader = Files.newBufferedReader(Paths.get(CarsRepositoryFileImpl.FILE_PATH))) {
            var line = reader.readLine();
            assertEquals(car.getId(), line.split("\\|")[1]);
        }
        assertNotNull(repo.findById(car.getId()));
    }

    @Test
    void findById() {
        Car car = createCar();
        repo.create(car);
        assertNotNull(repo.findById(car.getId()));
    }

    @Test
    void carNotFoundById() {
        var id = UUID.randomUUID().toString();
        assertThrows(CarNotFoundException.class, () -> repo.findById(id));
    }

    @Test
    void findAll() {
        var car = createCar();
        repo.create(car);
        List<Car> cars = repo.findAll();
        assertEquals(1, cars.size());
        assertEquals(car, cars.getFirst());
    }

    @Test
    void updateExistingCar() throws IOException {
        var car = createCar();
        repo.create(car);
        int newAge = car.getYear() + 1;
        car.setYear(newAge);
        repo.update(car);
        try (var reader = Files.newBufferedReader(Paths.get(CarsRepositoryFileImpl.FILE_PATH))) {
            var line = reader.readLine();
            assertEquals(car.getYear(), Integer.parseInt(line.split("\\|")[4]));
        }
    }

    @Test
    void createCarWhenUpdate() throws IOException {
        var car = createCar();
        repo.update(car);
        try (var reader = Files.newBufferedReader(Paths.get(CarsRepositoryFileImpl.FILE_PATH))) {
            var line = reader.readLine();
            assertEquals(car.getId(), line.split("\\|")[1]);
        }
    }

    @Test
    void deleteById() throws IOException {
        var car = createCar();
        repo.create(car);
        repo.deleteById(car.getId());
        assertThrows(CarNotFoundException.class, () -> repo.findById(car.getId()));
        assertFileEmpty();
    }

    @Test
    void deleteAll() throws IOException {
        var car = createCar();
        repo.create(car);
        repo.deleteAll();
        assertFileEmpty();
    }

    void assertFileEmpty() throws IOException {
        try (var reader = Files.newBufferedReader(Paths.get(CarsRepositoryFileImpl.FILE_PATH))) {
            assertEquals(-1, reader.read());
        }
    }

}