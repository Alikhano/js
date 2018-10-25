package ru.alikhano.cyberlife.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderId")
	private int orderId;

	@Column(name = "paymentType")
	@NotNull
	private String paymentType;

	@Column(name = "paymentStatus")
	@NotNull
	private String paymentStatus;

	@Column(name = "orderStatus")
	@NotNull
	private String orderStatus;

	@OneToOne
	@JoinColumn(name = "customerId")
	@NotNull
	Customer customer;

	@Column(name = "orderPrice")
	@NotNull
	@Min(value=100)
	private double orderPrice;

	@Column(name = "orderDate")
	@Temporal(TemporalType.DATE)
	@NotNull
	private Date orderDate;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	Set<OrderItem> orderedItems = new HashSet<>(0);

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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
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

	public Set<OrderItem> getOrderedItems() {
		return orderedItems;
	}

	public void setOrderedItems(Set<OrderItem> orderedItems) {
		this.orderedItems = orderedItems;
	}

	public void addOrderItem(OrderItem item) {
		orderedItems.add(item);
		item.setOrder(this);
		
	}

	public void removeOrderItem(OrderItem item) {
		orderedItems.remove(item);
		item.setOrder(null);
	}
	
	/*public void addItem(OrderItem item) {
		orderedItems.add(item);
		item.setOrder(this);
	}
	
	public void removeItem(OrderItem item) {
		orderedItems.remove(item);
		item.setOrder(null);
	}
*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + orderId;
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
		Orders other = (Orders) obj;
		if (orderId != other.orderId)
			return false;
		return true;
	}

	public Orders(int orderId, @NotNull String paymentType, @NotNull String paymentStatus, @NotNull String orderStatus,
			@NotNull Customer customer, @NotNull @Min(100) double orderPrice, @NotNull Date orderDate,
			Set<OrderItem> orderedItems) {
		this.orderId = orderId;
		this.paymentType = paymentType;
		this.paymentStatus = paymentStatus;
		this.orderStatus = orderStatus;
		this.customer = customer;
		this.orderPrice = orderPrice;
		this.orderDate = orderDate;
		this.orderedItems = orderedItems;
	}

	public Orders() {
		
	}
	
	
	
	

}
