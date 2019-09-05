package ru.alikhano.cyberlife.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {

	private int orderId;
	private String paymentType;
	private String paymentStatus;
	private String orderStatus;
	private CustomerDTO customer;

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
