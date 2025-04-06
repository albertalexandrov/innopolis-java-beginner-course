package ru.innopolis.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.innopolis.dto.CreateUpdateUserDto;
import ru.innopolis.dto.UserDto;
import ru.innopolis.entity.User;
import ru.innopolis.service.impl.UserServiceImpl;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final ModelMapper modelMapper;

    @GetMapping("/user/{user_id}/")
    public ResponseEntity<UserDto> getUser(@PathVariable long user_id) {
        Optional<User> user = userServiceImpl.findById(user_id);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        var body = modelMapper.map(user.get(), UserDto.class);
        return ResponseEntity.ok().body(body);
    }

    @GetMapping("/users/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        var users = userServiceImpl.findAll();
        var body = users.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();
        return ResponseEntity.ok().body(body);
    }

    @PostMapping("/user/")
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUpdateUserDto data) {
        var user = userServiceImpl.createUser(data);
        var body = modelMapper.map(user, UserDto.class);
        return ResponseEntity.ok().body(body);
    }

    @PutMapping("/user/{userId}/")
    public ResponseEntity<UserDto> updateUser(@PathVariable long userId, @RequestBody CreateUpdateUserDto data) {
        Optional<User> user = userServiceImpl.updateUser(userId, data);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        var body = modelMapper.map(user.get(), UserDto.class);
        return ResponseEntity.ok().body(body);
    }

    @DeleteMapping("/user/{userId}/")
    public void deleteUser(@PathVariable long userId) {
        userServiceImpl.deleteUser(userId);
    }

}
