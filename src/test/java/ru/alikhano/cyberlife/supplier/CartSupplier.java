package ru.alikhano.cyberlife.supplier;

import ru.alikhano.cyberlife.dto.CartDTO;
import ru.alikhano.cyberlife.dto.CartItemDTO;
import ru.alikhano.cyberlife.model.Cart;
import ru.alikhano.cyberlife.model.CartItem;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class CartSupplier {

    private static final Integer TEST_ID = 1;
    private static final Double TEST_GRAND_TOTAL = 1500.0;

    public static Cart getCartWithSpecifiedCartItems(CartItem... cartItems) {
        Cart cart = new Cart();
        cart.setCartId(TEST_ID);
        cart.setGrandTotal(TEST_GRAND_TOTAL);
        cart.setItems(new HashSet<>(Arrays.asList(cartItems)));

        return cart;
    }

    public static CartDTO getCartDTOWithSpecifiedCartItems(CartItemDTO... cartItems) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(TEST_ID);
        cartDTO.setGrandTotal(TEST_GRAND_TOTAL);
        cartDTO.setItems(new HashSet<>(Arrays.asList(cartItems)));

        return cartDTO;
    }

    public static Cart getCartWithSetCartItems() {
        Cart cart = new Cart();
        cart.setCartId(TEST_ID);
        cart.setGrandTotal(TEST_GRAND_TOTAL);
        cart.setItems(new HashSet<>(Collections.singletonList(CartItemSupplier.getCartItemWithSetProduct())));

        return cart;
    }

    public static CartDTO getCartDTOWithSetCartItems() {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(TEST_ID);
        cartDTO.setGrandTotal(TEST_GRAND_TOTAL);
        cartDTO.setItems(new HashSet<>(Collections.singletonList(CartItemSupplier.getCartItemDTOWithSetProduct())));

        return cartDTO;
    }

    public static Cart getEmptyCart() {
        Cart cart = new Cart();
        cart.setCartId(TEST_ID);
        cart.setGrandTotal(TEST_GRAND_TOTAL);

        return cart;
    }

    public static CartDTO getEmptyCartDTO() {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(TEST_ID);
        cartDTO.setGrandTotal(TEST_GRAND_TOTAL);

        return cartDTO;
    }
}
