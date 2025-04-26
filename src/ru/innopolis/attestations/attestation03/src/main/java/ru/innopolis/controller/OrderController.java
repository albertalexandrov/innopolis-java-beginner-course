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
import ru.innopolis.dto.AddressUpdateDTO;
import ru.innopolis.dto.OrderCreateDTO;
import ru.innopolis.dto.OrderRetrieveDTO;
import ru.innopolis.dto.OrderUpdateDTO;
import ru.innopolis.entity.Order;
import ru.innopolis.mappers.OrderMapper;
import ru.innopolis.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Заказы", description = "Контроллер для работы с заказами")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @DeleteMapping("/order/{id}")
    @Operation(summary = "Удаляет заказ")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(
            @Parameter(description = "Идентификатор заказа", example = "1") @PathVariable("id") Long orderId
    ) {
        orderService.deleteOrder(orderId);
    }

    @PostMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Создает заказ")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderRetrieveDTO> createOrder(@Valid @RequestBody OrderCreateDTO data) {
        var order = orderService.createOrder(data);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderMapper.map(order));
    }

    @GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Возвращает список заказов")
    public ResponseEntity<List<OrderRetrieveDTO>> getOrders(
            @Parameter(description = "Идентификатор пользователя", example = "1") @RequestParam(required = false) Long userId
    ) {
        var orders = orderService.getOrders(userId);
        return ResponseEntity.ok(orderMapper.map(orders));
    }

    @GetMapping(value = "/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Возвращает заказ")
    public ResponseEntity<OrderRetrieveDTO> getOrder(
            @Parameter(description = "Идентификатор заказа", example = "1") @PathVariable("id") Long orderId
    ) {
        var order = orderService.getOrder(orderId);
        return ResponseEntity.ok(orderMapper.map(order));
    }

    @PutMapping(value = "/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Обновляет заказ")
    public ResponseEntity<OrderRetrieveDTO> updateOrder(
            @Parameter(description = "Идентификатор заказа", example = "1") @PathVariable(name = "id") Long orderId,
            @Valid @RequestBody OrderUpdateDTO data
    ) {
        var order = orderService.updateOrder(orderId, data);
        return ResponseEntity.ok(orderMapper.map(order));
    }

}
