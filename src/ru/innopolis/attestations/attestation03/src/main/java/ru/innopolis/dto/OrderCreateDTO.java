package ru.innopolis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreateDTO {

    @Schema(description = "Идентификатор пользователя", example = "1")
    private Long userId;

    @Schema(description = "Идентификатор пиццы", example = "1")
    private Long pizzaId;

    @Schema(description = "Идентификатор адреса доставки", example = "1")
    private Long addressId;

}
