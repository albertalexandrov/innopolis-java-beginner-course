package ru.innopolis.service;

import ru.innopolis.dto.AddressCreateDTO;
import ru.innopolis.dto.AddressUpdateDTO;
import ru.innopolis.entity.Address;

import java.util.List;

public interface AddressService {
    Address createAddress(AddressCreateDTO data);
    Address getAddress(Long id);
    List<Address> listAddresses(Long userId);
    Address updateAddress(Long addressId, AddressUpdateDTO data);
    void softDeleteAddress(Long addressId);
}
