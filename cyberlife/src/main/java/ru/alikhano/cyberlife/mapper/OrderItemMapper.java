package ru.alikhano.cyberlife.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import ru.alikhano.cyberlife.dto.OrderDTO;
import ru.alikhano.cyberlife.dto.OrderItemDTO;
import ru.alikhano.cyberlife.model.OrderItem;
import ru.alikhano.cyberlife.model.Orders;
import ru.alikhano.cyberlife.model.Role;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN,
		uses = {OrderMapper.class, ProductMapper.class, AddressMapper.class, RoleMapper.class})
public interface OrderItemMapper {
	
	@Mappings({
		@Mapping(target = "order", qualifiedByName = "orderDTOtoOrder") })
	OrderItem orderItemDTOtoOrderItem(OrderItemDTO orderItemDTO);

	@Mappings({
			@Mapping(target = "order", qualifiedByName = "orderToOrderDTO") })
	OrderItemDTO orderItemToOrderItemDTO(OrderItem orderItem);

	@Named("orderToOrderDTO")
	@Mappings({ @Mapping(target = "orderedItems", expression = "java(null)") })
	OrderDTO orderToOrderDTO(Orders order);
	
	@Named("orderDTOtoOrder")
	@Mappings({ @Mapping(target = "orderedItems", expression = "java(null)") })
	Orders orderDTOtoOrder(OrderDTO orderDTO);

}
