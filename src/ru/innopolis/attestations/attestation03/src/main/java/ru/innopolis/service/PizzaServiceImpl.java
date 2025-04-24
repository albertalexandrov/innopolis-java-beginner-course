package ru.innopolis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innopolis.dto.PizzaCreateUpdateDTO;
import ru.innopolis.entity.Pizza;
import ru.innopolis.exceptions.PizzaNotFoundException;
import ru.innopolis.mappers.PizzaMapper;
import ru.innopolis.repository.PizzaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository pizzaRepository;
    private final PizzaMapper pizzaMapper;

    @Override
    public Pizza createPizza(PizzaCreateUpdateDTO data) {
        Pizza pizza = pizzaMapper.map(data);
        pizza.setIsDeleted(false);
        pizzaRepository.save(pizza);
        return pizza;
    }

    @Override
    public Pizza getPizza(Long pizzaId) {
        return pizzaRepository
                .findByIdAndIsDeleted(pizzaId, false)
                .orElseThrow(PizzaNotFoundException::new);
    }

    @Override
    public List<Pizza> listPizzas(String name) {
        if (name == null || name.isEmpty()) {
            return pizzaRepository.findAll();
        } else {
            return pizzaRepository
                    .findAllByIsDeletedAndNameContainsIgnoreCase(false, name);
        }
    }

    @Override
    public Pizza updatePizza(Long pizzaId, PizzaCreateUpdateDTO data) {
        var pizza = pizzaRepository
                .findByIdAndIsDeleted(pizzaId, false)
                .orElseThrow(PizzaNotFoundException::new);
        pizza.setName(data.getName());
        pizzaRepository.save(pizza);
        return pizza;
    }

    @Override
    public void softDeletePizza(Long pizzaId) {
        var pizza = pizzaRepository
                .findByIdAndIsDeleted(pizzaId, false)
                .orElseThrow(PizzaNotFoundException::new);
        pizza.setIsDeleted(true);
        pizzaRepository.save(pizza);
    }
}
