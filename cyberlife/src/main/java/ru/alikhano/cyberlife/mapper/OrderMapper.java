package ru.alikhano.cyberlife.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ru.alikhano.cyberlife.dto.OrderDTO;
import ru.alikhano.cyberlife.model.Orders;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses= {OrderItemMapper.class,
		AddressMapper.class, RoleMapper.class})
public interface OrderMapper extends BiConverter<Orders, OrderDTO> {

}
