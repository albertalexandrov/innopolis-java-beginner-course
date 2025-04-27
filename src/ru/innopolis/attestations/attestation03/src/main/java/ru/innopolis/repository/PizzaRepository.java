package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.entity.Pizza;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    Optional<Pizza> findFirstByIdAndIsDeleted(Long id, Boolean isDeleted);
    List<Pizza> findAllByIsDeletedAndNameContainsIgnoreCase(Boolean isDeleted, String name);
    List<Pizza> findAllByIsDeleted(Boolean isDeleted);
}
