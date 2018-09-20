package ru.alikhano.cyberlife.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import ru.alikhano.cyberlife.DTO.ProductInfo;
import ru.alikhano.cyberlife.model.Product;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ProductInfoMapper {
	
	@Mapping(source="product.category.catType", target="category")
	@Mapping(source="product.cons.level", target="cons")
	ProductInfo productToProductInfo(Product product);
	
	@Mapping(source="category", target="category.catType")
	@Mapping(source="cons", target="cons.level")
	Product productInfoToProduct(ProductInfo productInfo);

}
