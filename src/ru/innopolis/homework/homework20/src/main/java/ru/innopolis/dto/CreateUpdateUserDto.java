package ru.innopolis.dto;

import lombok.Data;

@Data
public class CreateUpdateUserDto {
    // UserDto при PUT и POST запросах не используется сознательно,
    // тк использование UserDto и при GET, и при PUT и POST запросах усложнит поддержку и понимание кода
    String firstName;
    String lastName;
}
