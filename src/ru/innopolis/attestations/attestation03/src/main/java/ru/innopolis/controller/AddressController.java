package ru.innopolis.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.dto.CreateAddressDTO;
import ru.innopolis.dto.RetrieveAddressDTO;
import ru.innopolis.dto.UpdateAddressDTO;
import ru.innopolis.mappers.AddressMapper;
import ru.innopolis.service.AddressServiceImpl;

import java.util.List;

@RestController("Адреса")
@RequiredArgsConstructor
@Tag(name = "Адреса", description = "Контроллер для работы с адресами")
public class AddressController {
    // получения по id нет намеренно

    private final AddressServiceImpl addressService;
    private final AddressMapper addressMapper;

    @GetMapping("/addresses")
    @Operation(summary = "Возвращает список адресов")
    public ResponseEntity<List<RetrieveAddressDTO>> getAddresses(
            @Parameter(description = "Идентификатор пользователя", example = "1") @RequestParam(required = false) Long userId
    ) {
        // todo:
        //  тут не смог разобраться, как делать множественные фильтры
        //  в примере на странице https://www.restack.io/p/spring-boot-answer-search-filter-api-cat-ai
        //  предоставлен странный пример, которые может привести к комбинаторному взрыву:
        //   @Service
        //   public class ProductService {
        //     @Autowired
        //     private ProductRepository productRepository;
        //     public List<Product> searchProducts(String name, String category) {
        //        if (name != null && !name.isEmpty()) {
        //            return productRepository.findByNameContaining(name);
        //        } else if (category != null && !category.isEmpty()) {
        //            return productRepository.findByCategory(category);
        //        }
        //        return productRepository.findAll();
        //    }
        //  }
        var addresses = addressService.getAddresses(userId);
        var body = addressMapper.map(addresses);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/address")
    @Operation(summary = "Создает адрес")
    public ResponseEntity<RetrieveAddressDTO> createAddress(@RequestBody CreateAddressDTO data) {
        var address = addressService.createAddress(data);
        var body = addressMapper.map(address);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/address/{id}")
    @Operation(summary = "Обновляет адрес")
    public ResponseEntity<RetrieveAddressDTO> updateAddress(
            @Parameter(description = "Идентификатор адреса", example = "1") @PathVariable(name = "id") Long addressId,
            @RequestBody UpdateAddressDTO data
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

//    @PostMapping
//    @Operation(summary = "Создание адреса")
//    public ResponseEntity<Address> createAddress(@RequestBody CreateAddressDTO address) {
//
//    }

}
