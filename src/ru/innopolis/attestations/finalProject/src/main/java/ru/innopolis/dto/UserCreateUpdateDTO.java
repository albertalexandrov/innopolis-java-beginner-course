package ru.innopolis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserCreateUpdateDTO {

    @Schema(description = "Имя", example = "Иван")
    public String firstName;

    @Schema(description = "Фамилия", example = "Иванов")
    public String lastName;

    @Schema(description = "Номер телефона", example = "+79171742521")
    public String phoneNumber;

}
