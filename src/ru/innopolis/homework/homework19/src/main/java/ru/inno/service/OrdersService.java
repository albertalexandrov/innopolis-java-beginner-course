package ru.inno.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.inno.model.Order;
import ru.inno.repository.OrdersRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;

    public Order getOrder(long id) {
        return ordersRepository.findById(id).orElseThrow();
    }

    public List<Order> getOrders() {
        return ordersRepository.findAll();
    }

    public void deleteAllOrders() {
        ordersRepository.deleteAll();
    }

}
