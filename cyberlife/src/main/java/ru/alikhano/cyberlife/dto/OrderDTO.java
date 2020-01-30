package ru.alikhano.cyberlife.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.alikhano.cyberlife.dto.enums.OrderStatusDTO;
import ru.alikhano.cyberlife.dto.enums.PaymentStatusDTO;
import ru.alikhano.cyberlife.dto.enums.PaymentTypeDTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

	private int              orderId;
	private PaymentTypeDTO   paymentType;
	private PaymentStatusDTO paymentStatus;
	private OrderStatusDTO   orderStatus;
	private CustomerDTO      customer;

	private double orderPrice;
	private Date orderDate;

	Set<OrderItemDTO> orderedItems = new HashSet<>(0);

	public void addItem(OrderItemDTO item) {
		orderedItems.add(item);
		item.setOrder(this);
	}
	
	public void removeItem(OrderItemDTO item) {
		orderedItems.remove(item);
		item.setOrder(null);
	}
}
