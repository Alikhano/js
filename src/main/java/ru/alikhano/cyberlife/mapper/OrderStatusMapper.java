package ru.alikhano.cyberlife.mapper;

import org.mapstruct.Mapper;
import ru.alikhano.cyberlife.dto.enums.OrderStatusDTO;
import ru.alikhano.cyberlife.model.enums.OrderStatus;

@Mapper(componentModel="spring")
public interface OrderStatusMapper extends BiConverter<OrderStatus, OrderStatusDTO> {

}
