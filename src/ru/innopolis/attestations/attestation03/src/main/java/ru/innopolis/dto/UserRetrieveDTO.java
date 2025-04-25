package ru.innopolis.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserRetrieveDTO {

    @Schema(example = "1")
    public Long id;

    @Schema(description = "Имя", example = "Иван")
    public String firstName;

    @Schema(description = "Фамилия", example = "Иванов")
    public String lastName;

    @Schema(description = "Номер телефона", example = "+79171742521")
    public String phoneNumber;

}
