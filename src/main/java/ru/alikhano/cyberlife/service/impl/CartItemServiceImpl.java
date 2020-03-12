package ru.alikhano.cyberlife.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.util.CollectionUtils;

import ru.alikhano.cyberlife.dto.CartDTO;
import ru.alikhano.cyberlife.dto.CartItemDTO;
import ru.alikhano.cyberlife.dto.ProductDTO;
import ru.alikhano.cyberlife.dao.CartItemDao;
import ru.alikhano.cyberlife.mapper.CartItemMapper;
import ru.alikhano.cyberlife.service.CartItemService;
import ru.alikhano.cyberlife.service.CartService;

@Service
public class CartItemServiceImpl implements CartItemService {

	private static final Logger LOGGER = LogManager.getLogger(CartItemServiceImpl.class);

    @Autowired
    private CartItemDao cartItemDao;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemMapper cartItemMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void create(CartItemDTO cartItemDTO) {
    	if (cartItemDTO == null) {
    	    LOGGER.info("Cart item is null");
    		return;
		}
        cartItemDao.create(cartItemMapper.backward(cartItemDTO));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void delete(CartItemDTO cartItemDTO) {
    	if (cartItemDTO == null) {
            LOGGER.info("Cart item is null");
    		return;
		}
        cartItemDao.delete(cartItemMapper.backward(cartItemDTO));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteAll(CartDTO cartDTO) {
    	if (cartDTO == null || CollectionUtils.isEmpty(cartDTO.getItems())) {
            LOGGER.info("Cart and/or cart items are null");
    		return;
		}
        for (CartItemDTO item : cartDTO.getItems()) {
            delete(item);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(CartItemDTO cartItemDTO) {
    	if (cartItemDTO == null) {
            LOGGER.info("Cart item is null");
    		return;
		}
        cartItemDao.update(cartItemMapper.backward(cartItemDTO));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void create(ProductDTO productDTO, CartDTO cartDTO, CartItemDTO cartItemDTO) {
        if (cartDTO == null || productDTO == null) {
            LOGGER.info("Cart and/or product are null - cannot create product");
            return;
        }

        CartItemDTO existingCartItemDTO = checkCartIfCartItemAlreadyExists(cartDTO, productDTO);

        if (existingCartItemDTO != null) {
            updateExistingCartItem(cartDTO, cartItemDTO, productDTO, existingCartItemDTO);

        } else {
            createNewCartItem(cartDTO, cartItemDTO, productDTO);
        }
    }

    private CartItemDTO checkCartIfCartItemAlreadyExists(CartDTO cartDTO, ProductDTO productDTO) {
        Set<CartItemDTO> items = cartDTO.getItems();

        if (!CollectionUtils.isEmpty(items)) {
            return items.stream()
                    .filter(item -> item.getProduct().getProductId().equals(productDTO.getProductId()))
                    .findFirst().orElse(null);
        }

        return null;
    }

    private void createNewCartItem(CartDTO cartDTO, CartItemDTO cartItemDTO, ProductDTO productDTO) {

        cartItemDTO.setTotalPrice(cartItemDTO.getQuantity() * productDTO.getPrice());
        cartItemDTO.setProduct(productDTO);
        cartItemDTO.setCart(cartDTO);

        if (CollectionUtils.isEmpty(cartDTO.getItems())) {
            cartDTO.setItems(new HashSet<>());
        }

        cartDTO.getItems().add(cartItemDTO);
        cartDTO.setGrandTotal(cartItemDTO.getTotalPrice() + cartDTO.getGrandTotal());
        cartService.update(cartDTO);

        create(cartItemDTO);

    }

    private void updateExistingCartItem(CartDTO cartDTO, CartItemDTO cartItemDTO, ProductDTO productDTO, CartItemDTO existingCartItemDTO) {
        double newPrice = cartItemDTO.getQuantity() * productDTO.getPrice();
        cartItemDTO.setQuantity(existingCartItemDTO.getQuantity() + cartItemDTO.getQuantity());

        cartItemDTO.setTotalPrice(existingCartItemDTO.getTotalPrice() + newPrice);
        cartDTO.setGrandTotal(newPrice + cartDTO.getGrandTotal());

        update(cartItemDTO);
    }

}
