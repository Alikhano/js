package ru.alikhano.cyberlife.supplier;

import ru.alikhano.cyberlife.dto.OrderDTO;
import ru.alikhano.cyberlife.model.Orders;

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
        orderDTO.setCustomer(CustomerSupplier.getCustomerDTO());

        return orderDTO;
    }

    public static Orders getOrder() {
        Orders order = new Orders();
        order.setOrderId(1);
        order.setPaymentType(TEST_PAYMENT_TYPE);
        order.setPaymentStatus(TEST_PAYMENT_STATUS);
        order.setOrderStatus(TEST_ORDER_STATUS);
        order.setOrderPrice(TEST_ORDER_PRICE);
        order.setOrderDate(new Date());
        order.setOrderedItems(Collections.singleton(OrderItemSupplier.getOrderItem()));
        order.setCustomer(CustomerSupplier.getCustomer());

        return order;
    }

    public static OrderDTO getEmptyOrderDTO() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(1);
        orderDTO.setPaymentType(TEST_PAYMENT_TYPE);
        orderDTO.setPaymentStatus(TEST_PAYMENT_STATUS);
        orderDTO.setOrderStatus(TEST_ORDER_STATUS);
        orderDTO.setOrderPrice(TEST_ORDER_PRICE);
        orderDTO.setOrderDate(new Date());
        orderDTO.setCustomer(CustomerSupplier.getCustomerDTO());

        return orderDTO;
    }

    public static Orders getEmptyOrder() {
        Orders order = new Orders();
        order.setOrderId(1);
        order.setPaymentType(TEST_PAYMENT_TYPE);
        order.setPaymentStatus(TEST_PAYMENT_STATUS);
        order.setOrderStatus(TEST_ORDER_STATUS);
        order.setOrderPrice(TEST_ORDER_PRICE);
        order.setOrderDate(new Date());
        order.setCustomer(CustomerSupplier.getCustomer());

        return order;
    }

    public static Orders getCustomizedOrder(Integer id, String paymentType, String paymentStatus) {
        Orders order = new Orders();
        order.setOrderId(id);
        order.setPaymentType(paymentType);
        order.setPaymentStatus(paymentStatus);
        order.setOrderStatus(TEST_ORDER_STATUS);
        order.setOrderPrice(TEST_ORDER_PRICE);
        order.setOrderDate(new Date());
        order.setOrderedItems(Collections.singleton(OrderItemSupplier.getOrderItem()));
        order.setCustomer(CustomerSupplier.getCustomer());

        return order;
    }

}
