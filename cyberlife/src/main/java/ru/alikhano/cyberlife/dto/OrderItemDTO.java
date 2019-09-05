package ru.alikhano.cyberlife.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.alikhano.cyberlife.model.OrderItem;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemDTO {
	private int orderItemId;
	private int orderQuantity;
	private double orderTotal;
	private ProductDTO product;
    private OrderDTO order;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + orderItemId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItemDTO other = (OrderItemDTO) obj;
		if (orderItemId != other.orderItemId)
			return false;
		return true;
	}
	
	public OrderItemDTO (OrderItem item) {
		this.orderItemId = item.getOrderItemId();
		this.orderQuantity = item.getOrderQuantity();
		this.orderTotal = item.getOrderTotal();
	}
}
