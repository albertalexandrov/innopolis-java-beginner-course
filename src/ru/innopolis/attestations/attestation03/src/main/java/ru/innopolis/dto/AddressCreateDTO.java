package ru.innopolis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressCreateDTO {
    public String locality;
    public String street;
    public String house;
    public String porch;
    public String floor;
    public String apartment;
    public Long userId;
}
