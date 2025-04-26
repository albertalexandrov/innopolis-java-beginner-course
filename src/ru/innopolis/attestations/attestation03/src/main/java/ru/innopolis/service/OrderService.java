package ru.innopolis.service;

import org.springframework.stereotype.Service;
import ru.innopolis.dto.OrderCreateDTO;
import ru.innopolis.dto.OrderUpdateDTO;
import ru.innopolis.dto.PizzaCreateUpdateDTO;
import ru.innopolis.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createOrder(OrderCreateDTO data);
    Order updateOrder(Long orderId, OrderUpdateDTO data);
    List<Order> getOrders(Long userId);
    void deleteOrder(Long orderId);
    Order getOrder(Long orderId);
}
