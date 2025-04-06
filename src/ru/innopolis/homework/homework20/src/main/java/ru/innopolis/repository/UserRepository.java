package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
