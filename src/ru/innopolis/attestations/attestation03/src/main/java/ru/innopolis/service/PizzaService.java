package ru.innopolis.service;

import ru.innopolis.dto.PizzaCreateUpdateDTO;
import ru.innopolis.entity.Pizza;

import java.util.List;

public interface PizzaService {

    Pizza createPizza(PizzaCreateUpdateDTO data);

    Pizza getPizza(Long pizzaId);

    List<Pizza> listPizzas(String name);

    Pizza updatePizza(Long pizzaId, PizzaCreateUpdateDTO data);

    void softDeletePizza(Long pizzaId);

}
