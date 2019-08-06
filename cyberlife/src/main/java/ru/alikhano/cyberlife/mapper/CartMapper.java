package ru.alikhano.cyberlife.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ru.alikhano.cyberlife.dto.CartDTO;
import ru.alikhano.cyberlife.model.Cart;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses= {CartItemMapper.class})
public interface CartMapper {
	
	CartDTO cartToCartDTO(Cart cart);
	Cart cartDTOtoCart(CartDTO cartDTO);
	
}
