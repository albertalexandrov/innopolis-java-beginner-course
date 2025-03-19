package ru.inno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inno.model.Order;

public interface OrdersRepository extends JpaRepository<Order, Long> {
}
