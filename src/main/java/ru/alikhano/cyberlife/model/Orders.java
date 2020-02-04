package ru.alikhano.cyberlife.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.alikhano.cyberlife.converter.OrderStatusJpaConverter;
import ru.alikhano.cyberlife.converter.PaymentStatusJpaConverter;
import ru.alikhano.cyberlife.converter.PaymentTypeJpaConverter;
import ru.alikhano.cyberlife.model.enums.OrderStatus;
import ru.alikhano.cyberlife.model.enums.PaymentStatus;
import ru.alikhano.cyberlife.model.enums.PaymentType;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
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
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderId")
	private int orderId;

	@Column(name = "paymentType")
	@Convert(converter = PaymentTypeJpaConverter.class)
	@NotNull
	private PaymentType paymentType;

	@Column(name = "paymentStatus")
	@Convert(converter = PaymentStatusJpaConverter.class)
	@NotNull
	private PaymentStatus paymentStatus;

	@Column(name = "orderStatus")
	@Convert(converter = OrderStatusJpaConverter.class)
	@NotNull
	private OrderStatus orderStatus;

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

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
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

}
