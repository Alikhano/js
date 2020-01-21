package ru.alikhano.cyberlife.supplier;

import ru.alikhano.cyberlife.dto.ProductDTO;
import ru.alikhano.cyberlife.model.Product;

public class ProductSupplier {

    private static final String TEST_DESCRIPTION = "test_description";
    private static final String TEST_MODEL = "rk800";
    private static final Integer TEST_UNITS_IN_STOCK = 5;
    private static final Double TEST_PRICE = 1500.0;

    public static Product getProduct(Integer id, String model) {
        Product product = new Product();
        product.setProductId(id);
        product.setModel(model);
        product.setDescription(TEST_DESCRIPTION);
        product.setUnitsInStock(TEST_UNITS_IN_STOCK);
        product.setPrice(TEST_PRICE);
        product.setCategory(CategorySupplier.getCategory());
        product.setCons(ConsciousnessSupplier.getConsciousness());

        return product;

    }

    public static ProductDTO getProductDTO(Integer id, String model) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(id);
        productDTO.setModel(model);
        productDTO.setDescription(TEST_DESCRIPTION);
        productDTO.setUnitsInStock(TEST_UNITS_IN_STOCK);
        productDTO.setPrice(TEST_PRICE);
        productDTO.setCategory(CategorySupplier.getCategoryDTO());
        productDTO.setCons(ConsciousnessSupplier.getConsciousnessDTO());

        return productDTO;
    }

    public static ProductDTO getProductDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(1);
        productDTO.setModel(TEST_MODEL);
        productDTO.setDescription(TEST_DESCRIPTION);
        productDTO.setUnitsInStock(TEST_UNITS_IN_STOCK);
        productDTO.setPrice(TEST_PRICE);
        productDTO.setCategory(CategorySupplier.getCategoryDTO());
        productDTO.setCons(ConsciousnessSupplier.getConsciousnessDTO());

        return productDTO;
    }


}
