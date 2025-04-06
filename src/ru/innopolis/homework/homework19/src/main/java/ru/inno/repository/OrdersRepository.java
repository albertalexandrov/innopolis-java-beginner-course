package ru.inno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inno.model.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Order, Long> {

    List<Order> findOrderByCreatedAt(LocalDateTime createdAt);

}
