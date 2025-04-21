package ru.innopolis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAddressDTO {

    public String locality;
    public String street;
    public String house;
    public String porch;
    public String floor;
    public String apartment;
    // тут нет userId

}
