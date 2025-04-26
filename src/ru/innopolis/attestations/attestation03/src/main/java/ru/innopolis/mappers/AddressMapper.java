package ru.innopolis.mappers;

import org.mapstruct.*;
import ru.innopolis.dto.AddressCreateDTO;
import ru.innopolis.dto.AddressRetrieveDTO;
import ru.innopolis.entity.Address;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface AddressMapper {

    @Mapping(target = "user", ignore = true)
    Address map(AddressCreateDTO data);

    AddressRetrieveDTO map(Address address);

    List<AddressRetrieveDTO> map(List<Address> addresses);
}
