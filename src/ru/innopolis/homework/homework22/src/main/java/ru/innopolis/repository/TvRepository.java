package ru.innopolis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innopolis.entity.Tv;

public interface TvRepository extends JpaRepository<Tv, Long> {
}
