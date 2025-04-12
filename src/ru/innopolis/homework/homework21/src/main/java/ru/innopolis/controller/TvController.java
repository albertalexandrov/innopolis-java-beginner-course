package ru.innopolis.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.innopolis.dto.CreateUpdateTvDto;
import ru.innopolis.dto.TvDto;
import ru.innopolis.entity.Tv;
import ru.innopolis.service.impl.TvServiceImpl;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TvController {

    private final TvServiceImpl tvServiceImpl;
    private final ModelMapper modelMapper;

    @GetMapping("/tv/{tv_id}/")
    public ResponseEntity<TvDto> getTv(@PathVariable long tv_id) {
        Optional<Tv> tv = tvServiceImpl.findById(tv_id);
        if (tv.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TV not found");
        }
        var body = modelMapper.map(tv.get(), TvDto.class);
        return ResponseEntity.ok().body(body);
    }

    @GetMapping("/tv/")
    public ResponseEntity<List<TvDto>> getAllTv() {
        var tvs = tvServiceImpl.findAll();
        var body = tvs.stream().map(tv -> modelMapper.map(tv, TvDto.class)).toList();
        return ResponseEntity.ok().body(body);
    }

    @PostMapping("/tv/")
    public ResponseEntity<TvDto> createTv(@RequestBody CreateUpdateTvDto data) {
        var tv = tvServiceImpl.createTv(data);
        var body = modelMapper.map(tv, TvDto.class);
        return ResponseEntity.ok().body(body);
    }

    @PutMapping("/tv/{tvId}/")
    public ResponseEntity<TvDto> updateTv(@PathVariable long tvId, @RequestBody CreateUpdateTvDto data) {
        Optional<Tv> tv = tvServiceImpl.updateTv(tvId, data);
        if (tv.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TV not found");
        }
        var body = modelMapper.map(tv.get(), TvDto.class);
        return ResponseEntity.ok().body(body);
    }

    @DeleteMapping("/tv/{tvId}/")
    public void deleteTv(@PathVariable long tvId) {
        tvServiceImpl.deleteTv(tvId);
    }

}
