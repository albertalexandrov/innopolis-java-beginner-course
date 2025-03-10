package exceptions;

public class CarNotFoundException extends RuntimeException {

  public CarNotFoundException(String message) {
    super(message);
  }

  public CarNotFoundException() {
    super("Автомобиль не найден");
  }

}
