package ru.innopolis.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.innopolis.dto.UserCreateUpdateDTO;
import ru.innopolis.dto.UserRetrieveDTO;
import ru.innopolis.entity.User;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {
    User map(UserCreateUpdateDTO data);
    UserRetrieveDTO map(User user);
    List<UserRetrieveDTO> map(List<User> users);
}
