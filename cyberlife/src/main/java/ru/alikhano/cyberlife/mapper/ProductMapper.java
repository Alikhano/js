package ru.alikhano.cyberlife.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ru.alikhano.cyberlife.dto.CategoryDTO;
import ru.alikhano.cyberlife.dto.ConsciousnessDTO;
import ru.alikhano.cyberlife.dto.ProductDTO;
import ru.alikhano.cyberlife.model.Category;
import ru.alikhano.cyberlife.model.Consciousness;
import ru.alikhano.cyberlife.model.Product;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ProductMapper {

	ProductDTO productToProductDTO(Product product);
	Product productDTOtOProduct(ProductDTO productDTO);
	
	CategoryDTO categoryToCategoryDTO(Category category);
	Category categoryDTOtoCategory(CategoryDTO categoryDTO);
	
	ConsciousnessDTO consToConsDTO(Consciousness cons);
	Consciousness consDTOtoCons(ConsciousnessDTO consciousnessDTO);

}
