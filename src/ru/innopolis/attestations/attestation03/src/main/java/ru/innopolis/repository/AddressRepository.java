package ru.innopolis.repository;

import org.springframework.data.repository.CrudRepository;
import ru.innopolis.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, Long> {
    List<Address> findAddressesByIsDeleted(Boolean isDeleted);
    List<Address> findAddressesByUserIdAndIsDeleted(Long userId, Boolean isDeleted);
    Optional<Address> findByIdAndIsDeleted(Long id, Boolean isDeleted);
}
