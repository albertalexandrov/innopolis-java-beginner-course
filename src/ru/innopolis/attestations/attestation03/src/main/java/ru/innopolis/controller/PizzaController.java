package ru.innopolis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.dto.PizzaCreateUpdateDTO;
import ru.innopolis.dto.RetrievePizzaDTO;
import ru.innopolis.mappers.PizzaMapper;
import ru.innopolis.service.PizzaService;

import java.util.List;

@RestController
@AllArgsConstructor
@Tag(name = "Пицца", description = "Контроллер для работы с пиццей")
public class PizzaController {

    private final PizzaService pizzaService;
    private final PizzaMapper pizzaMapper;

    @PostMapping("/pizza")
    @Operation(summary = "Создает пиццу")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RetrievePizzaDTO> createPizza(@RequestBody PizzaCreateUpdateDTO data) {
        var pizza = pizzaService.createPizza(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(pizzaMapper.map(pizza));
    }

    @GetMapping("/pizza/{id}")
    @Operation(summary = "Возвращает пиццу")
    public ResponseEntity<RetrievePizzaDTO> getPizza(@PathVariable("id") Long pizzaId) {
        var pizza = pizzaService.getPizza(pizzaId);
        return ResponseEntity.ok(pizzaMapper.map(pizza));
    }

    @GetMapping("/pizzas")
    @Operation(summary = "Возвращает список пицц")
    public ResponseEntity<List<RetrievePizzaDTO>> listPizzas(
            @Parameter(description = "Название пиццы", example = "пеперони") @RequestParam(required = false) String name) {
        var pizzas = pizzaService.listPizzas(name);
        return ResponseEntity.ok(pizzaMapper.map(pizzas));
    }

    @PutMapping("/pizza/{id}")
    @Operation(summary = "Обновляет пиццу")
    public ResponseEntity<RetrievePizzaDTO> updatePizza(
            @PathVariable("id") Long pizzaId, @RequestBody PizzaCreateUpdateDTO data
    ) {
        var pizza = pizzaService.updatePizza(pizzaId, data);
        return ResponseEntity.ok(pizzaMapper.map(pizza));
    }

    @DeleteMapping("/pizza/{id}")
    @Operation(summary = "Удаляет пиццу")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePizza(@Parameter(description = "Идентификатор пиццы", example = "1") @PathVariable(name = "id") Long pizzaId) {
        pizzaService.softDeletePizza(pizzaId);
    }

}
