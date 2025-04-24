package ru.innopolis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PizzaCreateUpdateDTO {
    @Schema(description = "Название", example = "Пеперони")
    public String name;
}
