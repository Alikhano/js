package ru.alikhano.cyberlife.supplier;

import ru.alikhano.cyberlife.dto.CartItemDTO;
import ru.alikhano.cyberlife.dto.ProductDTO;
import ru.alikhano.cyberlife.model.CartItem;
import ru.alikhano.cyberlife.model.Product;

public class CartItemSupplier {

    private static final Integer TEST_ID = 1;
    private static final Integer TEST_QUANTITY = 1;
    private static final Double TEST_TOTAL_PRICE = 1500.0;
    private static final Integer TEST_PRODUCT_ID = 1;
    private static final String TEST_PRODUCT_MODEL = "rk800";

    public static CartItem getCartItemWithSetProduct() {
        CartItem cartItem = getEmptyCartItem();
        cartItem.setProduct(ProductSupplier.getProduct(TEST_PRODUCT_ID, TEST_PRODUCT_MODEL));

        return cartItem;
    }

    public static CartItemDTO getCartItemDTOWithSetProduct() {
        CartItemDTO cartItemDTO = getEmptyCartItemDTO();
        cartItemDTO.setProduct(ProductSupplier.getProductDTO(TEST_PRODUCT_ID, TEST_PRODUCT_MODEL));

        return cartItemDTO;
    }

    public static CartItem getCartItemWithSpecifiedProduct(Product product) {
        CartItem cartItem = getEmptyCartItem();
        cartItem.setProduct(product);

        return cartItem;
    }

    public static CartItemDTO getCartItemDTOWithSpecifiedProduct(ProductDTO productDTO) {
        CartItemDTO cartItemDTO = getEmptyCartItemDTO();
        cartItemDTO.setProduct(productDTO);

        return cartItemDTO;
    }

    public static CartItem getEmptyCartItem() {
        CartItem cartItem = new CartItem();
        cartItem.setItemId(TEST_ID);
        cartItem.setQuantity(TEST_QUANTITY);
        cartItem.setTotalPrice(TEST_TOTAL_PRICE);

        return cartItem;
    }

    public static CartItemDTO getEmptyCartItemDTO() {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setItemId(TEST_ID);
        cartItemDTO.setQuantity(TEST_QUANTITY);
        cartItemDTO.setTotalPrice(TEST_TOTAL_PRICE);

        return cartItemDTO;
    }

}
