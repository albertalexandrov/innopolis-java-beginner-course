package ru.innopolis.service;

import ru.innopolis.dto.CreateAddressDTO;
import ru.innopolis.dto.UpdateAddressDTO;
import ru.innopolis.entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAddresses(Long id);

    Address createAddress(CreateAddressDTO data);

    Address updateAddress(Long addressId, UpdateAddressDTO data);

    void softDeleteAddress(Long addressId);

}
