package ru.innopolis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderUpdateDTO {

    @Schema(description = "Идентификатор пиццы")
    private Long pizzaId;

    @Schema(description = "Идентификатор адреса")
    private Long addressId;

}
