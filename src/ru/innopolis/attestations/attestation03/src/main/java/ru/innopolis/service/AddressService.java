package ru.innopolis.service;

import ru.innopolis.dto.AddressCreateDTO;
import ru.innopolis.dto.AddressUpdateDTO;
import ru.innopolis.entity.Address;

import java.util.List;

public interface AddressService {

    Address createAddress(AddressCreateDTO data);
    List<Address> listAddresses(Long id);
    Address updateAddress(Long addressId, AddressUpdateDTO data);
    void softDeleteAddress(Long addressId);

}
