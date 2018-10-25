package ru.alikhano.cyberlife.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="orderItem")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="orderItemId")
	private int orderItemId;
	
	@Column(name="orderQuantity")
	@NotNull
	@Min(value=1)
	private int orderQuantity;
	
	@Column(name="orderTotal")
	@NotNull
	@Min(value=100)
	private double orderTotal;
	
	@ManyToOne
	@JoinColumn(name="productId")
	@NotNull
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="orderId")
	@NotNull
	private Orders order;

	
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
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
		OrderItem other = (OrderItem) obj;
		if (orderItemId != other.orderItemId)
			return false;
		return true;
	}
	
	

	public OrderItem() {
	}

	public OrderItem(int orderItemId, @NotNull @Min(1) int orderQuantity, @NotNull @Min(100) double orderTotal,
			@NotNull Product product, @NotNull Orders order) {
		this.orderItemId = orderItemId;
		this.orderQuantity = orderQuantity;
		this.orderTotal = orderTotal;
		this.product = product;
		this.order = order;
	}
	
	
	
	

}
