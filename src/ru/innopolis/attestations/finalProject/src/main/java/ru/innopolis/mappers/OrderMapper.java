package ru.innopolis.mappers;

import org.mapstruct.*;
import ru.innopolis.dto.OrderCreateDTO;
import ru.innopolis.dto.OrderRetrieveDTO;
import ru.innopolis.entity.Order;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface OrderMapper {
    Order map(OrderCreateDTO data);
    OrderRetrieveDTO map(Order order);
    List<OrderRetrieveDTO> map(List<Order> orders);
}
