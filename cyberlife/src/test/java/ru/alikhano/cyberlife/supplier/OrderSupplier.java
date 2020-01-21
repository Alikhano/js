package ru.alikhano.cyberlife.supplier;

import ru.alikhano.cyberlife.dto.OrderDTO;

import java.util.Collections;
import java.util.Date;

public class OrderSupplier {

    private static final String TEST_PAYMENT_TYPE = "credit card";
    private static final String TEST_PAYMENT_STATUS = "unpaid";
    private static final String TEST_ORDER_STATUS = "awaits delivery";
    private static final Double  TEST_ORDER_PRICE = 1500.0;

    public static OrderDTO getOrderDTO() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(1);
        orderDTO.setPaymentType(TEST_PAYMENT_TYPE);
        orderDTO.setPaymentStatus(TEST_PAYMENT_STATUS);
        orderDTO.setOrderStatus(TEST_ORDER_STATUS);
        orderDTO.setOrderPrice(TEST_ORDER_PRICE);
        orderDTO.setOrderDate(new Date());
        orderDTO.setOrderedItems(Collections.singleton(OrderItemSupplier.getOrderItemDTO()));

        return orderDTO;
    }

}
