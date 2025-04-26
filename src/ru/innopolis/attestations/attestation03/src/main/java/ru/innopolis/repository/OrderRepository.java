package ru.innopolis.repository;

import org.springframework.data.repository.CrudRepository;
import ru.innopolis.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Optional<Order> findFirstByIdAndIsDeleted(Long id, Boolean isDeleted);

    List<Order> findByUserIdAndIsDeleted(Long userId, Boolean isDeleted);

    List<Order> findByIsDeleted(Boolean isDeleted);
}
