package ru.alikhano.cyberlife.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ru.alikhano.cyberlife.dto.ProductDTO;
import ru.alikhano.cyberlife.model.Product;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.WARN, uses = {CategoryMapper.class,
		ConsciousnessMapper.class})
public interface ProductMapper extends BiConverter<Product, ProductDTO> {

}
