package ru.innopolis.service;

import ru.innopolis.dto.CreateUpdateTvDto;
import ru.innopolis.entity.Tv;

import java.util.List;
import java.util.Optional;

public interface TvService {

    Optional<Tv> findById(long tvId);

    List<Tv> findAll();

    Tv createTv(CreateUpdateTvDto data);

    Optional<Tv> updateTv(long tvId, CreateUpdateTvDto data);

    void deleteTv(long tvId);

}
