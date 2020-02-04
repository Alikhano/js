package ru.alikhano.cyberlife.supplier;

import ru.alikhano.cyberlife.dto.OrderDTO;
import ru.alikhano.cyberlife.dto.enums.OrderStatusDTO;
import ru.alikhano.cyberlife.dto.enums.PaymentStatusDTO;
import ru.alikhano.cyberlife.dto.enums.PaymentTypeDTO;
import ru.alikhano.cyberlife.model.Orders;
import ru.alikhano.cyberlife.model.enums.OrderStatus;
import ru.alikhano.cyberlife.model.enums.PaymentStatus;
import ru.alikhano.cyberlife.model.enums.PaymentType;

import java.util.Collections;
import java.util.Date;

public class OrderSupplier {

    private static final PaymentType      TEST_PAYMENT_TYPE   = PaymentType.CREDIT_CART;
    private static final PaymentStatus    TEST_PAYMENT_STATUS = PaymentStatus.UNPAID;
    private static final OrderStatus      TEST_ORDER_STATUS   = OrderStatus.AWAITS_DELIVERY;
    private static final PaymentTypeDTO   TEST_PAYMENT_TYPE_DTO   = PaymentTypeDTO.CREDIT_CART;
    private static final PaymentStatusDTO TEST_PAYMENT_STATUS_DTO = PaymentStatusDTO.UNPAID;
    private static final OrderStatusDTO   TEST_ORDER_STATUS_DTO   = OrderStatusDTO.AWAITS_DELIVERY;
    private static final Double           TEST_ORDER_PRICE    = 1500.0;

    public static OrderDTO getOrderDTO() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(1);
        orderDTO.setPaymentType(TEST_PAYMENT_TYPE_DTO);
        orderDTO.setPaymentStatus(TEST_PAYMENT_STATUS_DTO);
        orderDTO.setOrderStatus(TEST_ORDER_STATUS_DTO);
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
        orderDTO.setPaymentType(TEST_PAYMENT_TYPE_DTO);
        orderDTO.setPaymentStatus(TEST_PAYMENT_STATUS_DTO);
        orderDTO.setOrderStatus(TEST_ORDER_STATUS_DTO);
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

    public static Orders getCustomizedOrder(Integer id, PaymentType paymentType, PaymentStatus paymentStatus) {
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
