package ru.alikhano.cyberlife.supplier;

import ru.alikhano.cyberlife.dto.OrderItemDTO;
import ru.alikhano.cyberlife.model.OrderItem;

public class OrderItemSupplier {

    private static final Integer TEST_QUANTITY = 2;
    private static final Double  TEST_ORDER_TOTAL = 1500.0;

    public static OrderItemDTO getOrderItemDTO() {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setOrderItemId(1);
        orderItemDTO.setOrderQuantity(TEST_QUANTITY);
        orderItemDTO.setProduct(ProductSupplier.getProductDTO());
        orderItemDTO.setOrderTotal(TEST_ORDER_TOTAL);

        return orderItemDTO;
    }

    public static OrderItem getOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(1);
        orderItem.setOrderQuantity(TEST_QUANTITY);
        orderItem.setProduct(ProductSupplier.getProduct());
        orderItem.setOrderTotal(TEST_ORDER_TOTAL);

        return orderItem;
    }
}
