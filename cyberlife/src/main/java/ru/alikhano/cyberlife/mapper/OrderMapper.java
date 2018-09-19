package ru.alikhano.cyberlife.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ru.alikhano.cyberlife.DTO.OrderDTO;
import ru.alikhano.cyberlife.model.Orders;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses= {OrderItemMapper.class})
public interface OrderMapper {
	
	OrderDTO orderToOrderDTO(Orders order);
	Orders orderDTOtoOder(OrderDTO orderDTO);

}
