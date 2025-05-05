package ru.innopolis.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PizzaNotFoundException extends RuntimeException {
    public PizzaNotFoundException() {
        super("Адрес не найдена");
    }
}
