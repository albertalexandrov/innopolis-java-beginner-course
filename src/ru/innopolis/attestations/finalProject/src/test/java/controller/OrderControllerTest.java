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
import ru.innopolis.dto.OrderCreateDTO;
import ru.innopolis.dto.OrderUpdateDTO;
import ru.innopolis.entity.Address;
import ru.innopolis.entity.Order;
import ru.innopolis.entity.Pizza;
import ru.innopolis.entity.User;
import ru.innopolis.repository.AddressRepository;
import ru.innopolis.repository.OrderRepository;
import ru.innopolis.repository.PizzaRepository;
import ru.innopolis.repository.UserRepository;

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
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserRepository userRepository;

    @MockitoBean
    private PizzaRepository pizzaRepository;

    @MockitoBean
    private OrderRepository orderRepository;

    @MockitoBean
    private AddressRepository addressRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private OrderCreateDTO orderCreateDTO;
    private OrderUpdateDTO orderUpdateDTO;
    private Order order;
    private Pizza pizza1;
    private Pizza pizza2;
    private Address address1;
    private Address address2;
    private User user;

    @BeforeEach
    public void setUp() {
        user = User
                .builder()
                .id(1L)
                .lastName("Иванов")
                .firstName("Иван")
                .phoneNumber("+79991234567")
                .isDeleted(false)
                .build();
        pizza1 = Pizza
                .builder()
                .id(1L)
                .name("Пеперони")
                .isDeleted(false)
                .build();
        pizza2 = Pizza
                .builder()
                .id(2L)
                .name("Гавайская")
                .isDeleted(false)
                .build();
        address1 = Address
                .builder()
                .id(1L)
                .locality("г. Казань")
                .street("ул. Павлюхина")
                .house("1")
                .porch("2")
                .floor("3")
                .apartment("4")
                .user(user)
                .build();
        address2 = Address
                .builder()
                .id(2L)
                .locality("г. Москва")
                .street("ул. Ленина")
                .house("2")
                .porch("3")
                .floor("4")
                .apartment("5")
                .user(user)
                .build();
        orderCreateDTO = OrderCreateDTO
                .builder()
                .userId(user.getId())
                .pizzaId(pizza1.getId())
                .addressId(address1.getId())
                .build();
        orderUpdateDTO = OrderUpdateDTO
                .builder()
                .pizzaId(pizza2.getId())
                .addressId(address2.getId())
                .build();
        order = Order
                .builder()
                .id(1L)
                .pizza(pizza1)
                .user(user)
                .address(address1)
                .isDeleted(false)
                .build();
    }

    @Test
    void createPizza() throws Exception {
        when(pizzaRepository.findFirstByIdAndIsDeleted(pizza1.getId(), false))
                .thenReturn(Optional.of(pizza1));
        when(userRepository.findFirstByIdAndIsDeleted(user.getId(), false))
                .thenReturn(Optional.of(user));
        when(addressRepository.findFirstByIdAndIsDeleted(address1.getId(), false))
                .thenReturn(Optional.of(address1));
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        ResultActions response = mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderCreateDTO))
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isCreated());
        assertRetrieveBody(response);
    }

    @Test
    void getOrder() throws Exception {
        when(orderRepository.findFirstByIdAndIsDeleted(order.getId(), false))
                .thenReturn(Optional.of(order));
        ResultActions response = mockMvc.perform(get("/order/{id}", order.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isOk());
        assertRetrieveBody(response);
    }

    @Test
    void getPizza_PizzaNotFound() throws Exception {
        when(orderRepository.findFirstByIdAndIsDeleted(order.getId(), false))
                .thenReturn(Optional.empty());
        ResultActions response = mockMvc.perform(get("/order/{id}", pizza1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isNotFound());
    }

    @Test
    void getOrders() throws Exception {
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        when(orderRepository.findAllByIsDeleted(false)).thenReturn(orders);
        ResultActions response = mockMvc.perform(get("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", CoreMatchers.is(order.getId().intValue())))
                .andExpect(jsonPath("$[0].user.id", CoreMatchers.is(user.getId().intValue())))
                .andExpect(jsonPath("$[0].user.lastName", CoreMatchers.is(user.getLastName())))
                .andExpect(jsonPath("$[0].user.firstName", CoreMatchers.is(user.getFirstName())))
                .andExpect(jsonPath("$[0].user.phoneNumber", CoreMatchers.is(user.getPhoneNumber())))
                .andExpect(jsonPath("$[0].pizza.id", CoreMatchers.is(pizza1.getId().intValue())))
                .andExpect(jsonPath("$[0].pizza.name", CoreMatchers.is(pizza1.getName())))
                .andExpect(jsonPath("$[0].address.id", CoreMatchers.is(address1.getId().intValue())))
                .andExpect(jsonPath("$[0].address.locality", CoreMatchers.is(address1.getLocality())))
                .andExpect(jsonPath("$[0].address.street", CoreMatchers.is(address1.getStreet())))
                .andExpect(jsonPath("$[0].address.house", CoreMatchers.is(address1.getHouse())))
                .andExpect(jsonPath("$[0].address.porch", CoreMatchers.is(address1.getPorch())))
                .andExpect(jsonPath("$[0].address.floor", CoreMatchers.is(address1.getFloor())))
                .andExpect(jsonPath("$[0].address.apartment", CoreMatchers.is(address1.getApartment())));
        when(orderRepository.findAllByUserIdAndIsDeleted(user.getId(), false)).thenReturn(orders);
        response = mockMvc.perform(get("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .param("userId", user.getId().toString())
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isOk());
    }

    @Test
    void updateOrder() throws Exception {
        when(orderRepository.findFirstByIdAndIsDeleted(order.getId(), false))
                .thenReturn(Optional.of(order));
        when(orderRepository.save(order)).thenReturn(order);
        when(pizzaRepository.findFirstByIdAndIsDeleted(pizza2.getId(), false))
                .thenReturn(Optional.of(pizza2));
        when(addressRepository.findFirstByIdAndIsDeleted(address2.getId(), false))
                .thenReturn(Optional.of(address2));
        ResultActions response = mockMvc.perform(put("/order/{id}", order.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderUpdateDTO))
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(jsonPath("$.id", CoreMatchers.is(order.getId().intValue())))
                .andExpect(jsonPath("$.user.id", CoreMatchers.is(user.getId().intValue())))
                .andExpect(jsonPath("$.user.lastName", CoreMatchers.is(user.getLastName())))
                .andExpect(jsonPath("$.user.firstName", CoreMatchers.is(user.getFirstName())))
                .andExpect(jsonPath("$.user.phoneNumber", CoreMatchers.is(user.getPhoneNumber())))
                .andExpect(jsonPath("$.pizza.id", CoreMatchers.is(pizza2.getId().intValue())))
                .andExpect(jsonPath("$.pizza.name", CoreMatchers.is(pizza2.getName())))
                .andExpect(jsonPath("$.address.id", CoreMatchers.is(address2.getId().intValue())))
                .andExpect(jsonPath("$.address.locality", CoreMatchers.is(address2.getLocality())))
                .andExpect(jsonPath("$.address.street", CoreMatchers.is(address2.getStreet())))
                .andExpect(jsonPath("$.address.house", CoreMatchers.is(address2.getHouse())))
                .andExpect(jsonPath("$.address.porch", CoreMatchers.is(address2.getPorch())))
                .andExpect(jsonPath("$.address.floor", CoreMatchers.is(address2.getFloor())))
                .andExpect(jsonPath("$.address.apartment", CoreMatchers.is(address2.getApartment())));
    }

    @Test
    void updateOrder_OrderNotFound() throws Exception {
        when(orderRepository.findFirstByIdAndIsDeleted(order.getId(), false))
                .thenReturn(Optional.empty());
        ResultActions response = mockMvc.perform(put("/order/{id}", order.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderUpdateDTO))
                .accept(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteOrder() throws Exception {
        when(orderRepository.findFirstByIdAndIsDeleted(order.getId(), false))
                .thenReturn(Optional.of(order));
        ResultActions response = mockMvc.perform(delete("/order/{id}", order.getId())
                .contentType(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteOrder_OrderNotFound() throws Exception {
        when(orderRepository.findFirstByIdAndIsDeleted(order.getId(), false))
                .thenReturn(Optional.empty());
        ResultActions response = mockMvc.perform(delete("/order/{id}", order.getId())
                .contentType(MediaType.APPLICATION_JSON)
        );
        response
                .andExpect(status().isNotFound());
    }

    private void assertRetrieveBody(ResultActions response) throws Exception {
        response
                .andExpect(jsonPath("$.id", CoreMatchers.is(order.getId().intValue())))
                .andExpect(jsonPath("$.user.id", CoreMatchers.is(user.getId().intValue())))
                .andExpect(jsonPath("$.user.lastName", CoreMatchers.is(user.getLastName())))
                .andExpect(jsonPath("$.user.firstName", CoreMatchers.is(user.getFirstName())))
                .andExpect(jsonPath("$.user.phoneNumber", CoreMatchers.is(user.getPhoneNumber())))
                .andExpect(jsonPath("$.pizza.id", CoreMatchers.is(pizza1.getId().intValue())))
                .andExpect(jsonPath("$.pizza.name", CoreMatchers.is(pizza1.getName())))
                .andExpect(jsonPath("$.address.id", CoreMatchers.is(address1.getId().intValue())))
                .andExpect(jsonPath("$.address.locality", CoreMatchers.is(address1.getLocality())))
                .andExpect(jsonPath("$.address.street", CoreMatchers.is(address1.getStreet())))
                .andExpect(jsonPath("$.address.house", CoreMatchers.is(address1.getHouse())))
                .andExpect(jsonPath("$.address.porch", CoreMatchers.is(address1.getPorch())))
                .andExpect(jsonPath("$.address.floor", CoreMatchers.is(address1.getFloor())))
                .andExpect(jsonPath("$.address.apartment", CoreMatchers.is(address1.getApartment())));
    }

}
