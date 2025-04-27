package ru.innopolis.service;

import ru.innopolis.dto.OrderCreateDTO;
import ru.innopolis.dto.OrderUpdateDTO;
import ru.innopolis.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderCreateDTO data);
    Order getOrder(Long orderId);
    List<Order> getOrders(Long userId);
    Order updateOrder(Long orderId, OrderUpdateDTO data);
    void deleteOrder(Long orderId);
}
