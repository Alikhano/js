package ru.alikhano.cyberlife.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ru.alikhano.cyberlife.dto.CartItemDTO;
import ru.alikhano.cyberlife.model.CartItem;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = { CartMapper.class,
		ProductMapper.class })
public interface CartItemMapper extends BiConverter<CartItem, CartItemDTO> {

}
