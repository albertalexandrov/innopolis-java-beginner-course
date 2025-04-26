package ru.innopolis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRetrieveDTO {

    @Schema(description = "Идентификатор заказа", example = "1")
    public Long id;

    @Schema(description = "Пользователь")
    public UserRetrieveDTO user;

    @Schema(description = "Пицца")
    public PizzaRetrieveDTO pizza;

    @Schema(description = "Адреса доставки")
    public AddressRetrieveDTO address;

}
