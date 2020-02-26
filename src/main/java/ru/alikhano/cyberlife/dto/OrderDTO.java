package ru.alikhano.cyberlife.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.alikhano.cyberlife.dto.enums.OrderStatusDTO;
import ru.alikhano.cyberlife.dto.enums.PaymentStatusDTO;
import ru.alikhano.cyberlife.dto.enums.PaymentTypeDTO;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

	private Integer orderId;
	private PaymentTypeDTO   paymentType;
	private PaymentStatusDTO paymentStatus;
	private OrderStatusDTO orderStatus;
	private CustomerDTO customer;

	private Double orderPrice;
	private Date orderDate;

	Set<OrderItemDTO> orderItems;

	public void addItem(OrderItemDTO item) {
		orderItems.add(item);
		item.setOrder(this);
	}

}
