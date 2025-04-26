package ru.innopolis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innopolis.dto.AddressCreateDTO;
import ru.innopolis.dto.AddressUpdateDTO;
import ru.innopolis.entity.Address;
import ru.innopolis.exceptions.BadRequestException;
import ru.innopolis.mappers.AddressMapper;
import ru.innopolis.repository.AddressRepository;
import ru.innopolis.repository.UserRepository;
import ru.innopolis.exceptions.AddressNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final AddressMapper addressMapper;

    @Override
    public List<Address> listAddresses(Long userId) {
        if (userId == null) {
            return addressRepository.findAddressesByIsDeleted(false);
        } else {
            return addressRepository.findAddressesByUserIdAndIsDeleted(userId, false);
        }
    }

    @Override
    public Address createAddress(AddressCreateDTO data) {
        var address = addressMapper.map(data);
        var user = userRepository
                .findFirstByIdAndIsDeleted(data.getUserId(), false)
                .orElseThrow(() -> new BadRequestException("Пользователь не найден"));
        address.setUser(user);
        address.setIsDeleted(false);
        addressRepository.save(address);
        return address;
    }

    @Override
    public Address updateAddress(Long addressId, AddressUpdateDTO data) {
        Address address = addressRepository.findFirstByIdAndIsDeleted(addressId, false).orElseThrow(AddressNotFoundException::new);
        address.setLocality(data.getLocality());
        address.setStreet(data.getStreet());
        address.setHouse(data.getHouse());
        address.setPorch(data.getPorch());
        address.setFloor(data.getFloor());
        address.setApartment(data.getApartment());
        addressRepository.save(address);
        return address;
    }

    @Override
    public void softDeleteAddress(Long addressId) {
        Address address = addressRepository.findFirstByIdAndIsDeleted(addressId, false).orElseThrow(AddressNotFoundException::new);
        address.setIsDeleted(true);
        addressRepository.save(address);
    }

}
