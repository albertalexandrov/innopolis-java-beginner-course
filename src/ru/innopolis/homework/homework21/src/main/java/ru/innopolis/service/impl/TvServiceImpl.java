package ru.innopolis.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.innopolis.dto.CreateUpdateTvDto;
import ru.innopolis.entity.Tv;
import ru.innopolis.repository.TvRepository;
import ru.innopolis.service.TvService;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class TvServiceImpl implements TvService {

    private final ModelMapper modelMapper;

    private final TvRepository tvRepository;

    public Optional<Tv> findById(long tvId) {
        return tvRepository.findById(tvId);
    }

    public List<Tv> findAll() {
        return tvRepository.findAll();
    }

    public Tv createTv(CreateUpdateTvDto data) {
        var user = modelMapper.map(data, Tv.class);
        return tvRepository.save(user);
    }

    public Optional<Tv> updateTv(long tvId, CreateUpdateTvDto data) {
        Optional<Tv> optionalTv = tvRepository.findById(tvId);
        if (optionalTv.isEmpty()) {
            return optionalTv;
        }
        Tv tv = optionalTv.get();
        tv.setBrand(data.getBrand());
        tv.setModel(data.getModel());
        tv.setModel(data.getScreen());
        tv = tvRepository.save(tv);
        return Optional.of(tvRepository.save(tv));
    }

    public void deleteTv(long tvId) {
        tvRepository.deleteById(tvId);
    }

}
