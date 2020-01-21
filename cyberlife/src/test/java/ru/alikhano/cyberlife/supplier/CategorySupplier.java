package ru.alikhano.cyberlife.supplier;

import ru.alikhano.cyberlife.dto.CategoryDTO;
import ru.alikhano.cyberlife.model.Category;

import java.util.Collections;
import java.util.List;

public class CategorySupplier {

    private static final String TEST_CATEGORY_TYPE = "education";
    private static final Integer TEST_CATEGORY_ID = 1;

    public static List<Category> getCategories() {
        return Collections.singletonList(getCategory());
    }

    public static Category getCategory() {
        Category category = new Category();
        category.setCategoryId(TEST_CATEGORY_ID);
        category.setCategoryType(TEST_CATEGORY_TYPE);

        return category;
    }

    public static CategoryDTO getCategoryDTO() {
        CategoryDTO categoryDTO =  new CategoryDTO();
        categoryDTO.setCategoryId(TEST_CATEGORY_ID);
        categoryDTO.setCategoryType(TEST_CATEGORY_TYPE);

        return categoryDTO;
    }
}
