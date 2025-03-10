package exceptions;

public class CarAlreadyExistsException extends RuntimeException {

    public CarAlreadyExistsException(String message) {
        super(message);
    }

    public CarAlreadyExistsException() {
        super("Автомобиль уже существует");
    }

}
