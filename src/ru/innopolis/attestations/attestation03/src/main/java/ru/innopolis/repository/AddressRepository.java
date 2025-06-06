package ru.innopolis.repository;

import org.springframework.data.repository.CrudRepository;
import ru.innopolis.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, Long> {
    List<Address> findAllByIsDeleted(Boolean isDeleted);
    List<Address> findAllByUserIdAndIsDeleted(Long userId, Boolean isDeleted);
    Optional<Address> findFirstByIdAndIsDeleted(Long id, Boolean isDeleted);
}
