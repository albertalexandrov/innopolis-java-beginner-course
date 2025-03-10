package exceptions;

public class CarTypeNotSpecifiedException extends RuntimeException {
    public CarTypeNotSpecifiedException() {
      super("Задайте тип автомобиля");
    }
}
