package ru.innopolis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.dto.AddressCreateDTO;
import ru.innopolis.dto.AddressRetrieveDTO;
import ru.innopolis.dto.AddressUpdateDTO;
import ru.innopolis.mappers.AddressMapper;
import ru.innopolis.service.AddressService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Адреса", description = "Контроллер для работы с адресами")
public class AddressController {
    // получения по id нет намеренно

    private final AddressService addressService;
    private final AddressMapper addressMapper;

    @GetMapping("/addresses")
    @Operation(summary = "Возвращает список адресов")
    public ResponseEntity<List<AddressRetrieveDTO>> getAddresses(
            @Parameter(description = "Идентификатор пользователя", example = "1") @RequestParam(required = false) Long userId
    ) {
        var addresses = addressService.listAddresses(userId);
        var body = addressMapper.map(addresses);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/address")
    @Operation(summary = "Создает адрес")
    public ResponseEntity<AddressRetrieveDTO> createAddress(@Valid @RequestBody AddressCreateDTO data) {
        var address = addressService.createAddress(data);
        var body = addressMapper.map(address);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/address/{id}")
    @Operation(summary = "Обновляет адрес")
    public ResponseEntity<AddressRetrieveDTO> updateAddress(
            @Parameter(description = "Идентификатор адреса", example = "1") @PathVariable(name = "id") Long addressId,
            @Valid @RequestBody AddressUpdateDTO data
    ) {
        var address = addressService.updateAddress(addressId, data);
        var body = addressMapper.map(address);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/address/{id}")
    @Operation(summary = "Удаляет адрес")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(
            @Parameter(description = "Идентификатор адреса", example = "1") @PathVariable(name = "id") Long addressId
    ) {
        addressService.softDeleteAddress(addressId);
    }

}
