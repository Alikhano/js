package ru.alikhano.cyberlife.DTO;

import ru.alikhano.cyberlife.model.OrderItem;

public class OrderItemDTO {
	

	private int orderItemId;
	private int orderQuantity;
	private double orderTotal;
	private ProductDTO product;
    private OrderDTO order;
	public int getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	public int getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public double getOrderTotal() {
		return orderTotal;
	}
	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	public OrderDTO getOrder() {
		return order;
	}
	public void setOrder(OrderDTO order) {
		this.order = order;
	}
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
	
	public OrderItemDTO() {
		
	}
	
	public OrderItemDTO (OrderItem item) {
		this.orderItemId = item.getOrderItemId();
		this.orderQuantity = item.getOrderQuantity();
		this.orderTotal = item.getOrderTotal();
	}
    


}
