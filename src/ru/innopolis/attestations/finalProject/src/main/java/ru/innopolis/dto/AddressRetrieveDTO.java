package ru.innopolis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRetrieveDTO {

    @Schema(description = "Идентификатор адреса", example = "1")
    public Long id;

    @Schema(description = "Населенный пункт", example = "г. Казань")
    public String locality;

    @Schema(description = "Улица", example = "ул. Павлюхина")
    public String street;

    @Schema(description = "Дом", example = "1")
    public String house;

    @Schema(description = "Подъезд", example = "2")
    public String porch;

    @Schema(description = "Этаж", example = "3")
    public String floor;

    @Schema(description = "Квартира", example = "4")
    public String apartment;

    @Schema(description = "Пользователь")
    public UserRetrieveDTO user;

}