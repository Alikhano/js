package ru.alikhano.cyberlife.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
