package ru.alikhano.cyberlife.supplier;

import ru.alikhano.cyberlife.dto.OrderItemDTO;

public class OrderItemSupplier {

    private static final Integer TEST_QUANTITY    = 2;
    private static final Double  TEST_ORDER_TOTAL = 1500.0;

    public static OrderItemDTO getOrderItemDTO() {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setOrderItemId(1);
        orderItemDTO.setOrderQuantity(TEST_QUANTITY);
        orderItemDTO.setProduct(ProductSupplier.getProductDTO());
        orderItemDTO.setOrderTotal(TEST_ORDER_TOTAL);
        orderItemDTO.setOrder(OrderSupplier.getOrderDTO());

        return orderItemDTO;
    }
}
