package ru.alikhano.cyberlife.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ru.alikhano.cyberlife.DTO.CartDTO;
import ru.alikhano.cyberlife.DTO.CartItemDTO;
import ru.alikhano.cyberlife.DTO.CategoryDTO;
import ru.alikhano.cyberlife.DTO.ConsDTO;
import ru.alikhano.cyberlife.DTO.ProductDTO;
import ru.alikhano.cyberlife.model.Cart;
import ru.alikhano.cyberlife.model.CartItem;
import ru.alikhano.cyberlife.model.Category;
import ru.alikhano.cyberlife.model.Consciousness;
import ru.alikhano.cyberlife.model.Product;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses= {CartItemMapper.class})
public interface CartMapper {
	
	CartDTO cartToCartDTO(Cart cart);
	Cart cartDTOtoCart(CartDTO cartDTO);
	
}
