package homeworks.homework11;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws IOException {
        final var INPUT_FILE_PATH = "src/main/java/homeworks/homework11/input.txt";
        final var OUTPUT_FILE_PATH = "src/main/java/homeworks/homework11/output.txt";
        final var CAR_ROW_FORMAT = "%-8s\t%-8s\t%-8s\t%-8s\t%-8s\n";

        try (
                var reader = new BufferedReader(new FileReader(INPUT_FILE_PATH));
                var writer = new BufferedWriter(new FileWriter(OUTPUT_FILE_PATH));
        ) {
            writer.write("Автомобили в базе:\n");
            writer.write(String.format(CAR_ROW_FORMAT, "Number", "Model", "Color", "Mileage", "Cost"));
            reader.readLine();  // пропускаем первую строку, которая несет в себе заголовки;
            String line;
            var cars = new ArrayList<Car>();
            while ((line = reader.readLine()) != null) {
                var tokens = line.split("\\|");
                var car = createCar(tokens);
                cars.add(car);
                System.out.println("Создан автомобиль: " + car);
                writer.write(String.format(CAR_ROW_FORMAT, car.getNumber(), car.getModel(), car.getColor(), car.getMileage(), car.getCost()));
            }

            writer.write("\n");

            // вывести номера всех автомобилей, имеющих заданный в переменной цвет colorToFind или нулевой пробег mileageToFind

            var colorToFind = "Black";
            var mileageToFind = 0;
            var numbers = cars.stream()
                    .filter(car -> car.getColor().equals(colorToFind) || car.getMileage().equals(mileageToFind))
                    .map(Car::getNumber)
                    .collect(Collectors.joining(" "));
            writer.write(String.format("Номера автомобилей по цвету или пробегу: %s\n", numbers));

            // вывести количество уникальных моделей в ценовом диапазоне от n до m тыс.

            var n = 700_000;
            var m = 800_000;
            var count = cars.stream().filter(car -> n < car.getCost() && car.getCost() < m).count();
            writer.write(String.format("Уникальные автомобили: %d шт.", count));
        }
    }

    private static Car createCar(String[] tokens) {
        var number = tokens[0];
        var model = tokens[1];
        var color = tokens[2];
        var mileage = Integer.parseInt(tokens[3]);
        var cost = Integer.parseInt(tokens[4]);
        return new Car(number, model, color, mileage, cost);
    }
}
