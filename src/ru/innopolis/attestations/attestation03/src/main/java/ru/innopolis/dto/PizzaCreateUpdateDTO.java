package ru.innopolis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PizzaCreateUpdateDTO {
    @Schema(description = "Название", example = "Пеперони")
    public String name;
}
