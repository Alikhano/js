package ru.alikhano.cyberlife.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ru.alikhano.cyberlife.dto.CategoryDTO;
import ru.alikhano.cyberlife.model.Category;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CategoryMapper {
	
	CategoryDTO categoryToCategoryDTO(Category category);
	Category categoryDTOtoCategory(CategoryDTO categoryDTO);

}
