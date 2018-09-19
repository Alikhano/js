package ru.alikhano.cyberlife.DTO;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ru.alikhano.cyberlife.model.OrderItem;

public class OrderDTO {
	
	private int orderId;
	private String paymentType;
	private String paymentStatus;
	private String orderStatus;
	private CustomerDTO customer;

	private double orderPrice;
	private Date orderDate;
	
	Set<OrderItemDTO> orderedItems;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public CustomerDTO getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}
	public double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Set<OrderItemDTO> getOrderedItems() {
		return orderedItems;
	}
	public void setOrderedItems(Set<OrderItemDTO> orderedItems) {
		this.orderedItems = orderedItems;
	}
	
	
}
