package ru.innopolis.dto;

import lombok.Data;

@Data
public class CreateUpdateTvDto {
    // TvDto при PUT и POST запросах не используется сознательно,
    // тк использование TvDto и при GET, и при PUT и POST запросах усложнит поддержку и понимание кода
    String brand;
    String model;
    String screen;
}
