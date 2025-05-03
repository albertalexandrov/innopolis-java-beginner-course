package ru.innopolis.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.innopolis.dto.PizzaCreateUpdateDTO;
import ru.innopolis.dto.PizzaRetrieveDTO;
import ru.innopolis.entity.Pizza;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PizzaMapper {
    Pizza map(PizzaCreateUpdateDTO data);
    PizzaRetrieveDTO map(Pizza pizza);
    List<PizzaRetrieveDTO> map(List<Pizza> pizzas);
}
