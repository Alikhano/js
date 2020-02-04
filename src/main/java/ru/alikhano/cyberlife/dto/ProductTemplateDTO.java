package ru.alikhano.cyberlife.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductTemplateDTO {

    private ProductDTO product;

    List<CategoryDTO> availableCategories;

    List<ConsciousnessDTO> availableConsciousnessLevels;
}
