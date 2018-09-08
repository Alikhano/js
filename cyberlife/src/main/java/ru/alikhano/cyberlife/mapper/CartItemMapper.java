package ru.alikhano.cyberlife.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ru.alikhano.cyberlife.DTO.CartItemDTO;
import ru.alikhano.cyberlife.model.CartItem;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CartItemMapper {
	
	CartItemDTO cartItemToCartItemDTO(CartItem carItem);
	CartItem cartDTOtoCartItem(CartItemDTO cartItemDTO);

}
