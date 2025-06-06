package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByIdAndIsDeleted(Long id, Boolean isDeleted);

    List<User> findAllByIsDeleted(Boolean isDeleted);
}
