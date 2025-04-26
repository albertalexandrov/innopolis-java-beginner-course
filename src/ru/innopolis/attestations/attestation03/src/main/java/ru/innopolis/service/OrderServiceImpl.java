package ru.innopolis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innopolis.dto.OrderCreateDTO;
import ru.innopolis.dto.OrderUpdateDTO;
import ru.innopolis.entity.Order;
import ru.innopolis.exceptions.BadRequestException;
import ru.innopolis.exceptions.OrderNotFoundException;
import ru.innopolis.mappers.OrderMapper;
import ru.innopolis.repository.AddressRepository;
import ru.innopolis.repository.OrderRepository;
import ru.innopolis.repository.PizzaRepository;
import ru.innopolis.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PizzaRepository pizzaRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final OrderMapper orderMapper;

    @Override
    public Order createOrder(OrderCreateDTO data) {
        var order = new Order();
        var pizza = pizzaRepository
                .findByIdAndIsDeleted(data.getPizzaId(), false)
                .orElseThrow(() -> new BadRequestException("Пицца не найдена"));
        order.setPizza(pizza);
        var user = userRepository
                .findFirstByIdAndIsDeleted(data.getUserId(), false)
                .orElseThrow(() -> new BadRequestException("Пользователь не найден"));
        order.setUser(user);
        var address = addressRepository
                .findFirstByIdAndIsDeleted(data.getAddressId(), false)
                .orElseThrow(() -> new BadRequestException("Адрес не найден"));
        order.setAddress(address);
        order.setIsDeleted(false);
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long orderId, OrderUpdateDTO data) {
        var order = orderRepository
                .findFirstByIdAndIsDeleted(orderId, false)
                .orElseThrow(OrderNotFoundException::new);
        var pizza = pizzaRepository
                .findByIdAndIsDeleted(data.getPizzaId(), false)
                .orElseThrow(() -> new BadRequestException("Пицца не найдена"));
        order.setPizza(pizza);
        var address = addressRepository
                .findFirstByIdAndIsDeleted(data.getAddressId(), false)
                .orElseThrow(() -> new BadRequestException("Адрес не найден"));
        order.setAddress(address);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrders(Long userId) {
        if (userId == null) {
            return orderRepository.findByIsDeleted(false);
        } else {
            return orderRepository.findByUserIdAndIsDeleted(userId, false);
        }
    }

    @Override
    public void deleteOrder(Long orderId) {
        var order = orderRepository
                .findFirstByIdAndIsDeleted(orderId, false)
                .orElseThrow(OrderNotFoundException::new);
        order.setIsDeleted(true);
        orderRepository.save(order);
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository
                .findFirstByIdAndIsDeleted(orderId, false)
                .orElseThrow(OrderNotFoundException::new);
    }
}
