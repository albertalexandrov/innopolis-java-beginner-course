package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.innopolis.Application;
import ru.innopolis.dto.PizzaCreateUpdateDTO;
import ru.innopolis.entity.Pizza;
import ru.innopolis.repository.PizzaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class PizzaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PizzaRepository pizzaRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private PizzaCreateUpdateDTO pizzaCreateDTO;
    private PizzaCreateUpdateDTO pizzaUpdateDTO;
    private Pizza pizza;

    @BeforeEach
    public void setUp() {
        var name = "Пеперони";
        pizzaCreateDTO = PizzaCreateUpdateDTO
                .builder()
                .name(name)
                .build();
        pizzaUpdateDTO = PizzaCreateUpdateDTO
                .builder()
                .name("new " + name)
                .build();
        pizza = Pizza
                .builder()
                .id(1L)
                .name(name)
                .isDeleted(false)
                .build();
    }

    @Test
    void createPizza() throws Exception {
        when(pizzaRepository.save(any(Pizza.class))).thenReturn(pizza);
        ResultActions response = mockMvc.perform(post("/pizza")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pizzaCreateDTO))
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", CoreMatchers.is(pizza.getId().intValue())))
                .andExpect(jsonPath("$.name", CoreMatchers.is(pizza.getName())));
    }

    @Test
    void getPizza() throws Exception {
        when(pizzaRepository.findFirstByIdAndIsDeleted(pizza.getId(), false))
                .thenReturn(Optional.of(pizza));
        ResultActions response = mockMvc.perform(get("/pizza/{id}", pizza.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is(pizza.getId().intValue())))
                .andExpect(jsonPath("$.name", CoreMatchers.is(pizza.getName())));
    }

    @Test
    void getPizza_PizzaNotFound() throws Exception {
        when(pizzaRepository.findFirstByIdAndIsDeleted(pizza.getId(), false))
                .thenReturn(Optional.empty());
        ResultActions response = mockMvc.perform(get("/pizza/{id}", pizza.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isNotFound());
    }

    @Test
    void getPizzas() throws Exception {
        List<Pizza> pizzas = new ArrayList<>();
        pizzas.add(pizza);
        when(pizzaRepository.findAllByIsDeleted(false)).thenReturn(pizzas);
        ResultActions response = mockMvc.perform(get("/pizzas")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", CoreMatchers.is(pizzas.size())))
                .andExpect(jsonPath("$[0].id", CoreMatchers.is(pizza.getId().intValue())))
                .andExpect(jsonPath("$[0].name", CoreMatchers.is(pizza.getName())));
        var name = "гавайская";
        when(pizzaRepository.findAllByIsDeletedAndNameContainsIgnoreCase(false, name)).thenReturn(pizzas);
        response = mockMvc.perform(get("/pizzas")
                .contentType(MediaType.APPLICATION_JSON)
                .param("name", name)
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isOk());
    }

    @Test
    void updatePizza() throws Exception {
        when(pizzaRepository.findFirstByIdAndIsDeleted(pizza.getId(), false))
                .thenReturn(Optional.of(pizza));
        when(pizzaRepository.save(pizza)).thenReturn(pizza);
        ResultActions response = mockMvc.perform(put("/pizza/{id}", pizza.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pizzaUpdateDTO))
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", CoreMatchers.is(pizza.getId().intValue())))
                .andExpect(jsonPath("$.name", CoreMatchers.is(pizzaUpdateDTO.getName())));
    }

    @Test
    void updatePizza_PizzaNotFound() throws Exception {
        when(pizzaRepository.findFirstByIdAndIsDeleted(pizza.getId(), false))
                .thenReturn(Optional.empty());
        ResultActions response = mockMvc.perform(put("/pizza/{id}", pizza.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pizzaUpdateDTO))
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isNotFound());
    }

    @Test
    void deletePizza() throws Exception {
        when(pizzaRepository.findFirstByIdAndIsDeleted(pizza.getId(), false))
                .thenReturn(Optional.of(pizza));
        ResultActions response = mockMvc.perform(delete("/pizza/{id}", pizza.getId())
                .contentType(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isNoContent());
    }

    @Test
    void deletePizza_PizzaNotFound() throws Exception {
        when(pizzaRepository.findFirstByIdAndIsDeleted(pizza.getId(), false))
                .thenReturn(Optional.empty());
        ResultActions response = mockMvc.perform(delete("/pizza/{id}", pizza.getId())
                .contentType(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isNotFound());
    }

}
