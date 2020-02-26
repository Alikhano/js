package ru.alikhano.cyberlife.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.alikhano.cyberlife.model.OrderItem;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

	private Integer orderItemId;
	private Integer orderQuantity;
	private Double orderTotal;
	private ProductDTO product;
    private OrderDTO order;

	public OrderItemDTO (OrderItem item) {
		this.orderItemId = item.getOrderItemId();
		this.orderQuantity = item.getOrderQuantity();
		this.orderTotal = item.getOrderTotal();
	}

}
