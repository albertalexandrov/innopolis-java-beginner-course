package ru.innopolis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.dto.UserCreateUpdateDTO;
import ru.innopolis.dto.UserRetrieveDTO;
import ru.innopolis.mappers.UserMapper;
import ru.innopolis.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "Контроллер для работы с пользователями")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Создает пользователя")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserRetrieveDTO> user(@RequestBody UserCreateUpdateDTO data) {
        var user = userService.createUser(data);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userMapper.map(user));
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Возвращает пользователя")
    public ResponseEntity<UserRetrieveDTO> user(
            @Parameter(description = "Идентификатор пользователя", example = "1") @PathVariable(name = "id") Long userId
    ) {
        var user = userService.getUser(userId);
        return ResponseEntity.ok(userMapper.map(user));
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Возвращает пользователей")
    public ResponseEntity<List<UserRetrieveDTO>> getUsers() {
        var users = userService.listUsers();
        return ResponseEntity.ok(userMapper.map(users));
    }

    @PutMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Обновляет пользователя")
    public ResponseEntity<UserRetrieveDTO> updateUser(
            @Parameter(description = "Идентификатор пользователя", example = "1") @PathVariable(name = "id") Long userId,
            @Valid @RequestBody UserCreateUpdateDTO data
    ) {
        var user = userService.updateUser(userId, data);

        return ResponseEntity
                .ok(userMapper.map(user));
    }

    @DeleteMapping("/user/{id}")
    @Operation(summary = "Удаляет пользователя")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePizza(
            @Parameter(description = "Идентификатор пользователя", example = "1") @PathVariable(name = "id") Long userId) {
        userService.deleteUser(userId);
    }

}
