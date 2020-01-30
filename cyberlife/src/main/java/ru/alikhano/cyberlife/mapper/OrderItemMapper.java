package ru.alikhano.cyberlife.mapper;

import org.mapstruct.Mapper;

import org.mapstruct.ReportingPolicy;
import ru.alikhano.cyberlife.dto.OrderItemDTO;
import ru.alikhano.cyberlife.model.OrderItem;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN,
		uses = {OrderMapper.class, ProductMapper.class, AddressMapper.class, RoleMapper.class})
public interface OrderItemMapper extends BiConverter<OrderItem, OrderItemDTO> {

}
