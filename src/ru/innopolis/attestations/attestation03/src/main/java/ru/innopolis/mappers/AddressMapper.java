package ru.innopolis.mappers;

import org.mapstruct.*;
import ru.innopolis.dto.CreateAddressDTO;
import ru.innopolis.dto.RetrieveAddressDTO;
import ru.innopolis.entity.Address;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface AddressMapper {

    @Mapping(target = "user", ignore = true)
    Address map(CreateAddressDTO data);

    @Mapping(source = "user.id", target = "userId")
    RetrieveAddressDTO map(Address address);

    List<RetrieveAddressDTO> map(List<Address> addresses);
}
